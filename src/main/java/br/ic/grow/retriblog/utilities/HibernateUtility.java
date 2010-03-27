package br.ic.grow.retriblog.utilities;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


public class HibernateUtility {
	
	   private static SessionFactory factory;
       
	    static {
	    	//Bloco est�tico que inicializa o Hibernate
	    	try {

	        factory = new AnnotationConfiguration().configure().buildSessionFactory();
	    	
	    	} catch (Exception e) {
	    		
	    		e.printStackTrace();
	    		factory = null;
	    	}
	    }
	    
	    public static synchronized Session getSession() {
	        //Retorna a sess�o aberta
	    	return factory.openSession();
	        
	    }
	    
	 

}
