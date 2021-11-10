package kahootADI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NodeList;  
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class configClass {
	
	static String idioma;
	static String tipus_pregunta;
	static String timeout;
	static String num_max_preguntes;
	
	public static void main(String[] args) {
		readXML();
	}
	
	public static void readXML() {
		File xml = new File("config.xml");
    
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	
	DocumentBuilder db;
	try {
		db = dbf.newDocumentBuilder();
		Document doc = db.parse(xml);
		doc.getDocumentElement().normalize();
		
		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());  
		NodeList nodeList = doc.getElementsByTagName("kahoot");
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				
				idioma = eElement.getElementsByTagName("idiomas").item(0).getTextContent();
				System.out.println("Idiomas: " + idioma);
				
				tipus_pregunta = eElement.getElementsByTagName("tipus_pregunta").item(0).getTextContent();
				System.out.println("Tipos de pregunta: " + tipus_pregunta);
				
				timeout = eElement.getElementsByTagName("timeout").item(0).getTextContent();
				System.out.println("Timeout: " + timeout);
				
				num_max_preguntes = eElement.getElementsByTagName("num_max_preguntes").item(0).getTextContent();
				System.out.println("Numero maximo de preguntas: " + num_max_preguntes);


			}
		}
				
		
	} catch (ParserConfigurationException | SAXException | IOException e) {
				e.printStackTrace();
	}  
	
	}
	
	


}
