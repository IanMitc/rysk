import axios from "axios";
import React from "react";
import { useState } from "react";
import { Button, ButtonGroup, Form } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import { updateGame } from "../../../../features/game/gameSlice";
import { CountryOption } from "../Armies/CountryOption";

export const Move = () => {
  const loggedInPlayer = useSelector((state) => state.loggedInPlayer.value);
  const game = useSelector((state) => state.game.value);
  const dispatch = useDispatch();
  const countries = game.countries;

  let playersCountries = [];
  countries.map((country) => {
    if (
      (country.controlledBy.playerId === loggedInPlayer.playerId) &
      (country.armies > 1)
    )
      playersCountries.push(country);
  });

  const [selectedCountry, setSelectedCountry] = useState(playersCountries[0] ? playersCountries[0] : "");

  let neighborsArray = [];
  countries.map((country) => {
    if (
      playersCountries[0].neighbors.includes(country.name) &&
      country.controlledBy.playerId === loggedInPlayer.playerId
    )
      neighborsArray.push(country);
  });
  let [neighbors, setNeighbors] = useState(neighborsArray);

  const [armiesCanMove, setArmiesCanMove] = useState(
    playersCountries[0].armies - 1
  );
  const [armiesToMove, setArmiesToMove] = useState(0);

  const [toCountry, setToCountry] = useState(
    neighborsArray[0] ? neighborsArray[0] : ""
  );

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

  const skipMoveHandler = (event) => {
    event.preventDefault();
    axios
      .post(
        "http://localhost:8080/game/" + game.gameId + "/play/move",
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

  const onSubmitHandler = async (event) => {
    event.preventDefault();
    axios
      .post(
        "http://localhost:8080/game/" +
          game.gameId +
          "/play/move/" +
          selectedCountry.countryId +
          "/" +
          toCountry.countryId +
          "/" +
          armiesToMove,
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

  const onFromChangeHandler = (event) => {
    setSelectedCountry(countries[event.target.value]);
    let neighborsArray = [];
    countries.map((country) => {
      if (
        countries[event.target.value].neighbors.includes(country.name) &&
        country.controlledBy.playerId === loggedInPlayer.playerId
      )
        neighborsArray.push(country);
    });
    setNeighbors(neighborsArray);
    setArmiesCanMove(countries[event.target.value].armies - 1);
    setArmiesToMove(0);
    setToCountry(neighborsArray[0] ? neighborsArray[0] : "");
  };

  const onToChangeHandler = (event) => {
    setToCountry(game.countries[event.target.value]);
  };

  const incrementHandler = () => {
    if (armiesCanMove > armiesToMove) {
      setArmiesToMove(armiesToMove + 1);
    }
  };

  const decrementHandler = () => {
    if (armiesToMove > 0) {
      setArmiesToMove(armiesToMove - 1);
    }
  };

  let optionList = "";
  if (playersCountries[0] !== "") {
    optionList = playersCountries.map((country, id) => {
      return <CountryOption country={country} key={id} />;
    });
  }

  let neighborsOptionList = "";
  if (neighbors[0] !== "") {
    neighborsOptionList = neighbors.map((country, id) => (
      <CountryOption country={country} key={id} />
    ));
  }

  return (
    <div>
      {game.currentPlayer.playerName} is moving their armies.
      <br />
      <Form onSubmit={onSubmitHandler}>
        <Form.Group>
          <Form.Label>From Country</Form.Label>
          <Form.Control
            as="select"
            label="country"
            onChange={onFromChangeHandler}
          >
            {optionList}
          </Form.Control>
        </Form.Group>
        <Form.Group>
          <Form.Label>To Country</Form.Label>
          <Form.Control
            as="select"
            label="country"
            onChange={onToChangeHandler}
          >
            {neighborsOptionList}
          </Form.Control>
        </Form.Group>
        <Form.Group>
          <Form.Label>
            Attacking Armies: <br />
            {armiesToMove}/{armiesCanMove}
          </Form.Label>
          <ButtonGroup>
            <Button variant="success" size="small" onClick={incrementHandler}>
              +
            </Button>
            <Button variant="danger" size="small" onClick={decrementHandler}>
              -
            </Button>
          </ButtonGroup>
        </Form.Group>
        <ButtonGroup>
          <Button variant="primary" type="submit">
            Submit
          </Button>
          <Button variant="danger" onClick={skipMoveHandler}>
            Skip Move
          </Button>
        </ButtonGroup>
      </Form>
    </div>
  );
};
