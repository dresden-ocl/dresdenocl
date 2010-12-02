/*
 * OCL22SQL.java
 * 
 * Copyright (c) 2006 Florian Heidenreich
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

package tudresden.ocl20.codegen.decl.tools.sql;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import tudresden.ocl20.transformation.TransformationEngine;
import tudresden.ocl20.transformation.gui.OptionPanel;
import tudresden.ocl20.transformation.impl.Cwm2DdlImpl;
import tudresden.ocl20.transformation.impl.Uml2CwmImpl;
import tudresden.ocl20.transformation.interfaces.TParameter;
import tudresden.ocl20.transformation.templates.StringTemplateEngineAdapter;

/**
 * Simple GUI for the new experimental OCL22SQL tool 
 * based on the DeclarativeCodeGenerator.
 * 
 * @author Florian Heidenreich
 */
public class OCL22SQL extends JPanel implements ActionListener {

	/**
     *  Main method to start the gui of the OCL22SQL tool.
     */
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("OCL22SQL");
        OCL22SQL ce = new OCL22SQL(mainFrame);
    }

	// gui
    private JFrame jMainFrame;
    private JTabbedPane jTabs;
    
    private JButton jBtnLoadProject;
    private JButton jBtnLoadConstraints;
	private JButton jBtnLoadXmi;
	private JButton jBtnProjectNext;
	
	private JPanel jpConfigurationPane;
	private JButton jBtnConfigurationNext;
	
	private JPanel jpResultPane;
	private JTextArea jTaResultIntegrityViews;
	private JTextArea jTaResultTables;
	private JTextField jTfConstraints;
	private JTextField jTfProjectDirectory;
	private JTextField jTfXmiSource;
	
	// internal
	private OCL22SQLModel model;
	private Map<String, OptionPanel> parameterKey2optionPanel;
	
	private String constraintsFilePath = "";
	private String projectDirectoryPath = "";
	private String xmiSourcePath = "";
	
	/**
     *  The constructor.
     *  @param mainFrame a main frame for the gui
     */
    public OCL22SQL(JFrame mainFrame) {
        model = new OCL22SQLModel();
        
    	if (mainFrame != null) initComponents();
        this.jMainFrame = mainFrame;
		
        jMainFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        jMainFrame.getContentPane().add(this);
		jMainFrame.setSize(600, 400);
		jMainFrame.setVisible(true);
		
		parameterKey2optionPanel = new HashMap<String, OptionPanel>();
    }
    
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
		else if (eventSrc == jBtnProjectNext) {
			onNextProject();
		}
		else if (eventSrc == jBtnConfigurationNext) {
			onNextConfiguration();
		}
	}
	
    /**
	 * Initializes the configuration pane
	 * 
     * @return the configuration pane
     */
	private JPanel getConfigurationPane() {
		JPanel jpMain = new JPanel(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0,0,0,0));
		jpMain.add(scrollPane);
		
		JPanel jpConfiguration = new JPanel();
		jpConfiguration.setBorder(new EmptyBorder(20,20,20,20));
		GridLayout configLayout = new GridLayout(0,1);
		configLayout.setHgap(30);
		configLayout.setVgap(10);
		jpConfiguration.setLayout(configLayout);
		
		JLabel header = new JLabel("Transformation Parameters", JLabel.CENTER);
        header.setFont(new java.awt.Font("Dialog",1,14));
        jpConfiguration.add(header);
        
		scrollPane.setViewportView(jpConfiguration);
		
		Collection<TParameter> parameters = TransformationEngine.getInstance().getRequiredParameters();
	
		if (parameters != null) {
			for (TParameter parameter : parameters) {
				if (parameter.getKey().equals(Uml2CwmImpl.DDL_LANGUAGE.getKey())) {
					parameter = new TParameter(Uml2CwmImpl.DDL_LANGUAGE.getKey(), 
							new String[]{StringTemplateEngineAdapter.POSTGRES, 
										 StringTemplateEngineAdapter.ORACLE,
										 StringTemplateEngineAdapter.STANDARD}, Uml2CwmImpl.DDL_LANGUAGE.getType());
				}
				OptionPanel pPanel = new OptionPanel(parameter);
				parameterKey2optionPanel.put(parameter.getKey(), pPanel);
				jpConfiguration.add(pPanel);

			}
		}
		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
		// next
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.add(Box.createRigidArea(new Dimension(400, 0)));
        buttonPanel.add(Box.createHorizontalGlue());
        jBtnConfigurationNext = new JButton("Next >>");
        jBtnConfigurationNext.addActionListener(this);
		buttonPanel.add(jBtnConfigurationNext);
				
		jpConfiguration.add(buttonPanel);
		
		jpConfigurationPane = jpMain;
		
		return jpMain;
	}

	/**
	 * Initializes the input pane
	 * 
	 * @return the initialized input pane
	 */
	private JPanel getProjectPane() {
		JPanel jpMain = new JPanel(new BorderLayout());
		JPanel jpData = new JPanel();
		
		jpMain.setBorder(BorderFactory.createEtchedBorder());
		
		GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        jpMain.setLayout(gridBag);
		c.weighty = 1;
        c.anchor = GridBagConstraints.NORTH;
        gridBag.setConstraints(jpData, c);
		
		jpMain.add(jpData);
        jpData.setLayout(new BoxLayout(jpData, BoxLayout.Y_AXIS));
        
        JPanel filePanels = new JPanel(new GridLayout(4,1));
				
        JLabel header = new JLabel("Project Settings", JLabel.CENTER);
        header.setFont(new java.awt.Font("Dialog",1,14));
        filePanels.add(header);
        
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
		xmiPanel.setBorder(BorderFactory.createTitledBorder(" Model as XMI file "));
        jTfXmiSource = new JTextField("");
		xmiPanel.add(BorderLayout.CENTER, jTfXmiSource);
        jBtnLoadXmi = new JButton(" ... ");
		jBtnLoadXmi.addActionListener(this);
        xmiPanel.add(BorderLayout.EAST, jBtnLoadXmi);
		filePanels.add(xmiPanel);
		
		jpData.add(filePanels);
		jpData.add(Box.createVerticalStrut(10));
		
		// next
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.add(Box.createRigidArea(new Dimension(400, 0)));
        buttonPanel.add(Box.createHorizontalGlue());
        jBtnProjectNext = new JButton("Next >>");
		jBtnProjectNext.addActionListener(this);
		jBtnProjectNext.setEnabled(false);
		buttonPanel.add(jBtnProjectNext);
		
		jpData.add(buttonPanel);
		
        return jpMain;
	}
	
	/**
	 * Initializes the result pane
	 * 
	 * @return the result pane
	 */
	private JPanel getResultPane() {
		JPanel jpMain = new JPanel(new BorderLayout());
		jpMain.setBorder(BorderFactory.createEtchedBorder());
		
		JTabbedPane jTpResult = new JTabbedPane();
        jTpResult.setTabPlacement(SwingConstants.TOP);

        jTaResultTables = new JTextArea("");
		jTaResultTables.setEditable(false);
		jTaResultTables.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane sp1 = new JScrollPane(jTaResultTables);
		jTpResult.addTab("Table Schema", sp1);

        jTaResultIntegrityViews = new JTextArea("");
		jTaResultIntegrityViews.setEditable(false);
		jTaResultIntegrityViews.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane sp3 = new JScrollPane(jTaResultIntegrityViews);
		jTpResult.addTab("Integrity Views", sp3);

		jpMain.add(jTpResult);
		
		jpResultPane = jpMain;

        return jpMain;
	}

	/**
	 * Initializes the GUI
	 */
	private void initComponents() {
		jTabs = new JTabbedPane();
		jTabs.add("Project", getProjectPane());
		setLayout(new BorderLayout());
        add(jTabs);
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
		JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));

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
	 * Action handler for the "Next" button on the configuration tab.
	 */
	private void onNextConfiguration() {
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		if (jpResultPane != null) {
			jTabs.remove(jpResultPane);
		}
		
		try {
			System.err.println("Running transformation from UML to CWM/MappedModel...");
			model.initTransformationCWM();
			parameterizeTransformationEngine();
			model.invokeTransformationCWM();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,e.getMessage(),"Cannot run CWM transformation!",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return;
		}		
		
		try {
			System.err.println("Running transformation from CWM to target DDL...");
			model.initTransformationDDL(this.projectDirectoryPath + "generated.sql");
			parameterizeTransformationEngine();		
			model.invokeTransformationDDL();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,e.getMessage(),"Cannot run DDL transformation!",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return;
		}
		
		
		System.err.println("Starting AST generation...");
		model.generateAST(constraintsFilePath);

		
		System.err.println("Loading template groups...");
		String selectedSqlDialect = parameterKey2optionPanel.get(Cwm2DdlImpl.DDL_LANGUAGE.getKey()).getSelectedOption();
		LinkedList<String> templateGroups = new LinkedList<String>();
		templateGroups.add("decl/sql/sql92.stg");
		if (selectedSqlDialect.equals(StringTemplateEngineAdapter.POSTGRES)) {
			templateGroups.add("decl/sql/sqlpostgres.stg");
		} else if (selectedSqlDialect.equals(StringTemplateEngineAdapter.ORACLE)) {
			templateGroups.add("decl/sql/sqloracle.stg");
		} // add other dialect here
		
		
		System.err.println("Generating Integrity views...");
		String integrityViews = model.generateCode(templateGroups);
		
		FileWriter fw;
		
        try {
			System.err.println("Writing " + projectDirectoryPath + "integrity_views.sql" + "...");
            fw = new FileWriter(projectDirectoryPath + "integrity_views.sql");
            fw.write(integrityViews);
            fw.close();
        } catch(Exception e) {
            System.err.println("Could not write all results!");
        }

		System.err.println("Finished!");
		
		jTabs.add("Output", getResultPane());
		
		jTaResultTables.setAutoscrolls(false);
		jTaResultTables.setText(model.getTableSchema());
        jTaResultIntegrityViews.setText(integrityViews);
        
		jTabs.setSelectedComponent(jpResultPane);
		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	/**
	 * Action handler for the "Next" button on the project tab
	 */
	private void onNextProject() {
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		if (jpConfigurationPane != null) {
			jTabs.remove(jpConfigurationPane);
		}
		if (jpResultPane != null) {
			jTabs.remove(jpResultPane);
		}
		
		try {
			model.loadModel(xmiSourcePath);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,e.getMessage(),"Cannot load model",JOptionPane.ERROR_MESSAGE);
		}
		try {
			model.initTransformationCWM();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,e.getMessage(),"Cannot load transformation",JOptionPane.ERROR_MESSAGE);
		}
			
		jTabs.add("Configuration", getConfigurationPane());
		jTabs.setSelectedComponent(jpConfigurationPane);		
	}

	/**
	 * Handles the state of the "Next" button on the project tab
	 */
	private void onProjectSettingsChanged() {
		if (projectDirectoryPath.length() > 0 &&
				constraintsFilePath.length() > 0 &&
				xmiSourcePath.length() > 0) {
			jBtnProjectNext.setEnabled(true);
		}
		else {
			jBtnProjectNext.setEnabled(false);
		}
	}
	
	/**
	 * Maps gui options to TransformationEngine parameter settings
	 */
	private void parameterizeTransformationEngine() {
		TransformationEngine te = TransformationEngine.getInstance();		
		Collection<TParameter> parameters = te.getRequiredParameters();
		if (parameters != null) {
			for (TParameter parameter : parameters) {
				String selectedOption = parameterKey2optionPanel.get(parameter.getKey()).getSelectedOption();
				te.setConfigurationParameter(parameter.getKey(), selectedOption);
			}
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
