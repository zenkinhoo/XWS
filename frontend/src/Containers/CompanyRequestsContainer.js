import React, { useEffect, useState } from "react";
import CompanyRequests from "../Components/Common/CompanyRequests";
import Footer from "../Components/Common/Footer";
import Navbar from "../Components/Common/Navbar";
import NavBarAgent from "../Components/Common/NavBarAgent";
import agentServices from "../Services/AgentServices/AgentServices";

export default function CompanyRequestsContainer() {

    const [companies, setCompanies] = useState([]);

    var logedUser = JSON.parse(localStorage.getItem("User"));


    useEffect(() => {

          agentServices.getAllCompanies()
             .then((data) => { 
                setCompanies(data.data);
            })
             .catch((error) => console.log(`error`, error));
    
        }, [])


         function approve(adminId,companyId) {
            agentServices.approveCompany(adminId,companyId)
               .then((data) => {
                setCompanies(data.data);
                 console.log("sucessfuly searched");
               })
               .catch((error) => {
                 console.log("Something wen't wrong try again");
               });
           }

  return  (
    <div>   
    <NavBarAgent></NavBarAgent>
    <CompanyRequests approveHandler={approve} companies={companies} logedUser={logedUser}></CompanyRequests>
    <Footer></Footer></div>
  )
}