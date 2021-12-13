import React, { useEffect, useState } from 'react';
import './register.css'
import axios from "axios";

const Register = () => {

    const [user, setUser] = useState({
        playerName: '',
        playerEmail: '',

        playerPassword: {
            "password": "password"
        }
    })

    const onChangeHandler = (event:React.ChangeEvent<HTMLInputElement>) => {
        //console.log(event.target.name)
        setUser({
            ...user,
            [event.target.name]: event.target.value
        })
    }

    const onsave = () => {
        axios.post("http://localhost3000/register", {
            user
        }).then((response) => {
            console.log(response);
        });

    

        return (

            <div className="row">
                <div className="col-lg-3"></div>
                <div className="col-lg-6">
                    <div className="wrapper">
                        <form onSubmit={ onsave }>

                            <h1>Register</h1>
                            <div className="form-group">
                                <label htmlFor="">Username</label>
                                <input type="text" className="form-control" name="username" value={user.playerName} onChange={onChangeHandler} />
                            </div>
                            <div className="form-group">
                                <label htmlFor="">Email</label>
                                <input type="text" className="form-control" name="email" value={user.playerEmail} onChange={onChangeHandler} />
                            </div>
                            <div className="form-group">
                                <label htmlFor="">Password</label>
                                <input type="text" className="form-control" name="password" value={user.playerPassword.password} onChange={onChangeHandler} />
                            </div>
                            <input type="submit" value="Register" className="btn btn-primary btn-block" />
                        </form>
                    </div>
                    <h1>{user.playerName}, {user.playerEmail}, {user.playerPassword.password}</h1>
                </div>
                <div className="col-lg-"></div>
            </div>

        )
    }
}
export default Register;