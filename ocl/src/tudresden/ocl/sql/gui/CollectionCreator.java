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

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.util.Vector;
import java.util.List;
import ru.novosoft.uml.MBase;
import ru.novosoft.uml.foundation.core.*;
import tudresden.ocl.sql.orstrategy.CollectionMapper;
import tudresden.ocl.sql.ORMappingImpl;
import tudresden.ocl.sql.TypeManager;
import tudresden.ocl.sql.orstrategy.DatatypeStrategy;

  /**
   * A StrategyCreator for CollectionMapper
   * @see tudresden.ocl.sql.orstrategy.CollectionMapper
   * @author Andrea Kling
   * */
public class CollectionCreator implements DatatypeStrategyCreator{

  private static CollectionCreator myInstance;
  private DatatypeStrategy strategy;
  List types;

  private CollectionCreator(){
    types = new Vector();
  }

  public static CollectionCreator getInstance(){
    if(myInstance == null)
      myInstance = new CollectionCreator();
    return myInstance;
  }

  /**
   * @return the Type of Strategy according to Types defined in ORMappingImpl
   * @see tudresden.ocl.sql.ORMappingImpl
   * */
  public String getStrategyType(){
    return ORMappingImpl.TYPE;
  }

  /**
   * @return a short description of the mapping method
   * */
  public String getStrategyDescription(){
    return "external collection table";
  }

  /**
   * provides a List of datatypes (classifiers) defined by the model
   * for use as datatypes in the collection table
   * @param types a List of String with additional types
   * */
  public void setAdditionalDatatypes(List types){
    if(types != null)
      this.types = types;
  }

  /**
   * @param element the MAttribute that shall be mapped with the Strategy
   * @return a component making all necessary input for the creation
   * of the Strategy available
   *
   * @see ru.novosoft.uml.foundation.core.MAttribute
   * */
  public JComponent getStrategyView(MBase element){
    if(element == null)
      return new JPanel();
    final JPanel panel = new JPanel(new BorderLayout(10,10));
    panel.setBorder(new EmptyBorder(10,10,10,10));
    final MAttribute attribute = (MAttribute) element;
    final JComboBox dataTypes = new JComboBox(new Vector(TypeManager.getInstance().getTypes()));
    for (int i=0; i<types.size(); i++)
      dataTypes.addItem(types.get(i));
    final JTextField attributeName=new JTextField(attribute.getName());
    final Vector atts = new Vector();
    final Vector dtypes = new Vector();
    TableModel dataModel = new AbstractTableModel() {
      public int getColumnCount() { return 2; }
      public int getRowCount() { //return dtypes.size()+2;
        return 20;}
      public String getColumnName(int col){
        if(col == 0)
          return "Column";
        return "Type";
      }
      public Object getValueAt(int row, int col) {
        if(row == 0 && col == 0)
          return attribute.getOwner().getName()+"_PK";
        if(row == 0 && col == 1)
          return "id";
        if(row == 1 && col == 0)
          return "sequence";
        if(row == 1 && col == 1)
          return "int";
        if(col == 0 && row-2<atts.size()){
          return atts.get(row-2);
        }
        if(col ==1 && row-2<dtypes.size())
          return dtypes.get(row-2);
        return "";
      }
    };
    final JTable jtable = new JTable(dataModel);
    JButton add = new JButton("Add Column");
    add.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(dataTypes.getSelectedIndex() == -1) return;
        if(attributeName.getText() == null) return;
        if(attributeName.getText().trim().equals("")) return;
        if(atts.contains(attributeName.getText().trim()) ||
          attributeName.getText().trim().toLowerCase().equals("sequence")){
          JOptionPane error = new JOptionPane();
          error.showMessageDialog(new JFrame(), "Column Name already exists."
          ,"Error",JOptionPane.ERROR_MESSAGE);
          return;
        }
        atts.add(attributeName.getText().trim());
        dtypes.add(dataTypes.getSelectedItem());
        jtable.changeSelection(dtypes.size()+1,0,false,false);
        panel.validate();
      }
    });
    JButton remove = new JButton("Remove Column", new ImageIcon("tudresden/ocl/images/sgdelete.gif"));
    remove.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(jtable.getSelectedRow() == -1) return;
        if(jtable.getSelectedRow() < 2) {
          JOptionPane error = new JOptionPane();
          error.showMessageDialog(new JFrame(), "Column can not be removed."
          ,"Information",JOptionPane.PLAIN_MESSAGE);
          return;
        }
        if(jtable.getSelectedRow() > atts.size()+1) return;
        atts.remove(jtable.getSelectedRow()-2);
        dtypes.remove(jtable.getSelectedRow()-2);
        jtable.changeSelection(0,0,false,false);
        panel.validate();
      }
    });
    JButton ok = new JButton("OK");
    ok.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(atts.size() > 0)
          strategy = new CollectionMapper(atts, dtypes);
        try{
          ((JDialog) panel.getTopLevelAncestor()).dispose();
        }catch(Exception ex){}
      }
    });
    JButton cancel = new JButton("Cancel");
    cancel.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        try{
          ((JDialog) panel.getTopLevelAncestor()).dispose();
        }catch(Exception ex){}
      }
    });
    JPanel center = new JPanel( new GridLayout(1,2,10,10));
    JPanel left = new JPanel(new GridLayout(3,1,10,10));
    left.add(new JLabel("Column Name:", JLabel.RIGHT));
    left.add(new JLabel("Column Type:", JLabel.RIGHT));
    left.add(add);
    JPanel middle = new JPanel(new GridLayout(3,1,10,10));
    middle.add(attributeName);
    middle.add(dataTypes);
    middle.add(remove);
    JPanel west = new JPanel(new BorderLayout(10,10));
    west.add(left, BorderLayout.WEST);
    west.add(middle, BorderLayout.CENTER);
    JPanel shrink = new JPanel();
    shrink.add(west);
    center.add(shrink);
    center.add(new JScrollPane(jtable));
    panel.add(new JLabel("Collection Table for "+attribute.getName()), BorderLayout.NORTH);
    panel.add(center, BorderLayout.CENTER);
    JPanel south = new JPanel(new FlowLayout(FlowLayout.LEFT));
    south.add(ok);
    south.add(cancel);
    panel.add(south, BorderLayout.SOUTH);
    return panel;
  }



  /**
   * @return the CollectionMapper defined in the strategyView,
   * null if not enough data ws provided by the view
   *
   * @see tudresden.ocl.sql.orstrategy.CollectionMapper
   * */
  public Object getStrategy(){
    return strategy;
  }
}

