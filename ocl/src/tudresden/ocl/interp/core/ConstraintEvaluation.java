/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL Interpreter                                                   *
 * Copyright (C) 2002 Nikolai Krambrock (nikk@gmx.de)                *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a diploma project at the Chair for Software *
 * Construction, University Of Technology Aachen, Germany            *
 * (http://www-lufgi3.informatik.rwth-aachen.de).                    *
 * It was done in co-operation with Software & Design and Managment  *
 * Troisdorf, Germany (http://www.sdm.de)                            *
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
 * this project, please visit the project home page:                 *
 * http://dresden-ocl.sourceforge.net                                * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package tudresden.ocl.interp.core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.Enumeration;
import java.util.Vector;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import tudresden.ocl.check.types.ModelFacade;

import tudresden.ocl.interp.core.intern.InterpreterVisitor;
import tudresden.ocl.interp.exp.intern.Exp;
import tudresden.ocl.interp.lib.intern.InstanceOclFactory;
import tudresden.ocl.interp.test.ABDummyFacade;
import tudresden.ocl.interp.test.SpecFacadeFactory;
import tudresden.ocl.interp.types.InstanceFacade;

import tudresden.ocl.lib.Ocl;


/**
 * This class is a gui-example to show the usage of the interpreter.
 */
class ConstraintEvaluation extends tudresden.ocl.ConstraintEvaluation {
  protected JTree expTree;
  protected ExpTree exp;
  protected JButton expShow;
  protected JButton expRun;
  protected JButton expLeaves;
  protected JTextField expResult;
  protected JRadioButton expModelReflect;
  protected JRadioButton expModelDummy;
  protected ABDummyFacade dummyFacade;
  protected JLabel classPicture;
  protected JLabel objectPicture;

  protected ConstraintEvaluation() {
  	super();
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("OCL Constraint Evaluation");
    ConstraintEvaluation ce = new ConstraintEvaluation();
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    frame.getContentPane().add(ce);
    frame.setSize(800, 400);
    frame.setVisible(true);
  }

  protected void addTabs(JTabbedPane tabs) {
    tabs.addTab("Constraint", getConstraintPane());
    tabs.addTab("Model", getModelPane());

    // tabs.addTab("Lexer", getLexerPane());
    tabs.addTab("AST", getASTPane());
    tabs.addTab("Exp", getExpPane());

    // tabs.addTab("Java", getJavaCodePane());
    tabs.addTab("Errors", getErrorPane());
    tabs.addTab("About", getAboutPane());
  }

  protected JPanel getExpPane() {
    JPanel result = new JPanel(new BorderLayout());
    JPanel buttons = new JPanel(new GridLayout(0, 1));
    JPanel north = new JPanel(new BorderLayout());

    expTree = new JTree(new DefaultMutableTreeNode());
    expTree.setFont(new Font("Monospaced", Font.PLAIN, 12));

    expShow = new JButton("Build Expressions");
    expShow.setToolTipText("Make an expression tree from the AST");
    expShow.addActionListener(this);

    expRun = new JButton("Evaluate Expression");
    expRun.setToolTipText("Evaluate the given Expression");
    expRun.addActionListener(this);

    expLeaves = new JButton("Show Leaves");
    expLeaves.setToolTipText("show all leafs of the evaluation tree");
    expLeaves.addActionListener(this);

    expResult = new JTextField(20);
    expResult.setEditable(false);

    north.add(new JLabel("Result: "), BorderLayout.WEST);
    north.add(expResult);

    buttons.add(expShow);

    // buttons.add(new JLabel(" "));
    buttons.add(expRun);
    buttons.add(new JLabel(" "));
    buttons.add(expLeaves);

    result.add(new JScrollPane(expTree));
    result.add(panelAround(buttons), BorderLayout.EAST);
    result.add(north, BorderLayout.SOUTH);
    return result;
  }

  public void actionPerformed(ActionEvent event) {

    // RadioButtons are handled by inner class RadioListener
    message.setText(" ");
    Object source = event.getSource();
    if (source == expShow) {
      doExp();
    } else if (source == expRun) {
      doExpRun();
    } else if (source == expLeaves) {
      doExpLeaves();
    } else {
      super.actionPerformed(event);
    }
  }

  protected void doExp() {
    try {
      if (tree == null) {
        doParse(false);
      }
      if (tree == null) {
        return;
      } else {
        tree.applyDefaultNormalizations();
        updateTree();
      }
    } catch (Exception e) {
      updateError(e);
      showTab(getIndexOfErrorPane());
      return;
    }

    Ocl.setFactory(new InstanceOclFactory(getModelFacade()));

    InterpreterVisitor jcg = new InterpreterVisitor(getModelFacade());
    exp = (ExpTree)jcg.getExp(tree);

    expTree.setModel(new DefaultTreeModel(new ExpNode(exp, null, "")));

  }

  protected void doExpRun() {
    if (exp == null) {
      doExp();
    }
    if (exp == null) {

      // The error was shown somewhere else
      return;
    }
    expResult.setText(exp.check(getInstanceFacade()).toString());
  }

  protected void doExpLeaves() {
    for (int i = 0; i < expTree.getRowCount(); i++) {
      expTree.expandRow(i);
    }
  }

  protected JPanel getConstraintPane() {
    JPanel result = super.getConstraintPane();
    cInput.setText("context Company inv: employees->exists(name = 'johnsen')");
    return result;
  }

  protected JPanel getModelPane() {
    // JPanel result = new JPanel(new GridLayout(1, 2));
    JPanel result = new JPanel(new BorderLayout());
    ButtonGroup radios = new ButtonGroup();
    JPanel radioPanel = new JPanel();
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
    radioPanel.setBorder(BorderFactory.createTitledBorder(
                               "select model & object information source"));

    expModelReflect = new JRadioButton("ReflectionFacade");
    radios.add(expModelReflect);
    expModelReflect.setSelected(true);
    JTextArea explReflect = new JTextArea(
                                  "      contains the \"Person-Company\" model\n" + 
                                  "      out of the OCL-Sepcification via reflection\n" + 
                                  "      (Source and Binarys have to be in the same folder)");
    explReflect.setEditable(false);
    explReflect.setOpaque(false);
    explReflect.setAlignmentX(0);

    expModelDummy = new JRadioButton("DummyFacade");
    radios.add(expModelDummy);
    String expl = "      contains a simple model with classes A and B\n" + 
                  "      explained in the source of: \n" + 
                  "          tudresden.ocl.interp.test.ABDummyFacade\n" + 
                  "      Does not work with usage of oclType";
    JTextArea explDummy = new JTextArea(expl);
    explDummy.setEditable(false);
    explDummy.setOpaque(false);
    explDummy.setAlignmentX(0);

	RadioListener rl = new RadioListener();
	expModelReflect.addActionListener(rl);
	expModelDummy.addActionListener(rl);

    radioPanel.add(expModelReflect);
    radioPanel.add(explReflect);
    radioPanel.add(expModelDummy);
    radioPanel.add(explDummy);

  	classPicture = new JLabel(getImage("interp/core/intern/company_class.gif"));
  	objectPicture = new JLabel(getImage("interp/core/intern/company_object.gif"));
	
    JPanel rightSide = new JPanel(new GridLayout(0, 1));
    rightSide.add(classPicture);
    rightSide.add(objectPicture);
    rightSide.setBorder(BorderFactory.createTitledBorder(
                               "The class and object information in UML"));
	
	rightSide.setPreferredSize(new Dimension(400,300));
	
    result.add(radioPanel,BorderLayout.WEST);
    result.add(rightSide);
    
    return panelAround(result);
  }

  protected JComponent getAboutPane() {
    JPanel result = new JPanel(new BorderLayout());

    // JPanel allLogos=new JPanel(new BorderLayout());
    // JPanel smallLogos=new JPanel(new BorderLayout());
    JPanel center = new JPanel(new GridLayout(0, 1));

    // smallLogos.add(new JLabel( getImage("images/tulogo.gif") ), BorderLayout.NORTH);
    // smallLogos.add(new JLabel( getImage("images/st.gif") ), BorderLayout.SOUTH);
    // allLogos.add(new JLabel( getImage("images/logo.gif") ));
    // allLogos.add(smallLogos, BorderLayout.WEST);
    center.add(new JLabel("OCL Interpreter OCL4Java", SwingConstants.CENTER));
    center.add(new JLabel("written 2002 by Nikolai Krambrock", 
                          SwingConstants.CENTER));
    center.add(new JLabel(
                     "Chair for Software Construction, Aachen University of Technology", 
                     SwingConstants.CENTER));
    center.add(new JLabel("in co-operation with sd&m research AG", 
                          SwingConstants.CENTER));
    center.add(new JLabel("", SwingConstants.CENTER));
    center.add(new JLabel(
                     "OCL Compiler written 1999/2000 by Frank Finger (frank@finger.org)", 
                     SwingConstants.CENTER));
    center.add(new JLabel("XMI support 2000 by Ralf Wiebicke (ralf@rw7.de)", 
                          SwingConstants.CENTER));
    center.add(new JLabel(
                     "Chair for Software Technology, Dresden University of Technology", 
                     SwingConstants.CENTER));
    center.add(new JLabel("", SwingConstants.CENTER));
    center.add(new JLabel("visit http://dresden-ocl.sourceforge.net/", 
                          SwingConstants.CENTER));

    // result.add(allLogos, BorderLayout.NORTH);
    result.add(panelAround(center));
    result.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
    return panelAround(result);
  }

  protected int getIndexOfErrorPane() {
    return 4;
  }

  protected int getIndexOfExp() {
    return 3;
  }

  protected int getIndexOfASTPane() {
    return 2;
  }

  protected ModelFacade getModelFacade() {
    if (expModelReflect.isSelected()) {
      return SpecFacadeFactory.getModelFacade();
    } else if (expModelDummy.isSelected()) {
      if (dummyFacade == null) {
        dummyFacade = new ABDummyFacade();
      }
      return dummyFacade;
    }
    throw new RuntimeException();
  }

  protected InstanceFacade getInstanceFacade() {
    if (expModelReflect.isSelected()) {
      return SpecFacadeFactory.getGUIFacade(getModelFacade());
    } else if (expModelDummy.isSelected()) {
      if (dummyFacade == null) {
        dummyFacade = new ABDummyFacade();
      }
      return dummyFacade;
    }
    throw new RuntimeException();
  }

  // We do not show tokens ...
  protected void updateTokens(){}
  
  class RadioListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      Object source=e.getSource();

      if (source==expModelReflect) {
      	classPicture.setIcon(getImage("interp/core/intern/company_class.gif"));
      	objectPicture.setIcon(getImage("interp/core/intern/company_object.gif"));
      } else if (source==expModelDummy) {
      	classPicture.setIcon(getImage("interp/core/intern/AB_class.gif"));
      	objectPicture.setIcon(getImage("interp/core/intern/AB_object.gif"));
      }
    }
  }
}

class ExpNode implements TreeNode {
  private Object o;
  private Exp exp;
  private ExpNode parent;
  private String prepend;
  private Vector cache;

  public ExpNode(Object o, ExpNode parent, String prepend) {
    this.o = o;
    this.parent = parent;
    this.prepend = prepend;
    if (o instanceof Exp) {
      exp = (Exp)o;
    }
  }

  private Vector getCollapsedChildren() {
    if (cache != null) {
      return cache;
    }

    Vector result = new Vector();
    if (exp != null) {
      Object[] tmp = exp.children();
      result = new Vector();
      for (int i = 0; i < tmp.length; i++) {
        if (tmp[i] instanceof String && tmp.length > i + 1 && 
            tmp[i + 1] instanceof Exp) {
          result.add(new ExpNode(tmp[i + 1], this, (String)tmp[i]));
          i++;
        } else {
          result.add(new ExpNode(tmp[i], this, ""));
        }
      }
    }

    cache = result;
    return result;
  }

  public Enumeration children() {
    return getCollapsedChildren().elements();
  }

  public boolean getAllowsChildren() {
    return true;
  }

  public TreeNode getChildAt(int childIndex) {
    return (ExpNode)getCollapsedChildren().elementAt(childIndex);
  }

  public int getChildCount() {
    return getCollapsedChildren().size();
  }

  public int getIndex(TreeNode node) {
    for (int i = 0; i < getCollapsedChildren().size(); i++) {
      if (getCollapsedChildren().elementAt(i) == node) {
        return i;
      }
    }
    throw new RuntimeException();
  }

  public TreeNode getParent() {
    return parent;
  }

  public boolean isLeaf() {
    return getCollapsedChildren().size() == 0;
  }

  public String toString() {
    return prepend + o.toString();
  }
}