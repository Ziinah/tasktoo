import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.util.*;

public class task2{

        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the fields you want to display (comma-separated, e.g. name,postalZip):");
        String[] selectedFields = scanner.nextLine().split(",");

        Set<String> fieldSet = new HashSet<>();
        for (String field : selectedFields) {
            fieldSet.add(field.trim());
        }

        try {
            File inputFile = new File("data.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);
            document.getDocumentElement().normalize();

            read(document, fieldSet);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
    private static void read(Document document) {
         String rootTag = document.getDocumentElement().getTagName();
         System.out.println("root tag: " + rootTag);


        NodeList records = document.getElementsByTagName("record");

        for (int i = 0; i < records.getLength(); i++) {
            Node node = records.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String name = element.getElementsByTagName("name").item(0).getTextContent();
                String postalZip = element.getElementsByTagName("postalZip").item(0).getTextContent();
                String region = element.getElementsByTagName("region").item(0).getTextContent();
                String country = element.getElementsByTagName("country").item(0).getTextContent();
                String address = element.getElementsByTagName("address").item(0).getTextContent();
                String list = element.getElementsByTagName("list").item(0).getTextContent();

                System.out.println("----- Record -----");
                System.out.println("Name: " + name);
                System.out.println("Postal Zip: " + postalZip);
                System.out.println("Region: " + region);
                System.out.println("Country: " + country);
                System.out.println("Address: " + address);
                System.out.println("List: " + list);
                System.out.println();
            }
        }
    }
}
