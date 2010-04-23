SYNTAXDEF ocl
FOR <http://www.tu-dresden.de/ocl20/pivot/language/ocl> <OCL.genmodel>
START PackageDeclarationCS

OPTIONS {
	reloadGeneratorModel = "true";
	tokenspace = "1";
	overrideTextResource = "false";
}

TOKENS {
	DEFINE ADDITIVE_OPERATOR		$ '+' | '-' $;
	DEFINE MULT_OPERATOR			$ '*' | '/' | '%' $;
	DEFINE RELATIONAL_OPERATOR		$ '<' | '>' | '<=' | '>='$;
	DEFINE EQUALITY_OPERATOR		$ '=' | '<>' $;
	DEFINE NOT_OPERATOR				$ 'not' $;
	DEFINE AND_OPERATOR				$ 'and' $;
	DEFINE OR_OPERATOR				$ 'or' $;
	DEFINE XOR_OPERATOR				$ 'xor' $;
	DEFINE IMPLIES_OPERATOR			$ 'implies' $;
	DEFINE IS_MARKED_PRE			$ '@pre'$;
	DEFINE BOOLEAN_LITERAL			$ 'true' | 'false' $;
	DEFINE COLLECTION_TYPES			$ 'Set' | 'Bag' | 'Sequence' | 'Collection' | 'OrderedSet' $;
	DEFINE STATIC					$ 'static'$;
	DEFINE INTEGER_LITERAL			$('1'..'9') ('0'..'9')* | '0'$;
	DEFINE REAL_LITERAL 			$ (('1'..'9') ('0'..'9')* | '0') '.' ('0'..'9')+$;
	DEFINE SIMPLE_NAME				$ ('A'..'Z'|'a'..'z'|'_') ('A'..'Z'|'a'..'z'|'0'..'9'|'_'|'-')* $;
}

TOKENSTYLES {
	"BOOLEAN_LITERAL" COLOR #800040, BOLD;
	"STATIC" COLOR #800040, BOLD;
	"COLLECTION_TYPES" COLOR #800040, BOLD;
}

RULES {

	SimpleNameCS						::= simpleName[SIMPLE_NAME];
	
	PathNameCS							::= simpleName ("::" pathName)?;
	
	PackageDeclarationCS				::= ("package" packageName (contextDeclarations)* "endpackage")
											| (contextDeclarations*);
	
	OperationContextDeclarationCS		::= "context" operation prePostOrBodyDeclarations+;
	
	AttributeContextDeclarationCS		::= "context" typeName "::" attributeName ":" type initOrDeriveValue+;
	
	ClassifierContextDeclarationCS		::= "context" typeName invariantsAndDefinitions+;
	
	InitValueCS							::= "init" ":" oclExpression;
	
	DeriveValueCS						::= "derive" ":" oclExpression;
	
	InvariantExpCS						::= "inv" name? ":" oclExpression;
	
	DefinitionExpCS						::= (static[STATIC])? "def" name? ":" 
											(variable "=" oclExpression
											|operation "=" oclExpression);
											
	PreConditionDeclarationCS			::= "pre" (name)? ":" oclExpression;
	
	PostConditionDeclarationCS			::= "post" (name)? ":" oclExpression;
	
	BodyDeclarationCS					::= "body" (name)? ":" oclExpression;
										
	OperationCS							::= operationName "(" (parameters ("," parameters)*)? ")" (":" type)?;
	
	ParameterCS							::= parameterName ":" parameterType;
	
	// TODO: add "." and "@pre"
	//@operator(type="binary_left_associative", weight="3", identifier="OclExpressionCS")
	//PropertyCallWithSourceExpCS	::= source "." propertyName;
	
	// *** OperationCallExpCS: binary (infix) operations [A]***
	@operator(type="binary_left_associative", weight="4", identifier="OclExpressionCS")
	LogicalImpliesOperationCallExpCS	::= source operationName[IMPLIES_OPERATOR] target;
	
	@operator(type="binary_left_associative", weight="5", identifier="OclExpressionCS")
	LogicalXorOperationCallExpCS	::= source operationName[XOR_OPERATOR] target;
	
	@operator(type="binary_left_associative", weight="6", identifier="OclExpressionCS")
	LogicalOrOperationCallExpCS	::= source operationName[OR_OPERATOR] target;
	
	@operator(type="binary_left_associative", weight="7", identifier="OclExpressionCS")
	LogicalAndOperationCallExpCS	::= source operationName[AND_OPERATOR] target;
	
	@operator(type="binary_left_associative", weight="8", identifier="OclExpressionCS")
	EqualityOperationCallExpCS		::= source operationName[EQUALITY_OPERATOR] target;
	
	@operator(type="binary_left_associative", weight="9", identifier="OclExpressionCS")
	RelationalOperationCallExpCS	::= source operationName[RELATIONAL_OPERATOR] target;
	
	@operator(type="binary_left_associative", weight="11", identifier="OclExpressionCS")
	AdditiveOperationCallExpCS	::= source operationName[ADDITIVE_OPERATOR] target;
	
	@operator(type="binary_left_associative", weight="12", identifier="OclExpressionCS")
	MultOperationCallExpCS	::= source operationName[MULT_OPERATOR] target;
	
	// *** OperationCallExpCS: unary (prefix) operations [H] ***
	@operator(type="unary", weight="13", identifier="OclExpressionCS")
	UnaryOperationCallExpCS			::= operationName[ADDITIVE_OPERATOR] target;
	
	@operator(type="unary", weight="13", identifier="OclExpressionCS")
	LogicalNotOperationCallExpCS	::= operationName[NOT_OPERATOR] target;
	
	// *** OperationCallExpCS: implicit source expression [D] ***
	//@operator(type="primitive", weight="20", identifier="OclExpressionCS")
	//OperationCallWithImlicitSourceExpCS	::= operationName "(" arguments* ")";
	
	
	// *** TypeCS: pathName, tuple type or collection type ***
	TypePathNameCS					::= pathName;
	
	TupleTypeCS						::= "TupleType" "(" variableDeclarationList? ")";
	
	CollectionTypeIdentifierCS		::= collectionTypeName[COLLECTION_TYPES] ("(" genericType ")")?;
	
	
	// *** VariableDeclarationWithoutInitCS ***
	VariableDeclarationWithoutInitCS		::= variableName ":" type;
	
	VariableDeclarationWithoutInitListCS	::= variableDeclarations ("," variableDeclarations)*;
	
	
	// *** VariableDeclarationWithInitCS ***
	VariableDeclarationWithInitCS		::= variableName (":" type)? ("=" initialization)?;
	
	VariableDeclarationWithInitListCS	::= variableDeclarations ("," variableDeclarations)*;
	
	
	@operator(type="primitive", weight="20", identifier="OclExpressionCS")
	TupleLiteralExpCS			::= "Tuple" "{" variableDeclarations "}";
	
	@operator(type="primitive", weight="20", identifier="OclExpressionCS")
	LetExpCS					::= "let" variableDeclaration letExpSub;
	
	LetExpSubSubCS				::= "," variableDeclaration letExpSub;
	
	// TODO: un-commenting these rules in causes errors in the generated parser
	// LetExpSubExpressionCS		::= "in" oclExpression;
	//@operator(type="primitive", weight="10", identifier="OclExpressionCS")
	//IfExpCS							::= "if" condition "then" thenBranch "else" elseBranch;
	
	@operator(type="primitive", weight="20", identifier="OclExpressionCS")
	CollectionLiteralExpCS			::= collectionType "{" (collectionLiteralParts ("," collectionLiteralParts)*)? "}";
	
	CollectionRangeCS				::= from #0 ".." #0 to;
	
	CollectionLiteralPartsOclExpCS	::= oclExpression;
	
	
	
//						|	source "->" simpleName "(" arguments+ ")"
//						|	source "."  simpleName "(" arguments+ ")"
//						|	simpleName "(" arguments+ ")"
//						|	source "." simpleName isMarkedPre[IS_MARKED_PRE] "(" arguments+ ")"
//						|	simpleName isMarkedPre[IS_MARKED_PRE] "(" arguments+ ")"
//						|	pathName "(" arguments ")"
//						|	source "." pathName "::" simpleName "(" arguments+ ")"
//						|	source "." pathName "::" simpleName isMarkedPre[IS_MARKED_PRE] "(" arguments+ ")";
	
	@operator(type="primitive", weight="20", identifier="OclExpressionCS")
	IntegerLiteralExpCS	::= integerLiteral[INTEGER_LITERAL];
	
	@operator(type="primitive", weight="20", identifier="OclExpressionCS")
	RealLiteralExpCS		::= realLiteral[REAL_LITERAL];
	
	@operator(type="primitive", weight="20", identifier="OclExpressionCS")
	BooleanLiteralExpCS		::= booleanLiteral[BOOLEAN_LITERAL];
	
	@operator(type="primitive", weight="20", identifier="OclExpressionCS")
	StringLiteralExpCS		::= stringLiteral['\'', '\''];
	
	@operator(type="primitive", weight="20", identifier="OclExpressionCS")
	VariableOrStaticPropertyOrEnumLiteralExpCS ::= name;
}