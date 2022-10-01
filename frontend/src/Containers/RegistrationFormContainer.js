import React, { useEffect, useState } from 'react'
import Footer from '../Components/Common/Footer'
import Navbar from '../Components/Common/Navbar'
import RegistrationForm from '../Components/Common/RegistrationForm'
import userServices from '../Services/UserServices/UserServices';

export default function RegistrationFormContainer() {

  const [user, setUser] = useState({});


  function addUser(user) {
    userServices
      .createUser(user)
      .then((data) => {
        if (data.status === 204) setUser({});
        else {
          setUser(data.data.content);
          console.log("sucessfuly added a user");
        }
      })
      .catch((error) => {
        console.log("Something wen't wrong try again", error);
      });
  }




  return (
    <div>
    <Navbar></Navbar>
    <RegistrationForm
       addUserHandler = {addUser}
    ></RegistrationForm>
    <Footer></Footer>
  </div>
  )
}
