package br.ic.grow.retriblog.technoratiApplication;


import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.ic.grow.retriblog.data.Item;
import br.ic.grow.retriblog.data.Weblog;
import br.ic.grow.retriblog.data.WeblogAuthor;
import br.ic.grow.retriblog.utilities.Utilities;
import br.ic.grow.retriblog.utilities.UtilitiesControler;
import br.ic.grow.retriblog.utilities.XMLDom;

public class TechnoratiXml {
	private Utilities dom = new UtilitiesControler(); 
	private static String [] itensList = {"weblog", "title", "excerpt", "created", "postupdate", "permalink"};
	private static String [] weblogList = {"name", "url", "rssurl", "atomurl", "inboundlinks", "inboundblogs", "lastupdate", "hasphoto", "author"};
	private static String [] authorList = {"firstname", "lastname", "username", "description", "bio", "thumbnailpicture"};
	
	
public ArrayList<Item> parserTechnorati(String uriXML) throws Exception{
		
		Document document =  (Document) dom.loadXML(uriXML , true);
		Element itemElement = document.getDocumentElement();
		NodeList itemElementList;
		Element itemX;
		Element itemAttribute;
		NodeList itemAttributeElementList;
		Element weblogX;
		NodeList weblogAttributeList;
		Element weblogAttribute;
		Element authorX;
		NodeList authorAttributeList;
		Element authorAttribute;
		String attribute;
		
		itemElementList = itemElement.getElementsByTagName("item");
		
		ArrayList<Item> technoratiItemList = new ArrayList<Item>();
		
		
		for (int itemList = 0; itemList < itemElementList.getLength(); itemList++){
			
			Item technoratiItem = new Item();
			technoratiItemList.add(technoratiItem);
			
			for (int itemIterator = 0; itemIterator < itensList.length; itemIterator++ ){
				itemX = (Element) itemElementList.item( itemList );
				itemAttributeElementList = itemX.getElementsByTagName(itensList[itemIterator]);
				
				if(itemAttributeElementList.getLength()>0){
					if(itensList[itemIterator].equals("weblog")){
						Weblog tw = new Weblog();
						for (int weblogIterator =0; weblogIterator < weblogList.length; weblogIterator++ ){
							
							weblogX = (Element) itemAttributeElementList.item( 0 );
							weblogAttributeList =  weblogX.getElementsByTagName(weblogList[weblogIterator]);
							
							if(weblogAttributeList.getLength()>0){
								if(weblogList[weblogIterator].equals("author")){
									WeblogAuthor twa = new WeblogAuthor();
									for (int authorIterator =0; authorIterator < authorList.length; authorIterator++ ){
										authorX = (Element) weblogAttributeList.item( 0 );
										authorAttributeList =  authorX.getElementsByTagName(authorList[authorIterator]);
										authorAttribute =  (Element) authorAttributeList.item(0);
										if (authorAttribute.hasChildNodes()){
											attribute = authorAttribute.getFirstChild().getNodeValue();
											
											switch (authorIterator) {//salvar no banco
											case 0:
												twa.setFirstname(attribute);
												break;
											case 1:
												twa.setLastname(attribute);
												break;
											case 2:
												twa.setUsername(attribute);
												break;
											case 3:
												twa.setDescription(attribute);
												break;
											case 4:
												twa.setBio(attribute);
												break;
											case 5:
												twa.setUsername(attribute);
												break;
											default:
												throw new Exception();
											}//salvar no banco

										}
										
									}
									tw.setAuthor(twa);
								}
								else{
									weblogAttribute =  (Element) weblogAttributeList.item(0);
									if (weblogAttribute.hasChildNodes()){
										attribute = weblogAttribute.getFirstChild().getNodeValue();
										switch (weblogIterator) {
										case 0:
											tw.setName(attribute);
											break;
										case 1:
											tw.setUrl(attribute);
											break;
										case 2:
											tw.setRssurl(attribute);
											break;
										case 3:
											tw.setAtomurl(attribute);
											break;
										case 4:
											tw.setInboundlinks(attribute);
											break;
										case 5:
											tw.setInboundblogs(attribute);
											break;
										case 6:
											tw.setLastupdate(attribute);
											break;
										case 7:
											tw.setHasphoto(attribute);
											break;

										default:
											throw new Exception();
										}//salvar no banco

									}
								}
							}

						}
						
//						technoratiItemList.get(itemList).setWeblog(tw); comentado pq a api ta fora do ar
					}
					else{
						itemAttribute = (Element) itemAttributeElementList.item( 0 );
						if (itemAttribute.hasChildNodes()){
							attribute = itemAttribute.getFirstChild().getNodeValue();
							switch (itemIterator) {//salvar no banco
							case 1:
								technoratiItemList.get(itemList).setTitle(attribute);
								break;
							case 2:
								technoratiItemList.get(itemList).setExcerpt(attribute);
								break;
							case 3:
								technoratiItemList.get(itemList).setCreated(attribute);
								break;
							case 4:
								technoratiItemList.get(itemList).setPostupdate(attribute);
								break;
							case 5:
								technoratiItemList.get(itemList).setPermalink(attribute);
								break;
							default:
								throw new Exception();
							}//salvar no banco;

						}
					}
				}


			}
		}
		
		return technoratiItemList;
	
	}
	
	public static void main(String[] args) {
		
//		TechnoratiXml xml = new TechnoratiXml();
//		
//		ArrayList<Item> teste = new ArrayList<Item>();
//		
//		try {
//			teste = xml.parserTechnorati("D:\\LiGeiRo\\workspace\\tag.xml");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		
		
	}

}
