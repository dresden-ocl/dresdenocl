/*
Copyright (C) 2000  Steffen Zschaler

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

/*
 * FileSaveConsumer.java
 *
 * Created on 6. September 2000, 17:53
 */
 
package tudresden.ocl.injection.reverseeng;

import java.io.*;
import java.util.*;

import tudresden.ocl.injection.*;

/** 
  * InjectionConsumer used to save changes made via RevengGUI.
  *
  * @author  sz9 (Steffen Zschaler)
  * @version 0.1
  */
public class FileSaveConsumer extends Object implements InjectionConsumer {

  /**
    * The writer used to produce the output file.
    */
  private Writer m_wOutput = null;
  
  /**
    * The AnalysisConsumer that contains the information about the analysed file.
    */
  private AnalysisConsumer m_acAnalysisResults = null;
  
  /**
    * The number of doc comments in the current file so far.
    */
  private int m_cComments = 0;
 
  /**
    * The Iterator of features that need a corrected doccomment.
    */
  private Iterator m_iFeatures = null;
  
  /**
    * The next feature that needs a corrected doccomment.
    */
  private AbstractDescriptor m_adCurrentFeature = null;
  
  /** Creates new FileSaveConsumer */
  public FileSaveConsumer (Writer wOutput, AnalysisConsumer acAnalysisResults) {
    super();
    
    m_wOutput = wOutput;
    m_acAnalysisResults = acAnalysisResults;
    m_iFeatures = m_acAnalysisResults.getAllFeatures().iterator();
  }
  
  private AbstractDescriptor getCurrentFeature() {
    if (m_adCurrentFeature == null) {
      if (m_iFeatures.hasNext()) {
        m_adCurrentFeature = (AbstractDescriptor) m_iFeatures.next();
      }
    }
    
    return m_adCurrentFeature;    
  }
  
  /** Encountered a package statement.
    * This method is guaranteed to be called at most once.
    * @see JavaFile#getPackageName()
    */
  public void onPackage(JavaFile javafile) throws InjectorParseException {
  }
  
  /** Encountered an import statement.
    * Imports are also saved in JavaFile.imports.
    * This information may be used for mapping type names to types.
    * @see JavaFile#findType(String)
    */
  public void onImport(String importname) {
  }
  
  /** Encountered a class header.
    * Is also called for inner classes.
    */
  public void onClass(JavaClass cc) {
  }
  
  /** Encountered the end of a class.
    * @parameter cc
    * the same object as in the corresponding call to onClass
    * @see #onClass(JavaClass)
    */
  public void onClassEnd(JavaClass cc) throws java.io.IOException, InjectorParseException {
  }

  /** Encountered the header of a java method.
    * Is called additionally to
    * {@link #onClassFeature(JavaFeature, String)}.
    *
    * @parameter cf
    * contains all parsed information about the method
    */
  public void onBehaviourHeader(JavaBehaviour jb) throws java.io.IOException {
    if (m_wOutput != null) {
      m_wOutput.write (jb.getLiteral());
    }
  }
 
  /** Called for attributes and methods.
    * Is called additionally to
    * {@link #onBehaviourHeader(JavaBehaviour)}.
    *
    * @parameter doccomment
    * the doccomment associated to this feature.
    * Is null, if there was none.
    */
  public void onClassFeature(JavaFeature cf,String doccomment) throws java.io.IOException, InjectorParseException {
  }
  
  /** Encountered a java documentation comment.
    * Is called for comments on class level only,
    * i.e. inside a class, but outside of methods and attributes.
    *
    * @return
    * if false is returned, the next class feature is ignored.
    */
  public boolean onDocComment(String doccomment) throws java.io.IOException {
    if (m_wOutput != null) {
      m_wOutput.write (doccomment);
    }
    return true;
  }

  /** Encountered the end of the input stream.
    */
  public void onFileEnd() {
  }
}