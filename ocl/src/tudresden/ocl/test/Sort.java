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

public class Sort
{
	
	public static void sort(final BufferedReader in, final Writer out)
	throws IOException
	{
		try
		{
			final String lineSeparator = System.getProperty("line.separator");
			final ArrayList list = new ArrayList(1024);
			
			for(String line = in.readLine(); line!=null; line = in.readLine())
				list.add(line);
			
			String[] array = (String[])list.toArray(new String[list.size()]);
			Arrays.sort(array);
			
			for(int i=0; i<array.length; i++)
			{
				out.write(array[i]);
				out.write(lineSeparator);
			}
		}
		finally
		{
			in.close();
			out.close();
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		final File inFile = new File(args[0]);
		final File outFile = new File(args[0]+".tempsorting");
		sort(new BufferedReader(new FileReader(inFile)), new FileWriter(outFile));
		if(!inFile.delete())
			throw new IOException("deleting "+inFile+" failed.");
		if(!outFile.renameTo(inFile))
			throw new IOException("renaming from "+outFile+" to "+inFile+" failed.");
	}
	
}

