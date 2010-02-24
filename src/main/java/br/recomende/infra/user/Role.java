package br.recomende.infra.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {
	
	private static final long serialVersionUID = -6717035896221540250L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String username;
	
	private String authority;

	protected Role() {
		
	}
	
	public Role(String username, Roles authority) {
		this.username = username;
		this.authority = authority.getType();
	}
	
	@Override
	public String getAuthority() {
		return this.authority;
	}

}
