package br.recomende.infra.persistence.dao;

import org.springframework.stereotype.Repository;

import br.recomende.infra.user.User;
import br.recomende.model.repository.UserRepository;

@Repository
public class UserDao extends RepositoryWrapper<User, String> implements
		UserRepository {

	public UserDao() {
		super(User.class);
	}

}
