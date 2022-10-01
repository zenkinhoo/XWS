import React, { useEffect, useState } from "react";
import CompanyHomePage from "../Components/Common/CompanyHomePage";
import Footer from "../Components/Common/Footer";
import Navbar from "../Components/Common/Navbar";
import NavBarAgent from "../Components/Common/NavBarAgent";
import agentServices from "../Services/AgentServices/AgentServices";


export default function CompanyHomePageContainer() {

    const [jobOffers, setJobOffers] = useState([]);
    const [tags, setTags] = useState([]);
    const [tags1, setTags1] = useState([]);
    

    var company = JSON.parse(localStorage.getItem("Company"));
  
  
    useEffect(() => {
  
          agentServices.getAllJobOffersByCompany(company.id)
             .then((data) => { 
                setJobOffers(data.data);
            })
             .catch((error) => console.log(`error`, error));

             
           
    
        }, [])
  
  
          function createJobOffer(jobComment) {
             agentServices.createJobOffer(jobComment)
                .then((data) => {
                  console.log("sucessfuly createdJob comment");
                })
                .catch((error) => {
                  console.log("Something wen't wrong try again");
               });
           }
  
           function addJobComment(jobOffer) {
            agentServices.createJobComment(jobOffer)
               .then((data) => {
                 console.log("sucessfuly createdJob comment");
               })
               .catch((error) => {
                 console.log("Something wen't wrong try again");
              });
          }


          const removeTags = (indexToRemove) => {
            setTags([...tags.filter((_, index) => index !== indexToRemove)]);
          };
          const addTags = (event) => {
            if (event.target.value !== "") {
              setTags([...tags, event.target.value]);
              //  props.selectedTags([...tags, event.target.value]);
        
              event.target.value = "";
            }
          };
    
    
          const removeTags1 = (indexToRemove) => {
            setTags1([...tags1.filter((_, index) => index !== indexToRemove)]);
          };
          const addTags1 = (event) => {
            if (event.target.value !== "") {
              setTags1([...tags1, event.target.value]);
              //  props.selectedTags([...tags, event.target.value]);
              event.target.value = "";
            }
          };


  return (
    <div>   
    <NavBarAgent></NavBarAgent>
    <CompanyHomePage tags={tags}
        removeTags={removeTags}
        addTags={addTags} 
        tags1={tags1}
        removeTags1={removeTags1}
        addTags1={addTags1} company={company} createJobOfferHandler={createJobOffer} addJobCommentHandler={addJobComment} jobOffers={jobOffers}></CompanyHomePage>
    <Footer></Footer></div>
  )
}