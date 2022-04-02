package com.drafael.repositories;

import com.drafael.entities.Profile;
import com.drafael.entities.UserInRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInRoleRepository extends CrudRepository<UserInRole, Integer> {

    List<UserInRole> findAllByRoleId(Integer roleId);
}
