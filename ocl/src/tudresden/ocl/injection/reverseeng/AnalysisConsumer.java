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
 * AnalysisConsumer.java
 *
 * Created on 7. August 2000, 16:34
 */
 
package tudresden.ocl.injection.reverseeng;

import tudresden.ocl.injection.*;

import java.util.*;

import java.io.*;

/** 
  * InjectionConsumer used to check for presence of element-type tags in a JavaFile.
  *
  * <p>After processing of a Java source file, {@link #m_lcdCollections} will contain a list of all collections
  * in the source file, together with their respective element-type tag. In addition, each element of the
  * list stores information that can be used to identifiy the exact comment where to place the element-type
  * tag when saving the modified file. {@link #m_lmdMaps} will contain a list of similar entries for each map 
  * in the analysed file.</p>
  *
  * <p>Note that this scheme only work if the Java Source code is not modified externally between analysis
  * and externalisation.</p>
  *
  * @see CollectionDescriptor
  * @see MapDescriptor
  *
  * @author  sz9 (Steffen Zschaler)
  * @version 0.1
  */
public class AnalysisConsumer extends Object implements InjectionConsumer {

  private static final int STATUS_MASK_NONE = 0;
  private static final int STATUS_MASK_COLLECTIONS = 1;
  private static final int STATUS_MASK_MAPS = 2;
  private static final int STATUS_MASK_INCOMPL = 4;
  
  /**
    * Normal Java file: no collections, no maps.
    */
  public static final int STATUS_NORMALFILE = STATUS_MASK_NONE;
  
  /**
    * File contains only collections which are complete.
    */
  public static final int STATUS_COLLECTIONSONLY = STATUS_MASK_COLLECTIONS;
  
  /**
    * File contains only collections, some of which are incomplete.
    */
  public static final int STATUS_COLLECTIONSONLY_INCOMPL = STATUS_MASK_COLLECTIONS | STATUS_MASK_INCOMPL;

  /**
    * File contains only maps which are complete.
    */
  public static final int STATUS_MAPSONLY = STATUS_MASK_MAPS;
  
  /**
    * File contains only maps, some of which are incomplete.
    */
  public static final int STATUS_MAPSONLY_INCOMPL = STATUS_MASK_MAPS | STATUS_MASK_INCOMPL;
  
  /**
    * File contains collections and maps which are complete.
    */
  public static final int STATUS_COLLECTIONSANDMAPS = STATUS_MASK_COLLECTIONS | STATUS_MASK_MAPS;
  
  /**
    * File contains collections and maps, some of which are incomplete.
    */
  public static final int STATUS_COLLECTIONSANDMAPS_INCOMPL = STATUS_MASK_COLLECTIONS | STATUS_MASK_MAPS | STATUS_MASK_INCOMPL;
  
  /**
    * Optimization: Cache for Collection class object.
    */
  public static Class s_clCollection;
  static {
    try {
      s_clCollection = Class.forName ("java.util.Collection");
    }
    catch (Throwable t) {
      t.printStackTrace ();
    }
  }
  
  /**
    * Optimization: Cache for Map class object.
    */
  public static Class s_clMap;
  static {
    try {
      s_clMap = Class.forName ("java.util.Map");
    }
    catch (Throwable t) {
      t.printStackTrace ();
    }
  }
  
  /**
    * The number of doc comments in the current file so far.
    */
  private int m_cComments = 0;
  
  /**
    * The last doc comment parsed so far.
    */
  private String m_sCurrentComment = null;
  
  /**
    * Collection attributes found in the current file.
    *
    * @element-type CollectionDescriptor
    */
  private List m_lcdCollections;
  {
    m_lcdCollections = new LinkedList();
  }
  
  /**
    * Map attributes found in the current file.
    *
    * @element-type MapDescriptor
    */
  private List m_lmdMaps;
  {
    m_lmdMaps = new LinkedList();
  }
  
  /**
    * All features found in the current file whether collections or maps.
    */
  private List m_ladFeatures;
  {
    m_ladFeatures = new LinkedList();
  }  
  
  private int m_nStatus = STATUS_NORMALFILE;
  
  /** 
    * Creates new AnalysisConsumer 
    */
  public AnalysisConsumer() {
    super ();
  }
  
  /** 
    * Encountered a package statement.
    * 
    * Ignored.
    *
    * @see JavaFile#getPackageName()
    */
  public void onPackage(JavaFile javafile) {}
  
  /** 
    * Encountered an import statement.
    * 
    * Ignored.
    *
    * @see JavaFile#findType(String)
    */
  public void onImport(String importname) {}
  
  /** 
    * Encountered a class header.
    * 
    * Ignored.
    *
    */
  public void onClass(JavaClass cc) {}
  
  /** 
    * Encountered the end of a class.
    * 
    * Ignored.
    *
    */
  public void onClassEnd(JavaClass cc) throws java.io.IOException {}
  
  /** 
    * Encountered the header of a java method.
    * 
    * Ignored.
    *
    */
  public void onBehaviourHeader(JavaBehaviour jb) throws java.io.IOException {}
  
  /** 
    * Called for attributes and methods.
    * 
    * <p>If an attribute, checks whether a collection. If so, creates a new entry in m_lCollections.</p>
    * 
    * <p><strong>This method relies on the compiled versions of all classes of the system to be analysed
    * to be available in the classpath.</strong></p>
    */
  public void onClassFeature(JavaFeature cf, String doccomment) throws InjectorParseException, java.io.IOException {
    if (cf instanceof JavaAttribute) {
      // Attribute. Also a collection?
      if (cf.getType () != null) {
        Class clClass = cf.getFile ().findType (cf.getType ());
        
        if (clClass != null) {
          // Not a simple type --> check whether collection
          if (s_clCollection.isAssignableFrom (clClass)) {
            // A collection
            CollectionDescriptor cd = new CollectionDescriptor (this, 
                                                                   cf.getParent(),
                                                                   cf.getName(), 
                                                                   m_sCurrentComment,
                                                                   m_cComments);
            
            if (m_sCurrentComment == null) {
              // Increase comment counter anyway, even though no comment had been attached.
              // This is done, so that each Descriptor in the list of features has a unique 
              // comment ID.
              m_cComments++;
            }
            
            m_lcdCollections.add (cd);
            m_ladFeatures.add (cd);
            
            m_nStatus |= STATUS_MASK_COLLECTIONS;
            
            if (cd.isIncomplete()) {
              m_nStatus |= STATUS_MASK_INCOMPL;
            }
          }
          else if (s_clMap.isAssignableFrom (clClass)) {
            // A map
            MapDescriptor md = new MapDescriptor (this, 
                                                    cf.getParent(),
                                                    cf.getName (), 
                                                    m_sCurrentComment,
                                                    m_cComments);
            
            if (m_sCurrentComment == null) {
              // Increase comment counter anyway, even though no comment had been attached.
              // This is done, so that each Descriptor in the list of features has a unique 
              // comment ID.
              m_cComments++;
            }
            
            m_lmdMaps.add (md);
            m_ladFeatures.add (md);

            m_nStatus |= STATUS_MASK_MAPS;
            if (md.isIncomplete()) {
              m_nStatus |= STATUS_MASK_INCOMPL;
            }            
          }
        }
      }
    }
    
    m_sCurrentComment = null;    
  }
  
  /** 
    * Encountered a java comment.
    *
    * <p>If this is a doc comment, it is saved temporarily in m_sCurrentComment, so that onClassFeature can
    * use it. Also, m_cComments is increased.</p>
    *
    * @return Always true, to indicate that the next feature should be parsed.
    */
  public boolean onDocComment(String doccomment) throws java.io.IOException {
    
    if (doccomment.startsWith ("/**")) {
      // JavaDoc comment: Save it!
      m_sCurrentComment = doccomment;
      m_cComments++;
    }
    
    return true;
  }
  
  /** 
    * Encountered the end of the input stream.
    * 
    * Ignored.
    *
    */
  public void onFileEnd() {}
  
  public boolean hasIncompleteElements() {
    return ((getStatus() & STATUS_MASK_INCOMPL) != 0);
  }
  
  public synchronized int getStatus() {
    return m_nStatus;
  }

  /**
    * Update the status of the file.
    */
  public void updateStatus() {    
    int nStatus = STATUS_MASK_NONE;
    
    if (m_lcdCollections.size() > 0) {
      nStatus |= STATUS_MASK_COLLECTIONS;
      
      for (Iterator i = m_lcdCollections.iterator(); i.hasNext() && ((nStatus & STATUS_MASK_INCOMPL) == 0);) {
        if (((CollectionDescriptor) i.next()).isIncomplete()) {
          nStatus |= STATUS_MASK_INCOMPL;
        }
      }
    }
    
    if (m_lmdMaps.size() > 0) {
      nStatus |= STATUS_MASK_MAPS;

      for (Iterator i = m_lmdMaps.iterator(); i.hasNext() && ((nStatus & STATUS_MASK_INCOMPL) == 0);) {
        if (((MapDescriptor) i.next()).isIncomplete()) {
          nStatus |= STATUS_MASK_INCOMPL;
        }
      }
    }
    
    synchronized (this) {
      m_nStatus = nStatus;
    }
  }

  public List getCollections() {
    return m_lcdCollections;
  }  
  
  public List getMaps() {
    return m_lmdMaps;
  }
  
  public List getAllFeatures() {
    return m_ladFeatures;
  }
  
  public static void main (String args[]) {
    try {
      Reader r = new InputStreamReader (AnalysisConsumer.class.getResourceAsStream ("../test/Example.java"));
      AnalysisConsumer ac = new AnalysisConsumer ();
      
      new Injector (r, null, ac).parseFile ();
      
      System.out.println (ac.m_lcdCollections);
      System.out.println (ac.m_lmdMaps);
    }
    catch (Throwable t) {
      t.printStackTrace();
    }
  }
 
  public static AnalysisConsumer analyse(File fToAnalyse) throws IOException, InjectorParseException {
    Reader r = new FileReader (fToAnalyse);
    AnalysisConsumer ac = new AnalysisConsumer ();
    
    new Injector (r, null, ac).parseFile ();
    
    return ac;
  }
}