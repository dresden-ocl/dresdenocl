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

public final class ClassAttribute extends ClassFeature 
{
  /**
     The content of the @element-type tag in the doccomment 
     connected to this attribute.
  */
  private String element_type=null;

  /**
     The content of the @key-type tag in the doccomment 
     connected to this attribute.
  */
  private String key_type=null;

  public ClassAttribute(ClassClass parent, 
                        int modifiers, 
                        String type, 
                        String name)
    throws InjectorParseException
  {
    super(parent, modifiers, type, name);
    if(parent==null || type==null)
      throw new RuntimeException();
  }

  public final void setElementType(String element_type)
  {
    if(this.element_type!=null)
      throw new IllegalArgumentException();
    this.element_type=element_type;
  }
  
  public final String getElementType()
  {
    return element_type;
  }

  public final void setKeyType(String key_type)
  {
    if(this.key_type!=null)
      throw new IllegalArgumentException();
    this.key_type=key_type;
  }
  
  public final String getKeyType()
  {
    return key_type;
  }

  public final int getAllowedModifiers()
  {
    return
      java.lang.reflect.Modifier.PUBLIC |
      java.lang.reflect.Modifier.PROTECTED |
      java.lang.reflect.Modifier.PRIVATE |
      java.lang.reflect.Modifier.FINAL |
      java.lang.reflect.Modifier.STATIC |
      java.lang.reflect.Modifier.TRANSIENT |
      java.lang.reflect.Modifier.VOLATILE;
  }

}
