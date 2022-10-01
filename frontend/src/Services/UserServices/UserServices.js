const axios = require("axios");

const userServices = {
  getUserByUsername: (username) => {
    return axios.get(`${process.env.REACT_APP_API_URL_USER}user?username=${username}`);
  },

  getAllUsers: () => {
    return axios.get(`${process.env.REACT_APP_API_URL_USER}users`);
  },


  getUserById: (id) => {
    return axios.get(`${process.env.REACT_APP_API_URL_USER}userById?userId=${id}`);
  },

  getUserByEmail: (email) => {
    return axios.get(`${process.env.REACT_APP_API_URL_USER}userByEmail?email=${email}`);
  },

  loginUser: (username,password) =>{
    return axios.post(`${process.env.REACT_APP_API_URL_USER}login`, {
      username: username,
      password: password,
    });
  },

  getPostsByUserId: (userId) => {
    return axios.get(`${process.env.REACT_APP_API_URL_POST}post?userId=${userId}`);
  },


  likePost: (userId,postId) => {
    return axios.put(
      `${process.env.REACT_APP_API_URL_POST}like`,
      {
        userId: userId,
        postId: postId,
      }
    );
  },

  createUser: (user) => {
    return axios.post(`${process.env.REACT_APP_API_URL_USER}create`,user);
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


  getAllUsers: () => {
    return axios.get(`${process.env.REACT_APP_API_URL_USER}users`);
  },

  searchForUsername: (username) => {
    return axios.get(`${process.env.REACT_APP_API_URL_USER}search/${username}`);
  },

  followUser: (followerUsername, toFollowUsername) => {
    return axios.put(`${process.env.REACT_APP_API_URL_USER}follow`,
    {
      followerId: followerUsername,
      toFollowId: toFollowUsername
    }
    );
  },


  blockUser: (blockerUsername, toBlockUsername) => {
    return axios.put(`${process.env.REACT_APP_API_URL_USER}block`,
    {
      blockerId: blockerUsername,
      blockedId: toBlockUsername
    }
    );
  },

  approveRequest: (userId,followerId) => {
    return axios.put(`${process.env.REACT_APP_API_URL_USER}approve`,
    {
      userId: userId,
      followerUserId: followerId,
    });
  },


  rejectRequest: (userId,followerId) => {
    return axios.put(`${process.env.REACT_APP_API_URL_USER}reject`,
    {
      userId: userId,
      followerUserId: followerId,
    });
  },


  updateUser: (user) => {
    return axios.put(`${process.env.REACT_APP_API_URL_USER}user/${user.id}`, user)
  },

  areBlocked: (firstUsername,secondUsername) => {
    return axios.get(`${process.env.REACT_APP_API_URL_USER}areBlocked?firstUsername=${firstUsername}&secondUsername=${secondUsername}`
    )
  },

  allUnreadNotifications: (userId) => {
    return axios.get(`${process.env.REACT_APP_API_URL_USER}unreadNotificationsByUser?userId=${userId}`
    )
  },

  markAsReadNotifi: (userId,notificationId) => {
    return axios.put(`${process.env.REACT_APP_API_URL_USER}markAsRead`,
    {
      userId:userId,
      notificationId:notificationId
    }
    )
  },

  deleteUserByUsername: (username) => {
    return axios.delete(`${process.env.REACT_APP_API_URL_USER}${username}`
    )
  },

  areFollowing: (userId,followedUserId) => {
    return axios.get(`${process.env.REACT_APP_API_URL_USER}following?userId=${userId}&followedUserId=${followedUserId}`
    )
  },


};

export default userServices;
