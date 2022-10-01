package com.example.ConnectionService.Repository;

import java.util.ArrayList;

import com.example.ConnectionService.Model.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ChatRepository extends MongoRepository<Message, Long>{
    public ArrayList<Message> findAll();
    public ArrayList<Message> findAllByReceiverId(String userId);
    public Message findById(String messageId);

    public ArrayList<Message> findAllBySenderId(String userId);


}

