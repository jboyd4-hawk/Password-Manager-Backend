package com.jboyd.hawk.passwordmanagerbackend.repos;

import com.jboyd.hawk.passwordmanagerbackend.models.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CredentialRepo extends JpaRepository<Credential, Long> {
    List<Credential> findByUserId(long userId);

}
