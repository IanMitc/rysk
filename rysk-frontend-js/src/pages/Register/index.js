import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import "./index.css";
import Form from "react-bootstrap/esm/Form";
import axios from "axios";
import { updatePlayer } from "../../features/player/playerSlice";
import Button from "react-bootstrap/esm/Button";

const Register = () => {
  const loggedInPlayer = useSelector((state) => state.loggedInPlayer.value);
  const dispatch = useDispatch();

  const [player, setPlayer] = useState({
    playerName: "",
    playerEmail: "",
    playerAuthToken: "",
    playerPassword: "",
  });

  function onChangeHandler(event) {
    setPlayer({
      ...player,
      [event.target.name]: event.target.value,
    });
  }

  return (

    <div className="wrapper">
       <div className="col-lg-6" >
        
       <div className="col-lg-6" >
       
         
    <Form
      onSubmit={(event) => {
        event.preventDefault();
        axios
          .post("http://localhost:8080/player", player)
          .then((response) => {
            console.log(response);
            setPlayer({
              playerName: response.data.playerName,
              playerEmail: response.data.playerEmail,
              playerAuthToken: response.data.playerAuthToken,
              playerPassword: response.data.playerPassword,
            });
            dispatch(updatePlayer(response.data));
          })
          .catch((e) => console.log(e))
          .finally(() => {
          });
      }}
    >
      <h1>Register</h1>
      <Form.Group controlId="formGroupName">
        <Form.Label>Player Name</Form.Label>
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
</div>
</div>
</div>

    // <h1>
    //   {player.playerName}, {player.playerEmail}, {player.playerPassword}
    // </h1>

      
  );
};
export default Register;
