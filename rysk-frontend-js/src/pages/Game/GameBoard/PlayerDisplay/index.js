import React from "react";
import { Card } from "react-bootstrap";

export const PlayerDisplay= (props) => {
return (
    <Card>
      <Card.Body>{props.player.playerName}</Card.Body>
    </Card>
  );
}