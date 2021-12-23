import React, { useState, useEffect } from "react";
import Moment from "moment";
import TaxiBackService from "../services/TaxiBackService";

const Ride = props => {
  const initialRideState = {
    id: null,
    driver: "",
    startTime: null,
    endTime: "",
    status: "",
    duration: null,
    distance: "",
    charge: "",
    cityId: ""
  };
  const [currentRide, setCurrentRide] = useState(initialRideState);
  const [endTime, setEndtime] = useState(null);

  const getRide = id => {
    TaxiBackService.findByRideId(id)
      .then(response => {
        setCurrentRide(response.data);
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  };

  useEffect(() => {
    getRide(props.match.params.id);
  }, [props.match.params.id]);

  useEffect(() => {
    if(currentRide.id != null) {
      Moment.locale('en');
      var timestampEnd = Moment(currentRide.duration).valueOf() + Moment(currentRide.startTime).valueOf();
      setEndtime(Moment(timestampEnd).utcOffset(180).format('MMMM Do YYYY, h:mm:ss a'));
      alert( Moment(currentRide.duration).format('HH:mm:ss') + ' - ' + Moment(timestampEnd).utcOffset(180).format('MMMM Do YYYY, h:mm:ss a'));
    }
  }, [currentRide]);

  return (
    <div>
      {currentRide.id ? (
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
                <strong>City ID:</strong>
              </label>{" "}
              {currentRide.cityId}
            </div>
            <div>
              <label>
                <strong>Start Time:</strong>
              </label>{" "}
              {Moment(currentRide.startTime).format('MMMM Do YYYY, h:mm:ss a')}
            </div>
            <div>
              <label>
                <strong>Arrival Time:</strong>
              </label>{" "}
              {endTime}
            </div>
            <div>
              <label>
                <strong>Status:</strong>
              </label>{" "}
              {currentRide.status}
            </div>
            <div>
              <label>
                <strong>Duration:</strong>
              </label>{" "}
              {Moment(currentRide.duration).format('HH:mm:ss')}
            </div>
            <div>
              <label>
                <strong>Distance:</strong>
              </label>{" "}
              {currentRide.distance}
            </div>
            <div>
              <label>
                <strong>Charge:</strong>
              </label>{" "}
              {currentRide.status === 'ARRIVED' ? currentRide.charge + ' EUR': 'Driver is currently on road. No Final Charge Yet.'}
            </div>
          </div>
        ) : (
          <div>
            <br />
            <p>Please Verify the duration and arrival time.</p>
          </div>
        )}
    </div>
  );
};

export default Ride;
