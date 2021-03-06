import React from "react";
import { ListGroup } from "react-bootstrap";
import { useSelector } from "react-redux";
import { LogItem } from "./LogItem";

export const Logs = () => {
  const logs = useSelector((state) => state.game.value.logs);
  return (
    <ListGroup as="ol">
      {logs.map((log, index) => (
        <LogItem log={log} key={index} />
      ))}
    </ListGroup>
  );
};
