package tudresden.ocl20.benchmark.tests;

import org.junit.Assert;

import org.junit.Test;




public class JavaTest 
{
	
	@Test
	public void testIntegerReferencePassing()
	{
		Integer one = 1;
		
		this.incValue(one);
		
		Assert.assertEquals(2, one);
	}
	
	@Test
	public void testIntArrayReferencePassing()
	{
		int[] values = {0,0};
		
		this.incValue(values);
		
		Assert.assertEquals(1, values[0]);
		Assert.assertEquals(-1, values[1]);
	}
	
	protected void incValue(Integer value)
	{
		value += 1;
	}
	
	protected void incValue(int[] value)
	{
		++value[0];
		--value[1];
	}
	
	
}
