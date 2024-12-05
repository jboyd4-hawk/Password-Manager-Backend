package com.jboyd.hawk.passwordmanagerbackend.controllers;

import com.jboyd.hawk.passwordmanagerbackend.models.Credential;
import com.jboyd.hawk.passwordmanagerbackend.models.User;
import com.jboyd.hawk.passwordmanagerbackend.services.CredentialService;
import com.jboyd.hawk.passwordmanagerbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/credentials")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CredentialController {

    @Autowired
    CredentialService credentialService;

    @Autowired
    UserService userService;

    @GetMapping
    public List<Credential> getCredentials(Principal principal) {
        User user = userService.findUsername(principal.getName());
        return credentialService.getCredentialsByUserId(user.getUserId());
    }

    @PostMapping
    public Credential createCredential(@RequestBody Credential credential, Principal principal) {
        User user = userService.findUsername(principal.getName());
        credential.setUserId(user.getUserId());
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
