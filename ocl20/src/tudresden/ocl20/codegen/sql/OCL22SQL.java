/* 
 * OCL22SQL.java
 * 
 * Copyright (c) 2005 Florian Heidenreich
 * Contact: <mail@fheidenreich.de>
 *
 * This file is part of the Dresden OCL2.0 Toolkit
 * created at Technische Universitaet Dresden (TUD), Germany.
 * Visit http://dresden-ocl.sourceforge.net/ for details.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 * MA  02111-1307  USA
 *
 */

package tudresden.ocl20.codegen.sql;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PushbackReader;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;

import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.OclModel;
import tudresden.ocl20.core.jmi.uml15.core.Classifier;
import tudresden.ocl20.core.jmi.uml15.uml15.Uml15Package;
import tudresden.ocl20.core.jmi.uml15.uml15ocl.expressions.ExpressionInOcl;
import tudresden.ocl20.core.parser.astgen.Heritage;
import tudresden.ocl20.core.parser.astgen.LAttrAstGenerator;
import tudresden.ocl20.core.parser.sablecc.lexer.Lexer;
import tudresden.ocl20.core.parser.sablecc.node.Start;
import tudresden.ocl20.core.parser.sablecc.parser.Parser;
import tudresden.ocl20.codegen.sql.codegen.ObjectViewSchema;
import tudresden.ocl20.codegen.sql.codegen.OracleSQLBuilder;
import tudresden.ocl20.codegen.sql.codegen.SQLBuilder;
import tudresden.ocl20.codegen.sql.codegen.SQLCodeGenerator;
import tudresden.ocl20.codegen.sql.codegen.SchemaGenerator;
import tudresden.ocl20.codegen.sql.orm.ORMapping;
import tudresden.ocl20.codegen.sql.orm.ORMappingImpl;
import tudresden.ocl20.codegen.sql.orm.ORMappingMode;

/**
 * Simple GUI for the experimental OCL22SQL tool.
 * 
 * @author Florian Heidenreich
 * @deprecated See tudresden.ocl20.codegen.decl.tools.sql
 */
public class OCL22SQL extends JPanel implements ActionListener {

	// gui
	private JButton jBtnExecute;
	private JButton jBtnLoadConstraints;
	private JButton jBtnLoadProject;
	private JButton jBtnLoadXmi;
    
	private JFrame jMainFrame;
	
	private JPanel jPOutputPane;
	
	private JRadioButton jRbInheritance0;
	private JRadioButton jRbInheritance1;
	private JRadioButton jRbInheritance2;
	
	private JTabbedPane jTabs;
	
	private JTextArea jTaResultIntegrityViews;
	private JTextArea jTaResultObjectViews;
	private JTextArea jTaResultTables;

	private JTextField jTfConstraints;
	private JTextField jTfProjectDirectory;
	private JTextField jTfXmiSource;

	// internal
	private String constraintsFilePath = "";
	private String xmiSourcePath = "";
	private String projectDirectoryPath = "";
	
	private OclModel model;

	/**
     *  The constructor.
     *  @param mainFrame a main frame for the gui
     */
    public OCL22SQL(JFrame mainFrame) {
        if (mainFrame != null) initComponents();
        this.jMainFrame = mainFrame;
		
		jMainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
		
		jMainFrame.getContentPane().add(this);
		jMainFrame.setSize(600, 400);
		jMainFrame.setVisible(true);
    }

	/**
     *  Main method to start the gui of the OCL22SQL tool.
     */
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("OCL22SQL");
        OCL22SQL ce = new OCL22SQL(mainFrame);
    }
	
	/**
	 * Initializes the GUI
	 */
	private void initComponents() {
		jTabs = new JTabbedPane();
		jTabs.add("Project", getInputPane());
		jTabs.add("Output", getOutputPane());
		setLayout(new BorderLayout());
        add(jTabs);
	}

	/**
	 * Initializes the input pane
	 * 
	 * @return the initialized input pane
	 */
	private JPanel getInputPane() {
		JPanel jpMain = new JPanel(new BorderLayout());
		JPanel jpData = new JPanel();
		
		jpMain.setBorder(BorderFactory.createEtchedBorder());
		
		GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        jpMain.setLayout(gridBag);
		c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        gridBag.setConstraints(jpData, c);
		
		jpMain.add(jpData);
        jpData.setLayout(new BoxLayout(jpData, BoxLayout.Y_AXIS));
		
		JPanel filePanels = new JPanel(new GridLayout(3,1));
		
		// project directory
        JPanel projectPanel = new JPanel(new BorderLayout());
		projectPanel.setBorder(BorderFactory.createTitledBorder(" Project directory "));
		jTfProjectDirectory = new JTextField("");
		projectPanel.add(BorderLayout.CENTER, jTfProjectDirectory);
        jBtnLoadProject = new JButton(" ... ");
		jBtnLoadProject.addActionListener(this);
		projectPanel.add(BorderLayout.EAST, jBtnLoadProject);
		
		filePanels.add(projectPanel);
		
        // constraints
        JPanel constraintPanel = new JPanel(new BorderLayout());
		constraintPanel.setBorder(BorderFactory.createTitledBorder(" Constraints "));
		jTfConstraints = new JTextField("");
		constraintPanel.add(BorderLayout.CENTER, jTfConstraints );
        jBtnLoadConstraints = new JButton(" ... ");
		jBtnLoadConstraints.addActionListener(this);
		constraintPanel.add(BorderLayout.EAST, jBtnLoadConstraints);
        
		filePanels.add(constraintPanel);

        // xmi
        JPanel xmiPanel = new JPanel(new BorderLayout());
		xmiPanel.setBorder(BorderFactory.createTitledBorder(" URL of XMI file "));
        jTfXmiSource = new JTextField("");
		xmiPanel.add(BorderLayout.CENTER, jTfXmiSource);
        jBtnLoadXmi = new JButton(" ... ");
		jBtnLoadXmi.addActionListener(this);
        xmiPanel.add(BorderLayout.EAST, jBtnLoadXmi);
		filePanels.add(xmiPanel);
		
		jpData.add(filePanels);
		jpData.add(Box.createVerticalStrut(10));
		
		// object relational mapping
        JPanel ormPanel = new JPanel();
		ormPanel.setLayout(new BoxLayout(ormPanel, BoxLayout.Y_AXIS));
        ormPanel.setBorder(BorderFactory.createTitledBorder(" Object relational mapping "));
		ormPanel.setLayout(new GridLayout(3, 1));

        jRbInheritance0 = new JRadioButton("one table per class");
		jRbInheritance1 = new JRadioButton("one table per hierarchy");
		jRbInheritance2 = new JRadioButton("one table per leaf class");
		
        ButtonGroup bgInheritance = new ButtonGroup();
        bgInheritance.add(jRbInheritance0);
        bgInheritance.add(jRbInheritance1);
        bgInheritance.add(jRbInheritance2);
		jRbInheritance0.setSelected(true);
        ormPanel.add(jRbInheritance0);
		ormPanel.add(jRbInheritance1);
		ormPanel.add(jRbInheritance2);
		
		jpData.add(ormPanel);
		jpData.add(Box.createVerticalStrut(10));
		
		// execute
        JPanel executePanel = new JPanel(new GridLayout(1,4));
        jBtnExecute = new JButton("Execute Project");
		jBtnExecute.addActionListener(this);
		jBtnExecute.setEnabled(false);
		executePanel.add(new JPanel());
		executePanel.add(new JPanel());
		executePanel.add(new JPanel());
		executePanel.add(jBtnExecute);
		
		jpData.add(executePanel);
		
        return jpMain;
	}
	
	/**
	 * Initializes the otuput pane
	 * 
	 * @return the initialized output pane
	 */
	private JPanel getOutputPane() {
		JPanel jpMain = new JPanel(new BorderLayout());
		jpMain.setBorder(BorderFactory.createEtchedBorder());
		
		JTabbedPane jTpResult = new JTabbedPane();
        jTpResult.setTabPlacement(SwingConstants.TOP);

        jTaResultTables = new JTextArea("");
		jTaResultTables.setEditable(false);
		jTaResultTables.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane sp1 = new JScrollPane(jTaResultTables);
		jTpResult.addTab("Table Schema", sp1);

        jTaResultObjectViews = new JTextArea("");
		jTaResultObjectViews.setEditable(false);
		jTaResultObjectViews.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane sp2 = new JScrollPane(jTaResultObjectViews);
		jTpResult.addTab("Object Views", sp2);

        jTaResultIntegrityViews = new JTextArea("");
		jTaResultIntegrityViews.setEditable(false);
		jTaResultIntegrityViews.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane sp3 = new JScrollPane(jTaResultIntegrityViews);
		jTpResult.addTab("Integrity Views", sp3);

		jpMain.add(jTpResult);
		
		jPOutputPane = jpMain;

        return jpMain;
	}

	/**
	 * Delegates action eevents to the various action handlers 
	 */
	public void actionPerformed(ActionEvent event) {
		Object eventSrc = event.getSource();
		
		if (eventSrc == jBtnLoadConstraints) {
			onLoadConstraints();
		}
		else if (eventSrc == jBtnLoadProject) {
			onLoadProject();
		}
		else if (eventSrc == jBtnLoadXmi) {
			onLoadXmi();
		}
		else if (eventSrc == jBtnExecute) {
			onExecute();
		}
	}

	/**
	 * Action handler for the "Execute Project" button.
	 */
	private void onExecute() {
		ORMappingMode ormClassToTableMode = ORMappingMode.oneTablePerClass;
		SQLBuilder sqlBuilder = new OracleSQLBuilder();
		
		// get the mapping mode
        if (jRbInheritance0.isSelected()) {
            ormClassToTableMode = ORMappingMode.oneTablePerClass;
        } else if (jRbInheritance1.isSelected()) {
            ormClassToTableMode = ORMappingMode.oneTablePerHierarchy;
        } else if (jRbInheritance2.isSelected()) {
            ormClassToTableMode = ORMappingMode.oneTablePerLeafClass;
        }
		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		try {
			String xmiFileUrl = (new File(xmiSourcePath)).toURL().toString();
			System.err.println("Loading XMI ...");
			model = new OclModel(MetaModelConst.UML15, xmiFileUrl);
			model.beginTrans(true);
			
			System.err.println("Creating OR mapping...");
			// initialize object relational mapping
	        ORMapping orm = new ORMappingImpl(model, ormClassToTableMode, 1, "int", false);
	
			System.err.println("Generating DDL...");
			SchemaGenerator schemaGen = new SchemaGenerator();
			schemaGen.createDDL(orm, sqlBuilder);
			String tableSchema = schemaGen.getDDLScript();
			
			System.err.println("Creating OV schema...");
	        // initialize object view schema
	        ObjectViewSchema ovSchema = new ObjectViewSchema(orm, sqlBuilder);
			
			System.err.println("Generating OV schema...");
			String objectViews = ovSchema.getViewDefinitions();
			
			try {				
				System.err.println("Reading OCL file " + constraintsFilePath + "...");
				Lexer lexer = new Lexer (new PushbackReader(new BufferedReader(new FileReader(constraintsFilePath)), 1024));
				
				System.err.println("Parsing OCL file...");
				Parser parser = new Parser(lexer);
				Start cst = parser.parse();
				
				System.err.println("Generating AST...");
				LAttrAstGenerator astgen = new LAttrAstGenerator(model);
				Heritage hrtg = new Heritage();
				
				cst.apply(astgen, hrtg);
			} catch (Exception e) {
	            e.printStackTrace(System.err);
	        }
			
			String sqlCodeGenPatternCatalogue = (ClassLoader.getSystemClassLoader().getResource("sql/OCL2SQL4Oracle.xml")).toString();
			SQLCodeGenerator sqlgen = new SQLCodeGenerator(sqlCodeGenPatternCatalogue);
			sqlgen.setORMappingScheme(ovSchema);
			
			String integrityViews = "";
			
			System.err.println("Generating Integrity views...");
			
			Iterator i = ((Uml15Package)model.getModel()).getCore().getClassifier().refAllOfType().iterator();
			while (i.hasNext()) {
				Collection expressions = ((Classifier)i.next()).getExpressionInOclA(); //((Uml15Package)model.getModel()).getUml15ocl().getOcl().getExpressions().getAContextualClassifierExpressionInOcl().getExpressionInOcl((Classifier)i.next());
				Iterator ie = expressions.iterator();
				while (ie.hasNext()) {
					integrityViews += sqlgen.getCode((ExpressionInOcl)ie.next());
					integrityViews += "\n";
					sqlgen.reset();
				}
			}
			
			FileWriter fw;
			
	        try {
				System.err.println("Writing " + projectDirectoryPath + "tables.sql" + "...");
	            fw = new FileWriter(projectDirectoryPath + "tables.sql");
	            fw.write(tableSchema);
	            fw.close();
	
				System.err.println("Writing " + projectDirectoryPath + "object_views.sql" + "...");
	            fw = new FileWriter(projectDirectoryPath + "object_views.sql");
	            fw.write(objectViews);
	            fw.close();
				
				System.err.println("Writing " + projectDirectoryPath + "integrity_views.sql" + "...");
	            fw = new FileWriter(projectDirectoryPath + "integrity_views.sql");
	            fw.write(integrityViews);
	            fw.close();
	        } catch(Exception e) {
	            System.err.println("Could not write all results!");
	        }
	
			System.err.println("Finished!");
			
			jTaResultTables.setAutoscrolls(false);
			jTaResultTables.setText(tableSchema);
			
			
	        jTaResultObjectViews.setText(objectViews);
	        jTaResultIntegrityViews.setText(integrityViews);
			
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			
			jTabs.setSelectedComponent(jPOutputPane);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (model != null) {
				model.close();
				model = null;
			}
		}
	}

	/**
	 * Action handler for the "Choose constraints file" button.
	 */
	private void onLoadConstraints() {
		File constraintsFile = null;
		int retVal = 0;
		JFileChooser fileChooser = new JFileChooser();

        // show open dialog for *.ocl files
        fileChooser.setFileFilter(new FileFilter () {
			public boolean accept(File file) {
				return file.isDirectory() || file.getName().toLowerCase().endsWith(".ocl");
			}
			public String getDescription() {
				return "OCL constraint files (*.ocl)";
			}
			
        });
		fileChooser.setDialogTitle("Choose constraint file");
		fileChooser.setCurrentDirectory(new File(projectDirectoryPath));
        retVal = fileChooser.showOpenDialog(this);
		
		// check for selected file
		constraintsFile = fileChooser.getSelectedFile();
        if ((constraintsFile != null) && (retVal == JFileChooser.APPROVE_OPTION)) {
			setConstraintsFilePath(constraintsFile.getAbsolutePath());
        }
	}
	
	/**
	 * Action handler for the "Choose project directory" button.
	 */
	private void onLoadProject() {
		File projectPath = null;
		int retVal = 0;
		JFileChooser fileChooser = new JFileChooser();

        // show open dialog
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Choose Project Directory");
		retVal = fileChooser.showOpenDialog(this);

        // check for selected directory
		projectPath = fileChooser.getSelectedFile();
        if ((projectPath != null) && (retVal ==  JFileChooser.APPROVE_OPTION)) {
			setProjectDirectoryPath(projectPath.getAbsolutePath());
        }
	}
	
	/**
	 * Action handler for the "Choose XMI file" button.
	 */
	private void onLoadXmi() {
		File xmiFile = null;
		int retVal = 0;
		JFileChooser fileChooser = new JFileChooser();

        // show open dialog for *.xmi files
        fileChooser.setFileFilter(new FileFilter () {
			public boolean accept(File file) {
				return file.isDirectory() || file.getName().toLowerCase().endsWith(".xmi");
			}
			public String getDescription() {
				return "XMI files (*.xmi)";
			}
			
        });
        fileChooser.setDialogTitle("Choose XMI file");
		fileChooser.setCurrentDirectory(new File(projectDirectoryPath));
        retVal = fileChooser.showOpenDialog(this);
		
		// check for selected file
		xmiFile = fileChooser.getSelectedFile();
        if ((xmiFile  != null) && (retVal == JFileChooser.APPROVE_OPTION)) {
			setXmiSourcePath(xmiFile.getAbsolutePath());
        }
	}
	
	/**
	 * Handles the state of the "Execute Project" button
	 */
	private void onProjectSettingsChanged() {
		if (projectDirectoryPath.length() > 0 &&
				constraintsFilePath.length() > 0 &&
				xmiSourcePath.length() > 0) {
			jBtnExecute.setEnabled(true);
		}
		else {
			jBtnExecute.setEnabled(false);
		}
	}

	private void setConstraintsFilePath(String constraintsFilePath) {
		jTfConstraints.setText(constraintsFilePath);
		this.constraintsFilePath = constraintsFilePath;
		onProjectSettingsChanged();
	}
	
	private void setProjectDirectoryPath(String projectDirectoryPath) {
		
		this.projectDirectoryPath = projectDirectoryPath;
		
		if (this.projectDirectoryPath.charAt(this.projectDirectoryPath.length()-1) != File.separatorChar) {
			this.projectDirectoryPath += File.separatorChar;
        }
		
		jTfProjectDirectory.setText(this.projectDirectoryPath);
		
		onProjectSettingsChanged();
	}
	
	private void setXmiSourcePath(String xmiSourcePath) {
		jTfXmiSource.setText(xmiSourcePath);
		this.xmiSourcePath = xmiSourcePath;
		onProjectSettingsChanged();
	}
}
