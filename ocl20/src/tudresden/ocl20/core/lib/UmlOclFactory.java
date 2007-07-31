/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL Compiler                                                      *
 * Copyright (C) 2005, 2006 Ronny Brandt (Ronny_Brandt@gmx.de).      *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a diploma project at the Chair for Software *
 * Technology, Dresden University Of Technology, Germany             *
 * (http://www-st.inf.tu-dresden.de).  It is understood that any     *
 * modification not identified as such is not covered by the         *
 * preceding statement.                                              *
 *                                                                   *
 * This work is free software; you can redistribute it and/or        *
 * modify it under the terms of the GNU Library General Public       *
 * License as published by the Free Software Foundation; either      *
 * version 2 of the License, or (at your option) any later version.  *
 *                                                                   *
 * This work is distributed in the hope that it will be useful,      *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU *
 * Library General Public License for more details.                  *
 *                                                                   *
 * You should have received a copy of the GNU Library General Public *
 * License along with this library; if not, write to the             *
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,      *
 * Boston, MA  02111-1307, USA.                                      *
 *                                                                   *
 * To submit a bug report, send a comment, or get the latest news on *
 * this project and other projects, please visit the web site:       *
 * http://www-st.inf.tu-dresden.de/ (Chair home page) or             *
 * http://www-st.inf.tu-dresden.de/ocl/ (project home page)          *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package tudresden.ocl20.core.lib;

import java.util.*;

public class UmlOclFactory extends AbstractOclFactory {
	
	private static UmlOclFactory instance;
	
	private UmlModelTypeManager typemanager;
	
	private UmlOclFactory() {
		typemanager = new UmlModelTypeManager(this);
	}
	
	public static UmlOclFactory getInstance() {
		if (instance == null)
			instance = new UmlOclFactory();
		return instance;
	}
	
	public UmlModelTypeManager getUmlModelTypeManager() {
		return typemanager;
	}
	
	@Override
	public OclRoot getOclRepresentationFor(OclType type, Object o) {
		if (type == null) 
			return getOclRepresentationFor(o);

		OclRoot result = null;
		if (o == null)
			result = new OclUndefined("OclRepresentation for null");
		else if (o instanceof Object[])
		{
			Object wrappedObject = ((Object[])o)[0];
			result = getOclRepresentationFor(type, wrappedObject);
		}
		else if (!(type instanceof OclCollectionType))
		{
			if (o instanceof Boolean)
				result = getOclRepresentationFor(((Boolean)o).booleanValue());
			else if (o instanceof Integer)
				result = getOclRepresentationFor(((Integer)o).intValue());
			else if (o instanceof Long)
				result = getOclRepresentationFor(((Long)o).longValue());
			else if (o instanceof Float)
				result = getOclRepresentationFor(((Float)o).floatValue());
			else if (o instanceof Double)
				result = getOclRepresentationFor(((Double)o).doubleValue());
			else if (o instanceof String)
				result = new OclString((String)o);
			else if (o instanceof Object)
				result = new UmlModelObject(o, this);
			// TODO: EnumType
			if (result == null || !type.isOfKind(result)) {
				throw new OclException("UML Type Mapping: Can not map " + o.getClass().getName() + " to " + type.getName());
			}
		}
		else
		{
			OclCollectionType oct = (OclCollectionType)type;
			if (oct.getKind() == OclCollectionType.SEQUENCE) {
				if (o instanceof List) {
					ArrayList list = new ArrayList();
					for (Iterator i = ((List)o).iterator(); i.hasNext(); )
						list.add(getOclRepresentationFor(oct.getElementType(), i.next()));
					result = new OclSequence(list);
				}
				else
					throw new OclException("UML Type Mapping: Expected a List, got " + o.getClass().getName() + "instead");
			}
			else if (oct.getKind() == OclCollectionType.ORDEREDSET) {
				if (o instanceof List) {
					ArrayList list = new ArrayList();
					// checking for dublicates is done by OclOrderedSet constructor
					for (Iterator i = ((List)o).iterator(); i.hasNext(); )
						list.add(getOclRepresentationFor(oct.getElementType(), i.next()));
					result = new OclOrderedSet(list);
				}
				else
					throw new OclException("UML Type Mapping: Expected a List, got " + o.getClass().getName() + "instead");
			}
			else if (oct.getKind() == OclCollectionType.BAG) {
				if (o instanceof Collection) {
					ArrayList list = new ArrayList();
					for (Iterator i = ((Collection)o).iterator(); i.hasNext(); )
						list.add(getOclRepresentationFor(oct.getElementType(), i.next()));
					result = new OclBag(list);
				}
				else
					throw new OclException("UML Type Mapping: Expected a Collection, got " + o.getClass().getName() + "instead");
			}
			else if (oct.getKind() == OclCollectionType.SET) {
				if (o instanceof Collection) {
					HashSet set = new HashSet();
					for (Iterator i = ((Collection)o).iterator(); i.hasNext(); )
						set.add(getOclRepresentationFor(oct.getElementType(), i.next()));
					result = new OclSet(set);
				}
				else
					throw new OclException("UML Type Mapping: Expected a Collection, got " + o.getClass().getName() + "instead");
			}
			else
				throw new OclException("Unknown collection kind or tried to instantiate abstract type OclCollection: " + oct.getKind());
		}
		return result;
	}

	@Override
	public OclRoot getOclRepresentationFor(Object o) {
		throw new OclException("getOclRepresentation without explicit type is not allowed in UmlOclFactory.");
	}

	@Override
	public Object reconvert(OclRoot oclObject) {
        throw new OclException("UML Type Mapping: reconvert: targetType must be explicitly stated!");
	}

	@Override
	public Object reconvert(OclParameter param) {
		int dir = param.getDirection();
		if (dir == OclParameter.DIR_IN)
			return reconvert(param.getNonOclType(), param.getValue(), false);
		else if (dir == OclParameter.DIR_OUT || dir == OclParameter.DIR_INOUT)
            return reconvert(param.getNonOclType(), param.getValue(), true);
		return null;
	}

	@Override
	public Object reconvert(NonOclType targetType, OclRoot oclObject) {
		return reconvert(targetType, oclObject, false);
	}
	
	/*
	 * Java code used with the Dresden OCL Toolkit can either use primitive
	 * types or wrappers for these types.
	 * Autoboxing and -unboxing is supported since Java 5.0.
	 */
	public Object reconvert(NonOclType targetType, OclRoot oclObject, boolean arrayWrap) {
		if (targetType == null)
			throw new OclException("UML Type Mapping: reconvert: targetType must not be null!");
		
		if (!(targetType instanceof UmlType))
			throw new OclException("UML Type Mapping: reconvert: targetType must be an UmlType");
		
		UmlType umlType = (UmlType)targetType;
		Object result = null;
		if (targetType == UmlType.INT && oclObject instanceof OclInteger) {
			if (arrayWrap && oclObject == null)
				result = new Integer[1];
			else
			{
				Integer i = new Integer(((OclInteger)oclObject).getInt());
				if (arrayWrap)
					result = new Integer[]{i};
				else
					result = i;
			}
		}
		else if (targetType == UmlType.BOOLEAN && oclObject instanceof OclBoolean) {
			if (arrayWrap && oclObject == null)
				result = new Boolean[1];
			else
			{
				Boolean b = Boolean.valueOf(((OclBoolean)oclObject).isTrue());
				if (arrayWrap)
					result = new Boolean[]{b};
				else
					result = b;
			}
		}
		else if (targetType == UmlType.STRING && oclObject instanceof OclString) {
			if (arrayWrap && oclObject == null)
				result = new String[1];
			else
			{
				String s = ((OclString)oclObject).getString();
				if (arrayWrap)
					result = new String[]{s};
				else
					result = s;
			}
		}
		else if (targetType == UmlType.LONG) {
			if (arrayWrap && oclObject == null)
				result = new Long[1];
			else if (oclObject instanceof OclInteger) {
				Long l = new Long(((OclInteger)oclObject).getLong());
				if (arrayWrap)
					result = new Long[]{l};
				else
					result = l;
			}
		}
		else if (targetType == UmlType.DOUBLE) {
			if (arrayWrap && oclObject == null)
				result = new Double[1];
			else 
			{
				Double d = null;
				
				if (oclObject instanceof OclInteger)
					d = new Double(((OclInteger)oclObject).getDouble());
				else if (oclObject instanceof OclReal)
					d = new Double(((OclReal)oclObject).getDouble());
				
				if (d != null)
				{
					if (arrayWrap)
						result = new Double[]{d};
					else
						result = d;
				}
			}
		}
		else if (targetType == UmlType.FLOAT) {
			if (arrayWrap && oclObject == null)
				result = new Float[1];
			else 
			{
				Float f = null;
				
				if (oclObject instanceof OclInteger)
					f = new Float(((OclInteger)oclObject).getDouble());
				else if (oclObject instanceof OclReal)
					f = new Float(((OclReal)oclObject).getDouble());
				
				if (f != null)
				{
					if (arrayWrap)
						result = new Float[]{f};
					else
						result = f;
				}
			}
		}
		else if (targetType == UmlType.MODELTYPE && oclObject instanceof UmlModelObject) {
			if (arrayWrap && oclObject == null)
				throw new OclException("UML Type Mapping: reconvert: out parameters not supported for model types.");
			else
			{
				UmlModelObject impl = (UmlModelObject)oclObject;
				Object o = impl.getObject();
				if (arrayWrap)
					throw new OclException("UML Type Mapping: reconvert: inout parameters not supported for model types.");
				else
					result = o;
			}
		}
		if (result == null)
			throw new OclException("UML Type Mapping: Could not reconvert " + oclObject + " to " + targetType);
		
		return result;
	}

	@Override
	public OclModelType getOclModelTypeFor(String name) {
		OclModelType result = typemanager.getModelType(name);
		if (result == null) {
			throw new OclException("Type " + name + " not found.");
		}
		return result;
	}

	@Override
	public OclEnumType getOclEnumTypeFor(String pathname) {
		// TODO Auto-generated method stub
		return null;
	}

}
