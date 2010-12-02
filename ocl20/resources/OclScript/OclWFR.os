
{
//context BooleanLiteralExp inv : type.oclIsKindOf(Primitive) and type.nameA = ’Boolean’
Classifier      context     =   "OCL::Expressions::BooleanLiteralExp";
OpCall          oce1        =   $context "allInstances";
IteratorVar     selfDecl    =   "self" $context;

VarExp          self        =   $selfDecl;
AssocEndCall    aece        =   $self "type";
AttrCall        name        =   $aece "nameA";
String          str         =   "Boolean";
OpCall          compare     =   $name "=" $str;
VarExp          self2       =   $selfDecl;
AssocEndCall    aece1       =   $self2 "type";
IsKindOf        kindOf      =   $aece1 "OCL::CommonModel::Primitive";
OpCall          and         =   $kindOf "and" $compare;

Iterator        forAll      =   $oce1 "forAll" $selfDecl $and;
Invariant       inv         =   $context "BooleanLiteralExpWFR" $forAll "self.type.oclIsKindOf(Primitive) and self.type.nameA = ’Boolean’";
}
{
Classifier      context     =   "OCL::Expressions::BooleanLiteralExp";
Boolean         bool        =   true;
Invariant       inv         =   $context "BooleanLiteralExpWFR2" $bool "true";
}

{
Classifier      context     =   "OCL::Types::SequenceType";
OpCall          oce1        =   $context "allInstances";
IteratorVar     selfDecl    =   "self" $context;

VarExp          self        =   $selfDecl;
AttrCall        name        =   $self "nameA";

String          str1        =   "Sequence(";

VarExp          self2       =   $selfDecl;
AssocEndCall    elmType     =   $self2 "elementType";
AttrCall        elmTypeName =   $elmType "nameA";
OpCall          concat1     =   $str1 "concat" $elmTypeName;

String          str2        =   ")";
OpCall          concat2     =   $concat1 "concat" $str2;

OpCall          compare     =   $name "=" $concat2;

Iterator        forAll      =   $oce1 "forAll" $selfDecl $compare;
Invariant       inv         =   $context "SequenceTypeWFR" $forAll "nameA=’Sequence(’.concat(self.elementType.nameA).concat(’)’)";
}
{
Classifier      context     =   "OCL::Expressions::CollectionLiteralExp";
OpCall          allInst     =   $context "allInstances";
IteratorVar     selfDecl    =   "self" $context;

VarExp          self        =   $selfDecl;
AttrCall        kind        =   $self "kind";
EnumLiteral     setKind     =   "OCL::Expressions::CollectionKind::Set";
OpCall          compare     =   $kind "=" $setKind;

VarExp          self2       =   $selfDecl;
AssocEndCall    type        =   $self2 "type";
IsKindOf        isKindOf    =   $type "OCL::Types::SetType";

OpCall          implies     =   $compare "implies" $isKindOf;

Iterator        forAll      =   $allInst "forAll" $selfDecl $implies;
Invariant       inv         =   $context "CollectionLiteralExpWFR1" $forAll "kind=CollectionKind::Set implies type.oclIsKindOf(SetType)";
}
{
//context OCL::Expressions::CollectionLiteralExp
//inv: type.oclAsType(CollectionType).elementType = parts->iterate (p; c : Classifier = VoidType.allInstances()->any(true) | c.commonSuperType (p.type))

Classifier      context     =   "OCL::Expressions::CollectionLiteralExp";
OpCall          allInst     =   $context "allInstances";
IteratorVar     selfDecl    =   "self" $context;

VarExp          self        =   $selfDecl;
AssocEndCall    type        =   $self "type";
AsType          asType      =   $type "OCL::Types::CollectionType";
AssocEndCall    elmType     =   $asType "elementType";

VarExp          self2       =   $selfDecl;
AssocEndCall    parts       =   $self2 "parts";

Classifier      clp         =   "OCL::Expressions::CollectionLiteralPart";
IteratorVar     pDecl       =   "p" $clp;

Classifier      voidType    =   "OCL::Types::VoidType";
OpCall          voidAllInst =   $voidType "allInstances";
IteratorVar     dummy       =   "dummy" $voidType;
Boolean         bool        =   true;
Iterator        void        =   $voidAllInst "any" $dummy $bool;

Classifier      classifier  =   "OCL::CommonModel::Classifier";
Var             cDecl       =   "c" $classifier $void;

VarExp          p           =   $pDecl;
AssocEndCall    ptype       =   $p "type";

VarExp          c           =   $cDecl;
OpCall          commonSuper =   $c "commonSuperType" $ptype;

Iterate         iterate     =   $parts $pDecl $cDecl $commonSuper;
OpCall          compare     =   $elmType "=" $iterate;

Iterator        forAll      =   $allInst "forAll" $selfDecl $compare;
Invariant       inv         =   $context "CollectionLiteralExpWFR2" $forAll "type.oclAsType(CollectionType).elementType = parts->iterate (p; c : Classifier = VoidType.allInstances()->any(true) | c.commonSuperType (p.type))";
}
