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

  /**
    * Normal Java file: no collections, no maps.
    */
  public static final int STATUS_NORMALFILE = 0;
  
  /**
    * File contains only collections which are complete.
    */
  public static final int STATUS_COLLECTIONSONLY = 1;
  
  /**
    * File contains only collections, some of which are incomplete.
    */
  public static final int STATUS_COLLECTIONSONLY_INCOMPL = 2;

  /**
    * File contains only maps which are complete.
    */
  public static final int STATUS_MAPSONLY = 3;
  
  /**
    * File contains only maps, some of which are incomplete.
    */
  public static final int STATUS_MAPSONLY_INCOMPL = 4;
  
  /**
    * File contains collections and maps which are complete.
    */
  public static final int STATUS_COLLECTIONSANDMAPS = 5;
  
  /**
    * File contains collections and maps, some of which are incomplete.
    */
  public static final int STATUS_COLLECTIONSANDMAPS_INCOMPL = 6;
  
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
  public void onClassFeature(JavaFeature cf) throws InjectorParseException, java.io.IOException {
    if (cf instanceof JavaAttribute) {
      // Attribute. Also a collection?
      if (cf.getType () != null) {
        Class clClass = cf.getFile ().findType (cf.getType ());
        
        if (clClass != null) {
          // Not a simple type --> check whether collection
          if (s_clCollection.isAssignableFrom (clClass)) {
            // A collection
            CollectionDescriptor cd = new CollectionDescriptor (cf.getName (), 
                                                                   m_sCurrentComment,
                                                                   m_cComments);
            m_lcdCollections.add (cd);
            
            switch (m_nStatus) {
              case STATUS_COLLECTIONSANDMAPS:
                if (cd.isIncomplete()) {
                  m_nStatus = STATUS_COLLECTIONSANDMAPS_INCOMPL;
                }
                break;
                
              case STATUS_MAPSONLY:
                if (cd.isIncomplete()) {
                  m_nStatus = STATUS_COLLECTIONSANDMAPS_INCOMPL;
                }
                else {
                  m_nStatus = STATUS_COLLECTIONSANDMAPS;
                }
                break;

              case STATUS_MAPSONLY_INCOMPL:
                  m_nStatus = STATUS_COLLECTIONSANDMAPS_INCOMPL;
                  break;

              case STATUS_NORMALFILE:                
              case STATUS_COLLECTIONSONLY:
                if (cd.isIncomplete()) {
                  m_nStatus = STATUS_COLLECTIONSONLY_INCOMPL;
                }
                else {
                  m_nStatus = STATUS_COLLECTIONSONLY;
                }
            }
          }
          else if (s_clMap.isAssignableFrom (clClass)) {
            // A map
            MapDescriptor md = new MapDescriptor (cf.getName (), 
                                                    m_sCurrentComment,
                                                    m_cComments);
            m_lmdMaps.add (md);

            switch (m_nStatus) {
              case STATUS_COLLECTIONSANDMAPS:
                if (md.isIncomplete()) {
                  m_nStatus = STATUS_COLLECTIONSANDMAPS_INCOMPL;
                }
                break;
                
              case STATUS_COLLECTIONSONLY:
                if (md.isIncomplete()) {
                  m_nStatus = STATUS_COLLECTIONSANDMAPS_INCOMPL;
                }
                else {
                  m_nStatus = STATUS_COLLECTIONSANDMAPS;
                }
                break;

              case STATUS_COLLECTIONSONLY_INCOMPL:
                  m_nStatus = STATUS_COLLECTIONSANDMAPS_INCOMPL;
                  break;
                  
              case STATUS_NORMALFILE:
              case STATUS_MAPSONLY:
                if (md.isIncomplete()) {
                  m_nStatus = STATUS_MAPSONLY_INCOMPL;
                }
                else {
                  m_nStatus = STATUS_MAPSONLY;
                }
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
  public boolean onComment(String comment) throws java.io.IOException {
    
    if (comment.startsWith ("/**")) {
      // JavaDoc comment: Save it!
      m_sCurrentComment = comment;
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
  
  public int getStatus() {
    return m_nStatus;
  }
  
  public List getCollections() {
    return m_lcdCollections;
  }  
  
  public List getMaps() {
    return m_lmdMaps;
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