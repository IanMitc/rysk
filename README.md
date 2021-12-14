1. Project Name
2. Project Description (short explanation of idea)
3. Feature list
4. Wire frame
5. App flow
6. Distribution of task between team members

# Rysk REST API version 0.2

## Player

### //server:port/player (post player)

- Register New Player

#### Input:

```
{
    "playerName": "Testy McTestface",
    "playerEmail": "test@example.com",
    "playerPassword": {
        "password": "password"
    }
}
```

- return Player with Auth Token. Can be saved in cookie or local storage. Adds player to system.

#### Output:

```    
{
    "playerId": 1,
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "playerAuthToken": {
        "authToken": "f03fc991-5258-4ecf-abb9-cf9093fb8fbe"
    },
    "playerPassword": {
        "password": ""
    }
}
```

### //server:port/player/email (post email)

- Find a player by Email Address

#### Input:

```
test@example.com
```

- returns Sanitized Player

#### Output:

```    
{
    "playerId": 1,
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "playerAuthToken": {
        "authToken": ""
    },
    "playerPassword": {
        "password": ""
    }
}
```

### //server:port/player (put updated player info)

- Update a Player

#### Input:

```
{
    "playerId": 1,
    "playerEmail": "updated@email.com",
    "playerName": "New Name",
    "playerAuthToken": {
        "authToken": "f03fc991-5258-4ecf-abb9-cf9093fb8fbe"
    },
    "playerPassword": {
        "password": "newPassword"
    }
}
```

- returns Sanitized Player with Auth Token

#### Output:

```
{
    "playerId": 1,
    "playerEmail": "updated@email.com",
    "playerName": "New Name",
    "playerAuthToken": {
        "authToken": "f03fc991-5258-4ecf-abb9-cf9093fb8fbe"
    },
    "playerPassword": {
        "password": ""
    }
}
```

### //server:port/player/login (post Player)

- log a Player into the system

#### Input:

```
{
    "playerEmail": "test@example.com",
    "playerPassword": {
        "password": "password"
    }
}
```

- returns Sanitized Player with Auth Token

#### Output:

```    
{
    "playerId": 1,
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "playerAuthToken": {
        "authToken": "3ef407ea-1d03-4105-a8f8-49153786b000"
    },
    "playerPassword": {
        "password": ""
    }
}
```

### //server:port/player/logout (post Player)

- Logs a Player out of the system

#### Input:

```
{
    "playerId": 1,
    "playerAuthToken": {
        "authToken": "3ef407ea-1d03-4105-a8f8-49153786b000"
    }
}
```

- returns success and removes Auth Token from system.

#### Output:

```
Success
```

### //server:port/player/login/check (post Player)

- Check if Auth Token is valid

#### Input:

```
 {
    "playerId": 1,
    "playerAuthToken": {
        "authToken": "54905046-4ad1-49bb-be23-90b95f1e35c0"
    }
}
```

- returns Sanitized Player with Auth Token

#### Output:

```
{
    "playerId": 1,
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "playerAuthToken": {
        "authToken": "54905046-4ad1-49bb-be23-90b95f1e35c0"
    },
    "playerPassword": {
        "password": ""
    }
}
```

### //server:port/player/games (post Player)

- returns all games that player is a participant in

## Game Management

### //server:port/game/new (post Players for game)

#### Input:

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

#### Output:

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

### //server:port/game/join/{gameID} (post Player)

#### Input:

```
    "playerEmail": "test3@example.com",
    "playerName": "Testy McTestface",
    "authToken": {
        "authToken": "4690fb69-0502-4c53-84e7-4093f8424ab7"
    }
```

- returns game board. Can be used for joining new or existing games also to update board in UI if needed.

#### Output:

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

### //server:port/game/quit/{gameID} (post Player)

#### Input:

```
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "authToken": {
        "authTokenId": 1,
        "authToken": "3df5860c-14a2-408f-bc7b-0042f27cb0ab"
    }
```

- returns success

#### Output:

```
Success
```

### //server:port/game/exit/{gameId] (post Player)

#### Input:

```
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "authToken": {
        "authTokenId": 1,
        "authToken": "3df5860c-14a2-408f-bc7b-0042f27cb0ab"
    }
```

- returns success

#### Output:

```
Success
```

### //server:port/game/log/{gameID} (post Player)

#### Input:

```
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "authToken": {
        "authTokenId": 1,
        "authToken": "3df5860c-14a2-408f-bc7b-0042f27cb0ab"
    }
```

- returns the game history

#### Output:

```
  {
  "message": "New Game Started"
  },
  {
  "message": "Testy McTestface goes first"
  }
```

### //server:port/game/log/{gameId}/{log id} (post Player)

#### Input:

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

#### Output:

```
    {
        "message": "Testy McTestface goes first"
    }
```

## Game Play

### //server:port/game/play/discard/{gameId} (post Player)

0 cards turned in for armies

- returns number of armies for player to place

### //server:port/game/play/discard/{gameId}/{cardType1}/{cardType2}/{cardType3} (post Player)

3 cards turned in for armies

- returns number of armies for player to place

### //server:port/game/play/armies (post Player, game id, country id, number of armies to add)

- returns updated country or failure if not auth, not turn, not controlled by player,

### //server:port/game/play/attack (post Player, attacking country id, number of attacking armies, number of dice, defending country id)

- returns up to 3 random numbers for dice roll or failure if not auth etc.

### //server:port/game/play/defend (post Player, defending country id, number of dice)

- returns up to 2 random numbers for dice roll or failure if not auth etc. Triggers logging of attack and if web sockets
  are implemented will tell players to update board info.

### //server:port/game/play/move (post Player, from country id, to country id, number of armies)

- returns updated countries or failure

### //server:port/game/play/draw (post Player)

- returns card if player took over a country, empty if not, or error. Also signals to the backend that the player's turn
  has ended.

### TODO: Probably needed for gameplay

- get player hand
- get current player
- get current stage
- get current bonus armies
- get countries by player id
