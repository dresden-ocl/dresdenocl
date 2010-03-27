/*
Copyright (C) 2010 by Claas Wilke (claaswilke@gmx.net)

This file is part of the PAIN Case Study of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.examples.pain.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * <p>
 * Provides a jUnit Test Suite containing tests the PAIN case study.
 * </p>
 * 
 * @author Claas Wilke
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { TestAmendmentInformationDetails1.class,
		TestDirectDebitTransaction1.class,
		TestDirectDebitTransactionInformation1.class, TestGroupHeader1.class,
		TestLocalInstrument1Choice.class, TestMandateRelatedInformation1.class,
		TestPain00800101.class, TestPaymentInstructionInformation2.class,
		TestPaymentTypeInformation2.class, TestRemittanceInformation1.class,
		TestServiceLevel3Choice.class,
		TestStructuredRemittanceInformation6.class })
public class AllPainTests {
	/*
	 * This class remains completely empty, being used only as a holder for the
	 * above annotations.
	 */
}
