package com.avinash.vault.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CredentialDto {
    private String userName;
    private String userPassword;
    private String domainName;
    private long vaultId;
}
