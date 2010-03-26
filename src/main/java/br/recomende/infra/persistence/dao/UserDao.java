package br.recomende.infra.persistence.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import br.recomende.infra.user.User;
import br.recomende.model.profile.Tag;
import br.recomende.model.repository.UserRepository;

@Repository
public class UserDao extends RepositoryWrapper<User, String> implements
		UserRepository {

	public UserDao() {
		super(User.class);
	}
	
	@Override
	public User persist(User object) {
		this.dropAllTags(object);
		for(Tag tag : object.getProfile().getTags()) {
			tag.getPk().setUser(object);
		}
		return super.persist(object);
	}
	
	private void dropAllTags(User user) {
		Session session = this.getSession();
		Query query = session.createQuery("DELETE FROM " + Tag.class.getSimpleName()
				+ " WHERE pk.user = :user");
		query.setParameter("user", user);
		
		query.executeUpdate();
	}

}
