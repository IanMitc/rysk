import React from "react";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import { PlayerDisplay } from "./PlayerDisplay";
import { CountryCardItem } from "./CountryCardItem";
import { Logs } from "../Logs";
import { CardColumns, CardDeck, CardGroup } from "react-bootstrap";

export const GameBoard = () => {
  const loggedInPlayer = useSelector((state) => state.loggedInPlayer.value);
  const currentGameCountries = useSelector((state) => state.game.value.countries);
  const currentGamePlayers = useSelector((state) => state.game.value.players);
  const dispatch = useDispatch();

  return (
    <div className="container m-4">
      <CardGroup>
        {currentGamePlayers.map((player, index) => (
          <PlayerDisplay player={player} key={index} />
        ))}
      </CardGroup><br/>
      <CardColumns>
        {currentGameCountries.map((country, index) => (
          <CountryCardItem country={country} key={index} />
        ))}
      </CardColumns>
    </div>
  );
};
