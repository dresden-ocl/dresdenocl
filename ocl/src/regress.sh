
java \
  -classpath .:xerces.jar:junit.jar \
  -Dtudresden.ocl.check.types.xmifacade.log=yes \
  junit.ui.TestRunner tudresden.ocl.test.TestAll > regress.results

echo --- diffs in regress.results
diff -c regress.results regress.results.reference

