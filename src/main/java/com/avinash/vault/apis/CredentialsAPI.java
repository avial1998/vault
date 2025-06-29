package com.avinash.vault.apis;

import com.avinash.vault.dtos.CredentialDto;
import com.avinash.vault.dtos.ResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RequestMapping(path = "/credentials",produces ={MediaType.APPLICATION_JSON_VALUE} )
public interface CredentialsAPI {
    @PostMapping
    ResponseEntity<ResponseDto> saveCredential(@AuthenticationPrincipal Jwt jwt,@Valid @RequestBody CredentialDto credentialDto);

    @PutMapping
    ResponseEntity<ResponseDto> updateCredential(@AuthenticationPrincipal Jwt jwt,@Valid @RequestBody CredentialDto credentialDto);

    @GetMapping(path = "/{vaultId}/{domainName}")
    ResponseEntity<CredentialDto> getCredential(@AuthenticationPrincipal Jwt jwt, @PathVariable String vaultId, @PathVariable String domainName);

    @GetMapping(path = "/{vaultId}")
    ResponseEntity<List<CredentialDto>> getAllCredentials(@AuthenticationPrincipal Jwt jwt, @PathVariable String vaultId);
}
