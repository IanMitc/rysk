import { configureStore } from "@reduxjs/toolkit";
import  playerReducer  from "../Slices/playerSlice";
import gameReducer from "../Slices/gameSlice"

export const store = configureStore({
  reducer: {
    loggedInPlayer: playerReducer,
    currentGame: gameReducer,
  },
});

// Infer the `RootState` and `AppDispatch` types from the store itself
export type RootState = ReturnType<typeof store.getState>;
// Inferred type: {posts: PostsState, comments: CommentsState, users: UsersState}
export type AppDispatch = typeof store.dispatch;
