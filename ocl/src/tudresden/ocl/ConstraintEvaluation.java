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

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.tree.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;

import java.util.*;
import java.util.StringTokenizer;
import java.io.*;

import tudresden.ocl.check.types.*;
import tudresden.ocl.parser.node.*;
import tudresden.ocl.codegen.*;

public class ConstraintEvaluation extends JPanel
             implements ActionListener, ClipboardOwner, ListSelectionListener {

  protected JTextArea cInput;
  protected JButton cParse, cToClipboard, cFromClipboard;

  protected JRadioButton mTest, mXmi, mReflection, mSable;
  protected JTextField mXmiUrl, mReflectionPackage;
  protected JCheckBox mReflectionSourceExtender;

  protected JTable lTable;
  protected LexerModel lexerModel;

  protected JTree aTree;
  protected JButton aToClipboard, aNormalize, aShowLeaves, aToText;
  protected JLabel aTypeCheck, aGeneratedTests;

  protected JTextArea jCode;
  protected JTextField jCType, jCOperation, jResult, jKind;
  protected JList jList;
  protected JButton jGenerate;
  protected CodeFragment[] frags;

  protected JTextArea eText;

  protected Icon imageOK=getImage("ok.gif");
  protected Icon imageFailed=getImage("failed.gif");
  protected Icon imageQuestion=getImage("question.gif");
  protected Icon imageEmpty=getImage("empty.gif");

  protected JLabel message;
  protected JTabbedPane tabs;

  protected OclTree tree;
  protected boolean synAndSemOK;

  public ConstraintEvaluation() {
    message=new JLabel(" ");

    tabs=new JTabbedPane();
    addTabs(tabs);

    setLayout(new BorderLayout());
    add(message, BorderLayout.SOUTH);
    add(tabs);
  }

  protected void addTabs(JTabbedPane tabs) {
    tabs.addTab("Constraint", getConstraintPane());
    tabs.addTab("Model", getModelPane());
    tabs.addTab("Lexer", getLexerPane());
    tabs.addTab("AST", getASTPane());
    tabs.addTab("Java", getJavaCodePane());
    tabs.addTab("Errors", getErrorPane());
    tabs.addTab("About", getAboutPane());
  }

  protected JPanel getConstraintPane() {
    JPanel result=new JPanel(new BorderLayout());
    JPanel buttons=new JPanel(new GridLayout(0, 1));
    cInput=new JTextArea("context Company\ninv: employees->forAll(age>15)");
    cInput.setFont(new Font("Monospaced", Font.PLAIN, 12));

    cParse=new JButton("Parse");
    cToClipboard=new JButton(getImage("right.gif"));
    cFromClipboard=new JButton(getImage("left.gif"));

    cToClipboard.setToolTipText("copy constraint to clipboard");
    cFromClipboard.setToolTipText("copy constraint from clipboard");

    cParse.addActionListener(this);
    cToClipboard.addActionListener(this);
    cFromClipboard.addActionListener(this);

    buttons.add(cParse);
    buttons.add(new JLabel(" "));
    buttons.add(cToClipboard);
    buttons.add(cFromClipboard);

    result.add(new JScrollPane(cInput));
    result.add(panelAround(buttons), BorderLayout.EAST);
    return result;
  }

  protected JPanel getModelPane() {
    JPanel result=new JPanel(new GridLayout(1, 2));

    mTest=new JRadioButton("TestFacade");
    mXmi=new JRadioButton("XmiFacade");
    mReflection=new JRadioButton("ReflectionFacade");
    mSable=new JRadioButton("SableFacade");

    mXmiUrl=new JTextField("http://www-st.inf.tu-dresden.de/ocl/royloy.xmi");
    mReflectionPackage=new JTextField("tudresden.ocl.test.royloy");
    mReflectionSourceExtender=new JCheckBox("Use SourceReflectionExtender", true);

    ButtonGroup radios=new ButtonGroup();
    radios.add(mTest);
    radios.add(mXmi);
    radios.add(mReflection);
    radios.add(mSable);
    mTest.setSelected(true);

    RadioListener rl=new RadioListener();
    mTest.addActionListener(rl);
    mXmi.addActionListener(rl);
    mReflection.addActionListener(rl);
    mSable.addActionListener(rl);
    mXmiUrl.setEnabled(false);
    mReflectionPackage.setEnabled(false);

    JPanel xmiConfig=new JPanel(new GridLayout(0, 1));
    xmiConfig.setBorder(BorderFactory.createTitledBorder(
      "XMI Facade Configuration"
    ));
    xmiConfig.add(new JLabel("URL of XMI file (DTD must be in same directory):"));
    xmiConfig.add(mXmiUrl);

    JPanel reflectionConfig=new JPanel(new GridLayout(0, 1));
    reflectionConfig.setBorder(BorderFactory.createTitledBorder(
      "ReflectionFacade Configuration"
    ));
    reflectionConfig.add(new JLabel("package names (';'-separated) to be queried:"));
    reflectionConfig.add(mReflectionPackage);
    reflectionConfig.add(mReflectionSourceExtender);

    JTextArea explTest=new JTextArea(
      "      contains the \"Person-Company\" model\n"+
      "      and the \"Royals and Loyals\" model"
    );
    explTest.setEditable(false);
    explTest.setOpaque(false);
    explTest.setAlignmentX(0);
    JTextArea explXmi=new JTextArea(
      "      extracts model information from a XMI file \n"+
      "      generated with Argo/UML"
    );
    explXmi.setEditable(false);
    explXmi.setOpaque(false);
    explXmi.setAlignmentX(0);
    JTextArea explReflection=new JTextArea(
      "      uses Java reflection to get model information;\n"+
      "      this poses some restrictions on the OCL \n"+
      "      constraint (e.g. no forAll without declarator)"
    );
    explReflection.setEditable(false);
    explReflection.setOpaque(false);
    explReflection.setAlignmentX(0);
    JTextArea explSable=new JTextArea(
      "      an adaptation of the ReflectionFacade that\n"+
      "      evaluates SableCC-generated node classes;\n"+
      "      used to constrain OCL abstract syntax trees"
    );
    explSable.setEditable(false);
    explSable.setOpaque(false);
    explSable.setAlignmentX(0);


    JPanel radioPanel=new JPanel();
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
    radioPanel.setBorder(BorderFactory.createTitledBorder(
      "select model information source"
    ));
    radioPanel.add(mTest);
    radioPanel.add(explTest);
    radioPanel.add(mXmi);
    radioPanel.add(explXmi);
    radioPanel.add(mReflection);
    radioPanel.add(explReflection);
    radioPanel.add(mSable);
    radioPanel.add(explSable);

    JPanel rightSide=new JPanel(new GridLayout(0, 1));
    rightSide.add(new JLabel());
    rightSide.add(xmiConfig);
    rightSide.add(reflectionConfig);
    rightSide.add(new JLabel());

    result.add(radioPanel);
    result.add(rightSide);

    return panelAround(result);
  }

  protected JComponent getLexerPane() {
    lexerModel=new LexerModel();
    lTable=new JTable(lexerModel);
    lTable.setPreferredScrollableViewportSize(new Dimension(300, 200));
    return new JScrollPane(lTable);
  }

  protected JPanel getASTPane() {
    JPanel result=new JPanel(new BorderLayout());
    JPanel buttons=new JPanel(new GridLayout(0, 1));

    aTree=new JTree(new DefaultMutableTreeNode());
    aTree.setFont(new Font("Monospaced", Font.PLAIN, 12));
    aNormalize=new JButton("Normalize");
    aShowLeaves=new JButton("Show Leaves");
    aToText=new JButton("To Text");
    aToClipboard=new JButton(getImage("right.gif"));

    JLabel txtTypeCheck=new JLabel("Type Check:");
    JLabel txtGeneratedTests=new JLabel("Generated Tests:");
    aTypeCheck=new JLabel(imageEmpty, SwingConstants.CENTER);
    aGeneratedTests=new JLabel(imageEmpty, SwingConstants.CENTER);

    aNormalize.setToolTipText("normalize syntax tree");
    aShowLeaves.setToolTipText("show all leafs of the syntax tree");
    aToText.setToolTipText("copy the syntax tree's expression into the input field");
    aToClipboard.setToolTipText("copy text version of syntax tree to clipboard");

    aNormalize.addActionListener(this);
    aToText.addActionListener(this);
    aShowLeaves.addActionListener(this);
    aToClipboard.addActionListener(this);

    buttons.add(aNormalize);
    buttons.add(aShowLeaves);
    buttons.add(new JLabel(" "));
    buttons.add(aToText);
    buttons.add(aToClipboard);
    buttons.add(new JLabel(" "));
    buttons.add(txtTypeCheck);
    buttons.add(aTypeCheck);
    buttons.add(txtGeneratedTests);
    buttons.add(aGeneratedTests);

    result.add(new JScrollPane(aTree));
    result.add(panelAround(buttons), BorderLayout.EAST);
    return result;
  }

  protected JPanel getJavaCodePane() {
    JPanel result=new JPanel(new BorderLayout());
    JPanel leftSide=new JPanel(new BorderLayout());
    JPanel center=new JPanel(new BorderLayout());

    jList=new JList();
    jList.setBorder(BorderFactory.createEtchedBorder());
    jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    jList.addListSelectionListener(this);
    jGenerate=new JButton("Generate");
    jGenerate.addActionListener(this);

    JPanel leftSouth=new JPanel(new GridLayout(0, 1));
    jCType=new JTextField(12);
    jCOperation=new JTextField(12);
    jCType.setEditable(false);
    jCOperation.setEditable(false);
    leftSouth.add(new JLabel("Type:"));
    leftSouth.add(jCType);
    leftSouth.add(new JLabel("Operation:"));
    leftSouth.add(jCOperation);
    leftSouth.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    jCode=new JTextArea();
    jCode.setFont(new Font("Monospaced", Font.PLAIN, 12));
    jCode.setEditable(false);
    jCode.setBorder(BorderFactory.createEtchedBorder());

    JPanel centerSouth=new JPanel();
    jResult=new JTextField(10);
    jResult.setEditable(false);
    jKind=new JTextField(10);
    jKind.setEditable(false);
    centerSouth.add(new JLabel("result variable:"));
    centerSouth.add(jResult);
    centerSouth.add(new JLabel("constraint kind:"));
    centerSouth.add(jKind);
    centerSouth.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));


    leftSide.add(jGenerate, BorderLayout.NORTH);
    leftSide.add(jList);
    leftSide.add(leftSouth, BorderLayout.SOUTH);
    center.add(new JScrollPane(jCode));
    center.add(centerSouth, BorderLayout.SOUTH);
    result.add(leftSide, BorderLayout.WEST);
    result.add(center);
    return result;
  }

  protected JComponent getErrorPane() {
    eText=new JTextArea();
    eText.setEditable(false);
    return new JScrollPane(eText,
      JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  }

  protected JComponent getAboutPane() {
    JPanel result=new JPanel(new BorderLayout());

    JPanel allLogos=new JPanel(new BorderLayout());
    JPanel smallLogos=new JPanel(new BorderLayout());
    JPanel center=new JPanel(new GridLayout(0, 1));

    smallLogos.add(new JLabel( getImage("tulogo.gif") ), BorderLayout.NORTH);
    smallLogos.add(new JLabel( getImage("st.gif") ), BorderLayout.SOUTH);
    allLogos.add(new JLabel( getImage("logo.gif") ));
    allLogos.add(smallLogos, BorderLayout.WEST);

    center.add(new JLabel("OCL Compiler", SwingConstants.CENTER));
    center.add(new JLabel("written 1999/2000 by Frank Finger (ff3@inf.tu-dresden.de)", SwingConstants.CENTER));
    center.add(new JLabel("XMI support 2000 by Ralf Wiebicke (rw7@inf.tu-dresden.de)", SwingConstants.CENTER));
    center.add(new JLabel("visit http://www-st.inf.tu-dresden.de/ocl", SwingConstants.CENTER));
    center.add(new JLabel("Chair for Software Technology, Dresden University of Technology", SwingConstants.CENTER));

    result.add(allLogos, BorderLayout.NORTH);
    result.add(panelAround(center));
    result.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
    return panelAround(result);
  }

  protected int getIndexOfErrorPane() {
    return 5;
  }

  protected int getIndexOfASTPane() {
    return 3;
  }

  public void actionPerformed(ActionEvent event) {
    // RadioButtons are handled by inner class RadioListener
    message.setText(" ");
    Object source=event.getSource();
    if (source==cParse) {
      doParse();
    } else if (source==cToClipboard) {
      setClipboard(cInput.getText());
    } else if (source==cFromClipboard) {
      String clipboard=getClipboard();
      if (clipboard!=null && clipboard.length()>0) cInput.setText(clipboard);
    } else if (source==aToClipboard) {
      doCopyTreeToClipboard();
    } else if (source==aNormalize) {
      doNormalize();
    } else if (source==aShowLeaves) {
      doShowLeaves();
    } else if (source==aToText) {
      doCopyTreeToText();
    } else if (source==jGenerate) {
      doGenerateJava();
    }
  }

  class RadioListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      Object source=e.getSource();
      if (source==mTest) {
        mXmiUrl.setEnabled(false);
        mReflectionPackage.setEnabled(false);
      } else if (source==mXmi) {
        mXmiUrl.setEnabled(true);
        mReflectionPackage.setEnabled(false);
      } else if (source==mReflection) {
        mXmiUrl.setEnabled(false);
        mReflectionPackage.setEnabled(true);
      } else if (source==mSable) {
        mXmiUrl.setEnabled(false);
        mReflectionPackage.setEnabled(false);
      }
    }
  }

  public void valueChanged(ListSelectionEvent lse) {
    if (jList.getSelectedIndex()>=0) {
      selectFragment(jList.getSelectedIndex());
    }
  }

  protected void doParse() {
    doParse(true);
  }

  protected void doNormalize() {
    try {
      if (tree==null) doParse();
      if (tree!=null) {
        tree.applyDefaultNormalizations();
        updateTree();
      }
    }
    catch (Exception e) {
      updateError(e);
      showTab(getIndexOfErrorPane());
    }
  }

  protected void doParse(boolean switchTabs) {
    String constraint=cInput.getText();
    try {
      synAndSemOK=false;
      tree=OclTree.createTree(
        constraint,
        getModelFacade()
      );
      if (switchTabs) showTab(getIndexOfASTPane());
    }
    catch (Exception e) {
      updateError(e);
      tree=null;
      showTab(getIndexOfErrorPane());
    }
    updateTokens();
    updateTree();
  }

  protected void doCopyTreeToText() {
    if (tree!=null) {
      cInput.setText( tree.getExpression() );
      showTab(0);
    } else {
      message.setText("no syntax tree to copy...");
    }
  }

  protected void doCopyTreeToClipboard() {
    if (tree!=null) {
      ASTTextGenerator tg=new ASTTextGenerator();
      tree.apply(tg);
      boolean successfull=setClipboard(tg.getText());
      if (!successfull) {
        JDialog d=new JDialog();
        d.setTitle("text representation of syntax tree");
        JTextArea text=new JTextArea(tg.getText());
        text.setFont(new Font("Monospaced", Font.PLAIN, 12));
        d.getContentPane().add(new JScrollPane(text));
        d.setSize(400, 400);
        d.setVisible(true);
      }
    } else {
      message.setText("no syntax tree to copy...");
    }
  }

  protected void doShowLeaves() {
    for (int i=0; i<aTree.getRowCount(); i++) {
      aTree.expandRow(i);
    }
  }

  protected void doGenerateJava() {
    if (tree==null) {
      doParse(false);
      doNormalize();
    }
    if (tree!=null) {
      try {
        JavaCodeGenerator jcg=new JavaCodeGenerator("this", "result");
        frags=jcg.getCode(tree);
        selectFragment(0);
        Object[] listData=new Object[frags.length];
        for (int i=0; i<frags.length; i++) {
          listData[i]=frags[i].getName();
        }
        jList.setListData(listData);
        jList.setSelectedIndex(0);
      }
      catch (tudresden.ocl.normalize.PreconditionViolatedException e) {
        updateError(e);
        message.setText("normalize before generating code!");
        jList.setListData(new Object[0]);
        frags=null;
      }
      catch (Exception e) {
        updateError(e);
        jList.setListData(new Object[0]);
        frags=null;
      }
    } else {
      jList.setListData(new Object[0]);
      frags=null;
    }
  }

  protected void selectFragment(int index) {
    CodeFragment f=frags[index];
    jCode.setText(f.getCode());
    jCOperation.setText(f.getConstrainedOperation());
    jCOperation.setToolTipText(f.getConstrainedOperation());
    jCType.setText(f.getConstrainedType());
    jCType.setToolTipText(f.getConstrainedType());
    jResult.setText(f.getResultVariable());
    String kind=null;
    switch (f.getKind()) {
      case CodeFragment.PRE: kind="PRE"; break;
      case CodeFragment.POST: kind="POST"; break;
      case CodeFragment.INV: kind="INV"; break;
      case CodeFragment.PREPARATION: kind="PREPARATION"; break;
      case CodeFragment.TRANSFER: kind="TRANSFER"; break;
    }
    jKind.setText(kind);
  }

  protected ModelFacade getModelFacade() throws Exception {
    if (mTest.isSelected()) {
      return new tudresden.ocl.check.types.testfacade.TestModelFacade();
    } else if (mXmi.isSelected()) {
      return tudresden.ocl.check.types.xmifacade.XmiParser.getModel(
        mXmiUrl.getText().trim()
      );
    } else if (mReflection.isSelected()) {
      StringTokenizer tok=new StringTokenizer(mReflectionPackage.getText(), ";");
      String[] pNames=new String[tok.countTokens()];
      for (int i=0; i<pNames.length; i++) {
        pNames[i]=tok.nextToken().trim();
      }
      ReflectionExtender extender=
        mReflectionSourceExtender.isSelected() ?
        extender=new tudresden.ocl.injection.SourceReflectionExtender() :
        null;
      return new tudresden.ocl.check.types.ReflectionFacade(
        pNames, new DefaultReflectionAdapter(), new tudresden.ocl.lib.ArgoNameAdapter(),
        extender
      );
    } else if (mSable.isSelected()) {
      String[] pNames={"tudresden.ocl.parser.node"};
      return new tudresden.ocl.check.bootstrap.SableReflectionFacade(
        pNames, new DefaultReflectionAdapter(), new tudresden.ocl.check.bootstrap.SableNameAdapter()
      );
    }
    return null;
  }

  protected void showTab(int i) {
    tabs.setSelectedIndex(i);
  }

  protected String getClipboard() {
    String content=null;
    try {
      Clipboard cb=Toolkit.getDefaultToolkit().getSystemClipboard();
      content=(String)cb.getContents(this).getTransferData(DataFlavor.stringFlavor);
    }
    catch (Exception e) {
      updateError(e);
      message.setText("cannot access system clipboard");
    }
    return content;
  }

  /** @return true if the operation was successful, false otherwise
   */
  protected boolean setClipboard(String content) {
    boolean result=false;
    try {
      Clipboard cb=Toolkit.getDefaultToolkit().getSystemClipboard();
      if (content.length()>0) {
        Transferable xfer=new StringSelection(content);
        cb.setContents(xfer, this);
      }
      result=true;
    }
    catch (Exception e) {
      updateError(e);
      message.setText("cannot access system clipboard");
    }
    return result;
  }

  public void lostOwnership(Clipboard cb, Transferable content) {
  }

  protected void updateTokens() {
    String text=cInput.getText();
    tudresden.ocl.parser.lexer.Lexer lexer=new tudresden.ocl.parser.lexer.Lexer(
      new PushbackReader( new StringReader(text) )
    );
    lexerModel.tokens.clear();
    try {
      Token t;
      do {
        t=lexer.next();
        lexerModel.tokens.add(t);
      } while (! (t instanceof EOF) );
    }
    catch (Exception e) {
      updateError(e);
    }
    lexerModel.fireTableStructureChanged();
    lTable.sizeColumnsToFit(-1);
  }

  protected void updateTree() {
    aGeneratedTests.setToolTipText("");
    synAndSemOK=false;
    if (tree!=null) {
      Visualizer vis=new Visualizer(false, tree);
      try {
        tree.apply(vis);
        aTypeCheck.setIcon(imageOK);
        try {
          tree.applyGeneratedTests();
          synAndSemOK=true;
          aGeneratedTests.setIcon(imageOK);
        }
        catch (java.security.AccessControlException ace) {
          aGeneratedTests.setIcon(imageQuestion);
          aGeneratedTests.setToolTipText("SecurityManager denies access to non-public fields");
        }
        catch (Exception e) {
          aGeneratedTests.setIcon(imageFailed);
          updateError(e);
        }
      }
      catch (Exception e) {
        aGeneratedTests.setIcon(imageQuestion);
        aGeneratedTests.setToolTipText("Generated Test need type information from type checking");
        aTypeCheck.setIcon(imageFailed);
        updateError(e);
      }
      aTree.setModel( new DefaultTreeModel(vis.getRootNode()) );
    } else {
      aTree.setModel( new DefaultTreeModel(new DefaultMutableTreeNode()) );
      aTypeCheck.setIcon(imageEmpty);
      aTypeCheck.setToolTipText("");
      aGeneratedTests.setIcon(imageEmpty);
    }
  }

  protected void updateError(Exception e) {
    message.setText( removeControlsFrom(e.getMessage(), true) );
    StringWriter sw=new StringWriter();
    e.printStackTrace( new PrintWriter(sw) );
    eText.setText( removeControlsFrom(sw.toString(), false) );
  }

  protected JPanel panelAround(JComponent comp) {
    JPanel p=new JPanel();
    p.add(comp);
    return p;
  }

  protected Icon getImage(String name) {
    java.net.URL url=ConstraintEvaluation.class.getResource(name);
    ImageIcon ii=null;
    try {
      ii=new ImageIcon(url);
    }
    catch (RuntimeException e) {
      // image not found, go on without it...
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
    return ii;
  }

  protected String removeControlsFrom(String s, boolean removeLineFeed) {
    if (s==null || s.equals("")) return " ";
    StringBuffer sb=new StringBuffer();
    for (int i=0; i<s.length(); i++) {
      char c=s.charAt(i);
      if ( !Character.isISOControl(c) || (!removeLineFeed && c=='\n')) {
        sb.append(c);
      }
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    JFrame frame=new JFrame("OCL Constraint Evaluation");
    ConstraintEvaluation ce=new ConstraintEvaluation();
    frame.addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          System.exit(0);
        }
      }
    );
    frame.getContentPane().add(ce);
    frame.setSize(600, 400);
    frame.setVisible(true);
  }
}

class LexerModel extends AbstractTableModel {

  ArrayList tokens=new ArrayList();

  public int getColumnCount() {
    return 2;
  }

  public int getRowCount() {
    return tokens.size();
  }

  public String getColumnName(int col) {
    if (col==0) {
      return "Token Class";
    } else {
      return "Token Text";
    }
  }

  public Object getValueAt(int row, int col) {
    Token t=(Token)tokens.get(row);
    if (col==0) {
      String result=t.getClass().toString();
      return result.substring( result.lastIndexOf(".")+1 );
    } else {
      return t;
    }
  }
}
