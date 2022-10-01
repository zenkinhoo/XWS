import React, { Profiler, useRef } from 'react'
import { Link } from 'react-router-dom'
import {
  Card,
  ListGroup,
  ListGroupItem,
  CardGroup,
  Button,
} from "react-bootstrap";

export default function CompanyHomePage({tags1,addTags1,removeTags1,tags,addTags,removeTags,company,jobOffers,createJobOfferHandler,addJobCommentHandler}) {

    console.log("jobOffers", jobOffers);
    var logedUser = JSON.parse(localStorage.getItem("User"));
                                      
  let conss = useRef([React.createRef(), React.createRef()]);
  let pross = useRef([React.createRef(), React.createRef()]);
  let ratings = useRef([React.createRef(), React.createRef()]);
  let salaries = useRef([React.createRef(), React.createRef()]);
  let interviews = useRef([React.createRef(), React.createRef()]);


  
const position = useRef();
const description = useRef();
const location = useRef();

var requirements = [];
var len = tags.length;
for (var i = 0; i < len; i++) {
    requirements.push({
        id: Math.floor(Math.random() * 100) + 1,
        title: tags[i],
    });
}

var dailyActivities = [];
var len1 = tags1.length;
for (var i = 0; i < len1; i++) {
    dailyActivities.push({
        id: Math.floor(Math.random() * 100) + 1,
        title: tags1[i],
    });
}


    
  return (
    <div><div id="card">

      <h1>{company.name}{" "} {company.username} <br />
      </h1>

      <div className="image-crop">
        <img
          id="avatar"
          src={company.profilePicture}
        />
      </div>
      <div id="bio">
        <p>
          {company.description}
        </p>
      </div>
      
      <div className="header">
    {" "}
    <h1 style={{ textAlign: "center" }}> News feed </h1>
  </div>
  <div className="containerNewPost">
    {logedUser.role === "Owner" &&
  <div className="wrapper">
    <section className="post">
      <header>Job Offers</header>
      <form action="#">
        <div className="content">
          <img src="https://webmuch.com/wp-content/uploads/2013/04/LinkedIn-Logo-022.png" alt="logo" />
          <div className="details">
            <p>Add new job offer</p>
            <div className="privacy">
              <i className="fas fa-user-friends" />
              <span>Friends</span>
              <i className="fas fa-caret-down" />
            </div>
          </div>
        </div>
        <textarea
          placeholder="Position"
          spellCheck="false"
          required=""
          defaultValue={""}
          ref= {position}
        />
          <textarea
          placeholder="Description"
          spellCheck="false"
          required=""
          defaultValue={""}
          ref= {description}

        />
           <textarea
          placeholder="Location"
          spellCheck="false"
          required=""
          defaultValue={""}
          ref= {location}
        />
            <div className="mb-3">
      <label>Requirements</label>
    <div className="tags-input">
                    <ul id="tags">
                      {tags?.map((tag, index) => (
                        <li key={index} className="tag">
                          <span className="tag-title">{tag}</span>
                          <span
                            className="tag-close-icon"
                            onClick={() => removeTags(index)}
                          >
                            x
                          </span>
                        </li>
                      ))}
                    </ul>
                    <input
                      type="text"
                      onKeyUp={(event) =>
                        event.key === "Shift" ? addTags(event) : null
                      }
                      placeholder="Press shift to requirement"
                    />
                  </div>      
    </div>

    <div className="mb-3">
      <label>Daily activities</label>
                  <div className="tags-input">
                    <ul id="tags">
                      {tags1?.map((tag, index) => (
                        <li key={index} className="tag">
                          <span className="tag-title">{tag}</span>
                          <span
                            className="tag-close-icon"
                            onClick={() => removeTags1(index)}
                          >
                            x
                          </span>
                        </li>
                      ))}
                    </ul>
                    <input
                      type="text"
                      onKeyUp={(event) =>
                        event.key === "Shift" ? addTags1(event) : null
                      }
                      placeholder="Press shift to add daily activity"
                    />
                  </div>
                  </div>

        
       
      
        <button
      onClick={() => {
        createJobOfferHandler({
          position: position.current.value,
          description: description.current.value,
          location: location.current.value,
          companyId: company.id,
          requirements: requirements,
          daily_activities: dailyActivities

        }
        )
       
      }}
        >Create job offer</button>
      </form>
    </section>
 
  </div>
  }
</div>

      {jobOffers.map((jobOffer,i) => 

      
  (
    
  <div className="container">
    <div className="row gutters">
      <div className="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
        <div className="card h-100">
          <div className="card-body">
            <Card className="cardContainer" style={{ width: "30rem" }}>
              <Card.Img variant="top" />
              <Card.Body>
                <Card.Title className="cardTitle">
                 {jobOffer.position} 
                </Card.Title>
            
                <Card.Text>Description: {jobOffer.description}</Card.Text>
                
              </Card.Body>
              <ListGroup className="list-group-flush">
              <ListGroupItem>
              Location: {jobOffer?.location}
  
                 
                </ListGroupItem>
                <ListGroupItem>
              Mobile: {jobOffer?.mobile}
  
              
                </ListGroupItem>
                Daily activities: 
                {jobOffer.daily_activities.map((act,i) => (
                    <ListGroupItem> 
         {act.title}
        
                    </ListGroupItem>
                    ))}


                Requirements: 
                {jobOffer.requirements.map((act,i) => (
                    <ListGroupItem> 
         {act.title}
        
                    </ListGroupItem>
                    ))}
      {logedUser.role === "Agent" && 
      <div>
      <label>Pros</label>
      <li key={i}>
      <input type="text" className="form-control" placeholder="Pros" ref={pross.current[i]} />
      </li>
   
      <label>Cons</label>
      <li key={i}>
      <input type="text" className="form-control" placeholder="Cons" ref={conss.current[i]} />
      </li>
  
      <label>Rating</label>
      <li key={i}>
      <input type="text" className="form-control" placeholder="Rating" ref={ratings.current[i]}  />
      </li>
  
      <label>Salary</label>
      <li key={i}>
      <input type="text" className="form-control" placeholder="Salary" ref={salaries.current[i]} />
      </li>

      <label>Interview</label>
      <li key={i}>
      <input type="text" className="form-control" placeholder="Interview" ref={interviews.current[i]}   />
      </li>
    </div>}
    

    
              </ListGroup>
              <Card.Body>
              {logedUser.role === "Agent" &&
              <Button
                  
                  onClick={() =>  addJobCommentHandler({
                  pros: pross.current[i].current.value,
                  cons: conss.current[i].current.value,
                  rating: ratings.current[i].current.value,
                  salary: salaries.current[i].current.value,
                  interview: interviews.current[i].current.value,
                  jobOfferId: jobOffer.id
                })}
                  style={{ width: "12rem" }}
                  variant="outline-success"
                >
                 Add comment
                </Button>}
            




                <Button
                 onClick={() => {
                  localStorage.setItem("JobOffer", JSON.stringify(jobOffer));
                }}
                  href="jobOfferComments"
                  style={{ width: "12rem" }}
                  variant="outline-info"
                >
                View comments
                </Button>
               
              
              </Card.Body>
            </Card>
          </div>
        </div>
      </div>
    </div>
  </div>
  ))}
</div>
    </div>
  )
}
