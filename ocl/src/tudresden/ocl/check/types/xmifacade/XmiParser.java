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

package tudresden.ocl.check.types.xmifacade;

import java.util.*;
import org.w3c.dom.*;
import com.ibm.xml.parsers.DOMParser;

import tudresden.ocl.check.types.Type;
import tudresden.ocl.check.types.Any;
import tudresden.ocl.check.types.Basic;
import tudresden.ocl.check.types.Collection;

public final class XmiParser
{
  /**
     This variable triggers, whether the qualifiers of an association are
     expected on the on the target end of the qualified association path, 
     or on its source end.
     This trigger is evaluated in ModelAssociation.dissolve.
     The author believes, that the default value of false is compliant
     with the UML spec.
     However, the value true must be used for Rose 2000 with the Unisys XMI Plugin.
     @see ModelAssociation#dissolve(Model)
  */
  private boolean qualifiersOnTarget=false;
  
  private String url;

  public static Model createModel(String url, String description) 
    throws org.xml.sax.SAXException, java.io.IOException
  {
    return createModel(url, description, false);
  }

  public static Model createModel(String url, String description, boolean qualifiersOnTarget) 
    throws org.xml.sax.SAXException, java.io.IOException
  {
    return (new XmiParser(url, description, qualifiersOnTarget)).model;
  }

  private static HashMap models=null;

  public static Model getModelCatch(String url, String description)
  {
    try
    {
      return getModel(url, description);
    }
    catch(org.xml.sax.SAXException e) {e.printStackTrace(System.out);}
    catch(java.io.IOException e) {e.printStackTrace(System.out);};
    return null;
  }

  /**
     Does caching, so that there is only one instance for each xmi file.
  */
  public static Model getModel(String url, String description) 
    throws org.xml.sax.SAXException, java.io.IOException
  {
    return getModel(url, description,  false);
  }

  public static Model getModel(String url, String description, boolean qualifiersOnTarget) 
    throws org.xml.sax.SAXException, java.io.IOException
  {
    if(models==null)
      models=new HashMap();

    Model m=(Model)(models.get(url));
    if(m==null)
      models.put(url, m=(new XmiParser(url, description, qualifiersOnTarget)).model);

    return m;
  }

  private Model model;

  private Adapter adapter;

  public String adapt(String x)
  {
    return adapter.adapt(x);
  }

  /**
     Maps for all Class elements the XMI.id attribute to a XmiClass instance.
     Maps for all DataType elements XMI.id attribute to a Basic/XmiVoid instance.
  */
  private HashMap ids=new HashMap();

  private void parseGeneralization(Element element)
  {
    //String name=getContent(getChildElement(element, "Foundation.Core.ModelElement.name"));

    String subtypeid=demandSubElRef(element, adapt("Foundation.Core.Generalization.subtype"));
    ModelClass subtype=((ModelClass)ids.get(subtypeid));
    if(subtype==null)
      throw new XmiException("Missing target of Generalization subtype id: "+subtypeid);

    String supertypeid=demandSubElRef(element, adapt("Foundation.Core.Generalization.supertype"));
    ModelClass supertype=((ModelClass)ids.get(supertypeid));
    if(supertype==null)
      throw new XmiException("Missing target of Generalization supertype id: "+supertypeid);

    subtype.addDirectSupertype(supertype);
    //System.out.println("Generalization "+subtype+" --> "+supertype);
  }

  private ModelAssociationEnd parseAssociationEnd(Element e)
  {
    String name=getContent(demandChildElement(e, adapt("Foundation.Core.ModelElement.name")));

    String id=demandSubElRef(e, adapt("Foundation.Core.AssociationEnd.type"));
    Type type=((Type)ids.get(id));
    if(type==null)
      throw new XmiException("Missing target of association end type id: "+type);
    if(!(type instanceof ModelClass))
      throw new XmiException("Association contains basic datatype.");
    ModelClass modelclass=(ModelClass)type;

    String multiplicity=getContent(demandChildElement(e, adapt("Foundation.Core.AssociationEnd.multiplicity")));

    String isOrderedString=demandAttribute(demandChildElement(e, adapt("Foundation.Core.AssociationEnd.isOrdered")), adapt("xmi.value"));
    boolean isOrdered;
    if("true".equals(isOrderedString))
      isOrdered=true;
    else if("false".equals(isOrderedString))
      isOrdered=false;
    else
      throw new XmiException("Value is <isOrdered> is neither true or false.");

    List qualifierslist=new LinkedList();
    NodeList nl=e.getElementsByTagName(adapt("Foundation.Core.Attribute"));
    int nli=nl.getLength();
    for(int i=0; i<nli; i++)
    {
      Element parel=(Element)nl.item(i);
      String typeid=demandSubElRef(parel, adapt("Foundation.Core.StructuralFeature.type"));
      Type qualifiertype=(Type)(ids.get(typeid));
      if(qualifiertype==null)
        throw new XmiException("Missing target of association qualifier type id \""+typeid+"\".");
      qualifierslist.add(qualifiertype);
    }

    Type[] qualifiers=null;
    if(!qualifierslist.isEmpty())
    {
      qualifiers=new Type[qualifierslist.size()];
      Iterator iter=qualifierslist.iterator();
      for(int i=0; iter.hasNext(); i++)
        qualifiers[i]=(Type)(iter.next());
    }

    return new ModelAssociationEnd(name, modelclass, multiplicity, isOrdered, qualifiers);
  }

  private void parseAssociation(Element e, ModelClass associationclass)
  {
    String name=getContent(getChildElement(e, adapt("Foundation.Core.ModelElement.name")));
    //System.out.println("Association "+(name!=null?name:""));

    NodeList nodelist=e.getElementsByTagName(adapt("Foundation.Core.AssociationEnd"));
    int nodelistlength=nodelist.getLength();
    ModelAssociation association=new ModelAssociation(associationclass);
    for(int i=0; i<nodelistlength; i++)
      association.addEnd(parseAssociationEnd((Element)(nodelist.item(i))));

    association.dissolve(model, qualifiersOnTarget);
  }

  private LinkedList generalizations=new LinkedList();
  private HashMap associations=new HashMap();

  public XmiParser(String url, String description, boolean qualifiersOnTarget) 
    throws org.xml.sax.SAXException, java.io.IOException
  {
    this.qualifiersOnTarget=qualifiersOnTarget;
    model=new Model(description);

    DOMParser parser = new DOMParser();
    parser.parse(url);
    Document doc = parser.getDocument();
    System.out.println("xmifacade: parsed "+description+(this.qualifiersOnTarget?"[qualifiersOnTarget]":""));
    Element documentEl=doc.getDocumentElement();

    //for(Element x=traverse(docel); x!=null; x=traverse(docel, x)) System.out.println(x.getTagName());

    Element contentEl=demandChildElement(documentEl, "XMI.content");

    adapter=Adapter.getAdapter(documentEl);
    System.out.println(adapter.getMessage());

    parsePackage(contentEl);

    if(!packagePath.isEmpty())
      throw new IllegalArgumentException();
    packagePath=null;

    for(Iterator i=generalizations.iterator(); i.hasNext(); )
      parseGeneralization((Element)i.next());
    generalizations=null;

    for(Iterator i=associations.keySet().iterator(); i.hasNext(); )
    {
      Element assoel=(Element)i.next();
      parseAssociation(assoel, (ModelClass)(associations.get(assoel)));
    }
    associations=null;

    for(Iterator i=classElements.keySet().iterator(); i.hasNext(); )
      elaborate((ModelClass)(i.next()));
    classElements=null;
    

    model.flatten();
    model.printData();

    url=null;
  }

  private LinkedList packagePath=new LinkedList();

  private void handlePackage(Element packageElement)
  {
    String packageName=getContent(demandChildElement(packageElement, adapt("Foundation.Core.ModelElement.name")));
    packagePath.add(packageName);
    //System.out.println("entering package "+packageName);
    parsePackage(packageElement);
    //System.out.println("leaving package "+packageName);
    packagePath.remove(packagePath.size()-1);
  }

  private void parsePackage(Element packageElement)
  {
    for(Element el1=getFirstChildElement(packageElement); el1!=null; el1=getNextSiblingElement(el1))
    {
      if(adapt("Foundation.Core.Namespace.ownedElement").equals(el1.getTagName()))
      {
        for(Element el2=getFirstChildElement(el1); el2!=null; el2=getNextSiblingElement(el2))
          handlePackageElement(el2);
      }
      handlePackageElement(el1);
    }
  }

  private void handlePackageElement(Element element)
  {
    //System.out.println("tagname "+el.getTagName()+" "+element.getTagName());
    if(adapt("Model_Management.Model").equals(element.getTagName()))
      handlePackage(element);
    if(adapt("Foundation.Core.Class").equals(element.getTagName()))
      parseClass(element, false);
    else if(adapt("Foundation.Core.DataType").equals(element.getTagName()))
      parseClass(element, true);
    else if(adapt("Foundation.Data_Types.Enumeration").equals(element.getTagName()))
      parseClass(element, true);
    else if(adapt("Foundation.Core.Generalization").equals(element.getTagName()))
      generalizations.add(element);
    else if(adapt("Foundation.Core.Association").equals(element.getTagName()))
      associations.put(element, null);
    else if(adapt("Foundation.Core.AssociationClass").equals(element.getTagName()))
    {
      ModelClass associationclass=(ModelClass)parseClass(element, false);
      associations.put(element, associationclass);
    }
  }

  /**
     Maps model classes to the elements in xmi file.
     Used to cross the gap beetween first and second pass of class parsing.
  */
  private HashMap classElements=new HashMap();

  private Any parseClass(Element element, boolean basictype)
  {
    String name=getContent(demandChildElement(element, adapt("Foundation.Core.ModelElement.name")));
    String id=demandAttribute(element, adapt("xmi.id"));

    Any any=null;
         if("String".equals(name))  any=Basic.STRING;
    else if("boolean".equals(name)) any=Basic.BOOLEAN;
    else if("byte".equals(name)||
            "int".equals(name)||
            "long".equals(name))    any=Basic.INTEGER;
    else if("float".equals(name) ||
            "double".equals(name))  any=Basic.REAL;
    else if("void".equals(name))    any=Model.VOID;
    else if("char".equals(name))    any=new ModelClass(packagePath, name);

    if(basictype)
      if(any!=null)
        //System.out.println("BasicType "+name+" "+id)
        ;
      else
      {
        any=new ModelClass(packagePath, name);
        System.out.println("xmifacade: warning: unknown basic type "+name);
      }
    else
      if(any!=null)
        //System.out.println("BasicType from Class "+name+" "+id)
        ;
      else
        any=new ModelClass(packagePath, name);

    if(ids.put(id, any)!=null)
      throw new XmiException("ambigous classifier id: "+id);

    if(any instanceof ModelClass)
    {
      model.putClassifier((ModelClass)any);
      classElements.put(any, element);
    }
    return any;
  }

  private void elaborate(ModelClass modelclass)
  {
    Element element=(Element)classElements.get(modelclass);

    NodeList nl;
    int nli;

    nl=element.getElementsByTagName(adapt("Foundation.Core.Attribute"));
    nli=nl.getLength();
    for(int i=0; i<nli; i++)
    {
      Element attel=(Element)nl.item(i);
      ModelAttribute attr=parseAttribute(attel);
      modelclass.addAttribute(attr);
    }

    nl=element.getElementsByTagName(adapt("Foundation.Core.Operation"));
    nli=nl.getLength();
    for(int i=0; i<nli; i++)
      modelclass.addOperation(parseOperation((Element)nl.item(i)));
  }

  private ModelAttribute parseAttribute(Element element)
  {
    String name=getContent(demandChildElement(element, adapt("Foundation.Core.ModelElement.name")));

    String typeid=demandSubElRef(element, adapt("Foundation.Core.StructuralFeature.type"));
    Type type=((Type)ids.get(typeid));
    if(type==null)
      throw new XmiException("Missing target of attribute type id \""+typeid+"\" in attribute \""+name+"\".");

    return new ModelAttribute(name, type);
  }

  private ModelOperation parseOperation(Element element)
  {
    String name=getContent(demandChildElement(element, adapt("Foundation.Core.ModelElement.name")));

    String isquerystring=demandAttribute(demandChildElement(element, adapt("Foundation.Core.BehavioralFeature.isQuery")), adapt("xmi.value"));
    boolean isquery;
    if("true".equals(isquerystring))
      isquery=true;
    else if("false".equals(isquerystring))
      isquery=false;
    else
      throw new XmiException("Value of <isQuery> is neither true or false.");

    Type returntype=null;
    List paramslist=new LinkedList();
    NodeList nl=element.getElementsByTagName(adapt("Foundation.Core.Parameter"));
    int nli=nl.getLength();
    for(int i=0; i<nli; i++)
    {
      Element parel=(Element)nl.item(i);
      String typeid=demandSubElRef(parel, adapt("Foundation.Core.Parameter.type"));
      Type type=(Type)(ids.get(typeid));
      if(type==null)
        throw new XmiException("Missing target of operation \""+name+"\" parameter type id \""+typeid+"\".");
      String kind=demandAttribute(demandChildElement(parel, adapt("Foundation.Core.Parameter.kind")), adapt("xmi.value"));
      if("return".equals(kind))
      {
        if(returntype!=null)
          throw new XmiException("Operation \""+name+"\" has more than one return parameter");
        returntype=type;
      }
      else
        paramslist.add(type);
    }
    if(returntype==null)
    {
      System.out.println("xmifacade: warning: operation \""+name+"\" has no return type defined, assuming void.");
      returntype=Model.VOID;
    }

    Type[] params=new Type[paramslist.size()];
    Iterator iter=paramslist.iterator();
    for(int i=0; iter.hasNext(); i++)
      params[i]=(Type)(iter.next());

    return new ModelOperation(name, params, returntype, isquery);
  }

  /**
     Returns the first direct subelement with the given name.
     Returns null if no such subelement exists.
  */
  static Element getChildElement(Element e, String tagname)
  {
    for(Node c=e.getFirstChild(); c!=null; c=c.getNextSibling())
      if(c.getNodeType()==Node.ELEMENT_NODE)
      {
        Element ce=(Element)c;
        if(tagname.equals(ce.getTagName()))
          return ce;
      }
    return null;
  }

  /**
     Returns the first direct subelement with the given name.
     Throws XmiException if no such subelement exists.
     @see XmiException
  */
  static Element demandChildElement(Element e, String tagname)
  {
    Element r=getChildElement(e, tagname);
    if(r==null)
      throw new XmiException("Expected subelement <"+tagname+"> in <"+e.getTagName()+">, not found "+e);
    return r;
  }

  /**
     Returns the attribute of the element with given the name.
     Throws XmiException if no such attribute exists.
     @see XmiException
  */
  static String demandAttribute(Element e, String attrname)
  {
    String r=e.getAttribute(attrname);
    if(r==null||r.length()==0)
      throw new XmiException("Expected attribute "+attrname+" in <"+e.getTagName()+">, not found");
    return r;
  }

  /**
     Returns the content of the element. The element should contain untagged text only.
     @see XmiException
  */
  static String getContent(Element e)
  {
    if(e==null) return null;
    Node c=e.getFirstChild();
    return (c!=null) ? c.getNodeValue() : null;
  }

  private Element getFirstChildElement(Element e)
  {
    for(Node n=e.getFirstChild(); n!=null; n=n.getNextSibling())
      if(n.getNodeType()==Node.ELEMENT_NODE)
        return (Element)n;
    return null;
  }

  private Element getNextSiblingElement(Element e)
  {
    for(Node n=e.getNextSibling(); n!=null; n=n.getNextSibling())
      if(n.getNodeType()==Node.ELEMENT_NODE)
        return (Element)n;
    return null;
  }
      
  /**
     Given the element &lt;element&gt;&lt;subelement&gt;&lt;XMI.reference target="somewhere"&gt;&lt;/&gt;&lt;/&gt;,
     demandSubElRef(element, "subelement") will return "somewhere".
     Vastly needed in XMI.
     Throws XmiException if subelements or attribute does not exist.
     @see XmiException
  */
  String demandSubElRef(Element element, String subelement)
  {
    Element subel=demandChildElement(element, subelement);
    Element subref=getFirstChildElement(subel);
    if(subref==null)
      throw new XmiException("subref not found");
    if(getNextSiblingElement(subref)!=null)
      throw new XmiException("more than one subrefs found");
    return demandAttribute(subref, adapt("xmi.idref"));
  }

  public static void main(String[] args)
  {
    for(int i=0; i<args.length; i++)
    {
      try
      {
        Model m=XmiParser.getModel(args[i], args[i]);
        m.printData();
      }
      catch(java.io.IOException e) {e.printStackTrace(System.out);}
      catch(org.xml.sax.SAXException e) {e.printStackTrace(System.out);}
    }
  }

}


abstract class Adapter
{
  static Adapter getAdapter(Element el)
  {
    NodeList nl;
    int nli;

    nl=el.getElementsByTagName("Class");
    nli=nl.getLength();
    if(nli>0) return new IbmAdapter();

    nl=el.getElementsByTagName("Foundation.Core.Class");
    nli=nl.getLength();
    if(nli>0) return new OmgAdapter();

    throw new XmiException("Document contains neither <Foundation.Core.Class> nor <Class>, cannot detect XMI type.");
  }

  abstract String adapt(String x);

  abstract String getMessage();
}

final class IbmAdapter extends Adapter
{
  private HashMap map=new HashMap();

  protected IbmAdapter()
  {
    map.put("xmi.id",    "XMI.id");
    map.put("xmi.value", "XMI.value" );
    map.put("xmi.idref", "target");
  }

  String adapt(String x)
  {
    Object o=map.get(x);
    if(o!=null)
      return (String)o;

    int pos=x.lastIndexOf('.');
    if(pos<0)
      throw new IllegalArgumentException();
    String s=x.substring(pos+1, x.length());
    map.put(x,s);
    //System.out.println("    IbmAdapter: \""+x+"\" - \""+s+"\".");
    return s;
  }

  String getMessage()
  {
    return "xmifacade: using XMI adapter for IBM (tested with Argo 0.7).";
  }
}

final class OmgAdapter extends Adapter
{
  protected OmgAdapter() {}

  String adapt(String x)
  {
    return x;
  }

  String getMessage()
  {
    return "xmifacade: using XMI adapter for OMG (tested with Rational Rose 98 sp1 with Unisys Plugin).";
  }
}
