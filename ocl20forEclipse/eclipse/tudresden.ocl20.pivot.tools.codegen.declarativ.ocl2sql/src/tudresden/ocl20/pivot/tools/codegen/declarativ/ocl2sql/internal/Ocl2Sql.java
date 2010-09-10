/*
 * OCL22SQL.java
 * 
 * Copyright (c) 2006-2010 Florian Heidenreich, Bjoern Freitag
 * Contact: <mail@fheidenreich.de>
 *
 * This file is part of the Dresden OCL2.0 Toolkit for Eclipse
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

package tudresden.ocl20.pivot.tools.codegen.declarativ.ocl2sql.internal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclCode;
import tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclSettings;
import tudresden.ocl20.pivot.tools.codegen.declarativ.Ocl2DeclCodeFactory;
import tudresden.ocl20.pivot.tools.codegen.declarativ.mapping.IMappedModel;
import tudresden.ocl20.pivot.tools.codegen.declarativ.ocl2sql.IOcl2Sql;
import tudresden.ocl20.pivot.tools.codegen.declarativ.ocl2sql.Ocl2SqlPlugin;
import tudresden.ocl20.pivot.tools.codegen.exception.Ocl2CodeException;
import tudresden.ocl20.pivot.tools.template.ITemplate;
import tudresden.ocl20.pivot.tools.transformation.ITransformation;
import tudresden.ocl20.pivot.tools.transformation.TransformationFactory;
import tudresden.ocl20.pivot.tools.transformation.exception.InvalidModelException;
import tudresden.ocl20.pivot.tools.transformation.exception.TransformationException;
import tudresden.ocl20.pivot.tools.transformation.impl.Tuple;

/**
 * OCL2SQL-Compiler
 * 
 * @author Florian Heidenreich
 * @author Bjoern Freitag
 */
public class Ocl2Sql implements IOcl2Sql {

	private Logger LOGGER = Ocl2SqlPlugin.getLogger(Ocl2Sql.class);

	private IOcl2DeclCode codeGenerator;
	private IOcl2DeclSettings ocl2DeclSettings;

	private IModel model;

	public Ocl2Sql() {

		this.resetEnvironment();

	}

	public String getTableSchema() {

		return this.getSettings().getTemplateGroup().getDisplayName();
	}

	public IOcl2DeclSettings getSettings() {

		return this.ocl2DeclSettings;
	}

	public void setSettings(IOcl2DeclSettings settings) {

		this.ocl2DeclSettings = settings;
	}

	private String create_SQLCommentar(String text) {

		ITemplate template =
				this.getSettings().getTemplateGroup().getTemplate("createComment");
		template.setAttribute("comment", text);
		return template.toString() + "\n";
	}

	public List<String> transformFragmentCode(List<Constraint> constraints)
			throws Ocl2CodeException {

		String dateString =
				new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(new Date());
		String sqlCommentar =
				create_SQLCommentar("Generated from OCL2SQL generator(Dresden OCL2)");
		sqlCommentar += create_SQLCommentar("Generation time: " + dateString);
		sqlCommentar +=
				create_SQLCommentar("Template: "
						+ this.getSettings().getTemplateGroup().getDisplayName());
		if (this.getSettings().getModus() == IOcl2DeclSettings.MODUS_TYPED) {
			sqlCommentar += create_SQLCommentar("Modus: typed");
		}
		else {
			sqlCommentar += create_SQLCommentar("Modus: vertical");
		}

		this.codeGenerator =
				Ocl2DeclCodeFactory.getInstance().createOcl2DeclCodeGenerator();
		this.codeGenerator.setSettings(this.getSettings());
		if (this.getSettings().isSaveCode()) {
			try {
				ITransformation<Namespace, IOcl2DeclSettings, Tuple<String, IMappedModel>> pdamm;
				pdamm =
						TransformationFactory.getInstance().getParallelTransformation(
								"Pivot2DdlAndMappedModel", Namespace.class, String.class,
								IMappedModel.class, IOcl2DeclSettings.class, dateString,
								dateString + "_generated");
				pdamm.setSettings(ocl2DeclSettings);
				pdamm.setParameterIN(model.getRootNamespace());
				pdamm.invoke();
				Tuple<String, IMappedModel> tupleDdlMM = pdamm.getResult();
				saveToFile(sqlCommentar + tupleDdlMM.getElem1(),
						dateString.concat("_schema") + ".sql");
			} catch (ModelAccessException e) {
				throw new Ocl2CodeException("Get not a namespace of active model",e);
			} catch (TransformationException e) {
				throw new Ocl2CodeException("Can't transformation the model",e);
			} catch (InvalidModelException e) {
				throw new Ocl2CodeException("Wrong model set",e);
			}

		}
		else {
			try {
				ITransformation<Namespace, IOcl2DeclSettings, IMappedModel> pmm;
				pmm =
						TransformationFactory.getInstance().getTransformation(
								"Pivot2MappedModelImpl", Namespace.class, IMappedModel.class,
								IOcl2DeclSettings.class, dateString, dateString + "_generated");
				pmm.setSettings(ocl2DeclSettings);
				pmm.setParameterIN(model.getRootNamespace());
				pmm.invoke();
			} catch (ModelAccessException e) {
				throw new Ocl2CodeException("Get not a namespace of active model",e);
			} catch (TransformationException e) {
				throw new Ocl2CodeException("Can't transformation the model",e);
			} catch (InvalidModelException e) {
				throw new Ocl2CodeException("Wrong model set",e);
			}
		}
		List<String> constraintList = null;
		if (this.ocl2DeclSettings.getMappedModel() != null) {
			constraintList = this.codeGenerator.transformFragmentCode(constraints);
			String output = sqlCommentar;
			for (String s : constraintList) {
				output += "\n\n";
				output += s;
			}
			saveToFile(output, dateString.concat("_view") + ".sql");
		}

		return constraintList;
	}

	public void resetEnvironment() {

		this.setSettings(Ocl2DeclCodeFactory.getInstance()
				.createOcl2DeclCodeSettings());
	}

	private void saveToFile(String text, String fileName)
			throws Ocl2CodeException {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("saveToFile(String, String)");
		}
		// no else.

		File outputFolder;
		File outputFile;
		FileOutputStream fileOutputStream;

		try {

			outputFolder = new File(this.ocl2DeclSettings.getSourceDirectory());

			/* Check if output folder does exists. */
			if (!outputFolder.isDirectory()) {
				outputFolder.mkdirs();
			}
			// no else.

			outputFile = new File(outputFolder.getAbsolutePath() + "/" + fileName);

			/* Create the output file. */
			fileOutputStream = new FileOutputStream(outputFile);
			fileOutputStream.write(text.getBytes());
			fileOutputStream.close();
		}

		catch (FileNotFoundException e) {

			String msg;

			msg = "File for saving generated code not found. ";
			msg += "File location was ";
			msg += this.ocl2DeclSettings.getSourceDirectory() + fileName + ".";

			LOGGER.fatal(msg);

			throw new Ocl2CodeException(msg);
		}

		catch (IOException e) {

			String msg;

			msg = "Opening of File for saving failed. ";
			msg += "File location was ";
			msg += this.ocl2DeclSettings.getSourceDirectory() + fileName + ".";

			LOGGER.fatal(msg);

			throw new Ocl2CodeException(msg);
		}

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("saveToFile(String, String,) - end");
		}
		// no else.
	}

	public void setInputModel(IModel inputModel) {

		model = inputModel;
	}
}
