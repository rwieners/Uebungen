import java.io.FileWriter;  
import java.io.IOException;  
import org.jdom2.Attribute;  
import org.jdom2.Document;  
import org.jdom2.Element;  
import org.jdom2.output.Format;  
import org.jdom2.output.XMLOutputter;  
  
public class CreateXml {  
  
 public void createXml() {  
  
  try {  
   Element school = new Element("school");  
   Document document = new Document(school);  
   Element student = new Element("student");  
   
   student.setAttribute(new Attribute("id", "1"));  
   student.addContent(new Element("firstname").setText("ankush"));  
   student.addContent(new Element("lastname").setText("thakur"));  
   student.addContent(new Element("email").setText("beingjavaguy@gmail.com"));  
   student.addContent(new Element("phone").setText("8788987656"));  
   
   document.getRootElement().addContent(student);  
   XMLOutputter xmlOutput = new XMLOutputter();  
   xmlOutput.output(document, System.out);  
   xmlOutput.setFormat(Format.getPrettyFormat());  
   xmlOutput.output(document, new FileWriter("generatedXmlFiles/generatedXml.xml"));  
  
  } catch (IOException io) {  
   System.out.println(io.getMessage());  
  }  
 }  

 public static void main(String[] args) {

 CreateXml createXml = new CreateXml();  
 createXml.createXml();  
 }

}  