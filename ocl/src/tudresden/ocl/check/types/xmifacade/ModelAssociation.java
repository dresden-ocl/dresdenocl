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

package tudresden.ocl.check.types.xmifacade;

import java.util.ArrayList;
import tudresden.ocl.check.types.Type;
import tudresden.ocl.check.types.Collection;

public class ModelAssociation
{
  private ArrayList ends=new ArrayList();
  private ModelClass attribute;

  public ModelAssociation(ModelClass attribute)
  {
    this.attribute=attribute;
  }

  public void addEnd(ModelAssociationEnd end)
  {
    ends.add(end);
  }

  public ModelClass getAttribute()
  {
    return attribute;
  }

  public void dissolve(Model model)
  {
    dissolve(model, false);
  }

  /**
     @see XmiParser#qualifiersOnTarget
  */
  public void dissolve(Model model, boolean qualifiersOnTarget)
  {
    ModelAssociationEnd[] octopus=new ModelAssociationEnd[ends.size()];
    ends.toArray(octopus);

    for(int xi=0; xi<octopus.length; xi++)
    {
      ModelAssociationEnd x=octopus[xi];
      boolean othersAreMultiple=false;
      for(int yi=0; yi<octopus.length; yi++)
        if(xi!=yi)
        {
          ModelAssociationEnd y=octopus[yi];
          Type[] qualifiers=qualifiersOnTarget 
            ? y.qualifiers 
            : x.qualifiers;
          x.modelclass.addAttribute(new ModelAttribute(y.name, y.type, qualifiers));
          othersAreMultiple|=y.isMultiple;
        }
      x.othersAreMultiple=othersAreMultiple;
    }

    if(attribute!=null)
    {
      for(int i=0; i<octopus.length; i++)
      {
        ModelAssociationEnd x=octopus[i];

        attribute.addAttribute(new ModelAttribute(x.name, x.modelclass, null));

        Type attributetype=
          x.othersAreMultiple ? new Collection(Collection.SET, attribute) : (Type)attribute;
        x.modelclass.addAttribute(new ModelAttribute(attribute.getImplicitRoleName(), attributetype, x.qualifiers));

      }
    }

    // this object is not usable any more.
    ends=null;
  }

}
