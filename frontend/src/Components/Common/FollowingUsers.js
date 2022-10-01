import React, { useRef } from "react";
import {
    Card,
    ListGroup,
    ListGroupItem,
    CardGroup,
    Button,
  } from "react-bootstrap";

export default function FollowingUsers({followingUsers,logedUser,sendMessageHandler}) {

    let answers = useRef([React.createRef(), React.createRef()]);
    const key = 'id';

    const arrayUniqueByKey = [...new Map(followingUsers.map(item =>
      [item[key], item])).values()];
    console.log("followingUsers", arrayUniqueByKey);

    console.log("logedUser", logedUser);


    

  return (
  <div><div className="header">
  {" "}
  <h1 style={{ textAlign: "center" }}> Following users </h1>
</div>

{arrayUniqueByKey.map((user, i) => (
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

                <ListGroupItem>
                <li key={i}>
                <input
                  type="text"
         className="form-control"
          placeholder="Send a message "
          spellCheck="false"
          required=""
          defaultValue={""}
          ref= {answers.current[i]}
        />
       </li>
                </ListGroupItem>
              
              </ListGroup>

           
             
         
              <Card.Body>
                <Button
                    onClick={() => {
                        sendMessageHandler(
                            {
                                senderId: logedUser.id,
                                receiverId: user.id,
                                message: answers.current[i].current.value
                            }
                        )
                
                      }}
                  style={{ width: "12rem" }}
                  variant="outline-success"
                >
                 Send message
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