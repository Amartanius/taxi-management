import React, { useState, useEffect } from "react";
import TaxiBackService from "../services/TaxiBackService";
import Moment from "moment";
import { Link } from "react-router-dom";
import Pagination from "@material-ui/lab/Pagination";

const RidesList = () => {
  const [rides, setRides] = useState([]);
  const [currentRide, setCurrentRide] = useState(null);
  const [currentIndex, setCurrentIndex] = useState(-1);
  const [searchRider, setSearchRider] = useState("");

  const [page, setPage] = useState(1);
  const [count, setCount] = useState(0);
  const [pageSize, setPageSize] = useState(3);

  const [clickedRides, setClickedRides] = useState([]);


  const pageSizes = [3, 6, 9];

  const onChangeSearchRider = e => {
    const searchRider = e.target.value;
    setSearchRider(searchRider);
  };

  const getRequestParams = (searchRider, page, pageSize) => {
    let params = {};

    if (searchRider) {
      params["rider"] = searchRider;
    }

    if (page) {
      params["page"] = page - 1;
    }

    if (pageSize) {
      params["size"] = pageSize;
    }

    return params;
  };

  const retrieveRides = () => {
    const params = getRequestParams(searchRider, page, pageSize);
    TaxiBackService.findAllByRider(params)
      .then(response => {
        setRides(response.data.content);
        setCount(response.data.totalPages);
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  };

  const setActiveRide = (ride, index) => {
    setCurrentRide(ride);
    setCurrentIndex(index);
    setClickedRides([...clickedRides, ride.id ]);
  };

  const findByRider = () => {
    const params = getRequestParams(searchRider, page, pageSize);
    TaxiBackService.findAllByRider(params)
      .then(response => {
        setRides(response.data.content);
        setCount(response.data.totalPages);
        console.log(response.data.content);
      })
      .catch(e => {
        console.log(e);
      });
  };

  useEffect(
    retrieveRides
  , [page,pageSize]);

  const refreshList = () => {
    retrieveRides();
    setCurrentRide(null);
    setCurrentIndex(-1);
  };

  const handlePageChange = (event, value) => {
    setPage(value);
  };

  const handlePageSizeChange = (event) => {
    setPageSize(event.target.value);
    setPage(1);
  };

  return (
    <div className="list row">
      <div className="col-md-8">
        <div className="input-group mb-3">
          <input
            type="text"
            className="form-control"
            placeholder="Search by Rider"
            value={searchRider}
            onChange={onChangeSearchRider}
          />
          <div className="input-group-append">
            <button
              className="btn btn-outline-secondary"
              type="button"
              onClick={findByRider}
            >
              Search
            </button>
          </div>
        </div>
      </div>
      <div className="col-md-6">
        <h4>Rides List</h4>
        <div className="mt-3">
          {"Rides per Page: "}
          <select onChange={handlePageSizeChange} value={pageSize}>
            {pageSizes.map((size) => (
              <option key={size} value={size}>
                {size}
              </option>
            ))}
          </select>

          <Pagination
            className="my-3"
            count={count}
            page={page}
            siblingCount={1}
            boundaryCount={1}
            variant="outlined"
            shape="rounded"
            onChange={handlePageChange}
          />
        </div>

        <ul className="list-group">
          {rides &&
            rides.map((ride, index) => (
              <li
                className={
                  "list-group-item " + (index === currentIndex ? "active" : "") + (ride.distance > 2 ? " background-red" : " no-background")
                }
                onClick={() => setActiveRide(ride, index)}
                key={index}
              >
                {ride.driver} | {Moment(ride.startTime).format('MMMM Do YYYY, h:mm:ss a')} | {ride.status}  {clickedRides.includes(ride.id) ? " | Clicked": ""}
              </li>
            ))}
        </ul>
       
      </div>
      <div className="col-md-6">
        {currentRide ? (
          <div>
            <h4>Ride</h4>
            <div>
              <label>
                <strong>Rider:</strong>
              </label>{" "}
              {currentRide.driver}
            </div>
            <div>
              <label>
                <strong>Start Time:</strong>
              </label>{" "}
              {currentRide.startTime}
            </div>
            <div>
              <label>
                <strong>Status:</strong>
              </label>{" "}
              {currentRide.status}
            </div>
            <div>
              <label>
                <strong>Distance:</strong>
              </label>{" "}
              {currentRide.distance}
            </div>

            <Link
              to={"/rides/" + currentRide.id}
            >  <button type="button" 
                  className="btn btn-outline-primary">
            Calculate Charge
            </button>
            </Link>
          </div>
        ) : (
          <div>
            <br />
            <p>Please click on a Ride...</p>
          </div>
        )}
      </div>
    </div>
  );
};

export default RidesList;
