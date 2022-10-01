package com.example.ConnectionService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import com.example.ConnectionService.Repository.*;
import com.example.ConnectionService.Model.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.events.Event;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    //get all messages - sorted by newest
    public ArrayList<Message> getAllMessages(){
        ArrayList<Message> messages = chatRepository.findAll();

        messages.sort(Comparator.comparing(Message::getSentDate).reversed());
        return messages;
    }

    //get all messages by receiverId - sorted by newest
    public ArrayList<Message> getAllMessagesByReceiver(String userId) {
        ArrayList<Message> messages = chatRepository.findAllByReceiverId(userId);
        messages.sort(Comparator.comparing(Message::getSentDate));
        return messages;
    }

    //create new message
    public Message createMessage(Message newMessage) {
        return chatRepository.save(newMessage);
    }

    //delete message by id
    public Boolean deleteSingleMessage(String messageId) {
        Message message = chatRepository.findById(messageId);
        chatRepository.delete(message);
        return true;
    }
    //delete all messages sent by user, when user is deleted all of his messages are also deleted, SAGA Pattern
    public void deleteUserMessages(String userId){
        ArrayList<Message> messagesToDelete = chatRepository.findAllBySenderId(userId);
        chatRepository.deleteAll(messagesToDelete);
    }

}
