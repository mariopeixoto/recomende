package br.recomende.model.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.recomende.model.repository.UserRepository;

@Service("loginService")
public class LoginService implements UserDetailsService {
	
	private UserRepository userRepository;
	
	@Autowired
	public LoginService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		UserDetails user = this.userRepository.get(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return user;
	}

}
