package tudresden.ocl.test.royloy;

import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.lang.reflect.*;

public class RLFrame extends JFrame implements ActionListener
{
	
	JList list;
	JTextField text;
	
	JButton bMembership=new JButton("Membership");
	JButton bLoyaltyProgram=new JButton("LoyaltyProgram");
	JButton bCustomer=new JButton("Customer");
	JButton bProgramPartner=new JButton("ProgramPartner");
	JButton bService=new JButton("Service");
	JButton bServiceLevel=new JButton("ServiceLevel");
	JButton bLoyaltyAccount=new JButton("LoyaltyAccount");
	JButton bCustomerCard=new JButton("CustomerCard");
	JButton bTransaction=new JButton("Transaction");
	JButton bBurning=new JButton("Burning");
	JButton bEarning=new JButton("Earning");
	JButton bDate=new JButton("Date");
	
	JButton bAssert=new JButton("ASSERT");
	JButton bExec=new JButton("EXECUTE");
	
	public RLFrame()
	{
		super("Royals and Loyals");
		list=new JList();
		updateList();
		JScrollPane sp=new JScrollPane(list);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(sp);
		
		JPanel south=new JPanel();
		text=new JTextField(20);
		south.add(text);
		south.add(bExec);
		south.add(bAssert);
		getContentPane().add(south, BorderLayout.SOUTH);
		
		bExec.addActionListener(
		new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				evaluateText();
			}
		}
		);
		
		JPanel buttons=new JPanel();
		buttons.setLayout(new GridLayout(2, 0));
		buttons.add(bMembership);
		bMembership.addActionListener(this);
		buttons.add(bLoyaltyProgram);
		bLoyaltyProgram.addActionListener(this);
		buttons.add(bCustomer);
		bCustomer.addActionListener(this);
		buttons.add(bProgramPartner);
		bProgramPartner.addActionListener(this);
		buttons.add(bService);
		bService.addActionListener(this);
		buttons.add(bServiceLevel);
		bServiceLevel.addActionListener(this);
		buttons.add(bLoyaltyAccount);
		bLoyaltyAccount.addActionListener(this);
		buttons.add(bCustomerCard);
		bCustomerCard.addActionListener(this);
		buttons.add(bTransaction);
		bTransaction.addActionListener(this);
		buttons.add(bBurning);
		bBurning.addActionListener(this);
		buttons.add(bEarning);
		bEarning.addActionListener(this);
		buttons.add(bDate);
		bDate.addActionListener(this);
		getContentPane().add(buttons, BorderLayout.NORTH);
		
		bAssert.addActionListener(
		new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				RLObject.assertAll();
			}
		}
		);
		
		this.addWindowListener(
		new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				RLObject.storePopFile();
				System.exit(0);
			}
		}
		);
		
		text.addActionListener(new ActionAdapter());
		pack();
	}
	
	void updateList()
	{
		list.setListData( RLObject.allObjects.toArray() );
	}
	
	void evaluateText()
	{
		Object obj=list.getSelectedValue();
		Object select=obj;
		String str=text.getText();
		System.out.println("evaluating "+str+" on "+obj);
		if (str.equals("") || obj==null) return;
		try
		{
			if (str.equals("delete"))
			{
				System.out.println("deleting "+obj);
				RLObject.allObjects.remove(obj);
			} else
			{
				if (str.indexOf(".")!=-1)
				{
					int i=str.indexOf(".");
					String attrName=str.substring(0, i);
					obj=obj.getClass().getField(attrName).get(obj);
					System.out.println("found attribute "+attrName+" ("+obj+")");
					str=str.substring(i+1);
				}
				if ( str.indexOf("(")==-1 )
				{
					int i=str.indexOf("=");
					String attrName=str.substring(0, i);
					String sValue=str.substring(i+1);
					Object value=getValue(obj.getClass().getField(attrName).getType(), sValue);
					System.out.println("found attribute "+attrName+" ("+value+")");
					obj.getClass().getField(attrName).set(obj, value);
				} else
				{
					int i=str.indexOf("(");
					int j=str.indexOf(":", i);
					if (j==-1) System.out.println("type missing");
					String methodName=str.substring(0, i);
					String sValue=str.substring(i+1, j);
					String sType=str.substring(j+1, str.length()-1);
					Class[] paramTypes=new Class[1];
					paramTypes[0]= getClass(sType);
					Object value=getValue(
					obj.getClass().getMethod(methodName,paramTypes).getReturnType(),
					sValue
					);
					System.out.println("found method "+methodName);
					Object[] params=new Object[1];
					params[0]=value;
					obj.getClass().getMethod(methodName, paramTypes).invoke(
					obj,
					params
					);
				}
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		updateList();
		list.setSelectedValue(select, true);
	}
	
	Class getClass(String name)
	{
		Class ret;
		try
		{
			ret=Class.forName("java.lang."+name);
			return ret;
		} catch (Exception e)
		{
			try
			{
				ret=Class.forName("tudresden.ocl.test.royloy."+name);
				return ret;
			} catch (Exception e2)
			{
				e2.printStackTrace();
				return null;
			}
		}
	}
	
	Object getValue(Class cl, String str)
	{
		Object ret=null;
		if (str.startsWith("@"))
		{
			ret=RLObject.getObjectWithID(str.substring(1));
		} else
		{
			try
			{
				if (cl==String.class)
				{
					ret=str;
				} else if (cl==Integer.class || cl==Integer.TYPE)
				{
					ret=new Integer(str);
				} else if (cl==Boolean.class || cl==Boolean.TYPE)
				{
					ret=new Boolean( (str.equals("true")) );
				}
			} catch (Exception e)
			{
				e.printStackTrace();
				ret=null;
			}
			if (ret==null)
			{
				System.out.println("getValue failed: Class "+cl+" Value "+str);
				ret=null;
			}
		}
		return ret;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String cmd=e.getActionCommand();
		try
		{
			Class c=Class.forName("tudresden.ocl.test.royloy."+cmd);
			Constructor cons=c.getConstructor(new Class[0]);
			RLObject rlo=(RLObject)cons.newInstance(new Object[0]);
		} catch (Exception exc)
		{
			exc.printStackTrace();
		}
		updateList();
	}
	
	class ActionAdapter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			evaluateText();
		}
	}
	
}

