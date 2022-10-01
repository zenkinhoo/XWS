import React, { useEffect, useState } from "react";
import FollowingUsers from "../Components/Common/FollowingUsers";
import Footer from "../Components/Common/Footer";
import Navbar from "../Components/Common/Navbar";
import connectionServices from "../Services/ConnectionServise/ConnectionServices";
import userServices from "../Services/UserServices/UserServices";

export default function FollowingUsersContainer() {

    const [user, setUser] = useState({});
    const [followingUsers, setFollowingUsers] = useState([]);
    
    var logedUser = JSON.parse(localStorage.getItem("User"));
   
    useEffect(() => {
      setUser(logedUser);

      for (var i = 0; i < logedUser.following.length; i++) {
  
           userServices.getUserById(logedUser.following[i])
           .then((data) => {
             
            setFollowingUsers((allEvents) => [
                  ...allEvents,
                 data.data
                ]);
          })
           .catch((error) => console.log(`error`, error));
        }
      }, [])

      function sendMessage(message) {
        connectionServices
          .createMessage(message)
          .then((data) => {
            console.log("sucessfuly created a message");
          })
          .catch((error) => {
            console.log("Something wen't wrong try again");
          });
      }
      


  return (
    <div>   
    <Navbar></Navbar>
    <FollowingUsers logedUser={user}  sendMessageHandler={sendMessage} followingUsers={followingUsers}></FollowingUsers>
    <Footer></Footer>
    </div>
  )
}