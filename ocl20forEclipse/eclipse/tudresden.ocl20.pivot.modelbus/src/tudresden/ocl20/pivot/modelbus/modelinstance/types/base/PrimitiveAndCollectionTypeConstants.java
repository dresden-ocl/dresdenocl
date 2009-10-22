/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Java Model Instance implementation of Dresden OCL2
for Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.modelbus.modelinstance.types.base;

import tudresden.ocl20.pivot.essentialocl.expressions.CollectionKind;
import tudresden.ocl20.pivot.essentialocl.types.CollectionType;
import tudresden.ocl20.pivot.essentialocl.types.TypesFactory;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceBoolean;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceInteger;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceReal;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceString;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceVoid;
import tudresden.ocl20.pivot.pivotmodel.PivotModelFactory;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * This class provides some constants that can be used as model {@link Type}s
 * for {@link Type}s that are not part of the {@link IModel} itself. E.g.,
 * {@link PrimitiveType}s and {@link CollectionType}s.
 * 
 * @author Claas Wilke
 * 
 */
public final class PrimitiveAndCollectionTypeConstants {

	/**
	 * The {@link PrimitiveType} implementation of the
	 * {@link PrimitiveTypeKind#BOOLEAN}. Because {@link PrimitiveType}s are not
	 * part of the {@link IModel}, their {@link Type} must be created externally.
	 * This field represents the {@link PrimitiveType} instance that is the only
	 * {@link Type} of all {@link IModelInstanceBoolean}s.
	 */
	public static final PrimitiveType MODEL_TYPE_BOOLEAN =
			PivotModelFactory.INSTANCE.createPrimitiveType();
	
	static{
		MODEL_TYPE_BOOLEAN.setKind(PrimitiveTypeKind.BOOLEAN);
		MODEL_TYPE_BOOLEAN.setName(PrimitiveTypeKind.BOOLEAN.getName());
	}

	/**
	 * The {@link PrimitiveType} implementation of the
	 * {@link PrimitiveTypeKind#INTEGER}. Because {@link PrimitiveType}s are not
	 * part of the {@link IModel}, their {@link Type} must be created externally.
	 * This field represents the {@link PrimitiveType} instance that is the only
	 * {@link Type} of all {@link IModelInstanceInteger}s.
	 */
	public static final PrimitiveType MODEL_TYPE_INTEGER =
			PivotModelFactory.INSTANCE.createPrimitiveType();
	static {
		MODEL_TYPE_INTEGER.setKind(PrimitiveTypeKind.INTEGER);
		MODEL_TYPE_INTEGER.setName(PrimitiveTypeKind.INTEGER.getName());
	}

	/**
	 * The {@link PrimitiveType} implementation of the
	 * {@link PrimitiveTypeKind#REAL}. Because {@link PrimitiveType}s are not part
	 * of the {@link IModel}, their {@link Type} must be created externally. This
	 * field represents the {@link PrimitiveType} instance that is the only
	 * {@link Type} of all {@link IModelInstanceReal}s.
	 */
	public static final PrimitiveType MODEL_TYPE_REAL =
			PivotModelFactory.INSTANCE.createPrimitiveType();
	static {
		MODEL_TYPE_REAL.setKind(PrimitiveTypeKind.REAL);
		MODEL_TYPE_REAL.setName(PrimitiveTypeKind.REAL.getName());
	}

	/**
	 * The {@link PrimitiveType} implementation of the
	 * {@link PrimitiveTypeKind#STRING}. Because {@link PrimitiveType}s are not
	 * part of the {@link IModel}, their {@link Type} must be created externally.
	 * This field represents the {@link PrimitiveType} instance that is the only
	 * {@link Type} of all {@link IModelInstanceString}s.
	 */
	public static final PrimitiveType MODEL_TYPE_STRING =
			PivotModelFactory.INSTANCE.createPrimitiveType();
	static {
		MODEL_TYPE_STRING.setKind(PrimitiveTypeKind.STRING);
		MODEL_TYPE_STRING.setName(PrimitiveTypeKind.STRING.getName());
	}

	/**
	 * The {@link PrimitiveType} implementation of the
	 * {@link PrimitiveTypeKind#VOID}. Because {@link PrimitiveType}s are not part
	 * of the {@link IModel}, their {@link Type} must be created externally. This
	 * field represents the {@link PrimitiveType} instance that is the only
	 * {@link Type} of all {@link IModelInstanceVoid}s.
	 */
	public static final PrimitiveType MODEL_TYPE_VOID =
			PivotModelFactory.INSTANCE.createPrimitiveType();
	static {
		MODEL_TYPE_VOID.setKind(PrimitiveTypeKind.VOID);
		MODEL_TYPE_VOID.setName(PrimitiveTypeKind.VOID.getName());
	}

	/**
	 * The {@link Type} of collections implementations that are of the kind
	 * {@link CollectionKind#BAG}. Because {@link CollectionType}s are not part of
	 * the {@link IModel}, their {@link Type} must be created externally. This
	 * field represents the {@link CollectionType} instance that is the only
	 * {@link Type} of all {@link IModelInstanceCollection}s of the kind
	 * {@link CollectionKind#BAG}.
	 */
	public static final CollectionType MODEL_TYPE_BAG =
			TypesFactory.INSTANCE.createCollectionType();

	static {
		MODEL_TYPE_BAG.setKind(CollectionKind.BAG);
	}

	/**
	 * The {@link Type} of collections implementations that are of the kind
	 * {@link CollectionKind#SEQUENCE}. Because {@link CollectionType}s are not
	 * part of the {@link IModel}, their {@link Type} must be created externally.
	 * This field represents the {@link CollectionType} instance that is the only
	 * {@link Type} of all {@link IModelInstanceCollection}s of the kind
	 * {@link CollectionKind#SEQUENCE}.
	 */
	public static final CollectionType MODEL_TYPE_SEQUENCE =
			TypesFactory.INSTANCE.createCollectionType();

	static {
		MODEL_TYPE_SEQUENCE.setKind(CollectionKind.SEQUENCE);
	}

	/**
	 * The {@link Type} of collections implementations that are of the kind
	 * {@link CollectionKind#SET}. Because {@link CollectionType}s are not part of
	 * the {@link IModel}, their {@link Type} must be created externally. This
	 * field represents the {@link CollectionType} instance that is the only
	 * {@link Type} of all {@link IModelInstanceCollection}s of the kind
	 * {@link CollectionKind#SET}.
	 */
	public static final CollectionType MODEL_TYPE_SET =
			TypesFactory.INSTANCE.createCollectionType();

	static {
		MODEL_TYPE_SET.setKind(CollectionKind.SET);
	}

	/**
	 * The {@link Type} of collections implementations that are of the kind
	 * {@link CollectionKind#ORDERED_SET}. Because {@link CollectionType}s are not
	 * part of the {@link IModel}, their {@link Type} must be created externally.
	 * This field represents the {@link CollectionType} instance that is the only
	 * {@link Type} of all {@link IModelInstanceCollection}s of the kind
	 * {@link CollectionKind#ORDERED_SET}.
	 */
	public static final CollectionType MODEL_TYPE_ORDERED_SET =
			TypesFactory.INSTANCE.createCollectionType();

	static {
		MODEL_TYPE_ORDERED_SET.setKind(CollectionKind.ORDERED_SET);
	}
}