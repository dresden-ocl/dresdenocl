/*
Copyright (C) 2001  Ralf Wiebicke
 
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


package tudresden.ocl.test;

import java.io.*;
import java.util.*;

// IMPORTANT! You may need to mount ant.jar before this class will
// compile. So mount the JAR modules/ext/ant.jar (NOT modules/ant.jar)
// from your IDE installation directory in your Filesystems before
// continuing to ensure that it is in your classpath.

import org.apache.tools.ant.*;
import org.apache.tools.ant.types.*;

public class SortTask extends Task
{
	
	private File file;
	
	public void setFile(final File file)
	{
		this.file = file;
	}
	
	
	public void execute() throws BuildException
	{
		if(!file.exists())
			throw new BuildException("File does not exist: "+file, location);
		if(!file.canRead())
			throw new BuildException("Cannot read file: "+file, location);
		if(!file.canWrite())
			throw new BuildException("Cannot write file: "+file, location);
		
		log("Sorting "+file);
		
		BufferedReader in = null;
		Writer out = null;
		try
		{
			final ArrayList list = new ArrayList(1024);
			
			in = new BufferedReader(new FileReader(file));
			for(String line = in.readLine(); line!=null; line = in.readLine())
				list.add(line);
			in.close();
			in = null;
			
			String[] array = (String[])list.toArray(new String[list.size()]);
			Arrays.sort(array);
			
			final String lineSeparator = System.getProperty("line.separator");
			
			out = new FileWriter(file);
			for(int i=0; i<array.length; i++)
			{
				out.write(array[i]);
				out.write(lineSeparator);
			}
			out.close();
			out = null;
		}
		catch(IOException e)
		{
			throw new BuildException(e, location);
		}
		finally
		{
			if(in!=null)
			{
				try
				{
					in.close();
				}
				catch(IOException e)
				{
					throw new BuildException(e, location);
				}
			}
			if(out!=null)
			{
				try
				{
					out.close();
				}
				catch(IOException e)
				{
					throw new BuildException(e, location);
				}
			}
		}
	}
	
}
