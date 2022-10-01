const axios = require("axios");

const postService = {
 

  likePost: (userId,postId) => {
    return axios.put(
      `${process.env.REACT_APP_API_URL_POST}like`,
      {
        userId: userId,
        postId: postId,
      }
    );
  },

 

  unlikePost: (userId,postId) => {
    return axios.put(
      `${process.env.REACT_APP_API_URL_POST}dislike`,
      {
        userId: userId,
        postId: postId,
      }
    );
  },

  
  createComment: (comment,postId) => {
    return axios.put(
      `${process.env.REACT_APP_API_URL_POST}${postId}/comment`,
     comment
    );
  },


  cratePost: (post) => {
    return axios.post(
      `${process.env.REACT_APP_API_URL_POST}create`,
     post
    );
  }

};

export default postService;
