import React from "react";
import {
    Card,
    ListGroup,
    ListGroupItem,
    CardGroup,
    Button,
  } from "react-bootstrap";

export default function Notifications({notifications,markAsReadHandler,logedUser}) {

console.log("notifications", notifications);
console.log("notifications", notifications[0]?.creationTime.slice(0, 10));
console.log("logedUser", logedUser);


  return (
    <div><div className="header">
    {" "}
    <h1 style={{ textAlign: "center" }}> Notifications </h1>
  </div>
 
    
  {notifications?.map((notification) => (
    <div className="container">
      <div className="row gutters">
        <div className="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
          <div className="card h-100">
            <div className="card-body">
              <Card className="cardContainer" style={{ width: "30rem" }}>
                <Card.Img variant="top" />
                <Card.Body>
                  <Card.Title className="cardTitle">
                   Notify: {notification?.text}
                  </Card.Title>
                  <Card.Text>Creation date: {notification?.creationTime?.slice(0, 10)}</Card.Text>
                  <Card.Text>Creation time: {notification?.creationTime?.slice(11, 19)}</Card.Text>

                </Card.Body>
                <Card.Body>
                <Button
                  onClick={() => {
                    markAsReadHandler(logedUser.id, notification?.id
                    )
                  }}
                  style={{ width: "12rem" }}
                  variant="outline-success"
                >
                 Mark as read
                </Button>
              
              </Card.Body>
            
              </Card>
            </div>
          </div>
        </div>
      </div>
    </div>
  ))}</div>
  );
}
