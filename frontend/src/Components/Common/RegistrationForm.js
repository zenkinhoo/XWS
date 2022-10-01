import React, { useRef } from 'react'

export default function RegistrationForm({addUserHandler}) {


const name = useRef();
const username = useRef();
const email = useRef();
const password = useRef();
const phone = useRef();
const gender = useRef();
const biography = useRef();

function saveHandler(e) {

  addUserHandler({
        name: name.current.value,
        username: username.current.value,
        email: email.current.value,
        password: password.current.value,
        phone: phone.current.value,
        gender: gender.current.value,
        biography: biography.current.value,
      });
}


  return (
    
    <div className='regForm'> <form action="javascript:void(0);" onSubmit={saveHandler}>
    <h3>Sign Up</h3>
    <div className="mb-3">
      <label>First and last name</label>
      <input
        type="text"
        className="form-control"
        placeholder="First and last name"
        ref={name}
      />
    </div>
    <div className="mb-3">
      <label>Username</label>
      <input type="text" className="form-control" placeholder="Username"  ref={username} />
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
      <label>Password</label>
      <input
        type="password"
        className="form-control"
        placeholder="Enter password"
        ref={password}
      />
    </div>
    <div className="mb-3">
      <label>Phone</label>
      <input
        type="phone"
        className="form-control"
        placeholder="Enter email"
        ref={phone}
      />
    </div>
    
    <div className="mb-3">
      <label>Gender</label>
      <input
        type="gender"
        className="form-control"
        placeholder="Enter gender"
        ref={gender}
      />
    </div>
    <div className="mb-3">
      <label>Biography</label>
      <input
        type="biography"
        className="form-control"
        placeholder="Enter biography"
        ref={biography}
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
