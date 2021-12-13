import React from "react";
import { Register } from "./Components/Register";
import { GameBoard } from "./Pages/GameBoard";

function App() {
  return (
    <div className="App">
      <GameBoard />
      <Register />
    </div>
  );
}

export default App;
