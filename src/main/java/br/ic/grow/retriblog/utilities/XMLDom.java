package br.ic.grow.retriblog.utilities;



import java.io.* ;

import java.util.* ;

import javax.xml.parsers.* ;
import org.xml.sax.* ;
import org.w3c.dom.* ;



public class XMLDom {
	
	private static XMLDom theLib ;  
	

	public synchronized static XMLDom getLibrary(){

		if( theLib == null ) theLib = new XMLDom();

		return theLib ;

	}

	// instance variables below this

	private Hashtable domHash ;

	private String lastErr = "none" ;

	// private constructor to ensure singleton

	public XMLDom(){

		domHash = new Hashtable();

	}

	Object loadXML( String src, boolean validate) {

		File xmlFile = new File( src ) ;

		String err = null ;

		try {  

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			dbf.setValidating( validate );

			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse( xmlFile );

			return doc ;

		}catch(ParserConfigurationException pce){

			err = pce.toString();

		}catch(SAXParseException spe ){

			StringBuffer sb = new StringBuffer( spe.toString() );

			sb.append("\n  Line number: " + spe.getLineNumber());

			sb.append("\nColumn number: " + spe.getColumnNumber() );

			sb.append("\n Public ID: " + spe.getPublicId() );

			sb.append("\n System ID: " + spe.getSystemId() + "\n");

			err = sb.toString();

		}catch( SAXException se ){

			err = se.toString();

			if( se.getException() != null ){

				err += " caused by: " + se.getException().toString() ;

			}
		}catch( IOException ie ){

			err = ie.toString(); 

		}

		return err ;    
	} // end loadXML
	
	
		
}


