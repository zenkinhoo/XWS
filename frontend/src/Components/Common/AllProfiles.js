import React, { useRef } from "react";
import {
    Card,
    ListGroup,
    ListGroupItem,
    CardGroup,
    Button,
  } from "react-bootstrap";
import { Link } from 'react-router-dom';

export default function AllProfiles({logedUserr,followUserHandler,users,searchByUsernameHandler}) {

    console.log('users', users)

    const publicUsers = new Array();

      for (const user of users){
        if(user.private == false)
        {
            console.log("publicUser",user);
            publicUsers.push(user);
        }
    }
  
    const search = useRef("");

    console.log('publicUsers', publicUsers)

    
  return (
    <div><div className="header">
    {" "}
    <h1 style={{ textAlign: "center" }}> All public profiles </h1>
  </div>
  <input
        ref={search}
        type="search"
        className="form-control"
        name="search-cottage"
        placeholder="Searchpublic profiles by username.."
      ></input>
    <Button
        onClick={(e) => searchByUsernameHandler(search.current.value)}
                  
                  style={{ width: "8rem" }}
                  variant="secondary"
                >
                  Search
                </Button>
  {users.map((user) => (
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
                    href={`/homepage/${user.username}`}
                    style={{ width: "8rem" }}
                    variant="outline-success"
                  >
                    View profile
                  </Button>
                  <Button  onClick={() => {
      followUserHandler(logedUserr.id, user.id
      )
    }} 
    class="btn btn-primary" >Follow
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
