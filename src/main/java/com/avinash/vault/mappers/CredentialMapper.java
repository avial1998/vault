package com.avinash.vault.mappers;

import com.avinash.vault.dtos.CredentialDto;
import com.avinash.vault.entities.Credential;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CredentialMapper {
    public Credential mapCredentialDtoToCredential(CredentialDto credentialDto) {
        Credential credential = new Credential();
        credential.setDomainName(credentialDto.getDomainName());
        credential.setVaultId(credentialDto.getVaultId());
        credential.setUserName(credentialDto.getUserName());
        credential.setUserPassword(credentialDto.getUserPassword());
        return credential;
    }
    public CredentialDto mapCredentialToCredentialDto(Credential credential) {
        return new CredentialDto(
                credential.getUserName(),
                credential.getUserPassword(),
                credential.getDomainName(),
                credential.getVaultId()
        );
    }
    public List<CredentialDto> mapCredentialsToCredentialDtos(List<Credential> credentials) {
        return credentials.stream()
                .map(this::mapCredentialToCredentialDto)
                .toList();
    }
}
