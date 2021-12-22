import React, { useEffect, useState } from "react";
import axios from "axios";
import Form from "react-bootstrap/esm/Form";
import Button from "react-bootstrap/esm/Button";
import { Player } from "../../Interfaces/Player/Player";

export const Register = () => {
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
            "http://localhost:8080/player",
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
      <Form.Group controlId="formGroupName">
        <Form.Label>Name</Form.Label>
        <Form.Control
          type="text"
          name="playerName"
          value={player.playerName}
          onChange={onChangeHandler}
          placeholder="Enter Name"
        />
      </Form.Group>
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
        Register
      </Button>
    </Form>
  );};
