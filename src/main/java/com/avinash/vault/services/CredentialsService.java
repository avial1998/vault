package com.avinash.vault.services;

import com.avinash.vault.dtos.CredentialDto;
import com.avinash.vault.entities.Credential;
import com.avinash.vault.entities.CredentialId;
import com.avinash.vault.exceptions.CredentialAlreadyExistsException;
import com.avinash.vault.exceptions.CredentialNotFoundException;
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
import java.util.List;
import java.util.Optional;

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

    public CredentialDto getCredential(String vaultId, String domainName) {
        Credential credential = credentialRepository.findById(new CredentialId(domainName,vaultId ))
                .orElseThrow(() -> new CredentialNotFoundException("Credential not found for domain: " + domainName + " and vault ID: " + vaultId));
        try {
            credential.setUserPassword(encryptionService.decrypt(credential.getUserPassword()));
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting password", e);
        }
        return credentialMapper.mapCredentialToCredentialDto(credential);
    }
    public List<CredentialDto> getAllCredentials(String vaultId) {
       List<Credential> credentials = credentialRepository.findAllByVaultId(vaultId)
                .orElseThrow(() -> new CredentialNotFoundException("No credentials found for vault ID: " + vaultId));
        for (Credential credential : credentials) {
            try {
                credential.setUserPassword(encryptionService.decrypt(credential.getUserPassword()));
            } catch (Exception e) {
                throw new RuntimeException("Error decrypting password", e);
            }
        }
        return credentialMapper.mapCredentialsToCredentialDtos(credentials);
    }
    public void updateCredential(CredentialDto credentialDto) {
        Credential credential = credentialMapper.mapCredentialDtoToCredential(credentialDto);
        if (!credentialRepository.existsById(new CredentialId(credential.getDomainName(), credential.getVaultId()))) {
            throw new CredentialNotFoundException("Credential not found for domain: " + credential.getDomainName() + " and vault ID: " + credential.getVaultId());
        }
        try {
            credential.setUserPassword(encryptionService.encrypt(credential.getUserPassword()));
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting password", e);
        }
        credentialRepository.save(credential);
    }
}
