/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL Editor                                                        *
 * Copyright (C) 2001 Steffen Zschaler.                              *
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
 * See CREDITS file for further details.                             *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

// OCLEditor.java -- new version of the ocl editor intented for practical use
//
// 03/12/2001  [sz9 ]  Added handling of line and column numbers in parser error
//                     messages.
// 02/23/2001  [sz9 ]  Added helper function to syntax check constraints.
// 02/15/2001  [sz9 ]  Created.
//
package tudresden.ocl.gui;

import tudresden.ocl.*;
import tudresden.ocl.gui.events.*;
import tudresden.ocl.parser.*;
import tudresden.ocl.parser.analysis.*;
import tudresden.ocl.parser.node.*;
import tudresden.ocl.check.*;
import tudresden.ocl.check.types.ModelFacade;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;

import java.util.*;

/** 
  * An editor for a list of OCL Constraints. The editor allows editing
  * of a list of {@link ConstraintRepresentation constraint representations}
  * as specified by its {@link OCLEditorModel model}.
  *
  * @author  sz9
  */
public class OCLEditor extends javax.swing.JPanel
                        implements javax.swing.event.ListSelectionListener,
                                   ConstraintChangeListener,
                                   EditingUtilities {
  /**
    * Attributes used to denote fields that need to be replaced.
    */
  private static SimpleAttributeSet s_sasField
      = new SimpleAttributeSet();
  /**
    * Attributes used to denote normal OCL constraint text.
    */
  private static SimpleAttributeSet s_sasNormalText
      = new SimpleAttributeSet();
  static {
    if (StyleConstants.Alignment != null) {
      // Just a dummy to properly initialize StyleConstants...
    }
    
    s_sasField.addAttribute (
        StyleConstants.CharacterConstants.Underline,
        Boolean.TRUE);
    s_sasField.addAttribute (
        "isField",
        Boolean.TRUE);
  }

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
    
    /**
     * The OCLEditor for which this table model is used.
     */
    protected java.lang.ref.WeakReference m_wrocle;
    
    public ConstraintTableModel (OCLEditor ocle) {
      super();
      
      m_wrocle = new java.lang.ref.WeakReference (ocle);
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
    public void setValueAt (Object value, int row, int column) {
      if (column == 0) {
        if (m_oclemModel != null) {
          ConstraintRepresentation cr = m_oclemModel.getConstraintAt (row);

          if (cr != null) {
            try {
              cr.setName (value.toString(), (OCLEditor) m_wrocle.get());
            }
            catch (IllegalArgumentException iae) {
              JOptionPane.showMessageDialog (null,
                                               "Invalid name: " +
                                                   iae.getMessage(),
                                               "Error",
                                               JOptionPane.ERROR_MESSAGE);
            }
            catch (IllegalStateException ise) {
              JOptionPane.showMessageDialog (null,
                                               "Couldn't set name: " +
                                                   ise.getMessage(),
                                               "Error",
                                               JOptionPane.ERROR_MESSAGE);
            }
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
  private ConstraintTableModel m_ctmTableModel = new ConstraintTableModel (this);

  /**
   * Does {@link #parseAndCheckConstraint} perform type checking?
   */
  private boolean m_fDoTypeCheck = true;
  
  /**
   * Should multi-constraint inputs be automatically split into individual
   * constraints?
   */
  private boolean m_fDoAutoSplit = true;

  public static final int OPTIONMASK_TYPECHECK = 1;
  public static final int OPTIONMASK_AUTOSPLIT = 2;

  /**
    * The options supported by this instance of the editor.
    */
  private int m_nOptionMask = OPTIONMASK_TYPECHECK | OPTIONMASK_AUTOSPLIT;

  /**
   * If true, we're in edit mode.
   */
  private boolean m_fInEditMode = false;
  
  /** Creates new form OCLEditor */
  public OCLEditor() {
    initComponents ();
    
    /*
     * Remove editor panel, which was added only to be able to manipulate it
     * from the IDE.
     */
    m_jpToolbarWrapper.remove (m_jpEditorPanel);
    
    m_jtConstraintList.getSelectionModel()
        .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    m_jtConstraintList.getSelectionModel()
        .addListSelectionListener (this);
    
    m_ocltbQuickBar.setVisible (false);
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
    setEditMode (false);
    
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

  /**
   * Return true if the OCL editor is currently in edit mode.
   */
  public boolean isInEditMode() {
    return m_fInEditMode;
  }
  
  /**
   * Set the edit mode of the OCL editor. Setting the edit mode to false cancels
   * any current edit.
   */
  public void setEditMode (boolean fEditMode) {
    if (fEditMode == m_fInEditMode) {
      return;
    }
    
    // From here it's basically a toggling of edit mode
    if (! m_fInEditMode) {
      // Only switch to edit mode if current constraint exists
      if (m_crCurrent == null) {
        return;
      }
      
      m_fInEditMode = true;
      
      m_jpToolbarWrapper.remove (m_jspMainPane);
      m_jpToolbarWrapper.add (m_jpEditorPanel, java.awt.BorderLayout.CENTER);

      m_jbNew.setEnabled (false);
      m_jbRemove.setEnabled (false);

      m_jbEdit.setIcon (new javax.swing.ImageIcon (getClass ().getResource ("/tudresden/ocl/images/Cancel16.gif")));
      m_jbEdit.setToolTipText ("Click to cancel editing without saving changes");
      //m_jbEdit.setText ("Stop Edit");

      m_jbSaveEditResult.setEnabled (true);
      
      m_jtpConstraintEditor.requestFocus();
      
      m_ocltbQuickBar.setVisible (m_jcbQuickBar.isSelected());
      m_jcbQuickBar.setEnabled (true);
    }
    else {
      m_fInEditMode = false;
      
      m_jpToolbarWrapper.remove (m_jpEditorPanel);
      m_jpToolbarWrapper.add (m_jspMainPane, java.awt.BorderLayout.CENTER);

      // Reset editor panel contents
      m_jtpConstraintEditor.setText (m_crCurrent.getData());
      
      m_jbNew.setEnabled (true);
      m_jbRemove.setEnabled (true);

      m_jbEdit.setIcon (new javax.swing.ImageIcon (getClass ().getResource ("/tudresden/ocl/images/Edit16.gif")));
      m_jbEdit.setToolTipText ("Click to edit the currently selected constraint");
      //m_jbEdit.setText ("Edit");

      m_jbSaveEditResult.setEnabled (false);

      m_ocltbQuickBar.setVisible (false);
      m_jcbQuickBar.setEnabled (false);
    }
    
    m_jpToolbarWrapper.revalidate();
    m_jpToolbarWrapper.repaint();
  }
    
  /**
   * Checks the specified name and returns true if it is a valid OCL name.
   */
  public boolean isValidConstraintName (String sName) {
    if ((sName == null) ||
         (sName.length() == 0)) {
      return false;
    }
    if ((! Character.isLetter (sName.charAt (0))) ||
         (! Character.isLowerCase (sName.charAt (0)))) {
      return false;
    }
    for (int i = 1; i < sName.length(); i++) {
      if ((! Character.isLetterOrDigit (sName.charAt (i))) &&
          (sName.charAt (i) != '_')) {
        return false;
      }
    }
    
    return true;
  }
  
  /**
   * Specify which user option check boxes should be displayed.
   */
  public void setOptionMask (int nOptionMask) {
    m_nOptionMask = nOptionMask;
    
    if (m_nOptionMask == 0) {
      return;
    }
  }

  public int getOptionMask() {
    return m_nOptionMask;
  }
  
  /**
    * Set the type checking mode.
    */
  public void setDoTypeCheck (boolean fDoTypeCheck) {
    m_fDoTypeCheck = fDoTypeCheck;
  }
  
  /**
   * Return whether type checking mode is on. If this returns true,
   * {@link #parseAndCheckConstraint} will also check type conformance of
   * constraints.
   */
  public boolean getDoTypeCheck() {
    return m_fDoTypeCheck;
  }
  
  /**
   * Check the specified constraint using the specified model facade for model
   * information. Return the parse tree for the constraint.
   * 
   * <p>This is a short-cut helper function for editor models that want to parse
   * constraints before adding them to the model base.</p>
   * 
   * @exception OclParserException if a syntax error occurred.
   * @exception IOException if an I/O operation failed.
   * @exception OclTypeException if a type checking error occurred.
   */
  public OclTree parseAndCheckConstraint (final String sConstraint,
                                              final ModelFacade mfFacade)
    throws OclParserException,
            java.io.IOException,
            OclTypeException {
    // Parse and syntax check
    final OclTree tree = OclTree.createTree (sConstraint,
                                               mfFacade);
    
    // Type check
    if ((tree != null) &&
         (m_fDoTypeCheck)) {
       DepthFirstAdapter dfaTypeChecker = new DepthFirstAdapter() {
        private RuntimeException m_rteException = null;
        
        public void inStart (Start node) {
          defaultIn(node);
        }

        public void outStart(Start s) {
          if (m_rteException != null) {
            throw m_rteException;
          }
        }
        
        public void defaultIn(Node node) {
          try {
            Object o = tree.getNodeType(node);
          }
          catch (RuntimeException e) {
            if (m_rteException == null) {
              m_rteException = e;
            }
          }
        }
      };

      try {
        tree.apply (dfaTypeChecker);

        try {
          tree.applyGeneratedTests();
        }
        catch (java.security.AccessControlException ace) {
          // SecurityManager denies access to non-public fields
          // Ignore and assume all is correct
        }
      }
      catch (Exception e) {
        throw new OclTypeException (e.getMessage());
      }
    }

    return tree;
  }

  /**
    * Set the auto split mode.
    */
  public void setDoAutoSplit (boolean fDoAutoSplit) {
    m_fDoAutoSplit = fDoAutoSplit;
  }
  
  /**
   * Return whether auto split mode is on. If this returns true, constraint
   * representations should call {@link #splitConstraint} and create one
   * constraint representation per actual constraint.
   */
  public boolean getDoAutoSplit() {
    return m_fDoAutoSplit;
  }
  
  /**
   * Split the specified constraint into its constituting constraints. E.g.
   * <pre>
   * context Test
   * inv: a > 0
   * inv: a < 10
   * inv: a * 100 = 900
   * </pre>
   * would be split into three constraints:
   * <pre>
   * context Test
   * inv: a > 0
   *
   * context Test
   * inv: a < 10
   *
   * context Test
   * inv: a * 100 = 900
   * </pre>
   */
  public List splitConstraint (OclTree ocltConstraint) {
    final List lResult = new LinkedList();
    
    class Splitter extends DepthFirstAdapter {
      public void caseAConstraint(AConstraint node) {
        PContextDeclaration pcd = node.getContextDeclaration();
        
        for (Iterator i = node.getConstraintBody().iterator(); i.hasNext();) {
          PConstraintBody pcbCurrent = (PConstraintBody) i.next();
          
          List lTempBody = new LinkedList();
          lTempBody.add (pcbCurrent.clone());

          lResult.add (new OclTree (
              new Start (
                new AConstraint ((PContextDeclaration) pcd.clone(),
                                   lTempBody),
                new EOF()
              )
          ));
        }
      }
    }
    
    ocltConstraint.apply (new Splitter());
    
    return lResult;
  }

  /**
   * Add the given text to the edit pane, if a constraint is currently being
   * edited. The text will replace the current selection. If saBefore/saAfter
   * are not <code>null</code> and contain elements, these will be added as
   * items to be replaced. The first such item will be selected.
   */
  void addConstraintText (String[] saBefore,
                             String sText,
                             String[] saAfter) {
    if ((m_crCurrent != null) && isInEditMode()) {
      int nSelStart = -1;
      int nSelEnd = -1;
      
      this.m_fHandleCaretUpdates = false;
      
      m_jtpConstraintEditor.replaceSelection ("");
      
      if (saBefore != null) {
        m_jtpConstraintEditor.setCharacterAttributes (s_sasField, true);
        for (int i = 0; i < saBefore.length; i++) {
          if (i == 0) {
            nSelStart = m_jtpConstraintEditor.getSelectionStart();
            nSelEnd = nSelStart + saBefore[i].length();
          }
          
          m_jtpConstraintEditor.replaceSelection (saBefore[i]);
        }
        m_jtpConstraintEditor.setCharacterAttributes (s_sasNormalText, true);
      }
      
      m_jtpConstraintEditor.replaceSelection (sText);
      
      if (saAfter != null) {
        m_jtpConstraintEditor.setCharacterAttributes (s_sasField, true);
        for (int i = 0; i < saAfter.length; i++) {
          if ((i == 0) &&
               (nSelStart == -1)) {
            nSelStart = m_jtpConstraintEditor.getSelectionStart();
            nSelEnd = nSelStart + saAfter[i].length();
          }
          
          m_jtpConstraintEditor.replaceSelection (saAfter[i]);
        }
        m_jtpConstraintEditor.setCharacterAttributes (s_sasNormalText, true);
      }
      
      if (nSelStart != -1) {
        m_jtpConstraintEditor.paintImmediately (
            0, 0,
            m_jtpConstraintEditor.getWidth(),
            m_jtpConstraintEditor.getHeight());
        m_jtpConstraintEditor.setSelectionStart (nSelStart);
        m_jtpConstraintEditor.setSelectionEnd (nSelEnd);
      }
      this.m_fHandleCaretUpdates = true;
      
/*      java.awt.Frame fTop = (java.awt.Frame) getTopLevelAncestor();
      fTop.toFront();
      fTop.requestFocus();
      m_jtpConstraintEditor.requestFocus();*/
    }
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the FormEditor.
   */
  private void initComponents() {//GEN-BEGIN:initComponents
    m_jtbTools = new javax.swing.JToolBar();
    m_jbNew = new javax.swing.JButton();
    m_jbRemove = new javax.swing.JButton();
    m_jbEdit = new javax.swing.JButton();
    m_jbSaveEditResult = new javax.swing.JButton();
    pad1 = new javax.swing.JPanel();
    m_jbPreferences = new javax.swing.JButton();
    pad2 = new javax.swing.JPanel();
    m_jcbQuickBar = new javax.swing.JCheckBox();
    m_jpToolbarWrapper = new javax.swing.JPanel();
    m_ocltbQuickBar = new tudresden.ocl.gui.OCLToolbar();
    m_jpEditorPanel = new javax.swing.JPanel();
    m_jspConstraintEditorScroller = new javax.swing.JScrollPane();
    m_jtpConstraintEditor = new javax.swing.JTextPane();
    m_jspMainPane = new javax.swing.JSplitPane();
    m_jpConstraintListPane = new javax.swing.JPanel();
    m_jspConstraintListScroller = new javax.swing.JScrollPane();
    m_jtConstraintList = new javax.swing.JTable();
    m_jpPreviewPane = new javax.swing.JPanel();
    m_jpPreviewGroup = new javax.swing.JPanel();
    m_jspConstraintPreviewScroller = new javax.swing.JScrollPane();
    m_jtpConstraintPreview = new javax.swing.JTextPane();
    setLayout(new java.awt.BorderLayout());
    setPreferredSize(new java.awt.Dimension(500, 300));
    setMinimumSize(new java.awt.Dimension(500, 300));
    
    
    m_jbNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tudresden/ocl/images/New16.gif")));
      m_jbNew.setToolTipText("Click to create a new constraint");
      m_jbNew.setMargin(new java.awt.Insets(0, 0, 0, 0));
      m_jbNew.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          onNewConstraintButton(evt);
        }
      }
      );
      m_jtbTools.add(m_jbNew);
      
      
    m_jbRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tudresden/ocl/images/Delete16.gif")));
      m_jbRemove.setToolTipText("Click to remove the currently selected constraint");
      m_jbRemove.setMargin(new java.awt.Insets(0, 0, 0, 0));
      m_jbRemove.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          onRemoveConstraintButton(evt);
        }
      }
      );
      m_jtbTools.add(m_jbRemove);
      
      
    m_jbEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tudresden/ocl/images/Edit16.gif")));
      m_jbEdit.setToolTipText("Click to edit the currently selected constraint");
      m_jbEdit.setMargin(new java.awt.Insets(0, 0, 0, 0));
      m_jbEdit.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          onEditButton(evt);
        }
      }
      );
      m_jtbTools.add(m_jbEdit);
      
      
    m_jbSaveEditResult.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tudresden/ocl/images/Ok16.gif")));
      m_jbSaveEditResult.setToolTipText("Check OCL syntax and save constraint into model");
      m_jbSaveEditResult.setMargin(new java.awt.Insets(0, 0, 0, 0));
      m_jbSaveEditResult.setEnabled(false);
      m_jbSaveEditResult.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          onSubmitConstraintButton(evt);
        }
      }
      );
      m_jtbTools.add(m_jbSaveEditResult);
      
      
    pad1.setMaximumSize(new java.awt.Dimension(10, 10));
      m_jtbTools.add(pad1);
      
      
    m_jbPreferences.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tudresden/ocl/images/Preferences16.gif")));
      m_jbPreferences.setToolTipText("Click to inspect and modify OCL editor preferences.");
      m_jbPreferences.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          onPreferencesButton(evt);
        }
      }
      );
      m_jtbTools.add(m_jbPreferences);
      
      
    pad2.setPreferredSize(new java.awt.Dimension(5, 10));
      pad2.setMinimumSize(new java.awt.Dimension(5, 10));
      pad2.setMaximumSize(new java.awt.Dimension(5, 10));
      m_jtbTools.add(pad2);
      
      
    m_jcbQuickBar.setToolTipText("Check to see the syntax assistant toolbar");
      m_jcbQuickBar.setText("Syntax Assistant");
      m_jcbQuickBar.setEnabled(false);
      m_jcbQuickBar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          onQuickBarButton(evt);
        }
      }
      );
      m_jtbTools.add(m_jcbQuickBar);
      
      
    add(m_jtbTools, java.awt.BorderLayout.NORTH);
    
    
    m_jpToolbarWrapper.setLayout(new java.awt.BorderLayout());
    
    m_ocltbQuickBar.setEditor(this);
      m_jpToolbarWrapper.add(m_ocltbQuickBar, java.awt.BorderLayout.NORTH);
      
      
    m_jpEditorPanel.setLayout(new java.awt.GridBagLayout());
      java.awt.GridBagConstraints gridBagConstraints1;
      m_jpEditorPanel.setBorder(new javax.swing.border.TitledBorder("Edit constraint"));
      
      
        m_jtpConstraintEditor.setToolTipText("Edit the constraint expression");
          m_jtpConstraintEditor.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
              onCaretUpdate(evt);
            }
          }
          );
          m_jspConstraintEditorScroller.setViewportView(m_jtpConstraintEditor);
          
          gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.gridheight = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(5, 5, 10, 5);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        m_jpEditorPanel.add(m_jspConstraintEditorScroller, gridBagConstraints1);
        
        m_jpToolbarWrapper.add(m_jpEditorPanel, java.awt.BorderLayout.CENTER);
      
      
    m_jspMainPane.setOneTouchExpandable(true);
      
      m_jpConstraintListPane.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        m_jpConstraintListPane.setPreferredSize(new java.awt.Dimension(100, 37));
        
        
          m_jtConstraintList.setToolTipText("Lists all constraints. Selecting a constraint, shows its body in the right hand preview pane. Clicking twice on a constraint allows editing the constraint's name.");
            m_jtConstraintList.setModel(m_ctmTableModel);
            m_jspConstraintListScroller.setViewportView(m_jtConstraintList);
            
            gridBagConstraints2 = new java.awt.GridBagConstraints();
          gridBagConstraints2.gridwidth = 0;
          gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
          gridBagConstraints2.insets = new java.awt.Insets(10, 5, 5, 5);
          gridBagConstraints2.anchor = java.awt.GridBagConstraints.NORTHWEST;
          gridBagConstraints2.weightx = 1.0;
          gridBagConstraints2.weighty = 1.0;
          m_jpConstraintListPane.add(m_jspConstraintListScroller, gridBagConstraints2);
          
          m_jspMainPane.setLeftComponent(m_jpConstraintListPane);
        
        
      m_jpPreviewPane.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;
        m_jpPreviewPane.setPreferredSize(new java.awt.Dimension(100, 80));
        
        m_jpPreviewGroup.setLayout(new java.awt.GridBagLayout());
          java.awt.GridBagConstraints gridBagConstraints4;
          m_jpPreviewGroup.setBorder(new javax.swing.border.TitledBorder("Preview"));
          
          
            m_jtpConstraintPreview.setToolTipText("Constraint expression preview");
              m_jtpConstraintPreview.setEditable(false);
              m_jspConstraintPreviewScroller.setViewportView(m_jtpConstraintPreview);
              
              gridBagConstraints4 = new java.awt.GridBagConstraints();
            gridBagConstraints4.gridwidth = 0;
            gridBagConstraints4.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints4.insets = new java.awt.Insets(5, 5, 5, 5);
            gridBagConstraints4.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints4.weightx = 1.0;
            gridBagConstraints4.weighty = 1.0;
            m_jpPreviewGroup.add(m_jspConstraintPreviewScroller, gridBagConstraints4);
            
            gridBagConstraints3 = new java.awt.GridBagConstraints();
          gridBagConstraints3.gridwidth = 0;
          gridBagConstraints3.gridheight = 0;
          gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
          gridBagConstraints3.insets = new java.awt.Insets(0, 5, 20, 5);
          gridBagConstraints3.weightx = 1.0;
          gridBagConstraints3.weighty = 1.0;
          m_jpPreviewPane.add(m_jpPreviewGroup, gridBagConstraints3);
          
          m_jspMainPane.setRightComponent(m_jpPreviewPane);
        
        m_jpToolbarWrapper.add(m_jspMainPane, java.awt.BorderLayout.CENTER);
      
      
    add(m_jpToolbarWrapper, java.awt.BorderLayout.CENTER);
    
  }//GEN-END:initComponents
  
  private void onEditButton (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onEditButton
    // Toggle edit mode
    setEditMode (! isInEditMode());
  }//GEN-LAST:event_onEditButton

  private void onNewConstraintButton (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onNewConstraintButton
    if (m_oclemModel != null) {
      int nOldCount = m_oclemModel.getConstraintCount();
      m_oclemModel.addConstraint();
      
      if (m_oclemModel.getConstraintCount() > nOldCount) {
        // New constraint was added, it should now also be selected because of
        // our reaction to the constraintAdded event.
        setEditMode (true);
      }
    }
  }//GEN-LAST:event_onNewConstraintButton

  private void onPreferencesButton (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onPreferencesButton
    new OCLEditorPreferences (new JFrame(), this).setVisible (true);
  }//GEN-LAST:event_onPreferencesButton

  private boolean m_fHandleCaretUpdates = true;
  
  private int m_nOldDot = 0;
  private int m_nOldMark = 0;
  
  private void onCaretUpdate (javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_onCaretUpdate
    if (! m_fHandleCaretUpdates) {
      return;
    }
    
    final CaretEvent e = evt;
    SwingUtilities.invokeLater(new Runnable () {
      public void run() {
        // Get affected element
        Element eAffected = m_jtpConstraintEditor.
            getDocument().getDefaultRootElement();
        while (! eAffected.isLeaf()) {
          eAffected = eAffected.getElement (
              eAffected.getElementIndex (e.getDot()));
        }

        if (eAffected.getAttributes().getAttribute("isField") == Boolean.TRUE) {
          // enhance selection to end of field
          int nFieldStart = eAffected.getStartOffset();
          int nFieldEnd = eAffected.getEndOffset();

          int nMark = e.getMark();
          int nDot = e.getDot();
          
          int nSelStart = 0;
          int nSelEnd = 0;
          
          if (nMark == nDot) {
            // Cursor set
            if (m_nOldDot > nDot) {
              // coming in from the right
              nSelStart = nFieldEnd;
              nSelEnd = nFieldStart;
            }
            else {
              // coming in from the left
              nSelStart = nFieldStart;
              nSelEnd = nFieldEnd;
            }
          }
          else if (nMark < nDot) {
            // Selection spanning toward the right
            if (m_nOldDot > nDot) {
              // making selection smaller...
              nSelStart = Math.min (nMark, nFieldStart);
              nSelEnd = Math.min (nDot, nFieldStart);
            }
            else {
              if (nMark > nFieldStart) {
                nSelStart = nFieldStart;
              }
              else {
                nSelStart = nMark;
              }

              if (nDot <= nFieldEnd) {
                nSelEnd = nFieldEnd;
              }
              else {
                nSelEnd = nDot;
              }
            }
          }
          else {
            if (m_nOldDot < nDot) {
              // making selection smaller...
              nSelStart = Math.max (nMark, nFieldEnd);
              nSelEnd = Math.max (nDot, nFieldEnd);
            }
            else {
              if (nMark < nFieldEnd) {
                nSelStart = nFieldEnd;
              }
              else {
                nSelStart = nMark;
              }

              if (nDot >= nFieldStart) {
                nSelEnd = nFieldStart;
              }
              else {
                nSelEnd = nDot;
              }
            }
          }
          
          Caret c = m_jtpConstraintEditor.getCaret();
          m_fHandleCaretUpdates = false;
          c.setDot (nSelStart);
          c.moveDot (nSelEnd);
          m_fHandleCaretUpdates = true;
          
          m_nOldMark = nSelEnd;
          m_nOldDot = nSelEnd;
        }
        else {
          m_nOldDot = e.getDot();
          m_nOldMark = e.getMark();
        }
      }
    });
  }//GEN-LAST:event_onCaretUpdate

  private void onQuickBarButton (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onQuickBarButton
    /*if (m_ocltQuickBar == null) {
      m_ocltQuickBar = new OCLToolbar (this);
    }

    if (m_jcbQuickBar.isSelected()) {
      m_ocltQuickBar.setVisible (true);
      m_ocltQuickBar.toFront();
    }
    else {
      m_ocltQuickBar.setVisible (false);
    }*/

    m_ocltbQuickBar.setVisible (m_jcbQuickBar.isSelected());
    revalidate();
    repaint();
  }//GEN-LAST:event_onQuickBarButton

  /**
    * React to the submit button.
    */
  private void onSubmitConstraintButton (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onSubmitConstraintButton
    if (m_oclemModel != null) {
      int nIdx = m_jtConstraintList.getSelectedRow();

      if (nIdx != -1) {
        try {
          m_oclemModel.getConstraintAt (nIdx)
              .setData (m_jtpConstraintEditor.getText(), this);
          
          // Stop editing if successful
          setEditMode (false);
        }
        catch (OclParserException ope) {
          int nCaretPos = getCaretPositionFromLineAndColumn (
              ope.getErrorLine(),
              ope.getErrorCol());
          
          JOptionPane.showMessageDialog (null,
                                           "Syntax error: " +
                                                ope.getMessage()/* +
                                                " at (" + ope.getErrorLine() +
                                                ", " + ope.getErrorCol() + ")"*/,
                                           "Error",
                                           JOptionPane.ERROR_MESSAGE);
          
          m_jtpConstraintEditor.select (nCaretPos, nCaretPos);
          m_jtpConstraintEditor.requestFocus();
        }
        catch (OclTypeException ote) {
          JOptionPane.showMessageDialog (null,
                                           "Type checking failed: " +
                                                ote.getMessage(),
                                           "Error",
                                           JOptionPane.ERROR_MESSAGE);
        }
        catch (IllegalStateException ise) {
          JOptionPane.showMessageDialog (null,
                                           "Couldn't set constraint: " +
                                               ise.getMessage(),
                                           "Error",
                                           JOptionPane.ERROR_MESSAGE);
        }
      }
    }
  }//GEN-LAST:event_onSubmitConstraintButton

  /**
   * Compute caret position for the given line and column in the editor pane's
   * text.
   */
  protected int getCaretPositionFromLineAndColumn (int nLine, int nCol) {
    int nCurLine = 1; int nCurCol = 1;
    String sText = m_jtpConstraintEditor.getText();
    int nCaret = 0;
    while ((nCurLine != nLine) || (nCurCol != nCol)) {
      if (nCaret >= sText.length()) {
        return 0;
      }
      
      if (sText.charAt (nCaret) == '\n') {
        // new line
        nCurLine++;
        nCurCol = 1;
      }
      else {
        nCurCol++;
      }
      
      nCaret++;
    }
    
    return nCaret;
  }
  
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

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JToolBar m_jtbTools;
  private javax.swing.JButton m_jbNew;
  private javax.swing.JButton m_jbRemove;
  private javax.swing.JButton m_jbEdit;
  private javax.swing.JButton m_jbSaveEditResult;
  private javax.swing.JPanel pad1;
  private javax.swing.JButton m_jbPreferences;
  private javax.swing.JPanel pad2;
  private javax.swing.JCheckBox m_jcbQuickBar;
  private javax.swing.JPanel m_jpToolbarWrapper;
  private tudresden.ocl.gui.OCLToolbar m_ocltbQuickBar;
  private javax.swing.JPanel m_jpEditorPanel;
  private javax.swing.JScrollPane m_jspConstraintEditorScroller;
  private javax.swing.JTextPane m_jtpConstraintEditor;
  private javax.swing.JSplitPane m_jspMainPane;
  private javax.swing.JPanel m_jpConstraintListPane;
  private javax.swing.JScrollPane m_jspConstraintListScroller;
  private javax.swing.JTable m_jtConstraintList;
  private javax.swing.JPanel m_jpPreviewPane;
  private javax.swing.JPanel m_jpPreviewGroup;
  private javax.swing.JScrollPane m_jspConstraintPreviewScroller;
  private javax.swing.JTextPane m_jtpConstraintPreview;
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
    
    m_jtpConstraintEditor.setCharacterAttributes (s_sasNormalText, true);
    if (m_crCurrent != null) {
      // TODO: Deal with fields.
      m_jtpConstraintEditor.setText (m_crCurrent.getData());
      m_jtpConstraintPreview.setText (m_crCurrent.getData());
      //m_jtpConstraintEditor.requestFocus(); --> Not visible, as this cannot happen in edit mode!
    }
    else {
      m_jtpConstraintEditor.setText ("");
      m_jtpConstraintPreview.setText ("");
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
    // TODO: Deal with fields.
    if (cce.getIndex() == m_jtConstraintList.getSelectedRow()) {
      m_jtpConstraintEditor.setText (cce.getNew().getData());
      m_jtpConstraintPreview.setText (cce.getNew().getData());
    }
  }
}