This plug-in is the OSGi version of the kiama library, version 0.9 that 
	depends on Scala 2.7.7.	It has been slightly modified (method 
	setChildConnections was made protected to override it) to	build a 
	technological bridge to Ecore models.

Attention:
----------

If you are using Eclipse 3.5 or lower, you cannot compile this plug-in
	as AttributableEObject overrides the method "eInvoke" in EObject that
	has been introduced in EMF 2.6 and therefore requires Eclipse 3.6. 
	Either upgrade to Eclipse 3.6 or simply remove this method from 
	AttributableEObject.

In order to make major changes to the Scala based plug-ins of
	DresdenOCL, you should consider using the Scala IDE for Eclipse
	(update site: http://www.scala-lang.org/scala-eclipse-plugin).
	If you have the Scala IDE installed, you have to disable the current
	ANT based builder for the Scala plug-ins and activate the Scala IDE
	builder.