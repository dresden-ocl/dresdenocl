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

import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

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
	private STGroup templateGroup;

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
	public StringTemplateEngine(LinkedList<String> groupFiles)
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
	 *            The names of the file as full path resource folder.
	 * @throws TemplateException
	 *             Thrown, if a given File name can not be found.
	 */
	public StringTemplateEngine(String file) throws TemplateException {

		this();
		addFile(file);
	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateEngine#getTemplate(String)
	 */
	public ITemplate getTemplate(String name) {

		try {
			ST stringTemplate = templateGroup.getInstanceOf(name);
			
			if (stringTemplate != null)
				return new StringTemplateAdapter(stringTemplate);
			else
				return null;
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
	public void addFile(String file) throws TemplateException {

		addFiles(Arrays.asList(file));

	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateEngine#addFiles(LinkedList)
	 */
	public void addFiles(List<String> files) throws TemplateException {

		assert (files.size() > 0);

		STGroup lastGroup;
		
		if (templateGroup == null) {
			templateGroup = new STGroupFile(files.get(0));

		} else {
			lastGroup = templateGroup;
			templateGroup = new STGroupFile(files.get(0));
			templateGroup.importTemplates(lastGroup);
		}

		lastGroup = templateGroup;
		for (int i = 1; i < files.size(); i++) {

			STGroupFile superGroup;

			superGroup = new STGroupFile(files.get(i));

			superGroup.importTemplates(lastGroup);
			lastGroup = superGroup;
		}

	}
}
