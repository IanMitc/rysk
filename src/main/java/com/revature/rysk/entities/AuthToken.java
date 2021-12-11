package com.revature.rysk.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthToken {
    @Id
    @Column(nullable = false)
    @Getter
    @Setter
    private long authTokenId;
    @Setter
    @Column(unique = true)
    private String authToken;

    private boolean checkAuthToken(String authTokenToCheck) {
        return authToken.equals(authTokenToCheck);
    }
}
