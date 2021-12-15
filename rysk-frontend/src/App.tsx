import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import { Login } from "./Components/Main/Login";
import { Register } from "./Components/Main/Register";
import { GameBoard } from "./Pages/GameBoard";
import { Button } from "react-bootstrap";
import { Row } from "react-bootstrap";
import Navbar from "./Components/Header/Navbar";
import Header from "./Components/Header";
import Footer from "./Components/Footer/footer";
import Sidebar from "./Components/Main/Sidebar/sidebar";
import SubFooter from "./Pages/GameBoard/SubFooter/footer2"
import ActiveGames from "./Components/Header/ActiveGames";
import InvitedGames from "./Components/Header/InvitedGames";
function App() {
  return (
    <div className="container-fluid">
      <Navbar />
      <ActiveGames />
      <InvitedGames />

      <div className="row">
        <div className="col-lg-3">
          <Sidebar />
        </div>
        <div className="col-lg-9">
        <GameBoard />
        <SubFooter />
      <Row className="mx-0">
        <Button as={ Register } variant="primary">Register</Button>
        <Button as={Login} variant="success">Login</Button>
      </Row>
      </div>
      
      {/* <Register />
      <Login /> */}
      </div>
      <Footer />
    </div>
  );
}

export default App;
