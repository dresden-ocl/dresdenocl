/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL Compiler                                                      *
 * Copyright (C) 1999, 2000 Frank Finger (frank@finger.org).         *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a diploma project at the Chair for Software *
 * Technology, Dresden University Of Technology, Germany             *
 * (http://www-st.inf.tu-dresden.de).  It is understood that any     *
 * modification not identified as such is not covered by the         *
 * preceding statement.                                              *
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
 * To submit a bug report, send a comment, or get the latest news on *
 * this project and other projects, please visit the web site:       *
 * http://www-st.inf.tu-dresden.de/ (Chair home page) or             *
 * http://www-st.inf.tu-dresden.de/ocl/ (project home page)          *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package tudresden.ocl;

import tudresden.ocl.parser.parser.*;
import tudresden.ocl.parser.lexer.*;
import tudresden.ocl.parser.node.*;
import tudresden.ocl.parser.OclParserException;
import tudresden.ocl.normalize.*;
import tudresden.ocl.check.TypeQueryable;
import tudresden.ocl.check.OclTypeException;
import tudresden.ocl.codegen.*;
import java.io.*;

import java.awt.*;
import java.awt.datatransfer.*;
import javax.swing.*;
import javax.swing.tree.*;
import java.lang.reflect.*;

public class ASTViewer extends JPanel
      implements java.awt.event.ActionListener,  ClipboardOwner{

  JTextArea text;
  JScrollPane scroll;
  JTree tree;
  JLabel message;

  OclTree oclTree;
  JButton parse, toText, normalize, print, fonts;
  JButton getClipboard, setClipboard;
  JButton generate;
  JButton generatedTests;

  JPanel panel, buttons, p, clipButtons;
  JRadioButton rbTest, rbReflection, rbXmi, rbSable;

  FontDialog fontDialog;

  static int counter=0;

  public ASTViewer(boolean isNotAnApplet) {
    text=new JTextArea(getDefaultText(), 4, 35);
    text.setFont(new Font("Monospaced", Font.PLAIN, 12));
    message=new JLabel(" ");

    parse=new JButton("Parse");
    toText=new JButton("To Text");
    normalize=new JButton("Normalize");
    fonts=new JButton("Change Font");
    if (isNotAnApplet) {
      print=new JButton("Create Text File");
      generate=new JButton("Generate Java");
      generatedTests=new JButton("Tests");
      generatedTests.setToolTipText("evaluate generated tests");
      getClipboard=new JButton("->");
      setClipboard=new JButton("<-");
      getClipboard.setToolTipText("copy OCL constraint from clipboard");
      setClipboard.setToolTipText("copy OCL constraint to clipboard");
      rbTest=new JRadioButton("T", true);
      rbReflection=new JRadioButton("R");
      rbXmi=new JRadioButton("X");
      rbSable=new JRadioButton("S");
      rbTest.setToolTipText("use TestModelFacade to query model information");
      rbReflection.setToolTipText("use ReflectionFacade to query model information");
      rbXmi.setToolTipText("use XmiFacade to query model information");
      rbSable.setToolTipText("use SableReflectionFacade to query model information");
      ButtonGroup radios=new ButtonGroup();
      radios.add(rbTest);
      radios.add(rbReflection);
      radios.add(rbXmi);
      radios.add(rbSable);
    }
    parse.addActionListener(this);
    parse.setMnemonic('p');
    normalize.addActionListener(this);
    toText.addActionListener(this);
    fonts.addActionListener(this);
    if (isNotAnApplet) {
      print.addActionListener(this);
      generate.addActionListener(this);
      generatedTests.addActionListener(this);
      getClipboard.addActionListener(this);
      setClipboard.addActionListener(this);
      getClipboard.setFont(new Font("Monospaced", Font.PLAIN, 12));
      setClipboard.setFont(new Font("Monospaced", Font.PLAIN, 12));
    }

    panel=new JPanel();
    panel.setLayout(new BorderLayout());
    p=new JPanel();
    buttons=new JPanel();
    if (isNotAnApplet) {
      clipButtons=new JPanel();
      clipButtons.setLayout(new BoxLayout(clipButtons, BoxLayout.Y_AXIS));
      clipButtons.add(getClipboard);
      clipButtons.add(setClipboard);
      JPanel radioPanel=new JPanel(new GridLayout(2, 2));
      radioPanel.add(rbTest);
      radioPanel.add(rbReflection);
      radioPanel.add(rbXmi);
      radioPanel.add(rbSable);
      clipButtons.add(radioPanel);
      Panel p2=new Panel();
      p2.add(clipButtons);
      panel.add(p2, BorderLayout.WEST);
      buttons.setLayout(new GridLayout(4, 2));
    } else {
      buttons.setLayout(new GridLayout(2, 2));
    }
    panel.add(
      new JScrollPane(
        text,
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
      )
    );
    buttons.add(parse);
    buttons.add(toText);
    buttons.add(normalize);
    buttons.add(fonts);
    if (isNotAnApplet) {
      buttons.add(print);
      buttons.add(generate);
      buttons.add(generatedTests);
    }
    p.add(buttons);
    panel.add(p, BorderLayout.EAST);

    tree=new JTree(new DefaultMutableTreeNode());
    tree.setFont(new Font("Monospaced", Font.PLAIN, 12));
    scroll=new JScrollPane(tree);

    setLayout(new BorderLayout());
    add(panel, BorderLayout.NORTH);
    add(scroll);
    add(message, BorderLayout.SOUTH);
  }

  public Dimension getPreferredSize() {
    return new Dimension(600, 400);
  }

  public void doParse() {
    try {
      message.setText("parsing");
      tudresden.ocl.check.types.ModelFacade mf;
      if (rbReflection!=null && rbReflection.isSelected()) {
        String[] packages={"tudresden.ocl.test.royloy", "tudresden.ocl.parser.node"};
        mf=new tudresden.ocl.check.types.ReflectionFacade(
          packages,
          new tudresden.ocl.check.types.DefaultReflectionAdapter(),
          new tudresden.ocl.lib.ArgoNameAdapter()
        );
      } else if (rbXmi!=null && rbXmi.isSelected()) {
        String xmifile=getClass().getResource("test/xmi/royloy.xmi").getFile();
        mf=tudresden.ocl.check.types.xmifacade.XmiParser.getModel(xmifile, xmifile);
      } else if (rbSable!=null && rbSable.isSelected()) {
        String[] packages={"tudresden.ocl.parser.node", "tudresden.ocl.test.royloy"};
        mf=new tudresden.ocl.check.bootstrap.SableReflectionFacade(
          packages,
          new tudresden.ocl.check.types.DefaultReflectionAdapter(),
          new tudresden.ocl.check.bootstrap.SableNameAdapter()
        );
      } else {
        mf=new tudresden.ocl.check.types.testfacade.TestModelFacade();
      }
      oclTree=OclTree.createTree(text.getText(), mf);
      if (oclTree.getRoot()!=null) {
        displayTree(oclTree.getRoot(), oclTree);
      }
    } catch (OclParserException e) {
      showMessage(e);
      e.printStackTrace(System.out);
      doLex();
    } catch (Exception e) {
      showMessage(e);
      e.printStackTrace(System.out);
    }
  }

  public void doLex() {
    Lexer lexer=new Lexer(
      new PushbackReader( new StringReader(text.getText()) )
    );
    Token t;
    DefaultMutableTreeNode dtm=new DefaultMutableTreeNode("lexer output");
    try {
      do {
        t=lexer.next();
        String text=t.getClass().getName()+" "+t.toString();
        dtm.add(new DefaultMutableTreeNode(text));
      } while (! (t instanceof EOF) );
    }
    catch (Exception e) {}
    tree.setModel(
      new DefaultTreeModel(dtm)
    );
  }

  public void doNormalize() {
    try {
      if (oclTree!=null) {
        oclTree.applyDefaultNormalizations();
        displayTree(oclTree.getRoot(), oclTree);
      } else {
        message.setText("parse first!");
      }
    } catch (Exception e) {
      message.setText("error while normalizing: "+e.getMessage());
      e.printStackTrace(System.out);
    }
  }

  public void createTextFile() {
    if (oclTree!=null) {
      try {
        ASTTextGenerator textGen=new ASTTextGenerator();
        oclTree.apply(textGen);

        FileWriter fw=new FileWriter("d:\\temp\\ocl.txt");
        fw.write(textGen.getText());
        fw.close();
      }
      catch (IOException e) {
        message.setText("error writing to file: "+e.getMessage());
      }
    }
  }

  public void doFonts() {
    if (fontDialog==null) {
      fontDialog=new FontDialog();
      fontDialog.setVisible(true);
    }
  }

  public void doGetClipboard() {
    Clipboard cb=Toolkit.getDefaultToolkit().getSystemClipboard();
    try {
      String content=
        (String)cb.getContents(this).getTransferData(DataFlavor.stringFlavor);
      text.setText(content);
    }
    catch (Exception e) {
      message.setText("error while reading clipboard: "+e.getMessage());
    }
  }

  public void doSetClipboard() {
    Clipboard cb=Toolkit.getDefaultToolkit().getSystemClipboard();
    try {
      String content=text.getText();
      if (content.length()>0) {
        Transferable xfer=new StringSelection(content);
        cb.setContents(xfer, this);
      }
    }
    catch (Exception e) {
      message.setText("error while reading clipboard: "+e.getMessage());
    }
  }

  public void lostOwnership(Clipboard cb, Transferable content) {
  }

  public void doGenerateJava() {
    try {
      System.out.print("generating Java source code ...");
      CodeFragment[] frags=oclTree.getCode(new JavaCodeGenerator());
      System.out.println(" created "+frags.length+" code fragment(s)");
      for (int i=0; i<frags.length; i++) {
        JDialog jd=new JDialog();
        JTextArea ta=new JTextArea(frags[i].getCode());
        ta.setFont(new Font("Monospaced", Font.PLAIN, 12));
        jd.setContentPane(new JScrollPane(ta));
        jd.setSize(400, 200);
        jd.setVisible(true);
      }
    } catch (Exception e) {
      e.printStackTrace(System.out);
      System.err.println("generation failed: "+e.getMessage());
    }
  }

  public void doGeneratedTests() {
    try {
      oclTree.applyGeneratedTests();
    }
    catch (Exception e) {
      e.printStackTrace(System.out);
      showMessage( e );
    }
  }

  protected void displayTree(Start root, TypeQueryable tq) {
    Visualizer vis=new Visualizer(true, tq);
    root.apply(vis);
    if (tree==null) {
      tree=new JTree( vis.getRootNode() );
    } else {
      TreeModel model=new DefaultTreeModel(vis.getRootNode());
      tree.setModel( model );
      makeVisible(tree, model, vis.getRootNode());
      showMessage( vis.getErrorMessage() );
    }
  }

  protected void makeVisible(JTree tree, TreeModel model, Object node) {
    for (int i=0; i<tree.getRowCount(); i++) {
      tree.expandRow(i);
    }
  }

  protected void showMessage(Exception e) {
    String s=e.getMessage();
    if (s==null) {
      message.setText(e.getClass().getName()+" without message");
      return;
    } else {
      showMessage(s);
    }
    if (e instanceof ParserException) {
      ParserException pe=(ParserException)e;
      String index=s.substring( s.indexOf(",")+1, s.indexOf("]") );
      int ix=Integer.parseInt(index);
      text.setCaretPosition(ix);
      text.setSelectionStart(ix);
      text.setSelectionEnd(ix+1);
    }
  }

  protected void showMessage(String s) {
    if (s==null || s.equals("")) {
      message.setText(" ");
    } else {
      StringBuffer sb=new StringBuffer();
      for (int i=0; i<s.length(); i++) {
        char c=s.charAt(i);
        if (! Character.isISOControl(c) ) {
          sb.append(c);
        }
      }
      message.setText(sb.toString());
    }
  }

  public void actionPerformed(java.awt.event.ActionEvent ae) {
    if (ae.getActionCommand().equals("Parse")) {
      doParse();
    }
    else if (ae.getActionCommand().equals("Normalize")) {
      doNormalize();
    }
    else if (ae.getActionCommand().equals("To Text")) {
      if (oclTree!=null) {
        text.setText( oclTree.getExpression() );
      } else {
        message.setText("parse first!");
      }
    }
    else if (ae.getActionCommand().equals("Create Text File")) {
      createTextFile();
    }
    else if (ae.getActionCommand().equals("Change Font")) {
      doFonts();
    } else if (ae.getActionCommand().equals("->")) {
      doGetClipboard();
    } else if (ae.getActionCommand().equals("<-")) {
      doSetClipboard();
    } else if (ae.getActionCommand().equals("Generate Java")) {
      doGenerateJava();
    } else if (ae.getActionCommand().equals("Tests")) {
      doGeneratedTests();
    }
  }

  protected String getDefaultText() {
    return "context Company\ninv: employees->forAll(age>15)";
  }

  public void setBackground(Color c) {
    super.setBackground(c);
    if (panel!=null) panel.setBackground(c);
    if (buttons!=null) buttons.setBackground(c);
    if (p!=null) p.setBackground(c);
    if (message!=null) message.setBackground(c);
  }

  public void addNotify() {
    super.addNotify();
    setBackground( getBackground() );
  }

  public static void main(String[] args) {
    JFrame jf=new JFrame("OCL AST Viewer");
    jf.getContentPane().add(
      new ASTViewer(true)
    );
    jf.pack();
    jf.setVisible(true);
    jf.addWindowListener(
      new java.awt.event.WindowAdapter() {
        public void windowClosing(java.awt.event.WindowEvent e) {
          System.exit(0);
        }
      }
    );
  }

  class FontDialog extends JDialog implements java.awt.event.ActionListener {

    JButton getAllFonts, apply, close;
    JComboBox fonts, sizes;
    DefaultComboBoxModel fontsModel;

    FontDialog() {
      getContentPane().setLayout( new BorderLayout() );
      JPanel center=new JPanel();
      JPanel south=new JPanel();
      getContentPane().add(center);
      getContentPane().add(south, BorderLayout.SOUTH);
      fontsModel=new DefaultComboBoxModel();
      fontsModel.addElement("monospaced");
      fontsModel.addElement("serif");
      fontsModel.addElement("sansserif");
      fontsModel.addElement("dialog");
      fontsModel.addElement("dialoginput");
      fonts=new JComboBox(
        fontsModel
      );
      String[] szs={"6", "8", "10", "12", "14"};
      sizes=new JComboBox(
        new DefaultComboBoxModel(szs)
      );
      fonts.setSelectedItem( text.getFont().getName() );
      sizes.setSelectedItem( Integer.toString(text.getFont().getSize()) );

      getAllFonts=new JButton("Get All Fonts");
      apply=new JButton("Apply");
      close=new JButton("Close");
      getAllFonts.addActionListener(this);
      apply.addActionListener(this);
      close.addActionListener(this);
      this.addWindowListener( new java.awt.event.WindowAdapter() {
        public void windowClosing(java.awt.event.WindowEvent e) {
          dispose();
          fontDialog=null;
        }
      } );

      center.add(fonts);
      center.add(sizes);
      south.add(getAllFonts);
      south.add(apply);
      south.add(close);
      pack();
    }

    public void getAllFonts() {
      Font[] allFonts=GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
      for (int i=0; i<allFonts.length; i++) {
        if (allFonts[i].canDisplay('a'))
          fontsModel.addElement( allFonts[i].getName() );
      }
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
      if (e.getSource()==apply && !fonts.getSelectedItem().equals("---------------") ) {
        Font newFont=new Font(
          fonts.getSelectedItem().toString(),
          Font.PLAIN,
          Integer.parseInt(sizes.getSelectedItem().toString())
        );
        text.setFont(newFont);
        tree.setFont(newFont);
      }
      if (e.getSource()==close) {
        fontDialog=null;
        dispose();
      }
      if (e.getSource()==getAllFonts) {
        getAllFonts();
      }
    }
  } // end inner class
}


