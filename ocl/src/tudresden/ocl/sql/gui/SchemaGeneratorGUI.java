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
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.Hashtable;
import java.util.Enumeration;
 import ru.novosoft.uml.model_management.MModel;
 import ru.novosoft.uml.xmi.XMIReader;
 import org.xml.sax.InputSource;
import tudresden.ocl.sql.*;

/**
 * a graphical user interface for cunstuction of SQL scripts from
 * object-relational mapping of a UML-model
 *
 * @see tudresden.ocl.sql.ORMappingImpl
 * @see tudresden.ocl.sql.SchemaGenerator
 * @author Andrea Kling
 */
public class SchemaGeneratorGUI extends JFrame{

  private Hashtable config;
  /**
   * key marking the working directory for loading xmi files
   */
  public final static String WORKING_DIR = "working dir";
  /**
   * key marking the directory used for saving SQL scripts
   */
  public final static String SAVING_DIR  = "saving dir";
  private JTabbedPane tabs;
  private JComponent init, classes, tables, output;
  private SchemaGenerator generator;
  private ORMappingImpl mapping;

  private MModel model;

  public SchemaGeneratorGUI(){
    super("Database Schema Generator");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //read saved configuration
    config = new Hashtable();
    try{
        BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("config.dat")));
        if(r != null)
            try{
              String line="";
              while((line = r.readLine())!=null){
                int i = line.indexOf('=');
                if(i > 0 && i+1<line.length()){
                  String prop = line.substring(0, i-1).trim().toLowerCase();
                  String value = line.substring(i+1).trim();
                  config.put(prop, value);
                }
              }
            }finally{
                r.close();
            }
    }
    catch (Exception e){
        System.out.println("Exception while opening init file");
    }
    /*JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("File");
    menuBar.add(menu);
    setJMenuBar(menuBar);
     */
    tabs = new JTabbedPane();
    init = new InitTab(this);
    tabs.addTab("Default Settings", init);
    classes = new ChoiceTab(this);
    tabs.addTab("Special Settings", classes);
    tables = new JPanel(new BorderLayout());
    tabs.addTab("Tables",tables);
    output = new OutputTab(config);
    tabs.addTab("Output", output);
    tabs.setEnabledAt(1,false);
    tabs.setEnabledAt(2,false);
    tabs.setEnabledAt(3,false);
    getContentPane().add(tabs);
    addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent e){
        config.putAll(((InitTab) init).getChosenEntries());
        config.put("database", ((SQLBuilder) ((InitTab) init).getSQLBuilder()).getDescription());
        StringBuffer configuration = new StringBuffer();
        Enumeration keys = config.keys();
        while(keys.hasMoreElements()){
          String key = (String) keys.nextElement();
          configuration.append(key);
          configuration.append(" = ");
          configuration.append((String) config.get(key));
          configuration.append("\n");
        }
        try{
          FileOutputStream out = new FileOutputStream("config.dat");
          out.write(configuration.toString().getBytes());
          out.flush();
          out.close();
        }catch(Exception se){
          se.printStackTrace();
        }
        System.exit(0);
      }
    });


  }

  /**
   * sets the initial ORMappingImpl containing the default mapping
   * Strategies and the UML model
   * causes the GUI the switch to Tab 2 (allowing special strategies to be set)
   */

  public void setLoaded(ORMappingImpl mapping){
    this.mapping = mapping;
    ((ChoiceTab) classes).activate(mapping);
    tabs.setEnabledAt(1,true);
    tabs.setEnabledAt(2,false);
    tabs.setEnabledAt(3,false);
    tabs.setSelectedIndex(1);
  }

   /**
    * causes the ORMappingImpl set tho start the object relational
    * mapping process and then switches the GUI to the next tab
    * to view generated Tables
    *
    * @see tudresden.ocl.sql.ORMappingImpl
    */
  public void map(){
    mapping.setDefaultStrategies(((InitTab)init).getChoices());
    mapping.map();
    tables.removeAll();
    tables.add(new TableTab(mapping, this), BorderLayout.CENTER);
    tables.validate();
    tabs.setEnabledAt(2, true);
    tabs.setSelectedIndex(2);
  }

   /**
    * causes the SchemaGenerator to construct an SQL script out of
    * the data provided by the ORMappingImpl after the SQL DDL
    * provided by the SQLBuilder chosen in InitTab
    * afterwards switches to the last Tab to view the script
    *
    * @see tudresden.ocl.sql.SQLBuilder
    * @see InitTab
    */
  public void construct(){
    SQLBuilder builder = ((InitTab) init).getSQLBuilder();
    if(builder == null){
      JOptionPane error = new JOptionPane();
      error.showMessageDialog(this, "No destination database system was defined."
      ,"Error",JOptionPane.ERROR_MESSAGE);
      return;
    }
    generator = new SchemaGenerator(mapping, builder);
    generator.construct();
    ((OutputTab) output).setText(generator.getCode());
    tabs.setEnabledAt(3,true);
    tabs.setSelectedIndex(3);
  }

  /**
   * returns the configration hashtable containing data according to
   * Keys defined in SchemaGeneratorGUI
   */
  public Hashtable getConfig(){
    return config;
  }
}
