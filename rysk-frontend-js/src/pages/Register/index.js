import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import "./index.css";
import axios from "axios";
import { updatePlayer } from "../../features/player/playerSlice";

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
    <div className="row">
      <div className="col-lg-3" />
      <div className="col-lg-6">
        <div className="wrapper">
          <form
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
            <div className="form-group">
              <label htmlFor="playerName">Player Name</label>
              <input
                type="text"
                className="form-control"
                name="playerName"
                value={player.playerName}
                onChange={onChangeHandler}
              />
            </div>
            <div className="form-group">
              <label htmlFor="playerEmail">Email</label>
              <input
                type="text"
                className="form-control"
                name="playerEmail"
                value={player.playerEmail}
                onChange={onChangeHandler}
              />
            </div>
            <div className="form-group">
              <label htmlFor="playerPassword">Password</label>
              <input
                type="text"
                className="form-control"
                name="playerPassword"
                value={player.playerPassword}
                onChange={onChangeHandler}
              />
            </div>
            <input
              type="submit"
              value="Register"
              className="btn btn-primary btn-block"
            />
          </form>
        </div>
        <h1>
          {player.playerName}, {player.playerEmail}, {player.playerPassword}
        </h1>
      </div>
      <div className="col-lg-"></div>
    </div>
  );
};
export default Register;
