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
    * Cached version of the comment cleaned of all tags and asterisks.
    */
  private String m_sCleanedComment = null;
  
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
  
  /** 
    * Returns just the text of the documentation, without any appended doc tags.
    * @return just the text of the documentation, without any appended doc tags
    */
  public String getCleanedComment() {
    
    if (m_sDocComment == null)
      return null;
    
    if (m_sCleanedComment == null) {
      StringBuffer sbCleanedComment = (m_sDocComment.indexOf ('@') != -1)?
                                      (new StringBuffer (m_sDocComment.substring (3, m_sDocComment.indexOf ('@') - 1))):
                                      (new StringBuffer (m_sDocComment.substring (3, m_sDocComment.length() - 2)));

      for (int i = sbCleanedComment.toString().indexOf ('\n'); i != -1; i = sbCleanedComment.toString().indexOf ('\n', i)) {
        i++;

        while ((i < sbCleanedComment.length()) &&
                ((sbCleanedComment.charAt (i) == ' ') ||
                 (sbCleanedComment.charAt (i) == '*'))) {
          sbCleanedComment.deleteCharAt (i);
        }
      }

      m_sCleanedComment = sbCleanedComment.toString();
    }
    
    return m_sCleanedComment;
  }
  
  /** Return a version of the doccomment that has been updated based on the values of element-type and
    * key-type.
    * @param nIndent indentation level to be used for any new line in the comment
    * @return the updated doccomment
    */
  public String getUpdatedComment (int nIndent) {
    String sToReturn;
    String sIndent = "";
    for (int i = 0; i < nIndent + 2; i++) {
      // nIndent + 2 because all lines after the second doc comment line
      // are indented two more characters!
      sIndent += " ";
    }

    if (m_sDocComment == null) {
      sToReturn = "/**\n" + sIndent + "*\n" + sIndent;
      
      // Conditionally add key type      
      if (getKeyType() != null) {
        sToReturn += "* @key-type " + getKeyType() + "\n" + sIndent;
      }

      // Conditionally add element type
      if (getElementType() != null) {
        sToReturn += "* @element-type " + getElementType() + "\n" + sIndent;
      }
      
      // Add final delimiter
      sToReturn += "*/";
    }
    else {
      String sTemp = m_sDocComment;

      // Remove old element-type tag
      int nTemp = sTemp.indexOf ("@element-type ");
      
      if (nTemp != -1) {
        sTemp = sTemp.substring (0, sTemp.lastIndexOf ('\n', nTemp)) + 
                sTemp.substring (sTemp.indexOf ('\n', nTemp) + 1);
      }
      
      // Remove old key-type tag
      nTemp = sTemp.indexOf ("@key-type ");
      
      if (nTemp != -1) {
        sTemp = sTemp.substring (0, sTemp.lastIndexOf ('\n', nTemp)) + 
                sTemp.substring (sTemp.indexOf ('\n', nTemp) + 1);
      }
      
      // Remove comment delimiter
      sToReturn = sTemp.substring (0, sTemp.indexOf ("*/"));
      
      // Conditionally add key type
      if (getKeyType() != null) {
        sToReturn += "* @key-type " + getKeyType() + "\n" + sIndent;
      }

      // Conditionally add element type
      if (getElementType() != null) {
        sToReturn += "* @element-type " + getElementType() + "\n" + sIndent;
      }
      
      // Add final delimiter
      sToReturn += "*/";
    }
    
    return sToReturn;
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
    m_sElementType = sElementType;

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
    m_sKeyType = sKeyType;

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
    return m_acOwner.hasIncompleteElements();
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