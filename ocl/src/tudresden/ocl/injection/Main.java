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
import tudresden.ocl.OclTree;
import tudresden.ocl.NameCreator;
import tudresden.ocl.parser.OclParserException;
import tudresden.ocl.check.types.*;
import tudresden.ocl.check.OclTypeException;
import tudresden.ocl.check.types.xmifacade.XmiParser;
import tudresden.ocl.codegen.CodeFragment;
import tudresden.ocl.codegen.JavaCodeGenerator;

public class Main
{
  public static String[] loadConstraints(File f) throws IOException
  {
    Vector constraintList=new Vector();
    BufferedReader br=new BufferedReader(new FileReader(f));
    String nextLine;
    String nextConstraint="";
    do
    {
      nextLine=br.readLine();
      if(nextLine==null || nextLine.trim().equals(""))
      {
        if(!nextConstraint.equals(""))
          constraintList.addElement(nextConstraint);
        nextConstraint="";
      }
      else
        nextConstraint=nextConstraint+"\n"+nextLine;
    }
    while (nextLine!=null);
    String[] result=new String[constraintList.size()];
    for(int i=0; i<constraintList.size(); i++)
    {
      result[i]=(String)(constraintList.elementAt(i));
      //System.out.println("loading constraint");
      //System.out.println((String)(constraintList.elementAt(i)));
    }
    return result;
  }

  public static OclTree[] checkConstraints(String[] constraints, ModelFacade modelfacade)
    throws OclParserException, OclTypeException, IOException
  {
    OclTree[] trees=new OclTree[constraints.length];

    int i=0;
    NameCreator namecreator=new NameCreator();
    for (i=0; i<constraints.length; i++)
    {
      System.out.println("loading constraint:");
      System.out.println(constraints[i]);
      OclTree nextTree=OclTree.createTree(constraints[i], modelfacade);
      nextTree.setNameCreator(namecreator);
      nextTree.assureTypes();
      trees[i]=nextTree;
    }
    return trees;
  }

  public static Hashtable generateCode(OclTree[] trees)
  {
    JavaCodeGenerator jcg=new JavaCodeGenerator("this", "result");
    Hashtable constrainedTypes=new Hashtable(); // type names are keys, SortedFragments values
    for (int i=0; i<trees.length; i++)
    {
      System.out.println("Normalizing "+i);
      trees[i].applyDefaultNormalizations();
      CodeFragment[] frags=jcg.getCode(trees[i]);
      for (int j=0; j<frags.length; j++)
      {
        String ct=frags[j].getConstrainedType();
        if ( ! constrainedTypes.containsKey(ct) )
        {
          constrainedTypes.put(ct, new SortedFragments());
        }
        SortedFragments sf=(SortedFragments)constrainedTypes.get(ct);
        switch (frags[j].getKind())
        {
          case CodeFragment.INV:
            sf.inv.addElement(frags[j]);
            break;
          case CodeFragment.POST:
            sf.post.addElement(frags[j]);
            break;
          case CodeFragment.PRE:
            sf.pre.addElement(frags[j]);
            break;
          case CodeFragment.TRANSFER:
            sf.transfer.addElement(frags[j]);
            break;
          case CodeFragment.PREPARATION:
            sf.preparation.addElement(frags[j]);
            break;
        }
      }
    }
    Enumeration iter=constrainedTypes.keys();
    while(iter.hasMoreElements())
    {
      String nexttype=(String)iter.nextElement();
      System.out.println("generated code for "+nexttype+":");
      ((SortedFragments)(constrainedTypes.get(nexttype))).print(System.out);
    }
    return constrainedTypes;
  }

  public static void main (String args[])
  {
    String usage="usage:\n   java tudresden.ocl.injection.Main [options] tobemodified1.java ...\n      --xmi-model model.xmi\n      --constraint-file constraints.txt\n      --reflection-model modelpackage\n      -m : modify files";
    String constraintfile=null;
    String xmimodel=null;
    String reflectionmodel=null;
    boolean modify=false;
    try
    {
      for(int i=0; i<args.length; i++)
      {
        if("--constraint-file".equals(args[i]))
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
        else if("--xmi-model".equals(args[i]))
        {
          if(xmimodel!=null)
          {
            System.out.println("can use only one xmi model.");
            System.out.println(usage);
            return;
          }
          i++;
          if(i>=args.length)
          {
            System.out.println("xmi file not given.");
            System.out.println(usage);
            return;
          }
          xmimodel=args[i];
        }
        else if("--reflection-model".equals(args[i]))
        {
          if(reflectionmodel!=null)
          {
            System.out.println("can use only one reflection model.");
            System.out.println(usage);
            return;
          }
          i++;
          if(i>=args.length)
          {
            System.out.println("reflection package not given.");
            System.out.println(usage);
            return;
          }
          reflectionmodel=args[i];
        }
        else if("-m".equals(args[i]))
          modify=true;
        else if(args[i].startsWith("-"))
        {
          System.out.println("unknown option: "+args[i]);
          System.out.println(usage);
          return;
        }
        else
        {
          Hashtable codefragments=null;
          if(constraintfile!=null)
          {
            if((xmimodel==null) == (reflectionmodel==null))
            {
              System.out.println("There must be exaxtly one of --xmi-model and --reflect-model");
              System.out.println(usage);
              return;
            }
            String[] constraints=loadConstraints(new File(constraintfile));
            String[] rp={reflectionmodel};
            ModelFacade modelfacade;
            if(xmimodel!=null)
              modelfacade=tudresden.ocl.check.types.xmifacade.XmiParser.getModel(xmimodel);
            else
              modelfacade=new ReflectionFacade
              (
                rp,
                new DefaultReflectionAdapter(),
                new tudresden.ocl.lib.SimpleNameAdapter()
              );
            OclTree[] ocltrees=checkConstraints(constraints, modelfacade);
            codefragments=generateCode(ocltrees);
          }
          else
            System.out.println("no constraints given, generating code for @element-type only.");
            
          for(int j=i; j<args.length; j++)
          {
            if(modify)
              Injector.inject(new File(args[j]), codefragments);
            else
            {
              Injector.inject(new File(args[j]), new File(args[j]+".injected"), codefragments);
            }
          }
          return;
        }
      }
      System.out.println("nothing to do.");
      System.out.println(usage);
      return;
    }
    catch(InjectorParseException e){System.out.println(e);}
    catch(org.xml.sax.SAXException e){System.out.println(e);}
    catch(IOException e){System.out.println(e);}
  }

}



