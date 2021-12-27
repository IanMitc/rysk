import axios from "axios";
import React from "react";
import { useState } from "react";
import { Button, ButtonGroup, Form } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import { updateGame } from "../../../../features/game/gameSlice";
import { CountryOption } from "../Armies/CountryOption";

export const Attack = () => {
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

  const [selectedCountry, setSelectedCountry] = useState(playersCountries[0]);
  const [armiesCanAttack, setArmiesCanAttack] = useState(
    playersCountries[0].armies - 1
  );
  const [armiesToAttack, setArmiesToAttack] = useState(
    playersCountries[0].armies - 1
  );

  let neighborsArray = [];
  countries.map((country) => {
    if (
      playersCountries[0].neighbors.includes(country.name) &&
      country.controlledBy.playerId !== loggedInPlayer.playerId
    )
      neighborsArray.push(country);
  });
  let [neighbors, setNeighbors] = useState(neighborsArray);

  const [defendingCountry, setDefendingCountry] = useState(neighborsArray[0]);
  const [attackDice, setAttackDice] = useState(1);

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
    if (armiesCanAttack > armiesToAttack) {
      setArmiesToAttack(armiesToAttack + 1);
    }
  };

  const decrementHandler = () => {
    if (armiesToAttack > 0) {
      setArmiesToAttack(armiesToAttack - 1);
    }
  };

  const incrementDiceHandler = () => {
    if ((attackDice < 3) & (attackDice < armiesToAttack)) {
      setAttackDice(attackDice + 1);
    }
  };

  const decrementDiceHandler = () => {
    if (attackDice > 1) {
      setAttackDice(attackDice - 1);
    }
  };

  
  const onAttackChangeHandler = (event) => {
    setSelectedCountry(countries[event.target.value]);
    let neighborsArray = [];
    countries.map((country) => {
      if (
        countries[event.target.value].neighbors.includes(country.name) &&
        country.controlledBy.playerId !== loggedInPlayer.playerId
      )
        neighborsArray.push(country);
    });
    setNeighbors(neighborsArray);
    console.log(countries[event.target.value].armies - 1);
    setArmiesCanAttack(countries[event.target.value].armies - 1);
    setArmiesToAttack(countries[event.target.value].armies - 1);
    setDefendingCountry(neighborsArray[0]);
  };

  const onDefendChangeHandler = (event) => {
    setDefendingCountry(game.countries[event.target.value]);
  };

  const endAttackHandler = async (event) => {
    event.preventDefault();
    axios
      .post(
        "http://localhost:8080/game/" + game.gameId + "/play/attack",
        loggedInPlayer
      )
      .then((response) => {
        console.log("In end attack then");
        console.log(response);
      })
      .catch((error) => {
        console.log("In end attack catch");
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
        console.log("In end attack finally");
        updateCurrentGame();
      });
  };
  
  const onSubmitHandler = async (event) => {
    event.preventDefault();
    console.log(loggedInPlayer);
    console.log(
      " " +
        game.gameId +
        " " +
        selectedCountry.countryId +
        " " +
        defendingCountry.countryId +
        " " +
        armiesToAttack +
        " " +
        attackDice
    );
    axios
      .post(
        "http://localhost:8080/game/" +
          game.gameId +
          "/play/attack/" +
          selectedCountry.countryId +
          "/" +
          defendingCountry.countryId +
          "/" +
          armiesToAttack +
          "/" +
          attackDice,
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
      Attack
      <Form onSubmit={onSubmitHandler}>
        <Form.Group>
          <Form.Label>Attacking Country</Form.Label>
          <Form.Control
            as="select"
            label="country"
            onChange={onAttackChangeHandler}
          >
            {playersCountries.map((country, id) => (
              <CountryOption country={country} key={id} />
            ))}
          </Form.Control>
        </Form.Group>
        <Form.Group>
          <Form.Label>Defending Country</Form.Label>
          <Form.Control
            as="select"
            label="country"
            onChange={onDefendChangeHandler}
          >
            {neighbors.map((country, id) => (
              <CountryOption country={country} key={id} />
            ))}
          </Form.Control>
        </Form.Group>
        <Form.Group>
          <Form.Label>
            Attacking Armies: <br />
            {armiesToAttack}/{armiesCanAttack}
          </Form.Label>
          <ButtonGroup>
            <Button variant="success" size="small" onClick={incrementHandler}>
              +
            </Button>
            <Button variant="danger" size="small" onClick={decrementHandler}>
              -
            </Button>
          </ButtonGroup>
          <Form.Label>
            Dice to Roll: <br />
            {attackDice}
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
          <Button variant="danger" onClick={endAttackHandler}>
            End Attack
          </Button>
        </ButtonGroup>
      </Form>
    </div>
  );
};
