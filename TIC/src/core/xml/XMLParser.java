package core.xml;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * XML parser
 * 
 * @author Maxime
 *
 */
public class XMLParser {
	
	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private Document document;
	
	/**
	 * Constructor
	 * @param file the XML file
	 */
	public XMLParser(File file){
		factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
			document = builder.parse(file);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the count of a node giving a tagname
	 * @param tagname the name of a node
	 * @return count of the node
	 */
	public int getCount(String tagname){
		NodeList nodes = document.getElementsByTagName(tagname);
		return nodes.getLength();
	}
	
	/**
	 * Get the name of root node
	 * @return the name node
	 */
	public String getRootTextNode(){
		return document.getDocumentElement().getNodeName();
	}
	
	/**
	 * Get text of a unique node
	 * @param tagname of a node
	 * @return the value of the node
	 */
	public String getTextNode(String tagname){
		Node node = document.getElementsByTagName(tagname).item(0);
		return node.getTextContent();
	}
	
	/**************************** APPLICATION FOR THE LINE PROJECT **************************/
	
	/**
	 * Get the datas of a list of same nodes (same names)
	 * wich contains only text node
	 * @param tagname the name of the node
	 * @return a map collection of datas
	 */
	public Map<String, Map<String, String>> getListInfos(String tagname){
		
		// We get the list of node that have the tagname specified
		Map<String, Map<String, String>> result = new HashMap<String, Map<String, String>>();
		
		// Normally, there is only one child found with the tagname given
		NodeList nodes = document.getElementsByTagName(tagname);
		
		for (int i = 0 ; i < nodes.getLength() ; i++){
			
			// We get the current child
			Node node = nodes.item(i);
			
			// We get the value of the attribute id of the node
			String id = ((Element) node).getAttribute("id");
			
			// We get the children of the current child
			NodeList children = node.getChildNodes();		
			
			// Storage of the datas of child node : key is node name and value is the value of child
			Map<String, String> data = new HashMap<String, String>(); 
			
			for (int j = 0 ; j < children.getLength() ; j++){	
				Node child = children.item(j);	
				// We consider that the children are only text nodes
				data.put(child.getNodeName(), child.getTextContent());
			}
			
			// We add the data in the result map
			result.put(id, data);
		}
		
		return result;
	}

}
