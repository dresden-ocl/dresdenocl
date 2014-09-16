BUILD
-----
The default builder for the Scala sources is an ANT script (build.xml) which uses
the scala.libary plug-in. The scripts can be executed manually or by forcing a
"Clean..." on the projects. Make sure that the script is executed in the same
JRE as the workspace.

If you want to change the Scala sources, it is highly recommended that you use the 
Scala IDE (http://www.scala-ide.org/) for Scala 2.9.x.
After the installation you have to manually change the builder of the projects
- org.dresdenocl.attributegrammar.integration.kiama
- org.dresdenocl.language.ocl.staticsemantics
to the Scala IDE builder. Right-click on the project and go to the properties panel.
There, go to Builders, de-select the ANT script and select the Scala IDE builder.
Just to be safe, clean both projects.