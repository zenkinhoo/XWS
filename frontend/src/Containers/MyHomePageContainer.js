import React, { useEffect, useState } from "react";
import Footer from "../Components/Common/Footer";
import MyHomePage from "../Components/Common/MyHomePage";
import Navbar from "../Components/Common/Navbar";
import postService from "../Services/PostServices/PostServices";
import userServices from "../Services/UserServices/UserServices";

export default function MyHomePageContainer() {

  const [user, setUser] = useState({});
  const [posts, setPosts] = useState([]);
  
  const [blocked, setBlocked] = useState();
  const [following, setFollowing] = useState();
  
  
  
  var logedUser = JSON.parse(localStorage.getItem("User"));
  
  const [logedUserr, setLogedUserr] = useState(logedUser);
  
  useEffect(() => {

  
      userServices.getPostsByUserId(logedUser.id)
      .then((data) => {
        setPosts(data.data);
      })
      .catch((error) => console.log(`error`, error));
  
  

     
  }, [])
  
  
  
  
  function likePost(userId,postId) {
    userServices
      .likePost(userId,postId)
      .then((data) => {
        console.log("sucessfuly updated post");
      })
      .catch((error) => {
        console.log("Something wen't wrong try again");
      });
  }
  
  
  
  function unlikePost(userId,postId) {
    userServices
      .unlikePost(userId,postId)
      .then((data) => {
        console.log("sucessfuly updated post");
      })
      .catch((error) => {
        console.log("Something wen't wrong try again");
      });
  }
  
  
  function addComment(comment,postId) {
    postService
      .createComment(comment,postId)
      .then((data) => {
        console.log("sucessfuly updated post");
        window.location.reload();
  
      })
      .catch((error) => {
        console.log("Something wen't wrong try again");
      });
  }
  
  
  
  function followUser(followerUsername,toFollowUsername) {
    userServices.followUser(followerUsername,toFollowUsername)
      .then((data) => {
        alert("sucessfuly followed user");
        
      })
      .catch((error) => {
       alert("Something wen't wrong try again");
      });
  }
  
  function block(blockerUsername,toBlockUsername) {
    userServices.blockUser(blockerUsername,toBlockUsername)
      .then((data) => {
        alert("sucessfuly blocked user");
      })
      .catch((error) => {
       alert("Something wen't wrong try again");
      });
  }
  
  
  function deleteUserByUsername(username) {
    userServices.deleteUserByUsername(username)
      .then((data) => {
        alert("sucessfuly deleted user");
      })
      .catch((error) => {
       alert("Something wen't wrong try again");
      });
  }
  
  
  
    return (
      <div>
      <Navbar></Navbar>
      <MyHomePage
        following={following}
        user={user}
        logedUserr={logedUserr}
        posts= {posts}
        blockHandler = {block}
        likePostHandler = {likePost}
        unlikePostHandler = {unlikePost}
        addCommentHandler = {addComment}
        followUserHandler= {followUser}
        blocked={blocked}
        deleteUserByUsernameHandler={deleteUserByUsername}
      ></MyHomePage>
      <Footer></Footer>
    </div>
    )
  }
  