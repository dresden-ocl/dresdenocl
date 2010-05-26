SYNTAXDEF ocl
FOR <http://www.tu-dresden.de/ocl20/pivot/language/ocl> <OCL.genmodel>
START PackageDeclarationWithNamespaceCS, PackageDeclarationWithoutNamespaceCS

IMPORTS {
	pivotmodel : <http://www.tu-dresden.de/ocl20/pivot/2007/pivotmodel> <../tudresden.ocl20.pivot.pivotmodel/model/pivotmodel.genmodel>
}

OPTIONS {
	reloadGeneratorModel = "true";
	tokenspace = "1";
	overrideTextResource = "false";
	overrideLocationMap = "false";
	overrideManifest = "false";
	usePredefinedTokens = "false";
}

TOKENS {
	DEFINE SL_COMMENT 				$ '--'(~('\n'|'\r'|'\uffff'))* $ COLLECT IN comments;
	DEFINE ML_COMMENT 				$ '/*'.*'*/'$ COLLECT IN comments;
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
	DEFINE ITERATOR_NAME			$ 'select' | 'reject' | 'collect' | 'forAll' | 'any' | 'exists' | 'one' | 'isUnique' | 'collectNested' | 'sortedBy' $;
	DEFINE STATIC					$ 'static'$;
	DEFINE INTEGER_LITERAL			$ ('1'..'9') ('0'..'9')* | '0'$;
	DEFINE REAL_LITERAL 			$ (('1'..'9') ('0'..'9')* | '0') '.' ('0'..'9')+$;
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

	SimpleNameCS						::= simpleName[SIMPLE_NAME];
	
	PathNameCS							::= simpleName ("::" pathName)?;
	
	PackageDeclarationWithNamespaceCS	::= "package" nestedNamespace (contextDeclarations)* "endpackage";
	
	PackageDeclarationNestedNamespaceCS	::= namespace[SIMPLE_NAME] ("::" nestedNamespace)?;
	
	PackageDeclarationWithoutNamespaceCS::= contextDeclarations*;
	
	OperationContextDeclarationCS		::= "context" operation prePostOrBodyDeclarations+;
	
	AttributeContextDeclarationCS		::= "context" typeName "::" attributeName ":" type initOrDeriveValue+;
	
	ClassifierContextDeclarationCS		::= "context" typeName invariantsAndDefinitions+;
	
	InitValueCS							::= "init" ":" oclExpression;
	
	DeriveValueCS						::= "derive" ":" oclExpression;
	
	InvariantExpCS						::= "inv" name? ":" oclExpression;
	
	DefinitionExpCS						::= (static[STATIC])? "def" ":" definitionExpPart;
	
	DefinitionExpPropertyCS				::= variableDeclaration;
	
	DefinitionExpOperationCS			::= operation "=" oclExpression;
											
	PreConditionDeclarationCS			::= "pre" (name)? ":" oclExpression;
	
	PostConditionDeclarationCS			::= "post" (name)? ":" oclExpression;
	
	BodyDeclarationCS					::= "body" (name)? ":" oclExpression;

	OperationDefinitionInContextCS		::= typeName "::" operation[SIMPLE_NAME] "(" (parameters ("," parameters)*)? ")" (":" returnType)?;
	
	OperationDefinitionInDefCS			::= operation[SIMPLE_NAME] "(" (parameters ("," parameters)*)? ")" (":" returnType)?;
	
	ParameterCS							::= parameter[SIMPLE_NAME] ":" parameterType;
	
	@operator(type="unary_prefix", weight="2", identifier="OclExpressionCS")
	LetExpCS							::= "let" variableDeclarations ("," variableDeclarations)* "in" oclExpression;
	
	// *** OperationCallExpCS: binary (infix) operations [A]***
	@operator(type="binary_left_associative", weight="4", identifier="OclExpressionCS")
	LogicalImpliesOperationCallExpCS	::= source operationName[IMPLIES_OPERATOR] target;
	
	@operator(type="binary_left_associative", weight="5", identifier="OclExpressionCS")
	LogicalXorOperationCallExpCS		::= source operationName[XOR_OPERATOR] target;
	
	@operator(type="binary_left_associative", weight="6", identifier="OclExpressionCS")
	LogicalOrOperationCallExpCS			::= source operationName[OR_OPERATOR] target;
	
	@operator(type="binary_left_associative", weight="7", identifier="OclExpressionCS")
	LogicalAndOperationCallExpCS		::= source operationName[AND_OPERATOR] target;
	
	@operator(type="binary_left_associative", weight="8", identifier="OclExpressionCS")
	EqualityOperationCallExpCS			::= source (operationName[EQUALITY_OPERATOR] | operationName[NEQUALITY_OPERATOR]) target;
	
	@operator(type="binary_left_associative", weight="9", identifier="OclExpressionCS")
	RelationalOperationCallExpCS		::= source operationName[RELATIONAL_OPERATOR] target;
	
	@operator(type="binary_left_associative", weight="11", identifier="OclExpressionCS")
	AdditiveOperationCallExpCS			::= source operationName[ADDITIVE_OPERATOR] target;
	
	@operator(type="binary_left_associative", weight="12", identifier="OclExpressionCS")
	MultOperationCallExpCS				::= source operationName[MULT_OPERATOR] target;
	
	
	// *** OperationCallExpCS: unary (prefix) operations [H] ***
	@operator(type="unary_prefix", weight="13", identifier="OclExpressionCS")
	UnaryOperationCallExpCS				::= operationName[ADDITIVE_OPERATOR] target;
	
	@operator(type="unary_prefix", weight="13", identifier="OclExpressionCS")
	LogicalNotOperationCallExpCS		::= operationName[NOT_OPERATOR] target;
	
	
	// *** OperationCallExpCS: normal operation call [C] ***
	@operator(type="unary_postfix", weight="14", identifier="OclExpressionCS")
	NavigationCallExp					::= source navigationOperator[NAVIGATION_OPERATOR] featureCalls (navigationOperator[NAVIGATION_OPERATOR] featureCalls)*;
	
	ImplicitOperationCallCS				::= operationName[SIMPLE_NAME] "(" (arguments ("," arguments)*)? ")";
	
	ImplicitPropertyCallCS				::= property[SIMPLE_NAME];
	
	ImplicitIteratorExpCS				::= iteratorName[ITERATOR_NAME] "(" (iteratorVariables ("," iteratorVariables)? "|")? bodyExpression ")";
	
	IteratorExpVariableCS				::= variableName (":" typeName)?;
	
	
	// *** OperationCallExpCS: implicit source expression [D] ***
	@operator(type="primitive", weight="20", identifier="OclExpressionCS")
	OperationCallWithImlicitSourceExpCS	::= operationName[SIMPLE_NAME]  "(" arguments* ")";
		
	
	// *** TypeCS: pathName, tuple type or collection type ***
	TypePathNameSimpleCS				::= typeName[SIMPLE_NAME];
	
	TypePathNameNestedCS				::= namespace[SIMPLE_NAME] #0 "::" #0 typePathName;
	
	TupleTypeCS							::= "TupleType" "(" variableDeclarationList? ")";
	
	CollectionTypeIdentifierCS			::= typeName[COLLECTION_TYPES] ("(" genericType ")")?;
	
	
	// *** VariableDeclarationWithoutInitCS ***
	VariableDeclarationWithoutInitCS	::= variableName ":" typeName;
	
	VariableDeclarationWithoutInitListCS::= variableDeclarations ("," variableDeclarations)*;
	
	
	// *** VariableDeclarationWithInitCS ***
	VariableDeclarationWithInitCS		::= variableName (":" typeName)? equal[EQUALITY_OPERATOR] initialization;
	
	VariableDeclarationWithInitListCS	::= variableDeclarations ("," variableDeclarations)*;
	
	
	@operator(type="primitive", weight="20", identifier="OclExpressionCS")
	TupleLiteralExpCS					::= "Tuple" "{" variableDeclarations "}";
		
	@operator(type="primitive", weight="20", identifier="OclExpressionCS")
	IfExpCS								::= "if" condition "then" thenBranch "else" elseBranch "endif";
	
	@operator(type="primitive", weight="20", identifier="OclExpressionCS")
	CollectionLiteralExpCS				::= collectionType "{" (collectionLiteralParts ("," collectionLiteralParts)*)? "}";
	
	CollectionRangeCS					::= from #0 ".." #0 to;
	
	CollectionLiteralPartsOclExpCS		::= oclExpression;

	
	@operator(type="primitive", weight="20", identifier="OclExpressionCS")
	IntegerLiteralExpCS					::= integerLiteral[INTEGER_LITERAL];
	
	@operator(type="primitive", weight="20", identifier="OclExpressionCS")
	RealLiteralExpCS					::= realLiteral[REAL_LITERAL];
	
	@operator(type="primitive", weight="20", identifier="OclExpressionCS")
	BooleanLiteralExpCS					::= booleanLiteral[BOOLEAN_LITERAL];
	
	@operator(type="primitive", weight="20", identifier="OclExpressionCS")
	StringLiteralExpCS					::= stringLiteral['\'', '\''];
	
	@operator(type="primitive", weight="20", identifier="OclExpressionCS")
	VariableOrStaticPropertyOrEnumLiteralExpCS ::= typedElement[SIMPLE_NAME];
		
	@operator(type="primitive", weight="20", identifier="OclExpressionCS")
	BracketExpCS						::= "(" oclExpression ")";

}