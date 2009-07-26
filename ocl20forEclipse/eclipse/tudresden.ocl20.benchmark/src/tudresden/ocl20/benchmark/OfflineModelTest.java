package tudresden.ocl20.benchmark;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

import tudresden.ocl20.benchmark.testdata.b1.*;



public class OfflineModelTest 
{
	
	@Test
	public void testFirstStateModel()
	{
		List<Object> pList = ModelFirstState.getModelObjects();

		for(Object obj : pList){
			obj.toString();
		}
	}
	
	@Test
	public void testEvolutionModel()
	{
		List<Object> pList = ModelEvolution.getModelObjects();

		for(Object obj : pList){
			obj.toString();
		}
	}
}
