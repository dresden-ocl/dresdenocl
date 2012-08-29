// $ANTLR 3.4

	package tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp;


import org.antlr.runtime3_4_0.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class OclLexer extends Lexer {
    public static final int EOF=-1;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__50=50;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int ADDITIVE_OPERATOR=4;
    public static final int AND_OPERATOR=5;
    public static final int BOOLEAN_LITERAL=6;
    public static final int COLLECTION_TYPES=7;
    public static final int EQUALITY_OPERATOR=8;
    public static final int IMPLIES_OPERATOR=9;
    public static final int INTEGER_0=10;
    public static final int INTEGER_LITERAL=11;
    public static final int IS_MARKED_PRE=12;
    public static final int ITERATOR_NAME=13;
    public static final int LINEBREAKS=14;
    public static final int ML_COMMENT=15;
    public static final int MULT_OPERATOR=16;
    public static final int NAVIGATION_OPERATOR=17;
    public static final int NEQUALITY_OPERATOR=18;
    public static final int NOT_OPERATOR=19;
    public static final int OR_OPERATOR=20;
    public static final int QUOTED_39_39=21;
    public static final int RELATIONAL_OPERATOR=22;
    public static final int SIMPLE_NAME=23;
    public static final int SL_COMMENT=24;
    public static final int STATIC=25;
    public static final int WHITESPACE=26;
    public static final int XOR_OPERATOR=27;

    	public java.util.List<org.antlr.runtime3_4_0.RecognitionException> lexerExceptions  = new java.util.ArrayList<org.antlr.runtime3_4_0.RecognitionException>();
    	public java.util.List<Integer> lexerExceptionsPosition = new java.util.ArrayList<Integer>();
    	
    	public void reportError(org.antlr.runtime3_4_0.RecognitionException e) {
    		lexerExceptions.add(e);
    		lexerExceptionsPosition.add(((org.antlr.runtime3_4_0.ANTLRStringStream) input).index());
    	}


    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public OclLexer() {} 
    public OclLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public OclLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "Ocl.g"; }

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:15:7: ( '(' )
            // Ocl.g:15:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:16:7: ( ')' )
            // Ocl.g:16:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:17:7: ( ',' )
            // Ocl.g:17:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:18:7: ( '..' )
            // Ocl.g:18:9: '..'
            {
            match(".."); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:19:7: ( ':' )
            // Ocl.g:19:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:20:7: ( '::' )
            // Ocl.g:20:9: '::'
            {
            match("::"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:21:7: ( ';' )
            // Ocl.g:21:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:22:7: ( 'Tuple' )
            // Ocl.g:22:9: 'Tuple'
            {
            match("Tuple"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "T__36"
    public final void mT__36() throws RecognitionException {
        try {
            int _type = T__36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:23:7: ( 'body' )
            // Ocl.g:23:9: 'body'
            {
            match("body"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__36"

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:24:7: ( 'context' )
            // Ocl.g:24:9: 'context'
            {
            match("context"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:25:7: ( 'def' )
            // Ocl.g:25:9: 'def'
            {
            match("def"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "T__39"
    public final void mT__39() throws RecognitionException {
        try {
            int _type = T__39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:26:7: ( 'derive' )
            // Ocl.g:26:9: 'derive'
            {
            match("derive"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__39"

    // $ANTLR start "T__40"
    public final void mT__40() throws RecognitionException {
        try {
            int _type = T__40;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:27:7: ( 'else' )
            // Ocl.g:27:9: 'else'
            {
            match("else"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__40"

    // $ANTLR start "T__41"
    public final void mT__41() throws RecognitionException {
        try {
            int _type = T__41;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:28:7: ( 'endif' )
            // Ocl.g:28:9: 'endif'
            {
            match("endif"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__41"

    // $ANTLR start "T__42"
    public final void mT__42() throws RecognitionException {
        try {
            int _type = T__42;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:29:7: ( 'endpackage' )
            // Ocl.g:29:9: 'endpackage'
            {
            match("endpackage"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__42"

    // $ANTLR start "T__43"
    public final void mT__43() throws RecognitionException {
        try {
            int _type = T__43;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:30:7: ( 'if' )
            // Ocl.g:30:9: 'if'
            {
            match("if"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__43"

    // $ANTLR start "T__44"
    public final void mT__44() throws RecognitionException {
        try {
            int _type = T__44;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:31:7: ( 'in' )
            // Ocl.g:31:9: 'in'
            {
            match("in"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__44"

    // $ANTLR start "T__45"
    public final void mT__45() throws RecognitionException {
        try {
            int _type = T__45;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:32:7: ( 'init' )
            // Ocl.g:32:9: 'init'
            {
            match("init"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__45"

    // $ANTLR start "T__46"
    public final void mT__46() throws RecognitionException {
        try {
            int _type = T__46;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:33:7: ( 'inv' )
            // Ocl.g:33:9: 'inv'
            {
            match("inv"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__46"

    // $ANTLR start "T__47"
    public final void mT__47() throws RecognitionException {
        try {
            int _type = T__47;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:34:7: ( 'invalid' )
            // Ocl.g:34:9: 'invalid'
            {
            match("invalid"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__47"

    // $ANTLR start "T__48"
    public final void mT__48() throws RecognitionException {
        try {
            int _type = T__48;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:35:7: ( 'iterate' )
            // Ocl.g:35:9: 'iterate'
            {
            match("iterate"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__48"

    // $ANTLR start "T__49"
    public final void mT__49() throws RecognitionException {
        try {
            int _type = T__49;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:36:7: ( 'let' )
            // Ocl.g:36:9: 'let'
            {
            match("let"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__49"

    // $ANTLR start "T__50"
    public final void mT__50() throws RecognitionException {
        try {
            int _type = T__50;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:37:7: ( 'null' )
            // Ocl.g:37:9: 'null'
            {
            match("null"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__50"

    // $ANTLR start "T__51"
    public final void mT__51() throws RecognitionException {
        try {
            int _type = T__51;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:38:7: ( 'package' )
            // Ocl.g:38:9: 'package'
            {
            match("package"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__51"

    // $ANTLR start "T__52"
    public final void mT__52() throws RecognitionException {
        try {
            int _type = T__52;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:39:7: ( 'post' )
            // Ocl.g:39:9: 'post'
            {
            match("post"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__52"

    // $ANTLR start "T__53"
    public final void mT__53() throws RecognitionException {
        try {
            int _type = T__53;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:40:7: ( 'pre' )
            // Ocl.g:40:9: 'pre'
            {
            match("pre"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__53"

    // $ANTLR start "T__54"
    public final void mT__54() throws RecognitionException {
        try {
            int _type = T__54;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:41:7: ( 'then' )
            // Ocl.g:41:9: 'then'
            {
            match("then"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__54"

    // $ANTLR start "T__55"
    public final void mT__55() throws RecognitionException {
        try {
            int _type = T__55;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:42:7: ( '{' )
            // Ocl.g:42:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__55"

    // $ANTLR start "T__56"
    public final void mT__56() throws RecognitionException {
        try {
            int _type = T__56;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:43:7: ( '|' )
            // Ocl.g:43:9: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__56"

    // $ANTLR start "T__57"
    public final void mT__57() throws RecognitionException {
        try {
            int _type = T__57;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:44:7: ( '}' )
            // Ocl.g:44:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__57"

    // $ANTLR start "SL_COMMENT"
    public final void mSL_COMMENT() throws RecognitionException {
        try {
            int _type = SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:9795:11: ( ( '--' (~ ( '\\n' | '\\r' | '\\uffff' ) )* ) )
            // Ocl.g:9796:3: ( '--' (~ ( '\\n' | '\\r' | '\\uffff' ) )* )
            {
            // Ocl.g:9796:3: ( '--' (~ ( '\\n' | '\\r' | '\\uffff' ) )* )
            // Ocl.g:9796:3: '--' (~ ( '\\n' | '\\r' | '\\uffff' ) )*
            {
            match("--"); 



            // Ocl.g:9796:7: (~ ( '\\n' | '\\r' | '\\uffff' ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= '\u0000' && LA1_0 <= '\t')||(LA1_0 >= '\u000B' && LA1_0 <= '\f')||(LA1_0 >= '\u000E' && LA1_0 <= '\uFFFE')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Ocl.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFE') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }


             _channel = 99; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SL_COMMENT"

    // $ANTLR start "ML_COMMENT"
    public final void mML_COMMENT() throws RecognitionException {
        try {
            int _type = ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:9799:11: ( ( '/*' ( . )* '*/' ) )
            // Ocl.g:9800:3: ( '/*' ( . )* '*/' )
            {
            // Ocl.g:9800:3: ( '/*' ( . )* '*/' )
            // Ocl.g:9800:3: '/*' ( . )* '*/'
            {
            match("/*"); 



            // Ocl.g:9800:7: ( . )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0=='*') ) {
                    int LA2_1 = input.LA(2);

                    if ( (LA2_1=='/') ) {
                        alt2=2;
                    }
                    else if ( ((LA2_1 >= '\u0000' && LA2_1 <= '.')||(LA2_1 >= '0' && LA2_1 <= '\uFFFF')) ) {
                        alt2=1;
                    }


                }
                else if ( ((LA2_0 >= '\u0000' && LA2_0 <= ')')||(LA2_0 >= '+' && LA2_0 <= '\uFFFF')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // Ocl.g:9800:7: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            match("*/"); 



            }


             _channel = 99; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ML_COMMENT"

    // $ANTLR start "NAVIGATION_OPERATOR"
    public final void mNAVIGATION_OPERATOR() throws RecognitionException {
        try {
            int _type = NAVIGATION_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:9803:20: ( ( '.' | '->' ) )
            // Ocl.g:9804:3: ( '.' | '->' )
            {
            // Ocl.g:9804:3: ( '.' | '->' )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='.') ) {
                alt3=1;
            }
            else if ( (LA3_0=='-') ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }
            switch (alt3) {
                case 1 :
                    // Ocl.g:9804:3: '.'
                    {
                    match('.'); 

                    }
                    break;
                case 2 :
                    // Ocl.g:9804:9: '->'
                    {
                    match("->"); 



                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NAVIGATION_OPERATOR"

    // $ANTLR start "ADDITIVE_OPERATOR"
    public final void mADDITIVE_OPERATOR() throws RecognitionException {
        try {
            int _type = ADDITIVE_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:9806:18: ( ( '+' | '-' ) )
            // Ocl.g:
            {
            if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ADDITIVE_OPERATOR"

    // $ANTLR start "MULT_OPERATOR"
    public final void mMULT_OPERATOR() throws RecognitionException {
        try {
            int _type = MULT_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:9809:14: ( ( '*' | '/' | '%' ) )
            // Ocl.g:
            {
            if ( input.LA(1)=='%'||input.LA(1)=='*'||input.LA(1)=='/' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MULT_OPERATOR"

    // $ANTLR start "RELATIONAL_OPERATOR"
    public final void mRELATIONAL_OPERATOR() throws RecognitionException {
        try {
            int _type = RELATIONAL_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:9812:20: ( ( '<' | '>' | '<=' | '>=' ) )
            // Ocl.g:9813:3: ( '<' | '>' | '<=' | '>=' )
            {
            // Ocl.g:9813:3: ( '<' | '>' | '<=' | '>=' )
            int alt4=4;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='<') ) {
                int LA4_1 = input.LA(2);

                if ( (LA4_1=='=') ) {
                    alt4=3;
                }
                else {
                    alt4=1;
                }
            }
            else if ( (LA4_0=='>') ) {
                int LA4_2 = input.LA(2);

                if ( (LA4_2=='=') ) {
                    alt4=4;
                }
                else {
                    alt4=2;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }
            switch (alt4) {
                case 1 :
                    // Ocl.g:9813:3: '<'
                    {
                    match('<'); 

                    }
                    break;
                case 2 :
                    // Ocl.g:9813:9: '>'
                    {
                    match('>'); 

                    }
                    break;
                case 3 :
                    // Ocl.g:9813:15: '<='
                    {
                    match("<="); 



                    }
                    break;
                case 4 :
                    // Ocl.g:9813:22: '>='
                    {
                    match(">="); 



                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RELATIONAL_OPERATOR"

    // $ANTLR start "EQUALITY_OPERATOR"
    public final void mEQUALITY_OPERATOR() throws RecognitionException {
        try {
            int _type = EQUALITY_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:9815:18: ( ( '=' ) )
            // Ocl.g:9816:3: ( '=' )
            {
            // Ocl.g:9816:3: ( '=' )
            // Ocl.g:9816:3: '='
            {
            match('='); 

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EQUALITY_OPERATOR"

    // $ANTLR start "NEQUALITY_OPERATOR"
    public final void mNEQUALITY_OPERATOR() throws RecognitionException {
        try {
            int _type = NEQUALITY_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:9818:19: ( ( '<>' ) )
            // Ocl.g:9819:3: ( '<>' )
            {
            // Ocl.g:9819:3: ( '<>' )
            // Ocl.g:9819:3: '<>'
            {
            match("<>"); 



            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NEQUALITY_OPERATOR"

    // $ANTLR start "NOT_OPERATOR"
    public final void mNOT_OPERATOR() throws RecognitionException {
        try {
            int _type = NOT_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:9821:13: ( ( 'not' ) )
            // Ocl.g:9822:3: ( 'not' )
            {
            // Ocl.g:9822:3: ( 'not' )
            // Ocl.g:9822:3: 'not'
            {
            match("not"); 



            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NOT_OPERATOR"

    // $ANTLR start "AND_OPERATOR"
    public final void mAND_OPERATOR() throws RecognitionException {
        try {
            int _type = AND_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:9824:13: ( ( 'and' ) )
            // Ocl.g:9825:3: ( 'and' )
            {
            // Ocl.g:9825:3: ( 'and' )
            // Ocl.g:9825:3: 'and'
            {
            match("and"); 



            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AND_OPERATOR"

    // $ANTLR start "OR_OPERATOR"
    public final void mOR_OPERATOR() throws RecognitionException {
        try {
            int _type = OR_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:9827:12: ( ( 'or' ) )
            // Ocl.g:9828:3: ( 'or' )
            {
            // Ocl.g:9828:3: ( 'or' )
            // Ocl.g:9828:3: 'or'
            {
            match("or"); 



            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OR_OPERATOR"

    // $ANTLR start "XOR_OPERATOR"
    public final void mXOR_OPERATOR() throws RecognitionException {
        try {
            int _type = XOR_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:9830:13: ( ( 'xor' ) )
            // Ocl.g:9831:3: ( 'xor' )
            {
            // Ocl.g:9831:3: ( 'xor' )
            // Ocl.g:9831:3: 'xor'
            {
            match("xor"); 



            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "XOR_OPERATOR"

    // $ANTLR start "IMPLIES_OPERATOR"
    public final void mIMPLIES_OPERATOR() throws RecognitionException {
        try {
            int _type = IMPLIES_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:9833:17: ( ( 'implies' ) )
            // Ocl.g:9834:3: ( 'implies' )
            {
            // Ocl.g:9834:3: ( 'implies' )
            // Ocl.g:9834:3: 'implies'
            {
            match("implies"); 



            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IMPLIES_OPERATOR"

    // $ANTLR start "IS_MARKED_PRE"
    public final void mIS_MARKED_PRE() throws RecognitionException {
        try {
            int _type = IS_MARKED_PRE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:9836:14: ( ( '@pre' ) )
            // Ocl.g:9837:3: ( '@pre' )
            {
            // Ocl.g:9837:3: ( '@pre' )
            // Ocl.g:9837:3: '@pre'
            {
            match("@pre"); 



            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IS_MARKED_PRE"

    // $ANTLR start "BOOLEAN_LITERAL"
    public final void mBOOLEAN_LITERAL() throws RecognitionException {
        try {
            int _type = BOOLEAN_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:9839:16: ( ( 'true' | 'false' ) )
            // Ocl.g:9840:3: ( 'true' | 'false' )
            {
            // Ocl.g:9840:3: ( 'true' | 'false' )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='t') ) {
                alt5=1;
            }
            else if ( (LA5_0=='f') ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }
            switch (alt5) {
                case 1 :
                    // Ocl.g:9840:3: 'true'
                    {
                    match("true"); 



                    }
                    break;
                case 2 :
                    // Ocl.g:9840:12: 'false'
                    {
                    match("false"); 



                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BOOLEAN_LITERAL"

    // $ANTLR start "COLLECTION_TYPES"
    public final void mCOLLECTION_TYPES() throws RecognitionException {
        try {
            int _type = COLLECTION_TYPES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:9842:17: ( ( 'Set' | 'Bag' | 'Sequence' | 'Collection' | 'OrderedSet' ) )
            // Ocl.g:9843:3: ( 'Set' | 'Bag' | 'Sequence' | 'Collection' | 'OrderedSet' )
            {
            // Ocl.g:9843:3: ( 'Set' | 'Bag' | 'Sequence' | 'Collection' | 'OrderedSet' )
            int alt6=5;
            switch ( input.LA(1) ) {
            case 'S':
                {
                int LA6_1 = input.LA(2);

                if ( (LA6_1=='e') ) {
                    int LA6_5 = input.LA(3);

                    if ( (LA6_5=='t') ) {
                        alt6=1;
                    }
                    else if ( (LA6_5=='q') ) {
                        alt6=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 5, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 1, input);

                    throw nvae;

                }
                }
                break;
            case 'B':
                {
                alt6=2;
                }
                break;
            case 'C':
                {
                alt6=4;
                }
                break;
            case 'O':
                {
                alt6=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }

            switch (alt6) {
                case 1 :
                    // Ocl.g:9843:3: 'Set'
                    {
                    match("Set"); 



                    }
                    break;
                case 2 :
                    // Ocl.g:9843:11: 'Bag'
                    {
                    match("Bag"); 



                    }
                    break;
                case 3 :
                    // Ocl.g:9843:19: 'Sequence'
                    {
                    match("Sequence"); 



                    }
                    break;
                case 4 :
                    // Ocl.g:9843:32: 'Collection'
                    {
                    match("Collection"); 



                    }
                    break;
                case 5 :
                    // Ocl.g:9843:47: 'OrderedSet'
                    {
                    match("OrderedSet"); 



                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COLLECTION_TYPES"

    // $ANTLR start "ITERATOR_NAME"
    public final void mITERATOR_NAME() throws RecognitionException {
        try {
            int _type = ITERATOR_NAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:9845:14: ( ( 'select' | 'reject' | 'collect' | 'forAll' | 'any' | 'exists' | 'one' | 'isUnique' | 'collectNested' | 'sortedBy' | 'closure' ) )
            // Ocl.g:9846:3: ( 'select' | 'reject' | 'collect' | 'forAll' | 'any' | 'exists' | 'one' | 'isUnique' | 'collectNested' | 'sortedBy' | 'closure' )
            {
            // Ocl.g:9846:3: ( 'select' | 'reject' | 'collect' | 'forAll' | 'any' | 'exists' | 'one' | 'isUnique' | 'collectNested' | 'sortedBy' | 'closure' )
            int alt7=11;
            switch ( input.LA(1) ) {
            case 's':
                {
                int LA7_1 = input.LA(2);

                if ( (LA7_1=='e') ) {
                    alt7=1;
                }
                else if ( (LA7_1=='o') ) {
                    alt7=10;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 1, input);

                    throw nvae;

                }
                }
                break;
            case 'r':
                {
                alt7=2;
                }
                break;
            case 'c':
                {
                int LA7_3 = input.LA(2);

                if ( (LA7_3=='o') ) {
                    int LA7_11 = input.LA(3);

                    if ( (LA7_11=='l') ) {
                        int LA7_13 = input.LA(4);

                        if ( (LA7_13=='l') ) {
                            int LA7_14 = input.LA(5);

                            if ( (LA7_14=='e') ) {
                                int LA7_15 = input.LA(6);

                                if ( (LA7_15=='c') ) {
                                    int LA7_16 = input.LA(7);

                                    if ( (LA7_16=='t') ) {
                                        int LA7_17 = input.LA(8);

                                        if ( (LA7_17=='N') ) {
                                            alt7=9;
                                        }
                                        else {
                                            alt7=3;
                                        }
                                    }
                                    else {
                                        NoViableAltException nvae =
                                            new NoViableAltException("", 7, 16, input);

                                        throw nvae;

                                    }
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 7, 15, input);

                                    throw nvae;

                                }
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 7, 14, input);

                                throw nvae;

                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 7, 13, input);

                            throw nvae;

                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 7, 11, input);

                        throw nvae;

                    }
                }
                else if ( (LA7_3=='l') ) {
                    alt7=11;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 3, input);

                    throw nvae;

                }
                }
                break;
            case 'f':
                {
                alt7=4;
                }
                break;
            case 'a':
                {
                alt7=5;
                }
                break;
            case 'e':
                {
                alt7=6;
                }
                break;
            case 'o':
                {
                alt7=7;
                }
                break;
            case 'i':
                {
                alt7=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }

            switch (alt7) {
                case 1 :
                    // Ocl.g:9846:3: 'select'
                    {
                    match("select"); 



                    }
                    break;
                case 2 :
                    // Ocl.g:9846:14: 'reject'
                    {
                    match("reject"); 



                    }
                    break;
                case 3 :
                    // Ocl.g:9846:25: 'collect'
                    {
                    match("collect"); 



                    }
                    break;
                case 4 :
                    // Ocl.g:9846:37: 'forAll'
                    {
                    match("forAll"); 



                    }
                    break;
                case 5 :
                    // Ocl.g:9846:48: 'any'
                    {
                    match("any"); 



                    }
                    break;
                case 6 :
                    // Ocl.g:9846:56: 'exists'
                    {
                    match("exists"); 



                    }
                    break;
                case 7 :
                    // Ocl.g:9846:67: 'one'
                    {
                    match("one"); 



                    }
                    break;
                case 8 :
                    // Ocl.g:9846:75: 'isUnique'
                    {
                    match("isUnique"); 



                    }
                    break;
                case 9 :
                    // Ocl.g:9846:88: 'collectNested'
                    {
                    match("collectNested"); 



                    }
                    break;
                case 10 :
                    // Ocl.g:9846:106: 'sortedBy'
                    {
                    match("sortedBy"); 



                    }
                    break;
                case 11 :
                    // Ocl.g:9846:119: 'closure'
                    {
                    match("closure"); 



                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ITERATOR_NAME"

    // $ANTLR start "STATIC"
    public final void mSTATIC() throws RecognitionException {
        try {
            int _type = STATIC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:9848:7: ( ( 'static' ) )
            // Ocl.g:9849:3: ( 'static' )
            {
            // Ocl.g:9849:3: ( 'static' )
            // Ocl.g:9849:3: 'static'
            {
            match("static"); 



            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STATIC"

    // $ANTLR start "INTEGER_0"
    public final void mINTEGER_0() throws RecognitionException {
        try {
            int _type = INTEGER_0;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:9851:10: ( ( ( '0' )+ ( '0' .. '9' ) ) )
            // Ocl.g:9852:3: ( ( '0' )+ ( '0' .. '9' ) )
            {
            // Ocl.g:9852:3: ( ( '0' )+ ( '0' .. '9' ) )
            // Ocl.g:9852:3: ( '0' )+ ( '0' .. '9' )
            {
            // Ocl.g:9852:3: ( '0' )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0=='0') ) {
                    int LA8_1 = input.LA(2);

                    if ( ((LA8_1 >= '0' && LA8_1 <= '9')) ) {
                        alt8=1;
                    }


                }


                switch (alt8) {
            	case 1 :
            	    // Ocl.g:9852:3: '0'
            	    {
            	    match('0'); 

            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);


            if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INTEGER_0"

    // $ANTLR start "INTEGER_LITERAL"
    public final void mINTEGER_LITERAL() throws RecognitionException {
        try {
            int _type = INTEGER_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:9854:16: ( ( ( '1' .. '9' ) ( '0' .. '9' )* | '0' ) )
            // Ocl.g:9855:3: ( ( '1' .. '9' ) ( '0' .. '9' )* | '0' )
            {
            // Ocl.g:9855:3: ( ( '1' .. '9' ) ( '0' .. '9' )* | '0' )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( ((LA10_0 >= '1' && LA10_0 <= '9')) ) {
                alt10=1;
            }
            else if ( (LA10_0=='0') ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;

            }
            switch (alt10) {
                case 1 :
                    // Ocl.g:9855:3: ( '1' .. '9' ) ( '0' .. '9' )*
                    {
                    if ( (input.LA(1) >= '1' && input.LA(1) <= '9') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    // Ocl.g:9855:14: ( '0' .. '9' )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( ((LA9_0 >= '0' && LA9_0 <= '9')) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // Ocl.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // Ocl.g:9855:28: '0'
                    {
                    match('0'); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INTEGER_LITERAL"

    // $ANTLR start "SIMPLE_NAME"
    public final void mSIMPLE_NAME() throws RecognitionException {
        try {
            int _type = SIMPLE_NAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:9857:12: ( ( ( 'A' .. 'Z' | 'a' .. 'z' | '_' ) ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '_' )* ) )
            // Ocl.g:9858:3: ( ( 'A' .. 'Z' | 'a' .. 'z' | '_' ) ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '_' )* )
            {
            // Ocl.g:9858:3: ( ( 'A' .. 'Z' | 'a' .. 'z' | '_' ) ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '_' )* )
            // Ocl.g:9858:3: ( 'A' .. 'Z' | 'a' .. 'z' | '_' ) ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // Ocl.g:9858:27: ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '_' )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0 >= '0' && LA11_0 <= '9')||(LA11_0 >= 'A' && LA11_0 <= 'Z')||LA11_0=='_'||(LA11_0 >= 'a' && LA11_0 <= 'z')) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // Ocl.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SIMPLE_NAME"

    // $ANTLR start "WHITESPACE"
    public final void mWHITESPACE() throws RecognitionException {
        try {
            int _type = WHITESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:9860:11: ( ( ( ' ' | '\\t' | '\\f' ) ) )
            // Ocl.g:9861:2: ( ( ' ' | '\\t' | '\\f' ) )
            {
            if ( input.LA(1)=='\t'||input.LA(1)=='\f'||input.LA(1)==' ' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


             _channel = 99; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WHITESPACE"

    // $ANTLR start "LINEBREAKS"
    public final void mLINEBREAKS() throws RecognitionException {
        try {
            int _type = LINEBREAKS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:9864:11: ( ( ( '\\r\\n' | '\\r' | '\\n' ) ) )
            // Ocl.g:9865:2: ( ( '\\r\\n' | '\\r' | '\\n' ) )
            {
            // Ocl.g:9865:2: ( ( '\\r\\n' | '\\r' | '\\n' ) )
            // Ocl.g:9865:2: ( '\\r\\n' | '\\r' | '\\n' )
            {
            // Ocl.g:9865:2: ( '\\r\\n' | '\\r' | '\\n' )
            int alt12=3;
            int LA12_0 = input.LA(1);

            if ( (LA12_0=='\r') ) {
                int LA12_1 = input.LA(2);

                if ( (LA12_1=='\n') ) {
                    alt12=1;
                }
                else {
                    alt12=2;
                }
            }
            else if ( (LA12_0=='\n') ) {
                alt12=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;

            }
            switch (alt12) {
                case 1 :
                    // Ocl.g:9865:3: '\\r\\n'
                    {
                    match("\r\n"); 



                    }
                    break;
                case 2 :
                    // Ocl.g:9865:10: '\\r'
                    {
                    match('\r'); 

                    }
                    break;
                case 3 :
                    // Ocl.g:9865:15: '\\n'
                    {
                    match('\n'); 

                    }
                    break;

            }


            }


             _channel = 99; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LINEBREAKS"

    // $ANTLR start "QUOTED_39_39"
    public final void mQUOTED_39_39() throws RecognitionException {
        try {
            int _type = QUOTED_39_39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ocl.g:9868:13: ( ( ( '\\'' ) (~ ( '\\'' ) )* ( '\\'' ) ) )
            // Ocl.g:9869:2: ( ( '\\'' ) (~ ( '\\'' ) )* ( '\\'' ) )
            {
            // Ocl.g:9869:2: ( ( '\\'' ) (~ ( '\\'' ) )* ( '\\'' ) )
            // Ocl.g:9869:2: ( '\\'' ) (~ ( '\\'' ) )* ( '\\'' )
            {
            // Ocl.g:9869:2: ( '\\'' )
            // Ocl.g:9869:3: '\\''
            {
            match('\''); 

            }


            // Ocl.g:9869:8: (~ ( '\\'' ) )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0 >= '\u0000' && LA13_0 <= '&')||(LA13_0 >= '(' && LA13_0 <= '\uFFFF')) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // Ocl.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '&')||(input.LA(1) >= '(' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);


            // Ocl.g:9869:18: ( '\\'' )
            // Ocl.g:9869:19: '\\''
            {
            match('\''); 

            }


            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "QUOTED_39_39"

    public void mTokens() throws RecognitionException {
        // Ocl.g:1:8: ( T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | SL_COMMENT | ML_COMMENT | NAVIGATION_OPERATOR | ADDITIVE_OPERATOR | MULT_OPERATOR | RELATIONAL_OPERATOR | EQUALITY_OPERATOR | NEQUALITY_OPERATOR | NOT_OPERATOR | AND_OPERATOR | OR_OPERATOR | XOR_OPERATOR | IMPLIES_OPERATOR | IS_MARKED_PRE | BOOLEAN_LITERAL | COLLECTION_TYPES | ITERATOR_NAME | STATIC | INTEGER_0 | INTEGER_LITERAL | SIMPLE_NAME | WHITESPACE | LINEBREAKS | QUOTED_39_39 )
        int alt14=54;
        alt14 = dfa14.predict(input);
        switch (alt14) {
            case 1 :
                // Ocl.g:1:10: T__28
                {
                mT__28(); 


                }
                break;
            case 2 :
                // Ocl.g:1:16: T__29
                {
                mT__29(); 


                }
                break;
            case 3 :
                // Ocl.g:1:22: T__30
                {
                mT__30(); 


                }
                break;
            case 4 :
                // Ocl.g:1:28: T__31
                {
                mT__31(); 


                }
                break;
            case 5 :
                // Ocl.g:1:34: T__32
                {
                mT__32(); 


                }
                break;
            case 6 :
                // Ocl.g:1:40: T__33
                {
                mT__33(); 


                }
                break;
            case 7 :
                // Ocl.g:1:46: T__34
                {
                mT__34(); 


                }
                break;
            case 8 :
                // Ocl.g:1:52: T__35
                {
                mT__35(); 


                }
                break;
            case 9 :
                // Ocl.g:1:58: T__36
                {
                mT__36(); 


                }
                break;
            case 10 :
                // Ocl.g:1:64: T__37
                {
                mT__37(); 


                }
                break;
            case 11 :
                // Ocl.g:1:70: T__38
                {
                mT__38(); 


                }
                break;
            case 12 :
                // Ocl.g:1:76: T__39
                {
                mT__39(); 


                }
                break;
            case 13 :
                // Ocl.g:1:82: T__40
                {
                mT__40(); 


                }
                break;
            case 14 :
                // Ocl.g:1:88: T__41
                {
                mT__41(); 


                }
                break;
            case 15 :
                // Ocl.g:1:94: T__42
                {
                mT__42(); 


                }
                break;
            case 16 :
                // Ocl.g:1:100: T__43
                {
                mT__43(); 


                }
                break;
            case 17 :
                // Ocl.g:1:106: T__44
                {
                mT__44(); 


                }
                break;
            case 18 :
                // Ocl.g:1:112: T__45
                {
                mT__45(); 


                }
                break;
            case 19 :
                // Ocl.g:1:118: T__46
                {
                mT__46(); 


                }
                break;
            case 20 :
                // Ocl.g:1:124: T__47
                {
                mT__47(); 


                }
                break;
            case 21 :
                // Ocl.g:1:130: T__48
                {
                mT__48(); 


                }
                break;
            case 22 :
                // Ocl.g:1:136: T__49
                {
                mT__49(); 


                }
                break;
            case 23 :
                // Ocl.g:1:142: T__50
                {
                mT__50(); 


                }
                break;
            case 24 :
                // Ocl.g:1:148: T__51
                {
                mT__51(); 


                }
                break;
            case 25 :
                // Ocl.g:1:154: T__52
                {
                mT__52(); 


                }
                break;
            case 26 :
                // Ocl.g:1:160: T__53
                {
                mT__53(); 


                }
                break;
            case 27 :
                // Ocl.g:1:166: T__54
                {
                mT__54(); 


                }
                break;
            case 28 :
                // Ocl.g:1:172: T__55
                {
                mT__55(); 


                }
                break;
            case 29 :
                // Ocl.g:1:178: T__56
                {
                mT__56(); 


                }
                break;
            case 30 :
                // Ocl.g:1:184: T__57
                {
                mT__57(); 


                }
                break;
            case 31 :
                // Ocl.g:1:190: SL_COMMENT
                {
                mSL_COMMENT(); 


                }
                break;
            case 32 :
                // Ocl.g:1:201: ML_COMMENT
                {
                mML_COMMENT(); 


                }
                break;
            case 33 :
                // Ocl.g:1:212: NAVIGATION_OPERATOR
                {
                mNAVIGATION_OPERATOR(); 


                }
                break;
            case 34 :
                // Ocl.g:1:232: ADDITIVE_OPERATOR
                {
                mADDITIVE_OPERATOR(); 


                }
                break;
            case 35 :
                // Ocl.g:1:250: MULT_OPERATOR
                {
                mMULT_OPERATOR(); 


                }
                break;
            case 36 :
                // Ocl.g:1:264: RELATIONAL_OPERATOR
                {
                mRELATIONAL_OPERATOR(); 


                }
                break;
            case 37 :
                // Ocl.g:1:284: EQUALITY_OPERATOR
                {
                mEQUALITY_OPERATOR(); 


                }
                break;
            case 38 :
                // Ocl.g:1:302: NEQUALITY_OPERATOR
                {
                mNEQUALITY_OPERATOR(); 


                }
                break;
            case 39 :
                // Ocl.g:1:321: NOT_OPERATOR
                {
                mNOT_OPERATOR(); 


                }
                break;
            case 40 :
                // Ocl.g:1:334: AND_OPERATOR
                {
                mAND_OPERATOR(); 


                }
                break;
            case 41 :
                // Ocl.g:1:347: OR_OPERATOR
                {
                mOR_OPERATOR(); 


                }
                break;
            case 42 :
                // Ocl.g:1:359: XOR_OPERATOR
                {
                mXOR_OPERATOR(); 


                }
                break;
            case 43 :
                // Ocl.g:1:372: IMPLIES_OPERATOR
                {
                mIMPLIES_OPERATOR(); 


                }
                break;
            case 44 :
                // Ocl.g:1:389: IS_MARKED_PRE
                {
                mIS_MARKED_PRE(); 


                }
                break;
            case 45 :
                // Ocl.g:1:403: BOOLEAN_LITERAL
                {
                mBOOLEAN_LITERAL(); 


                }
                break;
            case 46 :
                // Ocl.g:1:419: COLLECTION_TYPES
                {
                mCOLLECTION_TYPES(); 


                }
                break;
            case 47 :
                // Ocl.g:1:436: ITERATOR_NAME
                {
                mITERATOR_NAME(); 


                }
                break;
            case 48 :
                // Ocl.g:1:450: STATIC
                {
                mSTATIC(); 


                }
                break;
            case 49 :
                // Ocl.g:1:457: INTEGER_0
                {
                mINTEGER_0(); 


                }
                break;
            case 50 :
                // Ocl.g:1:467: INTEGER_LITERAL
                {
                mINTEGER_LITERAL(); 


                }
                break;
            case 51 :
                // Ocl.g:1:483: SIMPLE_NAME
                {
                mSIMPLE_NAME(); 


                }
                break;
            case 52 :
                // Ocl.g:1:495: WHITESPACE
                {
                mWHITESPACE(); 


                }
                break;
            case 53 :
                // Ocl.g:1:506: LINEBREAKS
                {
                mLINEBREAKS(); 


                }
                break;
            case 54 :
                // Ocl.g:1:517: QUOTED_39_39
                {
                mQUOTED_39_39(); 


                }
                break;

        }

    }


    protected DFA14 dfa14 = new DFA14(this);
    static final String DFA14_eotS =
        "\4\uffff\1\55\1\57\1\uffff\12\50\3\uffff\1\26\1\27\2\uffff\1\31"+
        "\2\uffff\3\50\1\uffff\7\50\1\47\11\uffff\10\50\1\141\1\144\13\50"+
        "\3\uffff\1\50\1\162\14\50\1\uffff\5\50\1\u0085\4\50\1\uffff\1\50"+
        "\1\u008d\1\uffff\3\50\1\u0091\1\50\1\u0093\2\50\1\u0096\2\50\1\u0099"+
        "\1\u009a\1\uffff\1\u009a\1\u009b\2\50\1\u009e\1\50\1\u009e\7\50"+
        "\1\u00a7\3\50\1\uffff\1\50\1\u00ac\3\50\1\u00b0\1\50\1\uffff\3\50"+
        "\1\uffff\1\u00b5\1\uffff\1\50\1\u00b7\1\uffff\1\u00b8\1\u00b9\3"+
        "\uffff\2\50\1\uffff\7\50\1\u00c3\1\uffff\4\50\1\uffff\1\u00c8\2"+
        "\50\1\uffff\4\50\1\uffff\1\50\3\uffff\1\u00b9\10\50\1\uffff\3\50"+
        "\1\u00db\1\uffff\1\50\1\u009a\5\50\1\u009a\3\50\1\u009a\1\50\1\u00e6"+
        "\1\u009a\1\u00e7\2\u009a\1\uffff\1\50\1\u00ea\1\u00eb\1\u00ec\1"+
        "\50\1\u00ee\4\50\2\uffff\2\50\3\uffff\1\u009a\1\uffff\1\u009e\2"+
        "\50\1\u009a\5\50\1\u00fc\2\u009e\1\50\1\uffff\1\50\1\u009a";
    static final String DFA14_eofS =
        "\u00ff\uffff";
    static final String DFA14_minS =
        "\1\11\3\uffff\1\56\1\72\1\uffff\1\165\1\157\1\154\1\145\1\154\1"+
        "\146\1\145\1\157\1\141\1\150\3\uffff\1\55\1\52\2\uffff\1\76\2\uffff"+
        "\2\156\1\157\1\uffff\1\141\1\145\1\141\1\157\1\162\2\145\1\60\11"+
        "\uffff\1\160\1\144\1\154\1\157\1\146\1\163\1\144\1\151\2\60\1\145"+
        "\1\160\1\125\1\164\1\154\1\164\1\143\1\163\2\145\1\165\3\uffff\1"+
        "\144\1\60\1\145\1\162\1\154\1\162\1\161\1\147\1\154\1\144\1\154"+
        "\1\162\1\141\1\152\1\uffff\1\154\1\171\1\164\1\154\1\163\1\60\1"+
        "\151\1\145\1\151\1\163\1\uffff\1\164\1\60\1\uffff\1\162\1\154\1"+
        "\156\1\60\1\154\1\60\1\153\1\164\1\60\1\156\1\145\2\60\1\uffff\2"+
        "\60\1\163\1\101\1\60\1\165\1\60\1\154\2\145\2\164\2\145\1\60\2\145"+
        "\1\165\1\uffff\1\166\1\60\1\146\1\141\1\164\1\60\1\154\1\uffff\1"+
        "\141\2\151\1\uffff\1\60\1\uffff\1\141\1\60\1\uffff\2\60\3\uffff"+
        "\1\145\1\154\1\uffff\2\145\1\162\1\143\1\145\1\151\1\143\1\60\1"+
        "\uffff\1\170\1\143\1\162\1\145\1\uffff\1\60\1\143\1\163\1\uffff"+
        "\1\151\1\164\1\145\1\161\1\uffff\1\147\3\uffff\1\60\1\154\1\156"+
        "\1\143\1\145\1\164\1\144\1\143\1\164\1\uffff\2\164\1\145\1\60\1"+
        "\uffff\1\153\1\60\1\144\1\145\1\163\1\165\1\145\1\60\1\143\1\164"+
        "\1\144\1\60\1\102\5\60\1\uffff\1\141\3\60\1\145\1\60\1\145\1\151"+
        "\1\123\1\171\2\uffff\1\145\1\147\3\uffff\1\60\1\uffff\1\60\1\157"+
        "\1\145\1\60\1\163\1\145\1\156\2\164\3\60\1\145\1\uffff\1\144\1\60";
    static final String DFA14_maxS =
        "\1\175\3\uffff\1\56\1\72\1\uffff\1\165\2\157\1\145\1\170\1\164\1"+
        "\145\1\165\2\162\3\uffff\1\76\1\52\2\uffff\1\76\2\uffff\1\156\1"+
        "\162\1\157\1\uffff\1\157\1\145\1\141\1\157\1\162\1\164\1\145\1\71"+
        "\11\uffff\1\160\1\144\1\156\1\157\1\162\1\163\1\144\1\151\2\172"+
        "\1\145\1\160\1\125\1\164\1\154\1\164\1\143\1\163\2\145\1\165\3\uffff"+
        "\1\171\1\172\1\145\1\162\1\154\1\162\1\164\1\147\1\154\1\144\1\154"+
        "\1\162\1\141\1\152\1\uffff\1\154\1\171\1\164\1\154\1\163\1\172\1"+
        "\151\1\145\1\160\1\163\1\uffff\1\164\1\172\1\uffff\1\162\1\154\1"+
        "\156\1\172\1\154\1\172\1\153\1\164\1\172\1\156\1\145\2\172\1\uffff"+
        "\2\172\1\163\1\101\1\172\1\165\1\172\1\154\2\145\2\164\2\145\1\172"+
        "\2\145\1\165\1\uffff\1\166\1\172\1\146\1\141\1\164\1\172\1\154\1"+
        "\uffff\1\141\2\151\1\uffff\1\172\1\uffff\1\141\1\172\1\uffff\2\172"+
        "\3\uffff\1\145\1\154\1\uffff\2\145\1\162\1\143\1\145\1\151\1\143"+
        "\1\172\1\uffff\1\170\1\143\1\162\1\145\1\uffff\1\172\1\143\1\163"+
        "\1\uffff\1\151\1\164\1\145\1\161\1\uffff\1\147\3\uffff\1\172\1\154"+
        "\1\156\1\143\1\145\1\164\1\144\1\143\1\164\1\uffff\2\164\1\145\1"+
        "\172\1\uffff\1\153\1\172\1\144\1\145\1\163\1\165\1\145\1\172\1\143"+
        "\1\164\1\144\1\172\1\102\5\172\1\uffff\1\141\3\172\1\145\1\172\1"+
        "\145\1\151\1\123\1\171\2\uffff\1\145\1\147\3\uffff\1\172\1\uffff"+
        "\1\172\1\157\1\145\1\172\1\163\1\145\1\156\2\164\3\172\1\145\1\uffff"+
        "\1\144\1\172";
    static final String DFA14_acceptS =
        "\1\uffff\1\1\1\2\1\3\2\uffff\1\7\12\uffff\1\34\1\35\1\36\2\uffff"+
        "\1\42\1\43\1\uffff\1\44\1\45\3\uffff\1\54\10\uffff\1\62\1\63\1\64"+
        "\1\65\1\66\1\4\1\41\1\6\1\5\25\uffff\1\37\1\40\1\46\16\uffff\1\61"+
        "\12\uffff\1\20\2\uffff\1\21\15\uffff\1\51\22\uffff\1\13\7\uffff"+
        "\1\23\3\uffff\1\26\1\uffff\1\47\2\uffff\1\32\2\uffff\1\50\1\57\1"+
        "\52\2\uffff\1\56\10\uffff\1\11\4\uffff\1\15\3\uffff\1\22\4\uffff"+
        "\1\27\1\uffff\1\31\1\33\1\55\11\uffff\1\10\4\uffff\1\16\22\uffff"+
        "\1\14\12\uffff\1\60\1\12\2\uffff\1\24\1\25\1\53\1\uffff\1\30\15"+
        "\uffff\1\17\2\uffff";
    static final String DFA14_specialS =
        "\u00ff\uffff}>";
    static final String[] DFA14_transitionS = {
            "\1\51\1\52\1\uffff\1\51\1\52\22\uffff\1\51\4\uffff\1\27\1\uffff"+
            "\1\53\1\1\1\2\1\27\1\26\1\3\1\24\1\4\1\25\1\46\11\47\1\5\1\6"+
            "\1\30\1\32\1\31\1\uffff\1\36\1\50\1\41\1\42\13\50\1\43\3\50"+
            "\1\40\1\7\6\50\4\uffff\1\50\1\uffff\1\33\1\10\1\11\1\12\1\13"+
            "\1\37\2\50\1\14\2\50\1\15\1\50\1\16\1\34\1\17\1\50\1\45\1\44"+
            "\1\20\3\50\1\35\2\50\1\21\1\22\1\23",
            "",
            "",
            "",
            "\1\54",
            "\1\56",
            "",
            "\1\60",
            "\1\61",
            "\1\63\2\uffff\1\62",
            "\1\64",
            "\1\65\1\uffff\1\66\11\uffff\1\67",
            "\1\70\6\uffff\1\73\1\71\4\uffff\1\74\1\72",
            "\1\75",
            "\1\77\5\uffff\1\76",
            "\1\100\15\uffff\1\101\2\uffff\1\102",
            "\1\103\11\uffff\1\104",
            "",
            "",
            "",
            "\1\105\20\uffff\1\55",
            "\1\106",
            "",
            "",
            "\1\107",
            "",
            "",
            "\1\110",
            "\1\112\3\uffff\1\111",
            "\1\113",
            "",
            "\1\114\15\uffff\1\115",
            "\1\116",
            "\1\117",
            "\1\120",
            "\1\121",
            "\1\122\11\uffff\1\123\4\uffff\1\124",
            "\1\125",
            "\12\126",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\127",
            "\1\130",
            "\1\132\1\uffff\1\131",
            "\1\133",
            "\1\134\13\uffff\1\135",
            "\1\136",
            "\1\137",
            "\1\140",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\10\50\1\142\14\50"+
            "\1\143\4\50",
            "\1\145",
            "\1\146",
            "\1\147",
            "\1\150",
            "\1\151",
            "\1\152",
            "\1\153",
            "\1\154",
            "\1\155",
            "\1\156",
            "\1\157",
            "",
            "",
            "",
            "\1\160\24\uffff\1\161",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\163",
            "\1\164",
            "\1\165",
            "\1\166",
            "\1\170\2\uffff\1\167",
            "\1\171",
            "\1\172",
            "\1\173",
            "\1\174",
            "\1\175",
            "\1\176",
            "\1\177",
            "",
            "\1\u0080",
            "\1\u0081",
            "\1\u0082",
            "\1\u0083",
            "\1\u0084",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0086",
            "\1\u0087",
            "\1\u0088\6\uffff\1\u0089",
            "\1\u008a",
            "",
            "\1\u008b",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\1\u008c\31\50",
            "",
            "\1\u008e",
            "\1\u008f",
            "\1\u0090",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0092",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0094",
            "\1\u0095",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u0097",
            "\1\u0098",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u009c",
            "\1\u009d",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u009f",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00a0",
            "\1\u00a1",
            "\1\u00a2",
            "\1\u00a3",
            "\1\u00a4",
            "\1\u00a5",
            "\1\u00a6",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00a8",
            "\1\u00a9",
            "\1\u00aa",
            "",
            "\1\u00ab",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00ad",
            "\1\u00ae",
            "\1\u00af",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00b1",
            "",
            "\1\u00b2",
            "\1\u00b3",
            "\1\u00b4",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u00b6",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "",
            "",
            "\1\u00ba",
            "\1\u00bb",
            "",
            "\1\u00bc",
            "\1\u00bd",
            "\1\u00be",
            "\1\u00bf",
            "\1\u00c0",
            "\1\u00c1",
            "\1\u00c2",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u00c4",
            "\1\u00c5",
            "\1\u00c6",
            "\1\u00c7",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00c9",
            "\1\u00ca",
            "",
            "\1\u00cb",
            "\1\u00cc",
            "\1\u00cd",
            "\1\u00ce",
            "",
            "\1\u00cf",
            "",
            "",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00d0",
            "\1\u00d1",
            "\1\u00d2",
            "\1\u00d3",
            "\1\u00d4",
            "\1\u00d5",
            "\1\u00d6",
            "\1\u00d7",
            "",
            "\1\u00d8",
            "\1\u00d9",
            "\1\u00da",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u00dc",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00dd",
            "\1\u00de",
            "\1\u00df",
            "\1\u00e0",
            "\1\u00e1",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00e2",
            "\1\u00e3",
            "\1\u00e4",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00e5",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\15\50\1\u00e8\14\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u00e9",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00ed",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00ef",
            "\1\u00f0",
            "\1\u00f1",
            "\1\u00f2",
            "",
            "",
            "\1\u00f3",
            "\1\u00f4",
            "",
            "",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00f5",
            "\1\u00f6",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00f7",
            "\1\u00f8",
            "\1\u00f9",
            "\1\u00fa",
            "\1\u00fb",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00fd",
            "",
            "\1\u00fe",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50"
    };

    static final short[] DFA14_eot = DFA.unpackEncodedString(DFA14_eotS);
    static final short[] DFA14_eof = DFA.unpackEncodedString(DFA14_eofS);
    static final char[] DFA14_min = DFA.unpackEncodedStringToUnsignedChars(DFA14_minS);
    static final char[] DFA14_max = DFA.unpackEncodedStringToUnsignedChars(DFA14_maxS);
    static final short[] DFA14_accept = DFA.unpackEncodedString(DFA14_acceptS);
    static final short[] DFA14_special = DFA.unpackEncodedString(DFA14_specialS);
    static final short[][] DFA14_transition;

    static {
        int numStates = DFA14_transitionS.length;
        DFA14_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA14_transition[i] = DFA.unpackEncodedString(DFA14_transitionS[i]);
        }
    }

    class DFA14 extends DFA {

        public DFA14(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 14;
            this.eot = DFA14_eot;
            this.eof = DFA14_eof;
            this.min = DFA14_min;
            this.max = DFA14_max;
            this.accept = DFA14_accept;
            this.special = DFA14_special;
            this.transition = DFA14_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | SL_COMMENT | ML_COMMENT | NAVIGATION_OPERATOR | ADDITIVE_OPERATOR | MULT_OPERATOR | RELATIONAL_OPERATOR | EQUALITY_OPERATOR | NEQUALITY_OPERATOR | NOT_OPERATOR | AND_OPERATOR | OR_OPERATOR | XOR_OPERATOR | IMPLIES_OPERATOR | IS_MARKED_PRE | BOOLEAN_LITERAL | COLLECTION_TYPES | ITERATOR_NAME | STATIC | INTEGER_0 | INTEGER_LITERAL | SIMPLE_NAME | WHITESPACE | LINEBREAKS | QUOTED_39_39 );";
        }
    }
 

}