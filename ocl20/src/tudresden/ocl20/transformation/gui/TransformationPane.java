/*
 * Created on 30.01.2006
 *
 */
package tudresden.ocl20.transformation.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tudresden.ocl20.transformation.TransformationEngine;
import tudresden.ocl20.transformation.TransformationInfo;

public class TransformationPane extends JPanel implements ListSelectionListener {

	private static final String TRANSFORMATION_SELECT = "Transformation Selection";

	private static final String INPUT_MODEL = "Input Model: ";

	private static final String OUT_NAME = "Name: ";

	private static final String OUTPUT_ENTITY = "Output Entity: ";

	private static final String IN_NAME = "Name: ";

//	private static final String DESCRIPTION = "Transformation\n\nNun muss aus den für "
//			+ "diesen Eingangsmodelltyp verfügbaren Modelltransformationen ausgefählt werden."
//			+ " Dadurch wird auch der Typ des Ausgangsmodells determiniert.\n\n";

	private static final String DESCRIPTION = "Transformation\n\nFor the selected input model a " +
			" transformation must be choosen. This also determines the type of the output entity.";
	
	private static final String CANCEL = "Cancel";

	private static final String OUTPUT_NAME_NOT_NULL = "The name for the output entity must not be empty.";

	private static final String NEXT = "Next >>";

	private static final String ERROR_MSG = "During the execution an error occured: ";

	private static final String ERROR_HEAD = "Execution Error";

	public static String id = "TransformationPane";

	private JButton next;

	private JTextPane messageField;

	private JPanel right;

	private JLabel jLabel2;

	private JTextField outModel;

	private JLabel jLabel3;

	private JTextField inModel;

	private JList jList1;

	private JScrollPane transformations;

	private JLabel jLabel1;

	private JPanel left;

	private ContainerPanel containerPanel1;

	private JPanel east;

	private JButton cancel;

	private JTextField outName;

	private JLabel outNameLabel;

	private JTextField inName;

	private JLabel inNameLabel;

	/**
	 * This is the default constructor
	 */
	public TransformationPane() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		BorderLayout thisLayout = new BorderLayout();
		this.setLayout(thisLayout);
		this.setPreferredSize(new java.awt.Dimension(1028, 424));
		{
			containerPanel1 = new ContainerPanel();
			BorderLayout buttonPanelLayout = new BorderLayout();
			containerPanel1.buttonPanel.setLayout(buttonPanelLayout);
			{
				next = new JButton();
				GridLayout messagePanelLayout = new GridLayout(1, 2);
				messagePanelLayout.setColumns(2);
				containerPanel1.messagePanel.setLayout(messagePanelLayout);
				containerPanel1.mainPanel.setBorder(new LineBorder(
						new java.awt.Color(0, 0, 0), 1, false));
				{
					messageField = new JTextPane();
					{
						left = new JPanel();
						containerPanel1.mainPanel.add(left);
						{
							jLabel1 = new JLabel();
							left.add(jLabel1);
							jLabel1.setText(TRANSFORMATION_SELECT);
						}
						{
							transformations = new JScrollPane();
							transformations
									.setPreferredSize(new java.awt.Dimension(
											300, 175));

							left.add(transformations);
							{
								loadTransformations();
							}

						}
					}
					{
						right = new JPanel();
						containerPanel1.mainPanel.add(right);
						right.setLayout(null);
						{
							jLabel2 = new JLabel();
							right.add(jLabel2);
							jLabel2.setText(INPUT_MODEL);
							jLabel2.setHorizontalAlignment(SwingConstants.LEFT);
							jLabel2.setPreferredSize(new java.awt.Dimension(
									246, 107));
							jLabel2.setBounds(20, 71, 108, 19);
						}
						{
							loadInModel();
						}
						{
							jLabel3 = new JLabel();
							right.add(jLabel3);
							jLabel3.setText(OUTPUT_ENTITY);
							jLabel3.setBounds(21, 125, 99, 16);
						}
						{
							outNameLabel = new JLabel();
							right.add(outNameLabel);
							outNameLabel.setText(OUT_NAME);
							outNameLabel.setBounds(76, 149, 37, 16);
						}
						{
							outName = new JTextField();
							right.add(outName);
							outName.setText("out");
							outName.setEditable(true);
							outName.setBounds(127, 148, 192, 20);
						}
						{
							inName = new JTextField();
							right.add(inName);
							inName.setText(TransformationEngine.getInstance()
									.getModel_inName());
							inName.setBounds(126, 93, 192, 20);
							inName.setEditable(false);
						}
						{
							inNameLabel = new JLabel();
							right.add(inNameLabel);
							inNameLabel.setText(IN_NAME);
							inNameLabel.setBounds(77, 95, 37, 16);
						}

						{
							loadOutModel();
						}
					}
					containerPanel1.messagePanel.add(messageField);
					messageField.setText(DESCRIPTION);
					messageField.setEditable(false);
					messageField.setFont(new java.awt.Font("Dialog", 1, 12));
					messageField
							.setBackground(new java.awt.Color(238, 238, 238));
				}
				{
					east = new JPanel();
					containerPanel1.buttonPanel.add(east, BorderLayout.EAST);

					{
						cancel = new JButton();
						cancel.setText(CANCEL);
						cancel.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								if (evt.getButton() == MouseEvent.BUTTON1) {
									DBGenApplication.getInstance()
											.initContentPane(ModelPane.id);
									TransformationEngine.getInstance()
											.cleanUp();
								}
							}
						});
						east.add(cancel);
					}
					{
						next = new JButton();
						next.setText(NEXT);
						next.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								nextMouseClicked(evt);
							}
						});
						next.setEnabled(false);
						east.add(next);
					}
				}

			}
			this.add(containerPanel1, BorderLayout.CENTER);
		}
	}

	private void loadInModel() {
		inModel = new JTextField();
		right.add(inModel);
		inModel.setText(TransformationEngine.getInstance().getModel_inType());
		inModel.setBounds(125, 70, 192, 20);
		inModel.setEditable(false);
	}

	private void loadOutModel() {
		outModel = new JTextField();

		right.add(outModel);

		TransformationInfo tinfo = (TransformationInfo) jList1
				.getSelectedValue();
		if (tinfo != null) {
			outModel.setText(tinfo.getOut_model());
			next.setEnabled(true);
		} else {
			outModel.setText(" --- ");
			next.setEnabled(false);
		}
		outModel.setBounds(126, 123, 193, 20);
		outModel.setEditable(false);
	}

	private void loadTransformations() {
		Set<TransformationInfo> tf = TransformationEngine.getInstance()
				.getAvailableTransformations();
		ListModel jList1Model = new DefaultComboBoxModel(tf.toArray());
		jList1 = new JList();
		transformations.setViewportView(jList1);
		jList1.setModel(jList1Model);
		jList1.addListSelectionListener(this);

	}

	private void nextMouseClicked(MouseEvent evt) {
		if (evt.getButton() == MouseEvent.BUTTON1) {
			TransformationInfo tinfo = (TransformationInfo) jList1
					.getSelectedValue();
			String outModelName;
			if (outName.getText().equals("")) {
				outModelName = JOptionPane.showInputDialog(this,
						OUTPUT_NAME_NOT_NULL);
			} else {
				outModelName = outName.getText();
			}
			TransformationEngine.getInstance().setModel_outName(outModelName);

			if (tinfo != null) {
				try {
					TransformationEngine.getInstance().loadTransformation(
							tinfo.getTransformationID());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, ERROR_MSG
							+ e.getClass().getSimpleName() + ": "
							+ e.getMessage(), ERROR_HEAD,
							JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				DBGenApplication.getInstance().initContentPane(ConfigPane.id);

			} else {
				outModel.setText(" --- ");
				next.setEnabled(false);
			}

		}
	}

	public void valueChanged(ListSelectionEvent arg0) {
		loadOutModel();
	}

}
