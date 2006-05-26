/*
 * Created on 30.01.2006
 *
 */
package tudresden.ocl20.transformation.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class ContainerPanel extends JPanel {
	public JPanel messagePanel;
	public JPanel mainPanel;
	public JPanel buttonPanel;

	/**
	 * This is the default constructor
	 */
	public ContainerPanel() {
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
		thisLayout.setHgap(30);
		thisLayout.setVgap(30);
		this.setLayout(thisLayout);
		//this.setSize(300, 400);
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		{
			messagePanel = new JPanel();
			BorderLayout messagePanelLayout = new BorderLayout();
			messagePanel.setLayout(messagePanelLayout);
			this.add(messagePanel, BorderLayout.NORTH);
		}
		{
			mainPanel = new JPanel();
			GridLayout mainPanelLayout = new GridLayout(1, 1);
			mainPanel.setLayout(mainPanelLayout);
			this.add(mainPanel);
		}
		{
			buttonPanel = new JPanel();
			BorderLayout buttonPanelLayout = new BorderLayout();
			buttonPanel.setLayout(buttonPanelLayout);
			this.add(buttonPanel, BorderLayout.SOUTH);
		}
	}

	

}
