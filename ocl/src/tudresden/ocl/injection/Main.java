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
import tudresden.ocl.check.OclTypeException;
import tudresden.ocl.parser.OclParserException;

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

}
