/*
 * ContainingClassTree.java
 *
 * Created on 11. September 2000, 17:00
 */
 
package tudresden.ocl.injection.reverseeng;

import tudresden.ocl.injection.*;

import javax.swing.*;
import javax.swing.tree.*;

/** 
 *
 * @author  sz9
 * @version 
 */
public class FeatureOverviewPage extends javax.swing.JPanel {

  private DefaultTreeModel m_dtmModel;
  private DefaultMutableTreeNode m_dmtnLastNode = null;
  private TreePath m_tpCompletePath = null; // Complete tree path, so that we can expand it by default...
  
  static class ContainingLevel {
    
    public static final int ATTR_PACKAGE = 1;
    public static final int ATTR_CLASS = 2;
    public static final int ATTR_COLLECTION = 4;
    public static final int ATTR_MAP = 8;
    public static final int ATTR_INCOMPL = 16;  // To be |-combined with ATTR_MAP or ATTR_COLLECTION.
    
    private int m_nAttribute;
    private String m_sName;
    
    public ContainingLevel (int nAttribute,
                               String sName) {
      super();
      
      m_nAttribute = nAttribute;
      m_sName = sName;
    }
    
    public String toString() {
      return m_sName;
    }
    
    public boolean isPackage() {
      return m_nAttribute == ATTR_PACKAGE;
    }
    
    public boolean isCollection() {
      return (m_nAttribute & ATTR_COLLECTION) == ATTR_COLLECTION;
    }

    public boolean isMap() {
      return (m_nAttribute & ATTR_MAP) == ATTR_MAP;
    }
    
    public boolean isIncomplete() {
      return (m_nAttribute & ATTR_INCOMPL) == ATTR_INCOMPL;
    }
  }
  
  static class ContainingLevelRenderer extends DefaultTreeCellRenderer {

    static Icon s_iClass = new javax.swing.ImageIcon (ContainingLevelRenderer.class.getResource ("resources/class.gif"));
    static Icon s_iMapOK = new javax.swing.ImageIcon (ContainingLevelRenderer.class.getResource ("resources/map.gif"));
    static Icon s_iMapInCompl = new javax.swing.ImageIcon (ContainingLevelRenderer.class.getResource ("resources/mapInCompl.gif"));
    static Icon s_iCollectionOK = new javax.swing.ImageIcon (ContainingLevelRenderer.class.getResource ("resources/collection.gif"));
    static Icon s_iCollectionInCompl = new javax.swing.ImageIcon (ContainingLevelRenderer.class.getResource ("resources/collectionInCompl.gif"));

    public ContainingLevelRenderer () {
      super();
    }
    
    public java.awt.Component getTreeCellRendererComponent (javax.swing.JTree tree,
                                                                 Object value,
                                                                 boolean sel,
                                                                 boolean expanded,
                                                                 boolean leaf,
                                                                 int row,
                                                                 boolean hasFocus) {
      super.getTreeCellRendererComponent (tree, value, sel, expanded, leaf, row, hasFocus);
      
      ContainingLevel cl = (ContainingLevel) ((DefaultMutableTreeNode) value).getUserObject();
      
      if (! cl.isPackage()) {
        if (cl.isCollection()) {
          if (cl.isIncomplete()) {
            setIcon (s_iCollectionInCompl);
          }
          else {
            setIcon (s_iCollectionOK);
          }
        }
        else if (cl.isMap()) {
          if (cl.isIncomplete()) {
            setIcon (s_iMapInCompl);
          }
          else {
            setIcon (s_iMapOK);
          }
        }
        else {
          setIcon (s_iClass);
        }
      }
      
      return this;
    }
  }
  
  /** Creates new form ContainingClassTree */
  public FeatureOverviewPage(AbstractDescriptor ad) {
    fillInClassTree (ad.getContainingClassJavaClass());
    
    // Add feature
    if (m_dmtnLastNode == null) {
      m_dmtnLastNode = new DefaultMutableTreeNode ();
      m_dtmModel = new DefaultTreeModel (m_dmtnLastNode);
      
      m_tpCompletePath = new TreePath (m_dmtnLastNode);
    }
    else {
      DefaultMutableTreeNode dmtnNext = new DefaultMutableTreeNode();
      m_dmtnLastNode.add (dmtnNext);
      m_dmtnLastNode = dmtnNext;
      
      m_tpCompletePath = m_tpCompletePath.pathByAddingChild (m_dmtnLastNode);
    }

    int nAttribute = ((ad.isCollection())?
                       (ContainingLevel.ATTR_COLLECTION):
                       (ContainingLevel.ATTR_MAP));
                      
    if (ad.isIncomplete()) {
      nAttribute |= ContainingLevel.ATTR_INCOMPL;
    }
    
    m_dmtnLastNode.setUserObject (new ContainingLevel (nAttribute, 
                                                           ad.getName()));

    // Build visual components
    initComponents ();
    
    // Expand complete tree...
    m_jtContainingClassTree.makeVisible (m_tpCompletePath);
  }

  private void fillInPackageTree (JavaClass jc) {
    String sPackage = jc.getPackageName();
    
    if ((sPackage != null) &&
         (sPackage != "")) {
      int nDotPos;
      while ((nDotPos = sPackage.indexOf ('.')) > -1) {
        String sTopPackage = sPackage.substring (0, nDotPos);
        
        if (m_dmtnLastNode == null) {
          m_dmtnLastNode = new DefaultMutableTreeNode ();
          m_dtmModel = new DefaultTreeModel (m_dmtnLastNode);

          m_tpCompletePath = new TreePath (m_dmtnLastNode);
        }
        else {
          DefaultMutableTreeNode dmtnNext = new DefaultMutableTreeNode();
          m_dmtnLastNode.add (dmtnNext);
          m_dmtnLastNode = dmtnNext;

          m_tpCompletePath = m_tpCompletePath.pathByAddingChild (m_dmtnLastNode);
        }

        m_dmtnLastNode.setUserObject (new ContainingLevel (ContainingLevel.ATTR_PACKAGE, sTopPackage));
        
        sPackage = sPackage.substring (nDotPos + 1);
      }
      
      if (m_dmtnLastNode == null) {
        m_dmtnLastNode = new DefaultMutableTreeNode ();
        m_dtmModel = new DefaultTreeModel (m_dmtnLastNode);
    
        m_tpCompletePath = new TreePath (m_dmtnLastNode);
      }
      else {
        DefaultMutableTreeNode dmtnNext = new DefaultMutableTreeNode();
        m_dmtnLastNode.add (dmtnNext);
        m_dmtnLastNode = dmtnNext;

        m_tpCompletePath = m_tpCompletePath.pathByAddingChild (m_dmtnLastNode);
      }

      m_dmtnLastNode.setUserObject (new ContainingLevel (ContainingLevel.ATTR_PACKAGE, sPackage));
    }
  }
  
  private void fillInClassTree (JavaClass jcParent) {
    if (jcParent == null) {
      return;
    }
    
    if (jcParent.getParent() == null) {
      fillInPackageTree (jcParent);
    }
    else {
      fillInClassTree (jcParent.getParent());
    }
    
    if (m_dmtnLastNode == null) {
      m_dmtnLastNode = new DefaultMutableTreeNode ();
      m_dtmModel = new DefaultTreeModel (m_dmtnLastNode);

      m_tpCompletePath = new TreePath (m_dmtnLastNode);
    }
    else {
      DefaultMutableTreeNode dmtnNext = new DefaultMutableTreeNode();
      m_dmtnLastNode.add (dmtnNext);
      m_dmtnLastNode = dmtnNext;

      m_tpCompletePath = m_tpCompletePath.pathByAddingChild (m_dmtnLastNode);
    }

    m_dmtnLastNode.setUserObject (new ContainingLevel (ContainingLevel.ATTR_CLASS, jcParent.getName()));
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the FormEditor.
   */
  private void initComponents () {//GEN-BEGIN:initComponents
    m_jspScroller = new javax.swing.JScrollPane ();
    m_jtContainingClassTree = new javax.swing.JTree ();
    setLayout (new java.awt.BorderLayout ());


      m_jtContainingClassTree.setModel (m_dtmModel);
      m_jtContainingClassTree.setCellRenderer (new ContainingLevelRenderer());
      // Specify lines to be drawn
      m_jtContainingClassTree.putClientProperty ("JTree.lineStyle", "Angled");
  
      m_jspScroller.setViewportView (m_jtContainingClassTree);
  

    add (m_jspScroller, java.awt.BorderLayout.CENTER);

  }//GEN-END:initComponents


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JScrollPane m_jspScroller;
  private javax.swing.JTree m_jtContainingClassTree;
  // End of variables declaration//GEN-END:variables

}