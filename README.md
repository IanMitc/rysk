Rysk is an api meant to assist in developing a web based board game. A basic web ui is included. 

Build and run the spring boot api with maven. to run the demo api create some users with the api and create a new game.
You can then run the react frontend by going into rysk-frontend-js, run npm install to download all dependencies 
then npm start.

The demo will log you in as the first player and as the players change, it will automatically log you in to the active 
player.

Rules for play can be found at https://www.ultraboardgames.com/risk/game-rules.php
Full Documentation and examples for the API are at the end of this document.

Credits:
Ian Mitchell:
API Design 
Spring Boot REST Server
Database Design and Setup
Demo Web Site
Documentation

Ahmadwali Zadron:
Register Componenet(Unused)
Login Component(Unused)

# Rysk REST API version 0.3

## Player

### //server:port/player

- Register a new Player.

#### Input: (post player)

```
{
    "playerName": "Testy McTestface",
    "playerEmail": "test@example.com",
    "playerPassword": "password"
}
```

- Returns a Player with Auth Token.

#### Output:

```    
{
    "playerId": 1,
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "playerAuthToken": "2979a5da-0d12-4d06-8ea9-73f6a205ff18"
}
```

### //server:port/player/email

- Find a player by Email Address.

#### Input: (post email)

```
test@example.com
```

- Returns a Player

#### Output:

```    
{
    "playerId": 53,
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface"
}
```

### //server:port/player

- Update a Player's name, email and password.

#### Input: (put Player)

```
{
    "playerId": 1,
    "playerEmail": "tasty@example.com",
    "playerName": "Tasty McTestface",
    "playerAuthToken": "2979a5da-0d12-4d06-8ea9-73f6a205ff18",
    "playerPassword": "newPassword"
}
```

- Returns a Player with an Auth Token.

#### Output:

```
{
    "playerId": 1,
    "playerEmail": "tasty@example.com",
    "playerName": "Tasty McTestface",
    "playerAuthToken": "2979a5da-0d12-4d06-8ea9-73f6a205ff18"
}
```

### //server:port/player/login

- Log a Player in.

#### Input: (post Player)

```
{
    "playerEmail": "test@example.com",
    "playerPassword": "password"
}
```

- Returns a sanitized Player with and Auth Token.

#### Output:

```    
{
    "playerId": 1,
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "playerAuthToken": "2979a5da-0d12-4d06-8ea9-73f6a205ff18"
}
```

### //server:port/player/logout

- Log a Player out.

#### Input: (post Player)

```
{
    "playerId": 1,
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "playerAuthToken": "2979a5da-0d12-4d06-8ea9-73f6a205ff18"
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
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "playerAuthToken": "2979a5da-0d12-4d06-8ea9-73f6a205ff18"
}
```

- Returns a Player with an Auth Token.

#### Output:

```
{
    "playerId": 1,
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "playerAuthToken": "af33be99-0eac-4619-a6b4-a838e513be9e"
}
```

### //server:port/player/games (post Player)

- Get all games that player is in.

#### Input: (post Player)

```
{
    "playerId": 1,
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "playerAuthToken": "2979a5da-0d12-4d06-8ea9-73f6a205ff18"
}
```

- Returns an array of games.

#### Output:

```
[
    {
        "gameId": 1,
        "players": [
           {
                "playerId": 1,
                "playerEmail": "test@example.com",
                "playerName": "Testy McTestface"
            },
            {
                "playerId": 53,
                "playerEmail": "test3@example.com",
                "playerName": "Testy McTestface"
            },
            {
                "playerId": 1,
                "playerEmail": "test@example.com",
                "playerName": "Testy McTestface"
            }
        ],
        "currentPlayer": {
            "playerId": 1,
            "playerEmail": "test@example.com",
            "playerName": "Testy McTestface"
        },
        "playerWon": false,
        "stage": "DISCARD"
    },
    {
        "gameId": 2,
        "players": [
            {
                "playerId": 1,
                "playerEmail": "test@example.com",
                "playerName": "Testy McTestface"
            },
            {
                "playerId": 53,
                "playerEmail": "test3@example.com",
                "playerName": "Testy McTestface"
            },
            {
                "playerId": 52,
                "playerEmail": "test2@example.com",
                "playerName": "Testy McTestface"
            }
        ],
        "currentPlayer": {
            "playerId": 52,
            "playerEmail": "test2@example.com",
            "playerName": "Testy McTestface"
        },
        "playerWon": false,
        "stage": "DISCARD"
    }
]
```

## Game Management

### //server:port/game/new

- Start a new game with 2-6 players. The first in the list has to be initiating player.

#### Input: (post Players)

```
[
    {
        "playerId": 1,
        "playerEmail": "test@example.com",
        "playerName": "Testy McTestface",
        "playerAuthToken": "2979a5da-0d12-4d06-8ea9-73f6a205ff18"
    },
    {
        "playerId": 1,
        "playerEmail": "test@example.com"
    },
    {
        "playerId": 52,
        "playerEmail": "test2@example.com"
    }
]
```

- Returns a new game board.

#### Output:

```
{
    "gameId": 1,
    "players": [
        {
            "playerId": 1,
            "playerEmail": "test@example.com",
            "playerName": "Testy McTestface"
        },
        {
            "playerId": 53,
            "playerEmail": "test3@example.com",
            "playerName": "Testy McTestface"
        },
        {
            "playerId": 52,
            "playerEmail": "test2@example.com",
            "playerName": "Testy McTestface"
        }
    ],
    "currentPlayer": {
        "playerId": 52,
        "playerEmail": "test2@example.com",
        "playerName": "Testy McTestface"
    },
    "attackingPlayer": null,
    "attackingCountry": null,
    "defendingCountry": null,
    "playersCards": {
        "cards": null,
        "heldBy": {
            "playerId": 53,
            "playerEmail": "test3@example.com",
            "playerName": "Testy McTestface"
        }
    },
    "logs": [
        {
            "message": "New Game Started"
        },
        {
            "message": "Testy McTestface goes first"
        }
    ],
    "countries": [
        {
            "controlledBy": {
                "playerId": 52,
                "playerEmail": "test2@example.com",
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
            "controlledBy": {
                "playerId": 1,
                "playerEmail": "test@example.com",
                "playerName": "Testy McTestface"
            },
            "name": "WesternAustralia",
            "countryId": 41,
            "printableName": "W Australia",
            "armies": 1
        }
    ],
    "attackingDice1": 0,
    "attackingDice2": 0,
    "attackingDice3": 0,
    "defendingDice1": 0,
    "defendingDice2": 0,
    "armiesToPlay": 0,
    "playerWon": false,
    "bonusArmies": 4,
    "stage": "DISCARD"
}
```

### //server:port/game/{gameID}/join

- Join a game that the player is participating in.

#### Input: (post Player)

```
{
    "playerId": 1,
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "playerAuthToken": "af33be99-0eac-4619-a6b4-a838e513be9e"
}
```

- Returns game board. Can be used for joining new or existing games also to update board in UI if needed.

#### Output:

```
 {
    "gameId": 1,
    "players": [
        {
            "playerId": 1,
            "playerEmail": "test@example.com",
            "playerName": "Testy McTestface"
        },
        {
            "playerId": 53,
            "playerEmail": "test3@example.com",
            "playerName": "Testy McTestface"
        },
        {
            "playerId": 52,
            "playerEmail": "test2@example.com",
            "playerName": "Testy McTestface"
        }
    ],
    "currentPlayer": {
        "playerId": 52,
        "playerEmail": "test2@example.com",
        "playerName": "Testy McTestface"
    },
    "attackingPlayer": null,
    "attackingCountry": null,
    "defendingCountry": null,
    "playersCards": {
        "cards": null,
        "heldBy": {
            "playerId": 53,
            "playerEmail": "test3@example.com",
            "playerName": "Testy McTestface"
        }
    },
    "logs": [
        {
            "message": "New Game Started"
        },
        {
            "message": "Testy McTestface goes first"
        }
    ],
    "countries": [
        {
            "controlledBy": {
                "playerId": 52,
                "playerEmail": "test2@example.com",
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
            "controlledBy": {
                "playerId": 1,
                "playerEmail": "test@example.com",
                "playerName": "Testy McTestface"
            },
            "name": "WesternAustralia",
            "countryId": 41,
            "printableName": "W Australia",
            "armies": 1
        }
    ],
    "attackingDice1": 0,
    "attackingDice2": 0,
    "attackingDice3": 0,
    "defendingDice1": 0,
    "defendingDice2": 0,
    "armiesToPlay": 0,
    "playerWon": false,
    "bonusArmies": 4,
    "stage": "DISCARD"
}
```

### //server:port/game/{gameID}/quit

- Quit a game that the player is participating in. Redistributes countries to other players with 1 army each.

#### Input: (post Player)

```
{
    "playerId": 1,
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "playerAuthToken": "af33be99-0eac-4619-a6b4-a838e513be9e"
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
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "playerAuthToken": "af33be99-0eac-4619-a6b4-a838e513be9e"
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
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "playerAuthToken": "af33be99-0eac-4619-a6b4-a838e513be9e"
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
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "playerAuthToken": "af33be99-0eac-4619-a6b4-a838e513be9e"
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
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "playerAuthToken": "af33be99-0eac-4619-a6b4-a838e513be9e"
}
```

- Returns the number of armies tha can be placed.

### //server:port/game/{gameId}/play/discard/{cardType1}/{cardType2}/{cardType3}

- Indicate the player wants to turn in cards for armies. Valid Card types are Horseman, Cannon, FootSoldier or Joker.

#### Input:  (post Player)

```
{
    "playerId": 1,
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "playerAuthToken": "af33be99-0eac-4619-a6b4-a838e513be9e"
}
```

- Returns the number of armies tha can be placed.

```

```

### //server:port/game/{gameId}/play/armies/{countryId}/{numberOfArmies}

- Add armies to a given country.

#### Input: (post Player)

```
{
    "playerId": 1,
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "playerAuthToken": "af33be99-0eac-4619-a6b4-a838e513be9e"
}
```

- Returns updated country.

```
{
    "controlledBy": {
        "playerId": 1,
        "playerEmail": "test@example.com",
        "playerName": "Testy McTestface"
    },
    "name": "Alaska",
    "countryId": 0,
    "printableName": "Alaska",
    "armies": 7
}
```

### //server:port/game/{gameId}/play/attack/{attackingCountryId}/{defendingCountryId}/{numberOfArmies}/{numberOfDice}

- Attack another country

#### Input: (post Player)

```
{
    "playerId": 1,
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "playerAuthToken": "af33be99-0eac-4619-a6b4-a838e513be9e"
}
```

- Returns an array of up to 3 random numbers for the dice roll.

### //server:port/game/{gameId}/play/attack/

- Ends the Attack stage

#### Input: (post Player)

```
{
    "playerId": 1,
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "playerAuthToken": "af33be99-0eac-4619-a6b4-a838e513be9e"
}
```

- Returns Success

#### Output:

```
Success
```

### //server:port/game/{gameId}/play/defend/{numberOfDice}

- Defend against an attack.

#### Input: (post Player)

```
{
    "playerId": 1,
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "playerAuthToken": "af33be99-0eac-4619-a6b4-a838e513be9e"
}
```

- Returns an array of up to 2 random numbers for the dice roll.

### //server:port/game/{gameId}/play/move/{fromCountryId}/{toCountryId}/{numberOfArmies}

- Move armies from one country to another.

#### Input: (post Player)

```
{
    "playerId": 1,
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "playerAuthToken": "af33be99-0eac-4619-a6b4-a838e513be9e"
}
```

- Returns an array of updated countries.

### //server:port/game/{gameId}/play/draw

- End turn. If the player took over another country, they draw a card.

#### Input: (post Player)

```
{
    "playerId": 1,
    "playerEmail": "test@example.com",
    "playerName": "Testy McTestface",
    "playerAuthToken": "af33be99-0eac-4619-a6b4-a838e513be9e"
}
```

- Returns a card.

### TODO: Probably needed for gameplay

- get player hand
- get current player
- get current stage
- get current bonus armies
- get countries by player id
