package com.example.JWT_Implementation.Controller;

import com.example.JWT_Implementation.Entities.User;
import com.example.JWT_Implementation.Services.IMPL.userServiceIMPL;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class adminController {

    @Autowired
    private userServiceIMPL userServiceIMPL;

    @PostConstruct
    public void saveAdmin(){
        User user=new User();
        user.setId(1);
        user.setName("Admin");
        user.setEmail("admin12@gmail.com");
        user.setPassword("1234");
        user.setRole("ROLE_ADMIN");
        user.setEnable(true);
        userServiceIMPL.saveUser(user);
    }

    @GetMapping("/allUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser = userServiceIMPL.getAllUser();
        if (allUser!=null){
            return ResponseEntity.status(HttpStatus.OK).body(allUser);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("/")
    public String session(HttpSession session){
        return "SESSION Id: "+session.getId();
    }
}
