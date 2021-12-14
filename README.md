1. Project Name
2. Project Description (short explanation of idea)
3. Feature list
4. Wire frame
5. App flow
6. Distribution of task between team members

# Rysk REST API version 0.3

## Player

### //server:port/player

- Register a new Player.

#### Input: (post player)

```
{
    "playerName": "Testy McTestface",
    "playerEmail": "test@example.com",
    "playerPassword": {
        "password": "password"
    }
}
```

- Returns a Player with Auth Token.

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

### //server:port/player/email

- Find a player by Email Address.

#### Input: (post email)

```
test@example.com
```

- Returns a sanitized Player

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

### //server:port/player

- Update a Player's name, email and password.

#### Input: (put Player)

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

- Returns a sanitized Player with an Auth Token.

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

### //server:port/player/login

- Log a Player in.

#### Input: (post Player)

```
{
    "playerEmail": "test@example.com",
    "playerPassword": {
        "password": "password"
    }
}
```

- Returns a sanitized Player with and Auth Token.

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

### //server:port/player/logout

- Log a Player out.

#### Input: (post Player)

```
{
    "playerId": 1,
    "playerAuthToken": {
        "authToken": "3ef407ea-1d03-4105-a8f8-49153786b000"
    }
}
```

- Returns 'Success'

#### Output:

```
Success
```

### //server:port/player/login/check

- Check if an Auth Token is still valid.

#### Input: (post Player)

```
 {
    "playerId": 1,
    "playerAuthToken": {
        "authToken": "54905046-4ad1-49bb-be23-90b95f1e35c0"
    }
}
```

- Returns a sanitized Player with an Auth Token.

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

- Get all games that player is in.

#### Input: (post Player)

```
 {
    "playerId": 1,
    "playerAuthToken": {
        "authToken": "54905046-4ad1-49bb-be23-90b95f1e35c0"
    }
}
```

- Returns array of game ids.

## Game Management

### //server:port/game/new

- Start a new game with 2-6 players. The first in the list has to be initiating player.

#### Input: (post Players)

```
[
     {
        "playerId": 1,
        "playerEmail": "test2@example.com",
        "playerAuthToken": {
           "authToken": "54905046-4ad1-49bb-be23-90b95f1e35c0"
        }
    },
    {
        "playerId": 2,
        "playerEmail": "test2@example.com"
    }
]
```

- Returns a new game board.

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

### //server:port/game/{gameID}/join

- Join a game that the player is participating in.

#### Input: (post Player)

```
{
    "playerId": 1,
    "playerAuthToken": {
        "authToken": "3ef407ea-1d03-4105-a8f8-49153786b000"
    }
}
```

- Returns game board. Can be used for joining new or existing games also to update board in UI if needed.

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

### //server:port/game/{gameID}/quit

- Quit a game that the player is participating in. Redistributes countries to other players with 1 army each.

#### Input: (post Player)

```
{
    "playerId": 1,
    "playerAuthToken": {
        "authToken": "3ef407ea-1d03-4105-a8f8-49153786b000"
    }
}
```

- Returns 'Success'.

#### Output:

```
Success
```

### //server:port/game/{gameId]/exit

- Notify other players the player is stepping away for a bit. Gameplay continues until the players next turn.

### Input: (post Player)

```
{
    "playerId": 1,
    "playerAuthToken": {
        "authToken": "3ef407ea-1d03-4105-a8f8-49153786b000"
    }
}
```

- Returns 'Success'.

#### Output:

```
Success
```

### //server:port/game/{gameID}/log

- Get the full log of a games messages.

### Input: (post Player)

```
{
    "playerId": 1,
    "playerAuthToken": {
        "authToken": "3ef407ea-1d03-4105-a8f8-49153786b000"
    }
}
```

- Returns an array of log messages.

#### Output:

```
  {
  "message": "New Game Started"
  },
  {
  "message": "Testy McTestface goes first"
  }
```

### //server:port/game/{gameId}/log/{logId}

- Get all messages after the requested message. For example, requesting log 4 will return all logs starting with log 5.

#### Input: (post Player)

```
{
    "playerId": 1,
    "playerAuthToken": {
        "authToken": "3ef407ea-1d03-4105-a8f8-49153786b000"
    }
}
```

- Returns an array of log messages.

#### Output:

```
    {
        "message": "Testy McTestface goes first"
    }
```

## Game Play

### //server:port/game/{gameId}/play/discard

- Indicate the player wants to turn in 0 cards for armies. Will send an error if Player has 5 cards and has to discard.

#### Input: (post Player)

```
{
    "playerId": 1,
    "playerAuthToken": {
        "authToken": "3ef407ea-1d03-4105-a8f8-49153786b000"
    }
}
```

- Returns the number of armies tha can be placed.

### //server:port/game/{gameId}/play/discard/{cardType1}/{cardType2}/{cardType3}

- Indicate the player wants to turn in cards for armies. Valid Card types are Horseman, Cannon, FootSoldier or Joker.

#### Input:  (post Player)

```
{
    "playerId": 1,
    "playerAuthToken": {
        "authToken": "3ef407ea-1d03-4105-a8f8-49153786b000"
    }
}
```

- Returns the number of armies tha can be placed.

### //server:port/game/{gameId}/play/armies/{countryId}/{numberOfArmies}

- Add armies to a given country.

#### Input: (post Player)

```
{
    "playerId": 1,
    "playerAuthToken": {
        "authToken": "3ef407ea-1d03-4105-a8f8-49153786b000"
    }
}
```

- Returns updated country.

### //server:port/game/{gameId}/play/attack/{attackingCountryId}/{defendingCountryId}/{numberOfArmies}/{numberOfDice}

- Attack another country

#### Input: (post Player)

```
{
    "playerId": 1,
    "playerAuthToken": {
        "authToken": "3ef407ea-1d03-4105-a8f8-49153786b000"
    }
}
```

- Returns an array of up to 3 random numbers for the dice roll.

### //server:port/game/{gameId}/play/defend/{numberOfDice}

- Defend against an attack.

#### Input: (post Player)

```
{
    "playerId": 1,
    "playerAuthToken": {
        "authToken": "3ef407ea-1d03-4105-a8f8-49153786b000"
    }
}
```

- Returns an array of up to 2 random numbers for the dice roll.

### //server:port/game/{gameId}/play/move/{fromCountryId}/{toCountryId}/{numberOfArmies}

- Move armies from one country to another.

#### Input: (post Player)

```
{
    "playerId": 1,
    "playerAuthToken": {
        "authToken": "3ef407ea-1d03-4105-a8f8-49153786b000"
    }
}
```

- Returns an array of updated countries.

### //server:port/game/{gameId}/play/draw

- End turn. If the player took over another country, they draw a card.

#### Input: (post Player)

```
{
    "playerId": 1,
    "playerAuthToken": {
        "authToken": "3ef407ea-1d03-4105-a8f8-49153786b000"
    }
}
```

- Returns a card.

### TODO: Probably needed for gameplay

- get player hand
- get current player
- get current stage
- get current bonus armies
- get countries by player id
