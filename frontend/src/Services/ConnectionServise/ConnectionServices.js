const axios = require("axios");

const connectionServices = {
  
    getAllMessagesByReceiverId: (id) => {
        return axios.get(`${process.env.REACT_APP_API_URL_CONNECTION}chat?receiverId=${id}`);
      },
    
    
      createMessage: (message) => {
        return axios.post(`${process.env.REACT_APP_API_URL_CONNECTION}create`,message);
      },


      deleteMessage: (id) => {
        return axios.delete(`${process.env.REACT_APP_API_URL_CONNECTION}delete?id=${id}`);
      },

};

export default connectionServices;
