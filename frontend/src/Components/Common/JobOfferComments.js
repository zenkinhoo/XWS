import React from "react";
import {
    Card,
    ListGroup,
    ListGroupItem,
    CardGroup,
    Button,
  } from "react-bootstrap";

export default function JobOfferComments({comments}) {

console.log("comments", comments);


return ( <div><div className="header">
{" "}
<h1 style={{ textAlign: "center" }}> Comments from post </h1>
</div>

{comments.map((comment,i) => 

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
               Comment
              </Card.Title>
            <ListGroup>
              
              <ListGroupItem>
              <Card.Text>Salary: {comment.salary}</Card.Text>
              </ListGroupItem>
              <ListGroupItem>
              <Card.Text>Interview: {comment.interview}</Card.Text>
              </ListGroupItem>
              <ListGroupItem>
              <Card.Text>Pros: {comment.pros}</Card.Text>
              </ListGroupItem>
              <ListGroupItem>
              <Card.Text>Cons: {comment.cons}</Card.Text>
              </ListGroupItem>
              <ListGroupItem>
              <Card.Text>Rating: {comment.rating}</Card.Text>
              </ListGroupItem>
            </ListGroup>
              
            </Card.Body>
            <ListGroup className="list-group-flush">
            
              

            </ListGroup>
           
          </Card>
        </div>
      </div>
    </div>
  </div>
</div>
))}</div>);
}
