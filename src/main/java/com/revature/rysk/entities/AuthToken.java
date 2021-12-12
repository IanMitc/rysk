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
@Getter
@Setter
@ToString
public class AuthToken {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private long authTokenId;

    @Column(unique = true)
    private String authToken;

    public AuthToken() {
        this.authToken = UUID.randomUUID().toString();
    }
}
