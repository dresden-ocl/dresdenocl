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
import java.net.*;

public final class DiffSource
{
   private final BufferedReader bufferedReader;
	private final String name;

	public DiffSource(final BufferedReader bufferedReader, final String name)
	throws IOException
	{
		this.bufferedReader=bufferedReader;
		this.name=name;
	}

	public final BufferedReader getBufferedReader()
	{
	   return bufferedReader;
	}

	public final String getName()
	{
	   return name;
	}

	public void close() throws IOException
	{
		bufferedReader.close();
	}

	public DiffSource(final Reader reader, final String name)
	throws IOException
	{
		this(new BufferedReader(reader), name);
	}

	public DiffSource(final InputStream stream, final String name)
	throws IOException
	{
		this(new InputStreamReader(stream), name);
	}

	public DiffSource(final File file)
	throws IOException
	{
		this(new FileInputStream(file), file.getAbsolutePath());
	}

	public DiffSource(final URL url)
	throws IOException
	{
		this(url.openStream(), url.toString());
	}



}
