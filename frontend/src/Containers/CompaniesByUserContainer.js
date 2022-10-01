import React, { useEffect, useState } from "react";
import Footer from "../Components/Common/Footer";
import Navbar from "../Components/Common/Navbar";
import CompaniesByUser from "../Components/Common/CompaniesByUser";
import agentServices from "../Services/AgentServices/AgentServices";
import NavBarAgent from "../Components/Common/NavBarAgent";

export default function CompaniesByUserContainer() {

  const [companies, setCompanies] = useState([]);

  var logedUser = JSON.parse(localStorage.getItem("User"));


  useEffect(() => {

        agentServices.getAllCompaniesOfOwner(logedUser.id)
           .then((data) => { 
              setCompanies(data.data);
          })
           .catch((error) => console.log(`error`, error));
  
      }, [])


       function updateCompany(company) {
          agentServices.updateCompany(company)
             .then((data) => {
               console.log("sucessfuly updated");
             })
             .catch((error) => {
               console.log("Something wen't wrong try again");
             });
         }



  return  (
    <div>   
    <NavBarAgent></NavBarAgent>
    <CompaniesByUser updateCompanyHandler={updateCompany} companies={companies}></CompaniesByUser>
    <Footer></Footer>
    </div>
  )

  }