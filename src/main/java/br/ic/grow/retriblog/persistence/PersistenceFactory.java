package br.ic.grow.retriblog.persistence;



public class PersistenceFactory {

	
	public static Persistence getMySqlPersistence(){
		return new MySqlPersistence();
	}
	
}
