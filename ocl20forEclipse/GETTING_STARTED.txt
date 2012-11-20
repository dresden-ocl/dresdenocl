Instructions for Dresden OCL
============================
Dresden OCL is organized as a group of Eclipse Plug-ins.


How to run Dresden OCL
======================
Dresden OCL2 for Eclipse is distributed as source code and binary jar archives. 
How to run the toolkit in both situation is explained below.

a) How to run a source code distribution

Follow the steps listed in the detailed description below. Afterwards you 
can run the plug-ins in a new Eclipse instance.

b) How to run a binary distribution

If you got a binary distribution, copy the jars located in the directory 'eclipse/plugins' into the
'plugins' directory of your eclipse distribution.

For further details look into the tutorials provided in the directory 'doc'.

More information about the Dresden OCL is also available at the Toolkit Website
(http://dresden-ocl.sourceforge.net/).


Detailed steps for running the source code distribution
=======================================================

For this guide, Eclipse 3.7 has been used.

Prerequisites:

I.	SVN Client
	I.I.	We recommend using Subclipse from inside your Eclipse installation.
			Install Subclipse from the Eclipse Marketplace or from their website.
			Install Subclipse plus the available SVNKit (or Select All).

	I.II.	Using Subclipse you should change the SVN Interface Client to SVNKit.
			Therefor go to Window -> Preferences -> Team -> SVN ->
			SVN Interface Client: SVNKit (Pure Java).

II.	AspectJ Development Tools (AJDT)
	II.I.	You can either download AJDT from the Eclipse Marketplace or using
			their website.

III. Eclipse Plug-Ins
			You either use the Eclipse Modeling Tools or install following
			Plug-Ins afterwards:

	III.I.		Help -> Install New Software... -> Indigo -> Modeling ->
					* Ecore Tools SDK
					* EMF - Eclipse Modeling Framework SDK
					* UML2 Extender SDK
					* XSD - XML Schema Definition SDK

	III.II.		Help -> Install New Software... -> Available Software Sites ->
				filter for 'Orbit' and choose the update-site. Then find and install
				the following Plug-Ins:
					* Apache Jakarta Log4J plugin
					* Apache Commons IO

	III.III.	Help -> Install New Software... -> http://www.emftext.org/update ->
					* EMFText


Installation:

1. Add repository: svn+ssh://<<username>>@svn-st.inf.tu-dresden.de/srv/svn/dresdenocl
	for readonly access, use the url http://svn-st.inf.tu-dresden.de/svn/dresdenocl/

2. Check out the following projects from Dresden OCL SVN (see step 3):
	* trunk/ocl20forEclipse/eclipse/** (all)
	* trunk/ocl20forEclipse/experimental/org.dresdenocl.examples.uml
	* trunk/ocl20forEclipse/tests/** (all)

3. Build the project: 
	* tudresden.ocl20.pivot.language.ocl.staticsemantics -> build.xml -> Run As -> 2nd entry of Ant-Build 
		** Tab Targets:
			- select Clean tudresden.ocl20.pivot.language.ocl.staticsemantics 
			- select Compile tudresden.ocl20.pivot.language.ocl.staticsemantics 
		** Tab JRE:
			- Runtime JRE: select "Run in the same JRE as the workspace"
	* Run

4. CodeStyle and more
	* http://st.inf.tu-dresden.de/stwiki/index.php/OCL:Best_Practices
		or just Window -> Preferences -> Java -> Code Style -> Formatter -> Import
		the XML from http://www1.inf.tu-dresden.de/~s6240579/OCL2%20for%20Eclipse.xml

Final.
	* In order to run the project now right-click any Dresden OCL Plug-In and select
	  run as -> Eclipse Plug-In 

Bugs and known issues
=====================
Please be aware, that the Dresden OCL is still under construction and 
contains some bugs and issues already known by the development team. 
Bugs already found are listed on the SourceForge project page of the Dresden OCL
(https://sourceforge.net/projects/dresden-ocl). Please feel free to contribute.

Code Contribution
=================
If you plan to submit any code to the Dresden OCL repository, please use the JDT code
formatter available at http://www.dresden-ocl.org/update/stuff/formatter.xml.
