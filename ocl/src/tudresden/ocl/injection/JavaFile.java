/*
Copyright (C) 2000  Ralf Wiebicke
 
This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.
 
This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.
 
You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package tudresden.ocl.injection;

import java.util.*;

/**
 * Represents a parsed java file.
 * Manages the mapping of type names and types.
 * This depends on the current package and all
 * imported packages/classes.
 */
public final class JavaFile
{
	private String packagename;
	
	/**
	 * Contains all imported classes of this source file.
	 *
	 * More formally it contains the TypeName's imported
	 * by a SingleTypeImportDeclaration as defined in
	 * Java Language Specification 7.5.1
	 * as values, and the their corresponding simple names
	 * as key.
	 *
	 * @element-type String
	 * @key-type String
	 */
	private HashMap import_single=new HashMap();
	
	/**
	 * Contains all imported packages of this source file
	 * with a trailing dot.
	 *
	 * More formally it contains the PackageName's imported
	 * by a TypeImportOnDemandDeclaration as defined in
	 * Java Language Specification 7.5.2
	 * suffixed by a single dot.
	 *
	 * This implies, that the package names dont have the
	 * trailing '*' anymore, but the dot before '*' is still
	 * present.
	 *
	 * Additionally contains &quot;java.lang.&quot; to implement
	 * Java Language Specification 7.5.3. &quot;Automatic Imports&quot;.
	 *
	 * @element-type String
	 */
	private HashSet import_demand=new HashSet();
	
	/**
	 * Distiguishes two stages in life cycle of this object:
	 * getting imports via addImport and finding types via findType.
	 * @see #addImport
	 * @see #findType
	 */
	private boolean buildStage=true;
	
	
	public JavaFile()
	{
		// implements Java Language Specification 7.5.3. "Automatic Imports"
		import_demand.add("java.lang.");
	}
	
	/**
	 * Sets the package of this file.
	 * Necessary, since the package is not known at construction time.
	 * @throws InjectorParseException if called more than once.
	 */
	public final void setPackage(String packagename)
	throws InjectorParseException
	{
		if(!buildStage)
			throw new RuntimeException();
		if(this.packagename!=null)
			throw new InjectorParseException("only one package statement allowed.");
		
		this.packagename=packagename;
	}
	
	/**
	 * Gets the value of the package statement encountered
	 * in this java file.
	 * Is null, if no package statement found.
	 */
	public final String getPackageName()
	{
		return packagename;
	}
	
	/**
	 * Adds the value of an import statement.
	 */
	public final void addImport(String importname)
	throws InjectorParseException
	{
		if(!buildStage)
			throw new RuntimeException();
		
		if(importname.endsWith(".*"))
			import_demand.add(importname.substring(0,importname.length()-1));
		else
		{
			// implements Java Language Specification 7.5.1 "Single-Type-Import Declaration"
			String s=(String)(import_single.put(extractClassName(importname), importname));
			if(s!=null)
			{
				// this simple name was imported before
				if(!s.equals(importname))
					throw new InjectorParseException(
					"imported "+s+
					" and "+importname+
					" which is forbidden by "+
					"Java Language Specification 7.5.1 'Single-Type-Import Declaration'");
				// else this is a duplicate import statement and therefore ignored
			}
		}
	}
	
	/**
	 * Maps type names to types.
	 * This mapping depends on the import statements encountered
	 * in this java file.
	 *
	 * Nearly (TODO) implements
	 * Java Language Specification 6.5.4 &quot;Meaning of Type Names&quot;
	 * and
	 * Java Language Specification 7.5 &quot;Import Declarations&quot;
	 *
	 * Note, that the result depends also on the classes that are
	 * available (in the CLASSPATH) when running this method.
	 * Using this method in the ocl injector assumes,
	 * that at injection time the same classes are available
	 * as at compile time of the modified user code.
	 */
	public final Class findType(String typename)
	throws InjectorParseException
	{
		//System.out.println("findtype: >"+typename+"<");
		
		buildStage=false;
		
		// ATTENTION!
		// This is a hack!
		// Assumes, that array types are given as
		//   String[] x;
		// and not as
		//   String x[];
		// or
		//   String [] x;
		// Also does not work for arrays of basic types.
		// TODO
		String arrayBefore="";
		String arrayAfter="";
		while(typename.endsWith("[]"))
		{
			if(arrayBefore=="")
				arrayBefore="L";
			arrayBefore="["+arrayBefore;
			arrayAfter=";";
			typename=typename.substring(0,typename.length()-"[]".length());
			//System.out.println("modified typename to "+arrayBefore+typename+arrayAfter);
		}
		
		// implements Java Language Specification 6.5.4.2 "Qualified Type Names"
		// I dont know, how this should work for inner classes, TODO.
		if(typename.indexOf('.')>=0)
		{
			try
			{
				return Class.forName(arrayBefore+typename+arrayAfter);
			}
			catch(ClassNotFoundException e)
			{
				throw new InjectorParseException(e.toString());
			}
		}
		
		// implements Java Language Specification 6.5.4.1 "Simple Type Names"
		
		// implements items 1 and 2 of 6.5.4.1
		try
		{
			return Class.forName(
			arrayBefore + (
			packagename!=null ?
			packagename+'.'+typename :
				typename) +
				arrayAfter);
		}
		catch(ClassNotFoundException e)
		{};
		
		// implements item 1 of 6.5.4.1
		try
		{
			String s=(String)(import_single.get(typename));
			if(s!=null)
				return Class.forName(arrayBefore+s+arrayAfter);
		}
		catch(ClassNotFoundException e)
		{
			throw new InjectorParseException(e.toString());
		};
		
		// implements item 3 and 4 of 6.5.4.1
		// java.lang is already in imports_demand
		Class result=null;
		for(Iterator i=import_demand.iterator(); i.hasNext(); )
		{
			String importString=(String)i.next();
			String full_element_type=arrayBefore+importString+typename+arrayAfter;
			try
			{
				final Class x=Class.forName(full_element_type);
				if(result!=null)
				{
					throw new InjectorParseException(
					"type "+typename+
					" found in two imported packages "+result.getName()+
					" and "+x.getName()+
					". This is ambigous and forbidden by "+
					"Java Language Specification 6.5.4.1. 'Simple Type Names' item 4.");
				}
				result=x;
			}
			catch(ClassNotFoundException e)
			{};
		}
		return result;
	}
	
	/**
	 * Extracts the class name from a fully qualified class name
	 * (including package path.)
	 */
	public static String extractClassName(String fullclassname)
	{
		int pos=fullclassname.lastIndexOf('.');
		if(pos>=0)
			return fullclassname.substring(pos+1, fullclassname.length());
		else
			return fullclassname;
	}
	
	/**
	 * Extracts the package path (without trailing dot)
	 * from a fully qualified class name.
	 */
	public static String extractPackageName(String fullclassname)
	{
		int pos=fullclassname.lastIndexOf('.');
		if(pos>=0)
			return fullclassname.substring(0, pos);
		else
			return null;
	}
	
}
