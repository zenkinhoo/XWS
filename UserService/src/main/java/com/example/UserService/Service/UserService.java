package com.example.UserService.Service;

import com.example.UserService.Helper.EmailValidator;
import com.example.UserService.Model.Education;
import com.example.UserService.Model.Notification;
import com.example.UserService.Model.User;
import com.example.UserService.Repository.EducationRepository;
import com.example.UserService.Repository.ExperienceRepository;
import com.example.UserService.Repository.NotificationRepository;
import com.example.UserService.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Console;
import java.util.ArrayList;
import java.util.Random;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EducationRepository educationRepository;
    @Autowired
    private ExperienceRepository experienceRepository;
    private EmailValidator emailValidator = new EmailValidator();

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationService notificationService;


    public User create(User user) {
        boolean isValidEmail = emailValidator.test(user.getEmail());

        if(!isValidEmail){
            throw new IllegalStateException("Email nije u validnom formatu!");
        }

        boolean userExists = userRepository.findByEmail(user.getEmail()) != null;
        if(userExists){
            throw new IllegalStateException("Email ime vec postoji!");
        }
        userExists = userRepository.findByUsername(user.getUsername()) != null;
        if(userExists){
            throw new IllegalStateException("Korisnicko ime vec postoji!");
        }
        user.setIsPrivate(false);
        return userRepository.save(user);
    }


    public ArrayList<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User findById(String userId){
        User user = userRepository.findById(userId);
        if(user == null){
            throw new IllegalStateException("Korisnik ne postoji!");
        }
        return user;
    }

    public User findByUsername(String username){
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new IllegalStateException("Korisnik ne postoji!");
        }
        return user;
    }
    public User findByEmail(String email){
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new IllegalStateException("Korisnik ne postoji!");
        }
        return user;
    }
    public Boolean deleteUserByUsername(String username){
        User user = this.findByUsername(username);
        userRepository.delete(user);
        return true;
    }
    public Boolean deleteUserByEmail(String email){
        User user = this.findByEmail(email);
        userRepository.delete(user);
        return true;
    }

    public User login(String username, String password){
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new IllegalStateException("Korisnik ne postoji");
        }
        if(!user.getPassword().equals(password)){
            throw new IllegalStateException("Lozinka ne postoji");
        }
        return user;
    }


    public void deleteAllUsers(){
        userRepository.deleteAll();
    }

    public ResponseEntity<User> updateUser(String userId,
                                           @RequestBody User u)  {

            User user = userRepository.findById(userId);
            System.out.println("prosledjeni user " + u.getUsername());
            System.out.println("pronadjeni user " + user.getUsername());
            user.setUsername(u.getUsername());
            user.setPassword(u.getPassword());
            user.setName(u.getName());
            user.setEmail(u.getEmail());
            user.setPhone(u.getPhone());
            user.setGender(u.getGender());
            user.setBiography(u.getBiography());
            user.setInterests(u.getInterests());
            ArrayList<Education> ed = u.getEducation();
            user.setEducation(u.getEducation());
            user.setExperience(u.getExperience());
            user.setSkills(u.getSkills());
            user.setIsPrivate(u.getIsPrivate());
            user.setNotifications(u.getNotifications());
            final User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(updatedUser);
    }
    public User update(User editedUser){
        User existingUser=userRepository.findById(editedUser.getId());
        if(existingUser==null){
            throw new IllegalStateException("User does not exist!");
        }
        else{
            userRepository.delete(existingUser);
            return userRepository.save(editedUser);
        }
    }
    public ArrayList<User> searchUserByUsername(String partOfUsername)
    {
        ArrayList<User> users = userRepository.findByUsernameContaining(partOfUsername);
        ArrayList<User> privateUsers = new ArrayList<User>();
        for (User u:
             users) {
            if(!u.isPrivate())
            {
                privateUsers.add(u);
            }
        }
        return privateUsers;
    }

    //follow user
    public User follow(String followerId, String toFollowId) {
        System.out.println(followerId);
        System.out.println(toFollowId);
        User followerUser = userRepository.findById(followerId);
        User toFollowUser = userRepository.findById(toFollowId);

        if(followerUser == null){
            throw new IllegalStateException("followerUser does not exist!");
        }
        if(toFollowUser == null){
            throw new IllegalStateException("toFollowUser does not exist!");
        }
        if(followerUser.getFollowing().contains(toFollowId)){
            throw new IllegalStateException("Vec pratite ovog korisnika!");
        }
        if(followerUser.getBlocked().contains(toFollowId))
        {
            throw new IllegalStateException("Blokirali ste ovog korisnika!");
        }
        if(toFollowUser.isPrivate()){
            toFollowUser.getFollowRequests().add(followerId);
            Notification notification = new Notification();
            notification.setText("Imate novi zahtev od"+followerId);
            notificationRepository.save(notification);
            toFollowUser.getNotifications().add(notification);
            return userRepository.save(toFollowUser);
        }else{
            followerUser.getFollowing().add(toFollowId);
            return userRepository.save(followerUser);
        }
    }
    public User block(String userBlockingUsername,String userBlockedUsername){
        User userBlocking=userRepository.findByUsername(userBlockingUsername);
        User userBlocked=userRepository.findByUsername(userBlockedUsername);
        ArrayList<String> blockedUsers = userBlocking.getBlocked();
        if(userBlocking==null){
            throw new IllegalStateException("User who is trying to block does not exist!");
        }

        if(userBlocked==null){
            throw new IllegalStateException("User being blocked does not exist!");
        }

        if( userBlocking.getBlocked().contains(userBlockedUsername)){
            throw new IllegalStateException("You already blocked this user!");
        }

        if( userBlocking.getFollowing().contains(userBlockedUsername)){
            userBlocking.getFollowing().remove(userBlockedUsername);
        }

        if( userBlocking.getFollowRequests().contains(userBlockedUsername)){
            userBlocking.getFollowRequests().remove(userBlockedUsername);
        }

        if(userBlocked.getFollowing().contains(userBlockingUsername)){
            userBlocked.getFollowing().remove(userBlockingUsername);
        }

        if( userBlocked.getFollowRequests().contains(userBlockingUsername)){
            userBlocked.getFollowRequests().remove(userBlockingUsername);
        }

       /* if(blockedUsers.isEmpty())
            System.out.println("lista je prazna pre dodavanja");*/
        blockedUsers.add(userBlockedUsername);
        System.out.println("blocked users "+ blockedUsers.get(0));
        userBlocking.setBlocked(blockedUsers);

        userRepository.save(userBlocked);
        return userRepository.save(userBlocking);

    }

    public Boolean approveFollow(String userId, String followerUserId) {
        User user = userRepository.findById(userId);
        User followerUser = userRepository.findById(followerUserId);
        if (user.getFollowRequests() != null && user.getFollowRequests().contains(followerUserId)) {
            user.getFollowRequests().remove(followerUserId);
            followerUser.getFollowing().add(userId);
            if (userRepository.save(user) != null && userRepository.save(followerUser) != null) {
                System.out.println("User '" + userId + "' approved follow request from '" + followerUserId + "'.");
                return true;
            }
            System.out.println("User '" + userId + "' failed to approve follow request from '" + followerUserId + "'.");
            return false;
        }
        System.out.println("No such request exists from '" + followerUserId + "' to '" + userId + "'.");
        return false;
    }
    public Boolean rejectFollow(String userId, String followerUserId) {
        User user = userRepository.findById(userId);
        if (user.getFollowRequests() != null && user.getFollowRequests().contains(followerUserId)) {
            user.getFollowRequests().remove(followerUserId);
            if (userRepository.save(user) != null ) {
                System.out.println("User '" + userId + "' rejected follow request from '" + followerUserId + "'.");
                return true;
            }
            System.out.println("User '" + userId + "' failed to reject follow request from '" + followerUserId + "'.");
            return false;
        }
        System.out.println("No such request exists from '" + followerUserId + "' to '" + userId + "'.");
        return false;
    }





    public Boolean isFollowing(String userId, String followedUserId)
    {
        User user = userRepository.findById(userId);
        if(user.getFollowing().contains(followedUserId))
            return true;
        return false;
    }

    public User saveNotification(String userId,Notification notification)
    {
        User user = userRepository.findById(userId);
        ArrayList<Notification> notifications = user.getNotifications();
        notifications.add(notification);
        user.setNotifications(notifications);
        this.notificationRepository.save(notification);
        return userRepository.save(user);
    }
    public ArrayList<Notification> getAllUnreadNotificationsOfUser(String userId)
    {
        User user = userRepository.findById(userId);
        ArrayList<Notification> allNotifications = user.getNotifications();
        ArrayList<Notification> unreadNotifications = new ArrayList<Notification>();
        for (Notification notification:allNotifications
             ) {
            if(!notification.getRead())
            {
                unreadNotifications.add(notification);
            }
        }
        return unreadNotifications;
    }

    public Boolean areMutuallyBlocked(String firstUsername,String secondUsername)
    {
        User firstUser = userRepository.findByUsername(firstUsername);
        User secondUser = userRepository.findByUsername(secondUsername);
        if(firstUser.getBlocked().contains(secondUsername) || secondUser.getBlocked().contains(firstUsername))
            return true;
        return false;
    }



    public String generateAPIToken(String userId) {
        User user = userRepository.findById(userId);

        if (user == null) {
            return null;
        }
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 48;

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphaNumeric.length());
            sb.append(alphaNumeric.charAt(index));
        }
        String token = sb.toString();
        user.setApiToken(token);
        if (userRepository.save(user) != null) {
            return token;
        } else {
            return null;
        }
    }
}
