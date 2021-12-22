import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { Player } from "../Interfaces/Player/Player";
import { RootState } from "../Store/store";

const initialState = {
  playerId: 0,
  playerEmail: "",
  playerName: "",
  playerPassword: "",
  playerAuthToken: "",
} as Player;

export const playerSlice = createSlice({
  name: "loggedInPlayer",
  initialState,
  reducers: {
    updatePlayer: (state, action: PayloadAction<Player>) => {
      state = action.payload;
    },
  },
});

export const { updatePlayer } = playerSlice.actions;
export const selectPlayer = (state: RootState) => state.loggedInPlayer;
export default playerSlice.reducer;
