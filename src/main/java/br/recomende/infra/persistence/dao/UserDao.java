package br.recomende.infra.persistence.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.recomende.infra.user.User;
import br.recomende.infra.util.SpringScope;
import br.recomende.model.repository.UserRepository;

@Component
@Scope(SpringScope.PROTOTYPE)
public class UserDao extends RepositoryWrapper<User, String>
		implements UserRepository{

}
