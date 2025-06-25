package com.avinash.vault.controllers;

import com.avinash.vault.apis.CredentialsAPI;
import com.avinash.vault.dtos.CredentialDto;
import com.avinash.vault.dtos.ResponseDto;
import com.avinash.vault.exceptions.InvalidRequestDetailsException;
import com.avinash.vault.services.CredentialsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@AllArgsConstructor
public class CredentialsController implements CredentialsAPI {
    private final CredentialsService credentialsService;

    @Override
    public ResponseEntity<ResponseDto> saveCredential(Jwt jwt, CredentialDto credentialDto) {
        authenticateUserDetails(jwt, credentialDto);
        credentialsService.saveCredential(credentialDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED.toString(),"Credential saved successfully"));
    }

    @Override
    public ResponseEntity<ResponseDto> updateCredential(CredentialDto credentialDto) {
        return null;
    }

    @Override
    public ResponseEntity<CredentialDto> getCredential(long vaultId, String domainName) {
        return null;
    }

    @Override
    public ResponseEntity<List<CredentialDto>> getAllCredentials(long vaultId) {
        return null;
    }
    private static void authenticateUserDetails(Jwt jwt, CredentialDto credentialDto) {
        String vaultId=(String) jwt.getClaims().get("preferred_username");
        if(!credentialDto.getUserName().equals(vaultId)){
            throw new InvalidRequestDetailsException("Invalid request details. User name in the credential does not match the authenticated user.");
        }
    }
}
