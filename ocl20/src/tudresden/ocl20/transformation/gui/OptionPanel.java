/*
 * Created on 02.02.2006
 *
 */
package tudresden.ocl20.transformation.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import tudresden.ocl20.transformation.interfaces.TParameter;

public class OptionPanel extends JPanel {
	
	private static final String OPTION_STRING = "Option for ";
	
	private JLabel nameLabel;
	private JPanel west;
	private JPanel options;
	private ButtonGroup optionGroup;
	private JTextField elseInput;
	private static String elseString = "Anders:";
	
	
	/**
	 * This is the default constructor
	 * @param parameter 
	 */
	public OptionPanel(TParameter parameter) {
		super();
		initialize();
		this.setBorder(BorderFactory.createTitledBorder(null, parameter.getKey(), TitledBorder.LEADING, TitledBorder.TOP));
		
		nameLabel.setText(OPTION_STRING + parameter.getKey()+ ": ");
		
		optionGroup = new ButtonGroup(); 
		boolean isFirst = true;
		for (String option : parameter.getOptions()) {
			JRadioButton button = new JRadioButton(option);
			button.setActionCommand(option);
			if (isFirst) {
				button.setSelected(true);
				isFirst = false;
			}
			optionGroup.add(button);
			options.add(button);
		}
	
		if (parameter.getType() == TParameter.FREE) {
			elseInput = new JTextField();
			elseInput.setSize(250, 20);
			elseInput.setMaximumSize(new Dimension(250,20));
		
			if(!isFirst) {
				JRadioButton button = new JRadioButton(elseString);
				button.setActionCommand(elseString);
				
				optionGroup.add(button);
				options.add(button);
			}
			options.add(elseInput);
		}

	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private  void initialize() {
		BorderLayout thisLayout = new BorderLayout();
		this.setLayout(thisLayout);
		{
			options = new JPanel();
			BoxLayout optionsLayout = new BoxLayout(options, javax.swing.BoxLayout.Y_AXIS);
			options.setLayout(optionsLayout);
			this.add(options, BorderLayout.CENTER);
			{
				nameLabel = new JLabel();
				this.add(nameLabel, BorderLayout.NORTH);
				nameLabel.setText("name");
			}
			{
				west = new JPanel();
				this.add(west, BorderLayout.WEST);
				west.setPreferredSize(new java.awt.Dimension(40, 10));
			}
		}
	}
	
	public JLabel getNameLabel() {
		return nameLabel;
	}
	
	public String getSelectedOption() {
		ButtonModel selected = optionGroup.getSelection();
		if (selected.getActionCommand().equals(elseString)) {
			return elseInput.getText();
		}
		return selected.getActionCommand();
	}
	
	
}
