package tudresden.ocl20.benchmark.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.pivotmodel.Constraint;


public class BenchmarkLogger 
{
	protected String name;
	
	protected FileWriter out;
	
	protected static String outputFileBase = "BenchmarkResult_%s.txt";
	
	public BenchmarkLogger(String name)
	{
		this.name = name;
		
		this.initTestFile();
		
	}
		
	protected void initTestFile() throws RuntimeException
	{
		try{
			File fh = new File(String.format(outputFileBase, this.name));
			
			this.out = new FileWriter(fh);
		}catch(IOException e){
			throw new RuntimeException(e);
		}
		
		this.outHead1("Log for test " + this.name);
		
	}
	
	protected void outHead1(String text)
	{
		this.outLine("\n");
		this.outRuler1();
		this.outLine(text);
		this.outRuler1();
	}
	
	protected void outHead2(String text)
	{
		this.print("\n");
		this.outLine(text);
		this.outRuler2();
	}
	
	protected void outHead3(String text)
	{
		this.print("\n");
		this.outLine(text);
		this.outRuler3();
	}
	
	public void outLine(String value)
	{
		this.print(value);
		this.print("\n");
	}
	
	public void printf(String format, Object... args)
	{
		this.outLine(String.format(format, args));
	}
		
	/**
	 * Prints eventually the specified text
	 */
	protected void print(String value)
	{
		try{
			this.out.write(value);
			this.out.flush();
			System.out.print(value);
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	
	private void outRuler1()
	{
		this.outLine("==========================================");
	}
	
	private void outRuler2()
	{
		this.outLine("------------------------------------------");
	}
	
	private void outRuler3()
	{
		this.outLine("..........................................");
	}
	
	public void oclParseError(String oclStatement, Exception e)
	{ 
		this.outLine("OCL Parse Error");
		this.outLine("Message: "+e.getMessage());
		this.outLine("Errors exist in the following code:");
		this.outLine(oclStatement);
	}
	
	
	
	// Log Interpretation Failure
	public void interpretationError(Constraint con, IModelObject obj, OclRoot result)
	{
		this.outLine("Constraint failed:");
		this.outLine(con.getSpecification().getBody());
		this.outLine("on Object:");
		this.outLine(obj.getType().getName());
		this.printf("Result: %s ", result.toString());		
	}
	
	public void interpretationException(Constraint con, IModelObject obj, Exception e)
	{
		this.outHead3("Exception during interpretation");
		this.outLine(con.getSpecification().getBody());
		this.printf("Object: %s", obj.getType().getName());
		this.printf("Exception: %s ", e.toString());
		this.printf(" (message: %s)", e.getMessage());
		
	}
	
		

}
