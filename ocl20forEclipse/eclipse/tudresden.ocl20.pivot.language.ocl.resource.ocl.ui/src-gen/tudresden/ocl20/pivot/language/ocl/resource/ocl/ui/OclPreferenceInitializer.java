/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.ui;

/**
 * A class used to initialize default preference values.
 */
public class OclPreferenceInitializer extends org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer {
	
	public void initializeDefaultPreferences() {
		
		initializeDefaultSyntaxHighlighting();
		initializeDefaultBrackets();
		
		org.eclipse.jface.preference.IPreferenceStore store = tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclUIPlugin.getDefault().getPreferenceStore();
		// Set default value for matching brackets
		store.setDefault(tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclPreferenceConstants.EDITOR_MATCHING_BRACKETS_COLOR, "192,192,192");
		store.setDefault(tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclPreferenceConstants.EDITOR_MATCHING_BRACKETS_CHECKBOX, true);
		
	}
	
	private void initializeDefaultBrackets() {
		org.eclipse.jface.preference.IPreferenceStore store = tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclUIPlugin.getDefault().getPreferenceStore();
		initializeDefaultBrackets(store, new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclMetaInformation());
	}
	
	public void initializeDefaultSyntaxHighlighting() {
		org.eclipse.jface.preference.IPreferenceStore store = tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclUIPlugin.getDefault().getPreferenceStore();
		initializeDefaultSyntaxHighlighting(store, new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclMetaInformation());
	}
	
	private void initializeDefaultBrackets(org.eclipse.jface.preference.IPreferenceStore store, tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclMetaInformation metaInformation) {
		String languageId = metaInformation.getSyntaxName();
		// set default brackets for ITextResource bracket set
		tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclBracketSet bracketSet = new tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclBracketSet(null, null);
		final java.util.Collection<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclBracketPair> bracketPairs = metaInformation.getBracketPairs();
		if (bracketPairs != null) {
			for (tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclBracketPair bracketPair : bracketPairs) {
				bracketSet.addBracketPair(bracketPair.getOpeningBracket(), bracketPair.getClosingBracket(), bracketPair.isClosingEnabledInside());
			}
		}
		store.setDefault(languageId + tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclPreferenceConstants.EDITOR_BRACKETS_SUFFIX, bracketSet.getBracketString());
	}
	
	private void initializeDefaultSyntaxHighlighting(org.eclipse.jface.preference.IPreferenceStore store, tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclMetaInformation metaInformation) {
		String languageId = metaInformation.getSyntaxName();
		String[] tokenNames = metaInformation.getSyntaxHighlightableTokenNames();
		if (tokenNames == null) {
			return;
		}
		for (int i = 0; i < tokenNames.length; i++) {
			String tokenName = tokenNames[i];
			tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTokenStyle style = metaInformation.getDefaultTokenStyle(tokenName);
			if (style != null) {
				String color = getColorString(style.getColorAsRGB());
				setProperties(store, languageId, tokenName, color, style.isBold(), true, style.isItalic(), style.isStrikethrough(), style.isUnderline());
			} else {
				setProperties(store, languageId, tokenName, "0,0,0", false, false, false, false, false);
			}
		}
	}
	
	private void setProperties(org.eclipse.jface.preference.IPreferenceStore store, String languageID, String tokenName, String color, boolean bold, boolean enable, boolean italic, boolean strikethrough, boolean underline) {
		store.setDefault(tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.StyleProperty.BOLD), bold);
		store.setDefault(tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.StyleProperty.COLOR), color);
		store.setDefault(tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.StyleProperty.ENABLE), enable);
		store.setDefault(tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.StyleProperty.ITALIC), italic);
		store.setDefault(tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.StyleProperty.STRIKETHROUGH), strikethrough);
		store.setDefault(tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.StyleProperty.UNDERLINE), underline);
	}
	
	private String getColorString(int[] colorAsRGB) {
		if (colorAsRGB == null) {
			return "0,0,0";
		}
		if (colorAsRGB.length != 3) {
			return "0,0,0";
		}
		return colorAsRGB[0] + "," +colorAsRGB[1] + ","+ colorAsRGB[2];
	}
}
