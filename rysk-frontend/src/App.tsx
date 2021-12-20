import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { Login } from "./Components/Login";
import { GameBoard } from "./Pages/GameBoard";
import { Register } from "./Components/Register";

function App() {
  return (
    <div className="App">
      <GameBoard />
      <Register />
      <Login />
    </div>
  );
}

export default App;
