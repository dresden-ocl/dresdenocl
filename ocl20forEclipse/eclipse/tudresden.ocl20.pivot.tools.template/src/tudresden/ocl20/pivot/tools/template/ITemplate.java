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

/**
 * <p>
 * The Template class is a general purpose interface for a Template class which
 * can be parameterized by key-value pairs.
 * </p>
 * 
 * @author Florian Heidenreich
 */
public interface ITemplate {

	/**
	 * @param name
	 *            The name of the attribute which shall be set.
	 * @param value
	 *            The value of the attribute which shall be set.
	 */
	public void setAttribute(String name, String value);

	/**
	 * @return The evaluated template as a {@link String}.
	 */
	public String toString();
}
