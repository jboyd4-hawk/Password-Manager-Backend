package com.jboyd.hawk.passwordmanagerbackend.services;

import com.jboyd.hawk.passwordmanagerbackend.models.Credential;
import com.jboyd.hawk.passwordmanagerbackend.repos.CredentialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CredentialService {

    @Autowired
    CredentialRepo credentialRepo;

    public Credential addCredential(Credential credential) {
        return credentialRepo.save(credential);
    }

    public Optional<Credential> getCredentialById(Long id) {
        return credentialRepo.findById(id);
    }

    public List<Credential> getAllCredentials() {
        return credentialRepo.findAll();
    }

    public void deleteCredentialById(Long id) {
        credentialRepo.deleteById(id);
    }

    public Optional<Credential> updateCredentialById(Credential credential) {
        Optional<Credential> existingCredential = credentialRepo.findById(credential.getId());

        if (existingCredential.isPresent()) {
            Credential updated = existingCredential.get();
            updated.setName(credential.getName());
            updated.setPassword(credential.getPassword());

            return Optional.of(credentialRepo.save(updated));
        }
        return Optional.empty();
    }
}
