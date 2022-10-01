import React from "react";
import {
    Card,
    ListGroup,
    ListGroupItem,
    CardGroup,
    Button,
  } from "react-bootstrap";

export default function CompanyRequests({companies,approveHandler,logedUser}) {
console.log("companies", companies);

var requests = companies.filter(obj => {
    return obj.request === "true"
  })

console.log("requests", requests);


return ( <div><div className="header">
{" "}
<h1 style={{ textAlign: "center" }}> Company requests </h1>
</div>

{requests.map((request,i) => 

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
               {request.name}
              </Card.Title>
              <Card.Text>Description: {request.description}</Card.Text>
              <Card.Text>Address: {request.address}</Card.Text>
              
            </Card.Body>
            <ListGroup className="list-group-flush">
            <ListGroupItem>
            Email: {request.email}

               
              </ListGroupItem>
              <ListGroupItem>
            Mobile: {request.mobile}

               
              </ListGroupItem>
              <ListGroupItem> 
          <img src={request?.profilePicture} className="center" width="200"
                    height="170"
                    />
         
                    </ListGroupItem>

            </ListGroup>
            <Card.Body>
          
              <Button
                onClick={() => approveHandler(logedUser.id,request.id)}
                style={{ width: "12rem" }}
                variant="outline-success"
              >
             Approve company
              </Button>
            
            
            </Card.Body>
          </Card>
        </div>
      </div>
    </div>
  </div>
</div>
))}</div>);
}
