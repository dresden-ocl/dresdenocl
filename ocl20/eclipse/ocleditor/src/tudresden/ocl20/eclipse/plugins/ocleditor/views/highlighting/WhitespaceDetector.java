/*
 * Created on 10.09.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tudresden.ocl20.eclipse.plugins.ocleditor.views.highlighting;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

/**
 * An instance of this class is used to identify all whitespace characters.
 * @author Mirko
 */
public class WhitespaceDetector implements IWhitespaceDetector {

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.rules.IWhitespaceDetector#isWhitespace(char)
	 */
	public boolean isWhitespace(char c) 
	{
		if (c == ' ')
			return true;
		return false;
	}

}
