import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { Login } from "./Components/Login";
import { GameBoard } from "./Pages/GameBoard";

function App() {
  return (
    <div className="App">
      <GameBoard />
      <Login />
    </div>
  );
}

export default App;
