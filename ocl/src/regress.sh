#!/usr/local/bin/tcsh

rm regress.results

java \
  -classpath .:xml4j.jar:test.jar \
  -Dtudresden.ocl.check.types.xmifacade.log=yes \
  test.ui.TestRunner tudresden.ocl.test.TestAll >> regress.results

echo --- diffs in regress.results
diff regress.results regress.results.reference

