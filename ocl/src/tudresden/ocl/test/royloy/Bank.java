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

import java.util.*;

public class Bank extends RLObject
{
  /**
     @element-type Person
     @key-type Integer
     @invariant customers_ordered_by_age:
        customer->size<=1
        or
        (
          Set{0 .. customer->size-2}->forAll
          (
            i:Integer | self.customer[i].age < self.customer[i+1].age
          )
        )
     @invariant bank_customer0:
        customer->isEmpty
        or
        customer[0]->size=1
     @invariant bank_customer2_age:
        customer[2]->isEmpty
        or
        customer[2].age>0
  */
  public HashMap customer=new HashMap();
  
  String description;

  public Bank(String description) 
  {
    this.description=description;
  }
  
  public void addCustomer(int index, Person p)
  {
    customer.put(new Integer(index), p);
  }
  
  public String toString()
  {
    return super.toString()+'['+description+']';
  }
  
  public boolean assertTrue()
  {
    return true;
  }

}
