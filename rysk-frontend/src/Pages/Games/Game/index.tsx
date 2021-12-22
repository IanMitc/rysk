import React, { useState } from "react";
import { GameState } from "../../../Interfaces/Game/GameState";

export const Game = () => {
  const [game, setGame] = useState<GameState>();

  return (
    <React.Fragment>
      <tr>
        <td>props.game.id</td>
        <td>props.game.name</td>
        <td>props.game.numberOfCountries</td>
        <td>props.game.numberOfPlayers</td>
        <td></td>
      </tr>
    </React.Fragment>
  );
};
