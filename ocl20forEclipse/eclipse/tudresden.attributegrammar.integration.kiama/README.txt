This project allows to define attribution rules for EObjects. It is shipped
with Dresden OCL, but can be also used in other scenarios.

It basically provides means to dynamically add a trait called "Attributable" to
an instance of EObject. With the means of Scala's implicit conversions, the process
is completely hidden to the user. One solely has to provide the following line:
"import tudresden.attributegrammar.integration.kiama.AttributableEObject._"

Then, attribution rules can be specified as given on the Kiama homepage
(http://code.google.com/p/kiama/). Just be aware that there are minor restrictions
that have to be obeyed in order to use the library. The convenience function "->"
in org.kiama CANNOT be used as it circumvents the proxy mechanism that is used for
the integration. Furthermore, the adaption relies on EObject instance types being
named like their interface, e.g., PersonImpl with the interface Person. Those names 
can be customised; the proxy might need some ADAPTION as well in order to find the
right interface for an EObject instance.


BUILD
-----
The default builder for the Scala sources is an ANT script (build.xml) which uses
the scala.libary plug-in. The scripts can be executed manually or by forcing a
"Clean..." on the projects. Make sure that the script is executed in the same
JRE as the workspace.

If you want to change the Scala sources, it is highly recommended that you use the 
Scala IDE (http://www.scala-ide.org/) for Scala 2.9.x.
After the installation you have to manually change the builder of the projects
- tudresden.attributegrammar.integration.kiama
- tudresden.ocl20.pivot.language.ocl.staticsemantics
to the Scala IDE builder. Right-click on the project and go to the properties panel.
There, go to Builders, de-select the ANT script and select the Scala IDE builder.
Just to be safe, clean both projects.