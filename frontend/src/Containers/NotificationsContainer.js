import React, { useEffect, useState } from "react";
import Footer from "../Components/Common/Footer";
import Navbar from "../Components/Common/Navbar";
import Notifications from "../Components/Common/Notifications";
import userServices from '../Services/UserServices/UserServices';

export default function NotificationsContainer() {


    const [notifications, setNotifications] = useState([]);

    var logedUser = JSON.parse(localStorage.getItem("User"));


    useEffect(() => {

        userServices.allUnreadNotifications(logedUser.id)
        .then((data) => {
            setNotifications(data.data);
        })
        .catch((error) => console.log(`error`, error));

      }, [])
      

      function markAsRead(userId,notificationId) {
        userServices.markAsReadNotifi(userId,notificationId)
          .then((data) => {
            alert("sucessfuly marked a notify as read");
            window.location.reload();
          })
          .catch((error) => {
            console.log("Something wen't wrong try again");
          });
      }

  return (
    <div> 
    <Navbar></Navbar>
    <Notifications notifications={notifications} markAsReadHandler={markAsRead} logedUser={logedUser}></Notifications>
    <Footer></Footer>
  </div>
  )
}
