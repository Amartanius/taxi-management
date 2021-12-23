import React from "react";
import { Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import Ride from "./components/Ride";
import RidesList from "./components/RidesList";

function App() {
  return (
    <div>
      <nav className="navbar navbar-expand navbar-dark bg-dark">
        <a href="/rides" className="navbar-brand">
          Taxi Management
        </a>
        <div className="navbar-nav mr-auto">
          <li className="nav-item">
            <Link to={"/rides"} className="nav-link">
              Rides
            </Link>
          </li>
      
        </div>
      </nav>

      <div className="container mt-3">
        <Switch>
          <Route exact path={["/", "/rides"]} component={RidesList} />
          <Route path="/rides/:id" component={Ride} />
        </Switch>
      </div>
    </div>
  );
}

export default App;
