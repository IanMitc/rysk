import React from "react";
import { ListGroup } from "react-bootstrap";

export const LogItem = (props) => {
  return (
    <ListGroup.Item as="li">
      {props.log.message}
    </ListGroup.Item>
  );
};
