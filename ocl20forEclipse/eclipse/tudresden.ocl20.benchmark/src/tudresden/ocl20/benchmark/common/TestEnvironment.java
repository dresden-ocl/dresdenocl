package tudresden.ocl20.benchmark.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tudresden.ocl20.pivot.modelbus.IModelInstance;


public class TestEnvironment {
	




	// @ directory where all files are stored
	public String fileDirectory = "";

	
	public List<String> loadedOclFiles;
	
	
	//@ current loaded Constraints, sorted by name
	public Map<String, String> loadedConstraints;

	
	public String curConstraintName;
	
	//@ independent number to assign as a constraint name
	public int curConstraintId;

	// @ Name of the currently executed test
	public String testName;
	
	// all loaded model instances
	// since the testperformer is used by differend test cases the instances
	// can be reused
	public Map<String, IModelInstance> loadedInstances;
	
	
	public TestEnvironment()
	{
		
		this.loadedOclFiles = new ArrayList<String>();

		this.loadedInstances = new HashMap<String, IModelInstance>();
		
		this.loadedConstraints = new HashMap<String, String>();
		
		this.curConstraintId = 1;
		
	}

}
