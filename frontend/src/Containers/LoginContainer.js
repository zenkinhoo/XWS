import React, { useState } from 'react'
import { Link, useHistory, useLocation } from "react-router-dom";
import Footer from '../Components/Common/Footer'
import Login from '../Components/Common/Login'
import Navbar from '../Components/Common/Navbar'
import NavbarUnregistered from '../Components/Common/NavBarUnregistered';
import agentServices from '../Services/AgentServices/AgentServices';
import userServices from '../Services/UserServices/UserServices';

export default function LoginContainer() {
  const history = useHistory();

  const [logedUser, setLogedUser] = useState({});


  function loginUser(username, password) {
    userServices
      .loginUser(username, password)
      .then((data) => {
        if (data.status === 204) setLogedUser();
        else {
          userServices
            .loginUser(username, password)
            .then((data) => {
              setLogedUser(data.data);
              localStorage.setItem("User", JSON.stringify(data.data));
              var user = JSON.parse(
                localStorage.getItem("User")
              );
                if (Object.keys(user).length !== 0) {
                 history.push("/newFeed"); 
              
                 alert("sucessfuly loged on");
                 window.location.reload();
                }
               else if (Object.keys(user).length == 0) {
              } 
            })
            .catch((error) => console.log(`error`, error));
        }
      })
      .catch((error) => {
        console.log(error);
      });
  }

  function loginAgent(username, password) {
    agentServices
      .login(username, password)
      .then((data) => {
        if (data.status === 204) setLogedUser();
        else {
          agentServices
            .login(username, password)
            .then((data) => {
              setLogedUser(data.data);
              localStorage.setItem("User", JSON.stringify(data.data));
              var user = JSON.parse(
                localStorage.getItem("User")
              );
                if (Object.keys(user).length !== 0) {
                 history.push("/allCompanies"); 
                 alert("sucessfuly loged on agent");
                 window.location.reload();
                }
               else if (Object.keys(user).length == 0) {
              } 
            })
            .catch((error) => console.log(`error`, error));
        }
      })
      .catch((error) => {
        console.log(error);
      });
  }



  return (
    <div> <div>
      <NavbarUnregistered></NavbarUnregistered>
    <Login
  loginUserHandler={loginUser}
  logedUser={logedUser}
  loginAgentHandler = {loginAgent}
    ></Login>
    <Footer></Footer>
  </div></div>
  )
}
