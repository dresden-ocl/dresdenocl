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
import tudresden.ocl.injection.lib.TypeTracer;

public final class TypeTraceInstrumentor implements TaskInstrumentor
{
	public void onAttributeChanged(Writer o, JavaAttribute ja, boolean is_weakly_typed) throws IOException
	{
		if(is_weakly_typed)
		{
			o.write("      ");
			o.write(TypeTracer.TRACE_TYPES);
			o.write("(\"");
			o.write(ja.getFullDocName());
			o.write("\", ");
			o.write(ja.getName());
			o.write(");\n");
		}
	}
	
	public void onDocComment(JavaClass jc, String doccomment) {}
	public String getMutex() { return null; }
	public void onWrapperConstructor(Writer o, JavaConstructor jc) {}
	public void onWrapperDefaultConstructor(Writer o, JavaClass jc) {}
	public void	onWrapperPre(Writer o, JavaMethod jm) {}
	public void	onWrapperPost(Writer o, JavaMethod jm) {}
	public void onClassEndPerFeature(Writer o, JavaFeature jf) {}
	public void onClassEnd(Writer o, JavaClass jc) {}
	
}
