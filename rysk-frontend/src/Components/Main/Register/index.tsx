import React, { useState } from "react";
// import "./register.css";
import axios from "axios";
import './index.css'

export const Register = () => {
  const [player, setPlayer] = useState({
    playerName: "",
    playerEmail: "",

    playerAuthToken: null || {
      authToken: "",
    },
    playerPassword: null || {
      password: "",
    },
  });

  const onChangeHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPlayer({
      ...player,
      [event.target.name]: event.target.value,
    });
    console.log(player);
  };

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

                    playerAuthToken: {
                      authToken: response.data.authToken.authToken,
                    },
                    playerPassword: {
                      password: response.data.playerPassword.password,
                    },
                  });
                })
                .catch((e) => console.log(e));
            }}
          >
            <h1>Register</h1>
            <div className="form-group">
              <label>Player Name</label>
              <input
                type="text"
                className="form-control"
                name="playerName"
                value={player.playerName}
                onChange={onChangeHandler}
              />
            </div>
            <div className="form-group">
              <label>Email</label>
              <input
                type="text"
                className="form-control"
                name="playerEmail"
                value={player.playerEmail}
                onChange={onChangeHandler}
              />
            </div>
            <div className="form-group">
              <label>Password</label>
              <input
                type="password"
                className="form-control"
                name="playerPassword.password"
                value={player.playerPassword.password}
                onChange={(event) => {
                  setPlayer((prevPlayer) => ({
                    ...prevPlayer,
                    playerPassword: {
                      password: event.target.value,
                    },
                  }));
                  console.log(player);
                }}
              />
            </div>
            <input
              type="submit"
              value="Register"
              className="btn btn-primary btn-block"
            />
          </form>
        </div>
      </div>
    </div>
  );
};
