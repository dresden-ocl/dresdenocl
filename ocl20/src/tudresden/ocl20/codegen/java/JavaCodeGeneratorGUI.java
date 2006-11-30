/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL Compiler                                                      *
 * Copyright (C) 2005, 2006 Ronny Brandt (Ronny_Brandt@gmx.de).      *
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
package tudresden.ocl20.codegen.java;



import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;

import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.File;
import java.net.URLClassLoader;

import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import java.awt.GridBagConstraints;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;

import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class JavaCodeGeneratorGUI {

	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="248,31"
	private JPanel jContentPane = null;
	private JTabbedPane jTabbedPane = null;
	private JPanel jPCodeGenTab = null;
	private JPanel jPInputPane = null;
	private JPanel jPModelFile = null;
	private JPanel jPConstraintFile = null;
	private JPanel jPGenerateButton = null;
	private JButton jBtnModelFile = null;
	private JButton jBtnGenerateCode = null;
	private JButton jBtnConstraintFile = null;
	private JTextArea jTaOutput = null;
	private JTextField jTfModelFile = null;
	private JTextField jTfConstraintFile = null;
	private JScrollPane jSpOutputPane = null;
	private JPanel jPInjector = null;
	private JPanel jPInjectorInputPane = null;
	private JPanel jPModelFileInjector = null;
	private JTextField jTfModelFileInjector = null;
	private JButton jBtnModelFileInjector = null;
	private JPanel jPConstraintFileInjector = null;
	private JTextField jTfConstraintFileInjector = null;
	private JButton jBtnConstraintFileInjector = null;
	private JPanel jPJavaFilesInjector = null;
	private JButton jBtnJavaFilesInjector = null;
	private JPanel jPViolationMacro = null;
	private JTextField jTfViolationMacro = null;
	private JTextField jTfJavaFilesInjector = null;
	private JPanel jPOptionsInjector = null;
	private JPanel jPModifyInjector = null;
	private JRadioButton jRbModifyInjecor = null;
	private JRadioButton jRbNewFilesInjector = null;
	private ButtonGroup bgModifyInjector = null;  //  @jve:decl-index=0:
	private ButtonGroup bgModifyRevEng = null;  //  @jve:decl-index=0:
	private JPanel jPanel = null;
	private JPanel jPGenerateButtonInjector = null;
	private JButton jBtnGenerateCodeInjcetor = null;
	private JPanel jPReverseEngeneering = null;
	private JPanel jPJavaFilesRevEng = null;
	private JTextField jTfJavaFilesRevEng = null;
	private JButton jBtnJavaFilesRevEng = null;
	private JPanel jPOptionsRevEng = null;
	private JPanel jPModifyRevEng = null;
	private JRadioButton jRbModifyRevEng = null;
	private JRadioButton jRbNewFilesRevEng = null;
	private JPanel jPanel1 = null;
	private JPanel jPRevEngInputPane = null;
	private JPanel jPRevEngButton = null;
	private JButton jBtnRevEng = null;
	private JFileChooser fc = new JFileChooser();
	public JavaCodeGeneratorGUI() {
		jFrame = getJFrame();
		jFrame.setVisible(true);
	}

	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	private JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setSize(new Dimension(481, 627));
			jFrame.setTitle("JavaCodeGenerator");
			jFrame.setContentPane(getJContentPane());
			jFrame.addWindowListener(new java.awt.event.WindowAdapter() {
				public void windowClosing(java.awt.event.WindowEvent e) {
					System.exit(0);
				}
			});
		}
		return jFrame;
	}

	/**
	 * This method initializes jContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJTabbedPane(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.addTab("CodeGenerator", null, getJPCodeGenTab(), null);
			jTabbedPane.addTab("CodeInjector", null, getJPInjector(), null);
			jTabbedPane.addTab("ReverseEngineering", null, getJPReverseEngeneering(), null);
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jPCodeGenTab	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPCodeGenTab() {
		if (jPCodeGenTab == null) {
			jPCodeGenTab = new JPanel();
			jPCodeGenTab.setLayout(new BorderLayout());
			jPCodeGenTab.add(getJPInputPane(), BorderLayout.NORTH);
			jPCodeGenTab.add(getJSpOutputPane(), BorderLayout.CENTER);
		}
		return jPCodeGenTab;
	}

	/**
	 * This method initializes jPInputPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPInputPane() {
		if (jPInputPane == null) {
			jPInputPane = new JPanel();
			jPInputPane.setLayout(new BoxLayout(getJPInputPane(), BoxLayout.Y_AXIS));
			jPInputPane.add(getJPModelFile(), null);
			jPInputPane.add(getJPConstraintFile(), null);
			jPInputPane.add(getJPGenerateButton(), null);
		}
		return jPInputPane;
	}

	/**
	 * This method initializes jPModelFile	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPModelFile() {
		if (jPModelFile == null) {
			BorderLayout borderLayout4 = new BorderLayout();
			borderLayout4.setHgap(5);
			borderLayout4.setVgap(5);
			jPModelFile = new JPanel();
			jPModelFile.setLayout(borderLayout4);
			jPModelFile.setBorder(BorderFactory.createTitledBorder(null, "ModelFile", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPModelFile.add(getJTfModelFile(), BorderLayout.CENTER);
			jPModelFile.add(getJBtnModelFile(), BorderLayout.EAST);
		}
		return jPModelFile;
	}

	/**
	 * This method initializes jPConstraintFile	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPConstraintFile() {
		if (jPConstraintFile == null) {
			BorderLayout borderLayout5 = new BorderLayout();
			borderLayout5.setHgap(5);
			borderLayout5.setVgap(5);
			jPConstraintFile = new JPanel();
			jPConstraintFile.setLayout(borderLayout5);
			jPConstraintFile.setBorder(BorderFactory.createTitledBorder(null, "ConstraintFile", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPConstraintFile.add(getJTfConstraintFile(), BorderLayout.CENTER);
			jPConstraintFile.add(getJBtnConstraintFile(), BorderLayout.EAST);
		}
		return jPConstraintFile;
	}

	/**
	 * This method initializes jPGenerateButton	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPGenerateButton() {
		if (jPGenerateButton == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(java.awt.FlowLayout.RIGHT);
			jPGenerateButton = new JPanel();
			jPGenerateButton.setLayout(flowLayout);
			jPGenerateButton.add(getJBtnGenerateCode(), null);
		}
		return jPGenerateButton;
	}

	/**
	 * This method initializes jBtnModelFile	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJBtnModelFile() {
		if (jBtnModelFile == null) {
			jBtnModelFile = new JButton();
			jBtnModelFile.setText("Browse");
			jBtnModelFile.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					fc.setDialogTitle("Please select model file ...");
          FileFilter ff = new FileFilter(){
						public String getDescription() {
							return "Model file (*.xmi)";
						}
						public boolean accept(File f) {
							if (f.isDirectory())
								return true;
							if (f.isFile())
								if (f.getName().toLowerCase().endsWith(".xmi"))
									return true;
							return false;
						}
					};
					fc.resetChoosableFileFilters();
					fc.setFileFilter(ff);
					int returnVal = fc.showOpenDialog(jFrame);
					if (returnVal == JFileChooser.APPROVE_OPTION)
						jTfModelFile.setText(fc.getSelectedFile().getPath());
				}
			});
		}
		return jBtnModelFile;
	}

	/**
	 * This method initializes jBtnGenerateCode	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJBtnGenerateCode() {
		if (jBtnGenerateCode == null) {
			jBtnGenerateCode = new JButton();
			jBtnGenerateCode.setHorizontalAlignment(SwingConstants.RIGHT);
			jBtnGenerateCode.setText("Generate Code");
			jBtnGenerateCode.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String xmiFile = jTfModelFile.getText();
					String oclFile = jTfConstraintFile.getText();
					if (!xmiFile.equals("") && !oclFile.equals(""))
					{
						JavaClassCodeGenerator jccg = new JavaClassCodeGenerator(xmiFile, oclFile, true);
						JOptionPane.showMessageDialog(getJFrame(), "Code generation done.");
						String code = jccg.generateCodeString();
						jTaOutput.setText(code);
					}
				}
			});
		}
		return jBtnGenerateCode;
	}

	/**
	 * This method initializes jBtnConstraintFile	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJBtnConstraintFile() {
		if (jBtnConstraintFile == null) {
			jBtnConstraintFile = new JButton();
			jBtnConstraintFile.setText("Browse");
			jBtnConstraintFile.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					fc.setDialogTitle("Please select constraint file ...");
					FileFilter ff = new FileFilter(){
						public String getDescription() {
							return "Constraint file (*.ocl)";
						}
						public boolean accept(File f) {
							if (f.isDirectory())
								return true;
							if (f.isFile())
								if (f.getName().toLowerCase().endsWith(".ocl"))
									return true;
							return false;
						}
					};
					fc.resetChoosableFileFilters();
					fc.setFileFilter(ff);
					int returnVal = fc.showOpenDialog(jFrame);
					if (returnVal == JFileChooser.APPROVE_OPTION)
						jTfConstraintFile.setText(fc.getSelectedFile().getPath());
				}
			});
		}
		return jBtnConstraintFile;
	}

	/**
	 * This method initializes jTaOutput	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTaOutput() {
		if (jTaOutput == null) {
			jTaOutput = new JTextArea();
			jTaOutput.setEditable(true);
			jTaOutput.setLineWrap(false);
			jTaOutput.setWrapStyleWord(true);
		}
		return jTaOutput;
	}

	/**
	 * This method initializes jTfModelFile	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTfModelFile() {
		if (jTfModelFile == null) {
			jTfModelFile = new JTextField();
		}
		return jTfModelFile;
	}

	/**
	 * This method initializes jTfConstraintFile	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTfConstraintFile() {
		if (jTfConstraintFile == null) {
			jTfConstraintFile = new JTextField();
			jTfConstraintFile.setPreferredSize(new Dimension(350, 20));
		}
		return jTfConstraintFile;
	}

	/**
	 * This method initializes jSpOutputPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJSpOutputPane() {
		if (jSpOutputPane == null) {
			jSpOutputPane = new JScrollPane();
			jSpOutputPane.setViewportView(getJTaOutput());
		}
		return jSpOutputPane;
	}

	/**
	 * This method initializes jPInjector	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPInjector() {
		if (jPInjector == null) {
			jPInjector = new JPanel();
			jPInjector.setLayout(new BorderLayout());
			jPInjector.add(getJPInjectorInputPane(), BorderLayout.NORTH);
		}
		return jPInjector;
	}

	/**
	 * This method initializes jPInjectorInputPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPInjectorInputPane() {
		if (jPInjectorInputPane == null) {
			jPInjectorInputPane = new JPanel();
			jPInjectorInputPane.setLayout(new BoxLayout(getJPInjectorInputPane(), BoxLayout.Y_AXIS));
			jPInjectorInputPane.add(getJPModelFileInjector(), null);
			jPInjectorInputPane.add(getJPConstraintFileInjector(), null);
			jPInjectorInputPane.add(getJPJavaFilesInjector(), null);
			jPInjectorInputPane.add(getJPViolationMacro(), null);
			jPInjectorInputPane.add(getJPOptionsInjector(), null);
			jPInjectorInputPane.add(getJPGenerateButtonInjector(), null);
		}
		return jPInjectorInputPane;
	}

	/**
	 * This method initializes jPModelFileInjector	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPModelFileInjector() {
		if (jPModelFileInjector == null) {
			BorderLayout borderLayout3 = new BorderLayout();
			borderLayout3.setHgap(5);
			borderLayout3.setVgap(5);
			jPModelFileInjector = new JPanel();
			jPModelFileInjector.setLayout(borderLayout3);
			jPModelFileInjector.setBorder(BorderFactory.createTitledBorder(null, "ModelFile", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPModelFileInjector.add(getJTfModelFileInjector(), BorderLayout.CENTER);
			jPModelFileInjector.add(getJBtnModelFileInjector(), BorderLayout.EAST);
		}
		return jPModelFileInjector;
	}

	/**
	 * This method initializes jTfModelFileInjector	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTfModelFileInjector() {
		if (jTfModelFileInjector == null) {
			jTfModelFileInjector = new JTextField();
		}
		return jTfModelFileInjector;
	}

	/**
	 * This method initializes jBtnModelFileInjector	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJBtnModelFileInjector() {
		if (jBtnModelFileInjector == null) {
			jBtnModelFileInjector = new JButton();
			jBtnModelFileInjector.setText("Browse");
			jBtnModelFileInjector.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					fc.setDialogTitle("Please select model file ...");
					FileFilter ff = new FileFilter(){
						public String getDescription() {
							return "Model file (*.xmi)";
						}
						public boolean accept(File f) {
							if (f.isDirectory())
								return true;
							if (f.isFile())
								if (f.getName().toLowerCase().endsWith(".xmi"))
									return true;
							return false;
						}
					};
					fc.resetChoosableFileFilters();
					fc.setFileFilter(ff);
					int returnVal = fc.showOpenDialog(jFrame);
					if (returnVal == JFileChooser.APPROVE_OPTION)
						jTfModelFileInjector.setText(fc.getSelectedFile().getPath());
				}
			});
		}
		return jBtnModelFileInjector;
	}

	/**
	 * This method initializes jPConstraintFileInjector	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPConstraintFileInjector() {
		if (jPConstraintFileInjector == null) {
			BorderLayout borderLayout2 = new BorderLayout();
			borderLayout2.setHgap(5);
			borderLayout2.setVgap(5);
			jPConstraintFileInjector = new JPanel();
			jPConstraintFileInjector.setLayout(borderLayout2);
			jPConstraintFileInjector.setBorder(BorderFactory.createTitledBorder(null, "ConstraintFile", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPConstraintFileInjector.add(getJTfConstraintFileInjector(), BorderLayout.CENTER);
			jPConstraintFileInjector.add(getJBtnConstraintFileInjector(), BorderLayout.EAST);
		}
		return jPConstraintFileInjector;
	}

	/**
	 * This method initializes jTfConstraintFileInjector	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTfConstraintFileInjector() {
		if (jTfConstraintFileInjector == null) {
			jTfConstraintFileInjector = new JTextField();
			jTfConstraintFileInjector.setPreferredSize(new Dimension(350, 20));
		}
		return jTfConstraintFileInjector;
	}

	/**
	 * This method initializes jBtnConstraintFileInjector	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJBtnConstraintFileInjector() {
		if (jBtnConstraintFileInjector == null) {
			jBtnConstraintFileInjector = new JButton();
			jBtnConstraintFileInjector.setText("Browse");
			jBtnConstraintFileInjector
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							fc.setDialogTitle("Please select constraint file ...");
							FileFilter ff = new FileFilter(){
								public String getDescription() {
									return "Constraint file (*.ocl)";
								}
								public boolean accept(File f) {
									if (f.isDirectory())
										return true;
									if (f.isFile())
										if (f.getName().toLowerCase().endsWith(".ocl"))
											return true;
									return false;
								}
							};
    					fc.resetChoosableFileFilters();
							fc.setFileFilter(ff);
							int returnVal = fc.showOpenDialog(jFrame);
							if (returnVal == JFileChooser.APPROVE_OPTION)
								jTfConstraintFileInjector.setText(fc.getSelectedFile().getPath());
						}
					});
		}
		return jBtnConstraintFileInjector;
	}

	/**
	 * This method initializes jPJavaFilesInjector	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPJavaFilesInjector() {
		if (jPJavaFilesInjector == null) {
			BorderLayout borderLayout = new BorderLayout();
			borderLayout.setHgap(5);
			borderLayout.setVgap(5);
			jPJavaFilesInjector = new JPanel();
			jPJavaFilesInjector.setLayout(borderLayout);
			jPJavaFilesInjector.setBorder(BorderFactory.createTitledBorder(null, "JavaFile(s)", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPJavaFilesInjector.add(getJTfJavaFilesInjector(), BorderLayout.CENTER);
			jPJavaFilesInjector.add(getJBtnJavaFilesInjector(), BorderLayout.EAST);
		}
		return jPJavaFilesInjector;
	}

	/**
	 * This method initializes jBtnJavaFilesInjector	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJBtnJavaFilesInjector() {
		if (jBtnJavaFilesInjector == null) {
			jBtnJavaFilesInjector = new JButton();
			jBtnJavaFilesInjector.setText("Browse");
			jBtnJavaFilesInjector.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					fc.setDialogTitle("Please select java file(s) to inject ...");
					FileFilter ff = new FileFilter(){
						public String getDescription() {
							return "Java files (*.java)";
						}
						public boolean accept(File f) {
							if (f.isDirectory())
								return true;
							if (f.isFile())
								if (f.getName().toLowerCase().endsWith(".java"))
									return true;
							return false;
						}
					};
					fc.resetChoosableFileFilters();
					fc.setFileFilter(ff);
					fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
					fc.setMultiSelectionEnabled(true);
					int returnVal = fc.showOpenDialog(jFrame);
					if (returnVal == JFileChooser.APPROVE_OPTION)
					{
						StringBuffer files = new StringBuffer();
						File file = fc.getSelectedFiles()[0];
						if (file.isDirectory())
							files.append("\"" + file.getPath() + "\\*.java\"");
						else
							files.append("\"" + file.getPath() + "\"");
						for (int i = 1; i < fc.getSelectedFiles().length; i++)
						{
							files.append(";");
							file = fc.getSelectedFiles()[i];
							if (file.isDirectory())
								files.append("\"" + file.getPath() + "\\*.java\"");
							else
								files.append("\"" + file.getPath() + "\"");
						}
						jTfJavaFilesInjector.setText(files.toString());
					}
				}
			});
		}
		return jBtnJavaFilesInjector;
	}

	/**
	 * This method initializes jPViolationMacro	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPViolationMacro() {
		if (jPViolationMacro == null) {
			BorderLayout borderLayout1 = new BorderLayout();
			borderLayout1.setHgap(5);
			borderLayout1.setVgap(5);
			jPViolationMacro = new JPanel();
			jPViolationMacro.setLayout(borderLayout1);
			jPViolationMacro.setBorder(BorderFactory.createTitledBorder(null, "ViolationMacro", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPViolationMacro.add(getJTfViolationMacro(), BorderLayout.NORTH);
		}
		return jPViolationMacro;
	}

	/**
	 * This method initializes jTfViolationMacro	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTfViolationMacro() {
		if (jTfViolationMacro == null) {
			jTfViolationMacro = new JTextField();
			jTfViolationMacro.setPreferredSize(new Dimension(4, 26));
			jTfViolationMacro.setText("System.out.println");
		}
		return jTfViolationMacro;
	}

	/**
	 * This method initializes jTfJavaFilesInjector	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTfJavaFilesInjector() {
		if (jTfJavaFilesInjector == null) {
			jTfJavaFilesInjector = new JTextField();
		}
		return jTfJavaFilesInjector;
	}

	/**
	 * This method initializes jPOptionsInjector	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPOptionsInjector() {
		if (jPOptionsInjector == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(1);
			gridLayout.setHgap(5);
			gridLayout.setVgap(5);
			gridLayout.setColumns(2);
			jPOptionsInjector = new JPanel();
			jPOptionsInjector.setLayout(gridLayout);
			jPOptionsInjector.setBorder(BorderFactory.createTitledBorder(null, "Options", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPOptionsInjector.add(getJPModifyInjector(), null);
			jPOptionsInjector.add(getJPanel(), null);
		}
		return jPOptionsInjector;
	}

	/**
	 * This method initializes jPModifyInjector	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPModifyInjector() {
		if (jPModifyInjector == null) {
			GridLayout gridLayout1 = new GridLayout();
			gridLayout1.setRows(2);
			gridLayout1.setColumns(1);
			jPModifyInjector = new JPanel();
			jPModifyInjector.setLayout(gridLayout1);
			jPModifyInjector.add(getJRbModifyInjecor(), null);
			jPModifyInjector.add(getJRbNewFilesInjector(), null);
			groupButtonsModifyInjector();
		}
		return jPModifyInjector;
	}
	
	private void groupButtonsModifyInjector() {
		if (bgModifyInjector == null) {
			bgModifyInjector = new ButtonGroup();
			bgModifyInjector.add(getJRbModifyInjecor());
			bgModifyInjector.add(getJRbNewFilesInjector());
		}
	}

	/**
	 * This method initializes jRbModifyInjecor	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRbModifyInjecor() {
		if (jRbModifyInjecor == null) {
			jRbModifyInjecor = new JRadioButton();
			jRbModifyInjecor.setText("Modify files");
			jRbModifyInjecor.setMnemonic(KeyEvent.VK_UNDEFINED);
		}
		return jRbModifyInjecor;
	}

	/**
	 * This method initializes jRbNewFilesInjector	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRbNewFilesInjector() {
		if (jRbNewFilesInjector == null) {
			jRbNewFilesInjector = new JRadioButton();
			jRbNewFilesInjector.setText("Create new files");
			jRbNewFilesInjector.setSelected(true);
		}
		return jRbNewFilesInjector;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
		}
		return jPanel;
	}

	/**
	 * This method initializes jPGenerateButtonInjector	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPGenerateButtonInjector() {
		if (jPGenerateButtonInjector == null) {
			FlowLayout flowLayout1 = new FlowLayout();
			flowLayout1.setAlignment(java.awt.FlowLayout.RIGHT);
			jPGenerateButtonInjector = new JPanel();
			jPGenerateButtonInjector.setLayout(flowLayout1);
			jPGenerateButtonInjector.add(getJBtnGenerateCodeInjcetor(), null);
		}
		return jPGenerateButtonInjector;
	}

	/**
	 * This method initializes jBtnGenerateCodeInjcetor	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJBtnGenerateCodeInjcetor() {
		if (jBtnGenerateCodeInjcetor == null) {
			jBtnGenerateCodeInjcetor = new JButton();
			jBtnGenerateCodeInjcetor.setText("Generate and inject code");
			jBtnGenerateCodeInjcetor.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ArrayList argsList = new ArrayList();
					String xmiFile = jTfModelFileInjector.getText();
					String oclFile = jTfConstraintFileInjector.getText();
					String javaFiles = jTfJavaFilesInjector.getText();
					String violationMacro = jTfViolationMacro.getText();
					if (!xmiFile.equals("") && !oclFile.equals("") && !javaFiles.equals("") && !violationMacro.equals(""))
					{
						argsList.add("-mf");
						argsList.add(xmiFile);
						argsList.add("-cf");
						argsList.add(oclFile);
						argsList.add("-vm");
						argsList.add(violationMacro);
						if (jRbModifyInjecor.isSelected())
							argsList.add("-m");
						java.util.List jFiles = java.util.Arrays.asList(javaFiles.replaceAll("\"", "").split(";"));
						for (int j = 0; j < jFiles.size(); j++)
							argsList.add(((String)jFiles.get(j)).trim());
					}

					tudresden.ocl20.injection.ocl20.Main.main((String[])argsList.toArray(new String[0]));
					JOptionPane.showMessageDialog(getJFrame(), "Code injection done.");
				}
			});
		}
		return jBtnGenerateCodeInjcetor;
	}

	/**
	 * This method initializes jPReverseEngeneering	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPReverseEngeneering() {
		if (jPReverseEngeneering == null) {
			jPReverseEngeneering = new JPanel();
			jPReverseEngeneering.setLayout(new BorderLayout());
			jPReverseEngeneering.add(getJPRevEngInputPane(), BorderLayout.NORTH);
		}
		return jPReverseEngeneering;
	}

	/**
	 * This method initializes jPJavaFilesRevEng	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPJavaFilesRevEng() {
		if (jPJavaFilesRevEng == null) {
			BorderLayout borderLayout6 = new BorderLayout();
			borderLayout6.setHgap(5);
			borderLayout6.setVgap(5);
			jPJavaFilesRevEng = new JPanel();
			jPJavaFilesRevEng.setLayout(borderLayout6);
			jPJavaFilesRevEng.setBorder(BorderFactory.createTitledBorder(null, "JavaFile(s)", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPJavaFilesRevEng.add(getJTfJavaFilesRevEng(), BorderLayout.CENTER);
			jPJavaFilesRevEng.add(getJBtnJavaFilesRevEng(), BorderLayout.EAST);
		}
		return jPJavaFilesRevEng;
	}

	/**
	 * This method initializes jTfJavaFilesRevEng	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTfJavaFilesRevEng() {
		if (jTfJavaFilesRevEng == null) {
			jTfJavaFilesRevEng = new JTextField();
		}
		return jTfJavaFilesRevEng;
	}

	/**
	 * This method initializes jBtnJavaFilesRevEng	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJBtnJavaFilesRevEng() {
		if (jBtnJavaFilesRevEng == null) {
			jBtnJavaFilesRevEng = new JButton();
			jBtnJavaFilesRevEng.setText("Browse");
			jBtnJavaFilesRevEng.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					fc.setDialogTitle("Please select java file(s) to clean ...");
					FileFilter ffj = new FileFilter(){
						public String getDescription() {
							return "Java files (*.java)";
						}
						public boolean accept(File f) {
							if (f.isDirectory())
								return true;
							if (f.isFile())
								if (f.getName().toLowerCase().endsWith(".java"))
									return true;
							return false;
						}
					};
					FileFilter ffji = new FileFilter(){
						public String getDescription() {
							return "Injected Java files (*.java.injected)";
						}
						public boolean accept(File f) {
							if (f.isDirectory())
								return true;
							if (f.isFile())
								if (f.getName().toLowerCase().endsWith(".java.injected"))
									return true;
							return false;
						}
					};
					FileFilter ffi = new FileFilter(){
						public String getDescription() {
							return "All Injected files (*.injected)";
						}
						public boolean accept(File f) {
							if (f.isDirectory())
								return true;
							if (f.isFile())
								if (f.getName().toLowerCase().endsWith(".injected"))
									return true;
							return false;
						}
					};
					fc.resetChoosableFileFilters();
					fc.addChoosableFileFilter(ffji);
					fc.addChoosableFileFilter(ffi);
					fc.addChoosableFileFilter(ffj);
					fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
					fc.setMultiSelectionEnabled(true);
					int returnVal = fc.showOpenDialog(jFrame);
					if (returnVal == JFileChooser.APPROVE_OPTION)
					{
						StringBuffer files = new StringBuffer();
						File file = fc.getSelectedFiles()[0];
						if (file.isDirectory())
							files.append("\"" + file.getPath() + "\\*.java\"");
						else
							files.append("\"" + file.getPath() + "\"");
						for (int i = 1; i < fc.getSelectedFiles().length; i++)
						{
							files.append(";");
							file = fc.getSelectedFiles()[i];
							if (file.isDirectory())
								files.append("\"" + file.getPath() + "\\*.java\"");
							else
								files.append("\"" + file.getPath() + "\"");
						}
						jTfJavaFilesRevEng.setText(files.toString());
					}
				}
			});
		}
		return jBtnJavaFilesRevEng;
	}

	/**
	 * This method initializes jPOptionsRevEng	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPOptionsRevEng() {
		if (jPOptionsRevEng == null) {
			GridLayout gridLayout2 = new GridLayout();
			gridLayout2.setRows(1);
			gridLayout2.setHgap(5);
			gridLayout2.setVgap(5);
			gridLayout2.setColumns(2);
			jPOptionsRevEng = new JPanel();
			jPOptionsRevEng.setBorder(BorderFactory.createTitledBorder(null, "Options", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPOptionsRevEng.setLayout(gridLayout2);
			jPOptionsRevEng.add(getJPModifyRevEng(), null);
			jPOptionsRevEng.add(getJPanel1(), null);
		}
		return jPOptionsRevEng;
	}

	/**
	 * This method initializes jPModifyRevEng	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPModifyRevEng() {
		if (jPModifyRevEng == null) {
			GridLayout gridLayout11 = new GridLayout();
			gridLayout11.setRows(2);
			gridLayout11.setColumns(1);
			jPModifyRevEng = new JPanel();
			jPModifyRevEng.setLayout(gridLayout11);
			jPModifyRevEng.add(getJRbModifyRevEng(), null);
			jPModifyRevEng.add(getJRbNewFilesRevEng(), null);
			groupButtonsModifyRevEng();
		}
		return jPModifyRevEng;
	}

	private void groupButtonsModifyRevEng() {
		if (bgModifyRevEng == null) {
			bgModifyRevEng = new ButtonGroup();
			bgModifyRevEng.add(getJRbModifyRevEng());
			bgModifyRevEng.add(getJRbNewFilesRevEng());
		}
	}

	/**
	 * This method initializes jRbModifyRevEng	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRbModifyRevEng() {
		if (jRbModifyRevEng == null) {
			jRbModifyRevEng = new JRadioButton();
			jRbModifyRevEng.setMnemonic(KeyEvent.VK_UNDEFINED);
			jRbModifyRevEng.setText("Modify files");
		}
		return jRbModifyRevEng;
	}

	/**
	 * This method initializes jRbNewFilesRevEng	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRbNewFilesRevEng() {
		if (jRbNewFilesRevEng == null) {
			jRbNewFilesRevEng = new JRadioButton();
			jRbNewFilesRevEng.setSelected(true);
			jRbNewFilesRevEng.setText("Create new files");
		}
		return jRbNewFilesRevEng;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GridBagLayout());
		}
		return jPanel1;
	}

	/**
	 * This method initializes jPRevEngInputPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPRevEngInputPane() {
		if (jPRevEngInputPane == null) {
			jPRevEngInputPane = new JPanel();
			jPRevEngInputPane.setLayout(new BoxLayout(getJPRevEngInputPane(), BoxLayout.Y_AXIS));
			jPRevEngInputPane.add(getJPJavaFilesRevEng(), null);
			jPRevEngInputPane.add(getJPOptionsRevEng(), null);
			jPRevEngInputPane.add(getJPRevEngButton(), null);
		}
		return jPRevEngInputPane;
	}

	/**
	 * This method initializes jPRevEngButton	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPRevEngButton() {
		if (jPRevEngButton == null) {
			FlowLayout flowLayout11 = new FlowLayout();
			flowLayout11.setAlignment(FlowLayout.RIGHT);
			jPRevEngButton = new JPanel();
			jPRevEngButton.setLayout(flowLayout11);
			jPRevEngButton.add(getJBtnRevEng(), null);
		}
		return jPRevEngButton;
	}

	/**
	 * This method initializes jBtnRevEng	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJBtnRevEng() {
		if (jBtnRevEng == null) {
			jBtnRevEng = new JButton();
			jBtnRevEng.setText("Reverse Engeneering");
			jBtnRevEng.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ArrayList argsList = new ArrayList();
					String javaFiles = jTfJavaFilesRevEng.getText();
					if (!javaFiles.equals(""))
					{
						argsList.add("-c");
						if (jRbModifyRevEng.isSelected())
							argsList.add("-m");
						java.util.List jFiles = java.util.Arrays.asList(javaFiles.replaceAll("\"", "").split(";"));
						for (int j = 0; j < jFiles.size(); j++)
							argsList.add(((String)jFiles.get(j)).trim());
					}
					

					tudresden.ocl20.injection.ocl20.Main.main((String[])argsList.toArray(new String[0]));
					JOptionPane.showMessageDialog(getJFrame(), "Reverse engeneering done.");
				}
			});
		}
		return jBtnRevEng;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JavaCodeGeneratorGUI jcgg = new JavaCodeGeneratorGUI();
		

	}

}
