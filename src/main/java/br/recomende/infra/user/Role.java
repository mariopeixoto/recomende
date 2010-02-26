package br.recomende.infra.user;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {
	
	private static final long serialVersionUID = -6717035896221540250L;
	
	@Id
	private String authority;

	protected Role() {
		
	}
	
	public Role(Roles authority) {
		this.authority = authority.getType();
	}
	
	@Override
	public String getAuthority() {
		return this.authority;
	}

}
