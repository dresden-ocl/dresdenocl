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

package tudresden.ocl20;
import javax.jmi.model.*;
import java.util.*;

/**
 * This class provides some utility methods for handling of metamodels.
 * @author  Stefan Ocke
 */
public class MetaModelUtil {
    
    private static final String TOPLEVEL_PACKAGENAME = "tudOcl20_TopLevelPackage";
    
    public static final String JMIPCKGPRFX = "javax.jmi.packagePrefix";
    /** Creates a new instance of MetaModelUtil */
    private MetaModelUtil() {
    }
    
    /**
     * Problem: Which MofPackage of a Metamodel shall be instantiated?
     * Solution: if there are more than one top level packages, create a new one, clustering all the others.
     */
    public static MofPackage getTopLevelPackage(ModelPackage modelPackage) {
        
        Collection topPackages = getTopPackages(modelPackage);
        
        if(topPackages.size()==1){
            return (MofPackage)topPackages.iterator().next();
        }
        
        MofPackage topPackage = null;
        Iterator it = topPackages.iterator();
        while(it.hasNext()){
            MofPackage mp =  (MofPackage) it.next();
            
            //////////ugly workaround. think about better solutions
            if(mp.getName().equals("UML") || mp.getName().equals("Model")){
                return mp;
            }
            if(mp.getName().equals(TOPLEVEL_PACKAGENAME)){
                return mp;
            }
        }
        
        
        //if there is not exactly one top level package, create  a package that clusters all top  level packages
        topPackage = modelPackage.getMofPackage().createMofPackage(TOPLEVEL_PACKAGENAME,"",true,false,false,VisibilityKindEnum.PUBLIC_VIS);
        it = topPackages.iterator();
        while(it.hasNext()){
            MofPackage mp = (MofPackage) it.next();
            
            //create a clustering import
            createImport(topPackage,  mp, true);
        }
        return topPackage;
        
    }
    
    //Returns all packages in the metamodel that are are not contained in other packages.
    //That "top-level" packages are subject to instantiation
    private static Collection getTopPackages(ModelPackage modelPackage){
        Collection result = new HashSet();
        Iterator it = modelPackage.getMofPackage().refAllOfType().iterator();
        while(it.hasNext()){
            MofPackage me = (MofPackage) it.next();
            if(me.refImmediateComposite()==null){
                result.add(me);
            }
        }
        return result;
    }
    
    /**
     * Finds a non-nested MofPackage by name.
     */
    public static MofPackage findMofPackage(ModelPackage modelPackage, String name) throws NameNotFoundException{
        Iterator it = modelPackage.getMofPackage().refAllOfClass().iterator();
        MofPackage mofPac = null;
        while(it.hasNext() && mofPac == null){
            mofPac = (MofPackage) it.next();
            if(!name.equals(mofPac.getName()) || mofPac.getContainer() != null){
                mofPac=null;
            }
        }
        if (mofPac==null){throw new NameNotFoundException(name);}
        return mofPac;
    }
    
    /**
     * Checks if the class cl is directly or indirectly  contained  in the namespace ns.
     */
    public static boolean contains(Namespace ns, MofClass cl){
        Namespace clns = cl.getContainer();
        while(clns != null){
            if(clns.equals(ns)){
                return true;
            }
            clns = clns.getContainer();
        }
        return false;
    }
    
    /**Creates an Import relationship between two packages. This import can be clustered (see [MOF14, p.4-8ff]).*/
    public static void createImport(MofPackage pkg, MofPackage importedPkg, boolean clustered){
        Import oclImport = ((ModelPackage)pkg.refImmediatePackage()).getImport().createImport(importedPkg.getName(),"",VisibilityKindEnum.PUBLIC_VIS,clustered);
        oclImport.setContainer(pkg);
        oclImport.setImportedNamespace(importedPkg);
        
    }
    
    /**Looks in a package and recursively in all its subpackages and imported packages for a model element by name and type.*/
    public static ModelElement lookupElementRecursive(MofPackage enclosingPackage, MofClass type, String name) throws NameNotFoundException{
        ModelElement result =  _lookupElementRecursive(enclosingPackage, type, name, new HashSet());
        if(result==null){
            throw new NameNotFoundException(name);
        }
        return result;
    }
    private static ModelElement _lookupElementRecursive(MofPackage enclosingPackage, MofClass type, String name, Set visitedPackages){
        ModelPackage metamodel = (ModelPackage) enclosingPackage.refImmediatePackage();
        if(visitedPackages.contains(enclosingPackage)){
            return null;
        }
        visitedPackages.add(enclosingPackage);
        
        try{
            ModelElement candidate = enclosingPackage.lookupElementExtended(name);
            if(candidate.refIsInstanceOf(type,false)){
                return candidate;
            }
        }
        catch (NameNotFoundException nnfe){
        }
        Iterator subpackagesIt = enclosingPackage.findElementsByTypeExtended((MofClass) metamodel.getMofPackage().refMetaObject(), false).iterator();
        while(subpackagesIt.hasNext()){
            ModelElement result =  _lookupElementRecursive((MofPackage) subpackagesIt.next(), type, name, visitedPackages);
            if (result != null){
                return result;
            }
        }
        
        //workaround for some Import-related bug in the extendedNamespace implementation for MofPackage
        //(imported namespaces should be returned by this method as well, but they are not)
        
        Iterator importsIt = enclosingPackage.findElementsByTypeExtended((MofClass) metamodel.getImport().refMetaObject(), false).iterator();
        while(importsIt.hasNext()){
            Namespace importedNs = ((Import) importsIt.next()).getImportedNamespace();
            if(importedNs.refIsInstanceOf(type, false) && importedNs.getName().equals(name)){
                return importedNs;
            }
            if(importedNs instanceof MofPackage){
                ModelElement result =  _lookupElementRecursive((MofPackage)importedNs, type, name, visitedPackages);
                if (result != null){
                    return result;
                }
            }
        }
        return null;
    }
    
    /**Sets the package prefix for JMI interface generation.*/
    public static void setJmiPckgPrefix(MofPackage pckg, String prfx){
        MetaModelUtil.setTag(pckg, JMIPCKGPRFX, prfx);
    }
    
    /**Set a tag for a model element. Prior to that, all tags of the model element with the same id are deleted.*/
    public static void setTag(ModelElement me, String tagId, String value){
        removeTag(me, tagId);
        Tag tag = ((ModelPackage)me.refImmediatePackage()).getTag().createTag();
        tag.setTagId(tagId);
        tag.setName(tagId+"="+value);
        if(me instanceof Namespace){
            tag.setContainer((Namespace)me);
        } else {
            tag.setContainer(me.getContainer());
        }
        tag.getValues().add(value);
        tag.getElements().add(me);
    }
    
    /**Removes all tags with a certain id from a model element.*/
    public static void removeTag(ModelElement me, String tagId){
        AttachesTo attachesTo= ((ModelPackage)me.refImmediatePackage()).getAttachesTo();
        Iterator tagsIt = attachesTo.getTag(me).iterator();
        while(tagsIt.hasNext()){
            Tag tag = (Tag)  tagsIt.next();
            if(tagId.equals(tag.getTagId())){
                tagsIt.remove();
            }
        }
    }
    
    /**Determines the path by which a certain model element can be reached, starting from a certain namspace. 
     * Clustered imports are taken into account.
     */
    public static boolean findPathReverse(ModelElement me, Namespace rootNs, List nsList){
        Aliases aliases = ((javax.jmi.model.ModelPackage)me.refOutermostPackage()).getAliases();
        Namespace oldns=null;
        Namespace ns = me.getContainer();
        while(ns!=null && ns!=rootNs){
            
            nsList.add(ns);
            oldns=ns;
            ns=ns.getContainer(); //navigate  through the package nesting hierarchy
        }
        if(ns==null){
            //examine all clustering
            Iterator importers = aliases.getImporter(oldns).iterator();
            while(importers.hasNext()){
                Import i = ((Import) importers.next());
                
                if(i.isClustered()){
                    //System.out.println("  ----Clustered Import-- "+i.getName()+ " in " + i.getContainer().getName());
                    List nsSubList = new ArrayList();
                    if(findPathReverse(i, rootNs, nsSubList)){
                        nsList.addAll(nsSubList);
                        return true;
                    }
                }
            }
        }
        return (ns==rootNs);
        
    }
    
}
