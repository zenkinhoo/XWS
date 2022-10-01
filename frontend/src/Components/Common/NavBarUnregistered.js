import React from 'react'

export default function NavbarUnregistered() {

  var logedUser = JSON.parse(localStorage.getItem("User"));


  return (
    <div><>
    
    {/* Navbar */}
    <nav className="navbar navbar-expand-lg navbar-light bg-white">
      {/* Container wrapper */}
      <div className="container-fluid">
        {/* Toggle button */}
        <button
          className="navbar-toggler"
          type="button"
          data-mdb-toggle="collapse"
          data-mdb-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <i className="fas fa-bars" />
        </button>
        {/* Collapsible wrapper */}
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          {/* Navbar brand */}
          <a className="navbar-brand mt-2 mt-lg-0" href="#">
            <img
              src="https://mdbcdn.b-cdn.net/img/logo/mdb-transaprent-noshadows.webp"
              height={15}
              alt="MDB Logo"
              loading="lazy"
            />
          </a>
          {/* Left links */}
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            <li className="nav-item">
              <a className="nav-link" href="publicProfiles">
              Public profiles
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="registration">
                Registration
              </a>
            </li>
            
            
          </ul>
          {/* Left links */}
        </div>
        {/* Collapsible wrapper */}
        {/* Right elements */}
        <div className="d-flex align-items-center">
          {/* Icon */}
          <a className="link-grayish me-3" href="#">
            <i className="fas fa-shopping-cart" />
          </a>
          {/* Notifications */}
          
          {/* Avatar */}
          
        </div>
        {/* Right elements */}
      </div>
      {/* Container wrapper */}
    </nav>
    {/* Navbar */}
  </>
  </div>
  )
}
