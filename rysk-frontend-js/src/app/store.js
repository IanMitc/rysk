import { configureStore } from "@reduxjs/toolkit"
import playerReducer from '../features/player/playerSlice'
import gameReducer from '../features/game/gameSlice'

export const store = configureStore({
    reducer: {
        loggedInPlayer: playerReducer,
        game: gameReducer,
    },
})