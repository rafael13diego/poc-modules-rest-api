package com.drafael.repositories;

import com.drafael.entities.Profile;
import com.drafael.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends CrudRepository<User,Integer> {


}
