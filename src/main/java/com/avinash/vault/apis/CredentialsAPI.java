package com.avinash.vault.apis;

import com.avinash.vault.dtos.CredentialDto;
import com.avinash.vault.dtos.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Credentials API", description = "API for managing credentials in the vault")
@RequestMapping(path = "/credentials",produces ={MediaType.APPLICATION_JSON_VALUE} )
public interface CredentialsAPI {
    @Operation(summary = "Save a new credential", description = "Saves a new credential in the vault. The request must contain valid authentication details.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Credential saved successfully",content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request details")
    })
    @PostMapping
    ResponseEntity<ResponseDto> saveCredential(@AuthenticationPrincipal Jwt jwt,@Valid @RequestBody CredentialDto credentialDto);

    @Operation(summary = "Update an existing credential", description = "Updates an existing credential in the vault. The request must contain valid authentication details.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Credential updated successfully",content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request details")
    })
    @PutMapping
    ResponseEntity<ResponseDto> updateCredential(@AuthenticationPrincipal Jwt jwt,@Valid @RequestBody CredentialDto credentialDto);

    @Operation(summary = "Get a credential by vault ID and domain name", description = "Retrieves a credential from the vault using the vault ID and domain name. The request must contain valid authentication details.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Credential retrieved successfully",content = @Content(schema = @Schema(implementation = CredentialDto.class))),
            @ApiResponse(responseCode = "404", description = "Credential not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request details")
    })
    @GetMapping(path = "/{vaultId}/{domainName}")
    ResponseEntity<CredentialDto> getCredential(@AuthenticationPrincipal Jwt jwt, @PathVariable String vaultId, @PathVariable String domainName);

    @Operation(summary = "Get all credentials for a vault", description = "Retrieves all credentials associated with a specific vault ID. The request must contain valid authentication details.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Credentials retrieved successfully",content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No credentials found for the vault ID"),
            @ApiResponse(responseCode = "400", description = "Invalid request details")
    })
    @GetMapping(path = "/{vaultId}")
    ResponseEntity<List<CredentialDto>> getAllCredentials(@AuthenticationPrincipal Jwt jwt, @PathVariable String vaultId);
}
