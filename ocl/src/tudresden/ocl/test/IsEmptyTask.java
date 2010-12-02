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

public class IsEmptyTask extends Task
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

		log("Checking "+file);

		if(file.length()!=0l)
			throw new BuildException("File is not empty: "+file, location);
	}

}
