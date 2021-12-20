import React, { useState, useEffect } from "react";
import axios from "axios";
import { Player } from "../../Interfaces/Player/Player";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

export const Login = () => {
  const [player, setPlayer] = useState<Player>({
    playerEmail: "",
    playerPassword: "",
  });

  useEffect(() => {
    console.log(player);
  });

  const onChangeHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPlayer({
      ...player,
      [event.target.name]: event.target.value,
    });
  };
  return (
    <Form
      onSubmit={async (event) => {
        event.preventDefault();
        try {
          const response = await axios.post(
            "http://localhost:8080/player/login",
            player
          );
          console.log(response);
          setPlayer({
            playerId: response.data.playerId,
            playerName: response.data.playerName,
            playerEmail: response.data.playerEmail,
            playerAuthToken: response.data.playerAuthToken,
            playerPassword: response.data.playerPassword,
          });
        } catch (error) {
          console.log(error);
        }
      }}
    >
      <Form.Group controlId="formGroupEmail">
        <Form.Label>Email</Form.Label>
        <Form.Control
          type="email"
          name="playerEmail"
          value={player.playerEmail}
          onChange={onChangeHandler}
          placeholder="Enter Email"
        />
      </Form.Group>
      <Form.Group controlId="formGroupPassword">
        <Form.Label>Password</Form.Label>
        <Form.Control
          type="password"
          name="playerPassword"
          value={player.playerPassword}
          onChange={onChangeHandler}
          placeholder="Enter Password"
        />
      </Form.Group>
      <Button variant="primary" type="submit" block={true}>
        Login
      </Button>
    </Form>
  );
};
