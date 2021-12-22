import React from "react";
import { Route, IndexRoute } from "react-router-dom";

import App from './Components/App';
import Register from './Components/Main/Register';
import Login from './Components/Main/Login';


// for all routes
export default (
    <Route path="/" component={App}>
        <IndexRoute component={Register} />
        <Route path="/Components/Main/Register" component={Register} />
        <Route path="/Components/Main/Login" component={Login} />

    </Route>
);