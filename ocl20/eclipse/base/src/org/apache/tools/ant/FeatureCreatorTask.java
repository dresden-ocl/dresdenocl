package org.apache.tools.ant;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FeatureCreatorTask extends Task
{
	private final static String copyright = "http://www.example.com/copyright";
	private final static String licence = "http://www.example.com/license";
	private final static String description = "http://www.example.com/description";
	private final static String copyrightDescription = " [Enter Copyright Description here.]";
	private final static String licenceDescription = " [Enter License Description here.]";
	private final static String descriptionDescription = " [Enter Feature Description here.]";
	
	protected String pluginXmlLoc;
	protected String featureLoc;
	
	private String pluginID;
	private String pluginVersion;
	private String pluginProvider;
	private String pluginName;
	private Node requires = null;
	
	public void setFeatureLoc(String featureLoc)
	{
		this.featureLoc = featureLoc;
	}
	
	public void setPluginXmlLoc(String pluginXmlLoc)
	{
		this.pluginXmlLoc = pluginXmlLoc;
	}
	
	private void readPluginInfo() throws Exception
	{
		File pluginFile = new File(pluginXmlLoc);
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document pluginDoc = builder.parse(pluginFile);
		NodeList elements = pluginDoc.getElementsByTagName("plugin");
		if (elements != null)
		{
			NamedNodeMap attributes = elements.item(0).getAttributes();
			pluginVersion = attributes.getNamedItem("version").getNodeValue();
			pluginID = attributes.getNamedItem("id").getNodeValue();
			pluginName = attributes.getNamedItem("name").getNodeValue();
			pluginProvider = attributes.getNamedItem("provider-name").getNodeValue();
			
			elements = elements.item(0).getChildNodes();
			if (elements != null)
			{
				int i = 0;
				while (!elements.item(i).getNodeName().equals("requires"))
					i++;
				if (i < elements.getLength())
					requires = elements.item(i);				
			}				
		}		
	}
	
	public void execute()
	{
		try
		{
			this.readPluginInfo();
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document featureDoc = builder.newDocument();
			featureDoc.setXmlVersion("1.0");
			
			Element rootNode = featureDoc.createElement("feature");
			featureDoc.appendChild(rootNode);
			rootNode.setAttribute("id",this.pluginID);
			rootNode.setAttribute("label",this.pluginName + "-Feature");
			rootNode.setAttribute("version",this.pluginVersion);
			rootNode.setAttribute("provider-name",this.pluginProvider);
						
			Element childNode = featureDoc.createElement("description");
			childNode.setAttribute("url",description);
			childNode.setTextContent(descriptionDescription);
			rootNode.appendChild(childNode);
			
			childNode = featureDoc.createElement("copyright");
			childNode.setAttribute("url",copyright);
			childNode.setTextContent(copyrightDescription);
			rootNode.appendChild(childNode);
			
			childNode = featureDoc.createElement("license");
			childNode.setAttribute("url",licence);
			childNode.setTextContent(licenceDescription);
			rootNode.appendChild(childNode);
			
			if (this.requires != null)
				rootNode.appendChild(featureDoc.importNode(requires, true));
			
			childNode = featureDoc.createElement("plugin");
			childNode.setAttribute("id",this.pluginID);
			childNode.setAttribute("download-size","0");
			childNode.setAttribute("install-size","0");
			childNode.setAttribute("version",this.pluginVersion);
			rootNode.appendChild(childNode);
			
			String featureLoc = project.getProperty("basedir");
			featureLoc += File.separator + this.featureLoc + 
						  File.separator + this.pluginID + "_" + this.pluginVersion;
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			DOMSource source = new DOMSource(featureDoc);
			File sourceFile = new File(featureLoc);
			sourceFile.mkdirs();
			sourceFile = new File(sourceFile, "feature.xml");
			FileOutputStream os = new FileOutputStream(sourceFile);
			StreamResult result = new StreamResult(os);
			transformer.transform(source, result);
			os.flush();
			os.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
