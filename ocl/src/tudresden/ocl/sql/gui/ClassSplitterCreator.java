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

import java.io.FileOutputStream;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Vector;
import java.util.Iterator;
import java.util.List;
import ru.novosoft.uml.MBase;
import ru.novosoft.uml.foundation.core.*;
import tudresden.ocl.sql.orstrategy.ClassSplitter;
import tudresden.ocl.sql.ORMappingImpl;


/**
 * A StrategyCreator for the ClassSplitter-ClassStrategy
 *
 * @see tudresden.ocl.sql.orstrategy.ClassSplitter
 * @author Andrea Kling
 * */
public class ClassSplitterCreator implements StrategyCreator{
  private ClassSplitter strategy;
  private static ClassSplitterCreator myInstance;

  private ClassSplitterCreator(){}

  public static ClassSplitterCreator getInstance(){
    if(myInstance == null)
      myInstance = new ClassSplitterCreator();
    return myInstance;
  }

  /**
   * @return the Type of Strategy according to Types defined in ORMappingImpl (ORMappingImpl.CLASS)
   * @see tudresden.ocl.sql.ORMappingImpl
   * */
  public String getStrategyType(){
    return ORMappingImpl.CLASS;
  }
  /**
   * @return a short description of the mapping method
   * */
  public String getStrategyDescription(){
    return "splitting class into several tables";
  }
  /**
   * a View to make input for StrategyCreation possible.<BR>
   * The JComponent returned tries to dispose its toplevel ancestor
   * when done with input if it is a JDialog,
   * assuming the Dialog was opened only to show this component
   *
   * @param element the MClassifier that shall be split into several tables
   * @return a component making all necessary input for the creation
   * of the Strategy available
   * */
  public synchronized JComponent getStrategyView(MBase element){
    if(element == null) return new JPanel();
    strategy = null;
    final JPanel panel = new JPanel(new BorderLayout(10,10));
    panel.setBorder(new EmptyBorder(10,10,10,10));
    final JList list = new JList();
    final MClassifier m = (MClassifier) element;
    final Vector attributes = new Vector();
    final Vector attributeNames = new Vector();
    Iterator it = m.getFeatures().iterator();
    while(it.hasNext()){
      MFeature attrib = (MFeature) it.next();
      if(attrib instanceof MAttribute){
        attributeNames.add(attrib.getName());
        attributes.add(attrib);
      }
    }
    list.setListData(attributeNames);
    final Vector names = new Vector();
    final Vector parts = new Vector();
    JPanel center = new JPanel(new GridLayout(1,3,10,10));
    JPanel left = new JPanel(new BorderLayout(10,10));
    left.add(new JLabel("Attributes of "+m.getName()),BorderLayout.NORTH);
    left.add(new JScrollPane(list), BorderLayout.CENTER);
    center.add(left);
    final JLabel mainTable = new JLabel("Main Table: ");
    JButton addPart = new JButton("Add Table");
    final JTextField tabName = new JTextField("TableName");
    final JComboBox tableChoice = new JComboBox();
    addPart.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        String str = tabName.getText();
        if (str == null) return;
        if(str.equals("TableName") || names.contains(str)){
          str = m.getName().toUpperCase()+(names.size()+1);
        }
        names.add(str);
        parts.add(new Vector());
        if(tableChoice.getSelectedIndex() > -1){
          tableChoice.removeAllItems();
          for(int i=0; i<names.size(); i++)
            tableChoice.addItem((String) names.get(i));
        }else{
          tableChoice.addItem(str);
        }
        tableChoice.setSelectedItem(str);
        if(names.size() == 1)
          mainTable.setText("Main Table: "+names.get(0));
        panel.validate();
      }
    });
    final JList tableList = new JList();
    tableChoice.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(tableChoice.getSelectedIndex() == -1) return;
        if(names.size() == 0){
          tableList.setListData(new Vector());
          tableList.validate();
          return;
        }
        Vector listData = new Vector();
        Vector data = (Vector) parts.get(tableChoice.getSelectedIndex());
        for(int i=0; i<data.size(); i++)
          listData.add(((MAttribute) data.get(i)).getName());
        tableList.setListData(listData);
        tableList.validate();
      }
    });
    JButton addToPart = new JButton("Add to Table   >>");
    addToPart.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(list.getSelectedIndex() == -1 ||
          tableChoice.getSelectedIndex() == -1 ||
          names.size() ==0) return;
        ((Vector) parts.get(tableChoice.getSelectedIndex())).add(
          attributes.get(list.getSelectedIndex()));
        attributes.remove(list.getSelectedIndex());
        attributeNames.remove(list.getSelectedIndex());
        list.setListData(attributeNames);
        list.validate();
        tableChoice.setSelectedIndex(tableChoice.getSelectedIndex());
      }
    });
    JButton removeFromPart = new JButton("<<   Remove from Table");
    removeFromPart.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(tableList.getSelectedIndex() == -1) return;
        if(tableChoice.getSelectedIndex() == -1) return;
        if(names.size() == 0) return;
        MAttribute att= (MAttribute)
        ((List) parts.get(tableChoice.getSelectedIndex())).get(tableList.getSelectedIndex());
        attributes.add(att);
        attributeNames.add(att.getName());
        list.setListData(attributeNames);
        ((List) parts.get(tableChoice.getSelectedIndex())).remove(tableList.getSelectedIndex());
        tableChoice.setSelectedIndex(tableChoice.getSelectedIndex());
        panel.validate();
      }
    });
    JButton removeTable = new JButton("Remove Table", new ImageIcon("tudresden/ocl/images/sgdelete.gif"));
    removeTable.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(tableChoice.getSelectedIndex() == -1) return;
        if(names.size() == 0) return;
        int select = tableChoice.getSelectedIndex();
        try{
        names.remove(select);
        List l = (List) parts.remove(select);
        for (int i=0; i<l.size(); i++){
          MAttribute att = (MAttribute) l.get(i);
          attributes.add(att);
          attributeNames.add(att.getName());
        }
        list.setListData(attributeNames);
        if(names.size() > 0){
          mainTable.setText("Main Table: "+names.get(0));
        }else{
          mainTable.setText("Main Table: ");
        }
        if(select == 0){
          tableChoice.removeAllItems();
          for(int i=0; i<names.size(); i++)
            tableChoice.addItem((String) names.get(i));
        }else{
          tableChoice.removeItemAt(select);
        }
        panel.validate();
        if(tableChoice.getSelectedIndex() == -1){
          tableList.setListData(new Vector());
          panel.validate();
        }
        }catch(Exception ex){
        }
      }
    });
    JButton setMainTable = new JButton("Set Main Table");
    setMainTable.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(tableChoice.getSelectedIndex() == -1) return;
        if(names.size() == 0) {
          mainTable.setText("Main Table: ");
          panel.validate();
          return;
        }
          names.add(0, names.remove(tableChoice.getSelectedIndex()));
          parts.add(0, parts.remove(tableChoice.getSelectedIndex()));
          tableChoice.removeAllItems();
          for(int i=0; i<names.size(); i++)
            tableChoice.addItem((String) names.get(i));
          tableChoice.setSelectedIndex(0);
          mainTable.setText("Main Table: "+names.get(0));
          panel.validate();
      }
    });
    JPanel center2 = new JPanel(new BorderLayout(10,10));
    center2.add(addPart, BorderLayout.NORTH);
    JPanel cCenter = new JPanel(new GridLayout(6,1,10,10));
    cCenter.add(tableChoice);
    cCenter.add(addToPart);
    cCenter.add(removeFromPart);
    cCenter.add(setMainTable);
    cCenter.add(mainTable);
    cCenter.add(removeTable);
    center2.add(cCenter, BorderLayout.CENTER);
    center.add(center2);
    JPanel right = new JPanel(new BorderLayout(10,10));
    right.add(tabName, BorderLayout.NORTH);
    right.add(new JScrollPane(tableList), BorderLayout.CENTER);
    center.add(right);
    JButton ok = new JButton("OK");
    ok.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(attributes.size() > 0){
          names.add(m.getName().toUpperCase()+(names.size()+1));
          parts.add(attributes);
        }
        strategy = new ClassSplitter(names, parts);
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
    JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    south.add(ok);
      south.add(cancel);
    panel.add(center, BorderLayout.CENTER);
    panel.add(south, BorderLayout.SOUTH);
    return panel;
  }
  /**
   * @return a ClassSplitter, null if not enough data was provided by the view
   * @see tudresden.ocl.sql.orstrategy.ClassSplitter
   * */
  public Object getStrategy(){
    return strategy;
  }
}
