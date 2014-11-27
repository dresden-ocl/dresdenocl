package org.dresdenocl.codegen.adapter;

import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapterFactory;
import org.eclipse.emf.common.notify.Adapter;

/**
 * Factory for all PivotAdapterGeneratorAdapters.
 * 
 * @author Michael Thiele
 * 
 */
public class PivotAdapterGeneratorAdapterFactory extends
		GenModelGeneratorAdapterFactory {

	@Override
	public Adapter createGenEnumAdapter() {
		return null;
	}

	@Override
	public Adapter createGenModelAdapter() {
		if (genModelGeneratorAdapter == null) {
			genModelGeneratorAdapter = new GenModelPivotAdapterGeneratorAdapter(this);
		}
		return genModelGeneratorAdapter;
	}

	@Override
	public Adapter createGenClassAdapter() {
		if (genClassGeneratorAdapter == null) {
			genClassGeneratorAdapter = new GenClassPivotAdapterGeneratorAdapter(this);
		}
		return genClassGeneratorAdapter;
	}

	@Override
	public Adapter createGenPackageAdapter() {
		if (genPackageGeneratorAdapter == null)
			genPackageGeneratorAdapter = new GenPackagePivotAdapterGeneratorAdapter(
					this);
		return genPackageGeneratorAdapter;
	}
}
