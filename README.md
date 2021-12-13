1. Project Name
2. Project Description (short explanation of idea)
3. Feature list
4. Wire frame
5. App flow
6. Distribution of task between team members

# Rysk REST API version 1.0

## Player

### player (post player)

```
    "playerName": "Testy McTestface",
    "playerEmail": "test@example.com",
    "playerPassword": {
        "password": "password"
    }
```

- return player. Can be saved in cookie or local storage. Adds player to system.

```    
    "playerId": 1,
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "authToken": {
        "authTokenId": 1,
        "authToken": "3df5860c-14a2-408f-bc7b-0042f27cb0ab"
    },
    "playerPassword": null
```

### player/email (post email)

```
test@example.com
```

- returns player

```    
    "playerId": 1,
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "authToken": null,
    "playerPassword": null
```

### player (put updated player info)

```
    "playerId": 1,
    "playerEmail": "test2@example.com",
    "playerName": "Testy McTestface",
    "authToken": {
        "authTokenId": 1,
        "authToken": "ec3dd984-1262-4a25-8064-a9000f175695"
    }
```

- returns updated player should be saved to cookie or local storage so user will stay logged in.

```
    "playerId": 1,
    "playerEmail": "test2@example.com",
    "playerName": "Testy McTestface",
    "authToken": {
        "authTokenId": 1,
        "authToken": "ec3dd984-1262-4a25-8064-a9000f175695"
    },
    "playerPassword": null
```

### player/login (post Player)

```
    "playerEmail": "test@example.com",
    "playerPassword": {
        "password": "password"
    }
```

- returns Player. Can be saved in cookie or local storage.

```    
    "playerId": 1,
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "authToken": {
        "authTokenId": 1,
        "authToken": "48daeea3-a109-43f0-a969-016cb6f9e86f"
    },
    "playerPassword": null
```

### player/logout (post Player)

```
    "playerId": 1,
    "playerEmail": "test2@example.com",
    "playerName": "Testy McTestface",
    "authToken": {
        "authTokenId": 6,
        "authToken": "9d6077c5-cc7b-4e85-91d0-0490fa4047b7"
    }
```

- returns empty if success and removes auth token from system so can no longer be used.

```
```

### player/login/check (post Player)

```
    "playerEmail": "test@example.com",

    "authToken": {
        "authToken": "be120813-d39e-4306-bdd8-becbf0a8da65"
    }
```

- returns existing Player if valid

```
    "playerId": 1,
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "authToken": {
        "authTokenId": 1,
        "authToken": "be120813-d39e-4306-bdd8-becbf0a8da65"
    },
    "playerPassword": null
```

### player/games (post Player)

- returns all games that player is a participant in

## Game Management

### game/new (post Players for game)

```
[
    {
        "playerId": 1,
        "playerEmail": "test@example.com",
        "playerName": "Testy McTestface"
    },
    {
        "playerId": 2,
        "playerEmail": "test2@example.com",
        "playerName": "Testy McTestface"
    }
]
```

- returns new game board.

```
{
    "gameId": 1,
    "players": [
        {
            "playerId": 1,
            "playerEmail": "test2@example.com",
            "playerName": "Testy McTestface"
        },
        {
            "playerId": 2,
            "playerEmail": "test@example.com",
            "playerName": "Testy McTestface"
        }
    ],
    "currentPlayer": {
        "playerId": 2,
        "playerEmail": "test@example.com",
        "playerName": "Testy McTestface"
    },
    "attackingPlayer": null,
    "deck": {
        "deckId": 14
    },
    "logs": [
        {
            "logId": 1,
            "message": "New Game Started"
        },
        {
            "logId": 2,
            "message": "Testy McTestface goes first"
        }
    ],
    "countries": [
        {
            "countryId": 0,
            "controlledBy": {
                "playerId": 1,
                "playerEmail": "test2@example.com",
                "playerName": "Testy McTestface"
            },
            "name": "Alaska",
            "printableName": "Alaska",
            "armies": 1
        },
        ...
        ...
        ...        
        {
            "countryId": 41,
            "controlledBy": {
                "playerId": 1,
                "playerEmail": "test2@example.com",
                "playerName": "Testy McTestface"
            },
            "name": "WesternAustralia",
            "printableName": "W Australia",
            "armies": 1
        }
    ],
    "attackingDice": [],
    "defendingDice": [],
    "bonusArmies": 4,
    "stage": "DISCARD"
}
```

### game/decline (post Player, game id)

- returns success or failure

### game/join (post Player, game id)

- returns game board. Can be used for joining new or existing games also to update board in UI if needed.

### game/quit (post Player, game id)

- returns current game board but player is no longer active (auto distribute countries to other players or make
  neutral?)

### game/exit (post Player, game id)

- returns success or error. Allows game play to continue until this players turn (or is attacked) and then waits for
  that player to rejoin.

### game/log (post Player, game id)

- returns the game history

### game/log/{log id} (post Player, game id)

- returns all log messages from a particular message to the most recent. If we don't use web sockets, we can have the
  log component call this on a timer and then update the UI game state if a new log is returned.

## Game Play

### game/play/discard (post Player, 0 or 3 cards)

- returns number of additional armies earned

### game/play/armies (post Player, game id, country id, number of armies to add)

- returns updated country or failure if not auth, not turn, not controlled by player,

### game/play/attack (post Player, attacking country id, number of attacking armies, number of dice, defending country id)

- returns up to 3 random numbers for dice roll or failure if not auth etc.

### game/play/defend (post Player, defending country id, number of dice)

- returns up to 2 random numbers for dice roll or failure if not auth etc. Triggers logging of attack and if web sockets
  are implemented will tell players to update board info.

### game/play/move (post Player, from country id, to country id, number of armies)

- returns updated countries or failure

### game/play/draw (post Player)

- returns card if player took over a country, empty if not, or error. Also signals to the backend that the player's turn
  has ended.
