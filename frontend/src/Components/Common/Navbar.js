import React from 'react'

export default function Navbar() {

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
              <a className="nav-link" href="allProfiles">
              All profiles
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="publicProfiles">
              Public profiles
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="newFeed">
                New feed
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="followRequests">
              Follow Requests
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="messages">
              Messages
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="jobOffers">
              Job offers
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="createJobOffer">
              Create Job Offer
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="following">
              Following users
              </a>
            </li>
            <li className="nav-item">
            <a className="nav-link" href={`updateUser`}>
              Update profile
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
          <div className="dropdown">
            <a
              className="link-grayish me-3 dropdown-toggle hidden-arrow"
              href="/notifications"
              id="navbarDropdownMenuLink"
              role="button"
          
              aria-expanded="false"
            >
              <i className="fas fa-bell" />
              <span className="badge rounded-pill badge-notification bg-danger">
                1
              </span>
            </a>
            <ul
              className="dropdown-menu dropdown-menu-end"
              aria-labelledby="navbarDropdownMenuLink"
            >
              <li>
                <a className="dropdown-item" href="#">
                  Some news
                </a>
              </li>
              <li>
                <a className="dropdown-item" href="#">
                  Another news
                </a>
              </li>
              <li>
                <a className="dropdown-item" href="#">
                  Something else here
                </a>
              </li>
            </ul>
          </div>
          {/* Avatar */}
          <div className="dropdown">
            <a
              className="dropdown-toggle d-flex align-items-center hidden-arrow"
              href="/myHomePage"
              id="navbarDropdownMenuAvatar"
              role="button"
              data-mdb-toggle="dropdown"
              aria-expanded="false"
            >
              <img
                src="https://mdbcdn.b-cdn.net/img/new/avatars/2.webp"
                className="rounded-circle"
                height={25}
                alt="Black and White Portrait of a Man"
                loading="lazy"
              />
            </a>
            <ul
              className="dropdown-menu dropdown-menu-end"
              aria-labelledby="navbarDropdownMenuAvatar"
            >
              <li>
                <a className="dropdown-item" href="#">
                  My profile
                </a>
              </li>
              <li>
                <a className="dropdown-item" href="#">
                  Settings
                </a>
              </li>
              <li>
                <a className="dropdown-item" href="#">
                  Logout
                </a>
              </li>
            </ul>
          </div>
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
