package com.example.JWT_Implementation.Services.IMPL;

import com.example.JWT_Implementation.Entities.User;
import com.example.JWT_Implementation.Repositories.userRepository;
import com.example.JWT_Implementation.Services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userServiceIMPL implements userService {

    @Autowired
    private userRepository userRepository;

    @Autowired
    private JWTService service;


    @Autowired
    AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public User saveUser(User user) {
       user.setPassword(passwordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.getUserById(id);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public String verify(User user) {
      Authentication authentication=authenticationManager
              .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
      if(authentication.isAuthenticated()){
          return service.generateToken(user.getEmail());
      }
      return "Fail";
    }
}
