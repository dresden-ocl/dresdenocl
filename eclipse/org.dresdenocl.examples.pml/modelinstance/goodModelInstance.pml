<?xml version="1.0" encoding="UTF-8"?>
<pml:PluginPackage xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:pml="http://www.tu-dresden.de/ocl20/pivot/examples/pml">
  <types fullyQualifiedName="java.net.URI"/>
  <types fullyQualifiedName="org.dresdenocl.IModel"/>
  <types fullyQualifiedName="org.dresdenocl.IModelProvider">
    <operations name="getModel" returnType="//@types.1" myType="//@types.2">
      <parameters name="uri" type="//@types.0"/>
    </operations>
  </types>
  <types fullyQualifiedName="org.dresdenocl.UML2ModelProvider" implements="//@types.2"/>
  <types fullyQualifiedName="org.dresdenocl.UML2Model" implements="//@types.1"/>
  <features id="org.dresdenocl" name="Dresden OCL2 for Eclipse" version="2.0">
    <plugins id="org.dresdenocl.modelbus" name="Modelbus" version="2.0" provider="TU Dresden, Software Technology Group">
      <extensionPoints id="org.dresdenocl.modelbus.metamodels" interface="//@types.2"/>
    </plugins>
    <plugins id="org.dresdenocl.metamodels.uml2" name="UML2 Meta-Model" version="2.0" provider="TU Dresden, Software Technology Group">
      <extensions implementation="//@types.3" extensionPoint="//@features.0/@plugins.0/@extensionPoints.0"/>
    </plugins>
  </features>
</pml:PluginPackage>
