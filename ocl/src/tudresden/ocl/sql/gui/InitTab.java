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

import javax.swing.*;
import java.util.Hashtable;
import java.util.Vector;
import java.util.Iterator;
import java.util.Map;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.border.EmptyBorder;
import tudresden.ocl.sql.*;
//import tudresden.ocl.sql.OracleSQLBuilder;
 import ru.novosoft.uml.model_management.MModel;
 import ru.novosoft.uml.xmi.XMIReader;
 import org.xml.sax.InputSource;

/**
 * a Panel used for basic settings for an object-relational mapping
 * with ORMappingImpl. Default Strategies can be chosen here. the
 * choice is based on StrategyCreators provided by the StrategyManager
 * SchemaGeneratorGUI contains this panel as its first tab
 *
 * @see tudresden.ocl.sql.ORMappingImpl
 * @see SchemaGeneratorGUI
 * @see StrategyManager
 *
 * @author Andrea Kling
 * */
public class InitTab extends JPanel{

  private SchemaGeneratorGUI parent;
  private JComboBox classes, keys, inheritance, oneOne, oneMany,
  manyMany, ordered, dbs;
  private Hashtable classStrategies, keyStrategies, inheritanceStrategies,
  oneOneStrategies, oneManyStrategies, manyManyStrategies,
  orderedStrategies, builders;
  private JTextField modelPath;
  private final Hashtable config;
  private StrategyManager strategies;

   /**
    * @param parent the SchemaGeneratorGUI containig the tab
    * */
  public InitTab(SchemaGeneratorGUI parent){
    super(new GridLayout(13,2,10,10));
    setBorder(new EmptyBorder(20,20,20,20));
    this.parent = parent;
    this.config = parent.getConfig();
    strategies = StrategyManager.getInstance();
    add(new JLabel("Object Relational Mapping Strategies"));
    add(new JLabel(""));
    add(new JLabel("Classes"));
    classes=new JComboBox();
    classes.setEditable(false);
    add(classes);
    add(new JLabel("Keys"));
    keys=new JComboBox();
    keys.setEditable(false);
    add(keys);
    add(new JLabel("Inheritance"));
    inheritance=new JComboBox();
    inheritance.setEditable(false);
    add(inheritance);
    add(new JLabel("1:1 Associations"));
    oneOne=new JComboBox();
    oneOne.setEditable(false);
    add(oneOne);
    add(new JLabel("1:n Associations"));
    oneMany=new JComboBox();
    oneMany.setEditable(false);
    add(oneMany);
    add(new JLabel("n:m Associations"));
    manyMany=new JComboBox();
    manyMany.setEditable(false);
    add(manyMany);
    add(new JLabel("Ordered Associationends"));
    ordered=new JComboBox();
    ordered.setEditable(false);
    add(ordered);
    add(new JLabel(""));
    add(new JLabel(""));
    add(new JLabel("Destination Database System"));
    dbs = new JComboBox();
    dbs.setEditable(false);
    add(dbs);
    fillChoices();
    add(new JLabel(""));
    add(new JLabel(""));
    add(new JLabel("UML Model"));
    modelPath = new JTextField();
    final String dir=(String) config.get(SchemaGeneratorGUI.WORKING_DIR);
    if(dir != null)
      modelPath.setText(dir);
    JButton choose = new JButton("...");
    choose.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        JFileChooser chooser;
        if(config.get(SchemaGeneratorGUI.WORKING_DIR) == null){
         chooser = new JFileChooser();
        }else{
          chooser = new JFileChooser((String) config.get(SchemaGeneratorGUI.WORKING_DIR));
        }
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter(){
          public boolean accept(File file){
            if(file.getName().toLowerCase().endsWith(".xmi") ||
            file.isDirectory() || file.getName().toLowerCase().endsWith(".xml"))
              return true;
            return false;
          }
          public String getDescription(){
            return "XMI Files (.xmi)";
          }
        });
        chooser.showOpenDialog(new JFrame());
        if(chooser.getSelectedFile() != null){
          String str = chooser.getSelectedFile().getPath();
          config.put(SchemaGeneratorGUI.WORKING_DIR, str);
          modelPath.setText(str);
        }
      }
    });
    JPanel modelChooser = new JPanel(new BorderLayout());
    modelChooser.add(modelPath, BorderLayout.CENTER);
    modelChooser.add(choose, BorderLayout.EAST);
    add(modelChooser);

    add(new JLabel(""));
    JButton load = new JButton("Load");
    load.addActionListener(new Loader(parent));
    JPanel j = new JPanel(new BorderLayout());
    j.add(load, BorderLayout.EAST);
    add(j);


  }

  /*
   * returns a Map containing the chosen entries for every StrategyKind
   * as defined in ORMappingImpl
   * Was to be used for restoring choices
   *
   * @return a Map containing a Strategydescription as used in the choices
   * for every strategy-kind defined in ORMappingImpl
   *
   * */
  public Map getChosenEntries(){
    Hashtable settings = new Hashtable();
    if(classes.getSelectedItem() != null)
      settings.put(ORMappingImpl.CLASS, classes.getSelectedItem());
    if(keys.getSelectedItem() != null)
      settings.put(ORMappingImpl.PK, keys.getSelectedItem());
    if(inheritance.getSelectedItem() != null)
      settings.put(ORMappingImpl.INHERIT, inheritance.getSelectedItem());
    if(oneOne.getSelectedItem() != null)
      settings.put(ORMappingImpl.ONE_ONE, oneOne.getSelectedItem());
    if(oneMany.getSelectedItem() != null)
      settings.put(ORMappingImpl.ONE_MANY, oneMany.getSelectedItem());
    if(manyMany.getSelectedItem() != null)
      settings.put(ORMappingImpl.MANY_MANY, manyMany.getSelectedItem());
    if(ordered.getSelectedItem() != null)
      settings.put(ORMappingImpl.ORDERED, ordered.getSelectedItem());
    return settings;
  }
  /**
   * returns a Map suitable to be set as defaultStrategies in
   * ORMappingImpl
   *
   * @return a Map containing a strategy for every strategy-kind defined
   * in ORMappingImpl
   *
   * @see tudresden.ocl.sql.ORMappingImpl
   * @see tudresden.ocl.sql.orstrategy.ClassStrategy
   * @see tudresden.ocl.sql.orstrategy.KeyStrategy
   * @see tudresden.ocl.sql.orstrategy.DatatypeStrategy
   * @see tudresden.ocl.sql.orstrategy.InheritanceStrategy
   * @see tudresden.ocl.sql.orstrategy.OrderedStrategy
   * @see tudresden.ocl.sql.orstrategy.AssociationStrategy
   */

  public Map getChoices(){
    Hashtable settings = new Hashtable();
    if(classes.getSelectedItem() != null)
      settings.put(ORMappingImpl.CLASS, classStrategies.get(classes.getSelectedItem()));
    if(keys.getSelectedItem() != null)
      settings.put(ORMappingImpl.PK, keyStrategies.get(keys.getSelectedItem()));
    if(inheritance.getSelectedItem() != null)
      settings.put(ORMappingImpl.INHERIT, inheritanceStrategies.get(inheritance.getSelectedItem()));
    if(oneOne.getSelectedItem() != null)
      settings.put(ORMappingImpl.ONE_ONE, oneOneStrategies.get(oneOne.getSelectedItem()));
    if(oneMany.getSelectedItem() != null)
      settings.put(ORMappingImpl.ONE_MANY, oneManyStrategies.get(oneMany.getSelectedItem()));
    if(manyMany.getSelectedItem() != null)
      settings.put(ORMappingImpl.MANY_MANY, manyManyStrategies.get(manyMany.getSelectedItem()));
    if(ordered.getSelectedItem() != null)
      settings.put(ORMappingImpl.ORDERED, orderedStrategies.get(ordered.getSelectedItem()));
    return settings;
  }

   /**
    * returns the chosen SQLBuilder
    * */
  public SQLBuilder getSQLBuilder(){
    SQLBuilder builder = null;
    if(dbs.getSelectedItem() != null){
      builder = (SQLBuilder) builders.get(dbs.getSelectedItem());
    }
    return builder;
  }


  /**
   * fills the choices with data provided by the StrategyManager.
   * this also includes available database DDLs
   *
   * @see StrategyManager
   * */
  private void fillChoices(){
    classStrategies = new Hashtable();
    Iterator it = strategies.getStrategies(ORMappingImpl.CLASS).iterator();
    while(it.hasNext()){
      StrategyCreator strat = (StrategyCreator) it.next();
      if(strat.getStrategyView(null) == null){
        classes.addItem(strat.getStrategyDescription());
        classStrategies.put(strat.getStrategyDescription(), strat.getStrategy());
      }
    }
    keyStrategies = new Hashtable();
    it = strategies.getStrategies(ORMappingImpl.PK).iterator();
    while(it.hasNext()){
      StrategyCreator strat = (StrategyCreator) it.next();
      if(strat.getStrategyView(null) == null){
        keys.addItem(strat.getStrategyDescription());
        keyStrategies.put(strat.getStrategyDescription(), strat.getStrategy());
      }
    }
    inheritanceStrategies = new Hashtable();
    it = strategies.getStrategies(ORMappingImpl.INHERIT).iterator();
    while(it.hasNext()){
      StrategyCreator strat = (StrategyCreator) it.next();
      if(strat.getStrategyView(null) == null){
        String desc = strat.getStrategyDescription();
        inheritance.addItem(desc);
        inheritanceStrategies.put(strat.getStrategyDescription(), strat.getStrategy());
      }
    }
    oneOneStrategies = new Hashtable();
    it = strategies.getStrategies(ORMappingImpl.ONE_ONE).iterator();
    while(it.hasNext()){
      StrategyCreator strat = (StrategyCreator) it.next();
      if(strat.getStrategyView(null) == null){
        oneOne.addItem(strat.getStrategyDescription());
        oneOneStrategies.put(strat.getStrategyDescription(), strat.getStrategy());
      }
    }
    oneManyStrategies = new Hashtable();
    it = strategies.getStrategies(ORMappingImpl.ONE_MANY).iterator();
    while(it.hasNext()){
      StrategyCreator strat = (StrategyCreator) it.next();
      if(strat.getStrategyView(null) == null){
        oneMany.addItem(strat.getStrategyDescription());
        oneManyStrategies.put(strat.getStrategyDescription(), strat.getStrategy());
      }
    }
    manyManyStrategies = new Hashtable();
    it = strategies.getStrategies(ORMappingImpl.MANY_MANY).iterator();
    while(it.hasNext()){
      StrategyCreator strat = (StrategyCreator) it.next();
      if(strat.getStrategyView(null) == null){
        manyMany.addItem(strat.getStrategyDescription());
        manyManyStrategies.put(strat.getStrategyDescription(), strat.getStrategy());
      }
    }
    orderedStrategies = new Hashtable();
    it = strategies.getStrategies(ORMappingImpl.ORDERED).iterator();
    while(it.hasNext()){
      StrategyCreator strat = (StrategyCreator) it.next();
      if(strat.getStrategyView(null) == null){
        ordered.addItem(strat.getStrategyDescription());
        orderedStrategies.put(strat.getStrategyDescription(), strat.getStrategy());
      }
    }
    builders = new Hashtable();
    it = strategies.getSQLBuilders().iterator();
    while(it.hasNext()){
      SQLBuilder builder = (SQLBuilder) it.next();
      dbs.addItem(builder.getDescription());
      builders.put(builder.getDescription(), builder);
    }

  }

   /**
    * Initiates the Loading of the chosen xmi-file and causes
    * the SchemaGeneratorGUI parent to switch to the next tab
    * */
  private class Loader implements ActionListener{
    private SchemaGeneratorGUI parent;
    public Loader(SchemaGeneratorGUI parent){
      this.parent = parent;
    }

    public void actionPerformed(ActionEvent e){
      if(modelPath.getText().toLowerCase().endsWith(".xmi")
       || modelPath.getText().toLowerCase().endsWith(".xml")){
        JFrame win = new JFrame("Loading..");
        win.getContentPane().add(new Label("Please wait while model is being loaded."));
        win.setSize(250, 100);
        win.setLocation(200,200);
        win.setVisible(true);
        try{
          MModel model = new XMIReader().parse(new InputSource(
            new FileInputStream(modelPath.getText())));
          if(model == null) throw new NullPointerException("Could not load model");
          ORMappingImpl mapping = new ORMappingImpl(model, new ModelAdjuster());
          mapping.setDefaultStrategies(getChoices());
          parent.setLoaded(mapping);

          //win.setVisible(false);
        }catch (Exception se) {
          if(se instanceof FileNotFoundException){
            JOptionPane error = new JOptionPane();
            error.showMessageDialog(parent, "File not found.\nPlease choose a valid .xmi file."
            ,"Error",JOptionPane.ERROR_MESSAGE);
          }else{
            JOptionPane error = new JOptionPane();
            error.showMessageDialog(parent, "Could not read file.\nPlease choose a valid .xmi file."
            ,"Error",JOptionPane.ERROR_MESSAGE);
          }
          //se.printStackTrace();
          //win.setVisible(false);
        }
        win.dispose();
      }else{
        JOptionPane error = new JOptionPane();
        error.showMessageDialog(parent,"Please choose a valid .xmi file.","Error",JOptionPane.ERROR_MESSAGE);
        //error.setVisible(true);
      }
    }
  }
}
