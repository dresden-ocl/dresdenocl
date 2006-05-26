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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import tudresden.ocl20.transformation.TransformationEngine;


public class TracePane extends JPanel {
//	private static final String DESCRIPTION = "Ausführungsprotokoll\n\nDie ausgewählte " +
//			"Modelltransformation wird nun mit den von ihnen gewählten Parametern a" +
//			"usgeführt.\n\nDas erstellte Ausführungsprotokoll ist unten zu sehen.\n";
	private static final String DESCRIPTION = "Execution protocol\n\nThe selected and configured " +
			"metamodelbased transformation was executed. The protocol is shown beneath.";

	private static final String FINISH = "Finish";
	private static final String NAME = "Execution protocol";
	public static String id = "TracePane";
	private JButton next;
	private JTextPane messagePanel;
	private JTextPane protocol;
	private JLabel jLabel1;
	private JScrollPane jScrollPane1;
	private ContainerPanel containerPanel1;

	/**
	 * This is the default constructor
	 */
	public TracePane() {
		super();
		initialize();
		
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private  void initialize() {
		BorderLayout thisLayout = new BorderLayout();
		this.setLayout(thisLayout);
		this.setPreferredSize(new java.awt.Dimension(860, 587));

		{
			containerPanel1 = new ContainerPanel();
			GridLayout messagePanelLayout = new GridLayout(1, 2);
			messagePanelLayout.setColumns(2);
			containerPanel1.messagePanel.setLayout(messagePanelLayout);
			{
				messagePanel = new JTextPane();
				BorderLayout buttonPanelLayout = new BorderLayout();
				containerPanel1.buttonPanel.setLayout(buttonPanelLayout);
				FlowLayout mainPanelLayout = new FlowLayout();
				containerPanel1.mainPanel.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				containerPanel1.mainPanel.setLayout(mainPanelLayout);
				{
					next = new JButton();
					{
						jLabel1 = new JLabel();
						containerPanel1.mainPanel.add(jLabel1);
						jLabel1.setText(NAME);
						jLabel1.setPreferredSize(new java.awt.Dimension(722, 12));
					}
					{
						jScrollPane1 = new JScrollPane();
						containerPanel1.mainPanel.add(jScrollPane1);
						jScrollPane1.setPreferredSize(new java.awt.Dimension(723, 296));
						{
							loadProtocol();
						}
					}
					containerPanel1.buttonPanel.add(next, BorderLayout.EAST);
					next.setText(FINISH);
					next.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							nextMouseClicked(evt);
						}
					});
				}
				containerPanel1.messagePanel.add(messagePanel);
				messagePanel.setText(DESCRIPTION);
				messagePanel.setEditable(false);
				messagePanel.setFont(new java.awt.Font("Dialog",1,12));
				messagePanel.setBackground(new java.awt.Color(238,238,238));
			}
			this.add(containerPanel1, BorderLayout.CENTER);
		}
	}
	
	
	private void loadProtocol() {
		protocol = new JTextPane();
		jScrollPane1.setViewportView(protocol);
		protocol.setText(TransformationEngine.getInstance().getTrace().toString());
	}

	private void nextMouseClicked(MouseEvent evt) {
		if(evt.getButton() == MouseEvent.BUTTON1) {
			DBGenApplication.getInstance().initContentPane(ModelPane.id);
		}
	}

	


	
}
	
	