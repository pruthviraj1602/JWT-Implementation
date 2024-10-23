package com.example.JWT_Implementation.Controller;

import com.example.JWT_Implementation.Entities.User;
import com.example.JWT_Implementation.Services.IMPL.userServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class publicController {

    @Autowired
    private userServiceIMPL userServiceIMPL;

    @PostMapping("/register-user")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        user.setEnable(false);
        user.setRole("ROLE_USER");
        User user1 = userServiceIMPL.saveUser(user);
        if(user1!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(user1);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        return userServiceIMPL.verify(user);
    }
}
