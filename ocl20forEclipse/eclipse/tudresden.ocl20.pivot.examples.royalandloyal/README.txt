Royal and Loyal 
=============== 

This is the famous "Royal and Loyal" example. It is based on the
model published on page 22 in [1]. The constraints are based on
the constraints from the same book.

The example exists out of the following files:
- model/royals_and_loyals.zargo: The model in ArgoUML [2] format. You need 
               at least version 0.20.alpha1 of ArgoUML. This file can directly
               be used together with the OCL parser GUI and CLI with the
 			   UML1.5 meta model, you do not need to export an XMI file.
- model/royals_and_loyals.xmi: The interchange format of the example can also
			   be used to run this example with the UML1.5 meta model.
- model/royals_and_loyals.uml: This file can be used to run this example
			   with the UML2 meta model.			   
- expressions/*.ocl: Constraints which can be imported as OCL expressions.


References
==========
[1] J. Warmer and A. Kleppke, The Object Constraint Language ---
    Getting Your Models Ready for MDA, 2nd edition. Addison-Wesley,
    2003
[2] ArgoUML: http://argouml.tigris.org/ A recent developer release
    of the upcoming 0.20 version is available at:
    http://argouml.tigris.org/download/release020a4.html
