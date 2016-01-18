package console.control;

import console.resources.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class XmlManager {
    private Map<String,String> conformity;
    private String path1 = "";
    private String NROOT = "command";
    private String NCHILD1 = "name";
    private String NCHILD2 = "class";
    private String pathUnix = "src/console/resources/configuration.xml";
    private String pathWin = "src\\console\\resources\\configuration.xml";
    private String path = pathUnix;

    public XmlManager(){
        conformity = new HashMap<>();
        path1 = new File("").getAbsolutePath();
    }

    private void parseDocument() {
        try {
            File file = ResourceLoader.getConfigFile();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            NodeList nList = doc.getElementsByTagName(NROOT);
            for( int i=0; i<nList.getLength(); i++){
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String cmdName = element.getElementsByTagName(NCHILD1).item(0).getTextContent();
                    String className = element.getElementsByTagName(NCHILD2).item(0).getTextContent();
                    conformity.put(cmdName,className);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getClassName(String cmName) {
        String className = "";
        for(String k : conformity.keySet()) {
            if (k.equals(cmName)) {
                className = conformity.get(cmName);
            }
        }
        return className;
    }

    public void setProperty(String osName){
        pathUnix = path1+"/"+pathUnix;
        pathWin = path1+"\\"+pathWin;
        if(osName.equals("unix")){ path = pathUnix; }
        if(osName.equals("win")){ path = pathWin; }
        parseDocument();
    }
}
