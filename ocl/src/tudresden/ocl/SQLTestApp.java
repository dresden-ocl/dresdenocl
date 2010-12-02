package tudresden.ocl;

import tudresden.ocl.*;
import tudresden.ocl.check.types.*;
import tudresden.ocl.check.types.xmifacade.*;
import tudresden.ocl.sql.*;
import tudresden.ocl.codegen.decl.*;
import tudresden.ocl.codegen.decl.treegen.*;
import tudresden.ocl.codegen.decl.treegen.normalize.*;

import java.io.FileWriter;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;

class SQLTestApp extends ConstraintEvaluation {

	Model mf;

	JEditorPane treeCode;
    JTextArea sqlCode;
    JButton sqlGenerate, bCreateTree, bNormalizeSql;
    JRadioButton sql92, sqlOracle, sqlOther;
    JTextField sqlOtherUrl;
    JTree sqlTree;
    tudresden.ocl.codegen.decl.treegen.node.Node theSqlTree;

    public SQLTestApp() {
	message=new JLabel(" ");
	tabs=new JTabbedPane();

	tabs.addTab("Constraint", getConstraintPane());
	tabs.addTab("Model", getModelPane());
	tabs.addTab("Lexer", getLexerPane());
	tabs.addTab("AST", getASTPane());
	tabs.addTab("Java", getJavaCodePane());
	tabs.addTab("Errors", getErrorPane());
	tabs.addTab("About", getAboutPane());
	tabs.addTab("SQL", getSQLCodePane());
	tabs.addTab("SQL tree", getSQLTree());

	setLayout(new BorderLayout());
	add(message, BorderLayout.SOUTH);
	add(tabs);
    }

    protected JPanel getSQLTree() {
    	JPanel result = new JPanel(new BorderLayout());

    	bCreateTree = new JButton("create tree");
    	bCreateTree.addActionListener(this);
    	bNormalizeSql = new JButton(" normalize ");
    	bNormalizeSql.addActionListener(this);
    	JPanel bp = new JPanel();
    	bp.setLayout(new BoxLayout(bp, BoxLayout.Y_AXIS));
    	bp.add(bCreateTree);
    	bp.add(bNormalizeSql);
    	result.add(BorderLayout.WEST, bp);


    	sqlTree = new JTree(new DefaultMutableTreeNode());
    	sqlTree.setFont(new Font("Monospaced", Font.PLAIN, 12));
    	JPanel p1 = new JPanel(new BorderLayout());
    	p1.add(BorderLayout.CENTER, new JScrollPane(sqlTree));

    	treeCode = new JEditorPane();
    	JPanel p2 = new JPanel(new BorderLayout());
    	p2.add(BorderLayout.CENTER, new JScrollPane(treeCode));

    	JTabbedPane stp = new JTabbedPane();
    	stp.add(" tree ", p1);
    	stp.add(" code ", p2);
    	result.add(BorderLayout.CENTER, stp);

    	return result;
    }

    protected JPanel getSQLCodePane() {
        JPanel result = new JPanel(new BorderLayout());
        JPanel leftSide = new JPanel(new BorderLayout());

        sql92 = new JRadioButton("SQL92");
        sqlOracle = new JRadioButton("Oracle 8i");
        sqlOther = new JRadioButton("Other");

        sqlOtherUrl = new JTextField("");

        ButtonGroup radios=new ButtonGroup();
        radios.add(sql92);
        radios.add(sqlOracle);
        radios.add(sqlOther);
        sql92.setSelected(true);

        SQLTabRadioListener rl=new SQLTabRadioListener();
        sql92.addActionListener(rl);
        sqlOracle.addActionListener(rl);
        sqlOther.addActionListener(rl);
        sqlOtherUrl.setEnabled(false);

        JPanel sqlOtherConfig = new JPanel(new BorderLayout());
        sqlOtherConfig.add(new JLabel("URL of XML file with mapping rules (DTD must be in same directory):"), BorderLayout.NORTH);
        sqlOtherConfig.add(sqlOtherUrl, BorderLayout.CENTER);

        JPanel radioPanel=new JPanel();
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
        radioPanel.setBorder(BorderFactory.createTitledBorder(" Target "));
        radioPanel.add(sql92);
        radioPanel.add(sqlOracle);
        radioPanel.add(sqlOther);

	sqlGenerate = new JButton("  Generate  ");
	sqlGenerate.addActionListener(this);

        leftSide.add(sqlGenerate, BorderLayout.NORTH);
        leftSide.add(radioPanel, BorderLayout.CENTER);

	sqlCode = new JTextArea();
	sqlCode.setFont(new Font("Monospaced", Font.PLAIN, 12));
	sqlCode.setEditable(false);
	sqlCode.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

	result.add(new JScrollPane(sqlCode), BorderLayout.CENTER);
        result.add(leftSide, BorderLayout.WEST);
        result.add(sqlOtherConfig, BorderLayout.SOUTH);

	return result;
    }

    public void actionPerformed(ActionEvent event) {
		Object source=event.getSource();

		if (source==sqlGenerate) {
			try {
				doGenerateSQL();
			} catch (Exception e) {
				updateError(e);
			}
		} else if (source==bCreateTree) {
			createSQLTree();
		} else if (source==bNormalizeSql) {
			normalizeSQL();
		} else {
	    	super.actionPerformed(event);
		}
    }

    /*
    protected ModelFacade getModelFacade() throws Exception {
    	return mf;
    }
    */

    protected void createSQLTree() {
    	SQLCodeGenerator stg = null;
    	/*
	String xmiFile = "";
    	String xmlFile = "";
    	Model rm;
    	... orm;
	*/

    	try {
	    	/* read the XMI source
			System.out.println("read XMI sources ...");
			if (xmiFile.charAt(0) != '/') xmiFile = "/" + xmiFile;
			xmiFile = "file://" + xmiFile;
			mf = XmiParser.createModel(xmiFile, "model in classic mode");
			rm = XmiParser.createRoughModel(xmiFile, "model in rough mode");

			// get the object relational map
			System.out.println("create object relational map ...");
			if (xmlFile.charAt(0) != '/') xmlFile = "/" + xmlFile;
			xmlFile = "file://" + xmlFile;
			orm = ...;


	 	   	stg.setORMappingScheme(orm);
	    	*/
	    	stg = new SQLCodeGenerator();
	    	stg.setORMappingScheme(new ORTestScheme());


	    	doParse(false);
		doNormalize();


    		theSqlTree = stg.getSQLTree(tree);
    	} catch(Exception ex) {
    		updateError(ex);
    		showTab(getIndexOfErrorPane());
    		theSqlTree = stg.getUnfinishedTree();
    	}

    	tudresden.ocl.codegen.decl.treegen.Visualizer visu = new tudresden.ocl.codegen.decl.treegen.Visualizer();
    	theSqlTree.apply(visu);
    	sqlTree.setModel( new DefaultTreeModel(visu.getRootNode()) );

    	Printer p = new Oracle8iPrinter();
    	theSqlTree.apply(p);
    	treeCode.setText(p.getCode());
    }

    protected void normalizeSQL() {
    	theSqlTree.apply(new SelectElimination());
    	theSqlTree.apply(new RedNavElimination());
    	theSqlTree.apply(new ContNavElimination());
    	theSqlTree.apply(new WhereSelectElimination());

    	tudresden.ocl.codegen.decl.treegen.Visualizer visu = new tudresden.ocl.codegen.decl.treegen.Visualizer();
    	theSqlTree.apply(visu);
    	sqlTree.setModel( new DefaultTreeModel(visu.getRootNode()) );

    	Printer p = new Oracle8iPrinter();
    	theSqlTree.apply(p);
    	treeCode.setText(p.getCode());
    }

    protected void doGenerateSQL() {
        String sqlRulesUrl;

	doParse(false);
	doNormalize();

        if (sql92.isSelected()) {
          sqlRulesUrl = (SQLTestApp.class.getResource("codegen/decl/OCL2SQL4SQL92.xml")).toString();
        } else if (sqlOracle.isSelected()) {
          sqlRulesUrl = (SQLTestApp.class.getResource("codegen/decl/OCL2SQL4Oracle.xml")).toString();
        } else {
          sqlRulesUrl = sqlOtherUrl.getText().trim();
        }

	ILSQLCodeGenerator generator = new ILSQLCodeGenerator(sqlRulesUrl);

	frags = generator.getCode(tree);

        try {
          FileWriter fw = new FileWriter("query.sql");

          for (int i=0; i<frags.length; i++) {
            fw.write(frags[i].getCode());
            sqlCode.append(frags[i].getCode());
          }

          fw.close();
        } catch (Exception e) {
          System.err.println("Cannot write query.sql\n" + e.toString());
        }
    }

    public static void main(String[] args) {
	JFrame frame=new JFrame("OCL ConstraintEvaluation");
	SQLTestApp sta=new SQLTestApp();

	frame.addWindowListener(
	  new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
		System.exit(0);
	    }
	}
        );

	frame.getContentPane().add(sta);
	frame.setSize(600,400);
	frame.setVisible(true);
    }

    class SQLTabRadioListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();
        if (source==sqlOther) {
          sqlOtherUrl.setEnabled(true);
        } else {
          sqlOtherUrl.setEnabled(false);
        }
      }
    }
}
