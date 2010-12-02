/*
 * Created on 09.02.2006
 *
 */
package tudresden.ocl20.transformation.interfaces;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.jmi.reflect.RefPackage;

import tudresden.ocl20.transformation.TransformationEngine;
import tudresden.ocl20.transformation.exception.InvalidModelException;
import tudresden.ocl20.transformation.exception.ModelAccessException;
import tudresden.ocl20.transformation.exception.TransformationException;

/**
 * ParallelTransformation is an abstract implementation of
 * a parallel transformation composition.
 * 
 * To implement a composition you must a class which implements 
 * ParallelTransformation and define the involved tranformations using for example
 * super(modelInName, outName, Uml2CwmImpl.class.getSimpleName(), Uml2MappedModelImpl.class.getSimpleName());
 * in the constructor.
* @author Christian Wende
 *
 */
public abstract class ParallelTransformations<IN extends RefPackage, A ,B> extends M2XTransformation<IN, Tuple<A,B>> {

	
	private String modelInName;
	private String outName;
	
	private String id1;
	private String id2;
	
	/**
	 * The constructor for ParallelTransformations.
	 * @param modelInName The name of the in model.
	 * @param outName The name ouf the out entity.
	 * @param tid1 The id of the first transformation.
	 * @param tid2 The id of the second transformation.
	 * @throws ModelAccessException
	 */
	public ParallelTransformations(String modelInName, String outName, String tid1, String tid2) throws ModelAccessException {
		super(modelInName, outName);
		this.modelInName = modelInName;
		this.outName = outName;
		
		TransformationEngine te = TransformationEngine.getInstance();
		
		te.setModel_inName(modelInName);
		te.setModel_outName(outName);
		id1 = te.loadTransformation(tid1);
		id2 = te.loadTransformation(tid2);
			
	}

	@Override
	public void invoke() throws TransformationException, InvalidModelException {
		TransformationEngine te = TransformationEngine.getInstance();
				
		te.invoke(id1);
		A a = (A) te.getResult(id1);
		
		te.invoke(id2);
		B b = (B) te.getResult(id2);

		this.out = new Tuple<A,B>(a,b);
	
	
	}

	@Override
	protected void initRequiredParameters() {
	}

	public Collection<TParameter> getRequiredParameters() {
		Set<TParameter> paras = new HashSet<TParameter>();
		TransformationEngine te = TransformationEngine.getInstance();
		
		Collection<TParameter> pt1 = te.getRequiredParameters(id1);
		Collection<TParameter> pt2 = te.getRequiredParameters(id2);
		
		paras.addAll(pt1);
		paras.addAll(pt2);
		
		conf = new TConfiguration(paras);

		return conf.getRequiredParameters();
	}
	
	
	public void setConfigurationParameter(String key, String value) {
		TransformationEngine te = TransformationEngine.getInstance();
		te.setConfigurationParameter(id1, key, value);
		te.setConfigurationParameter(id2, key, value);
	}
	
	public Trace getTrace() {
		TransformationEngine te = TransformationEngine.getInstance();
		Trace t1 = te.getTrace(id1);
		Trace t2 = te.getTrace(id2);
		t1.addTrace(t2);
		return t1;
	}
	
	

}
