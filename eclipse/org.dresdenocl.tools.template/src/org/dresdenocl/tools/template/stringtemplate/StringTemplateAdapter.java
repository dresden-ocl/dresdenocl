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

import org.antlr.stringtemplate.StringTemplate;
import org.stringtemplate.v4.ST;

import tudresden.ocl20.pivot.tools.template.ITemplate;

/**
 * <p>
 * The {@link StringTemplateAdapter} is a simple adapter for the
 * {@link StringTemplate} class.
 * </p>
 * 
 * @author Florian Heidenreich
 */
public class StringTemplateAdapter implements ITemplate {

	/**
	 * <p>
	 * The adapted template object.
	 * </p>
	 */
	private ST template;

	/**
	 * <p>
	 * Constructs a new {@link StringTemplateAdapter}.
	 * </p>
	 * 
	 * @param template
	 *          The adapted {@link ST} object.
	 */
	public StringTemplateAdapter(ST template) {

		this.template = template;
	}

	/**
	 * <p>
	 * Assign a value to an attributes' name. Please note, that you can call this
	 * method multiple times for the same name to build lists of values.
	 * </p>
	 * 
	 * @param name
	 *          The name of the attribute which shall be set.
	 * @param value
	 *          The value of the attribute which shall be set.
	 */
	public void setAttribute(String name, Object value) {
		try {
			template.add(name, value);
		} catch (IllegalArgumentException e) {
			 throw new IllegalArgumentException("No such attribute "+name+" in template "+getName(),e);
		}
	}

	/**
	 * @return The evaluated template as a {@link String}.
	 */
	public String toString() {
		String s =  template.render();
		
		return s;
	}

	public void reset() {
		template = template.groupThatCreatedThisInstance.getInstanceOf(getName());
	}

	public String getName() {

		return template.getName();
	}
}
