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

public class ReadPluginInfoTask extends Task
{
	protected String pluginXmlLoc;
	protected String propertyName;
	protected String pluginLoc;
	
	private String pluginID;
	private String pluginVersion;
	
	public void setPluginLoc(String pluginLoc)
	{
		this.pluginLoc = pluginLoc;
	}
	
	public void setPluginXmlLoc(String pluginXmlLoc)
	{
		this.pluginXmlLoc = pluginXmlLoc;
	}
	
	public void setPropertyName(String propertyName)
	{
		this.propertyName = propertyName;
	}
	
	private void readPluginInfo() throws Exception
	{
		File pluginFile = new File(pluginXmlLoc, "plugin.xml");
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
	
	public void execute()
	{
		try
		{
			this.readPluginInfo();
			String pluginFolder = project.getProperty("basedir");
			pluginFolder += File.separator + this.pluginLoc + File.separator + this.pluginID + "_" + this.pluginVersion;
			project.setProperty("plugin.id", this.pluginID);
			project.setProperty("plugin.version", this.pluginVersion);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
