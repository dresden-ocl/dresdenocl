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
 * SequentialTransformations is a abstract implementation of a sequential transformation composition.
 * @author Christian Wende
 *
 * @param <IN>
 * @param <OUT>
 */
public abstract class SequentialTransformations<IN extends RefPackage,OUT> extends M2XTransformation<IN, OUT> {

		
	private String modelInName;
	private String outName;
		
	private String id1;
	private String id2;
		
	public SequentialTransformations(String modelInName, String outName, String tid1, String tid2) throws ModelAccessException  {
		super(modelInName, outName);
		this.modelInName = modelInName;
		this.outName = outName;
		
		TransformationEngine te = TransformationEngine.getInstance();
		
		te.setModel_inName(modelInName);
		te.setModel_outName("intermediateResult");
		id1 = te.loadTransformation(tid1);
		te.setModel_inName("intermediateResult");
		te.setModel_outName(outName);
		id2 = te.loadTransformation(tid2);
			
	}

	@Override
	public void invoke() throws TransformationException, InvalidModelException {
		TransformationEngine te = TransformationEngine.getInstance();
				
		te.invoke(id1);
	//	A a = (A) te.getResult(id1);
		
		te.invoke(id2);
		OUT b = (OUT) te.getResult(id2);

		this.out = b;
	
	
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
