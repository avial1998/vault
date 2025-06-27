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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@AllArgsConstructor
@Validated
public class CredentialsController implements CredentialsAPI {
    private final CredentialsService credentialsService;

    @Override
    public ResponseEntity<ResponseDto> saveCredential(Jwt jwt, CredentialDto credentialDto) {
        authenticateUserDetails(jwt, credentialDto.getVaultId());
        credentialsService.saveCredential(credentialDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED.toString(),"Credential saved successfully"));
    }

    @Override
    public ResponseEntity<ResponseDto> updateCredential(Jwt jwt,CredentialDto credentialDto) {
        authenticateUserDetails(jwt, credentialDto.getVaultId());
        credentialsService.updateCredential(credentialDto);
        return ResponseEntity.ok()
                .body(new ResponseDto(HttpStatus.OK.toString(), "Credential updated successfully"));
    }

    @Override
    public ResponseEntity<CredentialDto> getCredential(Jwt jwt,String vaultId, String domainName) {
        authenticateUserDetails(jwt,vaultId);
        return ResponseEntity.ok(credentialsService.getCredential(vaultId, domainName));
    }

    @Override
    public ResponseEntity<List<CredentialDto>> getAllCredentials(Jwt jwt,String vaultId) {
        authenticateUserDetails(jwt,vaultId);
        return ResponseEntity.ok(credentialsService.getAllCredentials(vaultId));
    }

    private static void authenticateUserDetails(Jwt jwt, String vaultId) {
        String vaultIdInClaims=(String) jwt.getClaims().get("preferred_username");
        if(!vaultId.equals(vaultIdInClaims)){
            throw new InvalidRequestDetailsException("Invalid request details. User name in the credential does not match the authenticated user.");
        }
    }

}
