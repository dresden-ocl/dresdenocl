package tudresden.ocl20.pivot.modelbus.test;

import java.io.IOException;
import java.util.Arrays;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import tudresden.ocl20.pivot.datatypes.impl.DatatypesPackageImpl;
import tudresden.ocl20.pivot.essentialocl.types.CollectionType;
import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.essentialocl.types.impl.TypesPackageImpl;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl;

/**
 * Scrapboard class to do some random testing. Should be replaced by proper unit testing asap.
 * 
 * @author Matthias Braeuer
 * @version 1.0 23.03.2007
 */
public class ModelLoadTest {

  /**
   * @param args
   */
  public static void main(String[] args) {

    ModelLoadTest test = new ModelLoadTest();
    test.run();

  }

  public void run() {

    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*",
        new XMIResourceFactoryImpl());

    EPackage.Registry.INSTANCE.put("http://www.tu-dresden.de/ocl20/pivot/2007/datatypes",
        DatatypesPackageImpl.eINSTANCE);
    EPackage.Registry.INSTANCE.put("http://www.tu-dresden.de/ocl20/pivot/2007/pivotmodel",
        PivotModelPackageImpl.eINSTANCE);
    EPackage.Registry.INSTANCE.put("http://www.tu-dresden.de/ocl20/pivot/2007/essentialocl/types",
        TypesPackageImpl.eINSTANCE);

    ResourceSet resourceSet = new ResourceSetImpl();

    Resource resource = resourceSet.createResource(URI
        .createFileURI("resources/oclstandardlibrary.types"));

    try {
      resource.load(null);
    }
    catch (IOException e) {
      System.out.println("Problem: " + e.getMessage());
    }

    OclLibrary oclLibrary = (OclLibrary) resource.getContents().get(0);
    CollectionType booleanCollection = oclLibrary.getCollectionType(oclLibrary.getOclBoolean());

    // find product operation
    Operation product = null;

    for (Operation o : booleanCollection.getOwnedOperation()) {
      if (o.getName().equals("product")) {
        product = o;
        break;
      }
    }

    product = (Operation) product.bindTypeParameter(product.getOwnedTypeParameter(),Arrays
        .asList(oclLibrary.getOclString()));

  }
}
