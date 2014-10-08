[![Build Status](https://travis-ci.org/dresden-ocl/dresdenocl.svg?branch=master)](https://travis-ci.org/dresden-ocl/dresdenocl)

More information is also available at the [Toolkit Website](http://dresden-ocl.org/).

##Build and run the source code
We develop with Eclipse 4.4.1 and Java 6. However, TravisCI tests Dresden OCL althought against Java 7.

__Prerequisites__:

1. Import luna.target from the */target/luna* folder
2. Set luna.target as Target Platform
	* Window -> Preferences -> Plug-in Development -> Target Platform

__Installation__:

1. Add repository: https://github.com/dresden-ocl/dresdenocl.git
2. Check out the following projects from the folders
	* plugins/** (all)
	* tests/** (all)
3. Build the project (otherwise it will complain about Ocl22Parser not found)
	* *mvn verify* at the root of Dresden OCL
4. Final
	* In order to run the project now right-click any Dresden OCL Plug-in and select
	  	Run as -> Eclipse Plug-in 

#Bugs and known issues
Please be aware, that the Dresden OCL is still under construction and 
contains some bugs and issues already known by the development team. 
Bugs already found are listed on the issue tracker. **Please feel free to contribute**.

#Code Contribution
If you plan to submit any code to the Dresden OCL repository, please use the JDT code
formatter available at http://www.dresden-ocl.org/update/stuff/formatter.xml.
Please note the [best practices](http://st.inf.tu-dresden.de/stwiki/index.php/OCL:Best_Practices).

**Just fork Dresden OCL on GitHub and make a pull request!**
