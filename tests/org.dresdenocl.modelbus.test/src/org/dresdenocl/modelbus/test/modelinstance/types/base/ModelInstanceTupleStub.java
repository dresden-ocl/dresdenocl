package org.dresdenocl.modelbus.test.modelinstance.types.base;

import java.util.List;

import org.dresdenocl.modelinstancetype.types.IModelInstanceElement;
import org.dresdenocl.modelinstancetype.types.IModelInstanceString;
import org.dresdenocl.modelinstancetype.types.base.ModelInstanceTuple;
import org.dresdenocl.pivotmodel.Type;

/**
 * <p>
 * A sub class of {@link ModelInstanceTuple} to provide a public constructor.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ModelInstanceTupleStub extends ModelInstanceTuple {

	/**
	 * <p>
	 * Creates a new {@link ModelInstanceTupleStub}.
	 * </p>
	 * 
	 * @see ModelInstanceTuple
	 */
	protected ModelInstanceTupleStub(List<IModelInstanceString> keys,
			List<IModelInstanceElement> values, Type type) {

		super(keys, values, type);
	}
}