package br.recomende.model.repository;

import java.util.Collection;

import br.recomende.infra.user.User;

public interface UserRepository extends GenericRepository<User, String>{
	
	Collection<User> getNotInListByCitName(Collection<String> citationNames);
	
}
