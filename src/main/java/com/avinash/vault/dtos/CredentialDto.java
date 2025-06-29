package com.avinash.vault.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
@Schema(
        description = "Data Transfer Object for Credential",
        title = "CredentialDto")
public class CredentialDto {
    @Schema(
            description = "Username",example = "admin")
    private String userName;
    @Schema(description = "User Password",example = "password123")
    private String userPassword;
    @Schema(
            description = "Domain Name",
            example = "example.com")
    private String domainName;
    @Schema(
            description = "Vault ID",
            example = "vault123")
    @NotBlank(message = "Vault ID cannot be blank")
    private String vaultId;
}
