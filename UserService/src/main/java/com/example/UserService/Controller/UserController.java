package com.example.UserService.Controller;


import com.example.CommunicationService.Event.UserDeleteEvent;
import com.example.CommunicationService.Event.UserUpdateEvent;
import com.example.UserService.Dto.LoginDto;
import com.example.UserService.Model.Notification;
import com.example.UserService.Model.User;
import com.example.UserService.Service.NotificationService;
import com.example.UserService.Service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.security.RolesAllowed;


@RestController
//@RequestMapping(path = "/users")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    NotificationService notificationService;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;




    @RequestMapping("/")
    public String helloWorld(){
        return "Hello World from Spring Boot";
    }


    //get all users
    @GetMapping(
            value = "/users",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsers(){
        ArrayList<User> users = userService.getAllUsers();
        if(users.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<ArrayList<User>>(userService.getAllUsers(), HttpStatus.OK);
    }

    //create (register) user
    @PostMapping(
            value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody User newUser){
        try {
            return new ResponseEntity<User>(userService.create(newUser) , HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    //login user
    @PostMapping(path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        try{
            return new ResponseEntity<User>(userService.login(loginDto.getUsername(), loginDto.getPassword()), HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //get user by username
    @GetMapping(
            value = "/userById",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserById(@RequestParam(value="userId") String userId){
        try{
            return new ResponseEntity<User>(userService.findById(userId), HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //get user by username
    @GetMapping(
            value = "/user",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserByUsername(@RequestParam(value="username") String username){
        try{
            return new ResponseEntity<User>(userService.findByUsername(username), HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //get user by email
    @GetMapping(value="/userByEmail",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserByEmail(@RequestParam(value="email") String email){
        try{
            return new ResponseEntity<User>(userService.findByEmail(email), HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

//    //delete user by username
//    @DeleteMapping(value = "/user",
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> deleteUserByUsername(@RequestParam(value="username") String username){
//        try{
//            userService.deleteUserByUsername(username);
//        } catch (IllegalStateException e) {
//            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<String>("User deleted by username", HttpStatus.OK);
//    }

    //delete user by username
    //TODO: SAGA
    @DeleteMapping(path = "/{username}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUser(@PathVariable("username") String username){
        try{

            User user = userService.findByUsername(username);

            //Saga start
            userService.deleteUserByUsername(username);
            UserDeleteEvent userDeleteEvent = new UserDeleteEvent();
            userDeleteEvent.setUserId(user.getId());
            kafkaTemplate.send("user_delete", userDeleteEvent.getUserId());

        } catch (IllegalStateException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("User deleted", HttpStatus.OK);
    }

    //delete user by email
    @DeleteMapping(value = "/userByEmail",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUserByEmail(@RequestParam(value="email") String email){
        try{
            userService.deleteUserByEmail(email);
        } catch (IllegalStateException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("User deleted by email", HttpStatus.OK);
    }

  @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") String userId,
                                                          @RequestBody User user)
    {
       return userService.updateUser(userId, user);
   }
    //TODO: SAGA
//    @PutMapping(
//            value = "/update",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> updateUser(@RequestBody User user){
//        try {
//            User editedUser = userService.updateUser(user.id, );
//            return new ResponseEntity<User>(editedUser, HttpStatus.OK);
//        } catch (IllegalStateException e) {
//            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }
    @GetMapping(
            value = "/following",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserByUsername(@RequestParam(value="userId") String userId,@RequestParam(value="followedUserId") String followedUserId){
        try{
            return new ResponseEntity<Boolean>(userService.isFollowing(userId,followedUserId),HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    //follow user
    @PutMapping(path = "/follow",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> follow(@RequestBody Map<String, String> followRequest){
        try{
            return new ResponseEntity<User>(userService.follow(followRequest.get("followerId"), followRequest.get("toFollowId")), HttpStatus.OK);
        } catch (IllegalStateException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    //block user
    @PutMapping(path = "/block",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> block(@RequestBody Map<String, String> block){
        try{
            return new ResponseEntity<User>(userService.block(block.get("blockerId"), block.get("blockedId")), HttpStatus.OK);
        } catch (IllegalStateException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //proverava da li je barem 1 od 2 korisnika blokirao ovog drugog, true znaci da jeste
    @GetMapping(
            value = "/areBlocked",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> areMutuallyBlocked(@RequestParam(value="firstUsername") String firstUsername,@RequestParam(value="secondUsername") String secondUsername){
        try{
            return new ResponseEntity<Boolean>(userService.areMutuallyBlocked(firstUsername,secondUsername),HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/approve",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> approveFollow(@RequestBody Map<String, String> userIds) {
        if (userService.approveFollow(userIds.get("userId"), userIds.get("followerUserId"))) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }


    @PutMapping(path = "/reject",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> rejectFollow(@RequestBody Map<String, String> userIds) {
        if (userService.rejectFollow(userIds.get("userId"), userIds.get("followerUserId"))) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }
    //get all users by part of username
    @GetMapping(path = "/search/{username}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchForUsername(@PathVariable("username") String username){
        ArrayList<User> users = userService.searchUserByUsername(username);
        if(users.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<ArrayList<User>>(users, HttpStatus.OK);
    }

    @PutMapping(path="/generateToken/{userId}",
            consumes=MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> generateToken(@PathVariable("userId") String userId){
        try{
            return new ResponseEntity<String>(userService.generateAPIToken(userId),HttpStatus.OK);
        } catch(IllegalStateException e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    //create user notification, za sad ostavljeno da vraca notifikaciju kao odgovor
    @PostMapping(path = "/{userId}/saveNotification",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> putPost(@PathVariable String userId, @RequestBody Notification notification){
        try{
            userService.saveNotification(userId,notification);
            return new ResponseEntity<Notification>(notification ,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<String>(e.getMessage() ,HttpStatus.NOT_FOUND);
        }
    }
    //get all notifications of user
    //get user by username
    @GetMapping(
            value = "/notificationsByUser",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllNotificationsOfUser(@RequestParam(value="userId") String userId){
        try{
            User user = userService.findById(userId);
            return new ResponseEntity<ArrayList<Notification>>(user.getNotifications(), HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
//unread notifications by user
    @GetMapping(
            value = "/unreadNotificationsByUser",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUnreadNotificationsOfUser(@RequestParam(value="userId") String userId){
        try{
            User user = userService.findById(userId);
            return new ResponseEntity<ArrayList<Notification>>(userService.getAllUnreadNotificationsOfUser(userId), HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping(path = "/markAsRead",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> markNotificationAsRead(@RequestBody Map<String, String> notificationInfo){
        try{
            Notification notification = notificationService.findById(notificationInfo.get("notificationId"));
            notificationService.markAsRead(notificationInfo.get("userId"), notificationInfo.get("notificationId"));
            return new ResponseEntity<Notification>(notification ,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<String>(e.getMessage() ,HttpStatus.NOT_FOUND);
        }
    }
}
