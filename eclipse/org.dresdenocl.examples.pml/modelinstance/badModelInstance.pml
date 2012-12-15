<?xml version="1.0" encoding="UTF-8"?>
<pml:PluginPackage xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:pml="http://www.tu-dresden.de/ocl20/pivot/examples/pml">
  <types fullyQualifiedName="java.net.URI"/>
  <types fullyQualifiedName="tudresden.ocl20.pivot.IModel"/>
  <types fullyQualifiedName="tudresden.ocl20.pivot.IModelProvider">
    <operations name="getModel" returnType="//@types.1" myType="//@types.2">
      <parameters name="uri" type="//@types.0"/>
    </operations>
  </types>
  <types fullyQualifiedName="tudresden.ocl20.pivot.BadModelProvider" implements="//@types.1"/>
  <types fullyQualifiedName="tudresden.ocl20.pivot.BadModel"/>
  <features id="tudresden.ocl20.pivot" name="Dresden OCL2 for Eclipse" version="1.5">
    <plugins id="" name="Modelbus" version="2.0" provider="TU Dresden, Software Technology Group">
      <extensionPoints id="" interface="//@types.2"/>
    </plugins>
    <plugins id="tudresden.ocl20.pivot.metamodels.uml2" name="UML2 Meta-Model" version="1.7" provider="TU Dresden, Software Technology Group">
      <extensions implementation="//@types.4" extensionPoint="//@features.0/@plugins.0/@extensionPoints.0"/>
    </plugins>
  </features>
  <features name="Empty Feature"/>
</pml:PluginPackage>
