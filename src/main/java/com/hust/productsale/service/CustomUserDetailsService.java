
package com.hust.productsale.service;

import com.hust.productsale.model.CustomUserDetails;
import com.hust.productsale.model.User;
import com.hust.productsale.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> dbUser = userRepository.findByUsername(username);
		return dbUser.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException(
				"Couldn't find a matching user email in the database for " + username));
	}

	public UserDetails loadUserById(String username) {
		Optional<User> dbUser = userRepository.findByUsername(username);
		return dbUser.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException(
				"Couldn't find a matching user id in the database for " + username));
	}
}
