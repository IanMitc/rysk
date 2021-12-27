import axios from "axios";
import React from "react";
import { useState } from "react";
import { Button, ButtonGroup, Form } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import { updateGame } from "../../../../features/game/gameSlice";

export const Defend = () => {
  const loggedInPlayer = useSelector((state) => state.loggedInPlayer.value);
  const game = useSelector((state) => state.game.value);
  const dispatch = useDispatch();
  const attackingCountry = game.attackingCountry;
  const defendingCountry = game.defendingCountry;
  let attackingArmies = game.attackingArmies;

  const [defendDice, setDefendDice] = useState(1);

  const incrementDiceHandler = () => {
    if ((defendDice < 2) & (defendDice < defendingCountry.armies)) {
      setDefendDice(defendDice + 1);
    }
  };

  const decrementDiceHandler = () => {
    if (defendDice > 1) {
      setDefendDice(defendDice - 1);
    }
  };

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

  const onSubmitHandler = async (event) => {
    event.preventDefault();
    axios
      .post(
        "http://localhost:8080/game/" +
          game.gameId +
          "/play/defend/" +
          defendDice,
        loggedInPlayer
      )
      .then((response) => {
        console.log("In then");
        console.log(response);
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
      })
      .finally(() => {
        console.log("In finally");
        updateCurrentGame();
      });
  };

  return (
    <div>
      {game.currentPlayer.playerName} is Defending <br />
      Attacked by {game.attackingPlayer.playerName} <br /> 
      Attacking Country: {attackingCountry.printableName} <br />
      Armies:{" "}
      {attackingArmies}
      <br />
      Defending Country: {defendingCountry.printableName} <br /> 
      Armies:{" "}
      {defendingCountry.armies}
      <br />
      <Form onSubmit={onSubmitHandler}>
        <Form.Group>
          <Form.Label>
            Dice to Roll: <br />
            {defendDice}
          </Form.Label>
          <ButtonGroup>
            <Button
              variant="success"
              size="small"
              onClick={incrementDiceHandler}
            >
              +
            </Button>
            <Button
              variant="danger"
              size="small"
              onClick={decrementDiceHandler}
            >
              -
            </Button>
          </ButtonGroup>
        </Form.Group>
        <ButtonGroup>
          <Button variant="primary" type="submit">
            Submit
          </Button>
        </ButtonGroup>
      </Form>
    </div>
  );
};
