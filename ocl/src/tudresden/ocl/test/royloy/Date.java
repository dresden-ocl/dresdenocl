package tudresden.ocl.test.royloy;

import java.util.*;

public class Date extends RLObject
{
	
	// Attributes
	public static Date now=new Date("now");
	
	public int iTime;
	
	public Date(final String description)
	{
		super(description);
	}
	
	public int difference(Date d)
	{
		return Math.abs(iTime-d.iTime);
	}
	
	public boolean isBefore(Date d)
	{
		return iTime<d.iTime;
	}
	
	public boolean isAfter(Date d)
	{
		return iTime>d.iTime;
	}
	
	public boolean equals(Date d)
	{
		return iTime==d.iTime;
	}
	
	public boolean assertTrue()
	{
		return true;
	}
	
} /* end class Date */

