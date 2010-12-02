/*
 * OCL22SQLModel.java
 * 
 * Copyright (c) 2006 Florian Heidenreich
 * Contact: <mail@fheidenreich.de>
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

package tudresden.ocl20.codegen.decl.tools.sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PushbackReader;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.jmi.reflect.RefPackage;

import tudresden.ocl20.codegen.decl.codegen.DeclarativeCodeGenerator;
import tudresden.ocl20.codegen.decl.mapping.MappedModel;
import tudresden.ocl20.codegen.decl.template.DeclarativeTemplateEngine;
import tudresden.ocl20.codegen.decl.template.TemplateEngine;
import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.OclModel;
import tudresden.ocl20.core.Repository;
import tudresden.ocl20.core.RepositoryManager;
import tudresden.ocl20.core.jmi.uml15.core.Classifier;
import tudresden.ocl20.core.jmi.uml15.uml15.Uml15Package;
import tudresden.ocl20.core.jmi.uml15.uml15ocl.expressions.ExpressionInOcl;
import tudresden.ocl20.core.parser.astgen.Heritage;
import tudresden.ocl20.core.parser.astgen.LAttrAstGenerator;
import tudresden.ocl20.core.parser.sablecc.lexer.Lexer;
import tudresden.ocl20.core.parser.sablecc.node.Start;
import tudresden.ocl20.core.parser.sablecc.parser.Parser;
import tudresden.ocl20.transformation.TransformationEngine;
import tudresden.ocl20.transformation.impl.Cwm2DdlImpl;
import tudresden.ocl20.transformation.impl.Uml2CwmAndMappedModel;
import tudresden.ocl20.transformation.interfaces.Tuple;

/**
 * Worker model of the OCL22SQL GUI
 * 
 * @author Florian Heidenreich
 */
public class OCL22SQLModel {

	private DeclarativeCodeGenerator codeGenerator;
	private MappedModel mappedModel;
	private OclModel model;
	private String modelName = "ocl22sql";
	private String modelNameOut = "ocl22sql_transformed";
	private String tableSchema;
	private TemplateEngine templateEngine;
	
	public void loadModel(String xmiSourcePath) throws Exception {
		try {
			Repository r = RepositoryManager.getRepository();
			RefPackage modelRef = r.getModel(modelName);
			if (modelRef != null) {
				r.deleteModel(modelRef);
			}
			
			String xmiFileUrl = (new File(xmiSourcePath)).toURL().toString();
			System.err.println("Loading XMI ...");
			model = new OclModel(MetaModelConst.UML15, xmiFileUrl, modelName);
			model.beginTrans(true);
		} catch (Exception e) {
			if (model != null) {
				model.close();
				model = null;
			}
			throw e; // re-throw exception
		}
	}

	public void initTransformationCWM() throws Exception {
		try {
			TransformationEngine te = TransformationEngine.getInstance();
			te.setModel_inName(modelName);
			te.setModel_inType(MetaModelConst.UML15);
			te.setModel_outName(modelNameOut);
			te.loadTransformation(Uml2CwmAndMappedModel.class.getSimpleName());
		} catch (Exception e) {
			if (model != null) {
				model.close();
				model = null;
			}
			throw e; // re-throw exception
		}
	}

	public void invokeTransformationCWM() throws Exception {
		try {
			TransformationEngine te = TransformationEngine.getInstance();
			te.invoke();
			mappedModel = (MappedModel)((Tuple)te.getResult()).getElem2();
		} catch (Exception e) {
			if (model != null) {
				model.close();
				model = null;
			}
			throw e; // re-throw exception
		}
	}

	public void initTransformationDDL(String filePath) throws Exception {
		try {
			TransformationEngine te = TransformationEngine.getInstance();
			te.setModel_inName(modelNameOut);
			te.setModel_inType("CWM"); //TODO use value from MetaModelConst after it was added there
			te.setModel_outName(filePath);
			te.loadTransformation(Cwm2DdlImpl.class.getSimpleName());
		} catch (Exception e) {
			if (model != null) {
				model.close();
				model = null;
			}
			throw e; // re-throw exception
		}
	}

	public void invokeTransformationDDL() throws Exception {
		try {
			TransformationEngine te = TransformationEngine.getInstance();
			te.invoke();
			tableSchema = (String)te.getResult();
		} catch (Exception e) {
			if (model != null) {
				model.close();
				model = null;
			}
			throw e; // re-throw exception
		}
	}

	public void generateAST(String constraintsFilePath) {
		try {				
			System.err.println("Reading OCL file " + constraintsFilePath + "...");
			Lexer lexer = new Lexer (new PushbackReader(new BufferedReader(new FileReader(constraintsFilePath)), 1024));
			
			System.err.println("Parsing OCL file...");
			Parser parser = new Parser(lexer);
			Start cst = parser.parse();
			
			System.err.println("Generating AST...");
			LAttrAstGenerator astgen = new LAttrAstGenerator(model);
			Heritage hrtg = new Heritage();
			
			cst.apply(astgen, hrtg);
		} catch (Exception e) {
            e.printStackTrace(System.err);
        }
	}

	public String generateCode(LinkedList<String> templateGroups) {
		try {
			templateEngine = new DeclarativeTemplateEngine(templateGroups);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "";
		}
		codeGenerator = new DeclarativeCodeGenerator(mappedModel, templateEngine);
		
		StringBuffer integrityViews = new StringBuffer();
		String lineSeperator = System.getProperty("line.separator");
		
		Iterator i = ((Uml15Package)model.getModel()).getCore().getClassifier().refAllOfType().iterator();
		while (i.hasNext()) {
			Collection expressions = ((Classifier)i.next()).getExpressionInOclA();
			Iterator ie = expressions.iterator();
			while (ie.hasNext()) {
				integrityViews.append(codeGenerator.getCode((ExpressionInOcl)ie.next()));
				integrityViews.append(lineSeperator);
				integrityViews.append(lineSeperator);
				codeGenerator.reset();
			}
		}
		
		return integrityViews.toString();
	}
	
	public String getTableSchema() {
		return tableSchema;
	}
}
