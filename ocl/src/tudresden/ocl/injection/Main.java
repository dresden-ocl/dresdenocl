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

package tudresden.ocl.injection;

import java.io.*;
import java.util.*;
import java.lang.reflect.Modifier;
import tudresden.ocl.OclTree;
import tudresden.ocl.NameCreator;
import tudresden.ocl.lib.NameAdapter;
import tudresden.ocl.lib.SimpleNameAdapter;
import tudresden.ocl.lib.ArgoNameAdapter;
import tudresden.ocl.parser.OclParserException;
import tudresden.ocl.check.types.*;
import tudresden.ocl.check.OclTypeException;
import tudresden.ocl.codegen.CodeFragment;
import tudresden.ocl.codegen.JavaCodeGenerator;
import tudresden.ocl.injection.lib.Invariant;
import tudresden.ocl.injection.lib.Check;
import tudresden.ocl.injection.lib.HashExact;
import tudresden.ocl.injection.lib.HashSize;
import tudresden.ocl.injection.lib.HashModCount;
import tudresden.ocl.injection.lib.WrapperDummy;
import tudresden.ocl.injection.lib.TypeTracer;


public class Main
{

  public static void makeConstraint(String text,
                                    String kind,
                                    String context,
                                    InstrumentorConfig conf)
    throws OclParserException, OclTypeException
  {
    String constraintString="context "+context+' '+kind+' '+text;
    //System.out.println("found inline constraint: >"+constraintString+"<");
    try
    {
      makeConstraint(constraintString, conf);
    }
    catch(IOException e) { throw new RuntimeException(e.toString()); }
  }

  public static void makeConstraint(String constraintString, InstrumentorConfig conf)
    throws OclParserException, OclTypeException, IOException
  {
    //System.out.println("Loaded constraint:");
    //System.out.println(constraintString);
    //System.out.println("Parsing constraint.");
    OclTree constraintTree=OclTree.createTree(constraintString, conf.modelfacade);
    constraintTree.setNameCreator(conf.namecreator);
    //System.out.println("Type checking constraint.");
    constraintTree.assureTypes();
    //System.out.println("Normalizing.");
    constraintTree.applyDefaultNormalizations();
    //System.out.println("Generating Code.");
    CodeFragment[] frags=conf.jcg.getCode(constraintTree);
    for (int j=0; j<frags.length; j++)
    {
      String ct=frags[j].getConstrainedType();
      SortedFragments sf=(SortedFragments)(conf.codefragments.get(ct));
      if(sf==null)
        conf.codefragments.put(ct, new SortedFragments(frags[j]));
      else
        sf.addFragment(frags[j]);
    }
  }

  public static void makeCode(File constraintfile, InstrumentorConfig conf)
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
          makeConstraint(nextConstraint, conf);
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

  public static void inject(File inputfile, File outputfile, InstrumentorConfig conf)
    throws IOException, InjectorParseException
  {
    //System.out.println("injecting from "+inputfile+" to "+outputfile);

    if(outputfile.exists())
    {
      if(inputfile.getCanonicalPath().equals(outputfile.getCanonicalPath()))
        throw new RuntimeException("error: input file and output file are the same.");
      if(!outputfile.isFile())
        throw new RuntimeException("error: output file is not a regular file.");
    }

    Reader input=null;
    Writer output=null;
    try
    {
      input =new InputStreamReader (new FileInputStream (inputfile));
      output=new OutputStreamWriter(new FileOutputStream(outputfile));
      (new Injector(input, output, new Instrumentor(output, conf))).parseFile();
      input.close();
      output.close();
    }
    catch(InjectorParseException e)
    {
      input.close();
      output.close();
      outputfile.delete();
      throw new InjectorParseException(inputfile+": "+e.getMessage());
    }
    catch(tudresden.ocl.check.OclTypeException e)
    {
      input.close();
      output.close();
      outputfile.delete();
      throw new tudresden.ocl.check.OclTypeException(inputfile+": "+e.getMessage());
    }
    catch(tudresden.ocl.parser.OclParserException e)
    {
      input.close();
      output.close();
      outputfile.delete();
      throw new tudresden.ocl.parser.OclParserException(inputfile+": "+e.getMessage());
    }
    catch(IOException e)
    {
      if(input!=null)  input.close();
      if(output!=null) output.close();
      outputfile.delete();
      throw e;
    }
  }

  public static final String TEMPFILE_SUFFIX=".temp_oclinjection";

  public static void inject(File tobemodifiedfile, InstrumentorConfig conf)
    throws IOException, InjectorParseException
  {
    File outputfile=new File(tobemodifiedfile.getPath()+TEMPFILE_SUFFIX);
    inject(tobemodifiedfile, outputfile, conf);
    if(!tobemodifiedfile.delete())
      System.out.println("warning: deleting "+tobemodifiedfile+" failed.");
    if(!outputfile.renameTo(tobemodifiedfile))
      System.out.println("warning: renaming "+outputfile+" to "+tobemodifiedfile+" failed.");
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
		TypeTraceConfig typeTraceConfig = null;
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
            conf.invariantScope=conf.INVARIANT_SCOPE_PRIVATE;
          else if("protected".equals(args[i]))
            conf.invariantScope=conf.INVARIANT_SCOPE_PROTECTED;
          else if("package".equals(args[i]))
            conf.invariantScope=conf.INVARIANT_SCOPE_PACKAGE;
          else if("public".equals(args[i]))
            conf.invariantScope=conf.INVARIANT_SCOPE_PUBLIC;
          else if("explicit".equals(args[i]))
            conf.invariantScope=conf.INVARIANT_SCOPE_EXPLICIT;
          else
          {
            System.out.println("invariant scope must be 'all', 'private', 'protected', 'package', 'public' or 'explicit'.");
            System.out.println(usage);
            return;
          }
        }
        else if("--violation-macro".equals(args[i])||"-vm".equals(args[i]))
        {
          if(conf.violationmacro!=null)
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
          conf.violationmacro=args[i];
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
          conf.tracechecking=true;
        else if("--simple-hash".equals(args[i]))
          conf.hashmode=HashSize.class;
        else if("--modcount-hash".equals(args[i]))
          conf.hashmode=HashModCount.class;
        else if("--log-class".equals(args[i]))
          conf.logclass=true;
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
			
			{
				ArrayList taskConfigs = new ArrayList();
				if(typeTraceConfig!=null)
     			taskConfigs.add(typeTraceConfig);
				conf.taskConfigs=new TaskConfig[taskConfigs.size()];
				for(int i=0; i<conf.taskConfigs.length; i++)
					conf.taskConfigs[i] = (TaskConfig)taskConfigs.get(i);
			}

      if(conf.clean&&constraintfile!=null)
      {
        System.out.println("cannot combine --clean and --constraint-file.");
        System.out.println(usage);
        return;
      }

      if(conf.violationmacro==null)
        conf.violationmacro="System.out.println";

      if(nameadapter==null)
        nameadapter=new SimpleNameAdapter();
      conf.modelfacade=new ReflectionFacade
      (
        (String[])(reflectionmodel.toArray(new String[0])),
        new DefaultReflectionAdapter(),
        nameadapter,
        new SourceReflectionExtender()
      );

      if(constraintfile!=null)
      {
        makeCode(new File(constraintfile), conf);
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
	
	public static void expand(java.util.Collection files, String pattern)
	  throws IOException
	{
		if(pattern.endsWith("*.java"))
		{
			//System.out.println("expanding "+pattern);
			String directoryName = pattern.substring(0,pattern.length()-"*.java".length());
			File directory = new File(directoryName);
			if(!directory.isDirectory())
				throw new IOException(directoryName+" should be a directory");
			File[] expandedFiles = directory.listFiles(new FileFilter()
			{
				public boolean accept(File file)
				{
					return
						file.isFile() &&
						file.getName().endsWith(".java");
				}
			});
			//for(int i=0; i<expandedFiles.length; i++) System.out.println("  into "+expandedFiles[i].getPath());
			for(int i=0; i<expandedFiles.length; i++)
			  files.add(expandedFiles[i].getPath());
		}
		else
			files.add(pattern);
	}

}



