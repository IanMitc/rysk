import axios from "axios";
import React from "react";
import { useState } from "react";
import { Container, FormGroup, ListGroup } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import { CheckableCard } from "./CheckableCard";

export const Discard = () => {
  const loggedInPlayer = useSelector((state) => state.loggedInPlayer.value);
  const game = useSelector((state) => state.game.value);
  const dispatch = useDispatch();

  const [errorMessage, setErrorMessage] = useState("");

  const [checkedCards, setCheckedCards] = useState({
    0: false,
    1: false,
    2: false,
    3: false,
    4: false,
  });

  const [count, setCount] = useState(0);

  const onCheckHandler = async (event) => {
    if ((count < 3) | !event.target.checked) {
      setErrorMessage("");
      setCheckedCards({
        ...checkedCards,
        [event.target.name]: event.target.checked,
      });

      if (event.target.checked) {
        setCount(count + 1);
      } else {
        setCount(count - 1);
      }
    } else {
      setErrorMessage("You can only choose 3 cards");
    }
  };

  const onClickHandler = async (event) => {
    await axios
      .post(
        "http://localhost:8080/game/" + game.gameId + "/play/discard",
        loggedInPlayer
      )
      .then((result) => {
        console.log(result);
      })
      .error((e) => {
        console.log(e);
      })
      .finally(() => {
        updateGame();
      });
  };

  const updateGame = async () => {
    const updatedGame = await axios.post(
      "http://localhost:8080/game/" + game.gameId + "join",
      loggedInPlayer
    );
    dispatch(updateGame, updatedGame);
  };

  const noCardsHandler = async () => {
    await axios
      .post(
        "http://localhost:8080/game/" + game.gameId + "/play/discard",
        loggedInPlayer
      )
      .then((result) => {
        console.log(result);
      })
      .error((e) => {
        console.log(e);
      })
      .finally(() => {
        updateGame();
      });
  };

  if (game.playersCards.cards === null) {
    noCardsHandler();
  }

  return (
    <Container>
      <h4>Choose 3 cards to Discard</h4>
      <FormGroup>
        <ListGroup>
          {game.playersCards.cards.length < 3
            ? game.playersCards.cards.map((card, id) => (
                <ListGroup.Item key={id}>{card.type}</ListGroup.Item>
              ))
            : game.playersCards.cards.map((card, id) => {
                console.log("This is the id: " + id);
                console.log(checkedCards[id]);
                return (
                  <CheckableCard
                    card={card}
                    checked={checkedCards[id]}
                    onChange={onCheckHandler}
                    name={id}
                    key={id}
                  />
                );
              })}
        </ListGroup>
      </FormGroup>
      {errorMessage}
    </Container>
  );
};
