package com.zach.NewStudentManagement.service;

import java.util.Optional;

import com.zach.NewStudentManagement.repository.userRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.zach.NewStudentManagement.model.User;
import com.zach.NewStudentManagement.mapper.UserLoginUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserLoginDetailsService implements UserDetailsService {

	private final userRepository userLoginRepo;

	@Autowired
	public UserLoginDetailsService(userRepository userLoginRepo) {
		this.userLoginRepo = userLoginRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userLogin = (userLoginRepo.findByEmail(username));

		System.out.println("username "+username+" userlogin "+userLogin);

		// Map the User to UserDetails and throw exception if not found
		return userLogin.map(UserLoginUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("Username Not Found"));
	}
}
