package com.avinash.vault.services;

import com.avinash.vault.dtos.CredentialDto;
import com.avinash.vault.entities.Credential;
import com.avinash.vault.entities.CredentialId;
import com.avinash.vault.exceptions.CredentialAlreadyExistsException;
import com.avinash.vault.exceptions.CredentialNotFoundException;
import com.avinash.vault.mappers.CredentialMapper;
import com.avinash.vault.repositories.CredentialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CredentialsServiceTest {

    @Mock
    private CredentialRepository credentialRepository;
    @Mock
    private CredentialMapper credentialMapper;
    @Mock
    private EncryptionService encryptionService;
    @InjectMocks
    private CredentialsService credentialsService;

    private CredentialDto credentialDto;
    private Credential credential;
    private CredentialId credentialId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        credentialId = new CredentialId("example.com", "user1");
        credentialDto = new CredentialDto();
        credentialDto.setDomainName("example.com");
        credentialDto.setVaultId("user1");
        credentialDto.setUserPassword("password");
        credentialDto.setUserName("user1");
        credential = new Credential();
        credential.setDomainName("example.com");
        credential.setVaultId("user1");
        credentialDto.setUserName("user2");
        credential.setUserPassword("password");
    }

    @Test
    void saveCredential_success() throws Exception {
        when(credentialMapper.mapCredentialDtoToCredential(any())).thenReturn(credential);
        when(credentialRepository.existsById(any())).thenReturn(false);
        when(encryptionService.encrypt(any())).thenReturn("encrypted");
        credentialsService.saveCredential(credentialDto);
        verify(credentialRepository, times(1)).save(any(Credential.class));
    }

    @Test
    void saveCredential_alreadyExists() {
        when(credentialMapper.mapCredentialDtoToCredential(any())).thenReturn(credential);
        when(credentialRepository.existsById(any())).thenReturn(true);
        assertThrows(CredentialAlreadyExistsException.class, () -> credentialsService.saveCredential(credentialDto));
    }

    @Test
    void getCredential_success() throws Exception {
        when(credentialRepository.findById(any())).thenReturn(Optional.of(credential));
        when(encryptionService.decrypt(any())).thenReturn("decrypted");
        when(credentialMapper.mapCredentialToCredentialDto(any())).thenReturn(credentialDto);
        CredentialDto result = credentialsService.getCredential("user1", "example.com");
        assertNotNull(result);
        verify(encryptionService, times(1)).decrypt(any());
    }

    @Test
    void getCredential_notFound() {
        when(credentialRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(CredentialNotFoundException.class, () -> credentialsService.getCredential("user1", "example.com"));
    }

    @Test
    void getAllCredentials_success() throws Exception {
        List<Credential> credentials = Arrays.asList(credential);
        when(credentialRepository.findAllByVaultId(any())).thenReturn(Optional.of(credentials));
        when(encryptionService.decrypt(any())).thenReturn("decrypted");
        when(credentialMapper.mapCredentialsToCredentialDtos(any())).thenReturn(Arrays.asList(credentialDto));
        List<CredentialDto> result = credentialsService.getAllCredentials("user1");
        assertEquals(1, result.size());
        verify(encryptionService, times(1)).decrypt(any());
    }

    @Test
    void getAllCredentials_notFound() {
        when(credentialRepository.findAllByVaultId(any())).thenReturn(Optional.empty());
        assertThrows(CredentialNotFoundException.class, () -> credentialsService.getAllCredentials("user1"));
    }

    @Test
    void updateCredential_success() throws Exception {
        when(credentialMapper.mapCredentialDtoToCredential(any())).thenReturn(credential);
        when(credentialRepository.existsById(any())).thenReturn(true);
        when(encryptionService.encrypt(any())).thenReturn("encrypted");
        credentialsService.updateCredential(credentialDto);
        verify(credentialRepository, times(1)).save(any(Credential.class));
    }

    @Test
    void updateCredential_notFound() {
        when(credentialMapper.mapCredentialDtoToCredential(any())).thenReturn(credential);
        when(credentialRepository.existsById(any())).thenReturn(false);
        assertThrows(CredentialNotFoundException.class, () -> credentialsService.updateCredential(credentialDto));
    }
}