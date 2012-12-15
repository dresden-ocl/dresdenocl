/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.mopp;

public class OclTokenStyleInformationProvider {
	
	public static String TASK_ITEM_TOKEN_NAME = "TASK_ITEM";
	
	public org.dresdenocl.language.ocl.resource.ocl.IOclTokenStyle getDefaultTokenStyle(String tokenName) {
		if ("ML_COMMENT".equals(tokenName)) {
			return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyle(new int[] {0x00, 0x80, 0x00}, null, false, true, false, false);
		}
		if ("SL_COMMENT".equals(tokenName)) {
			return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyle(new int[] {0x00, 0x80, 0x00}, null, false, true, false, false);
		}
		if ("BOOLEAN_LITERAL".equals(tokenName)) {
			return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyle(new int[] {0x80, 0x00, 0x40}, null, true, false, false, false);
		}
		if ("STATIC".equals(tokenName)) {
			return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyle(new int[] {0x80, 0x00, 0x40}, null, true, false, false, false);
		}
		if ("COLLECTION_TYPES".equals(tokenName)) {
			return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyle(new int[] {0x80, 0x00, 0x40}, null, true, false, false, false);
		}
		if ("package".equals(tokenName)) {
			return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyle(new int[] {0x80, 0x00, 0x55}, null, true, false, false, false);
		}
		if ("endpackage".equals(tokenName)) {
			return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyle(new int[] {0x80, 0x00, 0x55}, null, true, false, false, false);
		}
		if ("context".equals(tokenName)) {
			return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyle(new int[] {0x80, 0x00, 0x55}, null, true, false, false, false);
		}
		if ("init".equals(tokenName)) {
			return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyle(new int[] {0x80, 0x00, 0x55}, null, true, false, false, false);
		}
		if ("derive".equals(tokenName)) {
			return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyle(new int[] {0x80, 0x00, 0x55}, null, true, false, false, false);
		}
		if ("inv".equals(tokenName)) {
			return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyle(new int[] {0x80, 0x00, 0x55}, null, true, false, false, false);
		}
		if ("def".equals(tokenName)) {
			return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyle(new int[] {0x80, 0x00, 0x55}, null, true, false, false, false);
		}
		if ("pre".equals(tokenName)) {
			return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyle(new int[] {0x80, 0x00, 0x55}, null, true, false, false, false);
		}
		if ("post".equals(tokenName)) {
			return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyle(new int[] {0x80, 0x00, 0x55}, null, true, false, false, false);
		}
		if ("body".equals(tokenName)) {
			return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyle(new int[] {0x80, 0x00, 0x55}, null, true, false, false, false);
		}
		if ("iterate".equals(tokenName)) {
			return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyle(new int[] {0x80, 0x00, 0x55}, null, true, false, false, false);
		}
		if ("Tuple".equals(tokenName)) {
			return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyle(new int[] {0x80, 0x00, 0x55}, null, true, false, false, false);
		}
		if ("if".equals(tokenName)) {
			return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyle(new int[] {0x80, 0x00, 0x55}, null, true, false, false, false);
		}
		if ("then".equals(tokenName)) {
			return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyle(new int[] {0x80, 0x00, 0x55}, null, true, false, false, false);
		}
		if ("else".equals(tokenName)) {
			return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyle(new int[] {0x80, 0x00, 0x55}, null, true, false, false, false);
		}
		if ("endif".equals(tokenName)) {
			return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyle(new int[] {0x80, 0x00, 0x55}, null, true, false, false, false);
		}
		if ("let".equals(tokenName)) {
			return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyle(new int[] {0x80, 0x00, 0x55}, null, true, false, false, false);
		}
		if ("in".equals(tokenName)) {
			return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyle(new int[] {0x80, 0x00, 0x55}, null, true, false, false, false);
		}
		if ("invalid".equals(tokenName)) {
			return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyle(new int[] {0x80, 0x00, 0x55}, null, true, false, false, false);
		}
		if ("null".equals(tokenName)) {
			return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyle(new int[] {0x80, 0x00, 0x55}, null, true, false, false, false);
		}
		if ("QUOTED_39_39".equals(tokenName)) {
			return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyle(new int[] {0x2A, 0x00, 0xFF}, null, false, false, false, false);
		}
		if ("TASK_ITEM".equals(tokenName)) {
			return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyle(new int[] {0x7F, 0x9F, 0xBF}, null, true, false, false, false);
		}
		return null;
	}
	
}
