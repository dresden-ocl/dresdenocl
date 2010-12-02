package tudresden.ocl.test.royloy;

import java.util.*;
import tudresden.ocl.lib.*;

public class Customer extends RLObject
{
	
	
	public String name;
	
	/**
	 * @invariant title_gender:
	 *    title=(if isMale=true then 'Mr' else 'Ms' endif)
	 */
	public String title;
	
	public boolean isMale;
	
	public Date dateOfBirth;
	
	public Vector  program=new Vector();
	
	/**
	 * @element-type CustomerCard
	 */
	public Vector  cards=new Vector();
	
	public Vector  membership=new Vector();
	
	public Customer(String name, boolean isMale)
	{
		super(name);
		this.name=name;
		this.title=isMale?"Mr":"Ms";
		this.isMale=isMale;
	}
	
	/**
	 * @precondition index_positive: index>=0
	 * @postcondition result_is_one: result=1
	 * @postcondition title_unchanged: title=title@pre
	 */
	public int ignoreCard(int index, CustomerCard c, int resultat)
	{
		if(index==42)
			title=title+" hoppla";
		return resultat;
	}
	
	public boolean assertTrue()
	{
		return true;
	}
	
} /* end class Customer */

