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

import java.io.IOException;
import java.io.Writer;

public interface TaskInstrumentor
{
	public void setLineSeparator(String lineSeparator);
	
	public void onDocComment(JavaClass jc, String doccomment);
	
	public void onAttributeChanged(Writer o, JavaAttribute ja, boolean is_weakly_typed)
	throws IOException;
	
	public String getMutex();
	
	public void onWrapperConstructor(Writer o, JavaConstructor jc)
	throws IOException;
	
	public void onWrapperDefaultConstructor(Writer o, JavaClass jc)
	throws IOException;
	
	public void	onWrapperPre(Writer o, JavaMethod jm)
	throws IOException;
	
	public void	onWrapperPost(Writer o, JavaMethod jm)
	throws IOException;
	
	public void onClassEndPerFeature(Writer o, JavaFeature jf)
	throws IOException;
	
	public void onClassEnd(Writer o, JavaClass jc)
	throws IOException;
}
