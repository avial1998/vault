package com.avinash.vault.apis;

import com.avinash.vault.dtos.CredentialDto;
import com.avinash.vault.dtos.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface CredentialsAPI {

    ResponseEntity<ResponseDto> saveCredential(CredentialDto credentialDto);

    ResponseEntity<ResponseDto> updateCredential(CredentialDto credentialDto);

    ResponseEntity<CredentialDto> getCredential(long vaultId, String domainName);

    ResponseEntity<List<CredentialDto>> getAllCredentials(long vaultId);
}
