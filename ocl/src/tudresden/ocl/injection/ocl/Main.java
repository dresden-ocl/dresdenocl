/*
Copyright (C) 2000  Ralf Wiebicke

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package tudresden.ocl.injection.ocl;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.lang.reflect.Modifier;
import tudresden.ocl.lib.NameAdapter;
import tudresden.ocl.lib.SimpleNameAdapter;
import tudresden.ocl.lib.ArgoNameAdapter;
import tudresden.ocl.parser.OclParserException;
import tudresden.ocl.check.types.ModelFacade;
import tudresden.ocl.check.types.ReflectionFacade;
import tudresden.ocl.check.types.DefaultReflectionAdapter;
import tudresden.ocl.check.OclTypeException;
import tudresden.ocl.injection.lib.Invariant;
import tudresden.ocl.injection.lib.Check;
import tudresden.ocl.injection.lib.HashExact;
import tudresden.ocl.injection.lib.HashSize;
import tudresden.ocl.injection.lib.HashModCount;
import tudresden.ocl.injection.lib.WrapperDummy;
import tudresden.ocl.injection.lib.TypeTracer;
import tudresden.ocl.injection.*;

public class Main extends tudresden.ocl.injection.Main
{

  private static void makeCode(File constraintfile, OclConfig conf)
    throws OclParserException, OclTypeException, IOException
  {
    BufferedReader br=new BufferedReader(new FileReader(constraintfile));

    String nextLine;
    String nextConstraint="";
    do
    {
      nextLine=br.readLine();
      if(nextLine==null || nextLine.trim().equals(""))
      {
        if(!nextConstraint.equals(""))
        {
          conf.makeConstraint(nextConstraint);
        }
        nextConstraint="";
      }
      else
        nextConstraint=nextConstraint+"\n"+nextLine;
    }
    while (nextLine!=null);
    
    /*
    for(Iterator iter=constrainedTypes.keySet().iterator(); iter.hasNext(); )
    {
      String nexttype=(String)iter.next();
      System.out.println("generated code for "+nexttype+":");
      ((SortedFragments)(constrainedTypes.get(nexttype))).print(System.out);
    }
    */
  }

  public static void main (String args[])
  {
    String usage=
      "usage:\n"+
      "java tudresden.ocl.injection.Main [options] tobemodified1.java ...\n"+
      "  -f  --constraint-file constraints.txt\n"+
      "  -r  --reflection-model modelpackage\n"+
      "      the model given by reflection\n"+
      "  -n  --name-adapter [none|argo]\n"+
      "      the nameadapter\n"+
      "  -is --invariant-scope [all|private|protected|package|public|explicit]\n"+
      "      the scope of invariants\n"+
      "  -vm --violation-macro macro\n"+
      "      what to to, if a constraint fails.\n"+
      "  -tt --trace-types\n"+
      "      trace types of collection elements.\n"+
      "  -c  --clean\n"+
      "      clean files\n"+
      "  -m  --modify\n"+
      "      modify files";
    String constraintfile=null;
    ArrayList reflectionmodel=new ArrayList();
    NameAdapter nameadapter=null;
    boolean modify=false;
    ArrayList sourcefiles=new ArrayList();
    InstrumentorConfig conf=new InstrumentorConfig();
		OclConfig oclconf = new OclConfig();
		TypeTraceConfig typeTraceConfig = null;
		String violationmacro = null;
    try
    {
      for(int i=0; i<args.length; i++)
      {
        if("--constraint-file".equals(args[i])||"-f".equals(args[i]))
        {
          if(constraintfile!=null)
          {
            System.out.println("can use only one constraint file.");
            System.out.println(usage);
            return;
          }
          i++;
          if(i>=args.length)
          {
            System.out.println("constraint file not given.");
            System.out.println(usage);
            return;
          }
          constraintfile=args[i];
        }
        else if("--reflection-model".equals(args[i])||"-r".equals(args[i]))
        {
          i++;
          if(i>=args.length)
          {
            System.out.println("reflection package not given.");
            System.out.println(usage);
            return;
          }
          reflectionmodel.add(args[i]);
        }
        else if("--name-adapter".equals(args[i])||"-n".equals(args[i]))
        {
          if(nameadapter!=null)
          {
            System.out.println("can use only one name adapter.");
            System.out.println(usage);
            return;
          }
          i++;
          if(i>=args.length)
          {
            System.out.println("name adapter not given.");
            System.out.println(usage);
            return;
          }
          if("none".equals(args[i]))
            nameadapter=new SimpleNameAdapter();
          else if("argo".equals(args[i]))
            nameadapter=new ArgoNameAdapter();
          else
          {
            System.out.println("name adapter must be 'none' or 'argo'.");
            System.out.println(usage);
            return;
          }
        }
        else if("--invariant-scope".equals(args[i])||"-is".equals(args[i]))
        {
          i++;
          if(i>=args.length)
          {
            System.out.println("invariant scope not given.");
            System.out.println(usage);
            return;
          }
          if("private".equals(args[i])||"all".equals(args[i]))
            oclconf.invariantScope=oclconf.INVARIANT_SCOPE_PRIVATE;
          else if("protected".equals(args[i]))
            oclconf.invariantScope=oclconf.INVARIANT_SCOPE_PROTECTED;
          else if("package".equals(args[i]))
            oclconf.invariantScope=oclconf.INVARIANT_SCOPE_PACKAGE;
          else if("public".equals(args[i]))
            oclconf.invariantScope=oclconf.INVARIANT_SCOPE_PUBLIC;
          else if("explicit".equals(args[i]))
            oclconf.invariantScope=oclconf.INVARIANT_SCOPE_EXPLICIT;
          else
          {
            System.out.println("invariant scope must be 'all', 'private', 'protected', 'package', 'public' or 'explicit'.");
            System.out.println(usage);
            return;
          }
        }
        else if("--violation-macro".equals(args[i])||"-vm".equals(args[i]))
        {
          if(violationmacro!=null)
          {
            System.out.println("can use only one violation macro.");
            System.out.println(usage);
            return;
          }
          i++;
          if(i>=args.length)
          {
            System.out.println("violation macro not given.");
            System.out.println(usage);
            return;
          }
          violationmacro=args[i];
        }
        else if("--trace-types".equals(args[i])||"-tt".equals(args[i]))
				{
					if(typeTraceConfig==null)
						typeTraceConfig=new TypeTraceConfig();
				}
        else if("--modify".equals(args[i])||"-m".equals(args[i]))
          modify=true;
        else if("--clean".equals(args[i])||"-c".equals(args[i]))
          conf.clean=true;
        else if("--insert-immediately".equals(args[i]))
          conf.insertimmediately=true;
        else if("--trace-checking".equals(args[i]))
          oclconf.tracechecking=true;
        else if("--simple-hash".equals(args[i]))
          conf.hashmode=HashSize.class;
        else if("--modcount-hash".equals(args[i]))
          conf.hashmode=HashModCount.class;
        else if("--log-class".equals(args[i]))
          oclconf.logclass=true;
        else if(args[i].startsWith("-"))
        {
          System.out.println("unknown option: "+args[i]);
          System.out.println(usage);
          return;
        }
        else
        {
          for(; i<args.length; i++)
            expand(sourcefiles, args[i]);
        }
      }

      if(sourcefiles.isEmpty())
      {
        System.out.println("nothing to do.");
        System.out.println(usage);
        return;
      }
			
      if(violationmacro==null)
        violationmacro="System.out.println";

      if(nameadapter==null)
        nameadapter=new SimpleNameAdapter();
      oclconf.modelfacade=new ReflectionFacade
      (
        (String[])(reflectionmodel.toArray(new String[0])),
        new DefaultReflectionAdapter(),
        nameadapter,
        new SourceReflectionExtender()
      );

      if(conf.clean&&constraintfile!=null)
      {
        System.out.println("cannot combine --clean and --constraint-file.");
        System.out.println(usage);
        return;
      }

      if(constraintfile!=null)
      {
        makeCode(new File(constraintfile), oclconf);
      }
            
			{
				ArrayList taskConfigs = new ArrayList();
				taskConfigs.add(new TypeCheckConfig(violationmacro));
				if(typeTraceConfig!=null)
     			taskConfigs.add(typeTraceConfig);
				oclconf.violationmacro = violationmacro;
				taskConfigs.add(oclconf);
				conf.taskConfigs=new TaskConfig[taskConfigs.size()];
				for(int i=0; i<conf.taskConfigs.length; i++)
					conf.taskConfigs[i] = (TaskConfig)taskConfigs.get(i);
			}

      for(Iterator i=sourcefiles.iterator(); i.hasNext(); )
      {
        String s=(String)i.next();
        if(modify)
          inject(new File(s), conf);
        else
          inject(new File(s), new File(s+".injected"), conf);
      }
    }
    catch(InjectorParseException e){System.out.println(e);}
    catch(IOException e){System.out.println(e);}
  }
	
}



