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
package tudresden.ocl20.benchmark;

import java.util.List;

import org.junit.Test;

import tudresden.ocl20.benchmark.testdata.b1.ModelFirstState;
import tudresden.ocl20.benchmark.testdata.b1.ModelEvolution;

/**
 * Tests all modelinstances just by retrieving it's model instance objects.
 * Errors are here much easier to detect than during the benchmark.
 */
public class OfflineModelTest {

	/**
	 * Test first state model.
	 */
	@Test
	public void testFirstStateModel() {
		List<Object> pList = ModelFirstState.getModelObjects();

		for (Object obj : pList) {
			obj.toString();
		}
	}

	/**
	 * Test evolution model.
	 */
	@Test
	public void testEvolutionModel() {
		List<Object> pList = ModelEvolution.getModelObjects();

		for (Object obj : pList) {
			obj.toString();
		}
	}

	/**
	 * Test b2 instance.
	 */
	@Test
	public void testB2Instance() {
		List<Object> pList = tudresden.ocl20.benchmark.testdata.b2.ModelInstance
				.getModelObjects();

		for (Object obj : pList) {
			obj.toString();
		}

	}

	/**
	 * Test b3 instance.
	 */
	@Test
	public void testB3Instance() {
		List<Object> pList = tudresden.ocl20.benchmark.testdata.b3.ModelInstance
				.getModelObjects();

		for (Object obj : pList) {
			obj.toString();
		}
	}

	/**
	 * Test Dummy instance
	 */
	@Test
	public void testDummyInstance() {
		List<Object> pList = tudresden.ocl20.benchmark.testdata.common.ModelInstance
				.getModelObjects();
		for (Object obj : pList) {
			obj.toString();
		}
	}

}
