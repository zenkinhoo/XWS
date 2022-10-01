import React, { useEffect, useState } from 'react'
import Footer from '../Components/Common/Footer'
import FriendRequests from '../Components/Common/FriendRequests'
import Navbar from '../Components/Common/Navbar'
import userServices from '../Services/UserServices/UserServices'



export default function FollowRequestsContainer() {

  const [user, setUser] = useState({});
  const [friendRequests, setFriendRequests] = useState([]);
  
  var logedUser = JSON.parse(localStorage.getItem("User"));
 
  useEffect(() => {

    setUser(logedUser);
   
   
    for (var i = 0; i < logedUser.followRequests.length; i++) {

         userServices.getUserById(logedUser.followRequests[i])
         .then((data) => {
           
          setFriendRequests((allEvents) => [
                ...allEvents,
               data.data
              ]);

          
        })
         .catch((error) => console.log(`error`, error));

      }
       
    }, [])
    

    function approve(userId,followerId) {
      userServices
        .approveRequest(userId,followerId)
        .then((data) => {
          console.log("sucessfuly accepted request");
          
          userServices.getUserById(user.id).then((data) => {
          
            localStorage.setItem("User", JSON.stringify(data.data));
            window.location.reload();
          })
         
        })
        .catch((error) => {
          console.log("Something wen't wrong try again");
        });
    }


    function reject(userId,followerId) {
      userServices
        .rejectRequest(userId,followerId)
        .then((data) => {
          console.log("sucessfuly rejected request");
          
          userServices.getUserById(user.id).then((data) => {
          
            localStorage.setItem("User", JSON.stringify(data.data));
            window.location.reload();
          })
         
        })
        .catch((error) => {
          console.log("Something wen't wrong try again");
        });
    }
    

  return (
    <div>   
    <Navbar></Navbar>
   <FriendRequests logedUser={logedUser} friendRequests={friendRequests} approveHandler={approve} rejectHandler={reject}></FriendRequests>
    <Footer></Footer></div>
  )
}
