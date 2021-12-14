import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import { Login } from "./Components/Login";
import { Register } from "./Components/Register";
import { GameBoard } from "./Pages/GameBoard";
import { Button } from "react-bootstrap";
import { Row } from "react-bootstrap";
function App() {
  return (
    <div className="App">
      <GameBoard />
      <Row className="mx-0">
        <Button as={ Register } variant="primary">Register</Button>
  
        <Button as={Login} variant="success">Login</Button>
      
      </Row>
      
      {/* <Register />
      <Login /> */}
    </div>
  );
}

export default App;
