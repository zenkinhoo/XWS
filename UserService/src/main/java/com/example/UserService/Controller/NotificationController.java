package com.example.UserService.Controller;

import com.example.UserService.Model.User;
import com.example.UserService.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
//@RequestMapping(path = "/users")
@CrossOrigin(origins = "*")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

}
