import http from "../http-common";

const findAllByRider = (params) => {
  return http.get(`/rides?pageNo=${params.page}&pageSize=${params.size}&driver=${params.rider != null ? params.rider: ''}`);
};

const findByRideId = id => {
  return http.get(`/rides/${id}`);
};

export default {
  findAllByRider,
  findByRideId
};
