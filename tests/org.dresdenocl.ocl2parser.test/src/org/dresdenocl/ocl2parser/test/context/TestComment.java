/*
Copyright (C) 2008-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the OCL2 Parser Test Suite of Dresden OCL2 for Eclipse.

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

package org.dresdenocl.ocl2parser.test.context;

import org.junit.Test;

import org.dresdenocl.ocl2parser.test.TestPerformer;

/**
 * <p>
 * Contains test cases that check that the context of OCL constraints is parsed
 * correctly.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestComment {

	/**
	 * <p>
	 * A test case to parse an OCL file containing a comment that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testCommentPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/commentPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer =
				TestPerformer.getInstance(AllContextTests.META_MODEL_ID,
						AllContextTests.MODEL_BUNDLE, AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse an OCL file containing a comment that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testCommentPositive02() throws Exception {
	
		TestPerformer testPerformer;
	
		String modelFileName;
		String oclFileName;
	
		oclFileName = "context/commentPositive02.ocl";
		modelFileName = "testmodel.uml";
	
		/* Try to get the TestPerformer. */
		testPerformer =
				TestPerformer.getInstance(AllContextTests.META_MODEL_ID,
						AllContextTests.MODEL_BUNDLE, AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);
	
		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse an OCL file containing a comment that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testCommentPositive03() throws Exception {
	
		TestPerformer testPerformer;
	
		String modelFileName;
		String oclFileName;
	
		oclFileName = "context/commentPositive03.ocl";
		modelFileName = "testmodel.uml";
	
		/* Try to get the TestPerformer. */
		testPerformer =
				TestPerformer.getInstance(AllContextTests.META_MODEL_ID,
						AllContextTests.MODEL_BUNDLE, AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);
	
		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse an OCL file containing a comment that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testCommentPositive04() throws Exception {
	
		TestPerformer testPerformer;
	
		String modelFileName;
		String oclFileName;
	
		oclFileName = "context/commentPositive04.ocl";
		modelFileName = "testmodel.uml";
	
		/* Try to get the TestPerformer. */
		testPerformer =
				TestPerformer.getInstance(AllContextTests.META_MODEL_ID,
						AllContextTests.MODEL_BUNDLE, AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);
	
		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse an OCL file containing a comment that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testCommentPositive05() throws Exception {
	
		TestPerformer testPerformer;
	
		String modelFileName;
		String oclFileName;
	
		oclFileName = "context/commentPositive05.ocl";
		modelFileName = "testmodel.uml";
	
		/* Try to get the TestPerformer. */
		testPerformer =
				TestPerformer.getInstance(AllContextTests.META_MODEL_ID,
						AllContextTests.MODEL_BUNDLE, AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);
	
		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse an OCL file containing a comment that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testCommentPositive06() throws Exception {
	
		TestPerformer testPerformer;
	
		String modelFileName;
		String oclFileName;
	
		oclFileName = "context/commentPositive06.ocl";
		modelFileName = "testmodel.uml";
	
		/* Try to get the TestPerformer. */
		testPerformer =
				TestPerformer.getInstance(AllContextTests.META_MODEL_ID,
						AllContextTests.MODEL_BUNDLE, AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);
	
		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse an OCL file containing a comment that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testCommentPositive07() throws Exception {
	
		TestPerformer testPerformer;
	
		String modelFileName;
		String oclFileName;
	
		oclFileName = "context/commentPositive07.ocl";
		modelFileName = "testmodel.uml";
	
		/* Try to get the TestPerformer. */
		testPerformer =
				TestPerformer.getInstance(AllContextTests.META_MODEL_ID,
						AllContextTests.MODEL_BUNDLE, AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);
	
		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse an OCL file containing a comment that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testCommentPositive08() throws Exception {
	
		TestPerformer testPerformer;
	
		String modelFileName;
		String oclFileName;
	
		oclFileName = "context/commentPositive08.ocl";
		modelFileName = "testmodel.uml";
	
		/* Try to get the TestPerformer. */
		testPerformer =
				TestPerformer.getInstance(AllContextTests.META_MODEL_ID,
						AllContextTests.MODEL_BUNDLE, AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);
	
		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse an OCL file containing a comment that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testCommentPositive09() throws Exception {
	
		TestPerformer testPerformer;
	
		String modelFileName;
		String oclFileName;
	
		oclFileName = "context/commentPositive09.ocl";
		modelFileName = "testmodel.uml";
	
		/* Try to get the TestPerformer. */
		testPerformer =
				TestPerformer.getInstance(AllContextTests.META_MODEL_ID,
						AllContextTests.MODEL_BUNDLE, AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);
	
		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse an OCL file containing a comment that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testCommentPositive10() throws Exception {
	
		TestPerformer testPerformer;
	
		String modelFileName;
		String oclFileName;
	
		oclFileName = "context/commentPositive10.ocl";
		modelFileName = "testmodel.uml";
	
		/* Try to get the TestPerformer. */
		testPerformer =
				TestPerformer.getInstance(AllContextTests.META_MODEL_ID,
						AllContextTests.MODEL_BUNDLE, AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);
	
		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}
}