/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.ui;

import java.util.Collection;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * This class can be used to initialize default preference values.
 */
public class OclPreferenceInitializer extends AbstractPreferenceInitializer {
	
	public void initializeDefaultPreferences() {
		
		initializeDefaultSyntaxHighlighting();
		initializeDefaultBrackets();
		initializeDefaultsContentAssist();
		
		IPreferenceStore store = org.dresdenocl.language.ocl.resource.ocl.ui.OclUIPlugin.getDefault().getPreferenceStore();
		// Set default value for matching brackets
		store.setDefault(org.dresdenocl.language.ocl.resource.ocl.ui.OclPreferenceConstants.EDITOR_MATCHING_BRACKETS_COLOR, "192,192,192");
		store.setDefault(org.dresdenocl.language.ocl.resource.ocl.ui.OclPreferenceConstants.EDITOR_MATCHING_BRACKETS_CHECKBOX, true);
		
	}
	
	protected void initializeDefaultBrackets() {
		IPreferenceStore store = org.dresdenocl.language.ocl.resource.ocl.ui.OclUIPlugin.getDefault().getPreferenceStore();
		initializeDefaultBrackets(store, new org.dresdenocl.language.ocl.resource.ocl.mopp.OclMetaInformation());
	}
	
	protected void initializeDefaultBrackets(IPreferenceStore store, org.dresdenocl.language.ocl.resource.ocl.IOclMetaInformation metaInformation) {
		String languageId = metaInformation.getSyntaxName();
		// set default brackets
		org.dresdenocl.language.ocl.resource.ocl.ui.OclBracketSet bracketSet = new org.dresdenocl.language.ocl.resource.ocl.ui.OclBracketSet();
		final Collection<org.dresdenocl.language.ocl.resource.ocl.IOclBracketPair> bracketPairs = metaInformation.getBracketPairs();
		if (bracketPairs != null) {
			for (org.dresdenocl.language.ocl.resource.ocl.IOclBracketPair bracketPair : bracketPairs) {
				bracketSet.addBracketPair(bracketPair.getOpeningBracket(), bracketPair.getClosingBracket(), bracketPair.isClosingEnabledInside(), bracketPair.isCloseAfterEnter());
			}
		}
		store.setDefault(languageId + org.dresdenocl.language.ocl.resource.ocl.ui.OclPreferenceConstants.EDITOR_BRACKETS_SUFFIX, bracketSet.serialize());
	}
	
	public void initializeDefaultSyntaxHighlighting() {
		IPreferenceStore store = org.dresdenocl.language.ocl.resource.ocl.ui.OclUIPlugin.getDefault().getPreferenceStore();
		initializeDefaultSyntaxHighlighting(store, new org.dresdenocl.language.ocl.resource.ocl.mopp.OclMetaInformation());
	}
	
	protected void initializeDefaultSyntaxHighlighting(IPreferenceStore store, org.dresdenocl.language.ocl.resource.ocl.mopp.OclMetaInformation metaInformation) {
		String languageId = metaInformation.getSyntaxName();
		String[] tokenNames = metaInformation.getSyntaxHighlightableTokenNames();
		if (tokenNames == null) {
			return;
		}
		for (int i = 0; i < tokenNames.length; i++) {
			String tokenName = tokenNames[i];
			org.dresdenocl.language.ocl.resource.ocl.IOclTokenStyle style = metaInformation.getDefaultTokenStyle(tokenName);
			if (style != null) {
				String color = getColorString(style.getColorAsRGB());
				setProperties(store, languageId, tokenName, color, style.isBold(), true, style.isItalic(), style.isStrikethrough(), style.isUnderline());
			} else {
				setProperties(store, languageId, tokenName, "0,0,0", false, false, false, false, false);
			}
		}
	}
	
	private void initializeDefaultsContentAssist() {
		IPreferenceStore store = org.dresdenocl.language.ocl.resource.ocl.ui.OclUIPlugin.getDefault().getPreferenceStore();
		store.setDefault(org.dresdenocl.language.ocl.resource.ocl.ui.OclPreferenceConstants.EDITOR_CONTENT_ASSIST_ENABLED, org.dresdenocl.language.ocl.resource.ocl.ui.OclPreferenceConstants.EDITOR_CONTENT_ASSIST_ENABLED_DEFAULT);
		store.setDefault(org.dresdenocl.language.ocl.resource.ocl.ui.OclPreferenceConstants.EDITOR_CONTENT_ASSIST_DELAY, org.dresdenocl.language.ocl.resource.ocl.ui.OclPreferenceConstants.EDITOR_CONTENT_ASSIST_DELAY_DEFAULT);
		store.setDefault(org.dresdenocl.language.ocl.resource.ocl.ui.OclPreferenceConstants.EDITOR_CONTENT_ASSIST_TRIGGERS, org.dresdenocl.language.ocl.resource.ocl.ui.OclPreferenceConstants.EDITOR_CONTENT_ASSIST_TRIGGERS_DEFAULT);
	}
	
	protected void setProperties(IPreferenceStore store, String languageID, String tokenName, String color, boolean bold, boolean enable, boolean italic, boolean strikethrough, boolean underline) {
		store.setDefault(org.dresdenocl.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.dresdenocl.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.StyleProperty.BOLD), bold);
		store.setDefault(org.dresdenocl.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.dresdenocl.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.StyleProperty.COLOR), color);
		store.setDefault(org.dresdenocl.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.dresdenocl.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.StyleProperty.ENABLE), enable);
		store.setDefault(org.dresdenocl.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.dresdenocl.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.StyleProperty.ITALIC), italic);
		store.setDefault(org.dresdenocl.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.dresdenocl.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.StyleProperty.STRIKETHROUGH), strikethrough);
		store.setDefault(org.dresdenocl.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.dresdenocl.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.StyleProperty.UNDERLINE), underline);
	}
	
	protected String getColorString(int[] colorAsRGB) {
		if (colorAsRGB == null) {
			return "0,0,0";
		}
		if (colorAsRGB.length != 3) {
			return "0,0,0";
		}
		return colorAsRGB[0] + "," +colorAsRGB[1] + ","+ colorAsRGB[2];
	}
	
}

