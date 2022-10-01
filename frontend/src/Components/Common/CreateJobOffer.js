import React, { useRef } from "react";
import "../../Assets/css/tags.scss";

export default function CreateJobOffer({tags1,addTags1,removeTags1,tags,addTags,removeTags,createJobOfferHandler}) {


    const position = useRef();
    const description = useRef();
    const location = useRef();
    const companyId = useRef();

    
    var requirements = [];
    var len = tags.length;
    for (var i = 0; i < len; i++) {
        requirements.push({
            id: Math.floor(Math.random() * 100) + 1,
            title: tags[i],
        });
    }

    var dailyActivities = [];
    var len1 = tags1.length;
    for (var i = 0; i < len1; i++) {
        dailyActivities.push({
            id: Math.floor(Math.random() * 100) + 1,
            title: tags1[i],
        });
    }
   

    
    
    function saveHandler(e) {
        createJobOfferHandler({
            position: position.current.value,
            description: description.current.value,
            location: location.current.value,
            requirements: requirements,
            daily_activities: dailyActivities,
          });
    }


   
    

  return (
    
    <div className='regForm'> <form action="javascript:void(0);" onSubmit={saveHandler}>
    <h3>Create new job offer</h3>
    <div className="mb-3">
      <label>Position</label>
      <input
        type="text"
        className="form-control"
        placeholder="Enter Position"
        ref={position}
      />
    </div>
    <div className="mb-3">
      <label>Description</label>
      <input type="text" className="form-control" placeholder="Enter Description"  ref={description} />
    </div>
    <div className="mb-3">
      <label>Location</label>
      <input
        type="text"
        className="form-control"
        placeholder="Enter location"
        ref={location}
      />
    </div>
    <div className="mb-3">      
    </div>

    <div className="mb-3">
      <label>Requirements</label>
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
      <label>Daily activities</label>
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
                      placeholder="Press shift to add daily activity"
                    />
                  </div>
                  </div>

    
    
    <div className="d-grid">
      <button type="submit" className="btn btn-primary">
        Create job offer
      </button>
    </div>
   
  </form>
  </div>
  )
}
