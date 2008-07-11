Directory structure:

./src/spec
Contains all specification files, here the ocl grammar is located.

./src/spec/jastadd
Contains all jastadd aspects and the abstract syntax grammar file.

./src/org.sablecc.sablecc
Contains the extended sablecc parser generator. This version was extended
twice by Nils because of the package names in the types.

./src/tudresden.ocl20.pivot.ocl2parser
Contains the ocl2 parser.

./src/tudresden.ocl20.pivot.ocl2parser/internal
Contains classes that are not visible from the outside.

./src/tudresden.ocl20.pivot.ocl2parser/ocl2Transformer
Contains only one class, namely the transformer class, that transform the
concrete syntax tree into an abstract syntax tree

./src/tudresden.ocl20.pivot.ocl2parser/parser
This is the directory that contains all files to work with
the parser from outside.

./src/tudresden.ocl20.pivot.ocl2parser/parser/exceptions
This directory contains all exceptions that can occur
while parsing a file.

./src/tudresden.ocl20.pivot.ocl2parser/gen
This directory is initially empty. It assimilate all files
that will be generated.



Creation of the parser:

This plugin contains the new ocl 2 parser that
works together with the pivotmodel.
To create the parser, execute the target "build" in
the ant buildfile "build.xml".

Hint:
If an error occurs while executing the "build"
target like "Cannot found ... target", then right click
on the "build.xml" and choose "Run -> Ant Build ...". In
the opening dialog choose the card rider "JRE" and
choose the option "Run in the same JRE as the workspace".
After that try to run the target "build" again.

If an error occurs while executing the parser then
try to build the project with Java1.6 compliance and
rebuild it.