import React, { useRef } from "react";

export default function CreateCompany({addCompanyHandler}) {

  var logedUser = JSON.parse(localStorage.getItem("User"));

  const name = useRef();
  const description = useRef();
  const address = useRef();
  const email = useRef();
  const mobile = useRef();
  const profilePicture = useRef();

  
  function saveHandler(e) {

    addCompanyHandler(logedUser.id,{
      name: name.current.value,
      description:description.current.value,
      address: address.current.value,
      email: email.current.value,
      mobile: mobile.current.value,
      profilePicture: profilePicture.current.value,
      approved: false,
      ownerId: logedUser.id,
      request: "true",
        });
  }



  return (
    
    <div className='regForm'> <form action="javascript:void(0);" onSubmit={saveHandler}>
    <h3>Create company</h3>
    <div className="mb-3">
      <label>Name</label>
      <input
        type="text"
        className="form-control"
        placeholder="Enter name"
        ref={name}
      />
    </div>
    <div className="mb-3">
      <label>Description</label>
      <input
        type="text"
        className="form-control"
        placeholder="Enter description"
        ref={description}
      />
    </div>
    <div className="mb-3">
      <label>Address</label>
      <input type="text" className="form-control" placeholder="Enter address"  ref={address} />
    </div>
    <div className="mb-3">
      <label>Email address</label>
      <input
        type="text"
        className="form-control"
        placeholder="Enter email"
        ref={email}
      />
    </div>
    <div className="mb-3">
      <label>Mobile</label>
      <input
        type="text"
        className="form-control"
        placeholder="Enter mobile"
        ref={mobile}
      />
    </div>
    <div className="mb-3">
      <label>Profile Picture url</label>
      <input
        type="phone"
        className="form-control"
        placeholder="Enter profile picture url"
        ref={profilePicture}
      />
    </div>

    
    <div className="d-grid">
      <button type="submit" className="btn btn-primary">
        Sign Up
      </button>
    </div>
    <p className="forgot-password text-right">
      Already registered <a href="/login">sign in?</a>
    </p>
  </form>
  </div>
  )
}