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

import org.netbeans.api.mdr.*;
import org.netbeans.api.xmi.*;
import javax.jmi.model.*;
import javax.jmi.xmi.*;
import javax.jmi.reflect.*;

import java.io.*;
import java.util.*;

/**
 * This class implements the Repository interface for the Netbeans Metadata Repository.
 *
 * @author  Stefan Ocke
 */
public class NetBeansRepository implements Repository {
    
    private MDRManager mdrManager;
    private MDRepository mdRepository;
    private Map refPackageToName = new HashMap();
    
    public NetBeansRepository() {
        try{
        java.net.URL repositoryUrl = NetBeansRepository.class.getResource("/repository/");
        System.out.println("Repostiory in: "+ repositoryUrl+ " "+java.net.URLDecoder.decode((new java.net.URL(repositoryUrl,"repository").getPath())));
        System.setProperty("org.netbeans.mdr.persistence.Dir", java.net.URLDecoder.decode((new java.net.URL(repositoryUrl,"repository").getPath())));
        System.setProperty("org.netbeans.mdr.storagemodel.StorageFactoryClassName",
        "org.netbeans.mdr.persistence.btreeimpl.btreestorage.BtreeFactory");
        //System.setProperty("org.netbeans.mdr.storagemodel.StorageFactoryClassName","org.netbeans.mdr.persistence.memoryimpl.StorageFactoryImpl");
        
        //System.setProperty("org.openide.util.Lookup", "org.netbeans.mdr.test.MDRTestLookup");
        System.setProperty("org.netbeans.mdr.SaveStorageOnExit", "true");
        mdrManager = MDRManager.getDefault();
        mdRepository = mdrManager.getDefaultRepository();
        initNameCache();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void initNameCache(){
        String [] names = mdRepository.getExtentNames();
        for(int i=0; i<names.length; i++){
            refPackageToName.put(mdRepository.getExtent(names[i]), names[i]);
        }
    }
    
    public ModelPackage getMetaModel(String name) throws RepositoryException{
        RefPackage result = mdRepository.getExtent(name);
        if((result != null) &&!(result instanceof ModelPackage)){
            throw new RepositoryException(name + " is not a metamodel. "+result);
        }
        return (ModelPackage)result;
    }
    
    //get the extend name of a model
    public String getName(RefPackage model) throws RepositoryException{
        return (String) this.refPackageToName.get(model);
    }
    
    public RefPackage getModel(String name){
        return mdRepository.getExtent(name);
    }
    
    public ModelPackage createMetaModel(String name) throws RepositoryException{
        try{
            System.out.println("Create MetaModel"+name);
            ModelPackage result = (ModelPackage) mdRepository.createExtent(name);
            refPackageToName.put(result, name);
            return result;
        } catch(CreationFailedException e){
            e.printStackTrace();
            throw new RepositoryException(e);
        }
    }
    
    public Collection getModels(ModelPackage metaModel){
        Collection result = new HashSet();
        String [] names = mdRepository.getExtentNames();
        for(int i=0; i< names.length; i++){
            //System.out.println(names[i]);
            RefPackage model = mdRepository.getExtent(names[i]);
            
            if(model != null){
                RefObject ro = model.refMetaObject();
                
                if(ro != null && ro.refOutermostPackage().equals(metaModel)){
                    result.add(model);
                }
            }
        }
        return result;
    }
    
    public RefPackage createModel(String name, RefObject metaPackage) throws RepositoryException{
        try{
            RefPackage result = mdRepository.createExtent(name, metaPackage);
            refPackageToName.put(result, name);
            return result;
        } catch(CreationFailedException e){
            e.printStackTrace();
            throw new RepositoryException(e);
        }
    }
    
    public void shutdown(){
        System.out.println("Repository Content on Shutdown:");
        String [] names = mdRepository.getExtentNames();
        for(int i=0; i<names.length; i++){
            System.out.println(names[i]);
        }
        mdRepository.shutdown();
    }
    
    protected void finalize(){
        shutdown();
    }
    
    public javax.jmi.xmi.XmiReader createXMIReader(){
        return XMIReaderFactory.getDefault().createXMIReader();
    }
    
    public javax.jmi.xmi.XmiWriter createXMIWriter(){
        return XMIWriterFactory.getDefault().createXMIWriter();
    }
    
    public void generateJMIInterfaces(RefBaseObject baseObject, final File directory) throws RepositoryException{
        try{
            JMIMapper.getDefault().generate(new JMIStreamFactory(){
                public OutputStream createStream(List pkg,
                String className,
                String extension)
                throws IOException {
                    
                    String filename = directory.getPath()+directory.separator;
                    
                    Iterator it = pkg.iterator();
                    while(it.hasNext()){
                        
                        filename+=(String) it.next() + java.io.File.separator;
                    }
                    (new File(filename)).mkdirs();
                    filename+=className+"."+extension;
                    
                    return new FileOutputStream(filename);
                    
                }},
                baseObject);
        } catch (IOException e){
            throw new RepositoryException(e);
        }
    }
    
    public Collection getAllNames() throws RepositoryException {
        return this.refPackageToName.values();
    }
    
    public void deleteMetaModel(ModelPackage modelPackage) throws RepositoryException {
        Iterator models = this.getModels(modelPackage).iterator();
        while(models.hasNext()){
            deleteModel((RefPackage) models.next());
        }
        this.refPackageToName.remove(modelPackage);
        modelPackage.refDelete();
    }
    
    public void deleteModel(RefPackage refPackage) throws RepositoryException {
        this.refPackageToName.remove(refPackage);
        refPackage.refDelete();
    }
    
    public void beginTrans(boolean writeAccess){
        this.mdRepository.beginTrans(writeAccess);
    }
    public void endTrans(boolean rollback){
        this.mdRepository.endTrans(rollback);
    }
    
}
