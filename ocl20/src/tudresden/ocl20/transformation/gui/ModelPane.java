/*
 * Created on 30.01.2006
 *
 */
package tudresden.ocl20.transformation.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Collection;

import javax.jmi.reflect.RefPackage;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;

import org.netbeans.api.mdr.MDRManager;

import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.ModelManager;
import tudresden.ocl20.core.ModelManagerException;
import tudresden.ocl20.core.OclModel;
import tudresden.ocl20.core.OclModelException;
import tudresden.ocl20.core.Repository;
import tudresden.ocl20.core.RepositoryException;
import tudresden.ocl20.core.RepositoryManager;
import tudresden.ocl20.transformation.TransformationEngine;
import tudresden.ocl20.transformation.util.RepositoryFacade;

public class ModelPane extends JPanel implements ListSelectionListener {

	public static final String id = "ModelPane";

	private static final String MM_SELECT = "Selection of the metamodel";

//	private static final String DESCRIPTION = "Modellselektion\n\nZunächst muss das " +
//			"Metamodell für das Eingangsmodell aus den im Repository verwalteten " +
//			"Metamodellen gewählt werden.\n\nDann werden alle Instanzen diese " +
//			"Metamodells zur Auswahl gestellt. Daraus muss ein konkretes Modell " +
//			"als Eingangsmodell der durchzuführenden metamodellbasierten Transformation " +
//			"bestimmt werden, bevor mit dem Transformationsprozess begonnen werden kann";
	private static final String DESCRIPTION = "Model Selection\n\nAt first the metamodel of " +
			"the input model must be selected. " +
			"\n\nAll instances of this metamodel will be presented on the right. " +
			"One of these instances must be selected as the input entity for the metamodelbased " + 
			"transformation to be executed.";

	private static final String END = "Quit";
	
	private static final String NEXT = "Next >>";
	
	private static final String MMI_SELECT = "Selection of the model";
	
	private static final String LOAD_UML = "Load UmlModel...";
	
	private static final String REMOVE_MODELINST = "Remove model";
	
	private static final String EXPORT_MODELINST = "Export model...";
	
	private static final String NAME_LOADED_UML = "Please enter a name for the loaded model: ";
	
	private ContainerPanel containerPanel1;

	private JButton next;

	private JButton load;

	private JButton remove;

	private JList models;

	private JList metamodels;

	private JTextPane messageField;

	private JTextPane message;

	private JPanel right;

	private JPanel left;

	private JLabel jLabel1;

	private JLabel jLabel2;

	private JScrollPane jScrollPane2;

	private JScrollPane jScrollPane1;

	private JPanel east;

	private JButton end;

	private JButton export;

	/**
	 * This is the default constructor
	 */
	public ModelPane() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridLayout thisLayout = new GridLayout(1, 2);
		this.setLayout(thisLayout);
		this.setPreferredSize(new java.awt.Dimension(826, 452));
		{
			containerPanel1 = new ContainerPanel();
			{
				right = new JPanel();
				FlowLayout rightLayout = new FlowLayout();
				right.setLayout(rightLayout);
				containerPanel1.mainPanel.add(right);
				{
					jLabel1 = new JLabel();
					right.add(jLabel1);
					jLabel1.setText(MM_SELECT);
					jLabel1.setBounds(0, 0, 280, 16);
				}
				{
					jScrollPane1 = new JScrollPane();
					right.add(jScrollPane1);
					jScrollPane1.setPreferredSize(new java.awt.Dimension(300,
							175));
					jScrollPane1.setBounds(67, 31, 181, 240);
					{
						loadMetamodels();
					}
				}
			}
			{
				left = new JPanel();
				BorderLayout buttonPanelLayout = new BorderLayout();
				containerPanel1.buttonPanel.setLayout(buttonPanelLayout);
				{
					next = new JButton();
					GridLayout messagePanelLayout = new GridLayout(1, 1);
					containerPanel1.messagePanel.setLayout(messagePanelLayout);
					containerPanel1.mainPanel.setBorder(new LineBorder(
							new java.awt.Color(0, 0, 0), 1, false));
					{
						message = new JTextPane();
						containerPanel1.messagePanel.add(message);
						message.setText(DESCRIPTION);
						message.setEditable(false);
						message.setFont(new java.awt.Font("Dialog", 1, 12));
						message
								.setBackground(new java.awt.Color(238, 238, 238));
					}
					{
						east = new JPanel();
						containerPanel1.buttonPanel
								.add(east, BorderLayout.EAST);

						{
							end = new JButton();
							end.setText(END);
							end.addMouseListener(new MouseAdapter() {
								public void mouseClicked(MouseEvent evt) {
									if (evt.getButton() == MouseEvent.BUTTON1) {
										TransformationEngine.getInstance()
												.cleanUp();
										MDRManager.getDefault().shutdownAll();
										System.exit(0);
									}
								}
							});
							east.add(end);
						}
						{
							next = new JButton();
							next.setText(NEXT);
							next.addMouseListener(new MouseAdapter() {
								public void mouseClicked(MouseEvent evt) {
									nextMouseClicked(evt);
								}
							});
							east.add(next);
						}
					}
				}
				containerPanel1.mainPanel.add(left);
				{
					jLabel2 = new JLabel();
					left.add(jLabel2);
					jLabel2.setText(MMI_SELECT);
					jLabel2.setBounds(0, 0, 280, 16);
				}
				{
					jScrollPane2 = new JScrollPane();
					left.add(jScrollPane2);
					jScrollPane2.setPreferredSize(new java.awt.Dimension(300,
							175));
					jScrollPane2.setBounds(67, 31, 181, 240);
					jScrollPane2.setRequestFocusEnabled(false);
					{
						loadModels();
					}
				}
				{
					load = new JButton();
					left.add(load);
					load.setText(LOAD_UML);
					load.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							loadMouseClicked(evt);
						}

					});
				}
				{
					remove = new JButton();
					left.add(remove);
					remove.setText(REMOVE_MODELINST);
					remove.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							removeMouseClicked(evt);
						}

					});
					remove.setEnabled(false);
				}

				{
					export = new JButton();
					left.add(export);
					export.setText(EXPORT_MODELINST);
					export.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							exportMouseClicked(evt);
						}

					});
					export.setEnabled(false);
				}

			}
			this.add(containerPanel1);
		}
	}

	protected void exportMouseClicked(MouseEvent evt) {
		String model2export = (String) models.getSelectedValue();
		Repository r = RepositoryManager.getRepository();
		r.beginTrans(true);
		RefPackage rPack = null;

		JFileChooser fc = new JFileChooser();

		fc.setFileFilter(new FileFilter() {
			public boolean accept(File f) {
				return f.isDirectory()
						|| f.getName().toLowerCase().endsWith(".xmi");
			}

			public String getDescription() {
				return "XMI documents";
			}
		});

		int returnVal = fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String xmiPath = fc.getSelectedFile().getPath();
			try {
				rPack = r.getModel(model2export);
				ModelManager.getInstance().saveModel(rPack, xmiPath);
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ModelManagerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			r.endTrans(false);

		}

	}

	private void nextMouseClicked(MouseEvent evt) {
		if (evt.getButton() == MouseEvent.BUTTON1) {
			TransformationEngine.getInstance().setModel_inName(
					(String) models.getSelectedValue());
			TransformationEngine.getInstance().setModel_inType(
					(String) metamodels.getSelectedValue());
			DBGenApplication.getInstance().initContentPane(
					TransformationPane.id);

		}
	}

	private void removeMouseClicked(MouseEvent evt) {
		String model2remove = (String) models.getSelectedValue();
		Repository r = RepositoryManager.getRepository();
		r.beginTrans(true);
		RefPackage rPack = null;
		try {
			rPack = r.getModel(model2remove);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rPack != null) {
			try {
				r.deleteModel(rPack);
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		r.endTrans(false);
		loadModels();
	}

	private void loadMouseClicked(MouseEvent evt) {
		if (evt.getButton() == MouseEvent.BUTTON1) {
			JFileChooser fc = new JFileChooser();

			fc.setFileFilter(new FileFilter() {
				public boolean accept(File f) {
					return f.isDirectory()
							|| f.getName().toLowerCase().endsWith(".xmi");
				}

				public String getDescription() {
					return "XMI documents";
				}
			});

			int returnVal = fc.showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				Repository r = RepositoryManager.getRepository();
				File file = fc.getSelectedFile();
				String name = JOptionPane
						.showInputDialog(this,
								NAME_LOADED_UML);
				if (name.equals("")) {
					name = "umlModel";
				}
				RefPackage model;
				try {
					model = r.getModel(name);
					if (model != null) {
						r.deleteModel(model);
					}
				} catch (RepositoryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				try {
//					System.out.println("LOADING: "
//							+ file.toURL().toExternalForm());
//				} catch (MalformedURLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				OclModel oclModel = null;
				try {

					oclModel = new OclModel(MetaModelConst.UML15, file.toURL()
							.toExternalForm(), name);
				} catch (OclModelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				loadModels();

			}

		}
	}

	private void loadMetamodels() {
		RepositoryFacade rf = RepositoryFacade.getInstance();
		try {
			Collection<String> mms = rf.getMetamodels();
			ListModel metamodelsModel = new DefaultComboBoxModel(mms.toArray());
			metamodels = new JList();
			metamodels.setName("METAMODELS");
			if (mms.size() > 0) {
				// metamodels.setSelectedIndex(2);
			}
			metamodels.addListSelectionListener(this);
			jScrollPane1.setViewportView(metamodels);
			metamodels.setModel(metamodelsModel);
			metamodels.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		} catch (RepositoryException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	private void loadModels() {
		RepositoryFacade rf = RepositoryFacade.getInstance();
		String selectedMM = (String) metamodels.getSelectedValue();
		try {
			ListModel modelsModel;
			if (selectedMM != null) {
				Collection ms = rf.getModelsForType(selectedMM);
				modelsModel = new DefaultComboBoxModel(ms.toArray());
				next.setEnabled(false);
			} else {
				modelsModel = new DefaultComboBoxModel(new String[] {});
				next.setEnabled(false);
			}
			models = new JList();
			models.setName("MODELS");
			models.addListSelectionListener(this);
			jScrollPane2.setViewportView(models);
			models.setModel(modelsModel);
			models.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void valueChanged(ListSelectionEvent lse) {
		JList source = (JList) lse.getSource();
		if (source.getName().equals("METAMODELS")) {
			loadModels();
			remove.setEnabled(false);
			export.setEnabled(false);
			next.setEnabled(false);
		}
		if (source.getName().equals("MODELS")) {
			next.setEnabled(true);
			remove.setEnabled(true);
			export.setEnabled(true);
		}
	}

}
