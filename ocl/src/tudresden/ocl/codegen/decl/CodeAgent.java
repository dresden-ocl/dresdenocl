/*
Copyright (C) 2000  Sten Loecher

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

*/
package tudresden.ocl.codegen.decl;

import java.util.Hashtable;
import java.util.Enumeration;
import java.lang.Exception;
import java.io.IOException;

import org.apache.xerces.parsers.DOMParser;
// import com.ibm.xml.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * Chooses code templates from a XML file that relates to a given specification.
 * @author Sten Loecher
 */
public class CodeAgent {
	Hashtable 	arguments;
	DOMParser 	xmlParser;
	Document 	xmlDocument;
	NodeList 	xmlPattern;
	boolean		connectorEnabled;
	String		ruleAndSpec;

	static final String TAGPATTERN 	= "pattern";
	static final String XMLFILE 	= "file:tudresden/ocl/codegen/decl/OCL2SQL4SQL92.xml";
	static final String ATTRID	= "id";
	static final String RULE	= "rule";
	static final String CONNECTOR	= "connector";
	static final String CONTRUE	= "true";
	static final String NOPATTERN	= "pattern for specified rule does not exist:";
	static final String NOTEMPLATE	= "template for Pattern with given specification does not exist: ";
	static final String TEMPLATE	= "template";
	static final String SPEC	= "spec";
	static final String LI		= "li";
	static final String PARAM	= "param";
	static final String NAME	= "name";
	static final String MISARG	= "missing argument specification: ";
	static final String MISPARNAME	= "missing name in parameter tag";

	public static void main(String args[]) {
		// only for test purposes
	}

        /**
         * Creates a CodeAgent object.
         * @param rules the URL of the file containing the mapping rules
         */
	public CodeAgent(String rules) {
		// prepare mapping source
              	xmlParser = new DOMParser();

		try {
    			xmlParser.parse(rules);
		} catch (SAXException se) {
    			se.printStackTrace();
		} catch (IOException ioe) {
    			ioe.printStackTrace();
		}

		xmlDocument = xmlParser.getDocument();
		xmlPattern = xmlDocument.getElementsByTagName(TAGPATTERN);

		// initialize arguments container
		arguments = new Hashtable();

		// disable connecting code
		connectorEnabled = false;
	}

        /**
         * Adds an argument specification to the current state.
         * @param argSpec the name or placeholder of the argument
         * @param argCont the replacement for the placeholder in the template
         */
	public void setArgument(String argSpec, String argCont) {
		arguments.put(new String(argSpec), new String(argCont));
	}

        /**
         * @return a code template with replaced placeholders
         * @param rule the name of the grammar rule
         * @param spec a specification that determines the template more precisely
         */
	public String getCodeFor(String rule, String spec) throws Exception {
		Node pattern, template;
		ruleAndSpec = " (" + rule + ", " + spec + ")";

		if ((pattern = getPatternForRule(rule)) != null) {
			if ((template = getTemplateForSpec(pattern, spec)) != null) {
				return getCode(template);
			} else {
				throw new Exception(NOTEMPLATE + rule + " -> " + spec);
			}
		} else {
			throw new Exception(NOPATTERN + rule);
		}
	}

        /**
         * Cleans up the CodeAgent object. That is, deleting all arguments and
         * disabling connector lines.
         */
	public void reset() {
		arguments.clear();
		connectorEnabled = false;
	}

        /**
         * Enables connector lines.
         */
	public void enableConnector() {
		connectorEnabled = true;
	}

	private Node getPatternForRule(String rule) {
		for (int i=0; i<xmlPattern.getLength(); i++) {
			Node pattern = xmlPattern.item(i);
			NamedNodeMap attributes;
			Node attRule;

			if ((attributes = pattern.getAttributes()) != null) {
				if ((attRule = attributes.getNamedItem(RULE)) != null) {
					if (attRule.getNodeValue().equals(rule)) {
						return pattern;
					}
				}
			}
		}

		return null;
	}

	private Node getTemplateForSpec(Node pattern, String spec) {
		NodeList pattCont = pattern.getChildNodes();
		NamedNodeMap attributes;
		Node attSpec;

		for (int i=0; i<pattCont.getLength(); i++) {
			if (!pattCont.item(i).getNodeName().equals(TEMPLATE))
				continue;
			if ((attributes = pattCont.item(i).getAttributes()) != null) {
				if ((attSpec = attributes.getNamedItem(SPEC)) != null) {
					if (attSpec.getNodeValue().equals(spec)) {
						return 	pattCont.item(i);
					}
				}
			}
		}

		return null;
	}

	private String getCode(Node template) throws Exception {
		NodeList tempCont = template.getChildNodes();
		NodeList liCont;
		NamedNodeMap paramName, liAttributes;
		String param, value;
		StringBuffer code = new StringBuffer();
		Node attConnector;

		for (int i=0; i<tempCont.getLength(); i++) {
			// ignore invalid tags
			if (!tempCont.item(i).getNodeName().equals(LI))
				continue;

			// filter connector lines if necessary
			if ((liAttributes = tempCont.item(i).getAttributes()) != null) {
				if ((attConnector = liAttributes.getNamedItem(CONNECTOR)) != null) {
					if ((attConnector.getNodeValue().equals(CONTRUE)) && (!connectorEnabled))
						continue;
				}
			}

			// get code
			liCont = tempCont.item(i).getChildNodes();
			for (int k=0; k<liCont.getLength(); k++) {
				// parameter to replace
				if (liCont.item(k).getNodeName().equals(PARAM)) {
					paramName = liCont.item(k).getAttributes();

					if (paramName.getNamedItem(NAME) != null) {
						param = paramName.getNamedItem(NAME).getNodeValue();
						if (arguments.get(param) != null) {
							code.append(arguments.get(param).toString());
						} else throw new Exception(MISARG + param + ruleAndSpec);
					} else throw new Exception(MISPARNAME);

					continue;
				}

				// ordinary text
				code.append(liCont.item(k).getNodeValue());
			}

			// carriage return + linefeed after each </li>
			code.append("\n");
		}

		return code.toString();
	}
}