
java \
  -classpath .:xerces.jar:test.jar \
  -Dtudresden.ocl.check.types.xmifacade.log=yes \
  test.ui.TestRunner tudresden.ocl.test.TestAll > regress.results

echo --- diffs in regress.results
diff -c regress.results regress.results.reference

