package br.recomende.infra.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.recomende.infra.util.SHA1;

@Entity
@Table(name="users")
public class User implements UserDetails {

	private static final long serialVersionUID = -2909778545255695860L;

	@Id
	private String username;
	
	private String password;
	
	private Boolean active;
	
	@ManyToMany(fetch=FetchType.EAGER, mappedBy="username", cascade=CascadeType.ALL)
	private Set<Role> roles;

	protected User() {
	}
	
	public User(String username, String password, Boolean active, Set<Role> roles) {
		this.username = username;
		this.password = SHA1.crypt(password);
		this.active = active;
		this.roles = roles;
	}
	
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return new ArrayList<GrantedAuthority>(roles);
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.active;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.active;
	}

	@Override
	public boolean isEnabled() {
		return this.active;
	}

}
