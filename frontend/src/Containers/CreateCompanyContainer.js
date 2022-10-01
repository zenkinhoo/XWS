import React, { useState } from "react";
import CreateCompany from "../Components/Common/CreateCompany";
import Footer from "../Components/Common/Footer";
import Navbar from "../Components/Common/Navbar";
import NavBarAgent from "../Components/Common/NavBarAgent";
import agentServices from "../Services/AgentServices/AgentServices";

export default function CreateCompanyContainer() {


  const [company, setCompany] = useState({});


  function addCompany(userId,company) {
    agentServices
      .createCompany(userId,company)
      .then((data) => {
        if (data.status === 204) setCompany({});
        else {
          setCompany(data.data.content);
          console.log("sucessfuly added a company");
        }
      })
      .catch((error) => {
        console.log("Something wen't wrong try again", error);
      });
  }

    
  return <div>
  <NavBarAgent></NavBarAgent>
  <CreateCompany addCompanyHandler={addCompany}></CreateCompany>
  <Footer></Footer>
</div>
}
