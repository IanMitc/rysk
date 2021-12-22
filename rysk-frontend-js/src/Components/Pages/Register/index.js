import React, { useState } from 'react';
import './index.css'

const Register = () => {

    const [player, setPlayer] = useState({
        playerName: '',
        playerEmail: '',
        playerAuthToken: null || {
            authToken: "",
        },
        playerPassword: null || {
            password: "",
        },
    })

    function onChangeHandler(event) {
        //console.log(event.target.name)
        setPlayer({
            ...player,
            [event.target.playerName]: event.target.value
        })

    }

    return (
        <div className="row">
            <div className="col-lg-3"></div>
            <div className="col-lg-6">
                <div className="wrapper">
                    <form onSubmit={(event) => {
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
                            <label htmlFor="">Player Name</label>
                            <input type="text" className="form-control" name="playerName" value={player.playerName} onChange={onChangeHandler} />
                        </div>
                        <div className="form-group">
                            <label htmlFor="">Email</label>
                            <input type="text" className="form-control" name="playerEmail" value={player.playerEmail} onChange={onChangeHandler} />
                        </div>
                        <div className="form-group">
                            <label htmlFor="">Password</label>
                            <input type="text" className="form-control" name="password" value={player.playerPassword.password} onChange={onChangeHandler} />
                        </div>
                        <input type="submit" value="Register" className="btn btn-primary btn-block" />
                    </form>
                </div>
                <h1>{player.playerName}, {player.playerEmail}, {player.playerPassword.password}</h1>
            </div>
            <div className="col-lg-"></div>
        </div>
    )      
}
export default Register;