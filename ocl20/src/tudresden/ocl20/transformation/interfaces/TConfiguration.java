/*
 * Created on 25.01.2006
 *
 */
package tudresden.ocl20.transformation.interfaces;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The class TConfiguration encapsulates all parameters that
 * are setable for a transformation. Each metamodelbased transformation has a list of parameters. These list 
 * and the selected options for each parameter is encalsulated by the TConfiguration
 * @author Christian Wende
 *
 */
public class TConfiguration {

	private Map<String, TParameter> requiredParameters;
	private Map<String, String> settings;
	
	public TConfiguration(Set<TParameter> paras) {
		super();
		requiredParameters = new HashMap<String, TParameter>();
		settings = new HashMap<String, String>();
		for (TParameter parameter : paras) {
			requiredParameters.put(parameter.getKey(), parameter);
		}
	}
	
	
	public TConfiguration() {
		super();
		requiredParameters = new HashMap<String, TParameter>();
		settings = new HashMap<String, String>();
	}


	public Collection<TParameter> getRequiredParameters() {
		return requiredParameters.values();
	}


	public String get(String key) {
		String value = settings.get(key) ;
		if(value == null) {
			String[] values = requiredParameters.get(key).getOptions();
			if(values.length > 0) {
				return values[0];
			}
			else {
				return "";
			}
		}
		return value;
	}


	public void put(String key, String value) {
		settings.put(key,value);
	}
	
	
	
}
