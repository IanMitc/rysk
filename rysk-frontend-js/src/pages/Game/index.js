import React from "react";
import { Col, Container, Row } from "react-bootstrap";
import { GameBoard } from "./GameBoard";
import { GamePlay } from "./GamePlay";
import { Logs } from "./Logs";

function Game() {
  return (
    <Container>
      <Row>
        <Col>
          <GameBoard />
        </Col>
        <Col>
        <GamePlay/>
        </Col>
      </Row>
      <Logs />
    </Container>
  );
}

export default Game;
