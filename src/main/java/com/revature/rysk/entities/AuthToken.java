package com.revature.rysk.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Builder
public class AuthToken {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    @Getter
    @Setter
    private long authTokenId;
    @Getter
    @Setter
    @Column(unique = true)
    private String authToken;

    public AuthToken() {
        this.authToken = UUID.randomUUID().toString();
    }
    private boolean checkAuthToken(String authTokenToCheck) {
        return authToken.equals(authTokenToCheck);
    }
}
