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

public class DiffTask extends Task
{
	
	private File expectedFile;
	
	public void setExpectedFile(final File expectedFile)
	{
		this.expectedFile = expectedFile;
	}
	
	private File actualFile;
	
	public void setActualFile(final File actualFile)
	{
		this.actualFile = actualFile;
	}
	
	
	public void execute() throws BuildException
	{
		if(!expectedFile.exists())
			throw new BuildException("File does not exist: "+expectedFile, location);
		if(!expectedFile.canRead())
			throw new BuildException("Cannot read file: "+expectedFile, location);
		if(!actualFile.exists())
			throw new BuildException("File does not exist: "+actualFile, location);
		if(!actualFile.canRead())
			throw new BuildException("Cannot read file: "+actualFile, location);
		if(expectedFile.equals(actualFile))
			throw new BuildException("Both files are the same !", location);
		
		log("Comparing "+expectedFile+" and "+actualFile);
		
		BufferedReader expectedReader = null;
		BufferedReader actualReader = null;
		try
		{
			expectedReader = new BufferedReader(new FileReader(expectedFile));
			actualReader = new BufferedReader(new FileReader(actualFile));
			
			String expectedLine;
			String actualLine;
			for(int line=1; ; line++)
			{
				expectedLine = expectedReader.readLine();
				actualLine = actualReader.readLine();
				if(expectedLine!=null)
				{
					if(actualLine!=null)
					{
						if(!expectedLine.equals(actualLine))
							throw new BuildException("diff: difference in line "+line+", expected \""+expectedLine+"\", but was \""+actualLine+"\".", location);
					}
					else
						throw new BuildException("diff: unexpected end of "+actualFile+" in line "+line+", expected \""+expectedLine+"\".", location);
				}
				else
				{
					if(actualLine!=null)
						throw new BuildException("diff: expected end of "+actualFile+" in line "+line+", but got \""+actualLine+"\".", location);
					else
						break; // sucessfully compared, no diffs.
				}
			}
			expectedReader.close();
			expectedReader = null;
			actualReader.close();
			actualReader = null;
		}
		catch(IOException e)
		{
			throw new BuildException(e, location);
		}
		finally
		{
			if(expectedReader!=null)
			{
				try
				{
					expectedReader.close();
				}
				catch(IOException e)
				{
					throw new BuildException(e, location);
				}
			}
			if(actualReader!=null)
			{
				try
				{
					actualReader.close();
				}
				catch(IOException e)
				{
					throw new BuildException(e, location);
				}
			}
		}
	}
	
}
