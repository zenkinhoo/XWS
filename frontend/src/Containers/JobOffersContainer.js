import React, { useEffect, useState } from "react";
import Footer from "../Components/Common/Footer";
import JobOffers from "../Components/Common/JobOffers";
import Navbar from "../Components/Common/Navbar";
import agentServices from "../Services/AgentServices/AgentServices";
import jobOfferService from "../Services/JobOfferServices/JobOfferServices";

export default function JobOffersContainer() {

    const [user, setUser] = useState({});
    const [jobOffers, setJobOffers] = useState([]);
    const [jobOffersAgent, setJobOffersAgent] = useState([]);


    var logedUser = JSON.parse(localStorage.getItem("User"));


    useEffect(() => {

          jobOfferService.getAllJobOffers()
             .then((data) => { 
                setJobOffers(data.data);
            })
             .catch((error) => console.log(`error`, error));

            agentServices.getAllJobOffers().then((data) => { 
              setJobOffersAgent(data.data);
          })
           .catch((error) => console.log(`error`, error));

    
        }, [])


        function searchByPosition(search) {
            jobOfferService.findJobOfferBySearch(search)
              .then((data) => {
                setJobOffers(data.data);
                console.log("sucessfuly searched");
              })
              .catch((error) => {
                console.log("Something wen't wrong try again");
              setJobOffers([]);

              });
          }

        function agentSearchByPosition(search) {
          agentServices.searchByPosition(search)
            .then((data) => {
              setJobOffersAgent(data.data);
              console.log("sucessfuly searched");
            })
            .catch((error) => {
              console.log("Something wen't wrong try again");
              setJobOffersAgent([]);

            });
        }
    

return <div>
    <Navbar></Navbar>
    <JobOffers jobOffersAgent={jobOffersAgent} jobOffers={jobOffers} searchByPositionHandler={searchByPosition} agentSearchByPositionHandler={agentSearchByPosition}></JobOffers>
    <Footer></Footer>
</div>
}
