import React from "react";
import { GameBoard } from "./Pages/GameBoard";
import  Register  from "./components/register/Register"
import Login from "./components/Login/Login";

function App() {
  return (
    <div className="App">
      <Register />
      <Login />
      <GameBoard />
    </div>
  );
}

export default App;
