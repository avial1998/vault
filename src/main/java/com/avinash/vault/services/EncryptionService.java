package com.avinash.vault.services;

import com.avinash.vault.configs.EncryptionConfig;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class EncryptionService {
    public static final String AES_GCM_NO_PADDING = "AES/GCM/NoPadding";
    private final SecretKeySpec keySpec;
    private final GCMParameterSpec gcmParameterSpec;
    public EncryptionService(EncryptionConfig encryptionConfig){
        this.keySpec = encryptionConfig.getKeySpec();
        this.gcmParameterSpec = encryptionConfig.getGcmParameterSpec();
    }
    public String encrypt(String password) throws Exception{
        Cipher cipher= Cipher.getInstance(AES_GCM_NO_PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
    public String decrypt(String encryptedPassword) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
        return new String(decryptedBytes);
    }
}
