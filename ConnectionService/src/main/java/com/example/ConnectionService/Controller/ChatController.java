package com.example.ConnectionService.Controller;


import com.example.CommunicationService.Event.UserNotifyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import com.example.ConnectionService.Service.*;
import com.example.ConnectionService.Model.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@RestController
//@RequestMapping(path = "/api/messages")
@CrossOrigin("*")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    //get all messages
    @GetMapping(value = "/messages", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllMessages(){
        ArrayList<Message> messages = chatService.getAllMessages();
        if(messages.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<ArrayList<Message>>(messages, HttpStatus.OK);
    }

    //get all messages by receiverId
    @GetMapping( value = "/chat", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllMessagesByReceiverId(@RequestParam(value="receiverId") String userId){
        ArrayList<Message> messages = chatService.getAllMessagesByReceiver(userId);
        if(messages.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<ArrayList<Message>>(messages, HttpStatus.OK);
    }

    //create message
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody Message newMessage){
        try {
            //Saga start
            UserNotifyEvent userNotifyEvent = new UserNotifyEvent();
            userNotifyEvent.setUserId(newMessage.getReceiverId());
            kafkaTemplate.send("user_notify", userNotifyEvent.getUserId());
            return new ResponseEntity<Message>(chatService.createMessage(newMessage) , HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    //delete message by id
    @DeleteMapping(value="/delete")
    public ResponseEntity<?> deleteMessage(@RequestParam(value = "id") String messageId){
        try{
            chatService.deleteSingleMessage(messageId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<String>(e.getMessage() ,HttpStatus.NOT_FOUND);
        }
    }

}

