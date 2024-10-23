package com.example.JWT_Implementation.Services;

import com.example.JWT_Implementation.Entities.User;

import java.util.List;

public interface userService {

    public User saveUser(User user);

    public User getUserById(Integer id);

    public List<User> getAllUser();


    public String verify(User user);
}
