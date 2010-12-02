package tudresden.ocl20.pivot.modelbus.test.modelinstance.types.base;

import java.util.List;

import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceString;
import tudresden.ocl20.pivot.modelinstancetype.types.base.ModelInstanceTuple;
import tudresden.ocl20.pivot.pivotmodel.Type;

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