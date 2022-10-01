import React, { useRef } from "react";

export default function RegistrationFromAgent({addAgentHandler}) {

  const name = useRef();
  const surname = useRef();
  const username = useRef();
  const email = useRef();
  const password = useRef();
  const phone = useRef();
  
  function saveHandler(e) {

    addAgentHandler({
          name: name.current.value,
          surname:surname.current.value,
          username: username.current.value,
          email: email.current.value,
          password: password.current.value,
          phone: phone.current.value,
          role: "owner",
        });
  }

  return (
    
    <div className='regForm'> <form action="javascript:void(0);" onSubmit={saveHandler}>
    <h3>Sign Up Agent</h3>
    <div className="mb-3">
      <label>First name</label>
      <input
        type="text"
        className="form-control"
        placeholder="First  name"
        ref={name}
      />
    </div>
    <div className="mb-3">
      <label>Last name</label>
      <input
        type="text"
        className="form-control"
        placeholder="Last name"
        ref={surname}
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