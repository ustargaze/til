package designpattern;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLUtil {
    public static String getConfigValue(String configName) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.parse(XMLUtil.class.getResource("config.xml").getPath());

            NodeList nodeList = document.getElementsByTagName(configName);
            Node classNameNode = nodeList.item(0).getFirstChild();
            return classNameNode.getNodeValue();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getBeanFromConfig(String configName) {
        try {
            String className = getConfigValue(configName);
            Class<?> c = Class.forName(className);
            return c.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
