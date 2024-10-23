package com.example.JWT_Implementation.Configuration;

import com.example.JWT_Implementation.Entities.User;
import com.example.JWT_Implementation.Repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class customUserDetailIMPL implements UserDetailsService {

    @Autowired
    private userRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String Email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(Email);
        if(user==null){
            throw new UsernameNotFoundException("User Not Found");
        }
        return new customUserDetail(user);
    }
}
