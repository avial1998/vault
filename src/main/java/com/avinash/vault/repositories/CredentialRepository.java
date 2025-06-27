package com.avinash.vault.repositories;

import com.avinash.vault.entities.Credential;
import com.avinash.vault.entities.CredentialId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, CredentialId> {
    Optional<List<Credential>> findAllByVaultId(String vaultId);
}
