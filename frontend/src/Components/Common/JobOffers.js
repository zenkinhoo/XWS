import React, { useRef } from 'react'
import {
    Card,
    ListGroup,
    ListGroupItem,
    CardGroup,
    Button,
  } from "react-bootstrap";
import { Link } from 'react-router-dom';

export default function JobOffers({jobOffersAgent,jobOffers,searchByPositionHandler,agentSearchByPositionHandler}) {


const search = useRef();
  return (
    <div><div className="header">
    {" "}
    <h1 style={{ textAlign: "center" }}> Job offers </h1>
  </div>
  <input
        ref={search}
        type="search"
        className="form-control"
        name="search-cottage"
        placeholder="Search job offers by position.."
      ></input>
    <Button
          onClick={(e) => {
            searchByPositionHandler(search.current.value);
            agentSearchByPositionHandler(search.current.value);
          }}
                  
                  style={{ width: "8rem" }}
                  variant="secondary"
                >
                  Search
                </Button>
  {jobOffers?.map((jobOffer) => (
    <div className="container">
      <div className="row gutters">
        <div className="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
          <div className="card h-100">
            <div className="card-body">
              <Card className="cardContainer" style={{ width: "30rem" }}>
                <Card.Img variant="top" />
                <Card.Body>
                  <Card.Title className="cardTitle">
                   Position {jobOffer?.position}
                  </Card.Title>
                  <Card.Text>Description: {jobOffer?.description}</Card.Text>
                  <Card.Text>Location: {jobOffer?.location}</Card.Text>
                </Card.Body>
                <ListGroup className="list-group-flush">
                Requirements:
                {jobOffer?.requirements?.map((require) => (
                <ListGroupItem>
                 {require?.title}
                </ListGroupItem>
                ))}
               
               Daily activities:
                {jobOffer?.daily_activities?.map((da) => (
                <ListGroupItem>
                 {da?.title}
                </ListGroupItem>
                ))}

                
                </ListGroup>
                <Card.Body>
                <Link to={`/homepage/${jobOffer?.username}`}>
                  <Button
                  
                    style={{ width: "8rem" }}
                    variant="outline-success"
                  >
                    View profile
                  </Button>
                  </Link>
                
                </Card.Body>
              </Card>
            </div>
          </div>
        </div>
      </div>
    </div>
  ))}
  {jobOffersAgent?.map((jobOffer) => (
    <div className="container">
      <div className="row gutters">
        <div className="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
          <div className="card h-100">
            <div className="card-body">
              <Card className="cardContainer" style={{ width: "30rem" }}>
                <Card.Img variant="top" />
                <Card.Body>
                  <Card.Title className="cardTitle">
                   Position {jobOffer?.position}
                  </Card.Title>
                  <Card.Text>Description: {jobOffer?.description}</Card.Text>
                  <Card.Text>Location: {jobOffer?.location}</Card.Text>
                </Card.Body>
                <ListGroup className="list-group-flush">
                Requirements:
                {jobOffer?.requirements?.map((require) => (
                <ListGroupItem>
                 {require?.title}
                </ListGroupItem>
                ))}
               
               Daily activities:
                {jobOffer?.daily_activities?.map((da) => (
                <ListGroupItem>
                 {da?.title}
                </ListGroupItem>
                ))}
                 
                  <ListGroupItem>
                   Company id : {jobOffer?.companyId}
                   
                  </ListGroupItem>

                
                </ListGroup>
                <Card.Body>
                <Link to={`/homepage/${jobOffer?.username}`}>
                  <Button
                  
                    style={{ width: "8rem" }}
                    variant="outline-success"
                  >
                    View profile
                  </Button>
                  </Link>
                
                </Card.Body>
              </Card>
            </div>
          </div>
        </div>
      </div>
    </div>
  ))}
  
  
  
  
  
  </div>
  );
}
