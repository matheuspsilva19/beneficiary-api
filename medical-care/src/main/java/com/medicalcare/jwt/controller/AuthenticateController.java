package com.medicalcare.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medicalcare.jwt.model.TokenRequest;
import com.medicalcare.jwt.model.TokenResponse;
import com.medicalcare.jwt.service.TokenService;

@RestController
public class AuthenticateController {

	@Autowired
	private TokenService service;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/authenticate")
	public ResponseEntity<TokenResponse> tokenGenerate(@RequestBody TokenRequest tokenRequest) {
		var authenticationToken = new UsernamePasswordAuthenticationToken(tokenRequest.username(),
				tokenRequest.password());

		var auth = authenticationManager.authenticate(authenticationToken);
		var token = service.tokenGenerate(auth);

		return ResponseEntity.ok(new TokenResponse(token));
	}

}
