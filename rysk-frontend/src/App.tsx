import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { Login } from "./Components/Login";
import { GameBoard } from "./Pages/GameBoard";
import { Register } from "./Components/Register";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Games } from "./Pages/Games";
import { Player } from "./Interfaces/Player/Player";
import { useCookies } from "react-cookie";

function App() {
  const [player, setPlayer] = useState<Player>();
  const [cookies, setCookie] = useCookies(["player"]);

  useEffect(() => {
    // setPlayer(cookies.player);
  });

  return (
    <BrowserRouter>
      <Routes>
        <Route path="home" element={<Games />} />
        <Route path="game" element={<GameBoard />} />
        <Route path="register" element={<Register />} />
        <Route path="login" element={<Login />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
