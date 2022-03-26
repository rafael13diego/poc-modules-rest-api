package com.drafael.Services;

import com.drafael.models.User;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServicesUsingList {

    @Autowired
    private Faker faker;

    private List<User> users = new ArrayList<>();

    @PostConstruct
    public void init(){
        for (int i = 0; i < 20; i++) {
            users.add( new User(faker.funnyName().name(), faker.name().username(),faker.dragonBall().character()));
        }

    }

    //public List<User> getUsers(){return users;}
    public List<User> getUsers(String startWith){
        if (startWith!=null){
            return users.stream().filter( u -> u.getUsername().startsWith(startWith)).collect(Collectors.toList());

        }else{
            return users;
        }
    }

    public User getUserByUserName(String username){
        return users.stream().filter( u -> u.getUsername().equals(username)).findFirst()
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User %s NOT Found", username)));
    }

    public User createUser(User user){
        if (users.stream().anyMatch( u -> u.getUsername().equals(user.getUsername()))){
            throw  new ResponseStatusException(HttpStatus.CONFLICT, String.format("User %s already exists", user.getUsername()));
        }
        users.add(user);
        return user;
    }

    public User updateUser(User user, String username) {
        User userToBeUpdated = getUserByUserName(username);
        userToBeUpdated.setNickName(user.getNickName());
        userToBeUpdated.setUsername(user.getUsername());
        userToBeUpdated.setPassword(user.getPassword());
        return userToBeUpdated;
    }

    public void deleteUser(String username){
        User userByName = getUserByUserName(username);
        users.remove(userByName);
    }

}
