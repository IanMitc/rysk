package com.revature.rysk.entities.Player;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "auth_token_auth_token_id")
    private AuthToken playerAuthToken;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "password_password_id", nullable = false)
    private Password playerPassword;

    public boolean checkPassword(String otherPassword) {
        return this.playerPassword.getPassword().equals(otherPassword);
    }

    public boolean checkAuthToken(String otherAuthToken) {
        return this.playerAuthToken.getAuthToken().equals(otherAuthToken);
    }
}
