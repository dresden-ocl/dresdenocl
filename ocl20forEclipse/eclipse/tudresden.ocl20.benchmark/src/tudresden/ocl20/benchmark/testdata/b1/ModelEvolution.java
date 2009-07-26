package tudresden.ocl20.benchmark.testdata.b1;

import java.util.List;

//import testData.b1.ModelFirstState.PersonMap;

public class ModelEvolution extends ModelFirstState 
{
	
	public static List<Object> getModelObjects() 
	{
		return getEvolutionedObjects().getModelObjects();
	}
	
	protected static PersonMap getEvolutionedObjects()
	{
		PersonMap pMap = getFinalStateModelInstance();
		
		Person ada = pMap.get("Ada");
		Person bob = pMap.get("Bob");
		Person cyd = pMap.get("Cyd");
		Person dan = pMap.get("Dan");
		Person eve = pMap.get("Eve");
		
		// make evolution do something
		ada.marry(bob);
		
		ada.divorce(bob);
		
		cyd.civstat = "single";
		ada.marry(cyd);
		
		dan.civstat = "single";
		
		cyd.die();
		
		eve.civstat = "single";
		
		dan.marry(eve);

		return pMap;
	}
}
