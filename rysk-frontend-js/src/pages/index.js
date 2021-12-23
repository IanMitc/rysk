import React from 'react';
import ReactDOM from 'react-dom';
import { Browser as Router, Switch, Route, Link } from "react-router-dom";
import Home from './Components/Pages/Home';
import Login from './Components/Pages/Login';
import Register from './Components/Pages/Register';

const WebPages = () => {
  return (
    <Router>
      <Route exact path="/" component={Home} />
      <Route path = "/Components/Pages/Login" component = {Login} />
      <Route path = "/Components/Pages/Register" component = {Register} />
    </Router>
  );
};

export default WebPages;