package com.ecommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.config.JwtProvider;
import com.ecommerce.exception.UserException;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	
	public AuthController(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user) throws UserException{
		String email=user.getEmail();
		String password=user.getPassword();
		String firstName=user.getFirstName();
		String lastName=user.getLastName();
		
		User isEmailExist=userRepository.findByEmail(email);
		
		if (isEmailExist!=null) {
			throw new UserException("Email is Already Used with Another Account");
		}
		
		User createUser=new User();
		createUser.setEmail(email);
		createUser.setPassword(password);
		createUser.setFirstName(firstName);
		createUser.setLastName(lastName);
		
		User savedUser=userRepository.save(createUser);
		
		Authentication authentication=new UsernamePasswordAuthenticationToken(email, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token=jwtProvider.generateToken(authentication)
		
	}

}
