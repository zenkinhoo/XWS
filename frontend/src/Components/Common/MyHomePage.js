import React, { useRef } from 'react'
import { Card, ListGroup, ListGroupItem,Button} from 'react-bootstrap'
import { Link } from 'react-router-dom'

export default function MyHomePage({following, deleteUserByUsernameHandler,user, posts, likePostHandler, unlikePostHandler, addCommentHandler,followUserHandler,logedUserr,blockHandler,username ,blocked}) {
  console.log('user', user)
  console.log('posts', posts)
console.log("logedUserr", logedUserr.username);

console.log("blocked", blocked);
console.log("following", following);


  const content = useRef();

  console.log("username", username);
  

  function saveHandler(e) {
    addCommentHandler({
      id: 1,
      content: content.current.value,
      userId: logedUserr.id,
    });
  }


  return (
    <div><div id="card">

      <h1>{logedUserr.name}{" "} {logedUserr.username} <br />
      </h1>

      <div className="image-crop">
        <img
          id="avatar"
          src="https://drive.google.com/uc?id=1EVA3KUBLxCXF2EGmTf4LUB8F4yAvBrjl"
        />
      </div>

     

     
      <div id="bio">
        <p>
          Hello, my name is John! Bacon ipsum dolor amet short ribs prosciutto strip
          steak, pig ham tongue buffalo beef ribs hamburger pork venison.{" "}
        </p>
      </div>



      <div id="stats">
        <div className="col">
          <p className="stat">108</p>
          <p className="label1">Posts</p>
        </div>
        <div className="col">
          <p className="stat">457</p>
          <p className="label1">Followers</p>
        </div>
        <div className="col">
          <p className="stat">229</p>
          <p className="label1">Following</p>
        </div>
      </div>

      <div id="buttons">

    
  



    <button  onClick={() => {
      deleteUserByUsernameHandler(logedUserr.username
      )
    }} 
    class="btn btn-danger" >Delete
    </button>
  





  
       
    
       
        <Button href={"/updateUser"} class="btn btn-light" id="msg">Update</Button>
       
     

       
      </div>
    </div>

    {
      <div>
        {posts.map((post) => (
          <div className="container">
            <div className="row gutters">
              <div className="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                <div className="card h-100">
                  <div className="card-body">
                    <Card className="cardContainer" style={{ width: "30rem" }}>

                      <Card.Body>
                        <Card.Title className="cardTitle">
                          {post.title}
                        </Card.Title>
                        <Card.Text></Card.Text>
                      </Card.Body>
                      <ListGroup className="list-group-flush">
                        <ListGroupItem>
                          {post.description}
                        </ListGroupItem>
                        <ListGroupItem>
                          Likes: {post.likes} {" "} Dislikes: {post.dislikes}
                        </ListGroupItem>
                        <ListGroupItem> 
          <img src={post?.imageLink} className="center" width="200"
                    height="170"
                    />
         
                    </ListGroupItem>
                    {post.links?.map((link) => (
                    <ListGroupItem> 
          Link: {link}
        
                    </ListGroupItem>
                    ))}
                      </ListGroup>
                      <Card.Body>

                        <div className="col">
                          <button
                            style={{ width: "8rem" }}
                            variant="outline-success"
                            class="btn btn-primary"
                            onClick={() => {
                              likePostHandler(logedUserr.id, post.id
                              )
                              window.location.reload();
                            }}
                          >
                            Like
                          </button>
                          <button
                            style={{ width: "8rem" }}
                            variant="outline-success"
                            class="btn btn-danger"
                            onClick={() => {
                              unlikePostHandler(logedUserr.id, post.id
                              );
                              window.location.reload();
                            }}
                          >
                            Dislike
                          </button>

                        </div>

                        {post.comments.map((comment) => (
                          <div className="card p-3 mt-2">
                            <div className="d-flex justify-content-between align-items-center">
                              <div className="user d-flex flex-row align-items-center">
                                <img
                                  src="https://i.imgur.com/C4egmYM.jpg"
                                  width={30}
                                  className="user-img rounded-circle mr-2"
                                />
                                <span>
                                  <small className="font-weight-bold text-primary">{logedUserr.username}</small>{" "}
                                  <small className="font-weight-bold">
                                    {" "} {comment.content}
                                  </small>
                                </span>
                              </div>
                              <small>3 days ago</small>
                            </div>
                            <div className="action d-flex justify-content-between mt-2 align-items-center">
                              <div className="reply px-4">
                                <small>Remove</small>
                                <span className="dots" />
                                <small>Reply</small>
                                <span className="dots" />
                                <small>Translate</small>
                              </div>
                              <div className="icons align-items-center">
                                <i className="fa fa-check-circle-o check-icon text-primary" />
                              </div>
                            </div>

                          </div>




                        ))}
                        <br></br>
                        <div className="mb-3">
                          <label>Add a comment</label>
                          <input type="text" ref={content} className="form-control" placeholder="type a comment" />
                          <button
                            style={{ width: "7rem" }}
                            variant="outline-success"
                            class="btn btn-primary"
                            onClick={() => {
                              addCommentHandler({
                                id: 1,
                                content: content.current.value,
                                userId: logedUserr.id,
                              }, post.id);

                            }}
                          >
                            Comment
                          </button>
                        </div>


                      </Card.Body>
                    </Card>
                  </div>
                </div>
              </div>
            </div>

          </div>

        ))}
      </div>}
    </div>
  )
}
