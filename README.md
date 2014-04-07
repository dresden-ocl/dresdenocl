#Instructions for Dresden OCL
Dresden OCL is organized as a group of Eclipse Plug-ins. More information is also available at the [Toolkit Website](http://dresden-ocl.org/).

##Detailed steps for running the source code distribution

For this guide, Eclipse 4.3.2 (Kepler SR2) and Java6 has been used.

__Prerequisites__:

1. Git Client
	* We recommend using EGit from inside your Eclipse installation.
2. AspectJ Development Tools (AJDT)
	* You can either download AJDT from the Eclipse Marketplace or using their website.
3. Eclipse Plug-Ins
	* You either use the Eclipse Modeling Tools or install following Plug-Ins afterwards:
	* Help -> Install New Software... -> Indigo -> Modeling ->
		* Ecore Tools SDK
		* EMF - Eclipse Modeling Framework SDK
		* UML2 Extender SDK
		* XSD - XML Schema Definition SDK
	* Help -> Install New Software... -> http://download.eclipse.org/tools/orbit/downloads/drops/R20140114142710/repository/
		* Then find and install the following Plug-Ins:
			* Apache Jakarta Log4J plugin
			* Apache Commons IO
	* Help -> Install New Software... -> http://www.emftext.org/update
		* EMFText

__Installation__:

1. Add repository: https://github.com/dresden-ocl/dresdenocl.git
2. Check out the following projects from the folders (see step 3)
	* eclipse/** (all)
	* tests/** (all)
3. Build the project
	* org.dresdenocl.language.ocl.staticsemantics -> build.xml -> Run As -> 2nd entry of Ant-Build 
		* Tab Targets:
			- select Clean org.dresdenocl.language.ocl.staticsemantics 
		* Tab JRE:
			- Runtime JRE: select "Run in the same JRE as the workspace"
	* Run
4. Final
	* In order to run the project now right-click any Dresden OCL Plug-In and select
	  run as -> Eclipse Plug-In 

Bugs and known issues
=====================
Please be aware, that the Dresden OCL is still under construction and 
contains some bugs and issues already known by the development team. 
Bugs already found are listed on the issue tracker. **Please feel free to contribute**.

Code Contribution
=================
If you plan to submit any code to the Dresden OCL repository, please use the JDT code
formatter available at http://www.dresden-ocl.org/update/stuff/formatter.xml.
Please note the [best practices](http://st.inf.tu-dresden.de/stwiki/index.php/OCL:Best_Practices).
