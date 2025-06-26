package com.avinash.vault.services;

import com.avinash.vault.dtos.CredentialDto;
import com.avinash.vault.entities.Credential;
import com.avinash.vault.entities.CredentialId;
import com.avinash.vault.exceptions.CredentialAlreadyExistsException;
import com.avinash.vault.mappers.CredentialMapper;
import com.avinash.vault.repositories.CredentialRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@AllArgsConstructor
public class CredentialsService {
    private final CredentialRepository credentialRepository;
    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    public void saveCredential(CredentialDto credentialDto) {
        Credential credential =credentialMapper.mapCredentialDtoToCredential(credentialDto);
        if (credentialRepository.existsById(new CredentialId( credential.getDomainName(), credential.getVaultId()))) {
            throw new CredentialAlreadyExistsException("Credential already exists for domain: " + credential.getDomainName() + " and vault ID: " + credential.getVaultId());
        }
       try {
            credential.setUserPassword(encryptionService.encrypt(credential.getUserPassword()));
        } catch (Exception e) {
            throw new RuntimeException();
        }
        credentialRepository.save(credential);
    }
}
