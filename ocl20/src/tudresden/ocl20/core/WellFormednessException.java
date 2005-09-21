/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL 2 Compiler                                                    *
 * Copyright (C) 2002, 2003 Stefan Ocke (stefan.ocke@gmx.de).        *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a diploma project at the Chair for Software *
 * Technology, Dresden University Of Technology, Germany             *
 * (http://www-st.inf.tu-dresden.de).  It is understood that any     *
 * modification not identified as such is not covered by the         *
 * preceding statement.                                              *
 *                                                                   *
 * This work is free software; you can redistribute it and/or        *
 * modify it under the terms of the GNU Library General Public       *
 * License as published by the Free Software Foundation; either      *
 * version 2 of the License, or (at your option) any later version.  *
 *                                                                   *
 * This work is distributed in the hope that it will be useful,      *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU *
 * Library General Public License for more details.                  *
 *                                                                   *
 * You should have received a copy of the GNU Library General Public *
 * License along with this library; if not, write to the             *
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,      *
 * Boston, MA  02111-1307, USA.                                      *
 *                                                                   *
 * To submit a bug report, send a comment, or get the latest news on *
 * this project and other projects, please visit the web site:       *
 * http://www-st.inf.tu-dresden.de/ (Chair home page) or             *
 * http://www-st.inf.tu-dresden.de/ocl/ (project home page)          *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package tudresden.ocl20.core;

import tudresden.ocl20.core.jmi.ocl.commonmodel.ModelElement;
import java.util.*;

/**
 * A WellformednessException is thrown by the {@link TypeEvaluator TypeEvaluator}, 
 * if some WFR of the OCL::Expressions package is hurt by the OCL model or if some 
 * mantadory reference is not initialized.
 *
 * @author  Stefan Ocke
 */
public class WellFormednessException extends Exception{
    
    ModelElement source;
    int errorcode;
    
    public static final int EC_UNKNOWN = 0;
    public static final int EC_NO_SOURCE_EXP = 1;
    public static final int EC_NO_SRCTYPE = 2;
    public static final int EC_NO_ATTRIBUTE = 3;
    public static final int EC_NO_ASSOCIATIONCLASS = 4;
    public static final int EC_AMBIGUOUS_ASSOCCLASS_NAVI = 5;
    public static final int EC_ILLEGAL_QUALIFIED_NAVI_N_ARY = 6;
    public static final int EC_ILLEGAL_QUALIFIED_NAVI_ASSOCCLASS = 7;
    public static final int EC_ILLEGAL_QUALIFIED_NAVI_TO_UNQUALIFIED_END = 8;
    public static final int EC_QUALIFIER_MISMATCH = 9;
    public static final int EC_NO_COLLECTIONKIND = 10;
    public static final int EC_KIND_IS_COLLECTION = 11;
    public static final int EC_NO_COLLECTION_ITEM = 12;
    public static final int EC_COLL_RANGE_NO_FIRST = 13;
    public static final int EC_COLL_RANGE_NO_LAST = 14;
    public static final int EC_NO_ENUMLITERAL = 15;
    public static final int EC_NO_CONDITION = 16;
    public static final int EC_NO_THEN_EXP = 17;
    public static final int EC_NO_ELSE_EXP = 18;
    public static final int EC_CONDITION_NOT_BOOL = 19;
    public static final int EC_NO_BODY_EXP = 20;
    public static final int EC_SRC_NOT_COLLECTION = 21;
    public static final int EC_NO_ITERATOR_VARS = 22;
    public static final int EC_ILLEGAL_ITERATOR_VAR_INIT = 23;
    public static final int EC_ITERATOR_VAR_WRONG_TYPE = 24;
    public static final int EC_BODY_NOT_BOOL = 25;
    public static final int EC_UNKNOWN_ITERATOR_EXP = 26;
    public static final int EC_NO_ACCUMUMLATOR_VAR = 27;
    public static final int EC_NO_INIT_EXP = 28;
    public static final int EC_WRONG_VAR_INIT_TYPE = 29;
    public static final int EC_WRONG_BODY_TYPE = 30;
    public static final int EC_NO_IN_EXP = 31;
    public static final int EC_NO_VARIABLE = 32;
    public static final int EC_NO_VAR_TYPE = 33;
    public static final int EC_INVALID_MSG_ARG = 34;
    public static final int EC_UNSPECIFIED_NO_TYPE = 35;
    public static final int EC_NO_TARGET = 36;
    public static final int EC_ILLEGAL_TARGET = 37;
    public static final int EC_OP_OR_SIGNAL = 38;
    public static final int EC_PARAMETER_MISMATCH = 39;
    public static final int EC_SIGNAL_ATTR_MISMATCH = 40;
    public static final int EC_NOT_OP_OF_TARGET = 41;
    public static final int EC_NO_TYPE_ARG = 42;
    public static final int EC_WRONG_TYPE_ARG = 43;
    public static final int EC_UNKNOWN_TYPE_ARG_EXP = 44;
    public static final int EC_NO_OPERATION = 45;
    public static final int EC_INVALID_ALLINSTANCES = 46;
    public static final int EC_TUPLE_PART_NAMES_NOT_UNIQUE = 47;
    public static final int EC_NO_ASSOCIATIONEND= 48;
    public static final int EC_COLL_RANGE_INT= 49;
    public static final int EC_INVARIANT_MUST_BE_BOOLEAN= 50;
    public static final int EC_ACCU_VAR_NO_TYPE= 51;
    public static final int EC_OP_BODY_TYPE_MUST_CONFORM= 52;
    
       
        
    private static String [] description = {
/*00*/        "Unknown reason.",
/*01*/        "Source expression of the property call is missing.",
/*02*/        "Source type is missing for classifier scoped operation or attribute.",
/*03*/        "Referred attribute is missing.",
/*04*/        "Referred association class is missing.",
/*05*/        "Navigation to assocation class is ambiguous and source end is not stated.",
/*06*/        "Qualified navigation is not allowed for n-ary (n>2) associations.",
/*07*/        "Qualified navigation is not allowed when navigating from association class to a participant of the association.",
/*08*/        "Qualified navigation is not allowed if referred association end has no qualifiers.",
/*09*/        "Qualifier arguments do not match with the qualifiers of the referred assocition end.",
/*10*/        "The kind of the collection is missing.",
/*11*/        "A collection literal may not be of kind Collection. Only Set, Bag, Sequence, OrderedSet are allowed.",
/*12*/        "Expression for collection item is missing.",
/*13*/        "First expression for collection range is missing.",
/*14*/        "Last expression for collection range is missing.",
/*15*/        "Referred enumeration literal is missing",
/*16*/        "The condition expression is missing.",
/*17*/        "The then expression is missing.",
/*18*/        "The else expression is missing.",
/*19*/        "Type of condition expression is not boolean.",
/*20*/        "Body expression of the loop expression is missing.",
/*21*/        "Source type of the loop expression is not a collection.",
/*22*/        "The loop expression has no iterator variables.",
/*23*/        "An iterator variable may not have an init expression.",
/*24*/        "The iterator variable does not comply with the element type of the collection.",
/*25*/        "The body expression for exist, forAll, select, reject, any must be boolean.",
/*26*/        "Unknown iterator expression.",
/*27*/        "Accumulator variable is missing in iterate expression.", 
/*28*/        "The init expression for the variable is missing.", 
/*39*/        "The type of the init expression does not conform to the type of the variable.",
/*30*/        "The body of the iterate expression does not conform to the type of the accumulator variable.",
/*31*/        "The in-expression of the let expression is missing.",
/*32*/        "The variable declaration  is missing.",
/*33*/        "The type of the variable is missing.",
/*34*/        "An message argument must be exactly one: an expression or an unspecified value.",
/*35*/        "The type of the unspecified value is missing.",
/*36*/        "The target of the message expression is missing.",
/*37*/        "The target of the message expression may not be a collection.",
/*38*/        "A message expression must either refer an operation or a signal.",
/*39*/        "The arguments do not match with the parameters of the operation.",
/*40*/        "The arguments do not match with the attributes of the signal.",
/*41*/        "The called operation does not belong to the target type.",
/*42*/        "The type argument of asType, isTypeOf or isKindOf is missing.",
/*43*/        "The type argument of asType must conform to the type of the source expression.",
/*44*/        "Unknown expression with type argument. Must be one of asType, isKindOf or isTypeOf.",
/*45*/        "Referred Operation is missing.",
/*46*/        "allInstances is not applicable to primitive types, collection types, tuple types and message types.",
/*47*/        "The tuple part names are not unique.",
/*48*/        "Referred association end is missing.",
/*49*/        "First and last of a collection range must be integers.",
/*50*/        "The type of an invariant must be boolean.",
/*51*/        "The type of the accumulator variable is missing.",
/*52*/        "The type of the body expression must conform to result type of the operation."
    };
    
    
    /** Creates a new instance of WellFormdnessException */
    public WellFormednessException() {
        source = null;
        this.errorcode = EC_UNKNOWN;
    }
    
    public WellFormednessException(ModelElement source, int errorcode) {
        super(description[errorcode]);
        this.source = source;
        this.errorcode = errorcode;
    }
    
    public ModelElement getSource() {
        return source;
    }
    
    public int getErrorCode() {
        return errorcode;
    }
    
}
