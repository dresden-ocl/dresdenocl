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
 * TypeEditPage.java
 *
 * Created on 12. September 2000, 13:44
 */
 
package tudresden.ocl.injection.reverseeng;

import java.awt.Font;

import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import javax.swing.text.*;

/** 
  * A property page that allows editing of the element/key type of a feature.
  *
  * @author  sz9 (Steffen Zschaler)
  * @version 0.1
  */
public class TypeEditPage extends javax.swing.JPanel {

  public static Icon s_iType = new javax.swing.ImageIcon (TypeNodeRenderer.class.getResource ("resources/type.gif"));
  public static Icon s_iReason = new javax.swing.ImageIcon (TypeNodeRenderer.class.getResource ("resources/reason.gif"));
    
  public static interface TreeElement {
    public Icon getIcon(boolean fExpanded);
    public String getToolTip();
    public boolean useBold();
  }
  
  public static class ProposalReason implements TreeElement {
    String m_sReason;
    
    public ProposalReason (String sReason) {
      super();
      
      m_sReason = sReason;
    }
    
    public String toString() {
      return m_sReason;
    }
    
    public Icon getIcon(boolean fExpanded) {
      return s_iReason;
    }
    
    public String getToolTip() {
      return "Proposal Reason.";
    }
    
    public boolean useBold() {
      return false;
    }
  }
  
  public static class ProposedType implements TreeElement {
    
    private String m_sTypeName;
    private List m_lprReasons = new LinkedList();
    
    public ProposedType (String sTypeName, String[] saReasons) {
      super();
      
      m_sTypeName = sTypeName;
      
      if (saReasons != null) {
        for (int i = 0; i < saReasons.length; i++) {
          m_lprReasons.add (new ProposalReason (saReasons[i]));
        }
      }
    }
    
    public Iterator getReasons() {
      return m_lprReasons.iterator();
    }
    
    public void addReason (String sReason) {
      addReason (new ProposalReason (sReason));
    }
    
    public void addReason (ProposalReason pr) {
      m_lprReasons.add (pr);
    }
    
    public void addAllReasons (ProposedType pt) {
      for (Iterator i = pt.getReasons(); i.hasNext();) {
        addReason ((ProposalReason) i.next());
      }
    }
    
    public boolean equals (Object o) {
      return ((o instanceof ProposedType) &&
               (((ProposedType) o).m_sTypeName.equals (this.m_sTypeName)));
    }
    
    public String toString() {
      return m_sTypeName;
    }
    
    public Icon getIcon(boolean fExpanded) {
      return s_iType;
    }
    
    public String getToolTip() {
      return "Proposed Type.";
    }
    
    public boolean useBold() {
      return false;
    }
  }
  
  public static interface TypeDescriptor {
    public void onTypeSelectionChanged (String sNewSelection);
    public List getProposedTypes();
    public String getCurrentType();
  }
  
  static class TypeNodeRenderer extends DefaultTreeCellRenderer {    
    public TypeNodeRenderer () {
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
      
      DefaultMutableTreeNode dmtnValue = (DefaultMutableTreeNode) value;
      
      if (dmtnValue.getUserObject() instanceof TreeElement) {
        TreeElement teData = (TreeElement) dmtnValue.getUserObject();

        if (teData != null) {
          setIcon (teData.getIcon (expanded));
          setToolTipText (teData.getToolTip());

          if (getFont() != null) {
            int nStyle = getFont().getStyle();

            if (teData.useBold()) {
              nStyle |= Font.BOLD;
            }
            else {
              nStyle &= ~Font.BOLD;
            }

            setFont (getFont().deriveFont (nStyle));
          }
        }
      }

      return this;
    }
  }  
  
  private TypeDescriptor m_tdType;
  private AbstractDescriptor m_adModel;
  private DefaultTreeModel m_dtmProposals = new DefaultTreeModel (new DefaultMutableTreeNode ("Root"));
  
  /** Creates new form TypeEditPage */
  public TypeEditPage (AbstractDescriptor ad, TypeDescriptor td) {
    super();
    
    m_adModel = ad;
    
    m_tdType = td;
    
    fillInProposedTypes();
    
    initComponents ();
    
    fillInCurrentSelection();
    
    // Setup text change listener
    final Position pStart = m_jtfSelection.getDocument().getStartPosition();
    final Position pEnd = m_jtfSelection.getDocument().getEndPosition();
    
    m_jtfSelection.getDocument().addDocumentListener (new DocumentListener() {
      public void insertUpdate (DocumentEvent e) {
        notify (e.getDocument());
      }
      public void removeUpdate (DocumentEvent e) {
        notify (e.getDocument());
      }
      public void changedUpdate (DocumentEvent e) {
        notify (e.getDocument());
      }
      
      private void notify (Document d) {
        try {
          m_tdType.onTypeSelectionChanged (d.getText (pStart.getOffset(), pEnd.getOffset() - 1));
        }
        catch (BadLocationException ble) {}        
      }
    });
  }

  private void fillInCurrentSelection() {
    m_jtfSelection.setText (m_tdType.getCurrentType());
  }
  
  private static List s_lptDefaultTypes;
  static {
    final String[] asDefaultTypes = {"java.lang.Boolean",
                                      "java.lang.Byte",
                                      "java.lang.Character",
                                      "java.lang.Double",
                                      "java.lang.Float",
                                      "java.lang.Integer",
                                      "java.lang.Long",
                                      "java.lang.Number",
                                      "java.lang.Object",
                                      "java.lang.Short",
                                      "java.lang.String",
                                      "java.lang.StringBuffer"};
    
    s_lptDefaultTypes = new ArrayList();
    
    for (int i = 0; i < asDefaultTypes.length; i++) {
      s_lptDefaultTypes.add (new ProposedType (asDefaultTypes[i], new String[] {"Standard Type."}));
    }
  }
  
  private void fillInProposedTypes() {
    List lProposals = m_tdType.getProposedTypes();
    for (Iterator i = lProposals.iterator(); i.hasNext();) {
      // Add proposed type.
      ProposedType pt = (ProposedType) i.next();
      
      int nIdx = s_lptDefaultTypes.indexOf (pt);
      if (nIdx > -1) {
        pt.addAllReasons ((ProposedType) s_lptDefaultTypes.get (nIdx));
      }
      
      DefaultMutableTreeNode dmtnProposal = new DefaultMutableTreeNode (pt);
      ((DefaultMutableTreeNode) m_dtmProposals.getRoot()).add (dmtnProposal);
      
      for (Iterator j = pt.getReasons(); j.hasNext();) {
        dmtnProposal.add (new DefaultMutableTreeNode ((ProposalReason) j.next()));
      }
    }
    
    for (Iterator i = s_lptDefaultTypes.iterator(); i.hasNext();) {
      ProposedType pt = (ProposedType) i.next();
      
      if (! lProposals.contains (pt)) {
        DefaultMutableTreeNode dmtnProposal = new DefaultMutableTreeNode (pt);
        ((DefaultMutableTreeNode) m_dtmProposals.getRoot()).add (dmtnProposal);

        for (Iterator j = pt.getReasons(); j.hasNext();) {
          dmtnProposal.add (new DefaultMutableTreeNode ((ProposalReason) j.next()));
        }
      }
    }
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the FormEditor.
   */
  private void initComponents () {//GEN-BEGIN:initComponents
    m_jpDocCommentBorder = new javax.swing.JPanel ();
    m_dcvDocComment = new tudresden.ocl.injection.reverseeng.DocCommentViewer ();
    m_dcvDocComment.setModel (m_adModel);
    m_jtfSelection = new javax.swing.JTextField ();
    m_jpProposalsBorder = new javax.swing.JPanel ();
    m_jspProposals = new javax.swing.JScrollPane ();
    m_jtProposals = new javax.swing.JTree ();
    setLayout (new java.awt.GridBagLayout ());
    java.awt.GridBagConstraints gridBagConstraints1;

    m_jpDocCommentBorder.setLayout (new java.awt.GridBagLayout ());
    java.awt.GridBagConstraints gridBagConstraints2;
    m_jpDocCommentBorder.setBorder (new javax.swing.border.TitledBorder("Documentation Comment"));

      java.awt.GridBagConstraints gridBagConstraints3;
  
      gridBagConstraints2 = new java.awt.GridBagConstraints ();
      gridBagConstraints2.gridwidth = 0;
      gridBagConstraints2.gridheight = 0;
      gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints2.anchor = java.awt.GridBagConstraints.NORTHWEST;
      gridBagConstraints2.weightx = 1.0;
      gridBagConstraints2.weighty = 1.0;
      m_jpDocCommentBorder.add (m_dcvDocComment, gridBagConstraints2);
  

    gridBagConstraints1 = new java.awt.GridBagConstraints ();
    gridBagConstraints1.gridwidth = 0;
    gridBagConstraints1.gridheight = 2;
    gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints1.weightx = 1.0;
    gridBagConstraints1.weighty = 2.0;
    add (m_jpDocCommentBorder, gridBagConstraints1);



    gridBagConstraints1 = new java.awt.GridBagConstraints ();
    gridBagConstraints1.gridwidth = 0;
    gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints1.weightx = 1.0;
    add (m_jtfSelection, gridBagConstraints1);

    m_jpProposalsBorder.setLayout (new java.awt.GridBagLayout ());
    java.awt.GridBagConstraints gridBagConstraints4;
    m_jpProposalsBorder.setBorder (new javax.swing.border.TitledBorder("Proposals"));

  
        m_jtProposals.setRootVisible (false);
        m_jtProposals.setModel (m_dtmProposals);
        m_jtProposals.setShowsRootHandles (true);
        m_jtProposals.setCellRenderer (new TypeNodeRenderer());
    
        // Enable tool tips.
        ToolTipManager.sharedInstance().registerComponent (m_jtProposals);
    
        // Set to display lines between nodes
        m_jtProposals.putClientProperty ("JTree.lineStyle", "Angled");
        m_jtProposals.addTreeSelectionListener (new javax.swing.event.TreeSelectionListener () {
          public void valueChanged (javax.swing.event.TreeSelectionEvent evt) {
            m_jtProposalsValueChanged (evt);
          }
        }
        );
    
        m_jspProposals.setViewportView (m_jtProposals);
    
      gridBagConstraints4 = new java.awt.GridBagConstraints ();
      gridBagConstraints4.gridwidth = 0;
      gridBagConstraints4.gridheight = 0;
      gridBagConstraints4.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints4.anchor = java.awt.GridBagConstraints.NORTHWEST;
      gridBagConstraints4.weightx = 1.0;
      gridBagConstraints4.weighty = 1.0;
      m_jpProposalsBorder.add (m_jspProposals, gridBagConstraints4);
  

    gridBagConstraints1 = new java.awt.GridBagConstraints ();
    gridBagConstraints1.gridwidth = 0;
    gridBagConstraints1.gridheight = 0;
    gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints1.weightx = 1.0;
    gridBagConstraints1.weighty = 3.0;
    add (m_jpProposalsBorder, gridBagConstraints1);

  }//GEN-END:initComponents

  private void m_jtProposalsValueChanged (javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_m_jtProposalsValueChanged
    if (evt.isAddedPath()) {
      m_jtfSelection.setText (((DefaultMutableTreeNode) evt.getPath().getPathComponent (1)).getUserObject().toString());
    }
  }//GEN-LAST:event_m_jtProposalsValueChanged


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel m_jpDocCommentBorder;
  private tudresden.ocl.injection.reverseeng.DocCommentViewer m_dcvDocComment;
  private javax.swing.JTextField m_jtfSelection;
  private javax.swing.JPanel m_jpProposalsBorder;
  private javax.swing.JScrollPane m_jspProposals;
  private javax.swing.JTree m_jtProposals;
  // End of variables declaration//GEN-END:variables

}