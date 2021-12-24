import React from "react";
import { ListGroup } from "react-bootstrap";
import { useSelector } from "react-redux";
import { LogItem } from "./LogItem";

const Logs = () => {
  const logs = useSelector((state) => state.game.value.logs);
  console.log(logs);
  return (
    <ListGroup as="ol">
      {logs.map((log, index) => (
        <LogItem log={log} key={index} />
      ))}
    </ListGroup>
  );
};
export default Logs;
