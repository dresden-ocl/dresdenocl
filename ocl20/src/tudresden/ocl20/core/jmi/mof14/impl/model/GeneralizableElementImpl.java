/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL 2 Compiler                                                    *
 * Copyright (C) 2002, 2003 Stefan Ocke (stefan.ocke@gmx.de).        *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a diploma project at the Chair for Software *
 * Technology, Dresden University Of Technology, Germany             *
 * (http://www-st.inf.tu-dresden.de).  It is understood that any     *
 * modification not identified as such is not covered by the         *
 * preceding statement.                                              *
 *                                                                   *
 * This work is free software; you can redistribute it and/or        *
 * modify it under the terms of the GNU Library General Public       *
 * License as published by the Free Software Foundation; either      *
 * version 2 of the License, or (at your option) any later version.  *
 *                                                                   *
 * This work is distributed in the hope that it will be useful,      *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU *
 * Library General Public License for more details.                  *
 *                                                                   *
 * You should have received a copy of the GNU Library General Public *
 * License along with this library; if not, write to the             *
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,      *
 * Boston, MA  02111-1307, USA.                                      *
 *                                                                   *
 * To submit a bug report, send a comment, or get the latest news on *
 * this project and other projects, please visit the web site:       *
 * http://www-st.inf.tu-dresden.de/ (Chair home page) or             *
 * http://www-st.inf.tu-dresden.de/ocl/ (project home page)          *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package tudresden.ocl20.jmi.mof14.impl.model;

import tudresden.ocl20.jmi.mof14.model.*;
import org.netbeans.mdr.handlers.InstanceHandler;
import org.netbeans.mdr.storagemodel.StorableObject;
import java.util.*;

/**
 */
public abstract class GeneralizableElementImpl extends ModelElementImpl /*NamespaceImpl*/ implements GeneralizableElement {

    protected GeneralizableElementImpl(StorableObject storable) {
        super(storable);
    }

    private Collection/*<StorableFeatured>*/ extendedNamespace() {
            Collection result = new ArrayList(getContents()); 

            if (this instanceof GeneralizableElement) {
                Collection supertypes = ((GeneralizableElement) this).allSupertypes();

                for (Iterator it = supertypes.iterator(); it.hasNext();) {
                    result.addAll(((GeneralizableElement) it.next()).getContents());
                }
            }

            if (this instanceof MofPackage) {
                ArrayList imports = new ArrayList();
                Object temp;

                for (Iterator it = result.iterator(); it.hasNext();) {
                    temp = it.next();
                    if (temp instanceof Import) {
                        imports.add(((Import) temp).getImportedNamespace());
                    }
                }

                result.addAll(imports);
            }

            return result;
    }

    private void allSupertypes2(List result, Set visited) {
        List tempElements = getSupertypes();
        GeneralizableElementImpl element;

        for (Iterator it = tempElements.iterator(); it.hasNext();) {
            element = (GeneralizableElementImpl) it.next();
            if (visited.add(element)) {
                element.allSupertypes2(result, visited);
                result.add(element);
            }
        }
    }

    // --- operations

    public List allSupertypes() {
        List result = new ArrayList();
        allSupertypes2(result, new HashSet());
        return result;
    }

    public ModelElement lookupElementExtended(String name) throws NameNotFoundException {
        Collection contents = extendedNamespace();

            for (Iterator it = contents.iterator(); it.hasNext();) {
                ModelElement el = (ModelElement) it.next();
                if (el != null && name.equals(el.getNameA())) {
                    return el;
                }
            }

        throw new NameNotFoundException(name);
    }

    public List findElementsByTypeExtended(javax.jmi.model.MofClass ofType, boolean includeSubtypes) {
        ArrayList result = new ArrayList();
        Collection contents = extendedNamespace();

        for (Iterator it = contents.iterator(); it.hasNext();) {
            ModelElement element = (ModelElement) it.next();
            if (element.refIsInstanceOf(ofType, includeSubtypes)) {
                result.add(element);
            }
        }

        return result;
    }

    // --- derived attributes

}