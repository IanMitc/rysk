import React from "react";
import { GameBoard } from "./GameBoard";
import { Logs } from "./Logs";

function Game() {
  return (
    <div className="App">
      <GameBoard />
      <Logs />
    </div>
  );
}

export default Game;
