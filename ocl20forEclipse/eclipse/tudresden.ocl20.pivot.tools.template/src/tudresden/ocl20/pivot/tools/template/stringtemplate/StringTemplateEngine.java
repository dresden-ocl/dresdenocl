/*
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

package tudresden.ocl20.pivot.tools.template.stringtemplate;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.LinkedList;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import tudresden.ocl20.pivot.tools.template.ITemplate;
import tudresden.ocl20.pivot.tools.template.ITemplateEngine;
import tudresden.ocl20.pivot.tools.template.exception.TemplateException;

/**
 * <p>
 * The {@link StringTemplateEngine} is an adapter for the
 * {@link StringTemplateGroup} class. It loads a list of {@link ITemplate}s and
 * sub-{@link ITemplate}s and creates a {@link ITemplate}s hierarchy according
 * to the order of the provided {@link ITemplate}s groups.
 * </p>
 * 
 * @author Florian Heidenreich
 * @author Bjoern Freitag
 */
public class StringTemplateEngine implements ITemplateEngine {

	/**
	 * The adapted template group.
	 */
	private StringTemplateGroup templateGroup;
	
	/**
	 * The name of the TemplateEngine
	 */
	private String templateName = "StringTemplate";
	
	/** 
	 * The constructor of the StringTemplateEngine
	 */
	public StringTemplateEngine() {
		
	}
	
	/**
	 * <p>
	 * Creates a new {@link StringTemplateEngine} and loads a list of
	 * {@link ITemplate}s and sub-{@link ITemplate}s and creates a
	 * {@link ITemplate} hierarchy according to the order of the provided
	 * {@link ITemplate} groups.
	 * 
	 * @param groupFiles
	 *            The names of the group files as full paths to the projects
	 *            resource folder.
	 * @throws TemplateException 
	 *             Thrown, if a given File name can not be found.
	 */
	public StringTemplateEngine(LinkedList<URL> groupFiles)
			throws TemplateException {
		
		this();
		addFiles(groupFiles);
	}
	
	/**
	 * <p>
	 * Creates a new {@link StringTemplateEngine} and loads a list of
	 * {@link ITemplate}s and sub-{@link ITemplate}s and creates a
	 * {@link ITemplate} hierarchy according to the order of the provided
	 * {@link ITemplate} groups.
	 * 
	 * @param files
	 *            The names of the file as full path
	 *            resource folder.
	 * @throws TemplateException
	 *             Thrown, if a given File name can not be found.
	 */
	public StringTemplateEngine(URL file)
	throws TemplateException {
		this();
		addFile(file);
	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateEngine#getTemplate(String)
	 */
	public ITemplate getTemplate(String name) {
		try {
			return new StringTemplateAdapter(templateGroup.getInstanceOf(name));
		} catch (NullPointerException e) {
			return null;
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateEngine#getDisplayName()
	 */
	public String getDisplayName() {
		return templateName;
	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateEngine#getVersion()
	 */
	public String getVersion() {
		// TODO Auto-generated method stub
		return StringTemplate.VERSION;
	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateEngine#addFile(URL)
	 */
	public void addFile(URL file) throws TemplateException {
		LinkedList<URL> files = new LinkedList<URL>();
		files.add(file);
		addFiles(files);
		
	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateEngine#addFiles(LinkedList)
	 */
	public void addFiles(LinkedList<URL> files) throws TemplateException {
		assert (files.size() > 0);

		Reader groupReader = null;
		StringTemplateGroup lastGroup;

		try {
			groupReader = new InputStreamReader(files.getLast().openStream());
		} catch (IOException e) {
			throw new TemplateException("Files not correctly added");
		}
		
		if (templateGroup == null ) {
			templateGroup = new StringTemplateGroup(groupReader);
			
		} else {
			lastGroup = templateGroup;
			templateGroup = new StringTemplateGroup(groupReader);
			lastGroup.setSuperGroup(templateGroup);
		}

		lastGroup = templateGroup;
		
		for (int i = 1; i < files.size(); i++) {

			StringTemplateGroup superGroup;
			URL templatePath;

			templatePath = files.get(files.size() - i - 1);
			
			try {
				groupReader = new InputStreamReader(templatePath.openStream());
			} catch (IOException e) {
				throw new TemplateException("Files not correctly added");
			}
			
			superGroup = new StringTemplateGroup(groupReader);
			

			lastGroup.setSuperGroup(superGroup);
			lastGroup = superGroup;
		}
		
	}

}
