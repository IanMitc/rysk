import React, { useState } from "react";
import axios from "axios";
//import Form from "react-bootstrap/Form";
//import Button from "react-bootstrap/Button";


export const Login = () => {
    const [player, setPlayer] = useState({

        playerEmail: "",

        playerAuthToken: null || {
            authToken: "",
        },
        playerPassword: {
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

    // const onSubmit = () => {
    //     console.log(player);
    //     axios
    //         .post("http://localhost:8080/player/login", player)
    //         .then((response) => {
    //             console.log(response);
    //         })
    //         .catch((e) => console.log(e));
    // };

    return (
        <div className="row">
            <div className="col-lg-3" />
            <div className="col-lg-6">
                <div className="wrapper">
                    <form 
                    onSubmit={(event) => {
                        event.preventDefault();
                        axios
                        .post("http://localhost:8080/player/login", player)
                        .then((response) => {
                            console.log(response);
                            setPlayer({
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
                    }}>
                        <h1>Login</h1>
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
                                name="password"
                                value={player.playerPassword.password}
                                onChange={(event) => {
                                    setPlayer((prevPlayer) => ({
                                        ...prevPlayer,
                                        playerPassword: {
                                            ...prevPlayer.playerPassword,
                                            password: event.target.value,
                                        },
                                    }));
                                    console.log(player);
                                }}
                            />
                        </div>
                        <input
                            type="submit"
                            value="Login"
                            className="btn btn-primary btn-block"
                        />
                    </form>
                </div>
            </div>
        </div>
    );
};

