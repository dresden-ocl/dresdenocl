package tudresden.ocl.injection.test;


class ContractImplementation
{
	private void assertContract(boolean condition)
	{
	}
	
	private void assertContract(boolean condition, String message)
	{
	}
	
	private int state;
	
	/**
	 * @assumethrows IllegalArgumentException state!=0
	 * @assumethrows ClassCastException x instanceof Comparable
	 * @assumethrows ClassCastException y instanceof Comparable
	 * @assume x!=y
	 *
	 * @ensurethrows RuntimeException state==0
	 * @ensurethrows NullPointerException x==null
	 * @ensurethrows NullPointerException y==null
	 * @ensure result>=0
	 */
	public int compare_wrapped_tudresden_ocl_injection_test_ContractImplementation(Object x, Object y)
	{
		if(state==0)
			throw new IllegalArgumentException();
		Comparable cx = (Comparable) x;
		Comparable cy = (Comparable) y;
		if(x==y)
			throw new RuntimeException();
		System.out.println(x.toString());
		System.out.println(y.toString());
		state = 0;
		return 0;
	}
	
	public int compare(Object x, Object y)
	{
		boolean
		assumeThrowAny=true,
		assumeThrow0=false,
		assumeThrow1=false,
		assumeThrow2=false,
		ensureThrowAny=false,
		ensureThrow0=false,
		ensureThrow1=false,
		ensureThrow2=false;
		
		if(!(state!=0)) assumeThrow0=true;
		else if(!(x instanceof Comparable)) assumeThrow1=true;
		else if(!(x instanceof Comparable)) assumeThrow2=true;
		else
		{
			assumeThrowAny=false;
			ensureThrowAny=true;
			if(state==0) ensureThrow0=true;
			else if(x==null) ensureThrow1=true;
			else if(y==null) ensureThrow2=true;
			else ensureThrowAny=false;
		}
		
		int result;
		try
		{
			result=compare_wrapped_tudresden_ocl_injection_test_ContractImplementation(x, y);
			assertContract(ensureThrow0, "should have thrown IllegalArgumentException, since state=0");
			assertContract(ensureThrow1, "should have thrown NullPointerException, since x==null");
			assertContract(ensureThrow2, "should have thrown NullPointerException, since y==null");
			assertContract(result>=0, "ensure violated: result>=0");
		}
		catch(ClassCastException e)
		{
			assertContract(assumeThrow1||assumeThrow2, "should not");
			throw e;
		}
		return result;
	}
	
}




