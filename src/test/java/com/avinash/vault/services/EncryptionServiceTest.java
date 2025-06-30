package com.avinash.vault.services;

import com.avinash.vault.configs.EncryptionConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionServiceTest {
    private EncryptionService encryptionService;
    private static final String SECRET = "AbCdEfGhIjKlMnOpQrStUvWxYz123456";
    private static final String IV = "AbCdEfGhIjKlMnOp";

    @BeforeEach
    void setUp() {
        EncryptionConfig encryptionConfig = new EncryptionConfig() {
            @Override
            public SecretKeySpec getKeySpec() {
                return new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), "AES");
            }
            @Override
            public GCMParameterSpec getGcmParameterSpec() {
                return new GCMParameterSpec(128, IV.getBytes(StandardCharsets.UTF_8));
            }
        };
        encryptionService = new EncryptionService(encryptionConfig);
    }

    @Test
    void encryptAndDecrypt_success() throws Exception {
        String password = "mySecretPassword";
        String encrypted = encryptionService.encrypt(password);
        assertNotNull(encrypted);
        assertNotEquals(password, encrypted);
        String decrypted = encryptionService.decrypt(encrypted);
        assertEquals(password, decrypted);
    }

    @Test
    void encrypt_nullOrEmpty() throws Exception {
        String encrypted = encryptionService.encrypt("");
        assertNotNull(encrypted);
        String decrypted = encryptionService.decrypt(encrypted);
        assertEquals("", decrypted);
    }

    @Test
    void decrypt_invalidData_throwsException() {
        assertThrows(Exception.class, () -> encryptionService.decrypt("invalidBase64"));
    }
}