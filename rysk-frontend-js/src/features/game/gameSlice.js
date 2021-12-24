import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  value: {
    gameId: -1,
    players: [
      {
        playerId: -1,
        playerEmail: "",
        playerName: "",
      },
    ],
    currentPlayer: {
      playerId: -1,
      playerEmail: "",
      playerName: "",
    },
    attackingPlayer: {
      playerId: -1,
      playerEmail: "",
      playerName: "",
    },
    attackingCountry: {
      controlledBy: {
        playerId: -1,
        playerEmail: "",
        playerName: "",
      },
      name: "",
      countryId: -1,
      printableName: "",
      armies: -1,
    },
    defendingCountry: {
      controlledBy: {
        playerId: -1,
        playerEmail: "",
        playerName: "",
      },
      name: "",
      countryId: -1,
      printableName: "",
      armies: -1,
    },
    playersCards: { cards: [{ type: "" }] }, //Possible types: Horseman, Cannon, FootSoldier, Joker
    logs: [{ message: "" }],
    countries: [
      {
        controlledBy: {
          playerId: -1,
          playerEmail: "",
          playerName: "",
        },
        name: "",
        countryId: -1,
        printableName: "",
        armies: -1,
      },
    ],
    attackingDice1: -1,
    attackingDice2: -1,
    attackingDice3: -1,
    defendingDice1: -1,
    defendingDice2: -1,
    armiesToPlay: -1,
    playerWon: false,
    bonusArmies: -1,
    stage: "", //Possible stages: DISCARD, ARMIES, ATTACK, DEFEND, MOVE, DRAW
  },
};

export const gameSlice = createSlice({
  name: "game",
  initialState,
  reducers: {
    updateGame: (state, action) => {
      state.value = action.payload;
    },
  },
});

export const { updateGame } = gameSlice.actions;
export default gameSlice.reducer;
