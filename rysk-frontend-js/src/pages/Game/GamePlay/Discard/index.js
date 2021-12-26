import axios from "axios";
import React from "react";
import { useState } from "react";
import { Button, Container, Form, FormGroup, ListGroup } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import { updateGame } from "../../../../features/game/gameSlice";
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

  const onSubmitHandler = async (event) => {
    event.preventDefault();
    let selectedCards = [];
    for (let i = 0; i < 5; i++) {
      let cardChecked = checkedCards[i];
      if (cardChecked) {
        selectedCards.push(game.playersCards.cards[i]);
      }
    }
    if (selectedCards.length === 3) {
      let cardString =
        "/" +
        selectedCards[0].type +
        "/" +
        selectedCards[1].type +
        "/" +
        selectedCards[2].type;
      await axios
        .post(
          "http://localhost:8080/game/" +
            game.gameId +
            "/play/discard" +
            cardString,
          loggedInPlayer
        )
        .then((result) => {
          console.log(result);
        })
        .catch((e) => {
          console.log(e);
        })
        .finally(() => {
          updateCurrentGame();
        });
    } else {
      setErrorMessage("You must choose 3 cards");
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
      })
  };

  const noCardsHandler = async () => {
      await axios
      .post(
        "http://localhost:8080/game/" + game.gameId + "/play/discard",
        loggedInPlayer
      )
      .then((response) => {
        console.log(response);
      })
      .catch((e) => {
        console.log(e);
      })
      .finally(() => {
        updateCurrentGame();
      });
  };

  if (game.playersCards.cards === null) {
    noCardsHandler();
  }

  return (
    <Container>
      <h4>Choose cards to Discard(Select 3)</h4>
      <Form onSubmit={onSubmitHandler}>
        <FormGroup>
          <ListGroup>
            {game.playersCards.cards.length < 3
              ? game.playersCards.cards.map((card, id) => (
                  <ListGroup.Item key={id}>{card.type}</ListGroup.Item>
                ))
              : game.playersCards.cards.map((card, id) => {
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
        <Button variant="primary" type="submit">
          Submit
        </Button>
      </Form>
      {errorMessage}
    </Container>
  );
};
