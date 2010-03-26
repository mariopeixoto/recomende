package br.recomende.infra.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.recomende.infra.util.SHA1;
import br.recomende.model.profile.TagSet;

@Entity
@Table(name="users")
public class User implements UserDetails {

	private static final long serialVersionUID = -2909778545255695860L;

	@Id
	private String username;
	
	private String password;
	
	private Boolean active = true;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@OrderBy("authority")
	private Set<Role> roles;
	
	@Embedded
	private TagSet profile;
	
	private String firstName;
	
	private String lastName;
	
	private String citationName;
	
	private String email;

	public User() {
		this.profile = new TagSet();
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

	public TagSet getProfile() {
		return profile;
	}

	public void setProfile(TagSet profile) {
		this.profile = profile;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCitationName() {
		return citationName;
	}

	public void setCitationName(String citationName) {
		this.citationName = citationName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
