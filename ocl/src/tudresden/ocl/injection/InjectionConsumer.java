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

public interface InjectionConsumer 
{
  public void onPackage(String packagename) throws InjectorParseException;
  public void onImport(String importname);
  public void onClass(String classname);
  public void onClassEnd(String classname) throws java.io.IOException;
  public void onClassFeature(ClassFeature cf) throws java.io.IOException;
  
  /**
     @return false: if the comment should be discarded and the next classfeature to be blocked.
  */
  public boolean onComment(String comment);
}
