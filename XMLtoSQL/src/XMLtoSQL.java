import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XMLtoSQL {

	public Connection conn = null;
	
	 public void connect() {
	        
	        try {
	            conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/renew/eclipse-workspace/XMLtoSQL/dataBase/ausXML.db");          
	            System.out.println("\nVerbindung wurde aufgebaut.");
	            
	        } catch (SQLException e) {
	        	System.out.println(e.getMessage());
	        } 
	    }
	 public void disconnect() {
	        
	        try {
	        	conn.close();
	        	conn = null;
	            System.out.println("\nVerbindung wurde abgebaut.");
	        } catch (Exception e) {
	        	System.out.println(e.getMessage());
	        } 
	    }
	
	public List readXML(){  

	     SAXBuilder saxBuilder = new SAXBuilder();  
	     File file = new File("xmlToParse/student.xml");  
	     List<Element> studentList = null;
	  
	  try {  
	   Document document = saxBuilder.build(file);  
	   Element rootNode = document.getRootElement();  
	   studentList = rootNode.getChildren("student");  
	        
	   System.out.println("\n-----Inhalt des XMLs:-----");
	      
	   for(int i=0;i<=studentList.size()-1;i++){  
	       Element element = studentList.get(i);  
	       System.out.println("First Name : "+element.getChildText("firstname"));  
	       System.out.println("Last Name : "+element.getChildText("lastname"));  
	       System.out.println("Email : "+element.getChildText("email"));  
	       System.out.println("Phone : "+element.getChildText("phone"));  
	   }  
	   System.out.println("\n-----XML Ende----------\n");	          
	  } catch (JDOMException e) {  
	   e.printStackTrace();  
	  } catch (IOException e) {  
	   e.printStackTrace();  
	  }
	return studentList;  
	}
	
	public void writeSQL(List<Element> studentList){
		
	      String query = " insert into studenten (firstname, lastname, email, phone)" + " values (?, ?, ?, ?)";
	      PreparedStatement preparedStmt = null;
	      List<Element> studentenListe = studentList;
		try {
			preparedStmt = conn.prepareStatement(query);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	      try{
	    	  for(int i=0;i<=studentenListe.size()-1;i++){  
	    		  	Element element = studentenListe.get(i);   
	    		  	preparedStmt.setString (1, element.getChildText("firstname"));
	    		  	preparedStmt.setString (2, element.getChildText("lastname"));
	    		  	preparedStmt.setString (3, element.getChildText("email"));
	    		  	preparedStmt.setString (4, element.getChildText("phone"));	
	    		  	preparedStmt.execute();
	    	  }
	      System.out.println("\nXMLinhalt wurde in Tabelle geschrieben.");
	      preparedStmt.setString (1, "vorname");
	      preparedStmt.setString (2, "nachname");
	      preparedStmt.setString (3, "ich@hier.de");
	      preparedStmt.setString (4, "0123456789");	     
	      preparedStmt.execute();	
	      System.out.println("\nFixdaten wurden in Tabelle geschrieben.");
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	    	  }	   
}

	public  void readSQL(){
		
        String sql = "SELECT firstname, lastname, email, phone FROM studenten";
        
        try {   	
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("SQL Ausgabe: " + rs.getString("firstname") +  "\t" + rs.getString("lastname") + "\t\t" + rs.getString("email")+ "\t" + rs.getString("phone"));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error Main " + e.getMessage());
        }

    }
	
	public static void main(String[] args) {

		XMLtoSQL MeinObjekt = new XMLtoSQL();
		
		System.out.println("\nXML wird eingelesen.");
		List<Element> studentList = MeinObjekt.readXML();	
		
		System.out.println("\nVerbindung wird aufgebaut.");
		MeinObjekt.connect();
		
		System.out.println("\nDatenbank wird beschrieben.");
		MeinObjekt.writeSQL(studentList);
		
		System.out.println("\nVerbundung wird abgebaut.");
		MeinObjekt.disconnect();
		
		System.out.println("\nVerbindung wird aufgebaut.");		
		MeinObjekt.connect();
		
		System.out.println("\nDatenbank wird gelesen.");
		MeinObjekt.readSQL();
		
		System.out.println("\nVerbundung wird abgebaut.");
		MeinObjekt.disconnect();
	}
}
