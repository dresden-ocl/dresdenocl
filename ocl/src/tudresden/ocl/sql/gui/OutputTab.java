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
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.border.EmptyBorder;
import java.util.Hashtable;
import java.util.Enumeration;

/**
 * Panel used for noneditable text-output of sql-scripts
 *
 * @see SchemaGeneratorGUI
 * @author Andrea Kling
 */
public class OutputTab extends JPanel{

  private JTextArea script;
  private JButton save;
  private final Hashtable config;
  private JPanel panel;

  /**
   * @param confi contains String arguments for
   * configuration data specified in SchemaGeneratorGUI (String -> String)
   */
  public OutputTab(Hashtable confi){
    super(new BorderLayout(0,10));
    this.config = confi;
    setBorder(new EmptyBorder(20,20,20,20));
    script = new JTextArea();
    script.setEditable(false);
    add(new JScrollPane(script), BorderLayout.CENTER);
    save = new JButton("Save");
    save.addActionListener(new Saver(config, save));
    JPanel south = new JPanel(new BorderLayout());
    south.add(save, BorderLayout.EAST);
    add(south, BorderLayout.SOUTH);
    panel = this;
  }

  /**
   * sets the Text shown in OutputTab TextArea
   */
  public void setText(String text){
    script.setText(text);
    save.setEnabled(true);
  }

  /**
   * actionListener for the 'save' Button,
   * saves the text in outputtab, sugessts the directory last used
   * for saving, end a '.sql' file suffix
   */
  private class Saver implements ActionListener{
    private Hashtable config;
    private JButton button;

    public Saver(Hashtable config, JButton button){
      this.config = config;
      this.button = button;
    }

    public void actionPerformed(ActionEvent e){
      JFileChooser chooser;
      if(config.get(SchemaGeneratorGUI.SAVING_DIR) == null){
       chooser = new JFileChooser((String) config.get(SchemaGeneratorGUI.WORKING_DIR));
      }else{
        chooser = new JFileChooser((String) config.get(SchemaGeneratorGUI.SAVING_DIR));
      }
      chooser.setFileFilter(new javax.swing.filechooser.FileFilter(){
        public boolean accept(File file){
          if(file.getName().toLowerCase().endsWith(".sql") ||
          file.isDirectory())
            return true;
          return false;
        }
        public String getDescription(){
          return "SQL scripts (.sql)";
        }
      });

      chooser.showSaveDialog(new JFrame());
      if(chooser.getSelectedFile() != null){
        String str = chooser.getSelectedFile().getPath();
        config.put(SchemaGeneratorGUI.SAVING_DIR, str);
        try{
          new FileInputStream(str);
          JOptionPane error = new JOptionPane();
          int i=error.showOptionDialog(panel, "File already exists.\n"+
          "Overwrite?",
          "Warning",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
          null,null,null);
          if(i==JOptionPane.NO_OPTION) return;
        }catch(Exception x){}
        try{
          FileOutputStream out = new FileOutputStream(str);
          out.write(script.getText().getBytes());
          out.flush();
          out.close();
          button.setEnabled(false);
        }catch(Exception ex){
          JOptionPane error = new JOptionPane();
          error.showMessageDialog(null, "Failed to save script.\nPlease try again."
          ,"Error",JOptionPane.ERROR_MESSAGE);
          ex.printStackTrace();
        }
      }
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
    }
  }

}

