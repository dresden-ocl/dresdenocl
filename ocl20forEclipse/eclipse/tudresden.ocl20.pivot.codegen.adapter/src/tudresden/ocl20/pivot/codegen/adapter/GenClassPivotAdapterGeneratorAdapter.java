package tudresden.ocl20.pivot.codegen.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.jet.JETEmitter;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.ecore.EAnnotation;

/**
 * Code Generation for all GenClass elements.
 * 
 * @author Michael Thiele
 * 
 */
public class GenClassPivotAdapterGeneratorAdapter extends
		GenBaseGeneratorAdapter {

	public static final String PIVOTADAPTER_PROJECT_TYPE = "tudresden.ocl20.pivot.codegen.generator.PivotAdapter";

	@SuppressWarnings("serial")
	protected static final Map<String, Integer> jetEmitterDescriptorIds = new HashMap<String, Integer>() {
		{
			put("Type", 0);
			put("PrimitiveType", 1);
			put("Property", 2);
			put("Parameter", 3);
			put("Operation", 4);
			put("Namespace", 5);
			put("EnumerationLiteral", 6);
			put("Enumeration", 7);
		}
	};

	protected static final JETEmitterDescriptor[] JET_EMITTER_DESCRIPTORS = {
			new JETEmitterDescriptor("model/TypeAdapter.javajet",
					"tudresden.ocl20.pivot.codegen.adapter.templates.model.TypeAdapter"),
			new JETEmitterDescriptor("model/PrimitiveTypeAdapter.javajet",
					"tudresden.ocl20.pivot.codegen.adapter.templates.model.PrimitiveTypeAdapter"),
			new JETEmitterDescriptor("model/PropertyAdapter.javajet",
					"tudresden.ocl20.pivot.codegen.adapter.templates.model.Property"),
			new JETEmitterDescriptor("model/ParameterAdapter.javajet",
					"tudresden.ocl20.pivot.codegen.adapter.templates.model.Parameter"),
			new JETEmitterDescriptor("model/OperationAdapter.javajet",
					"tudresden.ocl20.pivot.codegen.adapter.templates.model.Operation"),
			new JETEmitterDescriptor("model/NamespaceAdapter.javajet",
					"tudresden.ocl20.pivot.codegen.adapter.templates.model.Namespace"),
			new JETEmitterDescriptor("model/EnumerationLiteralAdapter.javajet",
					"tudresden.ocl20.pivot.codegen.adapter.templates.model.EnumerationLiteral"),
			new JETEmitterDescriptor("model/EnumerationAdapter.javajet",
					"tudresden.ocl20.pivot.codegen.adapter.templates.model.Enumeration") };

	protected JETEmitterDescriptor[] getJETEmitterDescriptors() {
		return JET_EMITTER_DESCRIPTORS;
	}

	public GenClassPivotAdapterGeneratorAdapter() {
		super();
	}

	public GenClassPivotAdapterGeneratorAdapter(
			GeneratorAdapterFactory generatorAdapterFactory) {
		super(generatorAdapterFactory);
	}

	@Override
	public boolean canGenerate(Object object, Object projectType) {
		return PIVOTADAPTER_PROJECT_TYPE.equals(projectType);
	}

	@Override
	public Diagnostic doGenerate(Object object, Object projectType,
			Monitor monitor) {
		if (PIVOTADAPTER_PROJECT_TYPE.equals(projectType))
			return generateModel(object, monitor);
		else
			throw new IllegalArgumentException("Invalid projectType: "
					+ projectType.toString());
	}

	/**
	 * Generates an adapter java class for a DSL data type that has to be mapped.
	 * 
	 * @param mappedType
	 *          the DSL type that has to be mapped to the Pivot Model
	 * @param genModel
	 *          the genModel of the DSL model
	 * @param monitor
	 *          a monitor for progress status
	 * @return
	 */
	protected Diagnostic generateAdapter(GenClass genClass, GenModel genModel,
			String mappedType, Monitor monitor) {

		// info that a new adapter is generated
		monitor.beginTask("", 2);
		message = PivotAdapterGeneratorPlugin.INSTANCE.getString("Generating"
				+ mappedType + "Adapter.message",
				new Object[] { PivotAdapterGeneratorUtil.getAdapterClassName(genModel,
						genClass.getName()) });
		monitor.subTask(message);

		// put generated code into the src folder of the new project
		String projectName = PivotAdapterGeneratorUtil.getProjectName(genModel);
		String targetPath = projectName + "/src";

		// generate the actual java class
		generateJava(targetPath, PivotAdapterGeneratorUtil
				.getAdapterPackage(genModel), PivotAdapterGeneratorUtil
				.getAdapterClassName(genModel, genClass), getJETEmitter(
				getJETEmitterDescriptors(), jetEmitterDescriptorIds.get(mappedType)),
				null, createMonitor(monitor, 1));

		return Diagnostic.OK_INSTANCE;
	}

	protected String getPivotModelAnnotation(GenClass genClass) {

		// find out the pivot model type this class should be mapped to
		for (EAnnotation annotation : genClass.getEcoreClass().getEAnnotations()) {
			if (annotation.getSource().equalsIgnoreCase(
					"http://www.tu-dresden.de/ocl20/pivot/2007/pivotmodel")) {
				return PivotAdapterGeneratorUtil.getAnnotationDetails(annotation);
			}
		}
		return null;
	}

	@Override
	protected Diagnostic generateModel(Object object, Monitor monitor) {

		GenClass genClass = (GenClass) object;
		GenModel genModel = genClass.getGenModel();

		String mappedType = getPivotModelAnnotation(genClass);

		// no or wrong annotation
		if (mappedType == null)
			return new BasicDiagnostic(GenClassPivotAdapterGeneratorAdapter.class
					.getName(), Diagnostic.WARNING,
					"There are no elements annotated with the PivotModel annotation",
					null);

		if (mappedType.equals("Type") || mappedType.equals("PrimitiveType")
				|| mappedType.equals("Property") || mappedType.equals("Parameter")
				|| mappedType.equals("Operation") || mappedType.equals("Namespace")
				|| mappedType.equals("EnumerationLiteral")
				|| mappedType.equals("Enumeration"))
			return generateAdapter(genClass, genModel, mappedType, monitor);

		// no Pivot Model type found
		return new BasicDiagnostic(GenClassPivotAdapterGeneratorAdapter.class
				.getName(), Diagnostic.WARNING,
				"No annotated Pivot Model elements were found.", null);
	}

	@Override
	protected void addBaseTemplatePathEntries(List<String> templatePath) {
		templatePath.add(PivotAdapterGeneratorUtil.TEMPLATE_LOCATION);
		super.addBaseTemplatePathEntries(templatePath);
	}

	@Override
	protected void addClasspathEntries(JETEmitter jetEmitter) throws JETException {
		super.addClasspathEntries(jetEmitter);
		jetEmitter.addVariable(PivotAdapterGeneratorUtil.CLASSPATH_VARIABLE_NAME,
				PivotAdapterGeneratorPlugin.ID);
	}
}
