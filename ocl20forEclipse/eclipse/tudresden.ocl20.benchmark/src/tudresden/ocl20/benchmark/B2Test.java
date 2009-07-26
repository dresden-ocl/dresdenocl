package tudresden.ocl20.benchmark;



import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import tudresden.ocl20.benchmark.common.BenchmarkPerformer;


public class B2Test
{

	protected static BenchmarkPerformer perf;

	@BeforeClass
	public static void testInit()
	{
		
		try{
			perf = new BenchmarkPerformer("B2");
			
			perf.setMetaModel("tudresden.ocl20.pivot.metamodels.ecore");
			perf.setModelBundle("tudresden.ocl20.benchmark");
			perf.setModelFile("bin/tudresden/ocl20/benchmark/testData/b2/CivilStatusWorld.ecore");
			perf.setModelInstanceType("tudresden.ocl20.pivot.modelinstancetype.java");
			perf.init();

		}catch(Exception e){
			e.printStackTrace();
			fail();
		}
	}
	@Before
	public void setUp()
	{
	}
	
	@Test
	public void testLoadModel()
	{
		//perf.loadModelInstance("bin/tudresden/ocl20/benchmark/testData/b2/ModelEvolution.class");
		perf.safeLoadStatementFile("bin/tudresden/ocl20/benchmark/testData/b2/expressions/prepostconditions.ocl");
			
		//perf.checkActiveModelInstance();
		
	}
	
	
}
