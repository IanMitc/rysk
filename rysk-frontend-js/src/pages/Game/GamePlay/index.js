import React from "react";
import { useSelector } from "react-redux";
import { Discard } from "./Discard";
import { Armies } from "./Armies";
import { Attack } from "./Attack";
import { Defend } from "./Defend";
import { Move } from "./Move";
import { Draw } from "./Draw";

export const GamePlay = () => {
  const stage = useSelector((state) => state.game.value.stage);
  switch (stage) {
    case "DISCARD":
      return <Discard />;
    case "ARMIES":
      return <Armies />;
    case "ATTACK":
      return <Attack />;
    case "DEFEND":
      return <Defend />;
    case "MOVE":
      return <Move />;
    case "DRAW":
      return <Draw />;
    default:
      return <div>Game Play Area</div>;
  }
};
