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
 * AbstractDescriptor.java
 *
 * Created on 10. August 2000, 15:51
 */
 
package tudresden.ocl.injection.reverseeng;

import tudresden.ocl.injection.*;

import java.util.*;

import javax.swing.tree.*;

/** 
  * Abstract super class of MapDescriptor and CollectionDescriptor.
  *
  * @author  sz9 (Steffen Zschaler)
  * @version 0.1
  */
public abstract class AbstractDescriptor extends Object {

  private static final String ELEMENT = "@element-type";
  private static final String KEY = "@key-type";
  
  /**
    * The analysis in which this descriptor occurred.
    */
  private AnalysisConsumer m_acOwner;
  
  /**
    * The class containing the feature described.
    */
  private JavaClass m_jcParent;
  
  /**
    * String cache of the containing class hierarchy.
    */
  private String m_sContainingClass;
    
  /**
    * Name of the feature described.
    */
  private String m_sName;
  
  /**
    * Type of the feature described.
    */
  private String m_sType;
  
  /**
    * Doccomment associated with the feature.
    */
  private String m_sDocComment;
  
  /**
    * Comment ID associated with the feature.
    */
  private int m_nCommentID;
  
  /**
    * Element type tag associated with the feature.
    */
  private String m_sElementType;

  /**
    * Key type tag, if any, associated with the feature.
    */
  private String m_sKeyType;
  
  /**
    * Tree node associated with the descriptor.
    * (Probably obsolete!)
    */
  private RevengTreeNode m_rtnAssociatedNode = null;
  
  /**
    * EventObject fired by descriptors to notify listeners of changes in their state.
    */
  public static class AbstractDescriptorEvent extends EventObject {
    public AbstractDescriptorEvent (AbstractDescriptor adSource) {
      super (adSource);
    }
  }
  
  /**
    * Event interface sourced by {@link AbstractDescriptor}.
    */
  public static interface AbstractDescriptorListener extends EventListener {
    public void onDescriptorModified (AbstractDescriptorEvent ade);
  }
  
  /**
    * Listeners associated with this descriptor. They will be notified about modifications to the 
    * descriptor.
    *
    * @element-type AbstractDescriptorListener
    */
  private List m_ladlListeners = new LinkedList();
  
  /**
    * Creates new AbstractDescriptor
    *
    * @param acOwner analysis in which this feature occurred.
    * @param jcParent class containing the feature to be described.
    * @param sName name of the described feature.
    * @param sType type of the feature
    * @param sComment the doccomment preceding the described feature.
    * @param nCommentID a number uniquely identifying the comment for the feature. (Number of comments from
    * beginning of source file.
    */
  public AbstractDescriptor (AnalysisConsumer acOwner,
                                JavaClass jcParent,
                                String sName,
                                String sType,
                                String sComment,
                                int nCommentID) {
    super();
    
    m_acOwner = acOwner;
    m_jcParent = jcParent;    
    m_sName = sName;
    m_sType = sType;
    m_sDocComment = sComment;
    m_nCommentID = nCommentID;

    m_sContainingClass = m_jcParent.getName();
    for (JavaClass jc = m_jcParent.getParent(); jc != null; jc = jc.getParent()) {
      m_sContainingClass = jc.getName() + "$" + m_sContainingClass;
    }
    
    if (sComment != null) {
      m_sElementType = Injector.findDocTag (sComment, "element-type");
      m_sKeyType = Injector.findDocTag (sComment, "key-type");
    }
    else {
      m_sElementType = null;
      m_sKeyType = null;
    }
  }
  
  /** 
    * Get the name of the feature being described.
    * @return the name of the feature being described
    */
  public String getName() {
    return m_sName;
  }
  
  /** Return a version of the doccomment that has been updated based on the values of element-type and
    * key-type.
    * @param nIndent indentation level to be used for any new line in the comment
    * @return the updated doccomment
    */
  public String getDocComment() {    
    return m_sDocComment;
  }
  
  /**
    * Indents the doccomment (all but the first line) the specified number of characters.
    */
  public void indentComment (int nIndent) {
    if (m_sDocComment != null) {
      String sIndent = "";
      for (; nIndent > 0; nIndent --) {
        sIndent += " ";
      }
      
      int nPos = m_sDocComment.indexOf ('\n');
      while (nPos != -1) {
        m_sDocComment = m_sDocComment.substring (0, nPos + 1) + sIndent + m_sDocComment.substring (nPos + 1);
        
        nPos = m_sDocComment.indexOf ('\n', nPos + 1);
      }      
    }
  }
  
  protected void adjustDocComment (String sData, String sContext) {
    if (m_sDocComment == null) {
      m_sDocComment = "/** */";
    }
    
    int nTagPos = m_sDocComment.indexOf (sContext);
    
    if (nTagPos == -1) {
      // Find maximum indentation level
      int nMaxIndent = 0;
      int nIndent = 0;
      int nPos = 0;
      boolean fHadLineBreak = false;
      boolean fHadStars = true;
      
      while ((nPos > -1) && (nPos < m_sDocComment.length())) {
        if (m_sDocComment.charAt (nPos) != ' ') {
          
          if ((m_sDocComment.charAt (nPos) != '*') &&
               (m_sDocComment.charAt (nPos) != '/')) {
            // Mark the fact that comment lines do not start with asterisks
            fHadStars = false;
          }
          
          // Find next \n and start searching from there
          nPos = m_sDocComment.indexOf ('\n', nPos);
          
          fHadLineBreak |= (nPos > -1);
          nIndent = 0;
        }
        else {
          nIndent++;
          if (nIndent > nMaxIndent) {
            nMaxIndent = nIndent;
          }
        }
        
        if (nPos > -1) {
          nPos++;
        }
      }
      
      if (! fHadLineBreak) {
        nMaxIndent += 2;
      }
      
      String sIndent = "";
      for (int i = nMaxIndent; i > 0; i--) {
        sIndent += " ";
      }
      
      int nInsertPos = m_sDocComment.indexOf ("*/");
      
      // Check whether \n found directly preceding "*/"
      int nLastLineFeedPos = m_sDocComment.lastIndexOf ('\n', nInsertPos);
      nPos = nLastLineFeedPos + 1;
      boolean fLineFeedBeforeEnd = true;

      while ((nPos < nInsertPos) &&
              fLineFeedBeforeEnd) {
        if (m_sDocComment.charAt(nPos) != ' ') {
          fLineFeedBeforeEnd = false;
        }
        
        nPos++;
      }
      
      if (!fLineFeedBeforeEnd) {
        m_sDocComment = m_sDocComment.substring (0, nInsertPos) + 
                        "\n" + sIndent + ((fHadStars)?("* "):("")) + sContext + " " + sData + 
                        "\n" + sIndent + "*/";
      }
      else {
        m_sDocComment = m_sDocComment.substring (0, nLastLineFeedPos) + 
                        "\n" + sIndent + ((fHadStars)?("* "):("")) + sContext + " " + sData + 
                        "\n" + sIndent + "*/";
      }
      
    }
    else {
      int nEndPos = m_sDocComment.indexOf ('\n', nTagPos);
      if (nEndPos == -1) {
        nEndPos = m_sDocComment.indexOf ("*/", nTagPos);
      }
      
      m_sDocComment = m_sDocComment.substring (0, nTagPos + sContext.length()) + " " + sData + " " + 
                      m_sDocComment.substring (nEndPos);
    }
  }
  
  /** 
    * Return the contents of the element-type tag for the described feature.
    * @return the contents of the element-type tag for the described feature
    */
  public String getElementType() {
    return m_sElementType;
  }
  
  /**
    * Set the contents of the element-type tag for the described feature.
    * <p>Fires {@link AbstractDescriptor.AbstractDescriptorListener#onDescriptorModified } event.</p>
    * @param sElementType The new contents of the element-type tag. <CODE>null</CODE> to delete the element-type tag.
    */
  public void setElementType(String sElementType) {
    
    // Trim to single word
    sElementType = sElementType.trim();
    
    int nSpacePos = sElementType.lastIndexOf (' ');
    if (nSpacePos > -1) {
      sElementType = sElementType.substring (0, nSpacePos);
    }
    
    m_sElementType = sElementType;

    adjustDocComment (sElementType, ELEMENT);
    
    fireModified();
  }  
  
  /** 
    * Return the contents of the key-type tag for the described feature.
    * @return the contents of the key-type tag for the described feature.
    */
  public String getKeyType() {
    return m_sKeyType;
  }

  /** 
    * Set the contents of the key-type tag for the described feature.
    * <p>Fires {@link AbstractDescriptor.AbstractDescriptorListener#onDescriptorModified } event.</p>
    * @param sKeyType The new contents of the key-type tag. <CODE>null</CODE> to delete the key-type tag.
    */
  protected void setKeyType(String sKeyType) {
    // Trim to single word
    sKeyType = sKeyType.trim();
    
    int nSpacePos = sKeyType.lastIndexOf (' ');
    if (nSpacePos > -1) {
      sKeyType = sKeyType.substring (0, nSpacePos);
    }
    
    m_sKeyType = sKeyType;

    adjustDocComment (sKeyType, KEY);
    
    fireModified();
  }  

  /** 
    * Return the comment ID associated with this descriptor.
    * @return the comment ID associated with this descriptor.
    */
  public int getCommentID() {
    return m_nCommentID;
  }
  
  /** 
    * True if the descriptor is incomplete, i.e. if a necessary tag 
    * (element-type/key-type) is not filled in.
    * @return True if the descriptor is incomplete, i.e. if a necessary tag 
    * (element-type/key-type) is not filled in.
    */
  public boolean isIncomplete() {
    return ((getElementType() == null) ||
             (getElementType().length() == 0));
  }
  
  /** 
    * Create tree node to render this descriptor.
    * @param dtmModel Tree model which the tree node should become part of.
    * @return the freshly created node
    */
  public abstract RevengTreeNode createTreeNode (DefaultTreeModel dtmModel);
  
  /** 
    * Internal method reassociating a tree node with this descriptor.
    *
    * (Probably obsolete!)
    * @param rtn The tree node with which to associate the descriptor.
    */
  public void setAssociatedTreeNode (RevengTreeNode rtn) {
    if (m_rtnAssociatedNode != null) {
      removeModifiedListener (m_rtnAssociatedNode);
    }

    m_rtnAssociatedNode = rtn;

    if (m_rtnAssociatedNode != null) {
      addModifiedListener (m_rtnAssociatedNode);
    }
  }
  
  /** Get the type of the feature described.
    * @return the type of the feature described
    */
  public String getType() {
    return m_sType;
  }
  
  /** 
    * Get the class hierarchy containing the described feature.
    * @return the class hierarchy containing the described feature
    */
  public JavaClass getContainingClass() {
    return m_jcParent;
  }
  
  /** 
    * True, if feature described is a collection and not a map.
    * @return true, if feature described is a collection and not a map
    */
  public abstract boolean isCollection();
  
  /**
    * Get the name of the described feature as for displaying it to a user.
    *
    * @param fExpanded if true, add containment hierarchy information to display name. Otherwise just return plain feature name.
    * @return  
    */
  public String getDisplayName (boolean fExpanded) {
    if (! fExpanded) {
      return m_sName;
    }
    else {
      if (! m_jcParent.getName().equals (m_acOwner.getFileName())) {
        return m_sContainingClass + "." + m_sName;
      }
      else {
        return m_sName;
      }
    }    
  }
  
  public void addModifiedListener (AbstractDescriptorListener adl) {
    synchronized (m_ladlListeners) {
      m_ladlListeners.add (adl);
    }
  }

  public void removeModifiedListener (AbstractDescriptorListener adl) {
    synchronized (m_ladlListeners) {
      m_ladlListeners.remove (adl);
    }
  }
  
  protected void fireModified() {
    synchronized (m_ladlListeners) {
      AbstractDescriptorEvent ade = new AbstractDescriptorEvent (this);
      for (Iterator i = m_ladlListeners.iterator(); i.hasNext();) {
        ((AbstractDescriptorListener) i.next()).onDescriptorModified (ade);
      }
    }
  }
}