/*
 * Created on 30.01.2006
 *
 */
package tudresden.ocl20.transformation.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import tudresden.ocl20.transformation.TransformationEngine;
import tudresden.ocl20.transformation.exception.InvalidModelException;
import tudresden.ocl20.transformation.exception.TransformationException;
import tudresden.ocl20.transformation.interfaces.TParameter;

public class ConfigPane extends JPanel {

	// private static final String DESCRIPTION = "Konfiguration\n\nBitte
	// konfigurieren Sie die auszuführende(n) metamodellbasierten
	// Transformation(n) im folgenden Konfigurationsinterface.\n\n\n";
	private static final String DESCRIPTION = "Configuration\n\nPlease configure the metamodelbased" +
			" transformation that should be executed using this configuration interface.\n\n\n";

	// private static final String TASK_DESC1 = "<html>Nachfolgend sind alle
	// Konfigurationsparameter für die Transformation ";
	private static final String TASK_DESC1 = "<html>Here all configuration parameters for the " +
			"transformation ";

	// private static final String TASK_DESC2 = " aufgeführt.<br><br> Für einige
	// Parameter kann nur aus den vorgegebenen Optionen " +
	// "ausgewählt werden. Wird bei Parametern mit der Möglichkeit zur
	// freien<br> Optionseingabe " +
	// "diese Auswahl getroffen, ist unbedingt auch eine " +
	// "Eingabe vorzunehmen. Insbesondere bei Benennungskonventionen<br> ist
	// darauf zu achten, dass " +
	// "sich frei eingegebene Präfixe unterscheiden.</html>";
	private static final String TASK_DESC2 = " are listed.<br><br> For some parameters it is " +
			"neccessary to select one of the given options. "
			+ "Other parameters have a textbox where you can enter an own option.<BR> " +
			"If this option is selected it is recommended to give an input that is not empty. " +
			"Especially naming conventions (prefixes) must be distinguishable.";

	private static final String CANCEL = "Cancel";

	private static final String NEXT = "Next >>";

	private static final String ERROR_MSG = "During the execution an error occured: ";

	private static final String ERROR_HEAD = "Execution Error";

	public static String id = "ConfigPane";

	private JButton next;

	private JTextPane messagePanel;

	private ContainerPanel containerPanel1;

	private JButton cancel;

	private JPanel east;

	private Map<String, OptionPanel> paraKey2optionPanel;

	private JPanel configurationPanel;

	/**
	 * This is the default constructor
	 */
	public ConfigPane() {
		super();
		paraKey2optionPanel = new HashMap<String, OptionPanel>();
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

		{
			containerPanel1 = new ContainerPanel();
			BorderLayout buttonPanelLayout = new BorderLayout();
			containerPanel1.buttonPanel.setLayout(buttonPanelLayout);
			{
				GridLayout messagePanelLayout = new GridLayout(1, 2);
				messagePanelLayout.setColumns(2);
				containerPanel1.messagePanel.setLayout(messagePanelLayout);
				containerPanel1.messagePanel.setFont(new java.awt.Font(
						"Dialog", 1, 12));

			}
			messagePanel = new JTextPane();
			containerPanel1.messagePanel.add(messagePanel);
			messagePanel.setText(DESCRIPTION);
			messagePanel.setEditable(false);
			messagePanel.setFont(new java.awt.Font("Dialog", 1, 12));
			messagePanel.setBackground(new java.awt.Color(238, 238, 238));
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
							DBGenApplication.getInstance().initContentPane(
									ModelPane.id);
							TransformationEngine.getInstance().cleanUp();
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
				east.add(next);
			}
		}
		this.add(containerPanel1, BorderLayout.CENTER);
		containerPanel1.setPreferredSize(new java.awt.Dimension(636, 407));
		containerPanel1.mainPanel.setBorder(new LineBorder(new java.awt.Color(
				0, 0, 0), 1, false));
		loadConfiguration();

	}

	private void loadConfiguration() {
		Collection<TParameter> parameters = TransformationEngine.getInstance()
				.getRequiredParameters();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		containerPanel1.mainPanel.add(scrollPane);

		configurationPanel = new JPanel();
		configurationPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		GridLayout configLayout = new GridLayout(0, 1);
		configLayout.setHgap(30);
		configLayout.setVgap(10);
		configurationPanel.setLayout(configLayout);
		JLabel textlabel = new JLabel(TASK_DESC1
				+ TransformationEngine.getInstance().getCurrentTransformation()
				+ TASK_DESC2);
		configurationPanel.add(textlabel);
		scrollPane.setViewportView(configurationPanel);

		if (parameters != null) {
			for (TParameter parameter : parameters) {
				OptionPanel pPanel = new OptionPanel(parameter);
				paraKey2optionPanel.put(parameter.getKey(), pPanel);
				configurationPanel.add(pPanel);

			}
		}
	}

	private void readConfiguration() {
		Collection<TParameter> parameters = TransformationEngine.getInstance()
				.getRequiredParameters();

		if (parameters != null) {
			for (TParameter parameter : parameters) {
				String selectedOption = paraKey2optionPanel.get(
						parameter.getKey()).getSelectedOption();
				TransformationEngine.getInstance().setConfigurationParameter(
						parameter.getKey(), selectedOption);
			}
		}
	}

	private void nextMouseClicked(MouseEvent evt) {
		if (evt.getButton() == MouseEvent.BUTTON1) {
			readConfiguration();
			try {
				TransformationEngine.getInstance().invoke();

			} catch (TransformationException e) {
				JOptionPane.showMessageDialog(null,
						ERROR_MSG
								+ e.getClass().getSimpleName() + ": "
								+ e.getMessage(), ERROR_HEAD,
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (InvalidModelException e) {
				JOptionPane.showMessageDialog(null,
						ERROR_MSG
								+ e.getClass().getSimpleName() + ": "
								+ e.getMessage(), ERROR_HEAD,
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
			DBGenApplication.getInstance().initContentPane(TracePane.id);
		}
	}

}
