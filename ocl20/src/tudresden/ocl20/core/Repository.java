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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.jmi.reflect.RefBaseObject;
import javax.jmi.reflect.RefObject;
import javax.jmi.reflect.RefPackage;
import javax.jmi.xmi.XmiReader;
import javax.jmi.xmi.XmiWriter;
import javax.jmi.model.ModelPackage;
import org.netbeans.api.mdr.CreationFailedException;
import org.netbeans.api.mdr.JMIMapper;
import org.netbeans.api.mdr.JMIStreamFactory;
import org.netbeans.api.xmi.XMIReaderFactory;
import org.netbeans.api.xmi.XMIWriterFactory;

/**Defines the interface to the JMI respository.*/

public interface Repository {
    void shutdown();
    
    /**
     * Creates a metamodel.
     */
    ModelPackage createMetaModel(String name) throws RepositoryException;
    
    /**
     * Deletes a metamodel and all its models.
     */
    void deleteMetaModel(ModelPackage modelPackage) throws RepositoryException;
    
    /**
     * Finds a metamodel by its name.
     */
    ModelPackage getMetaModel(String name) throws RepositoryException;
    
    /**
     * Finds all models in the repository that are instance of metaModel.
     */
    Collection getModels(ModelPackage metaModel) throws RepositoryException;
    
    /**
     * Creates a model. Note, that metaPackage is not a ModelPackage,
     * but a MofPackage in the metamodel (for instance for the UML metamodel this is the MofPackage named "UML").
     */
    RefPackage createModel(String name, RefObject metaPackage) throws RepositoryException;
    
    /**
     * Deletes a model.
     */
    void deleteModel(RefPackage refPackage) throws RepositoryException;
    
    /**
     * Finds a model by its name.
     */
    RefPackage getModel(String name) throws RepositoryException;
    
    /**
     * Gets the name of a model/metamodel
     */
    String getName(RefPackage model) throws RepositoryException;
    
    /**
     * Gets the name of all models/metamodels in the repository.
     */
    Collection getAllNames() throws RepositoryException;
    
    /**
     * Creates an XMI reader.
     */
    XmiReader createXMIReader() throws RepositoryException;
    
    /**
     * Creates an XMI writer.
     */
    XmiWriter createXMIWriter() throws RepositoryException;
    
    
    /**
     * Starts an transaction.
     */
    void beginTrans(boolean writeAccess);
    
    /**
     * Commits a transaction or rolls it back.
     */
    void endTrans(boolean rollback);
    
    
    /**
     * Generates JMI interfaces.
     */
    void generateJMIInterfaces(RefBaseObject baseObject, final File directory) throws RepositoryException;
    
}
