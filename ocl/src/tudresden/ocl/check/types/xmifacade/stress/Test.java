/*
Copyright (C) 2000  Ralf Wiebicke

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package tudresden.ocl.check.types.xmifacade.stress;

import tudresden.ocl.check.types.*;
import tudresden.ocl.check.OclTypeException;
import tudresden.ocl.check.types.xmifacade.*;
import tudresden.ocl.test.*;

import java.io.*;
import junit.framework.*;

public class Test extends TestCase
{
  public Test()
  {
    this("");
  }

  public Test(String s)
  {
    super(s);
  }

  private Model m;

  private void assertQualified(Any classifier, String name, Type type)
  {
    assertQualified(classifier, name, null, type);
  }

  private void assertQualified(Any classifier, String name, Type[] qualifiers, Type type)
  {
    assertQualified(classifier, name, qualifiers, type, null);
  }

  private void assertQualified(Any classifier, String name, Type[] qualifiers, Type type, String exceptionMessage)
  {
    Type restype=null;
    try
    {
      restype=classifier.navigateQualified(name, qualifiers);
      assertNull(exceptionMessage);
    }
    catch(OclTypeException e)
    {
      assertEquals(exceptionMessage, e.getMessage());
    };

    if(type==null&&restype!=null || type!=null&&!type.equals(restype))
    {
      fail(
        "For classifier\""+classifier+
        "\" feature \""+Basic.qualifierString(name, qualifiers)+
        "\": expected type \""+((type!=null)?Basic.typeString(type):"NULL")+
        "\", found \""+((restype!=null)?Basic.typeString(restype):"NULL")+"\".");
    }
  }

  private void assertParameterized(Any classifier, String name, Type[] params, Type type)
  {
    assertParameterized(classifier, name, params, type, null);
  }

  private void assertParameterized(Any classifier, String name, Type[] params, Type type, String exceptionMessage)
  {
    Type restype=null;
    try
    {
      restype=classifier.navigateParameterized(name, params);
    }
    catch(OclTypeException e)
    {
      assertEquals(exceptionMessage, e.getMessage());
    };

    if(type==null&&restype!=null || type!=null&&!type.equals(restype))
    {
      fail(
        "For classifier\""+classifier+
        "\" feature \""+Basic.signatureString(name,params)+
        "\": expected type \""+((type!=null)?Basic.typeString(type):"NULL")+
        "\", found \""+((restype!=null)?Basic.typeString(restype):"NULL")+"\".");
    }
  }

  private void assertTrue(String a1name, Any a2)
  {
    Any a1=null;
    try
    {
      a1=m.getClassifier(a1name);
    }
    catch(OclTypeException e) {System.out.println(e.getMessage());};

    if(a1!=a2)
    {
      fail(
        "classifier \""+a1+
        "\" found, but \""+a2+
        "\", expected.");
    }
  }

  public void doTest() throws Exception
  {
    String filename="xmistress.xmi";
    String xmiurl=getClass().getResource(filename).getFile();
    m=tudresden.ocl.check.types.xmifacade.XmiParser.getModelCatch(
      xmiurl,
      getClass().toString()+".getResource("+filename+')'
    );
    assertNotNull(m);
    m.printData(new PrintStream(new FileOutputStream(filename+".debug.bak")));
    Diff.diff(new DiffSource(getClass().getResource(filename+".debug")), new DiffSource(new File(filename+".debug.bak")));

    Any alpha=m.getClassifier("Alpha");
    Any beta =m.getClassifier("Beta");
    Any gamma=m.getClassifier("Gamma");
    Any eins =m.getClassifier("Eins");
    Any zwei =m.getClassifier("Zwei");
    Any drei =m.getClassifier("Drei");

    Type alphaSet=new Collection(Collection.SET, alpha);
    Type betaSet =new Collection(Collection.SET, beta);
    Type gammaSet=new Collection(Collection.SET, gamma);
    Type einsSet =new Collection(Collection.SET, eins);
    Type zweiSet =new Collection(Collection.SET, zwei);
    Type dreiSet =new Collection(Collection.SET, drei);

    Type[] paramsEmpty=new Type[0];
    Type[] paramsString=new Type[] { Basic.STRING };
    Type[] paramsInteger=new Type[] { Basic.INTEGER };
    Type[] paramsReal=new Type[] { Basic.REAL };

    Type[] paramsAlpha=new Type[] { alpha };
    Type[] paramsBeta =new Type[] { beta };
    Type[] paramsGamma=new Type[] { gamma };

    assertQualified(alpha, "alpha1", Basic.INTEGER);
    assertQualified(alpha, "alpha2", Basic.REAL);
    assertQualified(alpha, "alpha3", Basic.INTEGER);
    assertQualified(alpha, "eins",   eins);
    assertParameterized(alpha, "alpha1", paramsEmpty, eins);
    assertParameterized(alpha, "alpha1", paramsString, zwei);
    assertParameterized(alpha, "alpha1", paramsInteger, drei);

    // inherited from alpha
    assertQualified(beta, "alpha3", Basic.INTEGER);
    assertQualified(beta, "eins", eins);

    // betas own
    assertQualified(beta, "alpha1", Basic.INTEGER);
    assertQualified(beta, "beta1", Basic.INTEGER);
    assertQualified(beta, "alpha2", Basic.INTEGER);
    assertQualified(beta, "ZWEI", zweiSet);
    assertParameterized(beta, "alpha1", paramsEmpty, eins);
    assertParameterized(beta, "beta1", paramsEmpty, eins);
    assertParameterized(beta, "alpha1", paramsString, zwei);
    assertQualified(beta, "beta", null, null, "Expected attribute \"beta\" in classifier \"packageAleph::Beta\" cannot be used in OCL due to ambiguity. See OCL spec 5.4.1.");
    assertQualified(beta, "gamma", gamma);

    //inherited from alpha
    assertQualified(gamma, "alpha3", Basic.INTEGER);
    assertQualified(gamma, "eins", eins);
    assertParameterized(gamma, "alpha1", paramsInteger, drei);

    //inherited from beta
    assertQualified(gamma, "alpha1", Basic.INTEGER);
    assertQualified(gamma, "beta1",  Basic.INTEGER);
    assertQualified(gamma, "alpha2", Basic.INTEGER);
    assertQualified(gamma, "ZWEI",   zweiSet);
    assertParameterized(gamma, "alpha1", paramsEmpty, eins);
    assertParameterized(gamma, "beta1",  paramsEmpty, eins);
    assertParameterized(gamma, "alpha1", paramsString, zwei);

    //gammas own
    // ambiguity beetween attribute and automatic rolename
    assertQualified(gamma, "drei", null, null, "Expected attribute \"drei\" in classifier \"packageAleph::Gamma\" cannot be used in OCL due to ambiguity. See OCL spec 5.4.1.");
    assertQualified(gamma, "beta", beta); // feature overrides inherited ambiguious feature

    assertQualified(eins, "alpha", alphaSet);
    assertParameterized(eins, "hoppel", paramsBeta, beta);

    //inherited from eins
    assertQualified(zwei, "alpha", alphaSet);
    // matching more than one operation, thus not available for OCL
    assertParameterized(zwei, "hoppel", paramsBeta,  null, "Expected operation \"hoppel(Beta)\" in classifier \"packageAleph::Zwei\" not found.");

    //zweis own
    assertQualified(zwei, "beta", beta);
    assertParameterized(zwei, "hoppel", paramsAlpha, alpha);
    // matching more than one operation, thus not available for OCL
    assertParameterized(zwei, "hoppel", paramsGamma, null, "Expected operation \"hoppel(Gamma)\" in classifier \"packageAleph::Zwei\" not found.");

    //inherited from eins
    assertQualified(drei, "alpha", alphaSet);
    // matching more than one operation, thus not available for OCL
    assertParameterized(drei, "hoppel", paramsBeta, null, "Expected operation \"hoppel(Beta)\" in classifier \"packageAleph::Drei\" not found.");

    //inherited from zwei
    assertQualified(drei, "beta", beta);
    assertParameterized(drei, "hoppel", paramsAlpha, alpha);
    // matching more than one operation, thus not available for OCL
    assertParameterized(drei, "hoppel", paramsGamma, null, "Expected operation \"hoppel(Gamma)\" in classifier \"packageAleph::Drei\" not found.");

    //dreis own
    assertQualified(drei, "gamma", gamma);
    assertQualified(drei, "GAMMA", gamma); // no ambiguity due to explicit rolename

    Any beleph=m.getClassifier("classBeleph");
    assertTrue("packageAleph::Alpha", alpha);
    assertTrue("packageAleph::Beta",  beta);
    assertTrue("packageAleph::Gamma", gamma);
    assertTrue("packageAleph::Eins",  eins);
    assertTrue("packageAleph::Zwei",  zwei);
    assertTrue("packageAleph::Drei",  drei);
    assertTrue("packageBeleph::classBeleph", beleph);
    assertTrue(" packageAleph :: Alpha ", alpha);
    assertTrue(" packageAleph :: Beta ",  beta);
    assertTrue(" packageAleph :: Gamma ", gamma);
    assertTrue(" packageAleph :: Eins ",  eins);
    assertTrue(" packageAleph :: Zwei ",  zwei);
    assertTrue(" packageAleph :: Drei ",  drei);
    assertTrue(" packageBeleph :: classBeleph ", beleph);

  }

  public static junit.framework.Test suite()
  {
    TestSuite suite=new TestSuite();
    suite.addTest(new Test("doTest"));
    return suite;
  }
}
