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
 * FileTreeNode.java
 *
 * Created on 16. August 2000, 15:50
 */
 
package tudresden.ocl.injection.reverseeng;

import java.io.*;

import java.util.*;

import javax.swing.*;

import javax.swing.tree.*;

import tudresden.ocl.injection.*;

/** 
  * A tree node representing a Java Source file.
  *
  * @author  sz9 (Steffen Zschaler)
  * @version 0.1
  */
public class FileTreeNode extends RevengTreeNode {

  /**
    * ThreadPool managing the threads used to calculate the correct icons.
    */
  static ThreadPool s_tpIconComputers = new ThreadPool ("File Icon Calculator Pool");

  /**
    * Icon associated with normal file without any maps or collections.
    */
  static Icon s_iNormalFile = new javax.swing.ImageIcon (FileTreeNode.class.getResource ("resources/normalFile.gif"));
  /**
    * Icon associated with file until the correct icon has been computed.
    */
  static Icon s_iFileWait = new javax.swing.ImageIcon (FileTreeNode.class.getResource ("resources/fileWait.gif"));
  /**
    * Icon associated with file that could not be parsed.
    */
  static Icon s_iFileError = new javax.swing.ImageIcon (FileTreeNode.class.getResource ("resources/fileError.gif"));
  /**
    * Icon associated with file that contains completely documented collections only.
    */
  static Icon s_iFileWithCollections = new javax.swing.ImageIcon (FileTreeNode.class.getResource ("resources/fileWithCollections.gif"));
  /**
    * Icon associated with file that contains collections only, which may be incompletely documented.
    */
  static Icon s_iFileWithCollectionsInComplete = new javax.swing.ImageIcon (FileTreeNode.class.getResource ("resources/fileWithCollectionsInComplete.gif"));
  /**
    * Icon associated with file that contains completely documented collections and maps.
    */
  static Icon s_iFileWithCollectionsAndMaps = new javax.swing.ImageIcon (FileTreeNode.class.getResource ("resources/fileWithCollectionsAndMaps.gif"));
  /**
    * Icon associated with file that contains collections and maps, which may be incompletely documented.
    */
  static Icon s_iFileWithCollectionsAndMapsInComplete = new javax.swing.ImageIcon (FileTreeNode.class.getResource ("resources/fileWithCollectionsAndMapsInComplete.gif"));
  /**
    * Icon associated with file that contains completely documented maps only.
    */
  static Icon s_iFileWithMaps = new javax.swing.ImageIcon (FileTreeNode.class.getResource ("resources/fileWithMaps.gif"));
  /**
    * Icon associated with file that contains maps only, which may be incompletely documented.
    */
  static Icon s_iFileWithMapsInComplete = new javax.swing.ImageIcon (FileTreeNode.class.getResource ("resources/fileWithMapsInComplete.gif"));

  /**
    * If true, use s_iFileWait.
    */
  private boolean m_fUseDefaultIcon = true;
  /**
    * Has the associated file already been parsed?    
    */
  private boolean m_fParsed = false;
  /**
    * Did parsing yield any errors?
    */
  private boolean m_fHadError = false;
  /**
    * If any errors occurred: error message to be presented to user.
    */
  private String m_sErrorMessage = null;
  /**
    * Results of parsing.
    */
  private AnalysisConsumer m_acAnalysisResults = null;  
  
  /**
    * Flag indicating whether the file needs to be saved.
    */
  private boolean m_fDirty = false;
  
  public FileTreeNode (DefaultTreeModel dtmModel) {
    super(dtmModel);
  }

  public FileTreeNode (DefaultTreeModel dtmModel, File f) {
    this(dtmModel);

    setFile (f);
  }

  /**
    * Set the file associated to this node.
    */
  public void setFile (File f) {
    if (f.isDirectory()) {
      throw new IllegalArgumentException ("FileTreeNode can only deal with files!");
    }
    
    setUserObject (f);

    m_fUseDefaultIcon = true;
    m_fParsed = false;
    m_fHadError = false;
    m_sErrorMessage = null;
    m_acAnalysisResults = null;

    nodeChanged();
    
    // Recompute icon...
    s_tpIconComputers.addTask (new Runnable() {
      public void run() {
        ensureParsed (false);

        m_fUseDefaultIcon = false;
        
        FolderTreeNode ftnParent = (FolderTreeNode) getParent();
        if (ftnParent != null) {
          if (m_fHadError ||
              ! m_acAnalysisResults.hasIncompleteElements()) {
            ftnParent.notifyUnknownChildTurnedUnCritical();
          }
          else {
            ftnParent.notifyCriticalChild();
          }
        }
        
        nodeChanged();
      }
    });
  }

  /**
    * Get the file associated with this node.
    */
  public File getFile() {
    return (File) getUserObject ();
  }
  
  /**
    * Make sure the associated file was parsed, if it is not a directory.
    *
    * @param fUpdateChildren if true, reflect the results of parsing in the node's children.
    */
  protected synchronized void ensureParsed (boolean fUpdateChildren) {
    if (! m_fParsed) {
      m_acAnalysisResults = null;
      
      try {
        m_acAnalysisResults = AnalysisConsumer.analyse (getFile());
        m_fHadError = false;
      }
      catch (IOException ioex) {
        m_acAnalysisResults = null;
        m_fHadError = true;

        m_sErrorMessage = "Couldn't read file: " + ioex.getLocalizedMessage();          
      }
      catch (InjectorParseException ipe) {
        m_acAnalysisResults = null;
        m_fHadError = true;
        m_sErrorMessage = "Not a Java file, or file is corrupted: " + ipe.getMessage();
      }
      finally {
        m_fParsed = true;
      }
    }


    if (fUpdateChildren) {
      removeAllChildren();

      if (! m_fHadError) {
        for (Iterator i = m_acAnalysisResults.getAllFeatures().iterator(); i.hasNext();) {
          add (((AbstractDescriptor) i.next()).createTreeNode(getModel ()));
        }
      }
      else {
        add (new ErrorTreeNode (getModel(), m_sErrorMessage));
      }
    }
  }

  public Icon getIcon (boolean fExpanded) {
    if (m_fUseDefaultIcon) {
      return s_iFileWait;
    }

    ensureParsed (false);

    if (! m_fHadError) {
      switch (m_acAnalysisResults.getStatus()) {
        case AnalysisConsumer.STATUS_NORMALFILE:
          return s_iNormalFile;
        case AnalysisConsumer.STATUS_COLLECTIONSONLY:
          return s_iFileWithCollections;
        case AnalysisConsumer.STATUS_COLLECTIONSONLY_INCOMPL:
          return s_iFileWithCollectionsInComplete;
        case AnalysisConsumer.STATUS_MAPSONLY:
          return s_iFileWithMaps;
        case AnalysisConsumer.STATUS_MAPSONLY_INCOMPL:
          return s_iFileWithMapsInComplete;
        case AnalysisConsumer.STATUS_COLLECTIONSANDMAPS:
          return s_iFileWithCollectionsAndMaps;
        case AnalysisConsumer.STATUS_COLLECTIONSANDMAPS_INCOMPL:
          return s_iFileWithCollectionsAndMapsInComplete;
        default:
          return s_iFileError;
      }
    }
    else {
      return s_iFileError;
    }
  }

  public void fill () {
    ensureParsed (true);

    nodeStructureChanged();
  }

  public String toString() {
    return ((getFile() != null)?
             (getFile().getName()):
             ("<>"));
  }
  
  /**
    * Notification that underlying file was modified.
    *
    * Checks the icon and remembers dirty state.
    */
  public void setModified() {
    super.setModified();

    updateIcon();
    
    m_fDirty = true;
  }
  
  private void updateIcon() {
    ensureParsed (false); // Should actually not be necessary, but just in case...

    if (m_acAnalysisResults != null) {
      boolean fWasIncomplete = m_acAnalysisResults.hasIncompleteElements();

      m_acAnalysisResults.updateStatus();

      if (fWasIncomplete != m_acAnalysisResults.hasIncompleteElements()) {
        FolderTreeNode ftnParent = (FolderTreeNode) getParent();

        if (ftnParent != null) {
          if (fWasIncomplete) {
            ftnParent.notifyChildTurnedUnCritical();
          }
          else {
            ftnParent.notifyCriticalChild();
          }
        }
      }

      nodeChanged();
    }
  }
  
  /**
    * Return true if this file has been modified since it has last been saved.
    */
  public boolean isDirty() {
    return m_fDirty;
  }
}