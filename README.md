1. Project Name 
2. Project Description (short explanation of idea) 
3. Feature list 
4. Wire frame 
5. App flow 
6. Distribution of task between team members 

# Rysk REST API version 1.0

## Player

### player (post player, password)

- return player and auth token. Adds player to system

### player/{email} (get)

- returns player

### player/{id} (put authkey, updated player info)

- returns updated player should be saved to cookie or local storage so user will stay logged in.

### player/login (post player email, password)

- returns auth token to use as for length of session. Can be saved in cookie or local storage with user info. we could just say success and assume the client side isn't going to try and inject moves for other players for now.

### player/logout (post auth token, player id)

- returns empty if success and removes auth token from system so can no longer be used.

### player/login/check (post auth token, player id)

- returns existing or new auth token if valid or empty if invalid

### player/games (post auth token, player id)

- returns games that player is a participant in

## Game Management

### game/new (post auth token, player id, other player ids for game)

- returns "empty" game board for a new game while waiting for other players.

### game/decline (post auth token, user id, game id)

- returns success or failure

### game/join (post auth token, user id, game id)

- returns game board. Can be used for joining new or existing games also to update board in UI if needed.

### game/quit (post auth token, player id, game id)

- returns current game board but player is no longer active (auto distribute countries to other players or make neutral?)

### game/exit (post auth token, player id, game id)

- returns success or error. Allows game play to continue until this players turn (or is attacked) and then waits for that player to rejoin.

### game/log (post auth token, player id, game id)

- returns the game history

### game/log/{log id} (post auth token, player id, game id)

- returns all log messages from a particular message to the most recent. If we don't use web sockets, we can have the log component call this on a timer and then update the UI game state if a new log is returned.

## Game Play

### game/play/discard (post auth token, player id, 0 or 3 cards)

- returns number of additional armies earned

### game/play/armies (post auth token, player id, game id, country id, number of armies to add)

- returns updated country or failure if not auth, not turn, not controlled by player,

### game/play/attack (post auth token, player id, attacking country id, number of attacking armies, number of dice, defending country id)

- returns up to 3 random numbers for dice roll or failure if not auth etc.

### game/play/defend (post auth token, player id, defending country id, number of dice)

- returns up to 2 random numbers for dice roll or failure if not auth etc. Triggers logging of attack and if web sockets are implemented will tell players to update board info.

### game/play/move (post auth token, player id, from country id, to country id)

- returns updated countries or failure

### game/play/draw (post auth token, player id)

- returns card if player took over a country, empty if not, or error. Also signals to the backend that the player's turn has ended.
