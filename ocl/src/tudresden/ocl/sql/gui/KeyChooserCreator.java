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

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Vector;
import java.util.Iterator;
import ru.novosoft.uml.MBase;
import ru.novosoft.uml.foundation.core.*;
import tudresden.ocl.sql.orstrategy.KeyChooser;
import tudresden.ocl.sql.ORMappingImpl;
import tudresden.ocl.sql.TypeManager;

  /**
   * a Creator for KeyChooser-KeyStrategies
   *
   * @see tudresden.ocl.sql.orstrategy.KeyChooser
   * @author Andrea Kling
   * */
public class KeyChooserCreator implements StrategyCreator{

  private static KeyChooserCreator myInstance;
  private Object strategy;

  private KeyChooserCreator(){}

  public static KeyChooserCreator getInstance(){
    if(myInstance == null)
      myInstance = new KeyChooserCreator();
    return myInstance;
  }

  /**
   * @return the Type of Strategy according to Types defined in ORMappingImpl
   * @see tudresden.ocl.sql.ORMappingImpl
   * */
  public String getStrategyType(){
    return ORMappingImpl.PK;
  }

  /**
   * @return a short description of the mapping method
   * */
  public String getStrategyDescription(){
    return "using attributes as primary key";
  }

  /**
   * @param element the MClassifier that shall be provided with a primary key
   * @return a component making all necessary input for the creation
   * of the Strategy available
   * */
  public JComponent getStrategyView(MBase element){
    if(element == null) return new JPanel();
    strategy = null;
    final JPanel panel = new JPanel(new BorderLayout(10,10));
    panel.setBorder(new EmptyBorder(10,10,10,10));
    MClassifier m = (MClassifier) element;
    final Vector attributes = new Vector();
    final Vector types = new Vector();
    final Vector allAttribs = new Vector();
    final Vector pks = new Vector();
    Iterator it = m.getFeatures().iterator();
    while(it.hasNext()){
      MFeature attrib = (MFeature) it.next();
      if(attrib instanceof MAttribute){
        attributes.add(attrib.getName());
        types.add(((MAttribute)attrib).getType().getName());
        allAttribs.add(attrib);
      }
    }
    TableModel dataModel = new AbstractTableModel() {
        public int getColumnCount() { return 3; }
        public int getRowCount() { return types.size();}
        public String getColumnName(int col){
          if(col == 0)
            return "Attribute";
          if(col == 1)
            return "Type";
          return "Primary Key";
        }
        public Object getValueAt(int row, int col) {
          if(col == 0){
            return attributes.get(row);
          }
          if(col == 1)
            return types.get(row);
          if(pks.contains(allAttribs.get(row)))
            return "primary key";
          return "";
        }
    };
    final JTable jtable = new JTable(dataModel);
    JButton set = new JButton("Set Primary Key");
    set.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(jtable.getSelectedRow() == -1) return;
        MAttribute att = (MAttribute) allAttribs.get(jtable.getSelectedRow());
        if(pks.contains(att)) return;
        if(! TypeManager.getInstance().isDefined(att.getType().getName())){
          JOptionPane error = new JOptionPane();
          error.showMessageDialog(panel, "This attribute's type: '"
          +att.getType().getName()+"' is either unknown to the mapper \n"+
          "or not a simple datatype. \n\nPlease use attributes with simple datatypes"+
          " as primary keys.","Warning",JOptionPane.ERROR_MESSAGE);
          return;
        }
        pks.add(att);
        int select = jtable.getSelectedRow();
        jtable.changeSelection(0,2,false,false);
        jtable.changeSelection(select,0,false,false);
        panel.validate();
      }
    });
    JButton reset = new JButton("Remove Primary Key");
    reset.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(jtable.getSelectedRow() == -1) return;
        MAttribute att = (MAttribute) allAttribs.get(jtable.getSelectedRow());
        if(!pks.contains(att)) return;
        pks.remove(att);
        int select = jtable.getSelectedRow();
        jtable.changeSelection(0,2,false,false);
        jtable.changeSelection(select,0,false,false);
        panel.validate();
      }
    });
    JButton ok = new JButton("OK");
    ok.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(pks.size() > 0)
          strategy = new KeyChooser(pks);
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
    JPanel center = new JPanel(new BorderLayout(10,10));
    center.add(new JScrollPane(jtable), BorderLayout.CENTER);
    JPanel csouth = new JPanel(new FlowLayout());
    csouth.add(set);
    csouth.add(reset);
    center.add(csouth, BorderLayout.SOUTH);
    JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    south.add(ok);
    south.add(cancel);
    panel.add(center, BorderLayout.CENTER);
    panel.add(south, BorderLayout.SOUTH);
    return panel;
  }

  /**
   * @return the KeyChooser strategy for the data provided by the view
   * null if not enough data was provided
   * */
  public Object getStrategy(){
    return strategy;
  }
}
