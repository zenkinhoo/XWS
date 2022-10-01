import React, { useState } from "react";
import CreateJobOffer from "../Components/Common/CreateJobOffer";
import Footer from "../Components/Common/Footer";
import Navbar from "../Components/Common/Navbar";
import jobOfferService from "../Services/JobOfferServices/JobOfferServices";

export default function CreateJobOfferContainer() {
    
      
  const [jobOffer, setJobOffer] = useState({});
  const [tags, setTags] = useState([]);
  const [tags1, setTags1] = useState([]);

   

    function createJobOffer(jobOffer) {
        jobOfferService.createJobOffer(jobOffer)
          .then((data) => {
            if (data.status === 204) setJobOffer({});
            else {
              setJobOffer(data.data.content);
              alert("sucessfuly added a jobOffer");
            }
          })
          .catch((error) => {
            console.log("Something wen't wrong try again", error);
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


  return <div>
    <Navbar></Navbar>
    <CreateJobOffer  tags={tags}
        removeTags={removeTags}
        addTags={addTags} 
        tags1={tags1}
        removeTags1={removeTags1}
        addTags1={addTags1}
        createJobOfferHandler={createJobOffer}></CreateJobOffer>
    <Footer></Footer>
  </div>
}
