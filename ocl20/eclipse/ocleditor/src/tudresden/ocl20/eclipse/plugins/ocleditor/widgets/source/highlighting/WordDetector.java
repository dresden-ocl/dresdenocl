/*
 * Created on 10.09.2005
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tudresden.ocl20.eclipse.plugins.ocleditor.widgets.source.highlighting;

import org.eclipse.jface.text.rules.IWordDetector;

/**
 * An instance of this class is used to identify all OCL keywords.
 * @author Mirko
 */
public class WordDetector implements IWordDetector 
{
	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.rules.IWordDetector#isWordStart(char)
	 */	
	public boolean isWordStart(char c) 
	{
		return (c > ' ' && c <= '~');
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.rules.IWordDetector#isWordPart(char)
	 */
	public boolean isWordPart(char c) 
	{
		return (c > ' ' && c <= '~');			
	}
}
