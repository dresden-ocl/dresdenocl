/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL Compiler                                                      *
 * Copyright (C) 2001 Steffen Zschaler (sz9@inf.tu-dresden.de).       *
 * All rights reserved.                                              *
 *                                                                   *
 * This work is free software; you can redistribute it and/or        *
 * modify it under the terms of the GNU Library General Public       *
 * License as published by the Free Software Foundation; either      *
 * version 2 of the License, or (at your option) any later version.  *
 *                                                                   *
 * This work is distributed in the hope that it will be useful,      *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU *
 * Library General Public License for more details.                  *
 *                                                                   *
 * You should have received a copy of the GNU Library General Public *
 * License along with this library; if not, write to the             *
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,      *
 * Boston, MA  02111-1307, USA.                                      *
 *                                                                   *
 * To submit a bug report, send a comment, or get the latest news on *
 * this project and other projects, please visit the web site:       *
 * http://www-st.inf.tu-dresden.de/ (Chair home page) or             *
 * http://www-st.inf.tu-dresden.de/ocl/ (project home page)          *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

// OCLEditor.java -- new version of the ocl editor intented for practical use
//
// 02/15/2001  [sz9 ]  Created.
//
package tudresden.ocl.gui;

import tudresden.ocl.gui.events.*;

/** 
  * An editor for a list of OCL Constraints. The editor allows editing
  * of a list of {@link ConstraintRepresentation constraint representations}
  * as specified by its {@link OCLEditorModel model}.
  *
  * @author  sz9
  */
public class OCLEditor extends javax.swing.JPanel
                        implements javax.swing.event.ListSelectionListener,
                                    ConstraintChangeListener {

  /**
    * Table model for the table of constraints.
    *
    * @author sz9
    */
  protected static class ConstraintTableModel extends javax.swing.table.AbstractTableModel
                                                 implements ConstraintChangeListener {
    /**
      * The OCLEditorModel on which this table model is based.
      */
    protected OCLEditorModel m_oclemModel;
    
    public ConstraintTableModel() {
      super();
    }
    
    /**
      * Set the OCLEditorModel to base this table model on.
      */
    public void setOCLModel (OCLEditorModel oclemModel) {
      if (m_oclemModel != null) {
        m_oclemModel.removeConstraintChangeListener (this);
      }
      
      m_oclemModel = oclemModel;
      
      if (m_oclemModel != null) {
        m_oclemModel.addConstraintChangeListener (this);
      }
      
      fireTableDataChanged();
    }
    
    /**
      * Return the number of rows in the table. This returns the number of
      * constraints in the underlying model.
      */
    public int getRowCount() {
      if (m_oclemModel != null) {
        return m_oclemModel.getConstraintCount();
      }
      else {
        return 0;
      }
    }
    
    /**
      * This table model has one column.
      */
    public int getColumnCount() {
      return 1;
    }

    /**
      * This table model has one column: &quot;Constraint name&quot;.
      */
    public String getColumnName (int nIdx) {
      return "Constraint Name";
    }
    
    /**
      * This table model has one column: &quot;String.class&quot;.
      */
    public Class getColumnClass (int nIdx) {
      return String.class;
    }
    
    /**
      * Get the constraint name of the specified constraint.
      */
    public Object getValueAt (int row, int column) {
      if (m_oclemModel != null) {
        ConstraintRepresentation cr = m_oclemModel.getConstraintAt (row);
        
        if (cr != null) {
          return cr.getName();
        }
        else {
          return null;
        }
      }
      else {
        return null;
      }
    }
    
    /**
      * Constraint names can be edited.
      */
    public boolean isCellEditable (int row, int column) {
      return column == 0;
    }
    
    /**
      * Set the edited constraint name.
      */
    public void setValueAt (int row, int column, Object value) {
      if (column == 0) {
        if (m_oclemModel != null) {
          ConstraintRepresentation cr = m_oclemModel.getConstraintAt (row);

          if (cr != null) {
            cr.setName (value.toString());
          }
        }
      }
    }
    
    // ConstraintChangeListener interface methods
    
    /**
      * Relay the event to the table indicating a row was added.
      */
    public void constraintAdded (ConstraintChangeEvent cce) {
      fireTableRowsInserted (cce.getIndex(), cce.getIndex());
    }
    
    /**
      * Relay the event to the table indicating a row was deleted.
      */
    public void constraintRemoved(ConstraintChangeEvent cce) {
      fireTableRowsDeleted (cce.getIndex(), cce.getIndex());
    }
    
    /**
      * Relay the event to the table indicating the data in the cell changed.
      */
    public void constraintNameChanged(ConstraintChangeEvent cce) {
      fireTableCellUpdated (cce.getIndex(), 0);
    }
    
    /**
      * Ignored.
      */
    public void constraintDataChanged(ConstraintChangeEvent cce) {
    }
  }
  
  /**
    * The underlying model.
    */
  private OCLEditorModel m_oclemModel;
  
  /**
    * The currently selected constraint, if any.
    */
  private ConstraintRepresentation m_crCurrent;
  
  /**
    * The table model used by the list of constraints table.
    */
  private ConstraintTableModel m_ctmTableModel = new ConstraintTableModel();
  
  /** Creates new form OCLEditor */
  public OCLEditor() {
    initComponents ();
    
    m_jspMainPane.setDividerLocation (50);
    
    m_jtConstraintList.getSelectionModel().addListSelectionListener (this);
  }

  /**
    * Retrieve the underlying model.
    */
  public OCLEditorModel getModel() {
    return m_oclemModel;
  }
  
  /**
    * Set the underlying model and update the display.
    */
  public void setModel (OCLEditorModel oclemModel) {
    if (m_oclemModel != null) {
      m_oclemModel.removeConstraintChangeListener (this);
    }
    
    m_oclemModel = oclemModel;
    
    m_ctmTableModel.setOCLModel (oclemModel);
    
    if (m_oclemModel != null) {
      m_oclemModel.addConstraintChangeListener (this);
    }
    
    m_jtConstraintList.clearSelection();
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the FormEditor.
   */
  private void initComponents () {//GEN-BEGIN:initComponents
    m_jspMainPane = new javax.swing.JSplitPane ();
    m_jpConstraintListPane = new javax.swing.JPanel ();
    m_jspConstraintListScroller = new javax.swing.JScrollPane ();
    m_jtConstraintList = new javax.swing.JTable ();
    m_jpConstraintListButtonsGroup = new javax.swing.JPanel ();
    m_jbAddConstraintButton = new javax.swing.JButton ();
    m_jbRemoveConstraintButton = new javax.swing.JButton ();
    m_jpConstraintEditorPane = new javax.swing.JPanel ();
    jPanel5 = new javax.swing.JPanel ();
    m_jspConstraintEditorScroller = new javax.swing.JScrollPane ();
    m_jtaConstraintEditor = new javax.swing.JTextArea ();
    m_jpEditorButtonsGroup = new javax.swing.JPanel ();
    m_jbSubmitConstraintButton = new javax.swing.JButton ();
    setLayout (new java.awt.BorderLayout ());
    setPreferredSize (new java.awt.Dimension(500, 300));
    setMinimumSize (new java.awt.Dimension(500, 300));

    m_jspMainPane.setOneTouchExpandable (true);

      m_jpConstraintListPane.setLayout (new java.awt.GridBagLayout ());
      java.awt.GridBagConstraints gridBagConstraints1;
  
    
          m_jtConstraintList.setModel (m_ctmTableModel);
      
          m_jspConstraintListScroller.setViewportView (m_jtConstraintList);
      
        gridBagConstraints1 = new java.awt.GridBagConstraints ();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets (10, 5, 5, 5);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        m_jpConstraintListPane.add (m_jspConstraintListScroller, gridBagConstraints1);
    
    
          m_jbAddConstraintButton.setText ("Add");
          m_jbAddConstraintButton.addActionListener (new java.awt.event.ActionListener () {
            public void actionPerformed (java.awt.event.ActionEvent evt) {
              onAddConstraintButton (evt);
            }
          }
          );
      
          m_jpConstraintListButtonsGroup.add (m_jbAddConstraintButton);
      
          m_jbRemoveConstraintButton.setText ("Remove");
          m_jbRemoveConstraintButton.addActionListener (new java.awt.event.ActionListener () {
            public void actionPerformed (java.awt.event.ActionEvent evt) {
              onRemoveConstraintButton (evt);
            }
          }
          );
      
          m_jpConstraintListButtonsGroup.add (m_jbRemoveConstraintButton);
      
        gridBagConstraints1 = new java.awt.GridBagConstraints ();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.gridheight = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets (0, 5, 20, 5);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints1.weightx = 1.0;
        m_jpConstraintListPane.add (m_jpConstraintListButtonsGroup, gridBagConstraints1);
    
      m_jspMainPane.setLeftComponent (m_jpConstraintListPane);
  
      m_jpConstraintEditorPane.setLayout (new java.awt.GridBagLayout ());
      java.awt.GridBagConstraints gridBagConstraints2;
  
        jPanel5.setLayout (new java.awt.GridBagLayout ());
        java.awt.GridBagConstraints gridBagConstraints3;
        jPanel5.setBorder (new javax.swing.border.TitledBorder("Edit selected constraint"));
    
      
        
            m_jspConstraintEditorScroller.setViewportView (m_jtaConstraintEditor);
        
          gridBagConstraints3 = new java.awt.GridBagConstraints ();
          gridBagConstraints3.gridwidth = 0;
          gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
          gridBagConstraints3.insets = new java.awt.Insets (5, 5, 5, 5);
          gridBagConstraints3.anchor = java.awt.GridBagConstraints.NORTHWEST;
          gridBagConstraints3.weightx = 1.0;
          gridBagConstraints3.weighty = 1.0;
          jPanel5.add (m_jspConstraintEditorScroller, gridBagConstraints3);
      
          m_jpEditorButtonsGroup.setLayout (new java.awt.FlowLayout (2, 5, 5));
      
            m_jbSubmitConstraintButton.setText ("Submit");
            m_jbSubmitConstraintButton.addActionListener (new java.awt.event.ActionListener () {
              public void actionPerformed (java.awt.event.ActionEvent evt) {
                onSubmitConstraintButton (evt);
              }
            }
            );
        
            m_jpEditorButtonsGroup.add (m_jbSubmitConstraintButton);
        
          gridBagConstraints3 = new java.awt.GridBagConstraints ();
          gridBagConstraints3.gridwidth = 0;
          gridBagConstraints3.gridheight = 0;
          gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
          gridBagConstraints3.insets = new java.awt.Insets (0, 5, 0, 0);
          gridBagConstraints3.anchor = java.awt.GridBagConstraints.NORTHWEST;
          gridBagConstraints3.weightx = 1.0;
          jPanel5.add (m_jpEditorButtonsGroup, gridBagConstraints3);
      
        gridBagConstraints2 = new java.awt.GridBagConstraints ();
        gridBagConstraints2.gridwidth = 0;
        gridBagConstraints2.gridheight = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets (0, 5, 20, 5);
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        m_jpConstraintEditorPane.add (jPanel5, gridBagConstraints2);
    
      m_jspMainPane.setRightComponent (m_jpConstraintEditorPane);
  

    add (m_jspMainPane, java.awt.BorderLayout.CENTER);

  }//GEN-END:initComponents

  /**
    * React to the submit button.
    */
  private void onSubmitConstraintButton (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onSubmitConstraintButton
    if (m_oclemModel != null) {
      int nIdx = m_jtConstraintList.getSelectedRow();

      if (nIdx != -1) {
        m_oclemModel.getConstraintAt (nIdx).setData (m_jtaConstraintEditor.getText());
      }
    }
  }//GEN-LAST:event_onSubmitConstraintButton

  /**
    * React to the remove button.
    */
  private void onRemoveConstraintButton (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onRemoveConstraintButton
    if (m_oclemModel != null) {
      int nIdx = m_jtConstraintList.getSelectedRow();

      if (nIdx != -1) {
        m_oclemModel.removeConstraintAt (nIdx);
      }
    }
  }//GEN-LAST:event_onRemoveConstraintButton

  /**
    * React to the add button.
    */
  private void onAddConstraintButton (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onAddConstraintButton
    if (m_oclemModel != null) {
      m_oclemModel.addConstraint();
    }
  }//GEN-LAST:event_onAddConstraintButton


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JSplitPane m_jspMainPane;
  private javax.swing.JPanel m_jpConstraintListPane;
  private javax.swing.JScrollPane m_jspConstraintListScroller;
  private javax.swing.JTable m_jtConstraintList;
  private javax.swing.JPanel m_jpConstraintListButtonsGroup;
  private javax.swing.JButton m_jbAddConstraintButton;
  private javax.swing.JButton m_jbRemoveConstraintButton;
  private javax.swing.JPanel m_jpConstraintEditorPane;
  private javax.swing.JPanel jPanel5;
  private javax.swing.JScrollPane m_jspConstraintEditorScroller;
  private javax.swing.JTextArea m_jtaConstraintEditor;
  private javax.swing.JPanel m_jpEditorButtonsGroup;
  private javax.swing.JButton m_jbSubmitConstraintButton;
  // End of variables declaration//GEN-END:variables

  // ListSelectionListener interface method
  
  /**
    * The selection in the table changed.
    */
  public void valueChanged(final javax.swing.event.ListSelectionEvent p1) {
    // Selected row changed in table.
    int newIndex = m_jtConstraintList.getSelectedRow();
    
    if (newIndex != -1) {
      if (m_oclemModel != null) {
        m_crCurrent = m_oclemModel.getConstraintAt (newIndex);
      }
      else {
        m_crCurrent = null;
      }
    }
    else {
      m_crCurrent = null;
    }
    
    if (m_crCurrent != null) {
      m_jtaConstraintEditor.setText (m_crCurrent.getData());
      m_jtaConstraintEditor.requestFocus();
    }
    else {
      m_jtaConstraintEditor.setText ("");
    }
  }
 
  // ConstraintChangeListener interface methods
  
  /**
    * Select the newly added constraint.
    */  
  public void constraintAdded(ConstraintChangeEvent cce) {
    m_jtConstraintList.setRowSelectionInterval (cce.getIndex(),
                                                   cce.getIndex());
  }
  
  /**
    * If the current constraint was deleted, clear the selection.
    */
  public void constraintRemoved(ConstraintChangeEvent cce) {
    if (cce.getIndex() == m_jtConstraintList.getSelectedRow()) {
      m_jtConstraintList.clearSelection();
    }
  }
  
  /**
    * Ignored.
    */
  public void constraintNameChanged(ConstraintChangeEvent cce) { }
  
  /**
    * Update the editor if the currently selected constraint changed.
    */
  public void constraintDataChanged(ConstraintChangeEvent cce) {
    if (cce.getIndex() == m_jtConstraintList.getSelectedRow()) {
      m_jtaConstraintEditor.setText (cce.getNew().getData());
    }
  }
  
  public static void main (String[] args) {
    javax.swing.JDialog jd = new javax.swing.JDialog (new javax.swing.JFrame(), true);
    OCLEditor ocle = new OCLEditor();
    
    jd.getContentPane().add (ocle);
 
    jd.pack();
    
    jd.setVisible (true);
    
    System.exit (0);
  }
}