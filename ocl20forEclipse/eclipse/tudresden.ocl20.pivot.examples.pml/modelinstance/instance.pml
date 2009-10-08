<?xml version="1.0" encoding="UTF-8"?>
<pml:PluginPackage xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:pml="http://www.tu-dresden.de/ocl20/pivot/examples/pml">
  <types fullyQualifiedName="MetaModel"/>
  <types fullyQualifiedName="Model"/>
  <types fullyQualifiedName="URI"/>
  <features id="dresdenocl" name="Dresden OCL2 for Eclipse" version="2.0">
    <plugins id="modelbus" name="ModelBus" version="2.0" provider="Software Technology Group">
      <extensionPoints id="metamodels" type="//@types.0"/>
    </plugins>
    <plugins id="metamodels.uml" name="UML Meta-Model" version="2.0" provider="Software Technology Group">
      <services name="loadModel" returnType="//@types.1">
        <parameters name="uri" type="//@types.2"/>
      </services>
    </plugins>
  </features>
</pml:PluginPackage>
