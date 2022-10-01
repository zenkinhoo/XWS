import React, { useRef } from "react";
import {
    Card,
    ListGroup,
    ListGroupItem,
    CardGroup,
    Button,
  } from "react-bootstrap";

export default function CompaniesByUser({companies,updateCompanyHandler}) {

  console.log("companies", companies);
  
  let names = useRef([React.createRef(), React.createRef()]);
  let descriptions = useRef([React.createRef(), React.createRef()]);
  let address = useRef([React.createRef(), React.createRef()]);
  let emails = useRef([React.createRef(), React.createRef()]);
  let mobiles = useRef([React.createRef(), React.createRef()]);


  var companiess = companies.filter(obj => {
    return obj.approved === true
  })



  return ( <div><div className="header">
  {" "}
  <h1 style={{ textAlign: "center" }}> My companies </h1>
  </div>
  
  {companiess.map((request,i) => 
  
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
      <input
        type="text"
        className="form-control"
        placeholder="Update name"
        ref= {names.current[i]}

      />
                </Card.Title>
                <Card.Text>Description: {request.description}
                <input
        type="text"
        className="form-control"
        placeholder="Update description"
        ref= {descriptions.current[i]}
      />
                </Card.Text>
                <Card.Text>Address: {request.address}
                <input
        type="text"
        className="form-control"
        placeholder="Update address"
        ref= {address.current[i]}

      />
                </Card.Text>
                
              </Card.Body>
              <ListGroup className="list-group-flush">
              <ListGroupItem>
              Email: {request.email}
              <input
        type="text"
        className="form-control"
        placeholder="Update email"
        ref= {emails.current[i]}
      />
                 
                </ListGroupItem>
                <ListGroupItem>
              Mobile: {request.mobile}
              <input
        type="text"
        className="form-control"
        placeholder="Update mobile"
        ref= {mobiles.current[i]}
      />
                 
                </ListGroupItem>
                <ListGroupItem> 
            <img src={request?.profilePicture} className="center" width="200"
                      height="170"
                      />
           
                      </ListGroupItem>
  
              </ListGroup>
              <Card.Body>
                <Button
                  onClick={() => {
                   
                    request.name = names.current[i].current.value;
                    request.description = descriptions.current[i].current.value;
                    request.mobile = mobiles.current[i].current.value;
                    request.email = emails.current[i].current.value;
                    request.address = address.current[i].current.value;
                    request.profilePicture =  request.profilePicture;
                    request.request = false;
                    request.apiToken = "asfafasfaf";
                    updateCompanyHandler(request);

                   }} 
                  style={{ width: "12rem" }}
                  variant="outline-success"
                >
               Update company
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
  