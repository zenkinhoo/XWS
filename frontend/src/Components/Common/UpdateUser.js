import React, { useRef, useState } from "react";

export default function UpdateUser({tags5,addTags5,removeTags5,tags4,addTags4,removeTags4,tags3,addTags3,removeTags3,tags2,addTags2,removeTags2,tags1,addTags1,removeTags1,tags,addTags,removeTags,logedUser,updateUserHandler}) {


    const name = useRef();
    const username = useRef();
    const email = useRef();
    const password = useRef();
    const phone = useRef();
    const gender = useRef();
    const biography = useRef();

    const fromDateEdu = useRef();
    const toDateEdu = useRef();

    const comany = useRef();
    const position = useRef();
    const fromDate = useRef();
    const toDate = useRef();
    const desciption = useRef();



      const [privateProfile, setPrivateProfile] = useState(logedUser.private);
      
      var education = [];
      var len = tags2?.length;
      for (var i = 0; i < len; i++) {
        education.push({
              id: Math.floor(Math.random() * 100) + 1,
              educationalFacility: tags2[i],
          });
      }
  
      var experience = [];
      var len1 = tags3?.length;
      for (var i = 0; i < len1; i++) {
        experience.push({
              id: Math.floor(Math.random() * 100) + 1,
              company: tags3[i],
              position:tags4[i],
              description:tags5[i],
          });
      }

  return (
    
    <div className='regForm'> 
    <h3>Update profile</h3>
    <div className="mb-3">
      <label>Name and Surname: {logedUser?.name}</label>
      <input
        type="text"
        className="form-control"
        placeholder="Update name"
        ref={name}
      />
    </div>
    <div className="mb-3">
      <label>Username: {logedUser?.username}</label>
      <input
        type="text"
        className="form-control"
        placeholder="Update username"
        ref={username}
      />
    </div>
    <div className="mb-3">
      <label>Email: {logedUser?.email}</label>
      <input
        type="text"
        className="form-control"
        placeholder="Update email"
        ref={email}
      />
    </div>


    <div className="mb-3">
      <label>Password: {logedUser?.password}</label>
      <input
        type="text"
        className="form-control"
        placeholder="Update password"
        ref={password}
      />
    </div>

    <div className="mb-3">
      <label>Phone: {logedUser?.phone}</label>
      <input
        type="text"
        className="form-control"
        placeholder="Update phone"
        ref={phone}
      />
    </div>

    <div className="mb-3">
      <label>Gender: {logedUser?.gender}</label>
      <input
        type="text"
        className="form-control"
        placeholder="Update gender"
        ref={gender}
      />
    </div>

    <div className="mb-3">
      <label>Biography: {logedUser?.biography}</label>
      <input
        type="text"
        className="form-control"
        placeholder="Update biography"
        ref={biography}
      />
    </div>


    <div className="mb-3">
      <label>Skills</label>
    <div className="tags-input">
                    <ul id="tags">
                      {tags?.map((tag, index) => (
                        <li key={index} className="tag">
                          <span className="tag-title">{tag}</span>
                          <span
                            className="tag-close-icon"
                            onClick={() => removeTags(index)}
                          >
                            x
                          </span>
                        </li>
                      ))}
                    </ul>
                    <input
                      type="text"
                      onKeyUp={(event) =>
                        event.key === "Shift" ? addTags(event) : null
                      }
                      placeholder="Press shift to requirement"
                    />
                  </div>      
    </div>

    <div className="mb-3">
      <label>Interests</label>
                  <div className="tags-input">
                    <ul id="tags">
                      {tags1?.map((tag, index) => (
                        <li key={index} className="tag">
                          <span className="tag-title">{tag}</span>
                          <span
                            className="tag-close-icon"
                            onClick={() => removeTags1(index)}
                          >
                            x
                          </span>
                        </li>
                      ))}
                    </ul>
                    <input
                      type="text"
                      onKeyUp={(event) =>
                        event.key === "Shift" ? addTags1(event) : null
                      }
                      placeholder="Press shift to add interests"
                    />
                  </div>
                  </div>


                  <div className="mb-3">
                  <label>Education - educational facility</label>
                  <div className="tags-input">
                    <ul id="tags">
                      {tags2?.map((tag, index) => (
                        <li key={index} className="tag">
                          <span className="tag-title">{tag}</span>
                          <span
                            className="tag-close-icon"
                            onClick={() => removeTags2(index)}
                          >
                            x
                          </span>
                        </li>
                      ))}
                    </ul>
                    <input
                      type="text"
                      onKeyUp={(event) =>
                        event.key === "Shift" ? addTags2(event) : null
                      }
                      placeholder="Press shift to add interests"
                    />
                  </div>

                  <label>Experience - company </label>
                  <div className="tags-input">
                    <ul id="tags">
                      {tags3?.map((tag, index) => (
                        <li key={index} className="tag">
                          <span className="tag-title">{tag}</span>
                          <span
                            className="tag-close-icon"
                            onClick={() => removeTags3(index)}
                          >
                            x
                          </span>
                        </li>
                      ))}
                    </ul>
                    <input
                      type="text"
                      onKeyUp={(event) =>
                        event.key === "Shift" ? addTags3(event) : null
                      }
                      placeholder="Press shift to add company"
                    />
                  </div>

                  <label>Experience - position </label>
                  <div className="tags-input">
                    <ul id="tags">
                      {tags4?.map((tag, index) => (
                        <li key={index} className="tag">
                          <span className="tag-title">{tag}</span>
                          <span
                            className="tag-close-icon"
                            onClick={() => removeTags4(index)}
                          >
                            x
                          </span>
                        </li>
                      ))}
                    </ul>
                    <input
                      type="text"
                      onKeyUp={(event) =>
                        event.key === "Shift" ? addTags4(event) : null
                      }
                      placeholder="Press shift to add position"
                    />
                  </div>

                  <label>Experience - description </label>
                  <div className="tags-input">
                    <ul id="tags">
                      {tags5?.map((tag, index) => (
                        <li key={index} className="tag">
                          <span className="tag-title">{tag}</span>
                          <span
                            className="tag-close-icon"
                            onClick={() => removeTags5(index)}
                          >
                            x
                          </span>
                        </li>
                      ))}
                    </ul>
                    <input
                      type="text"
                      onKeyUp={(event) =>
                        event.key === "Shift" ? addTags5(event) : null
                      }
                      placeholder="Press shift to add description"
                    />
                  </div>

                 



                  <div className="radio-btn-container">
                    Private profile?
                  <div
                      className="radio-btn"
                      onClick={() => {
                        setPrivateProfile(true);
                      }}
                    >
                      <input
                        type="radio"
                        value={privateProfile}
                        name="badd"
                        checked={privateProfile == true}
                      />
                      Yes
                    </div>
                  </div>
                  <div
                      className="radio-btn"
                      onClick={() => {
                        setPrivateProfile(false);
                      }}
                    >
                      <input
                        type="radio"
                        value={privateProfile}
                        name="badd"
                        checked={privateProfile == false}
                      />
                      No
                    </div>
                    </div>


    
    
    <div className="d-grid">
      <button  onClick={() => {
                         if(username.current.value!=undefined)
                         {
                            logedUser.username = username.current.value;
                         }
                         if(password.current.value!=undefined)
                         {
                            logedUser.password = password.current.value;
                         }
                         if(name.current.value!=undefined)
                         {
                            logedUser.name = name.current.value;
                         }
                         if(email.current.value!=undefined)
                         {
                            logedUser.email = email.current.value;
                         }
                         if(phone.current.value!=undefined)
                         {
                            logedUser.phone = phone.current.value;
                         }
                         if(gender.current.value!=undefined)
                         {
                            logedUser.gender = gender.current.value;
                         }
                         if(biography.current.value!=undefined)
                         {
                            logedUser.biography = biography.current.value;
                         }
                         logedUser.skills = tags;
                         logedUser.interests = tags1;
                         logedUser.education = education;
                         logedUser.experience = experience;
                         logedUser.private = privateProfile;
                         updateUserHandler(logedUser);
                         localStorage.setItem("User", JSON.stringify(logedUser));



                        
                        }} 
        type="submit" className="btn btn-primary">
        Update
      </button>
    </div>
   

  </div>
  )
}
