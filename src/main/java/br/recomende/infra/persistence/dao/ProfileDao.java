package br.recomende.infra.persistence.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import br.recomende.infra.util.SpringScope;
import br.recomende.model.profile.Profile;
import br.recomende.model.repository.ProfileRepository;

@Repository
@Scope(SpringScope.PROTOTYPE)
public class ProfileDao extends RepositoryWrapper<Profile, Integer> implements ProfileRepository {

	public ProfileDao() {
		super(Profile.class);
	}

}
