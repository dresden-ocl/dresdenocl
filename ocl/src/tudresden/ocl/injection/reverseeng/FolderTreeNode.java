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
 * FolderTreeNode.java
 *
 * Created on 17. August 2000, 12:35
 */
 
package tudresden.ocl.injection.reverseeng;

import java.io.*;

import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;

/** 
  * A tree node representing a File system folder.
  *
  * @author  sz9 (Steffen Zschaler)
  * @version 0.1
  */
public class FolderTreeNode extends RevengTreeNode {

  /**
    * ThreadPool managing the threads used to fill the folders.
    */
  static ThreadPool s_tpFolderFiller = new ThreadPool ("Folder Filler Pool");

  /**
    * Icon associated with normal folder without any files that contain incomplete maps or collections.
    */
  static Icon s_iFolderOK = new javax.swing.ImageIcon (FileTreeNode.class.getResource ("resources/folderOK.gif"));
  /**
    * Icon associated with open folder without any files that contain incomplete maps or collections.
    */
  static Icon s_iFolderOpenOK = new javax.swing.ImageIcon (FileTreeNode.class.getResource ("resources/folderOpenOK.gif"));
  /**
    * Icon associated with normal folder while checking for files with incomplete maps/collections.
    */
  static Icon s_iFolderWait = new javax.swing.ImageIcon (FileTreeNode.class.getResource ("resources/folderWait.gif"));
  /**
    * Icon associated with open folder while checking for files with incomplete maps/collections.
    */
  static Icon s_iFolderOpenWait = new javax.swing.ImageIcon (FileTreeNode.class.getResource ("resources/folderOpenWait.gif"));
  /**
    * Icon associated with normal folder containing files with incomplete maps/collections.
    */
  static Icon s_iFolderInCompl = new javax.swing.ImageIcon (FileTreeNode.class.getResource ("resources/folderInCompl.gif"));
  /**
    * Icon associated with open folder containing files with incomplete maps/collections.
    */
  static Icon s_iFolderOpenInCompl = new javax.swing.ImageIcon (FileTreeNode.class.getResource ("resources/folderOpenInCompl.gif"));

  /**
    * If true, use s_iFolderWait.
    */
  private boolean m_fUseDefaultIcon = true;

  /**
    * Does this folder contain any files that have incomplete collections/maps ?
    */
  private boolean m_fCriticalFolder = false;
  /**
    * Has this node already been filled?
    */
  private boolean m_fFilled = false;
  
  /**
    * Number of direct critical children.
    */
  private int m_cCriticalChildren = 0;
  /**
    * Number of direct uncritical children.
    */
  private int m_cUncriticalChildren = 0;
  
  /** 
    * Creates new FolderTreeNode 
    */
  public FolderTreeNode (DefaultTreeModel dtmModel) {
    super(dtmModel);
  }

  public FolderTreeNode (DefaultTreeModel dtmModel, File f) {
    this(dtmModel);

    setFolder (f);
  }
  
  /**
    * Retrieve the folder associated with this node.
    */
  public File getFolder() {
    return (File) getUserObject();
  }
  
  /**
    * Set the folder associated with this node.
    */
  public void setFolder (File f) {
    if (! f.isDirectory()) {
      throw new IllegalArgumentException ("FolderTreeNode can only deal with directories!");
    }
    
    setUserObject (f);

    m_fUseDefaultIcon = true;
    m_fCriticalFolder = false;
    
    if (getChildCount() > 0) {
      synchronized (this) {
        removeAllChildren();
      }
      
      nodeStructureChanged();
    }
    else {
      nodeChanged();
    }
    
    // Fill in children, so that we can calculate the icon...
    m_fFilled = false;
    s_tpFolderFiller.addTask (new Runnable() {
      public void run() {
        fill();
      }
    });
  }
    
  class RevengFileFilter implements FileFilter {
    public boolean accept (File f) {
      return ((f.isDirectory()) ||
               (f.getName().endsWith (".java")));
    }
  }
  
  public Icon getIcon (boolean fExpanded) {
    if (m_fUseDefaultIcon) {
      if (fExpanded) {
        return s_iFolderOpenWait;
      }
      else {
        return s_iFolderWait;
      }
    }
    else {
      if (m_fCriticalFolder) {
        if (fExpanded) {
          return s_iFolderOpenInCompl;
        }
        else {
          return s_iFolderInCompl;
        }
      }
      else {
        if (fExpanded) {
          return s_iFolderOpenOK;
        }
        else {
          return s_iFolderOK;
        }
      }
    }
  }
  
  /**
    * Fill in this node's children, setting the associated folder to sDirectory.
    */
  public void fill (String sDirectory) {
    setFolder (new File (sDirectory));
  }

  public synchronized final void fill() {
    if (m_fFilled)
      return;
    
    File[] afFiles = getFolder().listFiles (new RevengFileFilter());

    if (afFiles.length == 0) {
      setAllowsChildren (false);

      setCriticalFolder (false);
    }
    else {
      m_fCriticalFolder = false;
      removeAllChildren();
      m_cCriticalChildren = 0;      
      m_cUncriticalChildren = 0;      
      
      List lFiles = Arrays.asList (afFiles);
      Collections.sort (lFiles, new Comparator() {
        // Sort directories before files, same type sort lexikographically
        public int compare (Object o1, Object o2) {
          File f1 = (File) o1;
          File f2 = (File) o2;

          if (f1.isDirectory() == f2.isDirectory()) {
            // Either two directories or two files
            return String.CASE_INSENSITIVE_ORDER.compare (f1.getName(), f2.getName());
          }
          else {
            // One directory, one file
            if (f1.isDirectory()) {
              // f1 is the directory, which is "smaller" than the file f2.
              return -1;
            }
            else {
              // f2 is the directory, so f1 is "greater" than f2.
              return 1;
            }
          }
        }
      });

      for (Iterator i = lFiles.iterator(); i.hasNext();) {
        File f = (File) i.next();
        if (f.isDirectory()) {
          add (new FolderTreeNode (getModel(), f));
        }
        else {
          add (new FileTreeNode (getModel(), f));
        }
      }

      nodeStructureChanged();
    }
    
    m_fFilled = true;
  }
 
  /**
    * Set the critical folder property of this folder.
    */
  protected void setCriticalFolder (boolean fCritical) {
    if (m_fUseDefaultIcon ||
        (m_fCriticalFolder != fCritical)) {
      boolean fNotify = m_fCriticalFolder != fCritical;
      
      m_fUseDefaultIcon = false;
      m_fCriticalFolder = fCritical;

      nodeChanged();
      
      FolderTreeNode ftnParent = (FolderTreeNode) getParent();
      if (ftnParent != null) {
        if (fCritical) {
          if (fNotify)
            ftnParent.notifyCriticalChild();
        }
        else {
          if (fNotify) 
            ftnParent.notifyChildTurnedUnCritical();
          else
            ftnParent.notifyUnknownChildTurnedUnCritical();
        }
      }
    }
  }
  
  /**
    * Called when one of this node's children becomes critical.
    */
  public synchronized void notifyCriticalChild() {
    m_cCriticalChildren ++;
    
    if (m_cUncriticalChildren > 0) {
      m_cUncriticalChildren --;
    }
    
    setCriticalFolder (true);
  }
  
  /**
    * Called when one of this node's children turns from critical to uncritical.
    */
  public synchronized void notifyChildTurnedUnCritical() {
    m_cCriticalChildren --;
    m_cUncriticalChildren ++;
    
    if (m_cCriticalChildren <= 0) {
      setCriticalFolder (false);
    }
  }
  
  /**
    * Called when one of this node's children turns from unknown state to uncritical.
    */
  public synchronized void notifyUnknownChildTurnedUnCritical() {
    m_cUncriticalChildren++;
    
    if (m_cUncriticalChildren >= getChildCount()) {
      setCriticalFolder (false);
    }
  }
  
  public String toString() {
    return ((getFolder() != null)?
             (getFolder().getName()):
             ("<>"));
  }
  
}