package org.dresdenocl.tools.codegen.declarativ.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dresdenocl.pivotmodel.AssociationProperty;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.tools.codegen.declarativ.IOcl2DeclSettings;
import org.dresdenocl.tools.codegen.declarativ.mapping.IMappedModel;
import org.dresdenocl.tools.template.ITemplateGroup;

public class Ocl2DeclSettings implements IOcl2DeclSettings {

	protected ITemplateGroup templateGroup;

	/** The location where the transformed Code shall be saved. */
	protected String sourceDirectory;

	/** The sub folder into which the aspect files shall be generated. */
	protected String constraintFolder;

	protected IMappedModel mappedModel;

	protected int modus;

	protected Map<String, String> prefix;

	protected int saveCode;

	protected boolean schemaUsing;
	
	protected Map<Property, String> associationTableName;

	public Ocl2DeclSettings() {

		this.constraintFolder = "constraint";
		this.sourceDirectory = "";
		this.saveCode = 1;
		this.templateGroup = null;
		this.mappedModel = null;
		this.schemaUsing = false;
		this.prefix = new HashMap<String, String>();
		this.associationTableName = new HashMap<Property, String>();
		setDefaultPrefix();
	}

	public String getConstraintDirectory() {

		return this.constraintFolder;
	}

	public String getSourceDirectory() {

		return this.sourceDirectory;
	}

	public ITemplateGroup getTemplateGroup() {

		return this.templateGroup;
	}

	public void setConstraintDirectory(String folderName) {

		this.constraintFolder = folderName;

	}

	public void setSourceDirectory(String path) {

		this.sourceDirectory = path;
	}

	public void setTemplateGroup(ITemplateGroup templateGroup) {

		this.templateGroup = templateGroup;
	}

	public IMappedModel getMappedModel() {

		return mappedModel;
	}

	public void setMappedModel(IMappedModel mappedModel) {

		this.mappedModel = mappedModel;
	}

	public int getSaveCode() {

		return this.saveCode;
	}

	public void setSaveCode(int saveCode) {

		this.saveCode = saveCode;
	}

	public void setModus(int modus) {

		this.modus = modus;

	}

	public int getModus() {

		return this.modus;
	}

	public void setTablePrefix(String tablePrefix) {

		prefix.put("table", tablePrefix);
	}

	public String getTablePrefix() {

		return prefix.get("table");
	}

	public void setObjectViewPrefix(String objectViewPrefix) {

		prefix.put("objectview", objectViewPrefix);
	}

	public String getObjectViewPrefix() {

		return prefix.get("objectview");
	}

	public void setAssociationTablePrefix(String associationTablePrefix) {

		prefix.put("associationtable", associationTablePrefix);
	}

	public String getAssociationTablePrefix() {

		return prefix.get("associationtable");
	}

	public void setPrimaryKeyPrefix(String primaryKeyPrefix) {

		prefix.put("primarykey", primaryKeyPrefix);
	}

	public String getPrimaryKeyPrefix() {

		return prefix.get("primarykey");
	}

	public void setForeignKeyPrefix(String foreignKeyPrefix) {

		prefix.put("foreignkey", foreignKeyPrefix);
	}

	public String getForeignKeyPrefix() {

		return prefix.get("foreignkey");
	}

	private void setDefaultPrefix() {

		prefix.put("table", "T_");
		prefix.put("objectview", "OV_");
		prefix.put("associationtable", "ASS_");
		prefix.put("primarykey", "PK_");
		prefix.put("foreignkey", "FK_");
	}

	public String getUniqueAssociationTableName(Property property) {

		String result = getUniqueAssTableName(property);
		if (result == null) {
			result = generateUniqueAssName(property);
		}
		return getAssociationTablePrefix() + result;
	}

	private String getUniqueAssTableName(Property property) {

		return associationTableName.get(property);
	}

	private String generateUniqueAssName(Property property) {

		List<String> resultList = new LinkedList<String>();
		String delimiter = "_";
		String assName = "";

		resultList.add(property.getName());
		if (property instanceof AssociationProperty) {
			for (AssociationProperty prop : ((AssociationProperty) property)
					.getInverseAssociationProperties()) {
				resultList.add(prop.getName());
			}
			Collections.sort(resultList);
			for (String s : resultList) {
				assName += delimiter + s;
			}
			assName = assName.substring(1);
		}
		else {
			assName = property.getOwner().getName() + delimiter + resultList.get(0);
		}
		int i = 0;
		String result = assName;
		while (checkAssociationTableName(result)) {
			result = assName + delimiter + ++i;
		}
		associationTableName.put(property, result);
		if (property instanceof AssociationProperty) {
			for (AssociationProperty prop : ((AssociationProperty) property)
					.getInverseAssociationProperties()) {
				associationTableName.put(prop, result);
			}
		}

		return result;
	}

	private boolean checkAssociationTableName(String assName) {

		return associationTableName.containsValue(assName);
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclSettings#setSchemaUsing(boolean)
	 */
	@Override
	public void setSchemaUsing(boolean schemaUsing) {
		this.schemaUsing = schemaUsing;
		
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclSettings#isSchemaUsing()
	 */
	@Override
	public boolean isSchemaUsing() {
		return schemaUsing;
	}

}
