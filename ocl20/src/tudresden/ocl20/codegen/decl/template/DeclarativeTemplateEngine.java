/*
 * DeclarativeTemplateEngine.java
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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedList;

import org.antlr.stringtemplate.StringTemplateGroup;

/**
 * <p>
 * The DeclarativeTemplateEngine is a adapter for the StringTemplateGroup
 * class for use in the DeclarativeCodeGenerator.
 * </p>
 * It loads a list of templates and sub-templates and creates a template
 * hierachy according to the order of the provided template groups.
 * 
 * @author Florian Heidenreich
 *
 */
public class DeclarativeTemplateEngine implements TemplateEngine {

	/**
	 * The adapted template group.
	 */
	private StringTemplateGroup leafGroup;
	
	/**
	 * Creates a new DeclarativeTemplateEngine and loads a list of templates 
	 * and sub-templates and creates a template hierachy according to the order 
	 * of the provided template groups.
	 * 
	 * @param groupFiles The names of the group files as relative paths to the
	 * projects resource folder.  
	 * @throws FileNotFoundException
	 */
	public DeclarativeTemplateEngine(LinkedList<String> groupFiles) throws FileNotFoundException {
		assert(groupFiles.size() > 0);
		
		Reader groupReader = new FileReader(getFullTemplateFileName(groupFiles.getLast()));
		leafGroup = new StringTemplateGroup(groupReader);

		StringTemplateGroup lastGroup = leafGroup; 
		for (int i = 1; i < groupFiles.size(); i++) {
			groupReader = new FileReader(getFullTemplateFileName(groupFiles.get(groupFiles.size()- i - 1)));
			StringTemplateGroup superGroup = new StringTemplateGroup(groupReader);
			lastGroup.setSuperGroup(superGroup);
			lastGroup = superGroup;
		}
	}
	
	/**
	 * Returns the DeclarativeTemplate specified by the given template name
	 */
	public DeclarativeTemplate getTemplate(String name) {
		return new DeclarativeTemplate(leafGroup.getInstanceOf(name));
	}

	/**
	 * Helper method to return the full path of the template file
	 */
	private String getFullTemplateFileName(String templateGroupFile) {
		String decodedFileName = ClassLoader.getSystemClassLoader().getResource(templateGroupFile).getFile();
		try {
			decodedFileName = URLDecoder.decode(decodedFileName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return decodedFileName;
	}
}
