import React from "react";
import { Form, ListGroup } from "react-bootstrap";

export const CheckableCard = (props) => {
  console.log(props)
  return (
    <ListGroup.Item>
      <Form.Check
        type="checkbox"
        checked={props.checked}
        onChange={props.onChange}
        name={props.name}
        label={props.card.type}
      />
    </ListGroup.Item>
  );
};
