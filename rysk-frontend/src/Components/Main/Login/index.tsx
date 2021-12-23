import React, { useState, useEffect } from "react";
import axios from "axios";
import { Player } from "../../Interfaces/Player/Player";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { Link } from "react-router-dom";
import { useCookies } from "react-cookie";
import { useAppDispatch, useAppSelector } from "../../Store/hooks";
import { updatePlayer } from "../../Slices/playerSlice";

export const Login = () => {
  const playerSelector = useAppSelector((state) => state.loggedInPlayer);
  const dispatch = useAppDispatch;

  const [player, setPlayer] = useState<Player>({});

  const [loading, setLoading] = useState<boolean>();

  const [cookies, setCookie] = useCookies(["player"]);

  const onChangeHandler = async (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setPlayer({
      ...player,
      [event.target.name]: event.target.value,
    });
  };

  return (
    <Form
      onSubmit={async (event) => {
        event.preventDefault();
        setLoading(true);
        const response = await axios
          .post("http://localhost:8080/player/login", player)
          .then((response) => {
            console.log(response);
            setPlayer(response.data);
          })
          .catch((error) => {
            console.log(error);
          })
          .finally(() => {
            setLoading(false);
            setCookie("player", player, { path: "/" });
            // dispatch();
          });
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
      <Form.Group controlId="formGroupButtons" className="row">
        <Button variant="primary" type="submit">
          Login
        </Button>
        <Link className="btn btn-secondary" to="/register">
          Register
        </Link>
      </Form.Group>
    </Form>
  );
};