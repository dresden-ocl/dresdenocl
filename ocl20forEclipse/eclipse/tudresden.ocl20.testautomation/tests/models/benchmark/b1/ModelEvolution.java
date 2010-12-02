package benchmark.b1;
/*
Copyright (C) 2009 by Franz Eichhorn (franz.eichhorn@gmx.de)

This file is part of the OCL Interpreter Test Suite of Dresden OCL2 for
Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
*/


import java.util.List;

// TODO: Auto-generated Javadoc
//import testData.b1.ModelFirstState.PersonMap;

public class ModelEvolution extends ModelFirstState 
{
	
	/**
	 * Gets the model objects.
	 * 
	 * @return the model objects
	 */
	public static List<Object> getModelObjects() 
	{
		return getEvolutionedObjects().getModelObjects();
	}
	
	/**
	 * Gets the evolutioned objects.
	 * 
	 * @return the evolutioned objects
	 */
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
