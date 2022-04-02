package com.drafael.Services;

import com.drafael.entities.Role;
import com.drafael.entities.User;
import com.drafael.entities.UserInRole;
import com.drafael.repositories.RoleRepository;
import com.drafael.repositories.UserInRoleRepository;
import com.drafael.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserInRoleServices {

    @Autowired
    private UserInRoleRepository userInRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;



    public UserInRole assingRole(Integer userId, Integer roleId) {
        Optional<User> resultUser = userRepository.findById(userId);
        Optional<Role> resultRole = roleRepository.findById(roleId);
        if (resultUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %d not found", userId));
        }
        else if(resultRole.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role %d not found", roleId));
        }
        else{
            UserInRole userInRole = new UserInRole();
            userInRole.setUser(resultUser.get());
            userInRole.setRole(resultRole.get());
            return userInRoleRepository.save(userInRole);
        }
    }

    public List<UserInRole> getUsersByRoleId(Integer roleId) {
        Optional<Role> resultRole = roleRepository.findById(roleId);
        if(resultRole.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role %d not found", roleId));
        }else{

         return userInRoleRepository.findAllByRoleId(roleId);
        }
    }
}
