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

import junit.framework.Assert;
import java.io.*;
import java.net.*;

public class Diff extends Assert
{

	public static void diff(final DiffSource expected, final DiffSource actual)
	throws IOException
	{
		try
		{
			final BufferedReader expectedReader = expected.getBufferedReader();
			final BufferedReader actualReader = actual.getBufferedReader();
			final String message =
				"difference comparing \""+expected.getName()+
				"\" and \""+actual.getName()+"\" in line ";
			String expectedLine;
			String actualLine;
			for(int line=1; ; line++)
			{
				expectedLine = expectedReader.readLine();
				actualLine = actualReader.readLine();
				assertEquals(message+line, expectedLine, actualLine);
				if(expectedLine==null)
					break;
			}
		}
		finally
		{
			expected.close();
			actual.close();
		}
	}

	public static void main(String[] args) throws IOException
	{
		diff(new DiffSource(new File(args[0])), new DiffSource(new File(args[1])));
	}

}
