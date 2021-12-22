import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  value: {
    playerId: undefined,
    playerEmail: undefined,
    playerName: undefined,
    playerAuthToken: undefined,
    playerPassword: undefined,
  },
};

export const playerSlice = createSlice({
  name: "loggedInPlayer",
  initialState,
  reducers: {
    updatePlayer: (state, action) => {
      state.value.playerId = action.payload.playerId;
      state.value.playerEmail = action.payload.playerEmail;
      state.value.playerName = action.payload.playerName;
      state.value.playerAuthToken = action.payload.playerAuthToken;
      state.value.playerPassword = action.payload.playerPassword;
    },
  },
});

export const { updatePlayer } = playerSlice.actions;
export const selectPlayer = (state) => state.loggedInPlayer;
export default playerSlice.reducer;
