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

import javax.swing.tree.*;

/** 
  * Abstract super class of MapDescriptor and CollectionDescriptor.
  *
  * @author  sz9 (Steffen Zschaler)
  * @version 0.1
  */
public abstract class AbstractDescriptor extends Object {

  private AnalysisConsumer m_acOwner;
  private JavaClass m_jcParent;
  private String m_sContainingClass;
  private String m_sName;
  private String m_sDocComment;
  private int m_nCommentID;
  private String m_sElementType;
  private String m_sKeyType;
  
  private RevengTreeNode m_rtnAssociatedNode = null;
  
  private String m_sCleanedComment = null;
  
  /** 
    * Creates new AbstractDescriptor 
    *
    * @param sName name of the described feature.
    * @param sComment the doccomment preceding the described feature.
    * @param nCommentID a number uniquely identifying the comment for the feature. (Number of comments from
    * beginning of source file.
    */
  public AbstractDescriptor (AnalysisConsumer acOwner,
                                JavaClass jcParent,
                                String sName,
                                String sComment,
                                int nCommentID) {
    super();
    
    m_acOwner = acOwner;
    m_jcParent = jcParent;    
    m_sName = sName;
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
  
  public String getName() {
    return m_sName;
  }
  
  /**
    * Returns just the text of the documentation, without any appended doc tags.
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
  
  /**
    * Return a version of the doccomment that has been updated based on the values of element-type and
    * key-type.
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
  
  public String getElementType() {
    return m_sElementType;
  }
  
  public void setElementType (String sElementType) {
    m_sElementType = sElementType;
    
    if (m_rtnAssociatedNode != null) {
      m_rtnAssociatedNode.setModified();
    }
  }  
  
  public String getKeyType() {
    return m_sKeyType;
  }

  protected void setKeyType (String sKeyType) {
    m_sKeyType = sKeyType;
    
    if (m_rtnAssociatedNode != null) {
      m_rtnAssociatedNode.setModified();
    }
  }  

  public int getCommentID () {
    return m_nCommentID;
  }
  
  public boolean isIncomplete() {
    return m_sElementType == null;
  }
  
  public abstract RevengTreeNode createTreeNode (DefaultTreeModel dtmModel);
  
  public void setAssociatedTreeNode (RevengTreeNode rtn) {
    m_rtnAssociatedNode = rtn;
  }  
  
  public String getContainingClass() {
    return m_sContainingClass;
  }
  
  public JavaClass getContainingClassJavaClass() {
    return m_jcParent;
  }
}