import App from "../../App";

const axios = require("axios");

const jobOfferService = {
 
  createJobOffer: (jobOffer) => {
    return axios.post(
      `${process.env.REACT_APP_API_URL_JOBOFFER}create`,
      jobOffer
    );
  },

  getAllJobOffers: () => {
    return axios.get(`${process.env.REACT_APP_API_URL_JOBOFFER}jobOffers`);
  },

  findJobOfferBySearch: (search) => {
    return axios.get(`${process.env.REACT_APP_API_URL_JOBOFFER}search/${search}`);
  },


  deleteJobOffer: (jobOfferId) => {
    return axios.delete(`${process.env.REACT_APP_API_URL_JOBOFFER}delete/${jobOfferId}`);
  },

};

export default jobOfferService;
