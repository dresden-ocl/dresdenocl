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

public class Empty
{

	public static void main(String[] args) throws IOException
	{
		final File file = new File(args[0]);
		if(!file.exists())
			throw new RuntimeException("File "+file+" does not exist.");
		if(file.length()!=0l)
			throw new RuntimeException("File "+file+" is not empty.");
	}

}
