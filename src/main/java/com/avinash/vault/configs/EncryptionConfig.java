package com.avinash.vault.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Configuration
public class EncryptionConfig {
    @Value("${encryption.secret}")
    private String secret;
    @Value("${encryption.iv}")
    private String iv;

    public SecretKeySpec getKeySpec(){
        return new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "AES");
    }
    public GCMParameterSpec getGcmParameterSpec() {
        return new GCMParameterSpec(128, iv.getBytes(StandardCharsets.UTF_8));
    }
}
