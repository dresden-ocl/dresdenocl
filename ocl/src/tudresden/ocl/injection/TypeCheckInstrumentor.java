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
import tudresden.ocl.injection.lib.Check;

public final class TypeCheckInstrumentor implements TaskInstrumentor
{
	private String violationmacro;
	
	public TypeCheckInstrumentor(final String violationmacro)
	{
		this.violationmacro=violationmacro;
	}
	
	private String lineSeparator;
	
	public void setLineSeparator(final String lineSeparator)
	{
		this.lineSeparator = lineSeparator;
	}
	
	public void onAttributeChanged(Writer o, JavaAttribute ja, boolean is_weakly_typed) throws IOException
	{
		write(o, ja, Check.CHECK_ELEMENT_TYPES, ja.getElementType());
		write(o, ja, Check.CHECK_KEY_TYPES,     ja.getKeyType());
	}
	
	private final void write(Writer o, JavaAttribute ja, String kind, String type)
	throws IOException
	{
		if(type!=null)
		{
			o.write("      if(!");
			o.write(kind);
			o.write('(');
			o.write(ja.getName());
			o.write(',');
			o.write(type);
			o.write(".class)) ");
			o.write(violationmacro);
			o.write("(\"");
			if(kind==Check.CHECK_ELEMENT_TYPES)
				o.write("element");
			else if(kind==Check.CHECK_KEY_TYPES)
				o.write("key");
			else
				throw new RuntimeException();
			o.write(" type checker failed at feature '");
			o.write(ja.getName());
			o.write("' of object \"+this+\".\");");
			o.write(lineSeparator);
		}
	}
	
	public void onDocComment(JavaClass jc, String doccomment) {}
	public void onClassFeature(JavaFeature jf, String doccomment) {}
	public String getMutex() { return null; }
	public void onWrapperConstructor(Writer o, JavaConstructor jc) {}
	public void onWrapperDefaultConstructor(Writer o, JavaClass jc) {}
	public void	onWrapperPre(Writer o, JavaMethod jm) {}
	public void	onWrapperPost(Writer o, JavaMethod jm) {}
	public void onClassEndPerFeature(Writer o, JavaFeature jf) {}
	public void onClassEnd(Writer o, JavaClass jc) {}
	
}

