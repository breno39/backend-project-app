package com.ninjaone.backendinterviewproject.credencial.application.service;

import com.ninjaone.backendinterviewproject.credencial.domain.Credential;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface CredentialService {

	Credential createCredential(Credential buildCredential);

	Credential findCredentialById(UUID id);

	Credential findCredentialByUsername(String username);

}
