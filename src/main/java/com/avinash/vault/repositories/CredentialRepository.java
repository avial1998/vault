package com.avinash.vault.repositories;

import com.avinash.vault.entities.Credential;
import com.avinash.vault.entities.CredentialId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, CredentialId> {
}
