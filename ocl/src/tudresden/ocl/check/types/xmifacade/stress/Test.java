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

import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import test.framework.*;

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

  public static void main(String[] args)
  {
    (new Test()).doTest();
  }

  private Model m;
  private int errors=0;

  private void assertQualified(Any classifier, String name, Type type)
  {
    assertQualified(classifier, name, null, type);
  }

  private void assertQualified(Any classifier, String name, Type[] qualifiers, Type type)
  {
    Type restype=null;
    try
    {
      restype=classifier.navigateQualified(name, qualifiers);
    }
    catch(OclTypeException e) {System.out.println(e.getMessage());};

    if(type==null&&restype!=null || type!=null&&!type.equals(restype))
    {
      errors++;
      System.out.println(
        "ERROR: For classifier\""+classifier+
        "\" feature \""+Model.qualifierString(name, qualifiers)+
        "\": expected type \""+((type!=null)?Model.typeString(type):"NULL")+
        "\", found \""+((restype!=null)?Model.typeString(restype):"NULL")+"\".");
      assert(false);
    }
  }

  private void assertParameterized(Any classifier, String name, Type[] params, Type type)
  {
    Type restype=null;
    try
    {
      restype=classifier.navigateParameterized(name, params);
    }
    catch(OclTypeException e) {System.out.println(e.getMessage());};

    if(type==null&&restype!=null || type!=null&&!type.equals(restype))
    {
      errors++;
      System.out.println(
        "ERROR: For classifier\""+classifier+
        "\" feature \""+Model.signatureString(name,params)+
        "\": expected type \""+((type!=null)?Model.typeString(type):"NULL")+
        "\", found \""+((restype!=null)?Model.typeString(restype):"NULL")+"\".");
      assert(false);
    }
  }

  private void assert(String a1name, Any a2)
  {
    Any a1=null;
    try
    {
      a1=m.getClassifier(a1name);
    }
    catch(OclTypeException e) {System.out.println(e.getMessage());};

    if(a1!=a2)
    {
      errors++;
      System.out.println(
        "ERROR: classifier \""+a1+
        "\" found, but \""+a2+
        "\", expected.");
      assert(false);
    }
  }

  public void doTest()
  {
    String xmiurl=(getClass().getResource("xmistress.xmi")).getFile();
    m=tudresden.ocl.check.types.xmifacade.XmiParser.getModelCatch(xmiurl);
    m.printData(System.out);

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
    assertQualified(beta, "beta", null);
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
    assertQualified(gamma, "drei", null); // ambiguity beetween attribute and automatic rolename
    assertQualified(gamma, "beta", beta); // feature overrides inherited ambiguious feature

    assertQualified(eins, "alpha", alphaSet);
    assertParameterized(eins, "hoppel", paramsBeta, beta);

    //inherited from eins
    assertQualified(zwei, "alpha", alphaSet);
    assertParameterized(zwei, "hoppel", paramsBeta,  null); // matching more than one operation, thus not available for OCL

    //zweis own
    assertQualified(zwei, "beta", beta);
    assertParameterized(zwei, "hoppel", paramsAlpha, alpha);
    assertParameterized(zwei, "hoppel", paramsGamma, null); // matching more than one operation, thus not available for OCL

    //inherited from eins
    assertQualified(drei, "alpha", alphaSet);
    assertParameterized(drei, "hoppel", paramsBeta, null); // matching more than one operation, thus not available for OCL

    //inherited from zwei
    assertQualified(drei, "beta", beta);
    assertParameterized(drei, "hoppel", paramsAlpha, alpha);
    assertParameterized(drei, "hoppel", paramsGamma, null); // matching more than one operation, thus not available for OCL

    //dreis own
    assertQualified(drei, "gamma", gamma);
    assertQualified(drei, "GAMMA", gamma); // no ambiguity due to explicit rolename

    Any beleph=m.getClassifier("classBeleph");
    assert("packageAleph::Alpha", alpha);
    assert("packageAleph::Beta",  beta);
    assert("packageAleph::Gamma", gamma);
    assert("packageAleph::Eins",  eins);
    assert("packageAleph::Zwei",  zwei);
    assert("packageAleph::Drei",  drei);
    assert("packageBeleph::classBeleph", beleph);
    assert(" packageAleph :: Alpha ", alpha);
    assert(" packageAleph :: Beta ",  beta);
    assert(" packageAleph :: Gamma ", gamma);
    assert(" packageAleph :: Eins ",  eins);
    assert(" packageAleph :: Zwei ",  zwei);
    assert(" packageAleph :: Drei ",  drei);
    assert(" packageBeleph :: classBeleph ", beleph);

    if(errors>0)
    {
      System.out.println(
        "ATTENTION: tudresden.ocl.check.types.xmifacade.Test detected " +
        String.valueOf(errors) +
        " errors.");
    }
  }

  public static test.framework.Test suite()
  {
    TestSuite suite=new TestSuite();
    suite.addTest(new Test("doTest"));
    return suite;
  }
}
