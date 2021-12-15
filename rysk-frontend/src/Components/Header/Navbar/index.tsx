import React from "react";
import {Link} from "react-router-dom";

const Navbar = () => {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
      <a className="nav-link" href="#">
        Home <span className="sr-only">(current)</span>
      </a>
      
      <a className="navbar-brand" href="./Components/Main/Register/index.tsx">
        Register
      </a>
      <a className="navbar-brand" href="./Components/Main/Login/index.tsx">
        Login
      </a>
      
    </nav>
  );
};

export default Navbar;