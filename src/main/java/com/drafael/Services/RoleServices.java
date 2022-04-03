package com.drafael.Services;

//import com.drafael.entities.Role;
//import com.drafael.repositories.RoleRepository;

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
public class RoleServices {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserInRoleRepository userInRoleRepository;

    public List<Role> getRoles() {
        //Iterable<Role> findAll = roleRepository.findAll();
        //return (List<Role>) findAll;
        return roleRepository.findAll();
    }

    public List<User> getUsersByRole(String roleName){
        //TODO: Hacer validación del nombre del rol
        return userInRoleRepository.findUserByRoleName(roleName);
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Optional<Role> findByIdRole(Integer roleId) {
        return roleRepository.findById(roleId);
    }

    /*public Role updateRole( Integer roleId, Role role){
        Optional <Role> userUpdated= findByIdRole(roleId);
        if (userUpdated.isPresent()){
            userUpdated.get().setName(role.getName());
            return roleRepository.save(userUpdated.get());
        }else{
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %d doesn't exists", roleId));
        }
    }*/
    public Role updateRole(Integer roleId, Role role) {
        Optional<Role> result = roleRepository.findById(roleId);
        if (result.isPresent()) {
            return roleRepository.save(role);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %d doesn't exists", roleId));
        }
    }

    public void deleteRole(Integer roleId) {
        Optional<Role> result = findByIdRole(roleId);
        if (result.isPresent()) {
            roleRepository.delete(result.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %d doesn't exists", roleId));
        }
    }


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
}
