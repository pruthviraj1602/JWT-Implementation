package com.example.JWT_Implementation.Repositories;

import com.example.JWT_Implementation.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface userRepository extends JpaRepository<User,Integer> {

    @Query(nativeQuery = true,value = "SELECT * FROM user WHERE email=?")
    public User getUserByEmail(String Email);
    public User getUserById(Integer id);

//    public User getUserByEmailAndPassword(String Email,String Password);
}
