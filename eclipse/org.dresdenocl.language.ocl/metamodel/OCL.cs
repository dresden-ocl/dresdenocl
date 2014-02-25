SYNTAXDEF ocl
FOR <http://www.tu-dresden.de/ocl20/pivot/language/ocl> <OCL.genmodel>
START PackageDeclarationWithNamespaceCS, PackageDeclarationWithoutNamespaceCS

IMPORTS {
	pivotmodel : <http://www.tu-dresden.de/ocl20/pivot/2007/pivotmodel> <../org.dresdenocl.pivotmodel/model/pivotmodel.genmodel>
}

OPTIONS {
	generateCodeFromGeneratorModel = "true";
	disableDebugSupport = "true";
	disableLaunchSupport = "true";
	reloadGeneratorModel = "true";
	tokenspace = "1";
	// -----------------------------
	// set this to true but check out OclEditor.setFocus() method
	// see documentation for more information
	overrideEditor = "false";
	//------------------------------
	overrideTextResource = "false";
	overrideCodeCompletionHelper = "true";
	overrideManifest = "false";
	overridePluginXML = "false";
	usePredefinedTokens = "false";
	overrideHyperlink = "false";
	overrideDefaultResolverDelegate = "false";
	resourcePluginID = "org.dresdenocl.language.ocl.resource.ocl";
	resourceUIPluginID = "org.dresdenocl.language.ocl.resource.ocl.ui";
	srcFolder = "src";
	srcGenFolder = "src-gen";
}

TOKENS {
	DEFINE SL_COMMENT 				$ '--'(~('\n'|'\r'|'\uffff'))* $;
	DEFINE ML_COMMENT 				$ '/*'.*'*/'$;
	DEFINE NAVIGATION_OPERATOR		$ '.' | '->' $;
	DEFINE ADDITIVE_OPERATOR		$ '+' | '-' $;
	DEFINE MULT_OPERATOR			$ '*' | '/' | '%' $;
	DEFINE RELATIONAL_OPERATOR		$ '<' | '>' | '<=' | '>='$;
	DEFINE EQUALITY_OPERATOR		$ '=' $;
	DEFINE NEQUALITY_OPERATOR		$ '<>' $;
	DEFINE NOT_OPERATOR				$ 'not' $;
	DEFINE AND_OPERATOR				$ 'and' $;
	DEFINE OR_OPERATOR				$ 'or' $;
	DEFINE XOR_OPERATOR				$ 'xor' $;
	DEFINE IMPLIES_OPERATOR			$ 'implies' $;
	DEFINE IS_MARKED_PRE			$ '@pre'$;
	DEFINE BOOLEAN_LITERAL			$ 'true' | 'false' $;
	DEFINE COLLECTION_TYPES			$ 'Set' | 'Bag' | 'Sequence' | 'Collection' | 'OrderedSet' $;
	DEFINE ITERATOR_NAME			$ 'select' | 'reject' | 'forAll' | 'collect' | 'any' | 'exists' | 'one' | 'isUnique' | 'collectNested' | 'sortedBy' | 'closure' $;
	DEFINE STATIC					$ 'static'$;
	DEFINE INTEGER_0				$ '0'+ ('0'..'9')$;
	DEFINE INTEGER_LITERAL			$ ('1'..'9') ('0'..'9')* | '0'$;
	DEFINE SIMPLE_NAME				$ ('A'..'Z'|'a'..'z'|'_') ('A'..'Z'|'a'..'z'|'0'..'9'|'_')*$;
	DEFINE WHITESPACE 				$(' '|'\t'|'\f')$;
	DEFINE LINEBREAKS 				$('\r\n'|'\r'|'\n')$;
}

TOKENSTYLES {
	"ML_COMMENT" COLOR #008000, ITALIC;
	"SL_COMMENT" COLOR #008000, ITALIC;
	"BOOLEAN_LITERAL" COLOR #800040, BOLD;
	"STATIC" COLOR #800040, BOLD;
	"COLLECTION_TYPES" COLOR #800040, BOLD;
}

RULES {

	SimpleNameCS						::= simpleName[ITERATOR_NAME] | simpleName[SIMPLE_NAME];
	
	PackageDeclarationWithNamespaceCS	::= "package" nestedNamespace !0 (contextDeclarations)* !0 "endpackage";
	
	PackageDeclarationNestedNamespaceCS	::= namespace[SIMPLE_NAME] ("::" nestedNamespace)?;
	
	PackageDeclarationWithoutNamespaceCS::= contextDeclarations*;
	
	@Foldable
	OperationContextDeclarationCS		::= "context" operation !0 prePostOrBodyDeclarations+;
	
	@Foldable
	AttributeContextDeclarationCS		::= "context" typeName (":" type)? !0 initOrDeriveValue initOrDeriveValue?;
	
	@Foldable
	ClassifierContextDeclarationCS		::= "context" typeName !0 invariantsAndDefinitions+;
	
	@Foldable
	InitValueCS							::= "init" #0 ":" oclExpression;
	
	@Foldable
	DeriveValueCS						::= "derive" #0 ":" oclExpression;
	
	@Foldable
	InvariantExpCS						::= "inv" name? #0 ":" oclExpression;
	
	@Foldable
	DefinitionExpCS						::= (static[STATIC])? "def" #0 ":" definitionExpPart;
	
	DefinitionExpPropertyCS				::= variableDeclaration;
	
	DefinitionExpOperationCS			::= operation equal[EQUALITY_OPERATOR] oclExpression;
	
	@Foldable										
	PreConditionDeclarationCS			::= "pre" (name)? #0 ":" oclExpression;
	
	@Foldable
	PostConditionDeclarationCS			::= "post" (name)? #0 ":" oclExpression;
	
	@Foldable
	BodyDeclarationCS					::= "body" (name)? #0 ":" oclExpression;

	OperationDefinitionInContextCS		::= typeName #0 "::" #0 operation[SIMPLE_NAME] #0 "(" #0 (parameters (#0 "," parameters)*)? #0 ")" (":" returnType)?;
	
	OperationDefinitionInDefCS			::= operation[SIMPLE_NAME] "(" (parameters ("," parameters)*)? ")" (":" returnType)?;
	
	ParameterCS							::= parameter[SIMPLE_NAME] ":" parameterType;

	
	// *** OperationCallExpCS: binary (infix) operations [A]***
	@Operator(type="binary_left_associative", weight="4", superclass="OclExpressionCS")
	LogicalImpliesOperationCallExpCS	::= source operationName[IMPLIES_OPERATOR] target;
	
	@Operator(type="binary_left_associative", weight="5", superclass="OclExpressionCS")
	LogicalXorOperationCallExpCS		::= source operationName[XOR_OPERATOR] target;
	
	@Operator(type="binary_left_associative", weight="6", superclass="OclExpressionCS")
	LogicalOrOperationCallExpCS			::= source operationName[OR_OPERATOR] target;
	
	@Operator(type="binary_left_associative", weight="7", superclass="OclExpressionCS")
	LogicalAndOperationCallExpCS		::= source operationName[AND_OPERATOR] target;
	
	@Operator(type="binary_left_associative", weight="8", superclass="OclExpressionCS")
	EqualityOperationCallExpCS			::= source (operationName[EQUALITY_OPERATOR] | operationName[NEQUALITY_OPERATOR]) target;
	
	@Operator(type="binary_left_associative", weight="9", superclass="OclExpressionCS")
	RelationalOperationCallExpCS		::= source operationName[RELATIONAL_OPERATOR] target;
	
	@Operator(type="binary_left_associative", weight="11", superclass="OclExpressionCS")
	AdditiveOperationCallExpCS			::= source operationName[ADDITIVE_OPERATOR] target;
	
	@Operator(type="binary_left_associative", weight="12", superclass="OclExpressionCS")
	MultOperationCallExpCS				::= source operationName[MULT_OPERATOR] target;
	
	
	// *** OperationCallExpCS: unary (prefix) operations [H] ***
	@Operator(type="unary_prefix", weight="13", superclass="OclExpressionCS")
	UnaryOperationCallExpCS				::= operationName[ADDITIVE_OPERATOR] #0 target;
	
	@Operator(type="unary_prefix", weight="13", superclass="OclExpressionCS")
	LogicalNotOperationCallExpCS		::= operationName[NOT_OPERATOR] #0 target;
	
	
	// *** OperationCallExpCS: normal operation call [C] ***
	@Operator(type="unary_postfix", weight="14", superclass="OclExpressionCS")
	NavigationCallExp					::= source #0 navigationOperator[NAVIGATION_OPERATOR] #0 featureCalls (#0 navigationOperator[NAVIGATION_OPERATOR] #0 featureCalls)*;
	
	ImplicitOperationCallCS				::= (operationName[EQUALITY_OPERATOR]
											|operationName[NEQUALITY_OPERATOR]
											|operationName[NOT_OPERATOR]
											|operationName[AND_OPERATOR]
											|operationName[OR_OPERATOR]
											|operationName[XOR_OPERATOR]
											|operationName[IMPLIES_OPERATOR]
											|operationName[SIMPLE_NAME]
											) #0 "(" (#0 arguments ("," arguments)*)? #0 ")";
	
	ImplicitPropertyCallCS				::= property[SIMPLE_NAME] (#0 isMarkedPre[IS_MARKED_PRE])?;
	
	IteratorExpCS						::= iteratorName[ITERATOR_NAME] #0 "(" (#0 iteratorVariables (#0 "," iteratorVariables)? "|")? !1 bodyExpression !0 ")";
	
	IterateExpCS						::= "iterate" #0 "(" (iteratorVariable #0 ";")? resultVariable "|" bodyExpression #0 ")";
	
	IteratorExpVariableCS				::= variableName (":" typeName)?;
	
	
	// *** TypeCS: pathName, tuple type or collection type ***	
	TupleTypeCS							::= "Tuple" "(" #0 variableDeclarationList? #0 ")";
	
	CollectionTypeIdentifierCS			::= typeName[COLLECTION_TYPES] ( #0 "(" #0 genericType #0 ")")?;

	TypeModelElementCS					::= modelElement;
	
	
	// *** VariableDeclarationWithoutInitCS ***
	VariableDeclarationWithoutInitCS	::= variableName ":" typeName;
	
	VariableDeclarationWithoutInitListCS::= variableDeclarations (#0 "," variableDeclarations)*;
	
	
	// *** VariableDeclarationWithInitCS ***
	VariableDeclarationWithInitCS		::= variableName (":" typeName)? equal[EQUALITY_OPERATOR] initialization;
	
	VariableDeclarationWithInitListCS	::= variableDeclarations (#0 "," variableDeclarations)*;
	
	
	// *** OperationCallExpCS: implicit source expression [D] ***
	@Operator(type="primitive", weight="20", superclass="OclExpressionCS")
	OperationCallOnSelfExpCS			::= (operationName[EQUALITY_OPERATOR]
											|operationName[NEQUALITY_OPERATOR]
											|operationName[NOT_OPERATOR]
											|operationName[AND_OPERATOR]
											|operationName[OR_OPERATOR]
											|operationName[XOR_OPERATOR]
											|operationName[IMPLIES_OPERATOR]
											|operationName[SIMPLE_NAME]
											) #0 (isMarkedPre[IS_MARKED_PRE] #0)? "(" (#0 arguments ("," arguments)*)? #0 ")";
	
	// *** OperationCallExpCS: static operation call [H] ***
	@Operator(type="primitive", weight="20", superclass="OclExpressionCS")
	StaticOperationCallExpCS			::= typeName #0 "::" #0 operationName[SIMPLE_NAME] #0 "(" (#0 arguments ("," arguments)*)? #0 ")";
	
	@Operator(type="primitive", weight="20", superclass="OclExpressionCS")	
	ModelElementCS							::= pathName;
	
	PathNameSimpleCS						::= namedElement[SIMPLE_NAME];
	
	PathNamePathCS							::= (pathName #0 "::" #0)+  pathName;		
	
	NamedElementCS							::= namedElement[SIMPLE_NAME];
	
	@Operator(type="primitive", weight="20", superclass="OclExpressionCS")
	TupleLiteralExpCS					::= "Tuple" "{" variableDeclarations "}";
		
	@Operator(type="primitive", weight="20", superclass="OclExpressionCS")
	IfExpCS								::= "if" condition !0 "then" !1 thenBranch !0 "else" !1 elseBranch !0 "endif";
	
	@Operator(type="primitive", weight="20", superclass="OclExpressionCS")
	CollectionLiteralExpCS				::= collectionType "{" (collectionLiteralParts (#0 "," collectionLiteralParts)*)? "}";
	
	CollectionRangeCS					::= from #0 ".." #0 to;
	
	CollectionLiteralPartsOclExpCS		::= oclExpression;
	
	
	@Operator(type="primitive", weight="20", superclass="OclExpressionCS")
	CollectionTypeLiteralExpCS			::= collectionType;
	
	@Operator(type="primitive", weight="20", superclass="OclExpressionCS")
	TupleTypeLiteralExpCS				::= tupleType;
	
	@Operator(type="primitive", weight="20", superclass="OclExpressionCS")
	PropertyCallOnSelfExpCS				::= property[SIMPLE_NAME] #0 isMarkedPre[IS_MARKED_PRE];
	
	
	@Operator(type="primitive", weight="20", superclass="OclExpressionCS")
	LetExpCS							::= "let" variableDeclarations (#0 "," variableDeclarations)* "in" !1 oclExpression !0;
	
	
	@Operator(type="primitive", weight="20", superclass="OclExpressionCS")
	RealLiteralExpCS					::= intValue[INTEGER_LITERAL] #0 navigationOperator[NAVIGATION_OPERATOR] #0 (realValue[INTEGER_0] | realValue[INTEGER_LITERAL]);
	
	@Operator(type="primitive", weight="20", superclass="OclExpressionCS")
	IntegerLiteralExpCS					::= integerLiteral[INTEGER_LITERAL];
	
	@Operator(type="primitive", weight="20", superclass="OclExpressionCS")
	BooleanLiteralExpCS					::= booleanLiteral[BOOLEAN_LITERAL];
	
	@Operator(type="primitive", weight="20", superclass="OclExpressionCS")
	StringLiteralExpCS					::= stringLiteral['\'', '\''];
	
	@Operator(type="primitive", weight="20", superclass="OclExpressionCS")
	InvalidLiteralExpCS					::= "invalid";
	
	@Operator(type="primitive", weight="20", superclass="OclExpressionCS")
	NullLiteralExpCS					::= "null";
			
	@Operator(type="primitive", weight="20", superclass="OclExpressionCS")
	BracketExpCS						::= "(" #0 oclExpression #0 ")";

}