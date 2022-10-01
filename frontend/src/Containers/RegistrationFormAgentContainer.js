import React, { useState } from "react";
import Footer from "../Components/Common/Footer";
import Navbar from "../Components/Common/Navbar";
import NavBarAgent from "../Components/Common/NavBarAgent";
import RegistrationFromAgent from "../Components/Common/RegistrationFromAgent";
import agentServices from '../Services/AgentServices/AgentServices';

export default function RegistrationFormAgentContainer() {



  const [user, setUser] = useState({});


  function addAgent(agent) {
    agentServices
      .createAgent(agent)
      .then((data) => {
        if (data.status === 204) setUser({});
        else {
          setUser(data.data.content);
          console.log("sucessfuly added a agent");
        }
      })
      .catch((error) => {
        console.log("Something wen't wrong try again", error);
      });
  }


  return (
    <div>
    <NavBarAgent></NavBarAgent>
    <RegistrationFromAgent
       addAgentHandler = {addAgent}
    ></RegistrationFromAgent>
    <Footer></Footer>
  </div>
  )
}
