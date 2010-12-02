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

public class UpdateSiteCreatorTask extends Task
{
	protected String featureXmlLoc;
	protected String siteXmlLoc;
	protected String categoryName;
	protected String description;
	
	private String pluginID;
	private String pluginVersion;
	
	public void setCategoryName(String categoryName)
	{
		this.categoryName = categoryName;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setFeatureXmlLoc(String featureXmlLoc)
	{
		this.featureXmlLoc = featureXmlLoc;
	}

	public void setSiteXmlLoc(String siteXmlLoc)
	{
		this.siteXmlLoc = siteXmlLoc;
	}
	
	private void readPluginInfo() throws Exception
	{
		File pluginFile = new File(featureXmlLoc, "feature.xml");
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document pluginDoc = builder.parse(pluginFile);
		NodeList elements = pluginDoc.getElementsByTagName("plugin");
		if (elements != null)
		{
			NamedNodeMap attributes = elements.item(0).getAttributes();
			pluginVersion = attributes.getNamedItem("version").getNodeValue();
			pluginID = attributes.getNamedItem("id").getNodeValue();
		}		
	}
	
	private void createCategoryNode(Element rootNode)
	{
		Element categoryNode = null;
		Element descriptionNode = null;
		NodeList categoryList = rootNode.getOwnerDocument().getElementsByTagName("category-def");
		if (categoryList != null)
		{
			int i = 0;
			while (i < categoryList.getLength() && categoryNode == null)
			{
				NamedNodeMap attrList = categoryList.item(i).getAttributes();
				if (attrList != null && 
					attrList.getNamedItem("name") != null &&
					attrList.getNamedItem("name").getNodeValue().equals(this.categoryName))
					categoryNode = (Element) categoryList.item(i);
				i++;
			}
		}
		if (categoryNode == null)
		{
			categoryNode = rootNode.getOwnerDocument().createElement("category-def");
			descriptionNode = rootNode.getOwnerDocument().createElement("description");
			categoryNode.appendChild(descriptionNode);
			rootNode.appendChild(categoryNode);
		}
		else
			descriptionNode = (Element) categoryNode.getElementsByTagName("description").item(0);
		categoryNode.setAttribute("name",this.categoryName);
		categoryNode.setAttribute("label", "Description");
		descriptionNode.setTextContent(this.description);		
	}
	
	private void createFeatureNode(Element rootNode)
	{
		Element featureNode = null;
		Element categoryNode = null;
		NodeList featureList = rootNode.getOwnerDocument().getElementsByTagName("feature");
		if (featureList != null)
		{
			int i = 0;
			while (i < featureList.getLength() && featureNode == null)
			{
				NamedNodeMap attrList = featureList.item(i).getAttributes();
				if (attrList != null && 
					attrList.getNamedItem("id") != null &&
					attrList.getNamedItem("id").getNodeValue().equals(this.pluginID))
					featureNode = (Element) featureList.item(i);
				i++;
			}
		}
		if (featureNode == null)
		{
			featureNode = rootNode.getOwnerDocument().createElement("feature");
			categoryNode = rootNode.getOwnerDocument().createElement("category");			
			featureNode.appendChild(categoryNode);
			rootNode.appendChild(featureNode);			
		}
		else
			categoryNode = (Element) featureNode.getElementsByTagName("category").item(0);
		featureNode.setAttribute("url","features/" + this.pluginID + "_" + this.pluginVersion + ".jar");
		featureNode.setAttribute("id",this.pluginID);
		featureNode.setAttribute("version",this.pluginVersion);
		categoryNode.setAttribute("name",this.categoryName);
	}	
	
	public void execute()
	{
		try
		{
			this.readPluginInfo();
			File siteFile = new File(siteXmlLoc, "site.xml");
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document siteDoc = null;
			Element rootNode = null;
			if (siteFile.exists())
			{
				siteDoc = builder.parse(siteFile);
				NodeList elements = siteDoc.getElementsByTagName("site");
				if (elements != null && elements.getLength() > 0)
					rootNode = (Element) elements.item(0);
			}
			else
				siteDoc = builder.newDocument();
			
			siteDoc.setXmlVersion("1.0");
			
			if (rootNode == null)
			{
				rootNode = siteDoc.createElement("site");
				siteDoc.appendChild(rootNode);
			}
			
			this.createFeatureNode(rootNode);
			this.createCategoryNode(rootNode);
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			DOMSource source = new DOMSource(siteDoc);
			File sourceFile = new File(this.siteXmlLoc, "site.xml");
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
