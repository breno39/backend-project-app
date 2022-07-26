package com.ninjaone.backendinterviewproject.authentication.application.api;

import com.ninjaone.backendinterviewproject.authentication.application.AuthenticationForm;
import com.ninjaone.backendinterviewproject.authentication.application.TokenDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/public/v1/authenticate")
public interface AuthenticationAPI {
    @PostMapping
    ResponseEntity<TokenDTO> Authenticate(@RequestBody @Valid AuthenticationForm authenticationForm) throws AuthenticationException;
    @PostMapping("/refresh")
    ResponseEntity<TokenDTO> refreshAuthentication(@RequestHeader("Authorization") String expiredToken) throws AuthenticationException;
}
