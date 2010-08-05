/*
 * MappedModel.java
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

package tudresden.ocl20.pivot.tools.codegen.declarativ.mapping;

/**
 * The MappedModel acts as a bridge between the DeclarativeCodeGenerator (which has access
 * to the elements of the model from the repository) and the model in the target language.
 * 
 * Using the MappedModel, you can get target model representations of the Classifiers from 
 * the base model.
 * 
 * @author Florian Heidenreich
 *
 */
public interface IMappedModel {

	/**
	 * This method returns the IMappedClass for the given class name from the MappedModel
	 * 
	 * @param name the name of the class
	 * @return the IMappedClass for the given class name from the MappedModel
	 */
	public IMappedClass getClass(String name);
	
	
	/**
	 * Returns a unique alias which may be used in the declarative target language
	 * 
	 * @return unique alias which may be used in the declarative target language
	 */
	public String getUniqueAlias();


	public boolean isClass(String classname);
}
