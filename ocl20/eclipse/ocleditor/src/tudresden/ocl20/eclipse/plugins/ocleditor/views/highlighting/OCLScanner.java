/*
 * Created on 10.09.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tudresden.ocl20.eclipse.plugins.ocleditor.views.highlighting;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

/**
 * An instance of this class is used to identify all OCL comments, OCL keywords 
 * and whitespace in the text of the OCL-Editor.
 * @author Mirko
 */
public class OCLScanner extends RuleBasedScanner 
{
	private String[] keyWords = {"and",
			 					 "attr",
								 "context",
								 "def",
								 "else",								 
								 "endif",
								 "endpackage",
								 "if",
								 "implies",
								 "in",
								 "inv",
								 "let",
								 "not",
								 "oper",
								 "or",
								 "package",
								 "post",
								 "pre",
								 "then",
								 "xor"};
	
	public static final String KEYWORD = "keyWord";
	public static final String COMMENT = "singleComment";
	public static final String DEFAULT = "default";
	public static final String[] PARTITION_TYPES = {KEYWORD,
													COMMENT,
													DEFAULT};
	
	public OCLScanner() 
	{
		IToken keywordToken = new Token(new TextAttribute(Display.getCurrent()
				.getSystemColor(SWT.COLOR_DARK_RED)));
		IToken defaultToken = new Token(new TextAttribute(Display.getCurrent()
				.getSystemColor(SWT.COLOR_BLACK)));
		IToken commentToken = new Token(new TextAttribute(Display.getCurrent()
				.getSystemColor(SWT.COLOR_GREEN)));
		
		IRule[] rules = new IRule[3];
		
		rules[0] = new WhitespaceRule(new WhitespaceDetector());
		rules[1] = new EndOfLineRule("--",commentToken);
		
		WordRule wordRule = new WordRule(new WordDetector(), defaultToken);
		for (int i = 0; i < this.keyWords.length; i++)
			wordRule.addWord(this.keyWords[i], keywordToken);
		
		rules[2] = wordRule;
		
		setRules(rules);		
	}
}
