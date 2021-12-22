import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  game: {
    gameId: undefined,
    players: undefined,
    currentPlayer: undefined,
    attackingPlayer: undefined,
    attackingCountry: undefined,
    defendingCountry: undefined,
    playersCards: undefined,
    logs: undefined,
    countries: undefined,
    attackingDice1: undefined,
    attackingDice2: undefined,
    attackingDice3: undefined,
    defendingDice1: undefined,
    defendingDice2: undefined,
    armiesToPlay: undefined,
    playerWon: undefined,
    bonusArmies: undefined,
    stage: undefined,
  },
};

export const gameSlice = createSlice({
  name: "game",
  initialState,
  reducers: {
    updateGame: (state, action) => {
      state.gameId = action.payload.gameId;
      state.players = action.payload.players;
      state.currentPlayer = action.payload.currentPlayer;
      state.attackingPlayer = action.payload.attackingPlayer;
      state.attackingCountry = action.payload.attackingCountry;
      state.defendingCountry = action.payload.defendingCountry;
      state.playersCards = action.payload.playersCards;
      state.logs = action.payload.logs;
      state.countries = action.payload.countries;
      state.attackingDice1 = action.payload.attackingDice1;
      state.attackingDice2 = action.payload.attackingDice2;
      state.attackingDice3 = action.payload.attackingDice3;
      state.defendingDice1 = action.payload.defendingDice1;
      state.defendingDice2 = action.payload.defendingDice2;
      state.armiesToPlay = action.payload.armiesToPlay;
      state.playerWon = action.payload.playerWon;
      state.bonusArmies = action.payload.bonusArmies;
      state.stage = action.payload.stage;
    },
  },
});

export const { updateGame } = gameSlice.actions;
export default gameSlice.reducer;
