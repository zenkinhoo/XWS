import React, { useRef } from 'react'

export default function Login({loginUserHandler,logedUser,loginAgentHandler}) {

console.log('logedUser', logedUser)

const username = useRef();
const password = useRef();

  return (
    <div><div className="login-wrap">
    <div className="login-html">
    
     
      <div className="login-form">
        <div className="sign-in-htm">
          <div className="group">
            <label htmlFor="user" className="label">
              Username
            </label>
            <input ref={username} id="user" type="text" className="input" />
          </div>
          <div className="group">
            <label htmlFor="pass" className="label">
              Password
            </label>
            <input
              ref={password}
              id="pass"
              type="password"
              className="input"
              data-type="password"
            />
          </div>
          <div className="group">
            <input
              id="check"
              type="checkbox"
              className="check"
              defaultChecked=""
            />
            <label htmlFor="check">
            <label htmlFor="user" className="label">
             Keep me singed in
            </label>
            </label>
          </div>
          <div className="group">
          <button
              className="button login__submit"
              onClick={(e) => {
                {
                  loginUserHandler(
                    username.current.value,
                    password.current.value
                  );

                  loginAgentHandler(
                    username.current.value,
                    password.current.value
                  );
                }
              }}
            >
              <span className="button__text">Log In </span>
            </button>
          </div>
          <div className="hr" />
          <div className="foot-lnk">
            <a href="#forgot">Forgot Password?</a>
          </div>
        </div>
        
      </div>
    </div>
  </div>
  </div>
  )
}
