package com.avinash.vault.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class CredentialDto {
    private String userName;
    private String userPassword;
    private String domainName;
    @NotBlank(message = "Vault ID cannot be blank")
    private String vaultId;
}
