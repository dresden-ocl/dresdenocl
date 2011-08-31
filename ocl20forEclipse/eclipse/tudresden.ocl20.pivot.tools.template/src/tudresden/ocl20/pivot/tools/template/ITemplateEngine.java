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

package tudresden.ocl20.pivot.tools.template;

import java.util.List;

import tudresden.ocl20.pivot.tools.template.exception.TemplateException;

/**
 * <p>
 * The {@link ITemplateEngine} is a general purpose interface for a class which
 * returns an {@link ITemplate} object for a given template name.
 * </p>
 * 
 * @author Florian Heidenreich
 * @author Bjoern Freitag
 */
public interface ITemplateEngine {

	/**
	 * @param name
	 *          The name of the {@link ITemplate} which shall be returned.
	 * @return A new instance of {@link ITemplate} for the given template name.
	 */
	public ITemplate getTemplate(String name);

	/**
	 * Returns the name of the {@link ITemplateGroup}.
	 * 
	 * @return the name of the TemplateEngine
	 */
	public String getDisplayName();

	/**
	 * Returns the version of the {@link ITemplateEngine}.
	 * 
	 * @return the version of the TemplateEngine
	 */
	public String getVersion();

	/**
	 * Added a new template file to this {@link ITemplateEngine}.
	 * 
	 * @param files
	 *          file path
	 * @throws TemplateException
	 *           If the template engine can't read the file or is the file exits.
	 */
	public void addFile(String file) throws TemplateException;

	/**
	 * Added a new template file to this {@link ITemplateEngine}.
	 * 
	 * @param file
	 *          full file path
	 * @throws TemplateException
	 *           If the template engine can't read the file or is the file exits.
	 */
	public void addFiles(List<String> files) throws TemplateException;

}
