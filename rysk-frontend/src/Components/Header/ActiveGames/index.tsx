import React from "react";

const ActiveGames = () => {
  return (
    <div className="jumbotron">
      <h3>Active Games</h3>
      <p className="lead">
        other players
      </p>
      <p className="lead">
          No of Countries
      </p>
      <p className="lead">
          No of Armies
      </p>
      <a href="#" role="button">
        Play
      </a> 
      {/* <input
              type="submit"
              value="Register"
              className="btn btn-primary btn-block"
            /> */}
      <a href="#" role="button">  Forfiet</a>
        
    </div>
  );
};

export default ActiveGames;