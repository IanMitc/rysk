package com.revature.rysk.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Password {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    @Getter
    @Setter
    private long passwordId;
    @Setter
    private String password;

    private boolean checkPassword(String passwordToCheck) {
        return password.equals(passwordToCheck);
    }
}
