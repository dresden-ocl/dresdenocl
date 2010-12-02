package tudresden.ocl20.eclipse.plugins.ocleditor.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import tudresden.ocl20.integration.OCLChecker;

/**
 * An instance of this class is used to parse constraints
 * @author Mirko Stölzel
 */
public class ParseJob extends Job 
{
	/**
	 * An instance of this class is used to parse one constraint
	 */
	private class ParseRunnable implements Runnable
	{
		private IParseConfiguration config = null;
		
		/**
		 * Constructor
		 * @param config
		 */
		public ParseRunnable(IParseConfiguration config)
		{
			this.config = config;
		}
		
		public void run() 
		{
			try
			{
				OCLChecker checker = OCLChecker.getInstance(config.getTopPackage());
				checker.setModelFacade(config.getModelFacade());
				count++;
				monitor.worked(count);
				checker.validate(config.getConstraintText());											
				status = DONE;
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				problem = e.getMessage();
				status =  DONE;
			}
		}
		
	}

	//This state constant symbolize that an OCL constraint is parsed
	private final static int PARSE = 0;
	//This state constant symbolize that the parsing process has finished	 
	private final static int DONE = 1;
	//Holds the parsing state
	private int status = -1;
	//Is used to count the working steps 
	private int count = 0;
	//The progress monitor of eclipse which presents the working progress
	private IProgressMonitor monitor;
	
	//The problem occured parsing the constraints
	private String problem = null;
	
	private IParseConfiguration[] parseConfigs;	

	/**
	 * Constructor
	 * @param parseConfigs - the parse configurations
	 */
	public ParseJob(IParseConfiguration[] parseConfigs) 
	{
		super("Parsing " + parseConfigs.length + " constraint(s)");
		this.parseConfigs = parseConfigs;
		this.setUser(true);	    
	}
	
	protected IStatus run(IProgressMonitor monitor) 
	{
		this.monitor = monitor;
		monitor.beginTask("Parsing " + this.parseConfigs.length + " constraint(s)", 
						  this.parseConfigs.length * 2);
		monitor.worked(0);
		for (int i = 0; i < this.parseConfigs.length; i++)
		{
			Runnable parse = new ParseRunnable(this.parseConfigs[i]);
			status = PARSE;			
			Thread parseThread = new Thread(parse);
			parseThread.start();
				
			while (!monitor.isCanceled() &&
					status == PARSE);
			
			if (monitor.isCanceled())
			{
				return Status.CANCEL_STATUS;
			}
			count++;
			monitor.worked(count);			
		}
		monitor.done();
		return Status.OK_STATUS;							
	}

	/**
	 * Returns the the problem which occured while parsing a constraint
	 * @return the problem
	 */
	public String getProblem() {
		return problem;
	}

	/**
	 * Returns the parse configurations which are used for the consistency check.
	 * @return parse configurations
	 */
	public IParseConfiguration[] getParseConfigs() {
		return parseConfigs;
	}
}
