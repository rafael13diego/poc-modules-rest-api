package com.drafael.Services;

import com.drafael.entities.User;
import com.drafael.repositories.UserRepository;
import com.sun.xml.bind.v2.TODO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    public static final Logger log = LoggerFactory.getLogger(UserServices.class);

    public Page<User> getUsers(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
        //return userRepository.findAll();
    }

    /*public List<User> getUsers(){
        //return userRepository.findAll();
    }*/

    public User getUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %d not found", userId)));

    }

    @Cacheable("users")
    public User getUserByUsername(String username) {
        log.info("Getting user by Username {}", username);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userRepository.findByUsername(username).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %s not found", username)));

    }

    public User getUserByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %s not registred", username)));

    }

    @CacheEvict("users")
    public void deleteUserByUsername(String username) {
        //TODO: usanddo el metodo de getUser de esta clase
        User user = getUserByUsername(username);
        userRepository.delete(user);
    }


}
