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

/** 
  * Abstract super class of MapDescriptor and CollectionDescriptor.
  *
  * @author  sz9 (Steffen Zschaler)
  * @version 0.1
  */
public abstract class AbstractDescriptor extends Object {

  private String m_sName;
  private String m_sDocComment;
  private int m_nCommentID;
  private String m_sElementType;
  private String m_sKeyType;
  
  private String m_sCleanedComment = null;
  
  /** 
    * Creates new AbstractDescriptor 
    *
    * @param sName name of the described feature.
    * @param sComment the doccomment preceding the described feature.
    * @param nCommentID a number uniquely identifying the comment for the feature. (Number of comments from
    * beginning of source file.
    */
  public AbstractDescriptor (String sName, String sComment, int nCommentID) {
    super();
    
    m_sName = sName;
    m_sDocComment = sComment;
    m_nCommentID = nCommentID;

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
  
  public String getElementType() {
    return m_sElementType;
  }
  
  public void setElementType (String sElementType) {
    m_sElementType = sElementType;
  }  
  
  public String getKeyType() {
    return m_sKeyType;
  }

  protected void setKeyType (String sKeyType) {
    m_sKeyType = sKeyType;
  }  
  
  public int getCommentID () {
    return m_nCommentID;
  }
  
  public boolean isIncomplete() {
    return m_sElementType == null;
  }
}