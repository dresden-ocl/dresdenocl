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

import java.awt.Component;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import javax.swing.tree.*;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.*;
import ru.novosoft.uml.foundation.core.*;
import ru.novosoft.uml.foundation.data_types.*;
import ru.novosoft.uml.MBase;
import tudresden.ocl.sql.*;
import tudresden.ocl.sql.orstrategy.*;

/**
 * A JPanel used as a Tab in SchemaGeneratorGUI. It is used to make choices
 * of object-relational mapping strategies for different model elements
 * possible. Sets the mapping strategies in ORMappingImpl
 * @see SchemaGeneratorGUI
 * @see tudresden.ocl.sql.ORMappingImpl
 *
 * @author Andrea Kling
 * */
public class ChoiceTab extends JPanel{

  private ORMappingImpl mapping;
  private JComboBox choice, strategies;
  private JList list;
  private JButton set, finish;
  private ListSelectionListener current;
  private ActionListener listener;
  private SchemaGeneratorGUI parent;
  private Hashtable classStrategies;        //MClassifier -> ClassStrategy
  private Hashtable datatypeStrategies;     //MAttribute -> DatatypeStrategy
  private Hashtable keyStrategies;          //MClassifier -> KeyStrategy
  private Hashtable inheritanceStrategies;  //MClassifier GeneralizationRoot -> InheritanceStrategy
  private Hashtable associationStrategies;  //MAssociation -> AssociationStrategy
  private Hashtable orderStrategies;        //MAssociationEnd -> OrderStrategy


   /**
    * @param parent the SchemaGeneratorGUI owning this ChoiceTab
    * */
  public ChoiceTab(SchemaGeneratorGUI parent){
    super(new BorderLayout(10,10));
    this.parent = parent;
    setBorder(new EmptyBorder(20,20,20,20));
    classStrategies = new Hashtable();
    datatypeStrategies = new Hashtable();
    keyStrategies = new Hashtable();
    inheritanceStrategies = new Hashtable();
    associationStrategies = new Hashtable();
    orderStrategies = new Hashtable();
    choice = new JComboBox();
    choice.setEditable(false);
    choice.addItem("Classes");
    choice.addItem("Primary Keys");
    choice.addItem("Inheritance");
    choice.addItem("1:1 Associations");
    choice.addItem("1:n Associations");
    choice.addItem("n:m Associations");
    choice.addItem("Ordered Associationends");
    choice.addItem("Attribute Types");
    JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
    north.add(new JLabel("Set Mapping Strategies for "));
    north.add(choice);
    add(north, BorderLayout.NORTH);
    list = new JList();
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    add(new JScrollPane(list), BorderLayout.WEST);
    set = new JButton("Set Strategy");
    strategies = new JComboBox();
    strategies.setEditable(false);
    finish = new JButton("Finished.. Start Mapping");
    JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    south.add(finish);
    add(south, BorderLayout.SOUTH);
  }
  /**
   * since this Panel can be created when first initializing the GUI
   * this method is used to make all UML - modelspecific data available
   * for display
   * @param mapping the ORMappingImpl containing the model to be displayed
   * */

  public void activate(ORMappingImpl mapping){
    if(this.mapping == null){
    this.mapping = mapping;
    JPanel center = new JPanel(new BorderLayout(10, 10));
    add(center, BorderLayout.CENTER);
    choice.addActionListener(new ListFiller(center, this));
    choice.setSelectedIndex(0);
    finish.addActionListener(new Finisher(parent));
    validate();
    }else{
      this.mapping = mapping;
      classStrategies = new Hashtable();
      datatypeStrategies = new Hashtable();
      keyStrategies = new Hashtable();
      inheritanceStrategies = new Hashtable();
      associationStrategies = new Hashtable();
      orderStrategies = new Hashtable();
      choice.setSelectedIndex(0);
    }
  }


   /**
    * ActionListener for the "finished.. Start Mapping"-Button
    * Sets all chosen Strategies in ORMappingImpl "mapping" and causes
    * SchemaGeneratorGUI to switch to the next Tab
    * */
  private class Finisher implements ActionListener{
    private SchemaGeneratorGUI gui;
    /**
     * @param gui the gui to switch to the next tab
     * */
    public Finisher(SchemaGeneratorGUI gui){
      this.gui = gui;
    }

    public void actionPerformed(ActionEvent e){
      Enumeration keys = classStrategies.keys();
      while(keys.hasMoreElements()){
        MClassifier m = (MClassifier) keys.nextElement();
        mapping.setStrategy(m, (ClassStrategy) classStrategies.get(m));
      }
      keys = datatypeStrategies.keys();
      while(keys.hasMoreElements()){
        MAttribute m = (MAttribute) keys.nextElement();
        mapping.setStrategy(m, (DatatypeStrategy) datatypeStrategies.get(m));
      }
      keys = keyStrategies.keys();
      while(keys.hasMoreElements()){
        MClassifier m = (MClassifier) keys.nextElement();
        mapping.setStrategy(m, (KeyStrategy) keyStrategies.get(m));
      }
      keys = inheritanceStrategies.keys();
      while(keys.hasMoreElements()){
        MClassifier m = (MClassifier) keys.nextElement();
        mapping.setStrategy(m, (InheritanceStrategy) inheritanceStrategies.get(m));
      }
      keys = associationStrategies.keys();
      while(keys.hasMoreElements()){
        MAssociation m = (MAssociation) keys.nextElement();
        mapping.setStrategy(m, (AssociationStrategy) associationStrategies.get(m));
      }
      keys = orderStrategies.keys();
      while(keys.hasMoreElements()){
        MAssociationEnd m = (MAssociationEnd) keys.nextElement();
        mapping.setStrategy(m, (OrderedStrategy) orderStrategies.get(m));
      }
      keys = datatypeStrategies.keys();
      while(keys.hasMoreElements()){
        MAttribute m = (MAttribute) keys.nextElement();
        mapping.setStrategy(m, (DatatypeStrategy) datatypeStrategies.get(m));
      }
      if(mapping.getUndefinedAttributes().size() > datatypeStrategies.size()){
        JOptionPane error = new JOptionPane();
        int i=error.showOptionDialog(parent, "There are still attributes with unknown datatypes.\n\n"+
        "This may cause problems with generated SQL code.\nContinue?",
        "Warning",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
        null,null,null);
        if(i==JOptionPane.NO_OPTION) return;
      }


      gui.map();
    }
  }

  /**
   * ActionListener for the combobox deciding which kind of Modelelements
   * to display in the List, and which strategies to offer in another
   * choice accordingly
   * */
  private class ListFiller implements ActionListener{
    private JPanel panel, parent;

     /**
      * @param panel the JPanel for display of selected ListItems
      * @param parent the ChoiceTab using the ListFiller
      * */
    public ListFiller(JPanel panel, JPanel parent){
      this.panel = panel;
      this.parent = parent;
    }

    public void actionPerformed(ActionEvent e){
      StrategyManager manager = StrategyManager.getInstance();
      if(choice.getSelectedIndex() == 0 ) { //class Strategies
        Vector listData = new Vector(mapping.classifiers());
        list.setListData(listData);
        strategies.removeAllItems();
        Vector choices = new Vector(manager.getStrategies(ORMappingImpl.CLASS));
        for (int i=0; i<choices.size(); i++)
          strategies.addItem(((StrategyCreator) choices.get(i)).getStrategyDescription());
        list.removeListSelectionListener(current);
        current = new ClassViewer(panel, list, classStrategies);
        list.addListSelectionListener(current);
        Vector objects = new Vector();
        for(int i=0; i<listData.size(); i++)
          objects.add(mapping.getClassifier((String) listData.get(i)));
        set.removeActionListener(listener);
        listener = new Setter(objects, choices, classStrategies);
        set.addActionListener(listener);
      }
      if(choice.getSelectedIndex() == 1){ //key Strategies
        Vector listData = new Vector(mapping.classifiers());
        list.setListData(listData);
        strategies.removeAllItems();
        Vector choices = new Vector(manager.getStrategies(ORMappingImpl.PK));
        for (int i=0; i<choices.size(); i++)
          strategies.addItem(((StrategyCreator) choices.get(i)).getStrategyDescription());
        list.removeListSelectionListener(current);
        current = new ClassViewer(panel, list, keyStrategies);
        list.addListSelectionListener(current);
        Vector objects = new Vector();
        for(int i=0; i<listData.size(); i++)
          objects.add(mapping.getClassifier((String) listData.get(i)));
        set.removeActionListener(listener);
        listener = new Setter(objects, choices, keyStrategies);
        set.addActionListener(listener);
      }
      if(choice.getSelectedIndex() == 2){  // Inheritance Strategies
        Vector objects = new Vector(mapping.getGeneralizationRoots());
        Vector listData = new Vector();
        for(int i=0; i<objects.size(); i++){
          listData.add(((MClassifier) objects.get(i)).getName());
        }
        list.setListData(listData);
        strategies.removeAllItems();
        Vector choices = new Vector(manager.getStrategies(ORMappingImpl.INHERIT));
        for (int i=0; i<choices.size(); i++)
          strategies.addItem(((StrategyCreator) choices.get(i)).getStrategyDescription());
        list.removeListSelectionListener(current);
        current = new TreeViewer(panel, list);
        list.addListSelectionListener(current);
        set.removeActionListener(listener);
        listener = new Setter(objects, choices, inheritanceStrategies);
        set.addActionListener(listener);
      }
      if(choice.getSelectedIndex() == 3){  // 1:1 Association strategies
        Vector data = new Vector(mapping.getAssociations());
        Vector listData = new Vector();
        Vector objects = new Vector();
        for(int i=0; i<data.size(); i++){
          MAssociation a = (MAssociation) data.get(i);
          MAssociationEnd one = a.getConnection(0);
          if(one.getMultiplicity().getUpper() == 1 &&
            one.getOppositeEnd().getMultiplicity().getUpper() == 1){
            listData.add(a.getName());
            objects.add(a);
          }
        }
        list.setListData(listData);
        strategies.removeAllItems();
        Vector choices = new Vector(manager.getStrategies(ORMappingImpl.ONE_ONE));
        for (int i=0; i<choices.size(); i++)
          strategies.addItem(((StrategyCreator) choices.get(i)).getStrategyDescription());
        list.removeListSelectionListener(current);
        current = new AssociationViewer(panel, list, objects);
        list.addListSelectionListener(current);
        set.removeActionListener(listener);
        listener = new Setter(objects, choices, associationStrategies);
        set.addActionListener(listener);

      }
      if(choice.getSelectedIndex() == 4){  // 1:n Association strategies
        Vector data = new Vector(mapping.getAssociations());
        Vector listData = new Vector();
        Vector objects = new Vector();
        for(int i=0; i<data.size(); i++){
          MAssociation a = (MAssociation) data.get(i);
          MAssociationEnd one = a.getConnection(0);
          int mulOne = one.getMultiplicity().getUpper();
          int mulTwo = one.getOppositeEnd().getMultiplicity().getUpper();
          if((mulOne == 1 && (mulTwo > 1 || mulTwo <0)) ||
            (mulTwo == 1 && (mulOne > 1 || mulOne <0))){
            listData.add(a.getName());
            objects.add(a);
          }
        }
        list.setListData(listData);
        strategies.removeAllItems();
        Vector choices = new Vector(manager.getStrategies(ORMappingImpl.ONE_MANY));
        for (int i=0; i<choices.size(); i++)
          strategies.addItem(((StrategyCreator) choices.get(i)).getStrategyDescription());
        list.removeListSelectionListener(current);
        current = new AssociationViewer(panel, list, objects);
        list.addListSelectionListener(current);
        set.removeActionListener(listener);
        listener = new Setter(objects, choices, associationStrategies);
        set.addActionListener(listener);
      }
      if(choice.getSelectedIndex() == 5){  // n:m association strategies
        Vector data = new Vector(mapping.getAssociations());
        Vector listData = new Vector();
        Vector objects = new Vector();
        for(int i=0; i<data.size(); i++){
          MAssociation a = (MAssociation) data.get(i);
          MAssociationEnd one = a.getConnection(0);
          int mulOne = one.getMultiplicity().getUpper();
          int mulTwo = one.getOppositeEnd().getMultiplicity().getUpper();
          if((mulTwo > 1 || mulTwo <0) && (mulOne > 1 || mulOne <0)){
            listData.add(a.getName());
            objects.add(a);
          }
        }
        list.setListData(listData);
        strategies.removeAllItems();
        Vector choices = new Vector(manager.getStrategies(ORMappingImpl.MANY_MANY));
        for (int i=0; i<choices.size(); i++)
          strategies.addItem(((StrategyCreator) choices.get(i)).getStrategyDescription());
        list.removeListSelectionListener(current);
        current = new AssociationViewer(panel, list, objects);
        list.addListSelectionListener(current);
        set.removeActionListener(listener);
        listener = new Setter(objects, choices, associationStrategies);
        set.addActionListener(listener);
      }
      if(choice.getSelectedIndex() == 6){  // ordered association end strategies
        Vector data = new Vector(mapping.getAssociations());
        Vector listData = new Vector();
        Vector objects = new Vector();
        for(int i=0; i<data.size(); i++){
          MAssociation a = (MAssociation) data.get(i);
          MAssociationEnd one = a.getConnection(0);
          if(one.getOrdering() == MOrderingKind.ORDERED){
            listData.add(one.getName());
            objects.add(one);
          }
          if(one.getOppositeEnd().getOrdering() == MOrderingKind.ORDERED){
            listData.add(one.getOppositeEnd().getName());
            objects.add(one.getOppositeEnd());
          }
        }
        list.setListData(listData);
        strategies.removeAllItems();
        Vector choices = new Vector(manager.getStrategies(ORMappingImpl.ORDERED));
        for (int i=0; i<choices.size(); i++)
          strategies.addItem(((StrategyCreator) choices.get(i)).getStrategyDescription());
        list.removeListSelectionListener(current);
        current = new AssociationEndViewer(panel, list, objects);
        list.addListSelectionListener(current);
        set.removeActionListener(listener);
        listener = new Setter(objects, choices, orderStrategies);
        set.addActionListener(listener);
      }
      if(choice.getSelectedIndex() == 7){  // datatype strategies
         Vector attributes = new Vector(mapping.getUndefinedAttributes());
         Vector listData = new Vector();
         for(int i=0; i<attributes.size(); i++){
          MAttribute att = (MAttribute) attributes.get(i);
          listData.add(att.getOwner().getName()+"."+att.getName());
         }
         list.setListData(listData);
         strategies.removeAllItems();
         Vector choices = new Vector(manager.getStrategies(ORMappingImpl.TYPE));
         for (int i=0; i<choices.size(); i++){
           StrategyCreator create = (StrategyCreator) choices.get(i);
           try{
            ((DatatypeStrategyCreator)create).setAdditionalDatatypes(new Vector(mapping.classifiers()));
           }catch(Exception ex){}
           strategies.addItem(create.getStrategyDescription());
         }
         Iterator it = mapping.classifiers().iterator();
         while(it.hasNext()){
          String str = (String) it.next();
          choices.add(new ClassTypeMappingCreator(mapping.getClassifier(str)));
          strategies.addItem(str);
         }
         list.removeListSelectionListener(current);
         current = new AttributeViewer(panel, list, attributes);
         list.addListSelectionListener(current);
         set.removeActionListener(listener);
         listener = new Setter(attributes, choices, datatypeStrategies);
         set.addActionListener(listener);

      }
      parent.validate();
    }
  }

     /**
      * ActionListener for the Set strategyButton.
      * checks whether the strategyCreator has a View and displays it
      * if nessecary.
      * Sets the chosen Strategy
      * */
  private class Setter implements ActionListener{

    private Vector objects, strategyCreators;
    private Hashtable strategyMapper;

    /**
     * @param objects a Vector of MBase according to chooseable Strategies
     * @param strategyCreators a Vector of StrategyCreator according to the choice
     * @param strategyMapper stores the set Strategy (MBase -> Strategy) Strategy as
     *  created by chosen StrategyCreator
     * */
    public Setter(Vector objects, Vector strategyCreators, Hashtable strategyMapper){
      this.objects = objects;
      this.strategyCreators = strategyCreators;
      this.strategyMapper = strategyMapper;
    }

    public void actionPerformed(ActionEvent e){
      if(strategies.getSelectedIndex() == -1 || list.getSelectedIndex() == -1)
        return;
      StrategyCreator creator = (StrategyCreator)
        strategyCreators.get(strategies.getSelectedIndex());
      JComponent view = creator.getStrategyView((MBase)
        objects.get(list.getSelectedIndex()));
      if(view != null){
        JDialog dialog = new JDialog(new JFrame(), creator.getStrategyDescription(),
          true);
        dialog.getContentPane().add(view, BorderLayout.CENTER);
        dialog.setSize(600,400);
        dialog.setVisible(true);
        dialog.dispose();
      }
      Object strategy = creator.getStrategy();
      if(strategy == null)
        return;
      strategyMapper.put(objects.get(list.getSelectedIndex()), strategy);
      //mapping.setStrategy(objects.get(list.getSelectedIndex()), strategy);
      int i= list.getSelectedIndex();
      list.clearSelection();
      list.setSelectedIndex(i);

    }
  }

 /**
  * ListselectionListener for a choice of ordered associationEnds
  * Views an anssociation containing at least one ordered association end
  * in a panel headed with the name of the ordered associationend displayed
  * */
  private class AssociationEndViewer implements ListSelectionListener{
    private JPanel panel;
    private JList list;
    private Vector associationEnds;

     /**
      * @param panel the JPanel for display
      * @param list the List to get selected associationends from
      * @param associationEnds a Vector of MAssociationEnd corresponding to list
      * */
    public AssociationEndViewer(JPanel panel, JList list, Vector associationEnds){
      this.list = list;
      this.panel = panel;
      this.associationEnds = associationEnds;
    }

    public void valueChanged(ListSelectionEvent e) {
      if(list.getSelectedIndex() == -1) {
        panel.removeAll();
        panel.add(new JLabel(""), BorderLayout.CENTER);
        panel.validate();
        return;
      }
      MAssociationEnd one = (MAssociationEnd) associationEnds.get(list.getSelectedIndex());
      panel.removeAll();
      panel.setLayout(new BorderLayout(10,10));
      if(orderStrategies.get(one) == null){
        panel.add(new JLabel((String) list.getSelectedValue()), BorderLayout.NORTH);
      }else{
        panel.add(new JLabel((String) list.getSelectedValue() +
        " mapped " + orderStrategies.get(one)), BorderLayout.NORTH);
      }
      JPanel center=new AssociationPanel(one);
      panel.add(center, BorderLayout.CENTER);
      JPanel south = new JPanel(new FlowLayout());
      south.add(strategies);
      south.add(set);
      panel.add(south, BorderLayout.SOUTH);
      panel.validate();
      parent.validate();
    }
  }

    /**
     * ListSelectionListener for all kinds of assoiations to be displayed
     * */
  private class AssociationViewer implements ListSelectionListener{
    private JPanel panel;
    private JList list;
    private Vector associations;

    /**
     * @param panel the panel for display
     * @param list the JList to get selected associations from
     * @param associations a Vctor of MAssociation corresponding to list
     * */
    public AssociationViewer(JPanel panel, JList list, Vector associations){
      this.list = list;
      this.panel = panel;
      this.associations = associations;
    }

    public void valueChanged(ListSelectionEvent e) {
      if(list.getSelectedIndex() == -1) {
        panel.removeAll();
        panel.add(new JLabel(""), BorderLayout.CENTER);
        panel.validate();
        return;
      }
      MAssociation a = (MAssociation) associations.get(list.getSelectedIndex());
      MAssociationEnd one = a.getConnection(0);
      panel.removeAll();
      panel.setLayout(new BorderLayout(10,10));
      if(associationStrategies.get(a) == null){
        panel.add(new JLabel((String) list.getSelectedValue()), BorderLayout.NORTH);
      }else{
        panel.add(new JLabel((String) list.getSelectedValue() +
        " mapped " + associationStrategies.get(a)), BorderLayout.NORTH);
      }
      JPanel center=new AssociationPanel(one);
      panel.add(center, BorderLayout.CENTER);
      JPanel south = new JPanel(new FlowLayout());
      south.add(strategies);
      south.add(set);
      panel.add(south, BorderLayout.SOUTH);
      panel.validate();
      parent.validate();
    }
  }

  /**
   * JPanel containing the actual Association
   * contains a table for each associationend and rolenames,
   * association directions and multiplicities in between
   * */
  private class AssociationPanel extends JPanel{
    /**
     * @param one the 'left' of the Associationends of the association for display
     * */
    public AssociationPanel(MAssociationEnd one){
      super(new GridLayout(1,3,10,10));
      ClassTableConstructor constructor = new ClassTableConstructor();
      JTable jtable1 = constructor.construct((MClassifier) one.getType());
      JTable jtable2 = constructor.construct((MClassifier) one.getOppositeEnd().getType());
      JPanel center2 = new JPanel(new BorderLayout());
      JPanel c1 = new JPanel(new GridLayout(4,1));
      c1.add(new JLabel(one.getType().getName()+":"));
      c1.add(new JLabel(one.getName()));
      if(one.isNavigable()){
        c1.add(new JLabel(new ImageIcon("tudresden/ocl/images/sgleftArrow.gif"), JLabel.RIGHT));
      }else{
        c1.add(new JLabel(new ImageIcon("tudresden/ocl/images/sgline_k.gif")));
      }
      c1.add(new JLabel(one.getMultiplicity().toString()));
      JPanel c2 = new JPanel(new GridLayout(4,1));
      c2.add(new JLabel(""));
      c2.add(new JLabel(""));
      c2.add(new JLabel(new ImageIcon("tudresden/ocl/images/sgline.gif")));
      c2.add(new JLabel(""));
      JPanel c3 = new JPanel(new GridLayout(4,1));
      c3.add(new JLabel(one.getOppositeEnd().getType().getName()+":", JLabel.RIGHT));
      c3.add(new JLabel(one.getOppositeEnd().getName(), JLabel.RIGHT));
      if(one.getOppositeEnd().isNavigable()){
        c3.add(new JLabel(new ImageIcon("tudresden/ocl/images/sgrightArrow.gif"), JLabel.LEFT));
      }else{
        c3.add(new JLabel(new ImageIcon("tudresden/ocl/images/sgline_k.gif")));
      }
      c3.add(new JLabel(one.getOppositeEnd().getMultiplicity().toString(), JLabel.RIGHT));
      center2.add(c1, BorderLayout.WEST);
      center2.add(c2, BorderLayout.CENTER);
      center2.add(c3, BorderLayout.EAST);
      add(new JScrollPane(jtable1));
      add(center2);
      add(new JScrollPane(jtable2));
    }
  }

   /**
    * ListSelectionListener to View InheritanceTrees
    * uses a JTree for display
    * */
  private class TreeViewer implements ListSelectionListener{
    private JPanel panel;
    private JList list;

    /**
     * @param panel the JPanel for display
     * @param the List to get selected items from
     */
    public TreeViewer(JPanel panel, JList list){
      this.list = list;
      this.panel = panel;
    }

    public void valueChanged(ListSelectionEvent e) {
      if(list.getSelectedIndex() == -1) {
        panel.removeAll();
        panel.add(new JLabel(""), BorderLayout.CENTER);
        panel.validate();
        return;
      }
      final MClassifier m = mapping.getClassifier((String) list.getSelectedValue());
      JTree tree = new JTree(new TreeModel(){
        public Object getRoot(){
          return m.getName();
        }
        public Object getChild(Object parent, int index){
          List l = mapping.getClassifier((String) parent).getChildren();
          if(l.size() == 0){
            Collection dependencies = mapping.getClassifier((String) parent).getSupplierDependencies();
            if(dependencies.size() == 0) return null;
            Iterator it = dependencies.iterator();
            while(it.hasNext()){
              MDependency realize = (MDependency) it.next();
              if(realize instanceof MAbstraction && realize.getStereotype() != null)
                 if(realize.getStereotype().getName().toLowerCase().trim().equals("realize"))
                   l.addAll(realize.getClients());
            }
          }

          return ((MClassifier) l.get(index)).getName();
        }

        public int getChildCount(Object parent){
          List l = mapping.getClassifier((String) parent).getChildren();
          if(l.size() == 0){
            Collection dependencies = mapping.getClassifier((String) parent).getSupplierDependencies();
            if(dependencies.size() == 0) return 0;
            Iterator it = dependencies.iterator();
            while(it.hasNext()){
              MDependency realize = (MDependency) it.next();
              if(realize instanceof MAbstraction && realize.getStereotype() != null)
                 if(realize.getStereotype().getName().toLowerCase().trim().equals("realize"))
                   l.addAll(realize.getClients());
            }
          }
          return l.size();
        }
        public int getIndexOfChild(Object parent, Object child){
          List l = mapping.getClassifier((String) parent).getChildren();
          if(l.size() == 0){
            Collection dependencies = mapping.getClassifier((String) parent).getSupplierDependencies();
            if(dependencies.size() == 0) return -1;
            Iterator it = dependencies.iterator();
            while(it.hasNext()){
              MDependency realize = (MDependency) it.next();
              if(realize instanceof MAbstraction && realize.getStereotype() != null)
                 if(realize.getStereotype().getName().toLowerCase().trim().equals("realize"))
                   l.addAll(realize.getClients());
            }
          }
          return l.indexOf(mapping.getClassifier((String) child));
        }
        public boolean isLeaf(Object node){
          List l = mapping.getClassifier((String) node).getChildren();
          if(l.size() == 0){
            Collection dependencies = mapping.getClassifier((String) node).getSupplierDependencies();
            if(dependencies.size() == 0) return true;
            Iterator it = dependencies.iterator();
            while(it.hasNext()){
              MDependency realize = (MDependency) it.next();
              if(realize instanceof MAbstraction && realize.getStereotype() != null)
                 if(realize.getStereotype().getName().toLowerCase().trim().equals("realize"))
                   l.addAll(realize.getClients());
            }
          }
          if(l.size() == 0)
            return true;
          return false;
        }
        public void addTreeModelListener(TreeModelListener l){}
        public void removeTreeModelListener(TreeModelListener l){}
        public void valueForPathChanged(TreePath path, Object newValue){}

      });
      tree.setVisibleRowCount(20);
      tree.expandRow(20);
      tree.setLargeModel(true);
      tree.setShowsRootHandles(true);
      panel.removeAll();
      panel.setLayout(new BorderLayout(10,10));
      if(inheritanceStrategies.get(m) == null){
        panel.add(new JLabel((String) list.getSelectedValue()), BorderLayout.NORTH);
      }else{
        panel.add(new JLabel((String) list.getSelectedValue() +
        " mapped " + inheritanceStrategies.get(m)), BorderLayout.NORTH);
      }
      panel.add(new JScrollPane(tree), BorderLayout.CENTER);
      JPanel south = new JPanel(new FlowLayout());
      south.add(strategies);
      south.add(set);
      panel.add(south, BorderLayout.SOUTH);
      panel.validate();
      parent.validate();
    }
  }

   /**
    * basically a ClassViewer for undefined attribute types,
    * marks the selected attribute in ClassView
    * */
  private class AttributeViewer implements ListSelectionListener{
    private JPanel panel;
    private JList list;
    private Vector attributes;

    /**
     * @param panel the JPanel for display
     * @param list the List to get selected attributes from
     * @param attributes Vector of MAttribute corresponding to list
     * */
    public AttributeViewer(JPanel panel, JList list, Vector attributes){
      this.panel = panel;
      this.list = list;
      this.attributes = attributes;
    }

    public void valueChanged(ListSelectionEvent e) {
      if(list.getSelectedIndex() == -1) {
        panel.removeAll();
        panel.add(new JLabel(""), BorderLayout.CENTER);
        panel.validate();
        return;
      }
      final MAttribute attribute = (MAttribute) attributes.get(list.getSelectedIndex());
      JTable jtable = (new ClassTableConstructor()).construct(attribute.getOwner());
      int selectRow = 0;
      for(int i=0; i<jtable.getRowCount(); i++)
        if(((String) jtable.getValueAt(i,0)).equals(attribute.getName()))
          selectRow = i;
      jtable.changeSelection(selectRow,0,false,false);
      panel.removeAll();
      if(datatypeStrategies.get(attribute) == null){
        panel.add(new JLabel((String) list.getSelectedValue()), BorderLayout.NORTH);
      }else{
        panel.add(new JLabel((String) list.getSelectedValue() +
        " mapped " + datatypeStrategies.get(attribute)), BorderLayout.NORTH);
      }
      panel.add(new JScrollPane(jtable), BorderLayout.CENTER);
      JPanel south = new JPanel(new FlowLayout());
      south.add(strategies);
      south.add(set);
      panel.add(south, BorderLayout.SOUTH);
      panel.validate();
      parent.validate();
    }
  }

/**
 * ListSelectionListener viewing selected classes
 * */
  private class ClassViewer implements ListSelectionListener{
    private JPanel panel;
    private JList list;
    private Hashtable classStrategies;

   /**
    * @param panel the JPanel to use for display
    * @param list the List where the displayable class was selected
    * @param classStrategies the Hashtable to store selected strategies
    *  (MClassifier -> Strategy)
    * */
    public ClassViewer(JPanel panel, JList list, Hashtable classStrategies){
      this.list = list;
      this.panel = panel;
      this.classStrategies = classStrategies;
    }

    public void valueChanged(ListSelectionEvent e) {
      if(list.getSelectedIndex() == -1) {
        panel.removeAll();
        panel.add(new JLabel(""), BorderLayout.CENTER);
        panel.validate();
        return;
      }
      MClassifier m = mapping.getClassifier((String) list.getSelectedValue());
      JTable jtable = new ClassTableConstructor().construct(m);
      panel.removeAll();
      panel.setLayout(new BorderLayout(10,10));
      if(classStrategies.get(m) == null){
        panel.add(new JLabel((String) list.getSelectedValue()), BorderLayout.NORTH);
      }else{
        panel.add(new JLabel((String) list.getSelectedValue() +
        " mapped " + classStrategies.get(m)), BorderLayout.NORTH);
      }
      panel.add(new JScrollPane(jtable), BorderLayout.CENTER);
      JPanel south = new JPanel(new FlowLayout());
      south.add(strategies);
      south.add(set);
      panel.add(south, BorderLayout.SOUTH);
      panel.validate();
      parent.validate();
    }
  }

  /**
   * creates a JTable containing attribute names and types of MClassifier
   * */
  private class ClassTableConstructor{
    private Vector attributes;
    private Vector types;

    public ClassTableConstructor(){}

    public JTable construct(MClassifier m){
      attributes = new Vector();
      types = new Vector();
      Iterator it = m.getFeatures().iterator();
      while(it.hasNext()){
        MFeature attrib = (MFeature) it.next();
        if(attrib instanceof MAttribute){
          attributes.add(attrib.getName());
          types.add(((MAttribute)attrib).getType().getName());
        }
      }
      TableModel dataModel = new AbstractTableModel() {
          public int getColumnCount() { return 2; }
          public int getRowCount() { return types.size();}
          public String getColumnName(int col){
            if(col == 0)
              return "Attribute";
            return "Type";
          }
          public Object getValueAt(int row, int col) {
            if(col == 0){
              return attributes.get(row);
            }
            return types.get(row);
          }
      };
      return new JTable(dataModel);
    }
  }

}
