import axios from "axios";
import React from "react";
import { useState } from "react";
import { Button, ButtonGroup, Form } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import { updateGame } from "../../../../features/game/gameSlice";
import { CountryOption } from "./CountryOption";

export const Armies = () => {
  const loggedInPlayer = useSelector((state) => state.loggedInPlayer.value);
  const game = useSelector((state) => state.game.value);
  const countries = game.countries;
  const [armiesToPlay, setArmiesToPlay] = useState(game.armiesToPlay);
  const [armiesToAdd, setArmiesToAdd] = useState(0);
  const dispatch = useDispatch();

  let playersCountries = [];
  countries.map((country) => {
    if (country.controlledBy.playerId === loggedInPlayer.playerId)
      playersCountries.push(country);
  });

  const [selectedCountry, setSelectedCountry] = useState(playersCountries[0]);

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

  const incrementHandler = () => {
    if (armiesToPlay > 0) {
      setArmiesToPlay(armiesToPlay - 1);
      setArmiesToAdd(armiesToAdd + 1);
    }
  };

  const decrementHandler = () => {
    if (armiesToAdd > 0) {
      setArmiesToPlay(armiesToPlay + 1);
      setArmiesToAdd(armiesToAdd - 1);
    }
  };

  const onChangeHandler = (event) => {
    setSelectedCountry(game.countries[event.target.value]);
  };

  const onSubmitHandler = async (event) => {
    event.preventDefault();
    console.log(loggedInPlayer);
    console.log(
      " " + game.gameId + " " + selectedCountry.countryId + " " + armiesToAdd
    );
    axios
      .post(
        "http://localhost:8080/game/" +
          game.gameId +
          "/play/armies/" +
          selectedCountry.countryId +
          "/" +
          armiesToAdd,
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
      Place your Armies
      <br />
      You have {armiesToPlay} armies to play
      <Form onSubmit={onSubmitHandler}>
        <Form.Group controlId="countrySelect">
          <Form.Label>Choose a Country</Form.Label>
          <Form.Control as="select" label="country" onChange={onChangeHandler}>
            {playersCountries.map((country, id) => (
              <CountryOption country={country} key={id} />
            ))}
          </Form.Control>
        </Form.Group>
        <Form.Group>
          {armiesToAdd}{" "}
          <ButtonGroup>
            <Button variant="success" size="small" onClick={incrementHandler}>
              +
            </Button>
            <Button variant="danger" size="small" onClick={decrementHandler}>
              -
            </Button>
          </ButtonGroup>
        </Form.Group>
        <Button variant="primary" type="submit">
          Submit
        </Button>
      </Form>
    </div>
  );
};
