package com.avinash.vault.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.Setter;

@Entity
@IdClass(CredentialId.class)
@Setter
@Getter
public class Credential {
    @Id
    private String domainName;
    @Id
    private String vaultId;
    private String userName;
    private String userPassword;
}
