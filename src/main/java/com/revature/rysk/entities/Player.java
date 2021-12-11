package com.revature.rysk.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_id_generator")
    @SequenceGenerator(name = "player_id_generator", sequenceName = "player_id_sequence")
    @Column(nullable = false)
    private long playerId;

    @Column(nullable = false, unique = true)
    private String playerEmail;
    @Column(nullable = false)
    private String playerName;
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "auth_token_auth_token_id")
    private AuthToken authToken;
    @ManyToOne
    @JoinColumn(name = "password_password_id")
    private Password password;

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }
}
