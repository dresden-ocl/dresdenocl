package tudresden.ocl;

import tudresden.ocl.*;
import tudresden.ocl.sql.*;
import tudresden.ocl.codegen.decl.*;

import java.io.FileWriter;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class SQLTestApp extends ConstraintEvaluation {

    JTextArea sqlCode;
    JButton sqlGenerate;
    JRadioButton sql92, sqlOracle, sqlOther;
    JTextField sqlOtherUrl;

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

	setLayout(new BorderLayout());
	add(message, BorderLayout.SOUTH);
	add(tabs);
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
	} else {
	    super.actionPerformed(event);
	}
    }

    protected void doGenerateSQL() {
        String sqlRulesUrl;

	doParse(false);
	doNormalize();

        if (sql92.isSelected()) {
          sqlRulesUrl = (SQLTestApp.class.getResource("codegen/decl/OCL2SQL4Oracle.xml")).toString();
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
