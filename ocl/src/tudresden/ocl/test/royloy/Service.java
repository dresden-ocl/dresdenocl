package tudresden.ocl.test.royloy;

import java.util.*;
import tudresden.ocl.lib.*;


public class Service extends RLObject
{
	
	// Attributes
	public boolean condition;
	
	/**
	 * @invariant : self.pointsEarned>0 implies not (self.pointsBurned=0)
	 */
	public int pointsEarned;
	
	public int pointsBurned;
	
	public String description;
	
	// Associations
	public ProgramPartner programPartner;
	
	public ServiceLevel serviceLevel;
	
	/**
	 * @element-type Transaction
	 */
	public Vector  transactions=new Vector();
	
	public Service(final String description)
	{
		super(description);
	}
	
	// Operations
	public boolean assertTrue()
	{
		return true;
	}
	
} /* end class Service */

