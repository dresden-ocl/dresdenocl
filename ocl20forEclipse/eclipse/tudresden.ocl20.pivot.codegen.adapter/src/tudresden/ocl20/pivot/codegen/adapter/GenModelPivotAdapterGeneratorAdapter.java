package tudresden.ocl20.pivot.codegen.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.jet.JETEmitter;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaRuntime;

/**
 * Code generation for GenClass independent GenModel elements such as the
 * plugin.xml, messages, etc.
 * 
 * @author Michael Thiele
 * 
 */
public class GenModelPivotAdapterGeneratorAdapter extends
		GenBaseGeneratorAdapter {

	public static final String PIVOTADAPTER_PROJECT_TYPE = "tudresden.ocl20.pivot.codegen.generator.PivotAdapter";

	protected static final int MANIFEST_MF_ID = 0;
	protected static final int PLUGIN_XML_ID = 1;
	protected static final int PLUGIN_JAVA_ID = 2;
	protected static final int ADAPTER_FACTORY_ID = 3;
	protected static final int MODEL_ID = 4;
	protected static final int MODEL_MESSAGES_ID = 5;
	protected static final int MESSAGES_ID = 6;
	protected static final int PROVIDER_ID = 7;
	protected static final int LOG4JPROPERTIES_ID = 8;

	protected static final JETEmitterDescriptor[] JET_EMITTER_DESCRIPTORS = {
			new JETEmitterDescriptor("manifest.mfjet",
					"tudresden.ocl20.pivot.codegen.adapter.templates.ManifestMF"),
			new JETEmitterDescriptor("plugin.xmljet",
					"tudresden.ocl20.pivot.codegen.adapter.templates.PluginXML"),
			new JETEmitterDescriptor("Plugin.javajet",
					"tudresden.ocl20.pivot.codegen.adapter.templates.PluginJava"),
			new JETEmitterDescriptor("model/AdapterFactory.javajet",
					"tudresden.ocl20.pivot.codegen.adapter.templates.AdapterFactory"),
			new JETEmitterDescriptor("model/Model.javajet",
					"tudresden.ocl20.pivot.codegen.adapter.templates.Model"),
			new JETEmitterDescriptor("model/ModelMessages.javajet",
					"tudresden.ocl20.pivot.codegen.adpater.templates.ModelMessages"),
			new JETEmitterDescriptor("model/Messages.propertiesjet",
					"tudresden.ocl20.pivot.codegen.adapter.templates.Messages"),
			new JETEmitterDescriptor("provider/ModelProvider.javajet",
					"tudresden.ocl20.pivot.codegen.adapter.templates.ModelProvider"),
			new JETEmitterDescriptor("log4j.propertiesjet",
					"tudresden.ocl20.pivot.codegen.adapter.templates.Log4jProperties") };

	protected JETEmitterDescriptor[] getJETEmitterDescriptors() {
		return JET_EMITTER_DESCRIPTORS;
	}

	public GenModelPivotAdapterGeneratorAdapter() {
		super();
	}

	public GenModelPivotAdapterGeneratorAdapter(
			GeneratorAdapterFactory generatorAdapterFactory) {
		super(generatorAdapterFactory);
	}

	/**
	 * Returns the {@link GenModel}'s packages.
	 */
	@Override
	public Collection<?> getGenerateChildren(Object object, Object projectType) {
		return new ArrayList<GenPackage>(((GenModel) object).getGenPackages());
	}

	/**
	 * Returns <code>true</code> for the appropriate project type
	 * (PIVOTADAPTER_PROJECT_TYPE)
	 */
	@Override
	public boolean canGenerate(Object object, Object projectType) {
		return PIVOTADAPTER_PROJECT_TYPE.equals(projectType);
	}

	/**
	 * Is called by <code>org.eclipse.emf.codegen.ecore.generator.Generator</code>
	 * during generation process.
	 */
	@Override
	public Diagnostic doGenerate(Object object, Object projectType,
			Monitor monitor) {
		if (PIVOTADAPTER_PROJECT_TYPE.equals(projectType))
			return generateModel(object, monitor);
		throw new IllegalArgumentException("Invalid projectType: "
				+ projectType.toString());
	}

	/**
	 * The following code has been adopted from
	 * org.eclipse.emf.codegen.ecore.Generator$EclipseHelper.createEMFProject
	 */
	protected void ensureProjectExists(String projectName) {

		List<IClasspathEntry> classpathEntries = new UniqueEList<IClasspathEntry>();

		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(
				projectName);

		try {
			boolean isEmptyProject = true;
			IProjectDescription projectDescription = null;
			IJavaProject javaProject = JavaCore.create(project);

			if (!project.exists()) {
				projectDescription = ResourcesPlugin.getWorkspace()
						.newProjectDescription(projectName);
				project.create(new NullProgressMonitor());
			} else {
				isEmptyProject = false;
				projectDescription = project.getDescription();
				classpathEntries.addAll(Arrays.asList(javaProject.getRawClasspath()));
			}

			// set the nature (Java + PDE) of the project if not already done
			String[] natureIds = projectDescription.getNatureIds();
			if (natureIds == null) {
				natureIds = new String[] { JavaCore.NATURE_ID };
			} else {
				boolean hasJavaNature = false;
				boolean hasPDENature = false;
				for (int i = 0; i < natureIds.length; ++i) {
					if (JavaCore.NATURE_ID.equals(natureIds[i])) {
						hasJavaNature = true;
					}
					if ("org.eclipse.pde.PluginNature".equals(natureIds[i])) {
						hasPDENature = true;
					}
				}
				if (!hasJavaNature) {
					String[] oldNatureIds = natureIds;
					natureIds = new String[oldNatureIds.length + 1];
					System.arraycopy(oldNatureIds, 0, natureIds, 0, oldNatureIds.length);
					natureIds[oldNatureIds.length] = JavaCore.NATURE_ID;
				}
				if (!hasPDENature) {
					String[] oldNatureIds = natureIds;
					natureIds = new String[oldNatureIds.length + 1];
					System.arraycopy(oldNatureIds, 0, natureIds, 0, oldNatureIds.length);
					natureIds[oldNatureIds.length] = "org.eclipse.pde.PluginNature";
				}
			}
			projectDescription.setNatureIds(natureIds);

			// set the builders (Manifest + Schema) of the project if not
			// already done
			ICommand[] builders = projectDescription.getBuildSpec();
			if (builders == null) {
				builders = new ICommand[0];
			}
			boolean hasManifestBuilder = false;
			boolean hasSchemaBuilder = false;
			for (int i = 0; i < builders.length; ++i) {
				if ("org.eclipse.pde.ManifestBuilder".equals(builders[i]
						.getBuilderName())) {
					hasManifestBuilder = true;
				}
				if ("org.eclipse.pde.SchemaBuilder"
						.equals(builders[i].getBuilderName())) {
					hasSchemaBuilder = true;
				}
			}
			if (!hasManifestBuilder) {
				ICommand[] oldBuilders = builders;
				builders = new ICommand[oldBuilders.length + 1];
				System.arraycopy(oldBuilders, 0, builders, 0, oldBuilders.length);
				builders[oldBuilders.length] = projectDescription.newCommand();
				builders[oldBuilders.length]
						.setBuilderName("org.eclipse.pde.ManifestBuilder");
			}
			if (!hasSchemaBuilder) {
				ICommand[] oldBuilders = builders;
				builders = new ICommand[oldBuilders.length + 1];
				System.arraycopy(oldBuilders, 0, builders, 0, oldBuilders.length);
				builders[oldBuilders.length] = projectDescription.newCommand();
				builders[oldBuilders.length]
						.setBuilderName("org.eclipse.pde.SchemaBuilder");
			}
			projectDescription.setBuildSpec(builders);

			project.open(new NullProgressMonitor());
			project.setDescription(projectDescription, new NullProgressMonitor());

			// if the project has just been created, generate...
			if (isEmptyProject) {
				// ... the source folder
				IFolder sourceContainer = project.getFolder("src");
				sourceContainer.create(false, true, new NullProgressMonitor());

				IClasspathEntry sourceClasspathEntry = JavaCore
						.newSourceEntry(new Path("/" + projectName + "/src"));
				classpathEntries.add(0, sourceClasspathEntry);

				// ... the jre
				String jreContainer = JavaRuntime.JRE_CONTAINER;
				String complianceLevel = CodeGenUtil.EclipseUtil
						.getJavaComplianceLevel(project);
				if ("1.5".equals(complianceLevel)) {
					jreContainer += "/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/J2SE-1.5";
				} else if ("1.6".equals(complianceLevel)) {
					jreContainer += "/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-1.6";
				}
				classpathEntries
						.add(JavaCore.newContainerEntry(new Path(jreContainer)));

				classpathEntries.add(JavaCore.newContainerEntry(new Path(
						"org.eclipse.pde.core.requiredPlugins")));

				// ...the output folder
				javaProject.setOutputLocation(new Path("/" + projectName + "/bin"),
						new NullProgressMonitor());
			}
			javaProject.setRawClasspath(classpathEntries
					.toArray(new IClasspathEntry[classpathEntries.size()]),
					new NullProgressMonitor());

		} catch (CoreException e) {
			e.printStackTrace();
			CodeGenEcorePlugin.INSTANCE.log(e);
		}
	}

	protected void generateManifestMF(String projectName, Monitor monitor) {
		monitor.beginTask("", 2);
		message = PivotAdapterGeneratorPlugin.INSTANCE
				.getString("GeneratingManifest.message");
		monitor.subTask(message);

		String targetFile = projectName + "/META-INF/MANIFEST.MF";
		generateText(targetFile, getJETEmitter(getJETEmitterDescriptors(),
				MANIFEST_MF_ID), null, true, getEncoding(URI.createURI(targetFile)),
				createMonitor(monitor, 1));
	}

	protected void generatePluginXML(String projectName, Monitor monitor) {
		monitor.beginTask("", 2);
		message = PivotAdapterGeneratorPlugin.INSTANCE
				.getString("GeneratingPluginXML.message");
		monitor.subTask(message);

		String targetFile = projectName + "/plugin.xml";
		generateText(targetFile, getJETEmitter(getJETEmitterDescriptors(),
				PLUGIN_XML_ID), null, true, getEncoding(URI.createURI(targetFile)),
				createMonitor(monitor, 1));
	}

	protected void generatePluginJava(String projectName, GenModel genModel,
			Monitor monitor) {
		String className = PivotAdapterGeneratorUtil
				.startWithCapitalLetter(genModel.getModelName())
				+ "MetamodelPlugin";
		monitor.beginTask("", 2);
		message = PivotAdapterGeneratorPlugin.INSTANCE.getString(
				"GeneratingPluginJava.message", new Object[] { className });
		monitor.subTask(message);

		String targetPath = projectName + "/src";
		// projectName == packageName
		generateJava(targetPath, projectName, className, getJETEmitter(
				getJETEmitterDescriptors(), PLUGIN_JAVA_ID), null, createMonitor(
				monitor, 1));
	}

	protected void generateAdapterFactory(String projectName, GenModel genModel,
			Monitor monitor) {
		String className = PivotAdapterGeneratorUtil
				.startWithCapitalLetter(genModel.getModelName())
				+ "AdapterFactory";
		monitor.beginTask("", 2);
		message = PivotAdapterGeneratorPlugin.INSTANCE.getString(
				"GeneratingAdapterFactory.message", new Object[] { className });
		monitor.subTask(message);

		String targetPath = projectName + "/src";
		generateJava(targetPath, PivotAdapterGeneratorUtil
				.getAdapterPackage(genModel), className, getJETEmitter(
				getJETEmitterDescriptors(), ADAPTER_FACTORY_ID), null, createMonitor(
				monitor, 1));
	}

	protected void generatePivotModel(String projectName, GenModel genModel,
			Monitor monitor) {
		String className = PivotAdapterGeneratorUtil
				.startWithCapitalLetter(genModel.getModelName())
				+ "Model";
		monitor.beginTask("", 2);
		message = PivotAdapterGeneratorPlugin.INSTANCE.getString(
				"GeneratingModel.message", new Object[] { className });
		monitor.subTask(message);

		String targetPath = projectName + "/src";
		generateJava(targetPath, PivotAdapterGeneratorUtil
				.getAdapterPackage(genModel), className, getJETEmitter(
				getJETEmitterDescriptors(), MODEL_ID), null, createMonitor(monitor, 1));
	}

	protected void generateModelMessages(String projectName, GenModel genModel,
			Monitor monitor) {
		String className = PivotAdapterGeneratorUtil
				.startWithCapitalLetter(genModel.getModelName())
				+ "ModelMessages";
		monitor.beginTask("", 2);
		message = PivotAdapterGeneratorPlugin.INSTANCE.getString(
				"GeneratingModelMessages.message", new Object[] { className });
		monitor.subTask(message);

		String targetPath = projectName + "/src";
		generateJava(targetPath, PivotAdapterGeneratorUtil
				.getAdapterPackage(genModel), className, getJETEmitter(
				getJETEmitterDescriptors(), MODEL_MESSAGES_ID), null, createMonitor(
				monitor, 1));
	}

	protected void generateMessages(String projectName, GenModel genModel,
			Monitor monitor) {
		monitor.beginTask("", 2);
		message = PivotAdapterGeneratorPlugin.INSTANCE
				.getString("GeneratingMessages.message");
		monitor.subTask(message);

		String targetFile = projectName
				+ "/src/"
				+ PivotAdapterGeneratorUtil.getAdapterPackage(genModel).replace('.',
						'/') + "/messages.properties";
		generateText(targetFile, getJETEmitter(getJETEmitterDescriptors(),
				MESSAGES_ID), null, true, getEncoding(URI.createURI(targetFile)),
				createMonitor(monitor, 1));
	}

	protected void generateModelProvider(String projectName, GenModel genModel,
			Monitor monitor) {
		String className = PivotAdapterGeneratorUtil
				.startWithCapitalLetter(genModel.getModelName())
				+ "ModelProvider";
		monitor.beginTask("", 2);
		message = PivotAdapterGeneratorPlugin.INSTANCE.getString(
				"GeneratingModelProvider.message", new Object[] { className });
		monitor.subTask(message);

		String targetPath = projectName + "/src";
		generateJava(targetPath, PivotAdapterGeneratorUtil
				.getProviderPackage(genModel), className, getJETEmitter(
				getJETEmitterDescriptors(), PROVIDER_ID), null, createMonitor(monitor,
				1));
	}

	protected void generateLog4jProperties(String projectName, GenModel genModel,
			Monitor monitor) {
		monitor.beginTask("", 2);
		message = PivotAdapterGeneratorPlugin.INSTANCE
				.getString("GeneratingLog4jProperties.message");
		monitor.subTask(message);

		String targetFile = projectName + "/log4j.properties";
		generateText(targetFile, getJETEmitter(getJETEmitterDescriptors(),
				LOG4JPROPERTIES_ID), null, true,
				getEncoding(URI.createURI(targetFile)), createMonitor(monitor, 1));
	}

	@Override
	protected Diagnostic generateModel(Object object, Monitor monitor) {
		GenModel genModel = (GenModel) object;

		String projectName = PivotAdapterGeneratorUtil.getProjectName(genModel);
		ensureProjectExists(projectName);

		generateManifestMF(projectName, monitor);
		generatePluginXML(projectName, monitor);
		generatePluginJava(projectName, genModel, monitor);
		generateAdapterFactory(projectName, genModel, monitor);
		generatePivotModel(projectName, genModel, monitor);
		generateModelMessages(projectName, genModel, monitor);
		generateMessages(projectName, genModel, monitor);
		generateModelProvider(projectName, genModel, monitor);
		generateLog4jProperties(projectName, genModel, monitor);

		return Diagnostic.OK_INSTANCE;
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
