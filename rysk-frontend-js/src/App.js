import React, { useEffect } from "react";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import Register from "./pages/Register";
import { updatePlayer } from "./features/player/playerSlice";
import { updateGame } from "./features/game/gameSlice";
import { GameBoard } from "./components/Game/GameBoard";
import Logs from "./components/Game/Logs";

function App() {
  const dispatch = useDispatch();

  const newPlayer = {
    playerId: 1,
    playerEmail: "test@email.com",
    playerName: "Name",
    playerAuthToken: "token",
    playerPassword: "password",
  };
  //dispatch(updatePlayer(newPlayer));
  dispatch(
    updatePlayer({
      playerId: 1,
      playerEmail: "1@example.com",
      playerName: "Player 1",
      playerAuthToken: "42776053-39f3-484c-80d4-3d89ff38fbd9",
    })
  );
  dispatch(
    updateGame({
      gameId: 1,
      players: [
        {
          playerId: 3,
          playerEmail: "3@example.com",
          playerName: "Player 3",
        },
        {
          playerId: 2,
          playerEmail: "2@example.com",
          playerName: "Player 2",
        },
        {
          playerId: 1,
          playerEmail: "1@example.com",
          playerName: "Player 1",
        },
      ],
      currentPlayer: {
        playerId: 1,
        playerEmail: "1@example.com",
        playerName: "Player 1",
      },
      attackingPlayer: null,
      attackingCountry: null,
      defendingCountry: null,
      playersCards: {
        cards: null,
      },
      logs: [
        {
          message: "New Game Started",
        },
        {
          message: "Player 1 goes first",
        },
      ],
      countries: [
        {
          controlledBy: {
            playerId: 2,
            playerEmail: "2@example.com",
            playerName: "Player 2",
          },
          name: "Alaska",
          countryId: 0,
          printableName: "Alaska",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 1,
            playerEmail: "1@example.com",
            playerName: "Player 1",
          },
          name: "Alberta",
          countryId: 1,
          printableName: "Alberta",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 3,
            playerEmail: "3@example.com",
            playerName: "Player 3",
          },
          name: "CentralAmerica",
          countryId: 2,
          printableName: "Central America",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 1,
            playerEmail: "1@example.com",
            playerName: "Player 1",
          },
          name: "EasternUnitedStates",
          countryId: 3,
          printableName: "Eastern US",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 1,
            playerEmail: "1@example.com",
            playerName: "Player 1",
          },
          name: "Greenland",
          countryId: 4,
          printableName: "Greenland",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 2,
            playerEmail: "2@example.com",
            playerName: "Player 2",
          },
          name: "NorthwestTerritory",
          countryId: 5,
          printableName: "NW Territory",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 3,
            playerEmail: "3@example.com",
            playerName: "Player 3",
          },
          name: "Ontario",
          countryId: 6,
          printableName: "Ontario",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 1,
            playerEmail: "1@example.com",
            playerName: "Player 1",
          },
          name: "Quebec",
          countryId: 7,
          printableName: "Quebec",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 3,
            playerEmail: "3@example.com",
            playerName: "Player 3",
          },
          name: "WesternUnitedStates",
          countryId: 8,
          printableName: "Western US",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 3,
            playerEmail: "3@example.com",
            playerName: "Player 3",
          },
          name: "Argentina",
          countryId: 9,
          printableName: "Argentina",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 3,
            playerEmail: "3@example.com",
            playerName: "Player 3",
          },
          name: "Brazil",
          countryId: 10,
          printableName: "Brazil",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 3,
            playerEmail: "3@example.com",
            playerName: "Player 3",
          },
          name: "Peru",
          countryId: 11,
          printableName: "Peru",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 3,
            playerEmail: "3@example.com",
            playerName: "Player 3",
          },
          name: "Venezuela",
          countryId: 12,
          printableName: "Venezuela",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 1,
            playerEmail: "1@example.com",
            playerName: "Player 1",
          },
          name: "GreatBritain",
          countryId: 13,
          printableName: "Great Britain",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 1,
            playerEmail: "1@example.com",
            playerName: "Player 1",
          },
          name: "Iceland",
          countryId: 14,
          printableName: "Iceland",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 1,
            playerEmail: "1@example.com",
            playerName: "Player 1",
          },
          name: "NorthernEurope",
          countryId: 15,
          printableName: "N Europe",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 2,
            playerEmail: "2@example.com",
            playerName: "Player 2",
          },
          name: "Scandinavia",
          countryId: 16,
          printableName: "Scandinavia",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 2,
            playerEmail: "2@example.com",
            playerName: "Player 2",
          },
          name: "SouthernEurope",
          countryId: 17,
          printableName: "S Europe",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 3,
            playerEmail: "3@example.com",
            playerName: "Player 3",
          },
          name: "Ukraine",
          countryId: 18,
          printableName: "Ukraine",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 3,
            playerEmail: "3@example.com",
            playerName: "Player 3",
          },
          name: "WesternEurope",
          countryId: 19,
          printableName: "W Europe",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 2,
            playerEmail: "2@example.com",
            playerName: "Player 2",
          },
          name: "Congo",
          countryId: 20,
          printableName: "Congo",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 3,
            playerEmail: "3@example.com",
            playerName: "Player 3",
          },
          name: "EastAfrica",
          countryId: 21,
          printableName: "E Africa",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 1,
            playerEmail: "1@example.com",
            playerName: "Player 1",
          },
          name: "Egypt",
          countryId: 22,
          printableName: "Egypt",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 2,
            playerEmail: "2@example.com",
            playerName: "Player 2",
          },
          name: "Madagascar",
          countryId: 23,
          printableName: "Madagascar",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 3,
            playerEmail: "3@example.com",
            playerName: "Player 3",
          },
          name: "NorthAfrica",
          countryId: 24,
          printableName: "N Africa",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 1,
            playerEmail: "1@example.com",
            playerName: "Player 1",
          },
          name: "SouthAfrica",
          countryId: 25,
          printableName: "S Africa",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 2,
            playerEmail: "2@example.com",
            playerName: "Player 2",
          },
          name: "Afghanistan",
          countryId: 26,
          printableName: "Afghanistan",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 2,
            playerEmail: "2@example.com",
            playerName: "Player 2",
          },
          name: "China",
          countryId: 27,
          printableName: "China",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 2,
            playerEmail: "2@example.com",
            playerName: "Player 2",
          },
          name: "India",
          countryId: 28,
          printableName: "India",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 2,
            playerEmail: "2@example.com",
            playerName: "Player 2",
          },
          name: "Irkutsk",
          countryId: 29,
          printableName: "Irkutsk",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 1,
            playerEmail: "1@example.com",
            playerName: "Player 1",
          },
          name: "Japan",
          countryId: 30,
          printableName: "Japan",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 2,
            playerEmail: "2@example.com",
            playerName: "Player 2",
          },
          name: "Kamchatka",
          countryId: 31,
          printableName: "Kamchatka",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 1,
            playerEmail: "1@example.com",
            playerName: "Player 1",
          },
          name: "MiddleEast",
          countryId: 32,
          printableName: "Middle East",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 3,
            playerEmail: "3@example.com",
            playerName: "Player 3",
          },
          name: "Mongolia",
          countryId: 33,
          printableName: "Mongolia",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 2,
            playerEmail: "2@example.com",
            playerName: "Player 2",
          },
          name: "Siam",
          countryId: 34,
          printableName: "Siam",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 2,
            playerEmail: "2@example.com",
            playerName: "Player 2",
          },
          name: "Siberia",
          countryId: 35,
          printableName: "Siberia",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 3,
            playerEmail: "3@example.com",
            playerName: "Player 3",
          },
          name: "Ural",
          countryId: 36,
          printableName: "Ural",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 1,
            playerEmail: "1@example.com",
            playerName: "Player 1",
          },
          name: "Yakutsk",
          countryId: 37,
          printableName: "Yakutsk",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 3,
            playerEmail: "3@example.com",
            playerName: "Player 3",
          },
          name: "EasternAustralia",
          countryId: 38,
          printableName: "E Australia",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 1,
            playerEmail: "1@example.com",
            playerName: "Player 1",
          },
          name: "Indonesia",
          countryId: 39,
          printableName: "Indonesia",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 2,
            playerEmail: "2@example.com",
            playerName: "Player 2",
          },
          name: "NewGuinea",
          countryId: 40,
          printableName: "New Guinea",
          armies: 1,
        },
        {
          controlledBy: {
            playerId: 1,
            playerEmail: "1@example.com",
            playerName: "Player 1",
          },
          name: "WesternAustralia",
          countryId: 41,
          printableName: "W Australia",
          armies: 1,
        },
      ],
      attackingDice1: 0,
      attackingDice2: 0,
      attackingDice3: 0,
      defendingDice1: 0,
      defendingDice2: 0,
      armiesToPlay: 0,
      playerWon: false,
      bonusArmies: 4,
      stage: "DISCARD",
    })
  );

  return (
    <div className="App">
      <GameBoard />
      <Logs />
    </div>
  );
}

export default App;
