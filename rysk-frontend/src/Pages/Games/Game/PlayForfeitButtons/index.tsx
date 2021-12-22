import React from "react";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

export const PlayForfeitButtons = () => {
  <div className="row">
    <Router>
      <Link to="/play?id={ props.id }" className="btn btn-primary">
        Play
      </Link>
      <Link to="/forfeit?id={ props.id }" className="btn btn-danger">
        Forfeit
      </Link>
    </Router>
  </div>;
};
