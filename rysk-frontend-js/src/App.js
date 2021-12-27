import React, { useEffect } from "react";
import { useDispatch } from "react-redux";
import { updatePlayer } from "./features/player/playerSlice";
import { updateGame } from "./features/game/gameSlice";
import Game from "./pages/Game";
import { useSelector } from "react-redux";
import axios from "axios";

function App() {
  const loggedInPlayer = useSelector((state) => state.loggedInPlayer.value);
  const game = useSelector((state) => state.game.value);
  const dispatch = useDispatch();

  const loginPlayer = async (id) => {
    if (loggedInPlayer.playerId !== id) {
      await axios
        .post("http://localhost:8080/player/login", {
          playerEmail: id + "@example.com",
          playerPassword: "password",
        })
        .then((response) => {
          console.log("Player Response: ");
          console.log(response.data);
          dispatch(updatePlayer(response.data));
        })
        .catch((error) => {
          console.log("Error Player Response: ");
          console.log(error.response.data);
        });
    }
  };

  const loadGame = async (gameId) => {
    if (game.gameId !== gameId) {
      await axios
        .post("http://localhost:8080/game/" + gameId + "/join", loggedInPlayer)
        .then((response) => {
          console.log("Game Response: ");
          console.log(response.data);
          dispatch(updateGame(response.data));
        })
        .catch((error) => {
          console.log("Error Game Response: ");
          console.log(error.response.data);
        });
    }
  };
  useEffect(() => {
    if (undefined === loggedInPlayer.playerId) {
      loginPlayer(1);
    }
    if (game.gameId === -1){
    loadGame(1);}
    if (game.currentPlayer.playerId !== loggedInPlayer.playerId) {
      loginPlayer(game.currentPlayer.playerId);
    }
  });

  return (
    <div className="App">
      <Game />
    </div>
  );
}

export default App;
