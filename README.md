[![Build Status](https://travis-ci.org/dresden-ocl/dresdenocl.svg?branch=master)](https://travis-ci.org/dresden-ocl/dresdenocl) [![Coverage Status](https://coveralls.io/repos/dresden-ocl/dresdenocl/badge.svg)](https://coveralls.io/r/dresden-ocl/dresdenocl) [![Join the chat at https://gitter.im/dresden-ocl/dresdenocl](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/dresden-ocl/dresdenocl?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

More information is also available at the [Toolkit Website](http://dresden-ocl.org/).

#Build and run the source code

We develop with Eclipse 4.4.1 and Java 6. However, TravisCI tests Dresden OCL althought against Java 7.

__Prerequisites__:

1. Import luna.target from the */target/luna* folder
2. Set luna.target as Target Platform
	* Window -> Preferences -> Plug-in Development -> Target Platform

__Installation__:

1. Add repository: https://github.com/dresden-ocl/dresdenocl.git
2. Check out the following projects from the folders
	* plugins/**
	* tests/**
3. Build the project (otherwise it will complain about Ocl22Parser not found)
	* *mvn verify* at the root of Dresden OCL
4. In order to run the project now right-click any Dresden OCL Plug-in and select Run as -> Eclipse Plug-in 

#Code Contribution
If you plan to submit any code to the Dresden OCL repository, please use the JDT code
formatter available at http://www.dresden-ocl.org/update/stuff/formatter.xml.

**Please feel free to contribute. Just fork Dresden OCL on GitHub and make a pull request!**
