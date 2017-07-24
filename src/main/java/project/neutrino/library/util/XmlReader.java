package project.neutrino.library.util;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class XmlReader {

    private InputStream xmlFile;

    private Document document;

    public XmlReader(InputStream xmlFile) throws IOException, SAXException, ParserConfigurationException {
        this.xmlFile = xmlFile;
        configure();
    }

    public String readUniqueTag(String tagName) {
        Node node = document
                .getElementsByTagName(tagName)
                .item(0);

        return node.getTextContent();
    }

    private void configure() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        document = dBuilder.parse(xmlFile);
        document.getDocumentElement().normalize();
    }
}
