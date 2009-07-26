package tudresden.ocl20.benchmark;



import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import tudresden.ocl20.benchmark.common.BenchmarkPerformer;


public class B1Test
{

	protected static BenchmarkPerformer perf;
	//protected TestPerformer perf;
//	public void testStart()
//	{
//		System.out.println("System start");
//		
//		TestCase.assertEquals(1, 1);
//	}
//	
//	public void testSimpleModelLoad() {
//		try {
//			System.out.println("SimpleModeLoadTest was started....");
//
//			IMetamodel[] metamodels = ModelBusPlugin.getMetamodelRegistry().getMetamodels();
//			System.out.println("Availabe metamodels");
//			for(IMetamodel metaModel : metamodels) {
//				System.out.println(metaModel.getName());
//				System.out.println("ID: " + metaModel.getId());
//				
//			}
//			System.out.println("SimpleModelLoadTest has finished ....");
//		}
//		catch(Exception ex) {
//			ex.printStackTrace();
//		}
//	}
	
//	public void setUp()
//	{
//		//this.perf = new TestPerformer();
//		
//		//this.perf.setMetaModel("tudresden.ocl20.pivot.metamodels.ecore");
//		
//		
//	}
	@BeforeClass
	public static void testInit()
	{
		
		try{
			perf = new BenchmarkPerformer("B1");
			
			perf.setMetaModel("tudresden.ocl20.pivot.metamodels.ecore");
			perf.setModelBundle("tudresden.ocl20.benchmark");
			perf.setModelFile("src/tudresden/ocl20/benchmark/testdata/b1/CivilStatusWorld.ecore");
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
	public void testEvolutionAndQueries()
	{
		perf.loadModelInstance("bin/tudresden/ocl20/benchmark/testdata/b1/ModelEvolution.class");
		perf.safeLoadStatementFile("bin/tudresden/ocl20/benchmark/testdata/b1/expressions/domainInvariants.ocl");
		//perf.safeLoadQueryFile("src/testData/b1/expressions/evolutionQueries.ocl");
		
		perf.checkActiveModelInstance();
		
	}
	
	@Test
	public void testModelFirstState()
	{
		perf.loadModelInstance("bin/tudresden/ocl20/benchmark/testdata/b1/ModelFirstState.class");
		perf.safeLoadStatementFile("bin/tudresden/ocl20/benchmark/testdata/b1/expressions/domainInvariants.ocl");
		
		perf.checkActiveModelInstance();
		
		
//		try{
//			perf.loadOCLFile(oclFilePath);
//		}catch(RuntimeException e){
//			System.out.println("OclFile could not be parsed -- seems like this type of constraint is not supported");
//			System.out.println(e.getCause().getMessage());
//			return;
//		}
//
//		modelObjects = perf.getObjectsOfKind(BenchmarkPerformer
//				.getClassNameAsPathList(clazz));
//		
//		
//		InterpretationPerformer intPerf = perf.prepareRemainingConstraintsForSingleInterpretation(modelObjects);
//		OclRoot result = intPerf.interpretNextPair();
//		
//		
//		//Person pers=null;
//		
//		while(result != null){
//						
//			if(result == null){
//				System.out.println("result was null");
//				continue;
//			}
//			
//			if(!((JavaOclBoolean) result).isTrue()){
//				System.out.println("\nConstraint failed!");
//				
////				try{
////					Object modelObjectAdaptee = intPerf.getCurrentModelObject().getOclObject().getAdaptee();
////					
////					pers = (Person) modelObjectAdaptee;
////				}catch(ClassCastException e){
////					fail();
////				}
//				//pers.print();
//				
//				System.out.println(intPerf.getCurrentConstraint().getQualifiedName());
//				
//				System.out.println(intPerf.getCurrentModelObject().getOclObject().getAdaptee().toString());
//				
//				
//			}
//			result = intPerf.interpretNextPair();
//			
//		}
		
	}
	
	
	
	
	/**
	 * Tests B1 Domain Invariants
	 */
	//@Test
//	public void testDomainInvariants()
//	{
//
//		this.testConstraintsByType("src/testData/b1/expressions/domainInvariants.ocl", Person.class);
//							
//	}
//	
//	//@Test
//	public void testQueryInvariants()
//	{
//		this.testConstraintsByType("src/testData/b1/expressions/queryInvariants.ocl", Person.class);
//	}
//	
//	public void testPossiblePairs_P1_VT()
//	{
//		this.testConstraintsByType("src/testData/b1/expressions/possiblePairs_P1_VT.ocl", Person.class);
//	}
//	
//	public void testPossiblePairs_P1_VN()
//	{
//		this.testConstraintsByType("src/testData/b1/expressions/possiblePairs_P1_VN.ocl", Person.class);
//	}
//	
//	public void testPossiblePairs_P1_VI()
//	{
//		this.testConstraintsByType("src/testData/b1/expressions/possiblePairs_P1_VI.ocl", Person.class);
//	}
	
	
//	protected void testConstraintsByType(String oclFilePath, Class<?> clazz)
//	{
//		List<IModelObject> modelObjects;
//
//		perf.loadModelInstance("bin/testData/b1/ModelFirstState.java");
//		perf.safeLoadOclFile(oclFilePath);
//		
//
//		modelObjects = perf.getObjectsOfKind(BenchmarkPerformer
//				.getClassNameAsPathList(clazz));
//		
//		
//		InterpretationPerformer intPerf = perf.prepareRemainingConstraintsForSingleInterpretation(modelObjects);
//		OclRoot result = intPerf.interpretNextPair();
//		
//		
//		//Person pers=null;
//		
//		while(result != null){
//						
//			if(result == null){
//				System.out.println("result was null");
//				continue;
//			}
//			
//			if(!((JavaOclBoolean) result).isTrue()){
//				System.out.println("\nConstraint failed!");
//				
////				try{
////					Object modelObjectAdaptee = intPerf.getCurrentModelObject().getOclObject().getAdaptee();
////					
////					pers = (Person) modelObjectAdaptee;
////				}catch(ClassCastException e){
////					fail();
////				}
//				//pers.print();
//				
//				System.out.println(intPerf.getCurrentConstraint().getQualifiedName());
//				
//				System.out.println(intPerf.getCurrentModelObject().getOclObject().getAdaptee().toString());
//				
//				
//			}
//			result = intPerf.interpretNextPair();
//			
//		}
//		
//	}
//		
	
	
	
}
