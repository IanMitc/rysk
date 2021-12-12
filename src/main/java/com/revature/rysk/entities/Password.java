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
@Getter
@Setter
@ToString
public class Password {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private long passwordId;
    private String password;
}
