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

package tudresden.ocl.test.royloy;

public class DayType
{
	DayType monday  =new DayType();
	DayType tuesday =new DayType();
	DayType saturday=new DayType();
	DayType sunday  =new DayType();
	
	public DayType()
	{
	}
	
	static DayType getFollowingDay(DayType dt)
	{
		return new DayType();
	}
	
}
