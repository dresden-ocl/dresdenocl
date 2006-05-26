/*
 * DeclarativeTemplate.java
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

package tudresden.ocl20.codegen.decl.template;

import org.antlr.stringtemplate.StringTemplate;

/**
 * The DeclarativeTemplate is a simple adapter for the
 * StringTemplate class.<br />
 * This class is used by the DeclarativeCodeGenerator. 
 * 
 * @author Florian Heidenreich
 *
 */
public class DeclarativeTemplate implements Template {

	/**
	 * The adapted template object
	 */
	private StringTemplate template;
	
	/**
	 * Constructs a new DeclarativeTemplate
	 * @param template the adapted template object
	 */
	public DeclarativeTemplate(StringTemplate template) {
		this.template = template;
	}
	
	/**
	 * Assign a value to an attributes' name. Please note, that
	 * you can call this method multiple times for the same name
	 * to build lists of values.
	 * 
	 * @param name the name of the attribute
	 * @param value the value of the attribute
	 */
	public void setAttribute(String name, String value) {
		template.setAttribute(name, value);
	}

	/**
	 * @return the evaluated template
	 */
	public String toString() {
		return template.toString();
	}

}
