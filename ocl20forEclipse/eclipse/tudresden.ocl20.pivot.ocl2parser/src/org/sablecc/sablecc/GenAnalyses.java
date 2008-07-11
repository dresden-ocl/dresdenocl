/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/*
 * Enhanced by Ansgar Konermann to create an evaluator for 
 * L-attributed grammars.
 * Enhancements (c) 2004, 2005  Ansgar Konermann
 * Contact: <konermann@itikko.net>
 */

package org.sablecc.sablecc;

import org.sablecc.sablecc.AltInfo;
import org.sablecc.sablecc.AltInfoCast;
import org.sablecc.sablecc.Cast;
import org.sablecc.sablecc.ElemInfo;
import org.sablecc.sablecc.ElemInfoCast;
import org.sablecc.sablecc.MacroExpander;
import org.sablecc.sablecc.ParamInfo;
import org.sablecc.sablecc.ProdInfo;
import org.sablecc.sablecc.ProdInfoCast;
import org.sablecc.sablecc.ResolveIds;
import org.sablecc.sablecc.StringCast;
import org.sablecc.sablecc.TokenInfo;
import org.sablecc.sablecc.TokenInfoCast;
import org.sablecc.sablecc.TypeMap;
import org.sablecc.sablecc.TypedHashMap;
import org.sablecc.sablecc.TypedLinkedList;
import org.sablecc.sablecc.analysis.*;
import org.sablecc.sablecc.node.*;
import org.sablecc.sablecc.parser.ParserException;


import java.util.*;
import java.io.*;

public class GenAnalyses extends DepthFirstAdapter
{
  private MacroExpander macros;
  private ResolveIds ids;
  private File pkgDir;
  private String pkgName;
  private List elemList;
  private List altList = new TypedLinkedList(AltInfoCast.instance);
  private List tokenList = new TypedLinkedList(StringCast.instance);
  private List prodAlts = new TypedLinkedList(AltInfoCast.instance);
  private ProdInfo currentProd = null;
  private AltInfo currentAlt = null;
  private ElemInfo currentElem = null;
  private TokenInfo currentToken = null;
  
  private TypedHashMap prods = new TypedHashMap(StringCast.instance, ProdInfoCast.instance);
  private TypedHashMap tokens = new TypedHashMap(StringCast.instance, TokenInfoCast.instance);
  private String mainProduction;
  
  private TypeMap typemap = new TypeMap();

  //    final GenAnalyses instance = this;

  public GenAnalyses(ResolveIds ids)
  {
    this.ids = ids;

    try
    {
      macros = new MacroExpander(
                 new InputStreamReader(
                   getClass().getResourceAsStream("analyses.txt")));
    }
    catch(IOException e)
    {
      throw new RuntimeException("unable to open analyses.txt.");
    }

    pkgDir = new File(ids.pkgDir, "analysis");
    pkgName = ids.pkgName.equals("") ? "analysis" : ids.pkgName + ".analysis";

    if(!pkgDir.exists())
    {
      if(!pkgDir.mkdir())
      {
        throw new RuntimeException("Unable to create " + pkgDir.getAbsolutePath());
      }
    }
  }

  public void inATokenDef(ATokenDef node) {
    String tokenName = (String) ids.names.get(node);
    // String tokenType = (String) ids.elemTypes.get(node);
    
    tokenList.add(tokenName);
    currentToken = new TokenInfo();
    currentToken.skipTransform = (node.getExclam() != null);
    AAstType astType = (AAstType) node.getAstType();
    if ( astType != null ) {
        AGenericExternalName name = (AGenericExternalName) astType.getExternalName();
        currentToken.setAstTypeName(name.getId().getText());        
    } else {
        currentToken.setAstTypeName(null);
    }
    
    // System.out.println("Setting token info for token name " + tokenName);
    tokens.put(tokenName, currentToken);
  }
   
  public void inAParsedAlt(AParsedAlt node) {
    inPAlt(node);
  }

  public void inAIgnoredAlt(AIgnoredAlt node) {
    inPAlt(node);
  }

  public void inPAlt(PAlt node) {
      currentAlt = new AltInfo();
      elemList = new TypedLinkedList(ElemInfoCast.instance);
  }

  public void inAElem(AElem node) {
    currentElem = new ElemInfo();

    currentElem.name = (String) ids.names.get(node);
    currentElem.type = (String) ids.elemTypes.get(node);
//    if ( currentElem.type == null ) {
//        throw new RuntimeException("No type for node " + node.toString());
//    }
    currentElem.operator = ElemInfo.OPER_NONE;
    if(node.getUnOp() != null) {
      node.getUnOp().apply(new DepthFirstAdapter() {
         public void caseAStarUnOp(AStarUnOp node) { currentElem.operator = ElemInfo.OPER_STAR; }
         public void caseAQMarkUnOp(AQMarkUnOp node) { currentElem.operator = ElemInfo.OPER_QMARK; }
         public void caseAPlusUnOp(APlusUnOp node) { currentElem.operator = ElemInfo.OPER_PLUS; }
       }
      );
    }
    
    currentElem.belongsToProduction = currentProd;
    currentElem.skipTransform = ( node.getExclam() != null );
    currentElem.customHeritage = ( node.getCustomHeritage() != null );
    currentElem.makeTree = ( node.getMakeTree() != null );    
    if ( currentElem.makeTree && currentElem.customHeritage ) {
        System.out.println("Warning: #customheritage behaviour is implicit when " +
            "using #maketree (at: " + node.getCustomHeritage().getLine() + "," +
            node.getCustomHeritage().getPos() + ")");
    }
    if ( currentElem.makeTree && 
        ( currentElem.operator == ElemInfo.OPER_NONE || currentElem.operator == ElemInfo.OPER_QMARK)
       )
    {
        throw new RuntimeException("Error: only list constructs can be converted " + 
            "into trees  (at: " + node.getMakeTree().getLine() + "," +
            node.getMakeTree().getPos() + ")");
    }
        
    if ( currentElem.type.startsWith("T") ) {
        currentElem.kind = ElemInfo.KIND_TOKEN;        
    } else if ( currentElem.type.startsWith("P") ) {
        currentElem.kind = ElemInfo.KIND_PRODUCTION;
    } else {
        currentElem.kind = ElemInfo.KIND_UNKNOWN;
    }


    elemList.add(currentElem);
  }
  
  public void outAElem(AElem node) {
      currentElem = null;
  }

  public void outAParsedAlt(AParsedAlt node)
  {
    AAstType astType = (AAstType) node.getAstType();  
    if ( astType != null ) {
        AGenericExternalName nm = (AGenericExternalName) astType.getExternalName();       
        String astTypeName = nm.getId().getText();
        currentAlt.setAstTypeName(astTypeName);       
    } else {
        currentAlt.setAstTypeName(null);
    }
    TChain chain = node.getChain();
    if ( chain != null ) {
        currentAlt.isChainRule = true;
        currentAlt.chainToken = chain;
    } else {
        currentAlt.isChainRule = false;
    }
    TNocreate nocreate = node.getNocreate();
    if ( nocreate != null ) {
        currentAlt.delegateCreateAst = true;
    } else {
        currentAlt.delegateCreateAst = false;
    }

    outPAlt(node);
  }

  public void outAIgnoredAlt(AIgnoredAlt node)
  {
    outPAlt(node);
  }

  public void outPAlt(PAlt node) {
    // AltInfo info = new AltInfo();

    currentAlt.name = (String) ids.names.get(node);
    if ( this.currentProd == null ) {
        System.out.println("currentProd is null for " + currentAlt.name );
    } else {
        currentAlt.belongsTo = this.currentProd;
    }
    currentAlt.elems.addAll(elemList);
    elemList = null;

    altList.add(currentAlt);
  }
  
  
  public void inAProd(AProd node) {
    if(mainProduction == null) {
      mainProduction = (String) ids.names.get(node);
    } 

    this.currentProd = new ProdInfo();
    String prodName = (String) ids.names.get(node);
    currentProd.name = prodName;
    boolean skipTransform = ( node.getExclam() != null );
    currentProd.skipTransform = skipTransform;

    AAstType astType = (AAstType) node.getAstType();
    String astTypeName = null;
    if ( astType != null ) {
        AGenericExternalName nm = (AGenericExternalName) astType.getExternalName();       
        astTypeName = nm.getId().getText();
    }
    currentProd.setAstTypeName(astTypeName);        
    
    // System.out.print("Setting production info for production name " + prodName);
    prods.put(prodName, currentProd);    
    // prodAlts = new TypedLinkedList(AltInfoCast.instance);
  }
  
  public void outStart(Start node)   {
    try {
        createAnalysis();
        createAnalysisWithReturn();

        createAnalysisAdapter();

        if(mainProduction != null) {
          createDepthFirstAdapter();
          createReversedDepthFirstAdapter();

          try {
              createLAttrEvalAdapter();
          } catch (ParserException pe) {
              pe.printStackTrace();
              Token tk = pe.getToken();
              System.out.println("At: " + tk.getLine() + ":" + tk.getPos());
          }
        }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void createAnalysis()
  {
    BufferedWriter file;

    try
    {
      file = new BufferedWriter(
               new FileWriter(
                 new File(pkgDir, "Analysis.java")));
    }
    catch(IOException e)
    {
      throw new RuntimeException("Unable to create " + new File(pkgDir, "Analysis.java").getAbsolutePath());
    }

    try
    {
      macros.apply(file, "AnalysisHeader", new String[] {pkgName,
                   ids.pkgName.equals("") ? "node" : ids.pkgName + ".node"});

      if(mainProduction != null)
      {
        macros.apply(file, "AnalysisStart", null);

        for(Iterator i = altList.iterator(); i.hasNext();) {
          AltInfo info = (AltInfo) i.next();
          macros.apply(file, "AnalysisBody", new String[] {info.name});
        }

        file.newLine();
      }

      for(Iterator i = tokenList.iterator(); i.hasNext();)
      {
        macros.apply(file, "AnalysisBody",
                     new String[] {(String) i.next()});
      }

      macros.apply(file, "AnalysisTail", null);
    }
    catch(IOException e)
    {
      throw new RuntimeException("An error occured while writing to " +
                                 new File(pkgDir, "Analysis.java").getAbsolutePath());
    }

    try
    {
      file.close();
    }
    catch(IOException e)
    {}
  }
  
  public void createAnalysisWithReturn() {
    BufferedWriter file;
    String fileName = "AnalysisWithReturn.java";

    try {
      file = new BufferedWriter(
               new FileWriter(
                 new File(pkgDir, fileName)));
    } catch(IOException e) {
      throw new RuntimeException("Unable to create " + new File(pkgDir, fileName).getAbsolutePath());
    }

    try {
        String rootPackageName = ResolveIds.getRootPackageName(pkgName);

        macros.apply(file, "AnalysisWithReturnHeader", new String[] {
                    pkgName,
                    ids.pkgName.equals("") ? "node" : ids.pkgName + ".node",
                    rootPackageName
        }
      );

      if(mainProduction != null)
      {
        macros.apply(file, "AnalysisWithReturnStart", null);

        for(Iterator i = altList.iterator(); i.hasNext();) {
          AltInfo info = (AltInfo) i.next();
          String returnType = info.getAstTypeName();
          macros.apply(file, "AnalysisWithReturnBody", new String[] {
              info.name, returnType }
          );
        }

        file.newLine();
      }

      for(Iterator i = tokenList.iterator(); i.hasNext();) {
        String tokName = (String) i.next();
        TokenInfo info = (TokenInfo) tokens.get(tokName);
//        if ( info.skipTransform ) {
//            continue;
//        }
        String astTypeName = info.getAstTypeName();
        macros.apply(file, "AnalysisWithReturnBody",
                     new String[] { tokName, astTypeName });
      }

      macros.apply(file, "AnalysisWithReturnTail", null);
    } catch(IOException e) {
      throw new RuntimeException("An error occured while writing to " +
                                 new File(pkgDir, fileName).getAbsolutePath());
    }

    try {
      file.close();
    } catch(IOException e) {
        e.printStackTrace();
    }
  }
  

  public void createAnalysisAdapter()
  {
    BufferedWriter file;

    try
    {
      file = new BufferedWriter(
               new FileWriter(
                 new File(pkgDir, "AnalysisAdapter.java")));
    }
    catch(IOException e)
    {
      throw new RuntimeException("Unable to create " + new File(pkgDir, "AnalysisAdapter.java").getAbsolutePath());
    }

    try
    {
      macros.apply(file, "AnalysisAdapterHeader", new String[] {pkgName,
                   ids.pkgName.equals("") ? "node" : ids.pkgName + ".node"});

      if(mainProduction != null)
      {
        macros.apply(file, "AnalysisAdapterStart", null);

        for(Iterator i = altList.iterator(); i.hasNext();)
        {
          AltInfo info = (AltInfo) i.next();

          macros.apply(file, "AnalysisAdapterBody",
                       new String[] {info.name});
        }
      }

      for(Iterator i = tokenList.iterator(); i.hasNext();)
      {
        macros.apply(file, "AnalysisAdapterBody",
                     new String[] {(String) i.next()});
      }

      macros.apply(file, "AnalysisAdapterTail", null);
    }
    catch(IOException e)
    {
      throw new RuntimeException("An error occured while writing to " +
                                 new File(pkgDir, "AnalysisAdapter.java").getAbsolutePath());
    }

    try
    {
      file.close();
    }
    catch(IOException e)
    {}
  }

  public void createDepthFirstAdapter()
  {
    BufferedWriter file;

    try
    {
      file = new BufferedWriter(
               new FileWriter(
                 new File(pkgDir, "DepthFirstAdapter.java")));
    }
    catch(IOException e)
    {
      throw new RuntimeException("Unable to create " + new File(pkgDir, "DepthFirstAdapter.java").getAbsolutePath());
    }

    try
    {
      macros.apply(file, "DepthFirstAdapterHeader", new String[] {pkgName,
                   ids.pkgName.equals("") ? "node" : ids.pkgName + ".node",
                   mainProduction});

      for(Iterator i = altList.iterator(); i.hasNext();)
      {
        AltInfo info = (AltInfo) i.next();

        macros.apply(file, "DepthFirstAdapterInOut",
                     new String[] {info.name});

        macros.apply(file, "DepthFirstAdapterCaseHeader",
                     new String[] {info.name});

        for(Iterator j = info.elems.iterator(); j.hasNext();)
        {
          ElemInfo eInfo = (ElemInfo) j.next();

          switch(eInfo.operator)
          {
          case ElemInfo.OPER_QMARK:
          case ElemInfo.OPER_NONE:
            {
              macros.apply(file, "DepthFirstAdapterCaseBodyNode",
                           new String[] {eInfo.name});
            }
            break;
          case ElemInfo.OPER_STAR:
          case ElemInfo.OPER_PLUS:
            {
              macros.apply(file, "DepthFirstAdapterCaseBodyList",
                           new String[] {eInfo.name, eInfo.type});
            }
            break;
          }
        }

        macros.apply(file, "DepthFirstAdapterCaseTail",
                     new String[] {info.name});

      }

      macros.apply(file, "DepthFirstAdapterTail", null);
    }
    catch(IOException e)
    {
      throw new RuntimeException("An error occured while writing to " +
                                 new File(pkgDir, "DepthFirstAdapter.java").getAbsolutePath());
    }

    try
    {
      file.close();
    }
    catch(IOException e)
    {}
  }

  public void createReversedDepthFirstAdapter()
  {
    BufferedWriter file;

    try
    {
      file = new BufferedWriter(
               new FileWriter(
                 new File(pkgDir, "ReversedDepthFirstAdapter.java")));
    }
    catch(IOException e)
    {
      throw new RuntimeException("Unable to create " + new File(pkgDir, "ReversedDepthFirstAdapter.java").getAbsolutePath());
    }

    try
    {
      macros.apply(file, "ReversedDepthFirstAdapterHeader", new String[] {pkgName,
                   ids.pkgName.equals("") ? "node" : ids.pkgName + ".node",
                   mainProduction});

      for(Iterator i = altList.iterator(); i.hasNext();)
      {
        AltInfo info = (AltInfo) i.next();

        macros.apply(file, "DepthFirstAdapterInOut",
                     new String[] {info.name});

        macros.apply(file, "DepthFirstAdapterCaseHeader",
                     new String[] {info.name});

        for(ListIterator j = info.elems.listIterator(info.elems.size()); j.hasPrevious();)
        {
          ElemInfo eInfo = (ElemInfo) j.previous();

          switch(eInfo.operator)
          {
          case ElemInfo.OPER_QMARK:
          case ElemInfo.OPER_NONE:
            {
              macros.apply(file, "DepthFirstAdapterCaseBodyNode",
                           new String[] {eInfo.name});
            }
            break;
          case ElemInfo.OPER_STAR:
          case ElemInfo.OPER_PLUS:
            {
              macros.apply(file, "ReversedDepthFirstAdapterCaseBodyList",
                           new String[] {eInfo.name, eInfo.type});
            }
            break;
          }
        }

        macros.apply(file, "DepthFirstAdapterCaseTail",
                     new String[] {info.name});

      }

      macros.apply(file, "DepthFirstAdapterTail", null);
    }
    catch(IOException e)
    {
      throw new RuntimeException("An error occured while writing to " +
                                 new File(pkgDir, "ReversedDepthFirstAdapter.java").getAbsolutePath());
    }

    try
    {
      file.close();
    }
    catch(IOException e)
    {}
  }
  
    private void createAttrEvalException() {
        
        BufferedWriter file;
        String outFileName = "AttrEvalException.java";
        String absOutFileName = new File(pkgDir, outFileName).getAbsolutePath();               
        

        try {
            file = new BufferedWriter(new FileWriter(new File(pkgDir, outFileName)));
        } catch(IOException e) {
            throw new RuntimeException("Unable to create " + absOutFileName );
        }

        try {
            macros.apply(file, "AttrEvalException",  new String[] { 
                pkgName,
                ids.pkgName.equals("") ? "node" : ids.pkgName + ".node",
            });
        } catch(IOException e) {
          throw new RuntimeException("An error occured while writing to " + absOutFileName );
        }

        try {
            file.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
  
    public void createLAttrEvalAdapter() throws ParserException {        
        
        createAttrEvalException();
        
        BufferedWriter file;
        String outFileName = "LAttrEvalAdapter.java";
        String absOutFileName = new File(pkgDir, outFileName).getAbsolutePath();               
        
        CharArrayWriter nestedCAW = new CharArrayWriter(32768);
        BufferedWriter nested = new BufferedWriter(nestedCAW);

        try {
            file = new BufferedWriter(new FileWriter(new File(pkgDir, outFileName)));
        } catch(IOException e) {
            throw new RuntimeException("Unable to create " + absOutFileName );
        }

        String rootPackageName = ResolveIds.getRootPackageName(pkgName);
        
        try {
            
            macros.apply(file, "LAttrEvalAdapterHeader",  new String[] { 
                pkgName,
                ids.pkgName.equals("") ? "node" : ids.pkgName + ".node",
                mainProduction,
                rootPackageName
            });
            
            // iterate over list of all alternatives
            for( Iterator i = altList.iterator(); i.hasNext(); ) {
                AltInfo info = (AltInfo) i.next();
                if ( info.belongsTo.skipTransform ) {
                    // don't generate code for this alternative, since it is
                    // an alternative of a skipped production
                    continue;
                }
                String returnType = info.getAstTypeName();
                if ( returnType == null ) {
                    throw new RuntimeException("No ast type name known for " +
                        "alternative " + info.name);
                }

                macros.apply(file, "LAttrEvalAdapterCaseHeader", 
                    new String[] { info.name, returnType }
                );

                // some child nodes might have been marked as irrelevant
                // by prefixing them with an exclamation mark '!', and so we
                // won't process them here.
                List relevantChildList = new java.util.LinkedList();                
                for(Iterator j = info.elems.iterator(); j.hasNext();) {
                    ElemInfo eInfo = (ElemInfo) j.next();
                    
                    if ( eInfo.skipTransform ) {
                        continue;
                    }
                    
                    String elementAstType = null;
                    switch ( eInfo.kind ) {
                        case ElemInfo.KIND_TOKEN:
                            TokenInfo ti = (TokenInfo) tokens.get(eInfo.type);
                            if ( ti == null ) {
                                System.out.println("No token info for element " + eInfo.name + " (type: " + eInfo.type + ")");
                                continue;
                            }
                            if (ti.skipTransform) {
                                continue;
                            }
                            elementAstType = ti.getAstTypeName();
                        break;
                        case ElemInfo.KIND_PRODUCTION:
                            ProdInfo pi = (ProdInfo) prods.get(eInfo.type);
                            if ( pi == null ) {
                                System.out.println("No production info for element " + eInfo.name + " (type: " + eInfo.type + ")");
                                continue;
                            }
                            if ( pi.skipTransform ) {
                                 continue;
                            }
                            elementAstType = pi.getAstTypeName();
                        break;
                        default:
                            throw new RuntimeException("Unknown element kind for " + eInfo.name);
                    }

                    String formalParamAstType = null;
                    String formalParamNodeName = null;
                    boolean formalParamIsList = false;                    
                    boolean convertToTree = false;
                    
                    switch(eInfo.operator) {
                        case ElemInfo.OPER_QMARK:
                        case ElemInfo.OPER_NONE:
                                formalParamAstType = elementAstType;
                                formalParamNodeName = eInfo.name;
                                formalParamIsList = false;
                            break;
                        case ElemInfo.OPER_STAR:
                        case ElemInfo.OPER_PLUS:
                                if ( eInfo.makeTree ) {
                                    // element will be converted into a tree
                                    formalParamAstType = elementAstType;
                                    formalParamNodeName = eInfo.name;
                                    // System.out.println("convertToTree is true for " + eInfo.name + " inside " + eInfo.belongsToProduction.name );
                                    convertToTree = true;
                                    formalParamIsList = false;
                                } else {
                                    // element will be converted into a list
                                    formalParamAstType = elementAstType;
                                    formalParamNodeName = eInfo.name;                                    
                                    formalParamIsList = true;
                                }                                
                            break;
                    }
                    
                    // create new formal parameter item for the current child
                    ParamInfo currentParam = new ParamInfo(formalParamIsList,
                        convertToTree, formalParamNodeName, formalParamAstType, 
                        eInfo);
                    relevantChildList.add(currentParam);
                }
                // end: filtering elements of alternative
                
                // check whether we want to use / can use chaining
                boolean useChaining = false;
                ParamInfo chainedElement = null;                                
                if ( info.isChainRule ) {                   
                    // check chaining rules
                    // 1. exactly one relevant child required
                    int size = relevantChildList.size();
                    if ( size != 1 ) {
                        throw new ParserException(info.chainToken, 
                            "Chain rules require exactly one relevant child node, found " + size + " nodes.");
                    }
                    ParamInfo currentParam = (ParamInfo) relevantChildList.get(0);
                    ElemInfo eInfo = currentParam.referencedElement;

                    // 2. AST type of alternative must be a supertype of AST type of
                    //    relevant child element 
                    String astTypeAlternative = info.getAstTypeName();                    
                    String astTypeElement = eInfo.getReferencedAstType(prods, tokens);
                    
                    /*************** Change by Nils *************************/
                    // If a dot is part of the type name then take the the whole part name, otherwise get the package name
                    // and append the type name
                    String astTypeAlternativeQualified = null;
                    if (astTypeAlternative.contains(".")) {
                    	astTypeAlternativeQualified = astTypeAlternative;
                    }
                    else {
                    	astTypeAlternativeQualified = typemap.getPackageForType(astTypeAlternative) + "." + astTypeAlternative;
                    }
                    /*String astTypeAlternativeQualified = typemap.getPackageForType(astTypeAlternative) +
                        "." + astTypeAlternative;*/
                    
                    String astTypeElementQualified = null;
                    if (astTypeElement.contains(".")) {
                    	astTypeElementQualified = astTypeElement;
                    }
                    else {
                    	astTypeElementQualified = typemap.getPackageForType(astTypeElement) + "." + astTypeElement;
                    }
                    
                    /************************* Change end ************************/
                    /*String astTypeElementQualified = typemap.getPackageForType(astTypeElement) + 
                        "." + astTypeElement;*/
                    boolean matches = false;
                    try {
                        Class classAlternative = Class.forName(astTypeAlternativeQualified ); /*,
                            true, ClassLoader.getSystemClassLoader() ); */
                        Class classElement = Class.forName(astTypeElementQualified ); /*,
                            true, ClassLoader.getSystemClassLoader() ); */
                        matches = classAlternative.isAssignableFrom(classElement);
                    } catch (ClassNotFoundException cnfe) {
                        Throwable t = cnfe.getCause();
                        if ( t != null ) {
                            t.printStackTrace();                            
                        }
                        cnfe.printStackTrace();
                        throw new ParserException(info.chainToken, 
                            "Class not found during chain type check: " + 
                            cnfe.getMessage());
                    }
                    if (! matches) {
                        throw new ParserException(info.chainToken, 
                            "AST type of element must match AST type " +
                            "of containing alternative. Containing alternative: " + info.name + 
                            ", alternative AST type: " + astTypeAlternative + "; " +
                            "element AST type: " + astTypeElement + ".");
                    } else {
                        useChaining = true;
                        chainedElement = currentParam;
                        if ( chainedElement == null ) {
                            throw new NullPointerException("ChainedElement is null.");
                        }
                    }
                } // end: check whether to use chaining or not
                
                // now generate descent into child nodes 
                Iterator childIt = relevantChildList.iterator();
                List children = new LinkedList();
                while (childIt.hasNext()) {
                    ParamInfo currentParam = (ParamInfo) childIt.next();
                    ElemInfo eInfo = currentParam.referencedElement;
                    
                    String actualParameterList = buildActualParameterList(children, macros, "ast");
                    String formalParameterList = buildFormalParameterList(children, macros, "ast");
                    children.add(currentParam);
                    
                    boolean useCustomHeritage = eInfo.customHeritage;
                    boolean useTreeGeneration = eInfo.makeTree;
                    String macroSuffix = null;
                    if ( useTreeGeneration ) {
                        macroSuffix = "_makeTree";
                    } else if ( useCustomHeritage ) {
                        macroSuffix = "_customHeritage";
                    } else {
                        macroSuffix = "_defaultHeritage";
                    }

                    if ( eInfo.isList() ) {
                        macros.apply(file, "LAttrEvalAdapterCaseBodyList" + macroSuffix,
                            new String[] { eInfo.name, info.name, eInfo.type, currentParam.paramType, actualParameterList });
                    } else {
                        macros.apply(file, "LAttrEvalAdapterCaseBodyNode" + macroSuffix,
                            new String[] { eInfo.name, info.name, eInfo.type, currentParam.paramType, actualParameterList });
                    }
                    
                    if ( useCustomHeritage || useTreeGeneration ) {
                        // generate "descend into" method
                        if ( useTreeGeneration ) {
                            String astNodeType = eInfo.getReferencedAstType(prods, tokens);
                            
                            macros.apply(nested, "LAttrEvalAdapterComputeHeritageHeaderIntro" +
                                "_makeTree", new String[] { info.name, eInfo.name, eInfo.type, astNodeType});
                        } else {
                            macros.apply(nested, "LAttrEvalAdapterComputeHeritageHeaderIntro",
                            new String[] { info.name, eInfo.name, eInfo.type });                           
                        }
                        nested.write(formalParameterList);
                        macros.apply(nested, "LAttrEvalAdapterComputeHeritageHeaderTail");
                        macros.apply(nested, "LAttrEvalAdapterComputeHeritageBodyHeader");
                        macros.apply(nested, "LAttrEvalAdapterComputeHeritageBodyTail");
                    }
                } // end: descent into child nodes
                
                String macroSuffix = "";
                if ( ! useChaining ) {
                    String macroSuffixDelegate = "";
                    if ( info.delegateCreateAst ) {
                        macroSuffixDelegate = "_delegateCreation";
                    }
                    // generate method call to computeAst(...)
                    macros.apply(file, "LAttrEvalAdapterCaseCreateAstIntro" + macroSuffixDelegate, 
                        new String[] { info.name, info.getAstTypeName() } );
                    file.write(buildActualParameterList(relevantChildList,
                        macros, "ast"));
                    macros.apply(file, "LAttrEvalAdapterCaseTail", 
                        new String[] {info.name, returnType});
                        
                    
                    // generate computeAst(...) method
                    macros.apply(nested, "LAttrEvalAdapterComputeAstHeaderJavadocIntro" + macroSuffixDelegate,
                        new String[] { info.name, info.belongsTo.name }
                    );
                    nested.write(buildParameterJavadoc(relevantChildList, macros, 
                        "LAttrEvalAdapterJavadocParam_list",
                        "LAttrEvalAdapterJavadocParam_tree",
                        "LAttrEvalAdapterJavadocParam_node",
                        "ast"));
                    macros.apply(nested, "LAttrEvalAdapterComputeAstHeaderJavadocTail",
                        new String[] { info.getAstTypeName(), info.belongsTo.getAstTypeName() }
                    );

                    macros.apply(nested, "LAttrEvalAdapterComputeAstHeader" + macroSuffixDelegate,
                        new String[] {
                            info.name, info.getAstTypeName(), info.belongsTo.getAstTypeName(),
                            info.belongsTo.name
                        }
                    );
                    nested.write(buildFormalParameterList(relevantChildList,
                        macros, "ast"));

                    macros.apply(nested, "LAttrEvalAdapterComputeAstHeaderTail");
                    macros.apply(nested, "LAttrEvalAdapterComputeAstBodyIntro", new String[] {
                        info.getAstTypeName()
                    } );
                    macros.apply(nested, "LAttrEvalAdapterComputeAstBodyTail");
                    nested.flush();
                    file.write(nestedCAW.toString());                
                    nestedCAW.reset();
                } else {
                    if ( chainedElement == null ) {
                        throw new NullPointerException("ChainedElement is null and AST node creation not delegated.");
                    }
                    List params = new LinkedList();
                    params.add(chainedElement);
                    
                    String paramName = buildParameterList(params, macros,
                        "LAttrEvalAdapterAstNodeVar_list", 
                        "LAttrEvalAdapterAstNodeVar_tree", 
                        "LAttrEvalAdapterAstNodeVar_node",
                        "ast");
                    macros.apply(file, "LAttrEvalAdapterCaseCreateAst_chained", new String[] {
                        info.getAstTypeName(), paramName
                        }
                    );
                    macros.apply(file, "LAttrEvalAdapterCaseTail_chained", new String[] {info.name, returnType});
                }
            }
            
            // generate caseXxx methods for tokens
            for(Iterator i = tokenList.iterator(); i.hasNext();) {
                String tokName = (String) i.next();
                TokenInfo info = (TokenInfo) tokens.get(tokName);
//                if ( info.skipTransform ) {
//                    continue;
//                }
                String astTypeName = info.getAstTypeName();
                if ( astTypeName.equals(TokenInfo.AST_DEFAULT ) ) {
                    macros.apply(file, "LAttrEvalAdapterCaseToken_default",
                             new String[] { tokName, astTypeName });
                } else {
                    macros.apply(file, "LAttrEvalAdapterCaseToken_custom",
                             new String[] { tokName, astTypeName });
                }
            }

            
            macros.apply(file, "LAttrEvalAdapterTail", null);            
        } catch(IOException e) {
          throw new RuntimeException("An error occured while writing to " + absOutFileName );
        }

        try {
            file.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }  
    
    private String buildParameterList(List paramInfo, MacroExpander macEx,
            String macroForList, String macroForTree, String macroForNode, 
            String prefix) throws IOException {
        CharArrayWriter caw = new CharArrayWriter(32768);
        BufferedWriter writer = new BufferedWriter(caw);
        
        Iterator itParam = paramInfo.iterator();
        while ( itParam.hasNext() ) {
            ParamInfo pi = (ParamInfo) itParam.next();
            String paramName = pi.paramName;
            String paramType = pi.paramType;
            if ( pi.isList ) {
                macEx.apply(writer, macroForList,
                    new String[] { paramName, "List" /* "("  + paramType + ")" */, prefix });
            } else if ( pi.makeTree ) {
                macEx.apply(writer, macroForTree,
                    new String[] { paramName, paramType, prefix });
            } else {
                macEx.apply(writer, macroForNode,
                    new String[] { paramName, paramType, prefix });
            }
        }
        writer.flush();
        String result = caw.toString();        
        return result;
    }
    
    private String buildFormalParameterList(List paramInfo, MacroExpander macEx,
            String prefix) throws IOException {
        return buildParameterList(paramInfo, macEx, 
            "LAttrEvalAdapterFormalParam_list",
            "LAttrEvalAdapterFormalParam_tree",
            "LAttrEvalAdapterFormalParam_node",
            prefix);
    }

    private String buildActualParameterList(List paramInfo, MacroExpander macEx,
            String prefix) throws IOException {
        return buildParameterList(paramInfo, macEx, 
            "LAttrEvalAdapterActualParam_list",
            "LAttrEvalAdapterActualParam_tree",
            "LAttrEvalAdapterActualParam_node",
            prefix);
    }
    
    private String buildParameterJavadoc(List paramInfo, MacroExpander macEx,
            String macroForList, String macroForTree, String macroForNode, 
            String prefix) throws IOException {
        CharArrayWriter caw = new CharArrayWriter(32768);
        BufferedWriter writer = new BufferedWriter(caw);
        
        Iterator itParam = paramInfo.iterator();
        while ( itParam.hasNext() ) {
            ParamInfo pi = (ParamInfo) itParam.next();
            String paramName = pi.paramName;
            String paramType = pi.paramType;
            if ( pi.isList ) {
                macEx.apply(writer, macroForList,
                    new String[] { paramName, paramType, prefix });
            } else if ( pi.makeTree ) {
                macEx.apply(writer, macroForTree,
                    new String[] { paramName, paramType, prefix });
            } else {
                macEx.apply(writer, macroForNode,
                    new String[] { paramName, paramType, prefix });
            }
        }
        writer.flush();
        String result = caw.toString();        
        return result;
    } 

}


class TokenInfo {
    boolean skipTransform = false;
    private String astTypeName;

    public static final String AST_DEFAULT = "String";

    public void setAstTypeName(String newAstTypeName) {
        if ( newAstTypeName == null ) {
            astTypeName = AST_DEFAULT; // "UnknownTokenAstType";
        } else {
            astTypeName = newAstTypeName;
        }
    }
    public String getAstTypeName() {
        return astTypeName;
    }      
}
    
class TokenInfoCast implements Cast {
    public static final TokenInfoCast instance = new TokenInfoCast();
    private TokenInfoCast() { }

    public Object cast(Object o) {
        return (TokenInfo) o;
    }    
}

class ElemInfo {
    final static int OPER_NONE = 0;
    final static int OPER_STAR = 1;
    final static int OPER_QMARK = 2;
    final static int OPER_PLUS = 3;    
    int operator;
    
    public boolean isList() {
        return ( (operator == OPER_STAR) || (operator == OPER_PLUS) );
    }
    
    final static int KIND_UNKNOWN = -1;
    final static int KIND_TOKEN = 0;
    final static int KIND_PRODUCTION = 1;
    int kind;    

    String name;
    String type;
    
    boolean customHeritage = false;
    boolean makeTree = false;
    
    ProdInfo belongsToProduction = null;
    boolean skipTransform = false;
    
    /**
     * AST type of referenced production or token
     */
    public String getReferencedAstType(Map prods, Map tokens) {
        String referencedAstType = null;
        switch ( this.kind ) {
            case KIND_TOKEN:
                TokenInfo refToken = (TokenInfo) tokens.get(type);                
                if ( refToken == null ) {
                    System.out.println("No TokenInfo for " + type);
                    referencedAstType = "<UNKNOWN>";
                } else {
                    referencedAstType = refToken.getAstTypeName();    
                }
                break;
            case KIND_PRODUCTION:
                ProdInfo refProd = (ProdInfo) prods.get(type);
                if ( refProd == null ) {
                    System.out.println("No ProdInfo for " + type);
                    referencedAstType = "<UNKNOWN>";
                } else {                    
                    referencedAstType = refProd.getAstTypeName();
                }
                break;
            default:
                throw new RuntimeException("Unimplemented element type.");
        }
        return referencedAstType;
    }
    
//    /**
//     * If kind == KIND_PRODUCTION, this attribute contains a reference to the
//     * ProdInfo describing the production this element is referencing.
//     */
//    ProdInfo referencedProduction = null;
//    
//    /**
//     * If kind == KIND_TOKEN, this attribute contains a reference to the
//     * TokenInfo describing the token this element is referencing.
//     */
//    TokenInfo referencedToken = null;
}

class ElemInfoCast implements Cast {
    final static ElemInfoCast instance = new ElemInfoCast();

    private ElemInfoCast()
    {}

    public Object cast(Object o) {
      return (ElemInfo) o;
    }
}

class AltInfo {
    String name;
    ProdInfo belongsTo;             // references the production this alt is part of
    boolean isChainRule;            // indicates whether this is a chain rule and the 
                                    // ast of the (only) child should be used as ast of
                                    // this alternative
    TChain chainToken = null;
    
    boolean delegateCreateAst;      // if true, the AST node creation is delegated to
                                    // computeAstFor_Xxx method (this is used for complex
                                    // nodes or nodes which type depends on parameters to
                                    // computeAstFor_Xxx
    
    private String astTypeName;

    final List elems = new TypedLinkedList(ElemInfoCast.instance);
    
    public void setAstTypeName(String newAstTypeName) {
        astTypeName = newAstTypeName;
    }
    
    /**
     * Returns AST type name for an alternative or, if none specified,
     * the return type of the production this alternative is part of.
     */
    public String getAstTypeName() {
        if ( astTypeName == null ) {
            return belongsTo.getAstTypeName();
        } else {
            return astTypeName;
        }
    }
}

class AltInfoCast implements Cast {
    final static AltInfoCast instance = new AltInfoCast();

    private AltInfoCast() { }

    public Object cast(Object o) {
      return (AltInfo) o;
    }
}

class ProdInfo {      
      String name;
      // String type;
      boolean skipTransform = false;    // ignore this production during AST
                                        // generation, simply don't generate any
                                        // ast generation code for it.
      private String astTypeName;
      
      public void setAstTypeName(String newAstTypeName) {
          // System.out.println("ProdInfo '" + name + "': setting ast type name");
          if ( newAstTypeName == null ) {
              this.astTypeName = "Object"; // "UnknownProdAstType";
          } else {
              this.astTypeName = newAstTypeName;
          }
      }
      
      public String getAstTypeName() {
          return astTypeName;
      }
      
}

class ProdInfoCast implements Cast {
      final static ProdInfoCast instance = new ProdInfoCast();
      
      private ProdInfoCast() { }
      
      public Object cast(Object o) {
          return (ProdInfo) o;
      }
}

/**
 * Data container for type names of a formal parameter to method createAst.
 
 */
class ParamInfo {
    /**
     * Indicates whether the parameter is a list of objects
     */
    public boolean isList;
    /**
     * Indicates whether the parameter should be converted into a tree of ast
     * nodes instead of a list.
     */
    public boolean makeTree;
    
    /**
     * Type of the child node this parameter is one instance of (single node)
     * or a List of instances of (List).
     */
    public String paramName;
    /**
     * AST type of the parameter. This is the type of the parameter itself for
     * single instances, or the type of all instances in the list for lists
     * of objects
     */
    public String paramType;
    
    public ElemInfo referencedElement = null;
    
    public ParamInfo(boolean isList, boolean makeTree, String paramName, String paramType, ElemInfo referencedElement) {
        this.isList = isList;
        this.makeTree = makeTree;
        this.paramName = paramName;
        this.paramType = paramType;
        this.referencedElement = referencedElement;
    }
    
   
}



