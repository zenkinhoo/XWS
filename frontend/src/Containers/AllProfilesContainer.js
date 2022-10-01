import React, { useEffect, useState } from "react";
import AllProfiles from "../Components/Common/AllProfiles";
import Footer from "../Components/Common/Footer";
import Navbar from "../Components/Common/Navbar";
import userServices from "../Services/UserServices/UserServices";

export default function AllProfilesContainer() {
    
    const [users, setUsers] = useState([]);

    const [logedUserr, setlogedUser] = useState({});

    var logedUser = JSON.parse(localStorage.getItem("User"));

    useEffect(() => {

    setlogedUser(logedUser);
        userServices.getAllUsers()
          .then((data) => {
            setUsers(data.data);
          })
          .catch((error) => console.log(`error`, error));
    
      }, [])


      function searchByUsername(username) {
        userServices
          .searchForUsername(username)
          .then((data) => {
            setUsers(data.data);
            console.log("sucessfuly searched");
          })
          .catch((error) => {
            console.log("Something wen't wrong try again");
          });
      }



      function followUser(followerUsername,toFollowUsername) {
        userServices.followUser(followerUsername,toFollowUsername)
          .then((data) => {
            alert("sucessfuly");
            localStorage.setItem("User", JSON.stringify(logedUser));
          })
          .catch((error) => {
           alert("Something wen't wrong try again");
          });
      }
      

  return (
    <div> <div>
    <Navbar></Navbar>
   <AllProfiles logedUserr={logedUserr} followUserHandler={followUser} users={users} searchByUsernameHandler= {searchByUsername}></AllProfiles>
    <Footer></Footer>
  </div></div>
  )
}