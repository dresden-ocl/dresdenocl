/*
 * EngineTest.java
 * 
 * Created on 07.02.2006
 * Copyright (c) 2006 
 * Contact:
 *
 * This file is part of the Dresden OCL2.0 Toolkit
 * created at Technische Universitaet Dresden (TUD), Germany.
 * Visit http://dresden-ocl.sourceforge.net/ for details.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 * MA  02111-1307  USA
 *
 */
package tudresden.ocl20.tests.transformation;

import tudresden.ocl20.transformation.TransformationEngine;
import tudresden.ocl20.transformation.exception.InvalidModelException;
import tudresden.ocl20.transformation.exception.TransformationException;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * <p>This test class tests the TransformationEngine.</p>
 *
 *@see tudresden.ocl20.transformation.TransformationEngine;
 */
/*
 * JavaDoc added changed to jUnit by Claas Wilke in 2007
 */
public class TransformationEngineTest {
		
	/**
	 * <p>Tests the TransformationEngine and its methods.
	 * Checks the interfaces of TransformationEngine with wrong parameters.</p>
	 */
	@Test public void run1() {
		// Get the instance of the TransformationEngine
		TransformationEngine te = TransformationEngine.getInstance();
		
		// Clean the Engine from all loaded transformations.
		te.cleanUp();
		
		// Check if the name of inModel could be null
		try {
			te.setModel_inName(null);
			// This should not happen
			fail("Expected NullPointerException");
		} 
		catch(NullPointerException e) {
			// Expected Exception
		}
		
		// Set name of inModel and check its name
		te.setModel_inName("inModel");
		assertEquals(te.getModel_inName(), "inModel");
		
		// Check if the name of outModel could be null		
		try {
			te.setModel_outName(null);
			// This should not happen
			fail("Expected NullPointerException");
		} 
		catch(NullPointerException e) {
			// Expected Exception
		}

		// Set name of outModel and check its name
		te.setModel_outName("outName");
		assertEquals(te.getModel_outName(), "outName");
		
		// Try to invoke transformation, although no Transformation
		// was loaded yet.
		try {
			te.invoke();
			// This should not happen
			fail("Expected IllegalArgumentException");
		} 
		catch (IllegalArgumentException e) {
			// Expected Exception			
		} 
		catch (TransformationException e) {
			fail("Wrong Exception occured. IllegalArgumentException was expected but " +
					"TransformationException occured.\n" +
					"See StrackTrace for details.");
			e.printStackTrace();
		} 
		catch (InvalidModelException e) {
			fail("Wrong Exception occured. IllegalArgumentException was expected but " +
					"InvalidModelException occured.\n" +
					"See StrackTrace for details.");
			e.printStackTrace();
		}
		
		// Try to invoke a transformation by an unknown id
		try {
			te.invoke("xxx");
			// This should not happen
			fail("Expected IllegalArgumentException");
		} 
		catch (IllegalArgumentException e) {
			// Expected Exception						
		} 
		catch (TransformationException e) {
			fail("Wrong Exception occured. IllegalArgumentException was expected but " +
					"TransformationException occured.\n" +
					"See StrackTrace for details.");
			e.printStackTrace();
		} 
		catch (InvalidModelException e) {
			fail("Wrong Exception occured. IllegalArgumentException was expected but " +
					"InvalidModelException occured.\n" +
					"See StrackTrace for details.");
			e.printStackTrace();
		}
		
		// Try to get requiredParameters although no Transformation
		// was loaded yet.
		try {
			te.getRequiredParameters();
			// This should not happen
			fail("Expected IllegalArgumentException");
		} 
		catch (IllegalArgumentException e) {
			// Expected Exception						
		}
		
		// Try to get requiredParameters with an unknown Transformation id
		try {
			te.getRequiredParameters("xxx");
			// This should not happen
			fail("Expected IllegalArgumentException");
		} 
		catch (IllegalArgumentException e) {
			// Expected Exception						
		}
		
		// Try to get a result although no Transformation was loaded  yet.
		try {
			te.getResult();
			// This should not happen
			fail("Expected IllegalArgumentException");
		} 
		catch (IllegalArgumentException e) {
			// Expected Exception						
		}
		
		// Try to get a result with an unknown Transformation id.
		try {
			te.getResult("xxx");
			// This should not happen
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Expected Exception						
		}
		
		// Try to get the Trace of the last loaded Transformation
		// although no Transformation was loaded yet.
		try {
			te.getTrace();
			// This should not happen
			fail("Expected IllegalArgumentException");
		} 
		catch (IllegalArgumentException e) {
			// Expected Exception						
		}
		
		// Try to get the Trace of an unknown Transformation id.
		try {
			te.getTrace("xxx");
			// This should not happen
			fail("Expected IllegalArgumentException");
		} 
		catch (IllegalArgumentException e) {
			// Expected Exception						
		}

		// Try to get the name of the currently loaded transformation
		// although no transformation was loaded yet.
		try {
			te.getCurrentTransformation();
			// This should not happen
			fail("Expected NullPointerException");
		} 
		catch (NullPointerException e) {
			// Expected Exception						
		}
	
		// Try to set Configuration parameters although no Transformation
		// was loaded yet.
		try {
			te.setConfigurationParameter("ccc", "kkk", "ooo");
			// This should not happen
			fail("Expected IllegalArgumentException");
		} 
		catch (IllegalArgumentException e) {
			// Expected Exception						
		}
		
		// Try to set Configuration parameters although no Transformation
		// was loaded yet.
		try {
			te.setConfigurationParameter("kkk", "ooo");
			// This should not happen
			fail("Expected IllegalArgumentException");
		} 
		catch (IllegalArgumentException e) {
			// Expected Exception						
		}
	}
	
}
