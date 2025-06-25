package com.avinash.vault.apis;

import com.avinash.vault.dtos.CredentialDto;
import com.avinash.vault.dtos.ResponseDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RequestMapping(path = "/credentials",produces ={MediaType.APPLICATION_JSON_VALUE} )
public interface CredentialsAPI {
    @PostMapping
    ResponseEntity<ResponseDto> saveCredential(@AuthenticationPrincipal Jwt jwt, @RequestBody CredentialDto credentialDto);

    ResponseEntity<ResponseDto> updateCredential(CredentialDto credentialDto);

    ResponseEntity<CredentialDto> getCredential(long vaultId, String domainName);

    ResponseEntity<List<CredentialDto>> getAllCredentials(long vaultId);
}
