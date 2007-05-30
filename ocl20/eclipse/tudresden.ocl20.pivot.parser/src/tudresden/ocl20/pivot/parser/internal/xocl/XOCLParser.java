/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology,     *
 * Dresden University Of Technology, Germany (http://st.inf.tu-dresden.de).  *
 * It is understood that any modification not identified as such is not      *
 * covered by the preceding statement.                                       *
 *                                                                           *
 * This work is free software; you can redistribute it and/or modify it      *
 * under the terms of the GNU Library General Public License as published    *
 * by the Free Software Foundation; either version 2 of the License, or      *
 * (at your option) any later version.                                       *
 *                                                                           *
 * This work is distributed in the hope that it will be useful, but WITHOUT  *
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or     *
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Library General Public     *
 * License for more details.                                                 *
 *                                                                           *
 * You should have received a copy of the GNU Library General Public License *
 * along with this library; if not, you can view it online at                *
 * http://www.fsf.org/licensing/licenses/gpl.html.                           *
 *                                                                           *
 * To submit a bug report, send a comment, or get the latest news on this    *
 * project, please visit the website: http://dresden-ocl.sourceforge.net.    *
 * For more information on OCL and related projects visit the OCL Portal:    *
 * http://st.inf.tu-dresden.de/ocl                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * $Id$
 */
package tudresden.ocl20.pivot.parser.internal.xocl;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.osgi.util.NLS;

import tudresden.ocl20.pivot.essentialocl.expressions.CollectionItem;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionKind;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralPart;
import tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl;
import tudresden.ocl20.pivot.essentialocl.expressions.OclExpression;
import tudresden.ocl20.pivot.essentialocl.expressions.Variable;
import tudresden.ocl20.pivot.essentialocl.types.CollectionType;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelFactory;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.parser.IOclParser;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.parser.ParserPlugin;
import tudresden.ocl20.pivot.parser.internal.ParserMessages;
import tudresden.ocl20.pivot.pivotmodel.ConstrainableElement;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.ConstraintKind;
import tudresden.ocl20.pivot.pivotmodel.Expression;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.xocl.CollectionItemXS;
import tudresden.ocl20.pivot.xocl.CollectionKindXS;
import tudresden.ocl20.pivot.xocl.CollectionLiteralExpXS;
import tudresden.ocl20.pivot.xocl.CollectionLiteralPartXS;
import tudresden.ocl20.pivot.xocl.CollectionOperationCallExpXS;
import tudresden.ocl20.pivot.xocl.CollectionRangeXS;
import tudresden.ocl20.pivot.xocl.ConstraintKindXS;
import tudresden.ocl20.pivot.xocl.ConstraintXS;
import tudresden.ocl20.pivot.xocl.ExpressionInOclXS;
import tudresden.ocl20.pivot.xocl.IntegerLiteralExpXS;
import tudresden.ocl20.pivot.xocl.ModelOperationCallExpXS;
import tudresden.ocl20.pivot.xocl.NamespaceXS;
import tudresden.ocl20.pivot.xocl.OclExpressionXS;
import tudresden.ocl20.pivot.xocl.OperationCallExpXS;
import tudresden.ocl20.pivot.xocl.PropertyCallExpXS;
import tudresden.ocl20.pivot.xocl.VariableExpXS;
import tudresden.ocl20.pivot.xocl.VariableXS;
import tudresden.ocl20.pivot.xocl.XOCLPackage;
import tudresden.ocl20.pivot.xocl.util.XOCLSwitch;

/**
 * 
 * 
 * @author Matthias Braeuer
 * @version 1.0 17.04.2007
 */
public class XOCLParser implements IOclParser {

  // a logger for this class
  private static final Logger logger = ParserPlugin.getLogger(XOCLParser.class);

  // a cached reference to the model that is the base of OCL expressions parsed by this parser
  private IModel model;

  // a cached reference to the model factory
  private IModelFactory modelFactory;

  /**
   * A concrete {@link XOCLSwitch} that uses an {@link IModelFactory} to produce
   * {@link OclExpression}s from {@link OclExpressionXS} elements.
   */
  protected class ModelSwitch extends XOCLSwitch<OclExpression> {

    // a cache of previously created variables
    private Map<VariableXS, Variable> variables;

    /**
     * Creates a new <code>ModelSwitch</code> instance.
     * 
     * @param variables the map of variables visible in the scope of the associated
     *          <code>ExpressionInOcl</code>
     */
    public ModelSwitch(Map<VariableXS, Variable> variables) {
      this.variables = variables;
    }

    /*
     * (non-Javadoc)
     * 
     * @see tudresden.ocl20.pivot.xocl.util.XOCLSwitch#casePropertyCallExpXS(tudresden.ocl20.pivot.xocl.PropertyCallExpXS)
     */
    @Override
    public OclExpression casePropertyCallExpXS(PropertyCallExpXS expression) {
      List<OclExpression> qualifier = new ArrayList<OclExpression>(expression.getQualifier().size());

      // parse the qualifiers of the property call expression
      for (OclExpressionXS oclExpressionXS : expression.getQualifier()) {
        qualifier.add(doSwitch(oclExpressionXS));
      }

      return getModelFactory().createPropertyCallExp(doSwitch(expression.getSource()),
          expression.getReferredPropertyName(),
          qualifier.toArray(new OclExpression[qualifier.size()]));
    }

    /*
     * (non-Javadoc)
     * 
     * @see tudresden.ocl20.pivot.xocl.util.XOCLSwitch#caseModelOperationCallExpXS(tudresden.ocl20.pivot.xocl.ModelOperationCallExpXS)
     */
    @Override
    public OclExpression caseModelOperationCallExpXS(ModelOperationCallExpXS expression) {
      return getModelFactory().createOperationCallExp(doSwitch(expression.getSource()),
          expression.getReferredOperationName(),parseArguments(expression));
    }

    /*
     * (non-Javadoc)
     * 
     * @see tudresden.ocl20.pivot.xocl.util.XOCLSwitch#caseCollectionOperationCallExpXS(tudresden.ocl20.pivot.xocl.CollectionOperationCallExpXS)
     */
    @Override
    public OclExpression caseCollectionOperationCallExpXS(CollectionOperationCallExpXS expression) {

      // parse the source expression
      OclExpression source = doSwitch(expression.getSource());

      // if the source expression is not a collection type, we need to create a Singleton set with
      // the expression (OCL Specification, Section 9.3)
      if (!(source.getType() instanceof CollectionType)) {
        source = source.withAsSet();
      }

      return getModelFactory().createOperationCallExp(source,
          expression.getReferredCollectionOperation().toString(),parseArguments(expression));
    }

    /**
     * Helper method to parse the arguments of an operation call expression.
     */
    private OclExpression[] parseArguments(OperationCallExpXS operationCallExpXS) {
      List<OclExpression> arguments;

      // create a new list for the OCL expressions parsed from the arguments
      arguments = new ArrayList<OclExpression>(operationCallExpXS.getArgument().size());

      // parse the arguments of the operation call expression
      for (OclExpressionXS oclExpressionXS : operationCallExpXS.getArgument()) {
        arguments.add(doSwitch(oclExpressionXS));
      }

      return arguments.toArray(new OclExpression[arguments.size()]);
    }

    /*
     * (non-Javadoc)
     * 
     * @see tudresden.ocl20.pivot.xocl.util.XOCLSwitch#caseVariableExpXS(tudresden.ocl20.pivot.xocl.VariableExpXS)
     */
    @Override
    public OclExpression caseVariableExpXS(VariableExpXS expression) {
      VariableXS variableXS = expression.getReferredVariable();

      if (variableXS == null) {
        throw new IllegalArgumentException(
            "The referred variable of a VariableExp must not be null."); //$NON-NLS-1$
      }

      Variable variable = variables.get(expression.getReferredVariable());

      if (variable == null) {
        throw new IllegalStateException("The variable '" //$NON-NLS-1$
            + expression.getReferredVariable().getName() + "' has not been defined."); //$NON-NLS-1$
      }

      return getModelFactory().createVariableExp(variable);
    }

    /*
     * (non-Javadoc)
     * 
     * @see tudresden.ocl20.pivot.xocl.util.XOCLSwitch#caseIntegerLiteralExpXS(tudresden.ocl20.pivot.xocl.IntegerLiteralExpXS)
     */
    @Override
    public OclExpression caseIntegerLiteralExpXS(IntegerLiteralExpXS expression) {
      return getModelFactory().createIntegerLiteralExp(expression.getIntegerSymbol());
    }

    /*
     * (non-Javadoc)
     * 
     * @see tudresden.ocl20.pivot.xocl.util.XOCLSwitch#caseCollectionLiteralExpXS(tudresden.ocl20.pivot.xocl.CollectionLiteralExpXS)
     */
    @Override
    public OclExpression caseCollectionLiteralExpXS(CollectionLiteralExpXS expression) {
      List<CollectionLiteralPart> parts = new ArrayList<CollectionLiteralPart>(expression.getPart()
          .size());

      // parse the individual parts
      for (CollectionLiteralPartXS part : expression.getPart()) {

        switch (part.eClass().getClassifierID()) {
          case XOCLPackage.COLLECTION_ITEM_XS:
            parts.add(createCollectionItem((CollectionItemXS) part));
            break;
          case XOCLPackage.COLLECTION_RANGE_XS:
            parts.add(createCollectionRange((CollectionRangeXS) part));
            break;
        }
      }

      return getModelFactory().createCollectionLiteralExp(
          translateCollectionKind(expression.getKind()),
          parts.toArray(new CollectionLiteralPart[parts.size()]));
    }

    /**
     * Helper method to create an OCL abstract syntax {@link CollectionItem} from an XOCL
     * {@link CollectionItemXS}.
     */
    private CollectionLiteralPart createCollectionItem(CollectionItemXS part) {
      return getModelFactory().createCollectionItem(doSwitch(part.getItem()));
    }

    /**
     * Helper method to create an OCL abstract syntax {@link CollectionRange} from an XOCL
     * {@link CollectionRangeXS}.
     */
    private CollectionLiteralPart createCollectionRange(CollectionRangeXS part) {
      return getModelFactory().createCollectionRange(doSwitch(part.getFirst()),
          doSwitch(part.getLast()));
    }

    /**
     * Translates a collection kind from XOCL to the OCL abstract syntax equivalent.
     */
    private CollectionKind translateCollectionKind(CollectionKindXS collectionKindXS) {
      CollectionKind kind;

      switch (collectionKindXS) {
        case SEQUENCE:
          kind = CollectionKind.SEQUENCE;
          break;

        case BAG:
          kind = CollectionKind.BAG;
          break;

        case SET:
          kind = CollectionKind.SET;
          break;

        case ORDERED_SET:
          kind = CollectionKind.ORDERED_SET;
          break;

        default:
          throw new IllegalArgumentException("Unknown collection kind '" + collectionKindXS //$NON-NLS-1$
              + "' found in XOCL expression."); //$NON-NLS-1$
      }

      return kind;
    }

  }

  /**
   * Creates a new <code>XOCLParser</code> instance.
   * 
   * @param model the model which OCL expressions should be added to
   */
  public XOCLParser(IModel model) {
    if (logger.isDebugEnabled()) {
      logger.debug("XOCLParser(model=" + model + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // precondition check
    if (model == null) {
      throw new IllegalArgumentException("The model must not be null."); //$NON-NLS-1$
    }

    // initialize
    this.model = model;

    // get the model factory from the model
    modelFactory = model.getFactory();

    if (modelFactory == null) {
      throw new IllegalArgumentException(
          "No model factory found for model '" + model.getDisplayName() + "'."); //$NON-NLS-1$ //$NON-NLS-2$
    }

    if (logger.isDebugEnabled()) {
      logger.debug("XOCLParser() - exit"); //$NON-NLS-1$
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.parser.IOclParser#parse(java.net.URL,
   *      tudresden.ocl20.pivot.modelbus.IModel)
   */
  public void parse(URL url) throws ParseException {
    if (logger.isDebugEnabled()) {
      logger.debug("parse(url=" + url + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    NamespaceXS declaredPackage;
    String declaredPackageName;
    Namespace targetNamespace;

    // check parameters
    if (url == null) {
      throw new IllegalArgumentException("The URL must not be null."); //$NON-NLS-1$
    }

    if (logger.isInfoEnabled()) {
      logger.info(NLS.bind(ParserMessages.XOCLParser_Parsing,url));
    }

    // get the top package and its name
    declaredPackage = getDeclaredPackage(loadResource(url));
    declaredPackageName = declaredPackage.getPathName();

    if (declaredPackageName == null) {
      logger.warn("The path name of the top package in the XOCL file '" + url //$NON-NLS-1$
          + "' is null; assuming the root package of the model."); //$NON-NLS-1$
      declaredPackageName = ""; //$NON-NLS-1$
    }

    // find the target namespace in the model
    targetNamespace = findTargetNamespace(declaredPackageName);

    // parse each constraint in the package
    for (ConstraintXS constraint : declaredPackage.getOwnedRule()) {
      if (logger.isInfoEnabled()) {
        logger.info(NLS.bind(ParserMessages.XOCLParser_ParsingConstraint,StringUtils
            .isNotEmpty(constraint.getName()) ? constraint.getName() : "unnamed")); //$NON-NLS-1$
      }

      // create the constraint and add it to the target namespace
      targetNamespace.addRule(createConstraint(constraint,declaredPackageName));
    }

    if (logger.isDebugEnabled()) {
      logger.debug("parse() - exit"); //$NON-NLS-1$
    }
  }

  /**
   * Helper method to load the XOCL resource from the given URL.
   */
  protected Resource loadResource(URL url) throws ParseException {
    if (logger.isDebugEnabled()) {
      logger.debug("loadResource(url=" + url + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    Resource resource;
    URI resourceURI;

    // try to create a URI
    try {
      resourceURI = URI.createURI(url.toString());
    }
    catch (IllegalArgumentException e) {
      logger.error("loadResource(url=" + url + ")",e); //$NON-NLS-1$ //$NON-NLS-2$
      throw new ParseException("Invalid URL: " + url,e); //$NON-NLS-1$
    }

    // get the resource
    resource = new ResourceSetImpl().createResource(resourceURI);

    // load the resource
    try {
      resource.load(null);
    }
    catch (IOException e) {
      logger.error("loadResource(url=" + url + ")",e); //$NON-NLS-1$ //$NON-NLS-2$
      throw new ParseException("Failed loading resource '" + resourceURI + "'.",e); //$NON-NLS-1$ //$NON-NLS-2$
    }

    if (logger.isDebugEnabled()) {
      logger.debug("loadResource() - exit - return value=" + resource); //$NON-NLS-1$
    }

    return resource;
  }

  /**
   * Helper method to return the containing namespace in the XOCL model which represents the
   * <code>package</code> statement in OCL.
   */
  protected NamespaceXS getDeclaredPackage(Resource resource) throws ParseException {
    if (logger.isDebugEnabled()) {
      logger.debug("getDeclaredPackage(resource=" + resource + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    NamespaceXS declaredPackage;

    // check that we have exactly one root
    if (resource.getContents().size() != 1) {
      throw new ParseException("Failed to parse '" + resource.getURI() //$NON-NLS-1$
          + "'. Exactly one NamespaceXS root representing the OCL package statement is required."); //$NON-NLS-1$
    }

    EObject root = resource.getContents().get(0);

    if (!(root instanceof NamespaceXS)) {
      throw new ParseException("The root of '" + resource.getURI() + "' is not a NamespaceXS."); //$NON-NLS-1$ //$NON-NLS-2$
    }

    declaredPackage = (NamespaceXS) root;

    if (logger.isDebugEnabled()) {
      logger.debug("getDeclaredPackage() - exit - return value=" + declaredPackage); //$NON-NLS-1$
    }

    return declaredPackage;
  }

  /**
   * Helper method to find the namespace in the model where the constraints should go to.
   */
  protected Namespace findTargetNamespace(String pathName) throws ParseException {
    if (logger.isDebugEnabled()) {
      logger.debug("findTargetNamespace(pathName=" + pathName + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    Namespace targetNamespace;

    try {
      targetNamespace = model.findNamespace(tokenizePathName(pathName));
    }

    catch (ModelAccessException e) {
      logger.error("findTargetNamespace(pathName=" + pathName + ")",e); //$NON-NLS-1$ //$NON-NLS-2$
      throw new ParseException("Error while looking up namespace '" + pathName + "' in model '" //$NON-NLS-1$ //$NON-NLS-2$
          + model.getDisplayName() + "'."); //$NON-NLS-1$
    }

    if (logger.isDebugEnabled()) {
      logger.debug("findTargetNamespace() - exit - return value=" + targetNamespace); //$NON-NLS-1$
    }

    return targetNamespace;
  }

  /**
   * Creates a {@link Constraint} from a {@link ConstraintXS} within the scope of the given package.
   * All references to types and features will be considered relative to the given package.
   */
  protected Constraint createConstraint(ConstraintXS constraintXS, String packageName)
      throws ParseException {

    if (logger.isDebugEnabled()) {
      logger.debug("createConstraint(constraintXS=" + constraintXS + ", packageName=" + packageName //$NON-NLS-1$ //$NON-NLS-2$
          + ") - enter"); //$NON-NLS-1$
    }

    Constraint constraint;
    ConstraintKind constraintKind;
    Expression specification;
    ConstrainableElement constrainedElement;

    try {
      // translate the constraint kind into the abstract syntax
      constraintKind = translateConstraintKind(constraintXS.getKind());

      // find the constrained element
      constrainedElement = findConstrainedElement(packageName,constraintXS.getConstrainedElement(),
          constraintKind);

      // create the specification expression
      specification = createExpressionInOcl(constraintXS.getSpecification());

      // create the constraint
      constraint = modelFactory.createConstraint(constraintXS.getName(),constraintKind,
          specification,constrainedElement);
    }

    catch (Exception e) {
      logger.error("createConstraint(constraintXS=" + constraintXS + ", packageName=" + packageName //$NON-NLS-1$ //$NON-NLS-2$
          + ")",e); //$NON-NLS-1$
      throw new ParseException("An error occured while parsing constraint '" //$NON-NLS-1$
          + constraintXS.getName() + "'.",e); //$NON-NLS-1$
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createConstraint() - exit - return value=" + constraint); //$NON-NLS-1$
    }

    return constraint;
  }

  /**
   * Helper method that translates a {@link ConstraintKindXS} into the corresponding
   * {@link ConstraintKind}.
   */
  protected ConstraintKind translateConstraintKind(ConstraintKindXS constraintKindXS) {
    if (logger.isDebugEnabled()) {
      logger.debug("translateConstraintKind(constraintKindXS=" + constraintKindXS + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    ConstraintKind constraintKind;

    if (constraintKindXS == null) {
      throw new IllegalArgumentException("The constraint kind must not be null."); //$NON-NLS-1$
    }

    switch (constraintKindXS) {
      case BODY:
        constraintKind = ConstraintKind.BODY;
        break;
      case DEFINITION:
        constraintKind = ConstraintKind.DEFINITION;
        break;
      case DERIVEDVALUE:
        constraintKind = ConstraintKind.DERIVED;
        break;
      case INITIALVALUE:
        constraintKind = ConstraintKind.INITIAL;
        break;
      case INVARIANT:
        constraintKind = ConstraintKind.INVARIANT;
        break;
      case PRECONDITION:
        constraintKind = ConstraintKind.PRECONDITION;
        break;
      case POSTCONDITION:
        constraintKind = ConstraintKind.POSTCONDITION;
        break;
      default:
        throw new IllegalArgumentException("Unknown constraint kind: " + constraintKindXS); //$NON-NLS-1$
    }

    if (logger.isDebugEnabled()) {
      logger.debug("translateConstraintKind() - exit - return value=" + constraintKind); //$NON-NLS-1$
    }

    return constraintKind;
  }

  /**
   * Helper method to look for a {@link ConstrainableElement} in the model.
   */
  protected ConstrainableElement findConstrainedElement(String packageName,
      String constrainedElementName, ConstraintKind constraintKind) {

    if (logger.isDebugEnabled()) {
      logger.debug("findConstrainedElement(packageName=" + packageName //$NON-NLS-1$
          + ", constrainedElementName=" + constrainedElementName + ", constraintKind=" //$NON-NLS-1$ //$NON-NLS-2$
          + constraintKind + ") - enter"); //$NON-NLS-1$
    }

    ConstrainableElement element = null;

    // split the name of the constrained element into its path segments, we construct a new list
    // here because tokenizePathName returns an immutable list
    List<String> pathName = new LinkedList<String>(tokenizePathName(constrainedElementName));

    // check if a valid path was found
    if (pathName.size() == 0) {
      throw new IllegalArgumentException(
          "The path name '" + constrainedElementName + "'is invalid."); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // prepend the package name
    pathName.add(0,packageName);

    // depending on the constraint kind, look for different types of constrainable elements
    switch (constraintKind) {
      case INVARIANT:
      case DEFINITION:
        element = findType(pathName);
        break;

      case DERIVED:
      case INITIAL:
        element = findProperty(pathName);
        break;

      case BODY:
      case PRECONDITION:
      case POSTCONDITION:
        element = findOperation(pathName);
        break;
    }

    // check that an element was found
    if (element == null) {
      throw new IllegalArgumentException("Failed to locate the constrained element '" //$NON-NLS-1$
          + constrainedElementName + "' in model '" + model.getDisplayName() + "'."); //$NON-NLS-1$ //$NON-NLS-2$
    }

    if (logger.isDebugEnabled()) {
      logger.debug("findConstrainedElement() - exit - return value=" + element); //$NON-NLS-1$
    }

    return element;
  }

  /**
   * Helper method to find a type in the associated model.
   */
  protected Type findType(List<String> pathName) {
    if (logger.isDebugEnabled()) {
      logger.debug("findType(pathName=" + pathName + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    Type type;

    try {
      type = model.findType(pathName);
    }

    catch (ModelAccessException e) {
      logger.error("findType(pathName=" + pathName + ")",e); //$NON-NLS-1$//$NON-NLS-2$
      throw new IllegalArgumentException("An error occured when accessing model '" //$NON-NLS-1$
          + model.getDisplayName() + "'."); //$NON-NLS-1$
    }

    if (logger.isDebugEnabled()) {
      logger.debug("findType() - exit - return value=" + type); //$NON-NLS-1$
    }

    return type;
  }

  /**
   * Helper method to find a property in the associated model.
   */
  protected Property findProperty(List<String> pathName) {
    // TODO: implement lookup of properties
    return null;
  }

  /**
   * Helper method to find an operation in the associated model.
   */
  protected Operation findOperation(List<String> pathName) {
    // TODO: implement lookup of operations
    return null;
  }

  /**
   * Helper method to create an {@link ExpressionInOcl} from an {@link ExpressionInOclXS}.
   */
  protected ExpressionInOcl createExpressionInOcl(ExpressionInOclXS expressionInOclXS) {
    if (logger.isDebugEnabled()) {
      logger.debug("createExpressionInOcl(expressionInOclXS=" + expressionInOclXS + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    ExpressionInOcl expressionInOcl;
    Map<VariableXS, Variable> variables;
    ModelSwitch modelSwitch;
    VariableXS contextVariableXS, resultVariableXS;
    Variable contextVariable, resultVariable = null;
    List<Variable> parameterVariables;

    // precondition check
    if (expressionInOclXS == null) {
      throw new IllegalArgumentException("The parameter 'expressionInOclCS' must not be null."); //$NON-NLS-1$
    }

    // create a new map to cache variables
    variables = new HashMap<VariableXS, Variable>();

    // create a new model switch
    modelSwitch = new ModelSwitch(variables);

    // create the various variables of the expression in OCL and cache them
    contextVariableXS = expressionInOclXS.getContext();

    if (contextVariableXS == null) {
      throw new IllegalArgumentException("No context variable defined for ExpressionInOclXS '" //$NON-NLS-1$
          + expressionInOclXS.getBody() + "'."); //$NON-NLS-1$
    }

    contextVariable = createVariable(expressionInOclXS.getContext(),modelSwitch);
    variables.put(contextVariableXS,contextVariable);

    resultVariableXS = expressionInOclXS.getResult();

    if (resultVariableXS != null) {
      resultVariable = createVariable(resultVariableXS,modelSwitch);
      variables.put(resultVariableXS,resultVariable);
    }

    parameterVariables = new ArrayList<Variable>(expressionInOclXS.getParameter().size());

    for (VariableXS parameterVariableXS : expressionInOclXS.getParameter()) {
      Variable parameterVariable = createVariable(parameterVariableXS,modelSwitch);
      parameterVariables.add(parameterVariable);
      variables.put(parameterVariableXS,parameterVariable);
    }

    // finally create the ExpressionInOcl
    expressionInOcl = modelFactory.createExpressionInOcl(expressionInOclXS.getBody(),modelSwitch
        .doSwitch(expressionInOclXS.getBodyExpression()),contextVariable,resultVariable,
        parameterVariables.toArray(new Variable[parameterVariables.size()]));

    if (logger.isDebugEnabled()) {
      logger.debug("createExpressionInOcl() - exit - return value=" + expressionInOcl); //$NON-NLS-1$
    }

    return expressionInOcl;
  }

  /**
   * Creates a {@link Variable} from a {@link VariableXS}.
   */
  protected Variable createVariable(VariableXS variableXS, ModelSwitch modelSwitch) {
    if (logger.isDebugEnabled()) {
      logger.debug("createVariable(variableXS=" + variableXS + ", modelSwitch=" + modelSwitch //$NON-NLS-1$ //$NON-NLS-2$
          + ") - enter"); //$NON-NLS-1$
    }

    Variable variable;
    OclExpression initExpression = null;

    // parse the init expression if existing
    if (variableXS.getInitExpression() != null) {
      initExpression = modelSwitch.doSwitch(variableXS.getInitExpression());
    }

    // create the variable using the model factory and the given model switch
    variable = modelFactory.createVariable(variableXS.getName(),tokenizePathName(variableXS
        .getType()),initExpression);

    if (logger.isDebugEnabled()) {
      logger.debug("createVariable() - exit - return value=" + variable); //$NON-NLS-1$
    }

    return variable;
  }

  /**
   * Helper method to split a path name separated by <code>::</code> into a list of strings. Note
   * that the returned list is <strong>immutable</strong>.
   * 
   * @param pathName a path name
   * 
   * @return a list of path segments
   */
  @SuppressWarnings("unchecked")
  protected List<String> tokenizePathName(String pathName) {

    // return an empty list if the path name is empty
    if (StringUtils.isEmpty(pathName)) {
      return Collections.EMPTY_LIST;
    }

    return Arrays.asList(pathName.split("::")); //$NON-NLS-1$
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.parser.IOclParser#dispose()
   */
  public void dispose() {
    model = null;
    modelFactory = null;
  }

  /**
   * Helper method that returns the model factory.
   */
  protected IModelFactory getModelFactory() {
    return modelFactory;
  }

  /**
   * Return a string representation of this parser.
   */
  @Override
  public String toString() {
    return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE).append("model",model) //$NON-NLS-1$
        .toString();
  }

}
