/*
Copyright (C) 2002 Andrea Kling

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
package tudresden.ocl.sql.gui;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Vector;
import tudresden.ocl.codegen.decl.Table;
import tudresden.ocl.codegen.decl.ObjectView;
import tudresden.ocl.sql.*;

/**
 * A JPanel used as a Tab in SchemaGeneratorGUI. It is used to view
 * Tables and ObjectViews, the results of object-relational mapping
 * using ORMappingImpl
 *
 * @see SchemaGeneratorGUI
 * @see tudresden.ocl.sql.ORMappingImpl
 * @see tudresden.ocl.codegen.decl.Table
 * @see tudresden.ocl.codegen.decl.ObjectView
 *
 * @author Andrea Kling
 * */

public class TableTab extends JPanel{
  private final ORMappingImpl mapping;
  private final SchemaGeneratorGUI gui;
  private final JComboBox choice;
  private final JList list;
  private final JPanel center;
  private final Vector tableNames, tables, classes, classViews, viewNames;
  private ListSelectionListener current;

  public TableTab(ORMappingImpl mapping, SchemaGeneratorGUI parent){
    super(new BorderLayout(10,10));
    this.mapping = mapping;
    this.gui = parent;
    tables = new Vector(mapping.tables());
    tableNames = new Vector();
    for(int i=0; i<tables.size(); i++)
      tableNames.add(((Table) tables.get(i)).getTableName());
    classes = new Vector(mapping.classifiers());
    classViews = new Vector(mapping.getClassViews());
    viewNames = new Vector();
    for(int i=0; i<classViews.size(); i++)
      viewNames.add(((ObjectView) classViews.get(i)).getName());
    setBorder(new EmptyBorder(20,20,20,20));
    choice = new JComboBox();
    choice.setEditable(false);
    choice.addItem("View all tables");
    choice.addItem("View tables by classes");
    choice.addItem("View Object Views");
    center = new JPanel();
    list = new JList();
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    current = new TableViewer(center, list, tables);
    list.addListSelectionListener(current);
    choice.addActionListener(new ListFiller());
    choice.setSelectedItem("View all tables");
    JPanel west = new JPanel(new BorderLayout(10,10));
    west.add(choice, BorderLayout.NORTH);
    west.add(new JScrollPane(list), BorderLayout.CENTER);
    add(west, BorderLayout.WEST);
    add(center, BorderLayout.CENTER);
    JButton finish = new JButton("View SQL Code");
    finish.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        gui.construct();
      }
    });
    JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    south.add(finish);
    add(south, BorderLayout.SOUTH);

  }

  /**
   * ActionListener for the combobox deciding whether to display tables,
   * classes or objectViews in the List
   * */

  private class ListFiller implements ActionListener{
    public ListFiller(){}

    public void actionPerformed(ActionEvent e){
      if(choice.getSelectedItem().equals("View all tables")){
        list.setListData(tableNames);
        list.removeListSelectionListener(current);
        current = new TableViewer(center, list, tables);
        list.addListSelectionListener(current);
      }
      if(choice.getSelectedItem().equals("View tables by classes")){
        list.setListData(classes);
        list.removeListSelectionListener(current);
        current = new ClassViewer(center);
        list.addListSelectionListener(current);
      }
      if(choice.getSelectedItem().equals("View Object Views")){
        list.setListData(viewNames);
        list.removeListSelectionListener(current);
        current = new ObjectViewViewer(center);
        list.addListSelectionListener(current);
      }

      center.removeAll();
      center.add(new JLabel(""), BorderLayout.CENTER);
      center.validate();
    }
  }


  private class ObjectViewViewer implements ListSelectionListener{
    private JPanel panel;

    public ObjectViewViewer(JPanel panel){
      this.panel = panel;
    }

    public void valueChanged(ListSelectionEvent e) {
      if(list.getSelectedIndex() == -1) return;
      final ObjectView ov = (ObjectView) classViews.get(list.getSelectedIndex());
      final Object[][] columns = ov.getColumns();
      TableModel dataModel = new AbstractTableModel() {
          public int getColumnCount() { return 3; }
          public int getRowCount() { return columns.length;}
          public String getColumnName(int col){
            if(col == 0)
              return "Class Attribute";
            if(col == 1)
              return "Column";
              return "Table";
          }
          public Object getValueAt(int row, int col) {
            if(col < 2){
              return columns[row][col];
            }else{
              return ((Table) columns[row][col]).getTableName();
            }
          }
      };
      JTable jtable = new JTable(dataModel);
      panel.removeAll();
      panel.setLayout(new BorderLayout(10,10));
      panel.add(new JLabel(ov.getName()), BorderLayout.NORTH);
      panel.add(new JScrollPane(jtable), BorderLayout.CENTER);
      panel.validate();
    }
  }



  private class TableViewer implements ListSelectionListener{
    private JPanel panel;
    private JList list;
    private Vector tables;

    public TableViewer(JPanel panel, JList list, Vector tables){
      this.list = list;
      this.panel = panel;
      this.tables = tables;
    }

    public void valueChanged(ListSelectionEvent e) {
      if(list.getSelectedIndex() == -1) return;
      final Table t = (Table) tables.get(list.getSelectedIndex());
      final String[] columns = t.getColumns();
      TableModel dataModel = new AbstractTableModel() {
          public int getColumnCount() { return 3; }
          public int getRowCount() { return columns.length;}
          public String getColumnName(int col){
            if(col == 0)
              return "column";
            if(col == 1)
              return "type";
              return "";
          }
          public Object getValueAt(int row, int col) {
            if(col == 0){
              return columns[row];
            }
            if(col == 1){
              return t.getColumnType(columns[row]);
            }else{
              String str = "";
              if(t.isPrimaryKeyColumn(columns[row]))
                str += "PK";
              if(t.isForeignKeyColumn(columns[row]))
                str += " FK: "+t.getForeignTable(columns[row])+
                "."+t.getForeignColumn(columns[row]);
              return str;
            }
          }
      };
      JTable jtable = new JTable(dataModel);
      panel.removeAll();
      panel.setLayout(new BorderLayout(10,10));
      panel.add(new JLabel(t.getTableName()), BorderLayout.NORTH);
      panel.add(new JScrollPane(jtable), BorderLayout.CENTER);
      panel.validate();
    }
  }

  private class ClassViewer implements ListSelectionListener{
    private JPanel panel;

    public ClassViewer(JPanel panel){
      this.panel = panel;
    }

    public void valueChanged(ListSelectionEvent e) {
      if(list.getSelectedIndex() == -1) return;
      panel.removeAll();
      panel.removeAll();
      panel.setLayout(new BorderLayout(10,10));
      Vector classTables = new Vector(mapping.getClassTables((String) list.getSelectedValue()));
      Vector listData = new Vector();
      for(int i=0; i<classTables.size();i++)
        listData.add(((Table) classTables.get(i)).getTableName());
      JList tableList = new JList(listData);
      tableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      JPanel center = new JPanel();
      tableList.addListSelectionListener(new TableViewer(center, tableList, classTables));
      JPanel west = new JPanel(new BorderLayout(10,10));
      west.add(new JLabel("Tables for "+ list.getSelectedValue()),BorderLayout.NORTH);
      west.add(new JScrollPane(tableList), BorderLayout.CENTER);
      panel.add(west, BorderLayout.WEST);
      panel.add(center, BorderLayout.CENTER);
      panel.validate();
    }
  }

}
