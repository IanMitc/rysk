import React, { useState } from "react";
import { Card } from "react-bootstrap";

export const CountryCardItem = (props) => {
  return (
    <Card>
        <Card.Title>{props.country.printableName}</Card.Title>
      <Card.Body>
          {props.country.controlledBy.playerName} <br/>
          Armies - {props.country.armies}
      </Card.Body>
    </Card>
  );
};
