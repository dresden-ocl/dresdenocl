/*
Copyright (C) 2001  Steffen Zschaler

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
package tudresden.ocl.parser;

import javax.swing.*;
import javax.swing.table.*;

import java.io.*;

/** 
 * GUI to edit the parser error messages in parser/parser.dat.
 *
 * @author  sz9
 */
public class JEditParserMessageTables extends javax.swing.JFrame {

  private static class ParserData {
    private int[][][] actionTable;
    private int[][][] gotoTable;

    private String[] errorMessages;
    private int[] errors;
    
    private class ErrMapModel extends AbstractTableModel {
      public int getRowCount() {
        return errors.length;
      }
      
      public void setValueAt (final Object oValue,
                                 int row, int col) {
        for (int i = 0; i < errorMessages.length; i++) {
          if (errorMessages[i] == oValue) {
            errors[row] = i;
            fireTableCellUpdated (row, col);
            
            return;
          }
        }
      }
      
      public boolean isCellEditable (int row, int col) {
        return col == 1;
      }
      
      public int getColumnCount() {
        return 2;
      }
      
      public Object getValueAt (int row, int col) {
        switch (col) {
          case 0:
            return new Integer (row);
          case 1:
            return "(" + errors[row] + "): \"" + errorMessages[errors[row]] + "\"";
           
          default:
            return null;
        }
      }
      
      public Class getColumnClass (int col) {
        switch (col) {
          case 0:
            return Integer.class;
          case 1:
            return String.class;
        }
        
        return null;
      }
      
      public String getColumnName (int col) {
        switch (col) {
          case 0:
            return "Error number";
          case 1:
            return "Error message";
        }
        
        return null;
      }
    }
    
    private ErrMapModel m_emmErrMap = new ErrMapModel();
    
    private class ErrMsgModel extends AbstractTableModel {
      public int getRowCount() {
        return errorMessages.length;
      }
      
      public void setValueAt (final Object oValue, int row, int col) {
        errorMessages[row] = oValue.toString();
        
        fireTableCellUpdated (row, col);
        m_emlm.fireContentsChanged (m_emlm, row, row);
      }
      
      public boolean isCellEditable (int row, int col) {
        return true;
      }
      
      public int getColumnCount() {
        return 1;
      }
      
      public Object getValueAt (int row, int col) {
        return errorMessages [row];
      }
      
      public Class getColumnClass (int col) {
        return String.class;
      }
      
      public String getColumnName (int col) {
        return "Error message";
      }
    }
    
    private ErrMsgModel m_emmErrMsg = new ErrMsgModel();
    
    private class ErrMsgListModel extends DefaultComboBoxModel {

      public int getSize() {
        return errorMessages.length;
      }
      
      public Object getElementAt (int row) {
        return errorMessages[row];
      }
      
      public void fireContentsChanged(Object source,
                                   int index0,
                                   int index1) {
        super.fireContentsChanged (source, index0, index1);
      }
    }
    
    private ErrMsgListModel m_emlm = new ErrMsgListModel();
    
    public ParserData() {
      try {
        DataInputStream s = new DataInputStream(
          new BufferedInputStream(
          getClass().getResourceAsStream ("parser/parser.dat")));

        // read actionTable
        int length = s.readInt();
        actionTable = new int[length][][];
        for(int i = 0; i < actionTable.length; i++) {
          length = s.readInt();
          actionTable[i] = new int[length][3];
          for(int j = 0; j < actionTable[i].length; j++) {
            for(int k = 0; k < 3; k++) {
              actionTable[i][j][k] = s.readInt();
            }
          }
        }

        // read gotoTable
        length = s.readInt();
        gotoTable = new int[length][][];
        for(int i = 0; i < gotoTable.length; i++) {
          length = s.readInt();
          gotoTable[i] = new int[length][2];
          for(int j = 0; j < gotoTable[i].length; j++) {
            for(int k = 0; k < 2; k++) {
              gotoTable[i][j][k] = s.readInt();
            }
          }
        }

        // read errorMessages
        length = s.readInt();
        errorMessages = new String[length];
        for(int i = 0; i < errorMessages.length; i++) {
          length = s.readInt();
          StringBuffer buffer = new StringBuffer();

          for(int j = 0; j < length; j++) {
            buffer.append(s.readChar());
          }
          
          errorMessages[i] = buffer.toString();
        }

        // read errors
        length = s.readInt();
        errors = new int[length];
        for(int i = 0; i < errors.length; i++) {
          errors[i] = s.readInt();
        }

        s.close();
      }
      catch(Exception e) {
        throw new RuntimeException("Unable to read parser.dat.");
      }
    }
    
    public TableModel getErrMapModel() {
      return m_emmErrMap;
    }
    
    public TableModel getErrMsgModel() {
      return m_emmErrMsg;
    }
    
    public TableCellEditor getErrMapEditor() {
      return new DefaultCellEditor (new JComboBox (m_emlm)) {
        public java.awt.Component getTableCellEditorComponent(JTable table,
                                                     Object value,
                                                     boolean isSelected,
                                                     int row,
                                                     int column) {
          JComboBox jcb = (JComboBox) super.getTableCellEditorComponent (
              table,
              value,
              isSelected,
              row,
              column);
          
          jcb.setSelectedItem (errorMessages[errors[row]]);
          
          return jcb;
        }
      };
    }
  }
  
  private ParserData m_pdParserData;
  
  /** Creates new form JEditParserMessageTables */
  public JEditParserMessageTables() {
    initComponents ();
    
    // read in parser.dat
    m_pdParserData = new ParserData();
    
    m_jtErrorMap.setModel (m_pdParserData.getErrMapModel());
    m_jtErrorMap.getColumnModel().getColumn(1).setCellEditor (m_pdParserData.getErrMapEditor());
    
    m_jtErrorMessages.setModel (m_pdParserData.getErrMsgModel());
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the FormEditor.
   */
  private void initComponents () {//GEN-BEGIN:initComponents
    m_jtbToolBar = new javax.swing.JToolBar ();
    m_jbSaveButton = new javax.swing.JButton ();
    m_jtpTabs = new javax.swing.JTabbedPane ();
    m_jpErrorMapping = new javax.swing.JPanel ();
    m_jspErrorMap = new javax.swing.JScrollPane ();
    m_jtErrorMap = new javax.swing.JTable ();
    m_jpErrorMessages = new javax.swing.JPanel ();
    m_jspErrorMessages = new javax.swing.JScrollPane ();
    m_jtErrorMessages = new javax.swing.JTable ();
    m_jpErrMsgButtons = new javax.swing.JPanel ();
    m_jbAddMessage = new javax.swing.JButton ();
    m_jbRemoveMessage = new javax.swing.JButton ();
    setTitle ("Edit Parser Error Messages");
    addWindowListener (new java.awt.event.WindowAdapter () {
      public void windowClosing (java.awt.event.WindowEvent evt) {
        exitForm (evt);
      }
    }
    );


      m_jbSaveButton.setIcon (new javax.swing.ImageIcon (getClass ().getResource ("/tudresden/ocl/images/save_icon.gif")));
      m_jbSaveButton.setHorizontalTextPosition (javax.swing.SwingConstants.CENTER);
      m_jbSaveButton.setMargin (new java.awt.Insets(2, 2, 2, 2));
      m_jbSaveButton.setAlignmentY (0.5F);
      m_jbSaveButton.setAlignmentX (0.5F);
      m_jbSaveButton.setDefaultCapable (false);
  
      m_jtbToolBar.add (m_jbSaveButton);
  

    getContentPane ().add (m_jtbToolBar, java.awt.BorderLayout.NORTH);


      m_jpErrorMapping.setLayout (new java.awt.GridBagLayout ());
      java.awt.GridBagConstraints gridBagConstraints1;
  
    
          m_jtErrorMap.setModel (new javax.swing.table.DefaultTableModel (
          new Object [][] {
          {null, null, null, null},
          {null, null, null, null},
          {null, null, null, null},
          {null, null, null, null}
          },
          new String [] {
            "Title 1", "Title 2", "Title 3", "Title 4"
          }
          ) {
            Class[] types = new Class [] {
              java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
      
            public Class getColumnClass (int columnIndex) {
              return types [columnIndex];
            }
          });
          m_jtErrorMap.setAutoResizeMode (javax.swing.JTable.AUTO_RESIZE_OFF);
      
          m_jspErrorMap.setViewportView (m_jtErrorMap);
      
        gridBagConstraints1 = new java.awt.GridBagConstraints ();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.gridheight = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets (5, 5, 10, 5);
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.weighty = 1.0;
        m_jpErrorMapping.add (m_jspErrorMap, gridBagConstraints1);
    
      m_jtpTabs.addTab ("Error mappings", null, m_jpErrorMapping, "Maps error numbers to error messages.");
  
      m_jpErrorMessages.setLayout (new java.awt.GridBagLayout ());
      java.awt.GridBagConstraints gridBagConstraints2;
  
    
          m_jtErrorMessages.setModel (new javax.swing.table.DefaultTableModel (
          new Object [][] {
          {null, null, null, null},
          {null, null, null, null},
          {null, null, null, null},
          {null, null, null, null}
          },
          new String [] {
            "Title 1", "Title 2", "Title 3", "Title 4"
          }
          ) {
            Class[] types = new Class [] {
              java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
      
            public Class getColumnClass (int columnIndex) {
              return types [columnIndex];
            }
          });
          m_jtErrorMessages.setAutoResizeMode (javax.swing.JTable.AUTO_RESIZE_OFF);
      
          m_jspErrorMessages.setViewportView (m_jtErrorMessages);
      
        gridBagConstraints2 = new java.awt.GridBagConstraints ();
        gridBagConstraints2.gridwidth = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets (5, 5, 5, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        m_jpErrorMessages.add (m_jspErrorMessages, gridBagConstraints2);
    
        m_jpErrMsgButtons.setLayout (new java.awt.FlowLayout (2, 5, 5));
    
          m_jbAddMessage.setIcon (new javax.swing.ImageIcon (getClass ().getResource ("/tudresden/ocl/images/RowInsertAfter24.gif")));
          m_jbAddMessage.setText ("New");
      
          m_jpErrMsgButtons.add (m_jbAddMessage);
      
          m_jbRemoveMessage.setIcon (new javax.swing.ImageIcon (getClass ().getResource ("/tudresden/ocl/images/RowDelete24.gif")));
          m_jbRemoveMessage.setText ("Remove");
      
          m_jpErrMsgButtons.add (m_jbRemoveMessage);
      
        gridBagConstraints2 = new java.awt.GridBagConstraints ();
        gridBagConstraints2.gridwidth = 0;
        gridBagConstraints2.gridheight = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints2.insets = new java.awt.Insets (0, 5, 10, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints2.weightx = 1.0;
        m_jpErrorMessages.add (m_jpErrMsgButtons, gridBagConstraints2);
    
      m_jtpTabs.addTab ("Error Messages", null, m_jpErrorMessages, "List of error messages");
  

    getContentPane ().add (m_jtpTabs, java.awt.BorderLayout.CENTER);

    pack ();
    java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    java.awt.Dimension dialogSize = getSize();
    setSize (new java.awt.Dimension (300, 200));
    setLocation((screenSize.width-300)/2, (screenSize.height-200)/2);
  }//GEN-END:initComponents

  /** Exit the Application */
  private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
    System.exit (0);
  }//GEN-LAST:event_exitForm

  /**
  * @param args the command line arguments
  */
  public static void main (String args[]) {
    new JEditParserMessageTables ().show ();
  }


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JToolBar m_jtbToolBar;
  private javax.swing.JButton m_jbSaveButton;
  private javax.swing.JTabbedPane m_jtpTabs;
  private javax.swing.JPanel m_jpErrorMapping;
  private javax.swing.JScrollPane m_jspErrorMap;
  private javax.swing.JTable m_jtErrorMap;
  private javax.swing.JPanel m_jpErrorMessages;
  private javax.swing.JScrollPane m_jspErrorMessages;
  private javax.swing.JTable m_jtErrorMessages;
  private javax.swing.JPanel m_jpErrMsgButtons;
  private javax.swing.JButton m_jbAddMessage;
  private javax.swing.JButton m_jbRemoveMessage;
  // End of variables declaration//GEN-END:variables

}