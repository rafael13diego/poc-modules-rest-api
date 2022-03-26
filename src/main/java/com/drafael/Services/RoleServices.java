package com.drafael.Services;

//import com.drafael.entities.Role;
//import com.drafael.repositories.RoleRepository;
import com.drafael.entities.Role;
import com.drafael.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServices {

    @Autowired
    private RoleRepository repository;

    public List<Role> getRoles(){
        //Iterable<Role> findAll = repository.findAll();
        //return (List<Role>) findAll;
        return repository.findAll();
    }

    public Role createRole( Role role){
        return repository.save(role);
    }

    public Optional<Role> findByIdRole(Integer roleId){
        return repository.findById(roleId);
    }

    /*public Role updateRole( Integer roleId, Role role){
        Optional <Role> userUpdated= findByIdRole(roleId);
        if (userUpdated.isPresent()){
            userUpdated.get().setName(role.getName());
            return repository.save(userUpdated.get());
        }else{
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %d doesn't exists", roleId));
        }
    }*/
    public Role updateRole( Integer roleId, Role role){
        Optional<Role> result = repository.findById(roleId);
        if (result.isPresent()){
            return repository.save(role);
        }else{
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %d doesn't exists", roleId));
        }
    }

    public void deleteRole( Integer roleId){
        Optional<Role> result = findByIdRole(roleId);
        if (result.isPresent()){
            repository.delete(result.get());
        }else{
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %d doesn't exists", roleId));
        }
    }



}
