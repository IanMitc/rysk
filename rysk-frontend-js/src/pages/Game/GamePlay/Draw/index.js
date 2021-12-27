import axios from "axios";
import React, { useState } from "react";
import { Button, ListGroup } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import { updateGame } from "../../../../features/game/gameSlice";

export const Draw = () => {
  const loggedInPlayer = useSelector((state) => state.loggedInPlayer.value);
  const game = useSelector((state) => state.game.value);
  const dispatch = useDispatch();
  const [drawnCard, setDrawnCard] = useState("");

  const updateCurrentGame = async () => {
    await axios
      .post(
        "http://localhost:8080/game/" + game.gameId + "/join",
        loggedInPlayer
      )
      .then((response) => {
        dispatch(updateGame(response.data));
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const drawCard = async () => {
    await axios
      .post(
        "http://localhost:8080/game/" + game.gameId + "/play/draw",
        loggedInPlayer
      )
      .then((response) => {
        setDrawnCard(response.data);
      })
      .catch((error) => {
        console.log("In catch");
        if (error.response) {
          // The request was made and the server responded with a status code
          // that falls out of the range of 2xx
          console.log(error.response.data);
          console.log(error.response.status);
          console.log(error.response.headers);
        } else if (error.request) {
          // The request was made but no response was received
          // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
          // http.ClientRequest in node.js
          console.log(error.request);
        } else {
          // Something happened in setting up the request that triggered an Error
          console.log("Error", error.message);
        }
        console.log(error.config);
      });
  };

  drawCard();

  let drawnCardItem = ""
  if (drawnCard !== null){
      drawnCardItem = drawnCard;
  }
  let cardItems = "";
  if (game.playersCards.cards !== null) {
    cardItems = game.playersCards.cards.map((card, id) => (
      <ListGroup.Item key={id}>{card.type}</ListGroup.Item>
    ));
  }
  return (
    <div>
      {game.currentPlayer.playerName} is Drawing a card
      <br />
      You drew: <br/>
      {drawnCardItem}
      Current Hand: <br/>
      <ListGroup>{cardItems}</ListGroup>
      <Button type="primary" onClick={updateCurrentGame}>End Turn</Button>
    </div>
    
  );
};
