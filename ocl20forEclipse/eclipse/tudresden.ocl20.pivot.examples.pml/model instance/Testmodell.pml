<?xml version="1.0" encoding="UTF-8"?>
<pml:PluginPackage xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:pml="http://www.tu-dresden.de/ocl20/pivot/2007/pml">
  <types fullyQualifiedName="Test"/>
  <types fullyQualifiedName="T2"/>
  <types fullyQualifiedName="T3"/>
  <features id="456" name="F1" version="123">
    <plugins activator="//@types.0">
      <extensionPoints id="123" type="//@types.0"/>
      <services name="S1" returnType="//@types.0"/>
      <services name="S2" returnType="//@types.0"/>
    </plugins>
    <plugins id="567" name="P2" version="1.0" provider="Pro1" activator="//@types.1" value="ValueTwo">
      <extensionPoints id="E2" type="//@types.1"/>
      <services name="S2" returnType="//@types.1"/>
    </plugins>
  </features>
  <features id="3424" name="F2" version="1.0">
    <plugins id="675" name="P3" version="1.0" provider="" activator="//@types.2">
      <extensionPoints id="987" type="//@types.0"/>
      <services name="S2" returnType="//@types.1"/>
    </plugins>
  </features>
</pml:PluginPackage>
