package tudresden.ocl.test.royloy;

import java.util.*;
import tudresden.ocl.lib.*;

public class ProgramPartner extends RLObject
{
	
	public int numberOfCustomers;
	
	public Vector  loyaltyProgram=new Vector();
	
	/**
	 * @element-type Service
	 * @invariant :
	 *    self.deliveredServices.transactions->iterate(
	 *      t:Transaction;
	 *      result:Integer = 0 |
	 *      if t.oclIsTypeOf(Burning) then result+t.points else result endif
	 *      )
	 *      <=
	 *      self.deliveredServices.transactions->iterate(
	 *        t:Transaction;
	 *        result:Integer = 0 |
	 *        if t.oclIsTypeOf(Earning) then result+t.points else result endif
	 *      )
	 *      -- to do: 't.points' was 'points' -> default context in iterate
	 */
	public Vector  deliveredServices=new Vector();
	
	public ProgramPartner(final String description)
	{
		super(description);
	}
	
	// Operations
	public boolean assertTrue()
	{
		return true;
	}
	
} /* end class ProgramPartner */

