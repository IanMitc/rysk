import React from "react";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import Register from "./Components/Pages/Register";
import { updatePlayer } from "./features/player/playerSlice";

function App() {
  const loggedInPlayer = useSelector((state) => state.loggedInPlayer.value);
  const dispatch = useDispatch();

  const newPlayer = {
    playerId: 1,
    playerEmail: "test@email.com",
    playerName: "Name",
    playerAuthToken: "token",
    playerPassword: "password",
  };
  //dispatch(updatePlayer(newPlayer));

  console.log(loggedInPlayer);

  return (
    <div className="App">
      The player is {loggedInPlayer.playerName}
      <Register />
    </div>
  );
}

export default App;
