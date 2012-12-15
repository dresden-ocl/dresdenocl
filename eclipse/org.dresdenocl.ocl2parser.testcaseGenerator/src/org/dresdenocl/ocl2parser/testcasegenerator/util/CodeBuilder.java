/*
    Copyright (C) 2008  Nils (s0006383@inf.tu-dresden.de)

    This file is part of the testcase generator for OCL parser of the Dresden OCL2 for Eclipse.

    The testcase generator is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    The testcase generator is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with the testcase generator.  If not, see <http://www.gnu.org/licenses/>.
.
*/
package org.dresdenocl.ocl2parser.testcasegenerator.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaRuntime;
import org.osgi.framework.Bundle;

import org.dresdenocl.ocl2parser.testcasegenerator.Activator;
import de.hunsicker.jalopy.Jalopy;

/**
 * The code builder generates the test plugin with its elements (MANIFEST.MF, etc.) and
 * also the test case files. 
 * @author Nils
 *
 */
public class CodeBuilder implements ICodeBuilder {
	
	// ************* Attributes that are used to generate the testcase and testsuite
	private String testName;
	private String metamodel;
	private String modelFile;
	private List<TestcaseStringElement> testcaseStringElements;
	
	private TestcaseStringElement actualTestcaseStringElement;
	
	private String testsuiteName;
	private List<String> testClassNames;
	// ****************** End of Attributes
	
	private String projectname;
	private IProject project;
	
	/**
	 * This is the package name that is used for generating
	 * the code for testcase and test suites.
	 */
	private String packagename;
	
	/**
	 * While creating the code location we save us the source folder to
	 * add new files.
	 */
	private IFolder sourceContainer;
	
	/**
	 * This is the default folder with the name of the project.
	 */
	private IFolder defaultFolder;
	
	public CodeBuilder(String projectname) throws Exception {
		this.projectname = projectname;
		
		/*
		 * The packagename is set to the projectname. This can be altered
		 * by the test suites and the testcases, but we need a at least one
		 * packagename.
		 */
		this.packagename = projectname;
		try {
			Velocity.init();
		}catch(Exception ex) {
			throw new BuilderException("An error occurred while initializing the velocity engine.", ex);
		}
		
		createCodeLocation();
		createDefaultFolder();
		createComparator();
		createActivatorFile();
		createManifestMF();
		createBuildProperties();
		
		
	}

	/**
	 * The following code has been adopted from
	 * org.dresdenocl.codegen.adapter$GenModelPivotAdapterGeneratorAdapter.ensureProjectExists
	 */
	public void createCodeLocation() {
		List<IClasspathEntry> classpathEntries = new UniqueEList<IClasspathEntry>();

		// Get a project with the projectname. Note: this project must not exist.
		project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectname);

		try {
			// A project description.
			IProjectDescription projectDescription = null;
			
			// We create a new java project.
			IJavaProject javaProject = JavaCore.create(project);

			// If the project already exists we delete it.
			if (project.exists()) {
				project.delete(true, null);
			}
			
			
			
			// Create a new project description with the projectname.
			projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription(projectname);
			
			project.create(projectDescription, new NullProgressMonitor());
			
			// set the nature (Java + PDE) of the project if not already done
			String[] natureIds = projectDescription.getNatureIds();
			if (natureIds == null) {
				natureIds = new String[] { JavaCore.NATURE_ID };
			} else {
				// boolean value to indicate whether the project is java project
				boolean hasJavaNature = false;
				
				// boolean value to indicate whether the project is pde project
				boolean hasPDENature = false;
				
				/* 
				 * Iterate over all nature id and determine whether the project is already a java and pde project.
				 * If so, set the corresponding flag.
				 */
				for (int i = 0; i < natureIds.length; ++i) {
					if (JavaCore.NATURE_ID.equals(natureIds[i])) {
						hasJavaNature = true;
					}
					if ("org.eclipse.pde.PluginNature".equals(natureIds[i])) {
						hasPDENature = true;
					}
				}
				
				// If the project isn't a java project then we set the corresponding nature id.
				if (!hasJavaNature) {
					String[] oldNatureIds = natureIds;
					natureIds = new String[oldNatureIds.length + 1];
					System.arraycopy(oldNatureIds, 0, natureIds, 0, oldNatureIds.length);
					natureIds[oldNatureIds.length] = JavaCore.NATURE_ID;
				}
				
				// If the project isn't a pde project then we set the corresponding nature id.
				if (!hasPDENature) {
					String[] oldNatureIds = natureIds;
					natureIds = new String[oldNatureIds.length + 1];
					System.arraycopy(oldNatureIds, 0, natureIds, 0, oldNatureIds.length);
					natureIds[oldNatureIds.length] = "org.eclipse.pde.PluginNature";
				}
			}
			
			// Here we set the nature id's
			projectDescription.setNatureIds(natureIds);

			// set the builders (Manifest + Schema) of the project 
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

			/*
			 * Here we add a src- and bin-directory to the new project.
			 */
			sourceContainer = project.getFolder("src");
			sourceContainer.create(false, true, new NullProgressMonitor());

			IClasspathEntry sourceClasspathEntry = JavaCore.newSourceEntry(new Path("/" + projectname + "/src"));
			classpathEntries.add(0, sourceClasspathEntry);
			
			

			// Here we add a java runtime environment.
			String jreContainer = JavaRuntime.JRE_CONTAINER;
			String complianceLevel = CodeGenUtil.EclipseUtil.getJavaComplianceLevel(project);
			if ("1.5".equals(complianceLevel)) {
				jreContainer += "/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/J2SE-1.5";
			} else if ("1.6".equals(complianceLevel)) {
				jreContainer += "/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-1.6";
			}
			classpathEntries.add(JavaCore.newContainerEntry(new Path(jreContainer)));

			classpathEntries.add(JavaCore.newContainerEntry(new Path("org.eclipse.pde.core.requiredPlugins")));
			
			javaProject.setOutputLocation(new Path("/" + projectname + "/bin"),	new NullProgressMonitor());
			javaProject.setRawClasspath(classpathEntries
				.toArray(new IClasspathEntry[classpathEntries.size()]), new NullProgressMonitor());
		} catch (CoreException e) {
			e.printStackTrace();
			CodeGenEcorePlugin.INSTANCE.log(e);
		}

	}
	
	/**
	 * Creates the file buildProperties.
	 * @throws Exception thrown if the build.properties file isn't found or an error occurs while creating the file in the new project
	 */
	public void createBuildProperties() throws Exception {
		// Find the 'build.properties' file.				
		URL fileLocatorURL = FileLocator.find(Activator.getDefault().getBundle(), new Path("template/build.properties"), null);
		
		// Transform this url into an url that can be understood by the File class.
		URL fileLocatorFileURL = FileLocator.toFileURL(fileLocatorURL);
		
		// We take the build.properties file.
		File buildPropertiesFile = new File(fileLocatorFileURL.toURI());
		
		// If the file doesn't exist it is an error.
		if (!(buildPropertiesFile.exists())) throw new FileNotFoundException("The template file build.properties was not found in the directory template.");
		
		// Open a stream to the content of the build.properties file.
		InputStream inputStream = new FileInputStream(buildPropertiesFile);
		
		// Get the build.properties file of the project (it must be exist).
		IFile projectBuildProperties = project.getFile("build.properties");
		
		// Create the build.properties file in the project.
		projectBuildProperties.create(inputStream, true, null);
	}

	public void generateTestcase() throws Exception {
		// Create a new hash map to store the values for the template.
		HashMap templateMap = new HashMap();
		
		/*
		 * If the package name exists we take this and make it to the
		 * local package name from which the directory structure will be built.
		 */ 
		if ((packagename != null) && (!(packagename.equals("")))) {
			// We set the package name in the java file.
			templateMap.put("packagename", packagename);
			
		} else {// The testcase has no own packagename. We take the project name as default.
			templateMap.put("packagename", packagename);
		}
		
		/* 
		 * The packagename consists of points between the package parts
		 * but we need a path name. So we replace all points through the slash.
		 */
		String packagePathName = packagename.replace('.', '/');
		
		// Add the test name, the metamodel and the model file name to the template map.
		templateMap.put("testname", testName);
		templateMap.put("metamodel", metamodel);
		templateMap.put("modelfile", modelFile);
		
		// We put a new key into the map that denotes the default package name.
		templateMap.put("defaultpackage", projectname + ".internal");
		
		// We put the informations of the test method into the map.
		templateMap.put("testcaseelementsmap", testcaseStringElements);
		
		/*
		 * First we must create the directory that map to the package structure.
		 * So we create a file instance that refers to the directory structure.
		 */
		File packageDirectory = new File(sourceContainer.getLocation().toFile().getCanonicalPath() + "/" + packagePathName);
		
		// Here we create the directory structure in the file system.
		packageDirectory.mkdirs();
		
		/*
		 * Now we can create the java file in the directory.
		 */
		File newJavaFile = new File(packageDirectory, testName + ".java");
		if (newJavaFile.exists()) {
			newJavaFile.delete();
		}
		
		newJavaFile.createNewFile();
		
		// We build a velocity context with our hashmap.
		VelocityContext ctx = new VelocityContext(templateMap);
		
		// Find the 'template' directory.				
		URL fileLocatorURL = FileLocator.find(Activator.getDefault().getBundle(), new Path("template"), null);
		
		// Transform this url into an url that can be understood by the File class.
		URL fileLocatorFileURL = FileLocator.toFileURL(fileLocatorURL);
		
		// Transform the url object into a file object that represents the 'template' directory.
		File templateDirectory = new File(fileLocatorFileURL.toURI());
		
		// Create a set of properties with only one property that contains the 'template' directory.
		Properties veloProperties = new Properties();
		veloProperties.setProperty("file.resource.loader.path", templateDirectory.getAbsolutePath());
		
		// Initialize the velocity engine with the given properties, meaning with the 'template' directory as place where the templates are found.
		VelocityEngine velo = new VelocityEngine(veloProperties);
		
		// Get the 'NamedTestcase.java' template file.
		Template templ = velo.getTemplate("NamedTestcase.java");
		
		/* 
		 * Create a writer that references the new java file.
		 * 
		 */
		Writer writer = new BufferedWriter(new FileWriter(newJavaFile));
		
		// Here the template will be processed.
		templ.merge(ctx, writer);
		
		// Flush the writer and close it.
		writer.flush();
		writer.close();
		
		//beautifyCode(newJavaFile);
		
		// Here we copy the test model file into the new eclipse plugin.
		//String modelFile = (String) map.get("modelfile");
		createTestdata(modelFile);
		
		
	}

	public void generateTestsuite() throws Exception {
		// Create a map to store all informations that are need to process the template file.
		HashMap templateMap = new HashMap();
		
		// We set the package name (if any exists), the suite name and the class names of the contained classes in the map.
		templateMap.put("packagename", packagename);
		templateMap.put("testsuitename", testsuiteName);
		
		String testclassString = BuildingCodeUtilClass.concatElements(testClassNames);
		templateMap.put("testclassnames", testclassString);
		
		
		String localProjectName = projectname.replace('.', '/');
		
		File testsuitePath = null;
				
		// If no package name exists, we take the project name as package name and set the path.
		if (packagename.equals("")) {
			testsuitePath = new File(sourceContainer.getLocation().toString() + "/" + localProjectName);
			templateMap.put("packagename", projectname);
		} else {
			String packagePath = packagename.replace('.', '/');
			testsuitePath = new File(sourceContainer.getLocation().toString() + "/" + packagePath);
		}
		
		if (!(testsuitePath.exists())) testsuitePath.mkdirs();
		
		File testsuiteFile = new File(testsuitePath.getAbsolutePath() + "/" + testsuiteName + ".java");
		testsuiteFile.createNewFile();
		
		FileWriter fileWriter = new FileWriter(testsuiteFile);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		
		VelocityContext ctx = new VelocityContext(templateMap);
		
		// Find the 'template' directory.				
		URL fileLocatorURL = FileLocator.find(Activator.getDefault().getBundle(), new Path("template"), null);
		
		// Transform this url into an url that can be understood by the File class.
		URL fileLocatorFileURL = FileLocator.toFileURL(fileLocatorURL);
		
		// Transform the url object into a file object that represents the 'template' directory.
		File templateDirectory = new File(fileLocatorFileURL.toURI());
		
		// Create a set of properties with only one property that contains the 'template' directory.
		Properties veloProperties = new Properties();
		veloProperties.setProperty("file.resource.loader.path", templateDirectory.getAbsolutePath());
		
		// Initialize the velocity engine with the given properties, meaning with the 'template' directory as place where the templates are found.
		VelocityEngine velo = new VelocityEngine(veloProperties);
		
		Template tmpl = velo.getTemplate("TemplateSuite.java");
		tmpl.merge(ctx, bufferedWriter);
		
		bufferedWriter.flush();
		bufferedWriter.close();
	}

	/**
	 * Initialize the builder with a valid projectname.
	 * If <i>projectname</i> is null an {@link IllegalArgumentException}
	 * will be thrown.
	 * 
	 * @param projectname the name of the project
	 */
	public void initialize(String projectname) {
		if (projectname == null) throw new IllegalArgumentException("The projectname must not be null");
		this.projectname = projectname; 
		

	}
	
	/**
	 * Create the activator file of the new plugin. An exception
	 * can occur if a problem occur with the file system or if
	 * the velocity engine has a problem For more details see the error
	 * message of the exception.
	 * @throws Exception is thrown while accessing the file system or through the velocity engine
	 */
	private void createActivatorFile() throws Exception {
			// Find the 'template' directory.				
			URL fileLocatorURL = FileLocator.find(Activator.getDefault().getBundle(), new Path("template"), null);
			
			// Transform this url into an url that can be understood by the File class.
			URL fileLocatorFileURL = FileLocator.toFileURL(fileLocatorURL);
			
			// Transform the url object into a file object that represents the 'template' directory.
			File templateDirectory = new File(fileLocatorFileURL.toURI());
			
			// Create a set of properties with only one property that contains the 'template' directory.
			Properties veloProperties = new Properties();
			veloProperties.setProperty("file.resource.loader.path", templateDirectory.getAbsolutePath());
			
			// Initialize the velocity engine with the given properties, meaning with the 'template' directory as place where the templates are found.
			VelocityEngine velo = new VelocityEngine(veloProperties);
				
			// We need the project name as the path name of the activator file.
			String localProjectname = projectname.replace('.', '/');
			
			// Create the path to the activator file.
			File activatorFilePath = new File(sourceContainer.getLocation().toFile().getCanonicalPath() + "/" + localProjectname);
			activatorFilePath.mkdirs();
			
			// Create the activator file.
			File activatorFile = new File(activatorFilePath.getAbsolutePath() + "/Activator.java");
			activatorFile.createNewFile();
			
			// Create two writers for writing the activator file.
			FileWriter activatorFileWriter = new FileWriter(activatorFile);
			BufferedWriter bufWriter = new BufferedWriter(activatorFileWriter, 2048);
			
			// We build a velocity context with the projectpackage name.
			VelocityContext ctx = new VelocityContext();
			ctx.put("projectpackage", projectname);
			
			// Get the template file and merge it with the values of the context.
			Template templ = velo.getTemplate("Activator.java");
			templ.merge(ctx, bufWriter);
			
			// Flush and close the writer.
			bufWriter.flush();
			bufWriter.close();
	}
	
	/**
	 * Creates the manifest file of the new project. An exception can be thrown
	 * if an access to the file system isn't possible  or the velocity engine
	 * report an error. For more information see the
	 * exception message.
	 * @throws Exception can be thrown if a file system error occurs or the velocity report an error
	 */
	private void createManifestMF() throws Exception {
		// Find the 'template' directory.				
		URL fileLocatorURL = FileLocator.find(Activator.getDefault().getBundle(), new Path("template"), null);
		
		// Transform this url into an url that can be understood by the File class.
		URL fileLocatorFileURL = FileLocator.toFileURL(fileLocatorURL);
		
		// Transform the url object into a file object that represents the 'template' directory.
		File templateDirectory = new File(fileLocatorFileURL.toURI());
		
		// Create a set of properties with only one property that contains the 'template' directory.
		Properties veloProperties = new Properties();
		veloProperties.setProperty("file.resource.loader.path", templateDirectory.getAbsolutePath());
		
		// Initialize the velocity engine with the given properties, meaning with the 'template' directory as place where the templates are found.
		VelocityEngine velo = new VelocityEngine(veloProperties);
		
		// First we create the manifest directory in the project folder.
		IFolder metaInfFolder = project.getFolder("META-INF");
		metaInfFolder.create(true, true, null);
		
		// We create the manifest.mf file.
		IFile projectManifestFile = metaInfFolder.getFile("MANIFEST.MF");
		projectManifestFile.create(null, true, null);
		
		// Next make the manifest file available for writing content.
		File manifestFile = projectManifestFile.getLocation().toFile(); 
				
		// Two writers for writing the filled template out.
		FileWriter manifestWriter = new FileWriter(manifestFile);
		BufferedWriter bufWriter = new BufferedWriter(manifestWriter);
		
		// Create a velocity context and set the variable in the template.
		VelocityContext ctx = new VelocityContext();
		ctx.put("projectname", projectname);
		ctx.put("activatorpackage", projectname);
		ctx.put("bundlename", projectname);
		
		// Get the template and merge them with the variables of the context.
		Template templ = velo.getTemplate("MANIFEST.MF");
		templ.merge(ctx, bufWriter);
		
		// Flush and close the writer.
		bufWriter.flush();
		bufWriter.close();
	}
	
	/**
	 * Creates the classes for the compare package.
	 * @throws Exception is thrown if an error occurs while creating the files and folders
	 */
	private void createComparator() throws Exception {
		// Create a new 'internal' folder in the new generated plugin.
		IFolder internalFolder = defaultFolder.getFolder("internal");
		internalFolder.create(true, true, null);
		
		// Create a new 'compare' folder in the new generated plugin into the 'internal' folder.
		IFolder destCompareDirectory = internalFolder.getFolder("compare");
		destCompareDirectory.create(true, true, null);
		
		// Create a new 'stringTree' folder under the 'compare' folder.
		IFolder srcStringTreeFolder = destCompareDirectory.getFolder("stringTree");
		srcStringTreeFolder.create(true, true, null);
		
		/* 
		 * Set the velocity context. In the template files of the compartor only one variable must be set
		 * and this is 'packagename'. The package name is the project name plus the postfix '.internal'.
		 */
		VelocityContext ctx = new VelocityContext();
		ctx.put("packagename", projectname + ".internal");
		
		// Locate the 'compare' directory of the testcase generator plugin.				
		URL fileLocatorURL = FileLocator.find(Activator.getDefault().getBundle(), new Path("template/compare"), null);
		
		// Transform the previous url into a url that can be understood by the File class.
		URL fileLocatorFileURL = FileLocator.toFileURL(fileLocatorURL);
		
		// Transform the url into a file object that represents the 'compare' folder.
		File srcCompareDirectory = new File(fileLocatorFileURL.toURI());
		
		
		// Create a property set with only one property that holds the path of the 'compare' folder.
		Properties veloProperties = new Properties();
		veloProperties.setProperty("file.resource.loader.path", srcCompareDirectory.getAbsolutePath());
		
		// Create a velocity engine object with the given search path for the template files.
		VelocityEngine velo = new VelocityEngine(veloProperties);
		
		
		// Make an array of the compare directory.
		File[] contentCompareDirectory = srcCompareDirectory.listFiles();
		
		// Copy the compare directory into the new plugin and merge the templates with the context. 
		for(File sourceFile : contentCompareDirectory) {
			if (sourceFile.isDirectory()) continue;
			
			IFile destinationFile = destCompareDirectory.getFile(sourceFile.getName());
			destinationFile.create(null, true, null);
			
			Writer destWriter = new FileWriter(destinationFile.getLocation().toFile());
			Writer bufWriter = new BufferedWriter(destWriter);
			
			Template templ = velo.getTemplate(sourceFile.getName());
			templ.merge(ctx, bufWriter);
			
			bufWriter.flush();
			bufWriter.close();
		}
		
		// Locate the 'stringTree' directory.
		URL stringTreeDirectoryURL = FileLocator.toFileURL(FileLocator.find(Activator.getDefault().getBundle(), new Path("template/compare/stringTree"), null));
		File srcStringTreeDirectory = new File(stringTreeDirectoryURL.toURI());
		
		//File stringTreeDirectory = new File("template/compare/stringTree");
		File[] contentStringTreeDirectory = srcStringTreeDirectory.listFiles();
		
		// Copy all files of the 'stringTree' directory and merge them with the context.
		for(File sourceFile : contentStringTreeDirectory) {
			if (sourceFile.isDirectory()) continue;
			
			IFile destinationFile = srcStringTreeFolder.getFile(sourceFile.getName());
			destinationFile.create(null, true, null);
			
			Writer destWriter = new FileWriter(destinationFile.getLocation().toFile());
			Writer bufWriter = new BufferedWriter(destWriter);
			
			Template templ = velo.getTemplate("./stringTree/"  + sourceFile.getName());
			templ.merge(ctx, bufWriter);
			
			bufWriter.flush();
			bufWriter.close();
		}
		
	}
	
	/**
	 * Creates the default package (folder) for the project. It is named
	 * by the projectname.
	 * @throws Exception is thrown if an error occurs while creating the new folder
	 */
	private void createDefaultFolder() throws Exception {
		IPath defaultFolderPath = new Path(projectname.replace(".", "/"));
		
		IFolder tempFolder = sourceContainer;
		for(int i = 0; i < defaultFolderPath.segmentCount(); i++) {
			String segment = defaultFolderPath.segment(i);
			
			tempFolder = tempFolder.getFolder(segment);
			tempFolder.create(true, true, null);
			
		}
		defaultFolder = tempFolder;
	}
	
	private void beautifyCode(File toBeautify) throws Exception {
		Jalopy beautifier = new Jalopy();
		
		beautifier.setInput(toBeautify);
		beautifier.setOutput(toBeautify);
		
		beautifier.format();
		
		System.out.println(beautifier.getState());
	}
	
	private void createTestdata(String testFilePath) throws Exception {
		File srcTestFile = new File(testFilePath);
		// The path is relative.
		if (testFilePath.charAt(0) == '.') {
			
			File parentFile = srcTestFile.getParentFile();
			List<String> srcPath = new ArrayList<String>();
			while(parentFile != null) {
				if (!parentFile.getName().equals(".")) {
					srcPath.add(parentFile.getName());
				}
				parentFile = parentFile.getParentFile();
			}
			
			IFolder destElement = null;
			if (srcPath.size() == 0) {
				destElement = project.getFolder(".");
			} else {
				destElement = project.getFolder(srcPath.get(srcPath.size()-1));
				if (!destElement.exists()) destElement.create(true, true, null);
				for(int i = srcPath.size()-1; i >= 1; i--) {
					destElement = destElement.getFolder(srcPath.get(i-1));
					if (!destElement.exists()) destElement.create(true, true, null);
					
				}
			}
			
			
			
			InputStream srcStream = new FileInputStream(srcTestFile);
			
			IFile testFile = destElement.getFile(srcTestFile.getName());
			if (!testFile.exists()) testFile.create(srcStream, true, null);
		} else { // The path doesn't begin with a point, so it can be an absolute path.
			// The path can consists only of a single file name, in that case the path name has no "/".
			if (testFilePath.indexOf("/") == -1) {
				// So we take the root path of the project.
				IFolder destFolder = project.getFolder(".");
				
				InputStream srcStream = new FileInputStream(srcTestFile);
				
				IFile testFile = destFolder.getFile(srcTestFile.getName());
				if (!testFile.exists()) testFile.create(srcStream, true, null);
			}
		}
			
	}
	
	/**
	 * Adds a class name to the code builder.
	 * @param className class name to be added
	 */
	public void addTestClassName(String className) {
		if (testClassNames == null) testClassNames = new ArrayList<String>();
		this.testClassNames.add(className);
	}
	
	public void addCodeSnippet(String codeSnippet) {
		
		initActualTestcaseStringElement();
		actualTestcaseStringElement.addCode(codeSnippet);
	}
	
	public void setTestcaseName(String name) {
		initActualTestcaseStringElement();
		actualTestcaseStringElement.setTestcaseName(name);
	}
	
	public void setOclExpression(String oclExp) {
		initActualTestcaseStringElement();
		actualTestcaseStringElement.setOclExpression(oclExp);
	}
	
	public void addVarDeclSnippet(String varDecl) {
		initActualTestcaseStringElement();
		actualTestcaseStringElement.addVarDeclSnippet(varDecl);
	}
	
	public void setErrorElement(boolean elementValue) {
		initActualTestcaseStringElement();
		actualTestcaseStringElement.setErrorElement(elementValue);
	}
	
	public void newTestMethod() {
		actualTestcaseStringElement = new TestcaseStringElement();
		testcaseStringElements.add(actualTestcaseStringElement);
	}
	
	public void newTestCase() {
		testcaseStringElements =  new ArrayList<TestcaseStringElement>();
		testName = "";
		metamodel = "";
		modelFile = "";
	}
	
	/**
	 * Initializes the code builder for the next test suite to be built.
	 */
	public void newTestSuite() {
		testClassNames = new ArrayList<String>();
	}
	
	/**
	 * Initializes the actual testcase string element if necessary. That means:
	 * if the actualTestcaseStringElement variable is null, then an element will
	 * be created, the actualTestcaseStringElement variable is set to it and
	 * the new element is added to the list of testcase string elements. If
	 * an actual testcase string element exists the method will do nothing.
	 */
	private void initActualTestcaseStringElement() {
		if (actualTestcaseStringElement == null) {
			actualTestcaseStringElement = new TestcaseStringElement();
			testcaseStringElements.add(actualTestcaseStringElement);
		}
	}
	
	
	
	/*
	 * These are the getter- and setter-methods for the attributes that
	 * are used to building the code.
	 */
	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getMetamodel() {
		return metamodel;
	}

	public void setMetamodel(String metamodel) {
		this.metamodel = metamodel;
	}

	public String getModelFile() {
		return modelFile;
	}

	public void setModelFile(String modelFile) {
		this.modelFile = modelFile;
	}

	public String getTestsuiteName() {
		return testsuiteName;
	}

	public void setTestsuiteName(String testsuiteName) {
		this.testsuiteName = testsuiteName;
	}

	public List<String> getTestClassNames() {
		return testClassNames;
	}

	public void setTestClassNames(List<String> testClassNames) {
		this.testClassNames = testClassNames;
	}

	public String getPackagename() {
		return packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}
	
	/**
	 * This class holds all elements that are used to generate
	 * a testcase-method.
	 * @author Nils
	 *
	 */
	public class TestcaseStringElement {
		private String testcaseName;
		private String oclExpression;
		private StringBuffer code;
		private StringBuffer variableDeclaration;
		private boolean errorElement;
		
		public TestcaseStringElement() {
			code = new StringBuffer();
			variableDeclaration = new StringBuffer();
		}
		
		public String getTestcaseName() {
			return testcaseName;
		}
		public void setTestcaseName(String testcaseName) {
			this.testcaseName = testcaseName;
		}
		public String getOclExpression() {
			return oclExpression;
		}
		public void setOclExpression(String oclExpression) {
			this.oclExpression = oclExpression;
		}
		
		public String getCode() {
			return code.toString();
		}
		
		public void addCode(String codeSnippet) {
			code.append(codeSnippet);
		}
		
		public String getVariableDeclaration() {
			return variableDeclaration.toString();
		}
		
		public void addVarDeclSnippet(String variableDeclaration) {
			this.variableDeclaration.append(variableDeclaration);
		}
		
		public boolean containsErrorElement() {
			return errorElement;
		}
		
		public void setErrorElement(boolean element) {
			errorElement = element;
		}
	}

	

}
