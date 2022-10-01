import React from "react";
import {
    Card,
    ListGroup,
    ListGroupItem,
    CardGroup,
    Button,
  } from "react-bootstrap";
import { Link } from "react-router-dom";

export default function FriendRequests({friendRequests,approveHandler,logedUser,rejectHandler}) {
    const key = 'id';

    const arrayUniqueByKey = [...new Map(friendRequests.map(item =>
      [item[key], item])).values()];
    console.log("friendRequests", arrayUniqueByKey);


  return(
    <div><div className="header">
    {" "}
    <h1 style={{ textAlign: "center" }}> Friend requests </h1>
  </div>
 
  {arrayUniqueByKey.map((user) => (
    <div className="container">
      <div className="row gutters">
        <div className="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
          <div className="card h-100">
            <div className="card-body">
              <Card className="cardContainer" style={{ width: "30rem" }}>
                <Card.Img variant="top" />
                <Card.Body>
                  <Card.Title className="cardTitle">
                    {user.name} {user.surname}
                  </Card.Title>
                  <Card.Text>{user.username}</Card.Text>
                  <Card.Text>{user.email}</Card.Text>
                  
                </Card.Body>
                <ListGroup className="list-group-flush">
                <ListGroupItem>
                Biography: {user.biography}

                   
                  </ListGroupItem>
                  <ListGroupItem>
                    Phone number: {user.phone}
                   
                  </ListGroupItem>
                  <ListGroupItem>
                   Gender: {user.gender}
                   
                  </ListGroupItem>

                
                </ListGroup>
                <Card.Body>
                
                  <Button
                    onClick={() => {
                      approveHandler(logedUser.id, user.id
                      )
              
                    }}
                    style={{ width: "8rem" }}
                    variant="outline-success"
                  >
                    Accept 
                  </Button>

                  <Button
                  onClick={() => {
                    rejectHandler(logedUser.id, user.id
                    )

                  }}
                  style={{ width: "8rem" }}
                  variant="outline-danger"
                >
                  Decline 
                </Button>
               
                
                </Card.Body>
              </Card>
            </div>
          </div>
        </div>
      </div>
    </div>
  ))}</div>
  )
}