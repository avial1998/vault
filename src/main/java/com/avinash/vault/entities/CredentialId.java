package com.avinash.vault.entities;

import java.io.Serializable;
import java.util.Objects;

public class CredentialId implements Serializable {
    private String domainName;
    private String vaultId;

    public CredentialId() {}

    public CredentialId(String domainName, String vaultId) {
        this.domainName = domainName;
        this.vaultId = vaultId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CredentialId that = (CredentialId) o;
        return vaultId.equals(that.vaultId) && Objects.equals(domainName, that.domainName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(domainName, vaultId);
    }
}

