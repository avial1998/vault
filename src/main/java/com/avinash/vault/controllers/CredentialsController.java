package com.avinash.vault.controllers;

import com.avinash.vault.apis.CredentialsAPI;
import com.avinash.vault.dtos.CredentialDto;
import com.avinash.vault.dtos.ResponseDto;
import com.avinash.vault.services.CredentialsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@AllArgsConstructor
public class CredentialsController implements CredentialsAPI {
    private final CredentialsService credentialsService;

    @Override
    public ResponseEntity<ResponseDto> saveCredential(CredentialDto credentialDto) {
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
}
