/*
 * Created on 30.01.2006
 *
 */
package tudresden.ocl20.transformation.gui;

import java.awt.Dimension;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.netbeans.api.mdr.MDRManager;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;



public class DBGenApplication extends JFrame {

	private static final String ERROR_MSG = "During the execution an error occured: ";

	private static final String ERROR_HEAD = "Execution Error";
	
	private javax.swing.JPanel jContentPane = null;
	private Map<String, Class<? extends JPanel>> availablePanes;
	private static DBGenApplication theInstance;
	
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBGenApplication application = DBGenApplication.getInstance();
		application.setVisible(true);
	}

	public static DBGenApplication getInstance() {
		if(theInstance == null) {
			theInstance = new DBGenApplication();
		}
		return theInstance;
	}

	/**
	 * This is the default constructor
	 */
	private DBGenApplication() {
		super();
		availablePanes = new HashMap<String, Class<? extends JPanel>>();
		availablePanes.put(ModelPane.id, ModelPane.class);
		availablePanes.put(TracePane.id, TracePane.class);
		availablePanes.put(TransformationPane.id, TransformationPane.class);
		availablePanes.put(ConfigPane.id, ConfigPane.class);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Transformation GUI [prototype]");
		this.setMinimumSize(new java.awt.Dimension(800, 600));
		this.setPreferredSize(new java.awt.Dimension(800, 600));
		this.setSize(new Dimension(800,600));
		this.setLocation(new java.awt.Point(50, 50));
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		this.setContentPane(jContentPane);
		this.pack();
		this.repaint();
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			initContentPane(ModelPane.id);
		}
		return jContentPane;
	}

	public void initContentPane(String id) {
		Class<? extends JPanel> cls = availablePanes.get(id);
		if (cls != null) {
			try {
				Constructor<? extends JPanel> constructor = cls.getConstructor(new Class[]{});
				jContentPane = constructor.newInstance(new Object[] {});
			
			} catch (SecurityException e) {
				 JOptionPane.showMessageDialog(null, ERROR_MSG +
							e.getClass().getSimpleName() +": " + e.getMessage(), ERROR_HEAD, JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				 JOptionPane.showMessageDialog(null, ERROR_MSG +
							e.getClass().getSimpleName() +": " + e.getMessage(), ERROR_HEAD, JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				 JOptionPane.showMessageDialog(null, ERROR_MSG +
							e.getClass().getSimpleName() +": " + e.getMessage(), ERROR_HEAD, JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (InstantiationException e) {
				 JOptionPane.showMessageDialog(null, ERROR_MSG +
							e.getClass().getSimpleName() +": " + e.getMessage(), ERROR_HEAD, JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				 JOptionPane.showMessageDialog(null, ERROR_MSG +
							e.getClass().getSimpleName() +": " + e.getMessage(), ERROR_HEAD, JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				 JOptionPane.showMessageDialog(null, ERROR_MSG +
							e.getClass().getSimpleName() +": " + e.getMessage(), ERROR_HEAD, JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}

		initialize();
	}
	
	public void shutdown() {
		MDRManager.getDefault().shutdownAll();
	}

}
