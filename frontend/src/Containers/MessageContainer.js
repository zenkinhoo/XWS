import React, { useEffect, useState } from "react";
import Footer from "../Components/Common/Footer";
import Messages from "../Components/Common/Messages";
import Navbar from "../Components/Common/Navbar";
import connectionServices from "../Services/ConnectionServise/ConnectionServices";
import userServices from "../Services/UserServices/UserServices";

export default function MessageContainer() {

    const [user, setUser] = useState({});
    const [messages, setMessages] = useState([]);
    const [post,setPost] = useState({});
    const [users, setUsers] = useState([]);



    var logedUser = JSON.parse(localStorage.getItem("User"));


    useEffect(() => {

        setUser(logedUser);
       
          connectionServices.getAllMessagesByReceiverId(logedUser.id)
             .then((data) => { 
                setMessages(data.data);
            })
             .catch((error) => console.log(`error`, error));
    

             userServices.getAllUsers()
             .then((data) => { 
                setUsers(data.data);
            })
             .catch((error) => console.log(`error`, error));
    
        }, [])


        function sendMessage(message) {
            connectionServices
              .createMessage(message)
              .then((data) => {
                alert("sucessfuly created a message");
              })
              .catch((error) => {
                console.log("Something wen't wrong try again");
              });
          }
          


  return <div><Navbar></Navbar>
  <Messages user={user} messages={messages} users={users} sendMessageHandler={sendMessage}></Messages>
   <Footer></Footer></div>;
}
