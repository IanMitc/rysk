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
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "authToken": {
        "authToken": "3df5860c-14a2-408f-bc7b-0042f27cb0ab"
    }
```

### player/email (post email)

```
test@example.com
```

- returns player

```    
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "authToken": null,
```

### player (put updated player info)

```
    "playerEmail": "test2@example.com",
    "playerName": "Testy McTestface",
    "authToken": {
        "authToken": "ec3dd984-1262-4a25-8064-a9000f175695"
    }
```

- returns updated player should be saved to cookie or local storage so user will stay logged in.

```
    "playerEmail": "test2@example.com",
    "playerName": "Testy McTestface",
    "authToken": {
        "authToken": "ec3dd984-1262-4a25-8064-a9000f175695"
    },
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
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "authToken": {
        "authToken": "48daeea3-a109-43f0-a969-016cb6f9e86f"
    }
```

### player/logout (post Player)

```
    "playerEmail": "test2@example.com",
    "playerName": "Testy McTestface",
    "authToken": {
        "authToken": "9d6077c5-cc7b-4e85-91d0-0490fa4047b7"
    }
```

- returns success and removes auth token from system so can no longer be used.

```
Success
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
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "authToken": {
        "authToken": "be120813-d39e-4306-bdd8-becbf0a8da65"
    },
```

### player/games (post Player)

- returns all games that player is a participant in

## Game Management

### game/new (post Players for game)

```
[
    {
        "playerEmail": "test@example.com",
        "playerName": "Testy McTestface"
    },
    {
        "playerEmail": "test2@example.com",
        "playerName": "Testy McTestface"
    }
]
```

- returns new game board.

```
    "gameId": 1,
    "players": [
        {
            "playerEmail": "test2@example.com",
            "playerName": "Testy McTestface"
        },
        {
            "playerEmail": "test@example.com",
            "playerName": "Testy McTestface"
        }
    ],
    "currentPlayer": {
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
```

### game/join/{gameID} (post Player)

```
    "playerEmail": "test3@example.com",
    "playerName": "Testy McTestface",
    "authToken": {
        "authToken": "4690fb69-0502-4c53-84e7-4093f8424ab7"
    }
```

- returns game board. Can be used for joining new or existing games also to update board in UI if needed.

```
    "gameId": 7,
    "players": [
        {
            "playerEmail": "test@example.com",
            "playerName": "Testy McTestface"
        },
        {
            "playerEmail": "test2@example.com",
            "playerName": "Testy McTestface"
        },
        {
            "playerEmail": "test3@example.com",
            "playerName": "Testy McTestface"
        }
    ],
    "currentPlayer": {
        "playerEmail": "test2@example.com",
        "playerName": "Testy McTestface"
    },
    "attackingPlayer": null,
    "logs": [
        {
            "logId": 51,
            "message": "New Game Started"
        },
        {
            "logId": 52,
            "message": "Testy McTestface goes first"
        }
    ],
    "countries": [
        {
            "gameDbId": 9,
            "controlledBy": {
                "playerEmail": "test@example.com",
                "playerName": "Testy McTestface"
            },
            "name": "Alaska",
            "countryId": 0,
            "printableName": "Alaska",
            "armies": 1
        },
        ...
        ...
        ...
        {
            "countryId": 41,
            "controlledBy": {
                "playerEmail": "test2@example.com",
                "playerName": "Testy McTestface"
            },
            "name": "WesternAustralia",
            "countryId": 41,
            "printableName": "W Australia",
            "armies": 1
        }
    ],
    "bonusArmies": 4,
    "stage": "DISCARD"
```

### game/quit/{gameID} (post Player)

```
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "authToken": {
        "authTokenId": 1,
        "authToken": "3df5860c-14a2-408f-bc7b-0042f27cb0ab"
    }
```

- returns success

```
Success
```

### game/exit/{gameId] (post Player)

```
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "authToken": {
        "authTokenId": 1,
        "authToken": "3df5860c-14a2-408f-bc7b-0042f27cb0ab"
    }
```

- returns success

```
Success
```

### game/log/{gameID} (post Player)

```
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "authToken": {
        "authTokenId": 1,
        "authToken": "3df5860c-14a2-408f-bc7b-0042f27cb0ab"
    }
```

- returns the game history

```
  {
  "message": "New Game Started"
  },
  {
  "message": "Testy McTestface goes first"
  }
```

### game/log/{gameId}/{log id} (post Player)

```
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "authToken": {
        "authTokenId": 1,
        "authToken": "3df5860c-14a2-408f-bc7b-0042f27cb0ab"
    }
```

- returns all log messages from a particular message to the most recent. If we don't use web sockets, we can have the
  log component call this on a timer and then update the UI game state if a new log is returned.

```
    {
        "message": "Testy McTestface goes first"
    }
```

## Game Play

### game/play/discard/{gameId} (post Player)

0 cards turned in for armies

- returns number of armies for player to place

### game/play/discard/{gameId}/{cardType1}/{cardType2}/{cardType3} (post Player)

3 cards turned in for armies

- returns number of armies for player to place

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

### TODO: Probably needed for gameplay

- get player hand
- get current player
- get current stage
- get current bonus armies
- get countries by player id
