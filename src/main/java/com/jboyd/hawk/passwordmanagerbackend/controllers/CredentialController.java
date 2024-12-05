package com.jboyd.hawk.passwordmanagerbackend.controllers;

import com.jboyd.hawk.passwordmanagerbackend.models.Credential;
import com.jboyd.hawk.passwordmanagerbackend.services.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/credentials")
public class CredentialController {

    @Autowired
    CredentialService credentialService;

    @GetMapping
    public List<Credential> getCredentials() {
        return credentialService.getAllCredentials();
    }

    @PostMapping
    public Credential createCredential(@RequestBody Credential credential) {
        return credentialService.addCredential(credential);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Credential> getCredentialById(@PathVariable Long id) {
        Optional<Credential> credential = credentialService.getCredentialById(id);
        if (credential.isPresent()) {
            return ResponseEntity.ok(credential.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredentialById(@PathVariable Long id) {
        credentialService.deleteCredentialById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCredentialById(@PathVariable Long id, @RequestBody Credential credential) {
        credentialService.updateCredentialById(credential);
        return ResponseEntity.noContent().build();
    }
}
