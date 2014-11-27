/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.analysis;

import java.util.Map;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <p>
 * A default implementation for token resolvers. Generated token resolvers
 * delegate calls to this class to convert text (i.e., tokens) to Java objects.
 * This default implementation tries to perform this conversion using the
 * EMF-based data type serialization mechanism using EcoreUtil.createFromString().
 * </p>
 * 
 * <p>
 * In addition, enumeration literals are converted to the respective literal
 * object, if the text (i.e., the token) matches the literal.
 * </p>
 * 
 * <p>
 * For boolean attributes the token is considered to represent <code>true</code>
 * if it matches the name of the attribute. Attributes that have names like
 * <code>isFoo</code> are also interpret as <code>true</code> if the text is
 * <code>foo</code>.
 * </p>
 * 
 * <p>
 * The behavior of this resolving can be customized by either changing the
 * generated token resolver classes or by using custom EMF data type converters.
 * </p>
 */
public class OclDefaultTokenResolver implements org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver {
	
	private Map<?, ?> options;
	private boolean escapeKeywords;
	
	/**
	 * This constructor is used by token resolvers that were generated before EMFText
	 * 1.4.0. It does not enable automatic escaping and unescaping of keywords.
	 */
	public OclDefaultTokenResolver() {
		this(false);
	}
	
	/**
	 * This constructor is used by token resolvers that were generated with EMFText
	 * 1.4.0 and later releases. It can optionally enable automatic escaping and
	 * unescaping of keywords.
	 */
	public OclDefaultTokenResolver(boolean escapeKeywords) {
		super();
		this.escapeKeywords = escapeKeywords;
	}
	
	public void resolve(String lexem, EStructuralFeature feature, org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result) {
		resolve(lexem, feature, result, null, null, null);
	}
	
	public void resolve(String lexem, EStructuralFeature feature, org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolveResult result, String suffix, String prefix, String escapeCharacter) {
		// Step 1: unescape keywords if required
		if (escapeKeywords && lexem.startsWith("_")) {
			for (String keyword : org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.INSTANCE.getKeywords()) {
				if (lexem.endsWith(keyword)) {
					String keywordPrefix = lexem.substring(0, lexem.length() - keyword.length());
					if (keywordPrefix.matches("_+")) {
						lexem = lexem.substring(1);
						break;
					}
				}
			}
		}
		
		// Step 2: remove prefix, suffix and unescape escaped suffixes
		// Step 2a: remove prefix
		if (prefix != null) {
			int count = prefix.length();
			lexem = lexem.substring(count);
		}
		// Step 2b: remove suffix
		if (suffix != null) {
			int count = suffix.length();
			lexem = lexem.substring(0, lexem.length() - count );
			// take care of the escape character (may be null)
			// Step 2c: replaced escaped suffixes and escaped escape sequences
			if (escapeCharacter != null) {
				lexem = lexem.replace(escapeCharacter + suffix, suffix);
				lexem = lexem.replace(escapeCharacter + escapeCharacter, escapeCharacter);
			}
		}
		
		// Step 3: convert text to Java object
		if (feature instanceof EAttribute) {
			EClassifier featureType = feature.getEType();
			if (featureType instanceof EEnum) {
				EEnumLiteral literal = ((EEnum) featureType).getEEnumLiteralByLiteral(lexem);
				if (literal != null) {
					result.setResolvedToken(literal.getInstance());
					return;
				} else {
					result.setErrorMessage("Could not map lexem '" + lexem + "' to enum '" + featureType.getName() + "'.");
					return;
				}
			} else if (featureType instanceof EDataType) {
				try {
					result.setResolvedToken(EcoreUtil.createFromString((EDataType) featureType, lexem));
				} catch (Exception e) {
					result.setErrorMessage("Could not convert '" + lexem + "' to '" + featureType.getName() + "'.");
				}
				String typeName = featureType.getInstanceClassName();
				if (typeName.equals("boolean") || java.lang.Boolean.class.getName().equals(typeName)) {
					String featureName = feature.getName();
					boolean featureNameMatchesLexem = featureName.equals(lexem);
					if (featureNameMatchesLexem) {
						result.setResolvedToken(true);
						return;
					}
					if (featureName.length() > 2 && featureName.startsWith("is")) {
						if ((featureName.substring(2, 3).toLowerCase() + featureName.substring(3)).equals(lexem)) {
							result.setResolvedToken(true);
							return;
						}
					}
					if (Boolean.parseBoolean(lexem)) {
						result.setResolvedToken(true);
						return;
					}
				}
			} else {
				assert false;
			}
		} else {
			result.setResolvedToken(lexem);
			return;
		}
	}
	
	public String deResolve(Object value, EStructuralFeature feature, EObject container) {
		return deResolve(value, feature, container, null, null, null);
	}
	
	public String deResolve(Object value, EStructuralFeature feature, EObject container, String prefix, String suffix, String escapeCharacter) {
		// Step 1: convert Java object to text
		String result = "";
		if (value != null) {
			result = value.toString();
		}
		
		// Step 2: escape suffixes, add prefix and suffix
		// Step 2a: escaped suffix
		if (suffix != null) {
			// take care of the escape character (may be null)
			if (escapeCharacter != null) {
				result = result.replace(escapeCharacter, escapeCharacter + escapeCharacter);
				result = result.replace(suffix, escapeCharacter + suffix);
			}
			// Step 2b: append suffix
			result += suffix;
		}
		// Step 2c: prepend prefix
		if (prefix != null) {
			result = prefix + result;
		}
		
		// Step 3: escape keywords if required
		if (escapeKeywords && result != null) {
			// Escape keywords if required
			for (String keyword : org.dresdenocl.language.ocl.resource.ocl.grammar.OclGrammarInformationProvider.INSTANCE.getKeywords()) {
				if (result.endsWith(keyword)) {
					String keywordPrefix = result.substring(0, result.length() - keyword.length());
					if (keywordPrefix.matches("_*")) {
						result = "_" + result;
						break;
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * This method can be used to disable automatic escaping and unescaping of tokens
	 * that match keywords of the syntax.
	 */
	public void setEscapeKeywords(boolean escapeKeywords) {
		this.escapeKeywords = escapeKeywords;
	}
	
	public void setOptions(Map<?, ?> options) {
		this.options = options;
	}
	
	public Map<?, ?> getOptions() {
		return options;
	}
	
}
