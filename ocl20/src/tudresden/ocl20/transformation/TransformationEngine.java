/*
 * Created on 18.01.2006
 *
 */
package tudresden.ocl20.transformation;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import tudresden.ocl20.transformation.exception.InvalidModelException;
import tudresden.ocl20.transformation.exception.TransformationException;
import tudresden.ocl20.transformation.impl.Cwm2DdlImpl;
import tudresden.ocl20.transformation.impl.Uml2CwmAndMappedModel;
import tudresden.ocl20.transformation.impl.Uml2CwmImpl;
import tudresden.ocl20.transformation.impl.Uml2Ddl;
import tudresden.ocl20.transformation.impl.Uml2MappedModelImpl;
import tudresden.ocl20.transformation.interfaces.ITransformation;
import tudresden.ocl20.transformation.interfaces.TParameter;
import tudresden.ocl20.transformation.interfaces.Trace;

/**
 * The TranformationEngine is the central point of the transformation framework. With the transformation engine
 * all metamodelbased transformations are managed, configured and executed.
 * 
 * @author Christian Wende
 *
 */
public class TransformationEngine {

	private String resourcePath;
	
	private static final String NO_CUR_TRANSFORMATION = "No Transformation currently loaded.";
	private static final String NO_SUCH_TRANSFORMATION = "No such Transformation loaded: ";
	private static TransformationEngine theInstance;
	private static int ids = 0;
	
	private ITransformation currentTransformation;
	private Map<String, ITransformation> loadedTransformations;
	private Map<String, TransformationInfo> allTransformations;
	
	private String model_inName;
	private String model_outName;
	private String model_inType;
	
	/**
	 * Returns the single instance of the TransformationEngine.
	 * @return Returns the single instance of the TransformationEngine.
	 */
	public static TransformationEngine getInstance() {
		if (theInstance == null) {
			theInstance = new TransformationEngine();
		}
		return theInstance;
		
	}
	
	private TransformationEngine() {
		allTransformations = new HashMap<String, TransformationInfo>();
		initTransformations();
		loadedTransformations = new HashMap<String, ITransformation>();
		try {
			resourcePath = URLDecoder.decode(ClassLoader.getSystemClassLoader().getResource("cwm/").getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			System.out.println("WARNING: No resourcepath found must set your own using TransformationEngine#setResourcePath");
		}
		
	}
	
	private void initTransformations() {
		registerTransformation(Uml2CwmImpl.class, new TransformationInfo(Uml2CwmImpl.in_type, Uml2CwmImpl.out_type, Uml2CwmImpl.class));
		registerTransformation(Cwm2DdlImpl.class, new TransformationInfo(Cwm2DdlImpl.in_type, Cwm2DdlImpl.out_type, Cwm2DdlImpl.class));
		registerTransformation(Uml2MappedModelImpl.class, new TransformationInfo(Uml2MappedModelImpl.in_type, Uml2MappedModelImpl.out_type, Uml2MappedModelImpl.class));
		registerTransformation(Uml2CwmAndMappedModel.class, new TransformationInfo(Uml2CwmAndMappedModel.in_type, Uml2CwmAndMappedModel.out_type, Uml2CwmAndMappedModel.class));
		registerTransformation(Uml2Ddl.class, new TransformationInfo(Uml2Ddl.in_type, Uml2Ddl.out_type, Uml2Ddl.class));
	}
	
	/**
	 * This method must be used to register a new Transformation to the TransformationEngine.
	 * @param implementingClass The Class that implements the transformation
	 * @param transInfo The metadata for the Transformation
	 */
	public void registerTransformation(Class implementingClass, TransformationInfo transInfo) {
		allTransformations.put(implementingClass.getSimpleName(), transInfo);
	}
	
	/**
	 * Instanciates a transformation for the given transformationId.
	 * @param transformationID The transformationId for which a new instance should be created for.
	 * @return Returns a unique runtime identifier for the instanciated transformation
	 */
	public String loadTransformation(String transformationID) {
		return loadTransformation(transformationID, model_inName, model_outName);
	}
	
	/**
	 * Instanciates a transformation for the given transformationId with the given out and in modelnames.
	 * @param transformationID The transformationId for which a new instance should be created for.
	 * @return Returns a unique runtime identifier for the instanciated transformation
	 */
	public String loadTransformation(String transformationId, String model_inName, String model_outName) {
		Class<? extends ITransformation> tClass = allTransformations.get(transformationId).getTransformation();
		Constructor<? extends ITransformation> cons;
		try {
			cons = tClass.getConstructor(new Class[] {String.class, String.class});
			this.currentTransformation = cons.newInstance(new Object[] {model_inName, model_outName});
			
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String id = getUniqueId();
		loadedTransformations.put(id, this.currentTransformation);
		return id;
	}
		
	private static String getUniqueId() {
		ids ++;
		return "Transaction-" + ids;
	}

	/**
	 * Invokes the last loaded transformation.
	 * @throws TransformationException
	 * @throws InvalidModelException
	 */
	public void invoke() throws TransformationException, InvalidModelException {
		if (currentTransformation == null) {
			throw new IllegalArgumentException(NO_CUR_TRANSFORMATION);
		}
		currentTransformation.invoke();
	}
	
	/**
	 * Invokes the transformation with the given runtime id.
	 * @param id The runtime id of the transformation, that should be invoked.
	 * @throws TransformationException
	 * @throws InvalidModelException
	 */
	public void invoke(String id) throws TransformationException, InvalidModelException {
		ITransformation t = loadedTransformations.get(id);
		if (t == null) {
			throw new IllegalArgumentException(NO_SUCH_TRANSFORMATION + id);
		}
		t.invoke();
	}
	
	/**
	 * Returns the trace for the last loaded transformation.
	 * @return Returns the trace for the last loaded transformation.
	 */
	public Trace getTrace() {
		if (currentTransformation == null) {
			throw new IllegalArgumentException(NO_CUR_TRANSFORMATION);
		}
		
		return currentTransformation.getTrace();
	}

	/**
	 * Returns the trace for the transformation with the given runtime id.
	 * @param id The runtime id of the transformation, that should be invoked.
	 * @return Returns the trace for the transformation with the given runtime id.
	 */
	public Trace getTrace(String id) {
		ITransformation t = loadedTransformations.get(id);
		if (t == null) {
			throw new IllegalArgumentException(NO_SUCH_TRANSFORMATION + id);
		}
		return t.getTrace();
	}
	
	/**
	 * Sets the configuration paramter with the given name for the last loaded transformation.
	 * @param key The name of the parameter to be set.
	 * @param option The value to which the parameter should be set.
	 */
	public void setConfigurationParameter(String key, String option) {
		if (currentTransformation == null) {
			throw new IllegalArgumentException(NO_CUR_TRANSFORMATION);
		}
		
		currentTransformation.setConfigurationParameter(key, option);
	}
	/**
	 * Sets the configuration paramter with the given name for the transformation with the given runtime id.
	 * @param id The runtime id of the transformation the parameter should be set for.
	 * @param key The name of the parameter to be set.
	 * @param option The value to which the parameter should be set.
	 */	
	public void setConfigurationParameter(String id, String key, String option) {
		ITransformation t = loadedTransformations.get(id);
		if (t == null) {
			throw new IllegalArgumentException(NO_SUCH_TRANSFORMATION + id);
		}
	
		t.setConfigurationParameter(key, option);
	}
	

	/**
	 * Returns the name of the in model.
	 * @return Returns the name of the in model.
	 */
	public String getModel_inName() {
		return model_inName;
	}

	/**
	 * Sets the name of the in model.
	 * @param model_inName The model in name to set.
	 */
	public void setModel_inName(String model_inName) {
		if (model_inName == null) {
			throw new NullPointerException();
		}
		this.model_inName = model_inName;
	}

	/**
	 * Returns the name of the out model.
	 * @return Returns the name of the out model.
	 */
	public String getModel_outName() {
		return model_outName;
	}

	/**
	 * Sets the name of the out model.
	 * @param model_outName The model out name to set.
	 */
	public void setModel_outName(String model_outName) {
		if (model_outName == null) {
			throw new NullPointerException();
		}
		this.model_outName = model_outName;
	}

	/**
	 * Sets the type of the in model.
	 * @param type The type to set for the in model.
	 */
	public void setModel_inType(String type) {
		this.model_inType = type;
		
	}

	/**
	 * Returns the type of the in model.
	 * @return Returns the type of the in model.
	 */
	public String getModel_inType() {
		return model_inType;
	}

	/**
	 * Returns all transformations, that are available for the currently setted model in type.
	 * @return Returns all transformations, that are available for the currently setted model in type.
	 */
	public Set<TransformationInfo> getAvailableTransformations() {
		if (model_inType == null) throw new NullPointerException("A type for the in model must be set before " +
				"asking for available transformations.");
 		Set<TransformationInfo> transformations = new HashSet<TransformationInfo>();
		for (String id : allTransformations.keySet()) {
			TransformationInfo tinfo = allTransformations.get(id);
			
			if (tinfo.getIn_model().equals(model_inType)) {
				transformations.add(tinfo);
			}
		}
		return transformations;
	}

	/**
	 * Returns all required parameters for the currently loaded transformation.
	 * @return Returns all required parameters for the currently loaded transformation.
	 *
	 */
	public Collection<TParameter> getRequiredParameters() {
		if (currentTransformation == null) {
			throw new IllegalArgumentException(NO_CUR_TRANSFORMATION);
		}
		return currentTransformation.getRequiredParameters();
	}

	/**
	 * Returns all required parameters for the transformation with the given runtime id.
	 * @param the runtime id of the transformation the parameter should be set for.
	 * @return Returns all required parameters for the with the given runtime id.
	 *
	 */
	public Collection<TParameter> getRequiredParameters(String id) {
		ITransformation t = loadedTransformations.get(id);
		if (t == null) {
			throw new IllegalArgumentException(NO_SUCH_TRANSFORMATION + id);
		}
		return t.getRequiredParameters();
	}
	
	/**
	 * Returns the name of the currently loaded transformation.
	 * @return Returns the name of the currently loaded transformation.
	 */
	public String getCurrentTransformation() {
		if (currentTransformation == null) {
			throw new NullPointerException(NO_CUR_TRANSFORMATION);
		}
		return currentTransformation.getClass().getSimpleName();
	}

	/**
	 * Cleans the Engina from all loaded transformations.
	 *
	 */
	public void cleanUp() {
		currentTransformation = null;
		loadedTransformations = new HashMap<String, ITransformation>();
	}

	/**
	 * Returns the result for the last loaded transformation.
	 * @return Returns the result for the last loaded transformation.
	 */
	public Object getResult() {
		if (currentTransformation == null) {
			throw new IllegalArgumentException(NO_CUR_TRANSFORMATION);
		}
	
		return currentTransformation.getResult();
	}
	
	/**
	 * Returns the result for the transformation with the given runtime id.
	 * @param id The runtime id of the transformation the result should be returned for.
	 * @return Returns the result for the transformation with the given runtime id.
 	 */
	public Object getResult(String id) {
		ITransformation t = loadedTransformations.get(id);
		if (t == null) {
			throw new IllegalArgumentException(NO_SUCH_TRANSFORMATION + id);
		}
	
		return t.getResult();
			
	}

	/**
	 * Returns the path to the resources directory of the TransformationEngine.
	 * @return Returns the path to the resources directory of the Transformation Engine.
	 * 
	 */
	public String getResourcePath() {
		return resourcePath;
	}

	/**
	 * Sets resource directory of the TransformationEngine. 
	 * @param resourcePath the path the resource path should be set for
	 */ 
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
}
