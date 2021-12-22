import React from "react";

import { NavLink } from "react-router-dom";

const Navigation = () => {
    return (
        <div>
            <NavLink to="/">Home</NavLink>
            <NavLink to="/Components/About/about">About</NavLink>
            <NavLink to="/Components/Contact/contact">Contact</NavLink>
            
        </div>
    );
}
export default Navigation;