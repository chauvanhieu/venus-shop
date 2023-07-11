package com.venus.Controller.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.venus.DTO.LoginRequest;
import com.venus.Service.JWTService;
import com.venus.entities.User;
import com.venus.repository.UserRepository;

@RestController
@RequestMapping("/api/login")
public class LoginRestController {
	private final static String TOKEN_HEADER = "authorization";

	@Autowired
	JWTService jwtService;

	@Autowired
	UserRepository userRepository;

	@PostMapping
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
		Optional<User> user = userRepository.findByEmailAndPassword(loginRequest.getEmail(),
				loginRequest.getPassword());

		if (user.isPresent()) {
			String token = jwtService.generateTokenLogin(loginRequest.getEmail());
			return new ResponseEntity<>(token, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

//	@GetMapping
//	public ResponseEntity<String> index(ServletRequest request) {
//		try {
//			HttpServletRequest httpRequest = (HttpServletRequest) request;
//			String authToken = httpRequest.getHeader(TOKEN_HEADER);
//			if (jwtService.validateTokenLogin(authToken)) {
//				String email = jwtService.getUsernameFromToken(authToken);
//				Optional<User> u = userRepository.findByEmail(email);
//				if (u.isPresent()) {
//					System.out.println(u.get().getFullName());
//					return new ResponseEntity<>("OK", HttpStatus.OK);
//				}
//			} else {
//				return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//		}
//		return null;
//	}

}
