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
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import tudresden.ocl.check.OclTypeException;
import tudresden.ocl.parser.OclParserException;
import tudresden.ocl.injection.lib.HashExact;
import tudresden.ocl.injection.lib.HashSize;
import tudresden.ocl.injection.lib.HashModCount;

public class Main
{
	
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
		catch(OclTypeException e)
		{
      e.printStackTrace();
			input.close();
			output.close();
			outputfile.delete();
			throw new OclTypeException(inputfile+": "+e.getMessage());
		}
		catch(OclParserException e)
		{
			input.close();
			output.close();
			outputfile.delete();
			throw new OclParserException(inputfile+": "+e.getMessage());
		}
		catch(IOException e)
		{
			if(input!=null)  input.close();
			if(output!=null) output.close();
			outputfile.delete();
			throw e;
		}
	}
	
	private static final String TEMPFILE_SUFFIX=".temp_oclinjection";
	
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

	public static void expand(Collection files, String pattern)
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

	public static void main(final String[] args)
	{
		(new Main()).run(args);
	}

	protected Main() {}

	protected void printUsage(PrintStream o)
	{
		o.println("usage:");
		o.print("java ");
		o.print(getClass().getName());
		o.println("[options] tobemodified1.java ...");
		o.println("  -m  --modify");
		o.println("      modify files");
		o.println("  -c  --clean");
		o.println("      clean files");
		o.println("  -vm --violation-macro macro");
		o.println("      what to to, if a constraint fails.");
		o.println("  -tt --trace-types");
		o.println("      trace types of collection elements.");
	}

	protected int i;
	protected String[] args;
	protected final ArrayList taskConfigs = new ArrayList();

	private boolean modify=false;
	private InstrumentorConfig conf=new InstrumentorConfig();
	private String violationmacro = null;
	private TypeTraceConfig typeTraceConfig = null;
	private ArrayList sourcefiles=new ArrayList();
	
	protected void processParameter() throws IOException, IllegalParameterException
	{
		if("--violation-macro".equals(args[i])||"-vm".equals(args[i]))
		{
			if(violationmacro!=null)
				throw new IllegalParameterException("can use only one violation macro.");
			i++;
			if(i>=args.length)
				throw new IllegalParameterException("violation macro not given.");
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
		else if("--simple-hash".equals(args[i]))
			conf.hashmode=HashSize.class;
		else if("--modcount-hash".equals(args[i]))
			conf.hashmode=HashModCount.class;
		else if(args[i].startsWith("-"))
			throw new IllegalParameterException("unknown option: "+args[i]);
		else
		{
			for(; i<args.length; i++)
				expand(sourcefiles, args[i]);
		}
	}
	
	protected void postProcessParameters() throws IOException, IllegalParameterException
	{
		if(violationmacro==null)
			violationmacro="System.out.println";
		
		taskConfigs.add(new TypeCheckConfig(violationmacro));
		if(typeTraceConfig!=null)
			taskConfigs.add(typeTraceConfig);
	}
	
	protected final String getViolationMacro()
	{
		return violationmacro;
	}
	
	protected final void run(final String[] args)
	{
		this.args = args;
		
		try
		{
			for(i=0; i<args.length; i++)
				processParameter();
			
			postProcessParameters();
			
			conf.taskConfigs=new TaskConfig[taskConfigs.size()];
			for(int i=0; i<conf.taskConfigs.length; i++)
				conf.taskConfigs[i] = (TaskConfig)taskConfigs.get(i);
			
			if(sourcefiles.isEmpty())
				throw new IllegalParameterException("nothing to do.");
			
			for(Iterator i=sourcefiles.iterator(); i.hasNext(); )
			{
				String s=(String)i.next();
				if(modify)
					inject(new File(s), conf);
				else
					inject(new File(s), new File(s+".injected"), conf);
			}
		}
		catch(IllegalParameterException e)
		{
			System.out.println(e.getMessage());
			printUsage(System.out);
		}
		catch(InjectorParseException e)
		{
			System.out.println(e);
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	}
	
}
