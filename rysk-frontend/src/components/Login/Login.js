import React, { useState } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import "./login.css";
import axios from "axios";

export default function Login() {
  const [user, setUser] = useState({
    playerName: '',
    playerEmail: '',
    playerPassword: {
      "password": "password"
    }
  })

  

  function handleSubmit(event) {
    event.preventDefault();
  }


  const login = () => {
    axios.post("http://localhost:8080/login", {
        user 
    }).then((response) => {
        console.log(response);
    });

  return (
    <div className="Login">
      <Form onSubmit={handleSubmit}>
        <h1>Login</h1>
        <Form.Group size="lg" controlId="email">
          <Form.Label>Email</Form.Label>
          <Form.Control
            autoFocus
            type="email"
            value={user}
            onChange={(e) => setUser(e.target.value)}
          />
        </Form.Group>
        <Form.Group size="lg" controlId="password">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            value={user}
            onChange={(e) => setUser(e.target.value)}
          />
        </Form.Group>
        <Button block size="lg" type="submit">
          Login
        </Button>
      </Form>
    </div>
  );
}
}