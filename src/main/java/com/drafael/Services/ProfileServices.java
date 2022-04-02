package com.drafael.Services;

import com.drafael.entities.Profile;
import com.drafael.entities.User;
import com.drafael.repositories.ProfileRepository;
import com.drafael.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServices {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;


    public List<Profile> getProfiles() {
        return (List<Profile>) profileRepository.findAll();
    }

    public Profile getByUserAndProfileId(Integer userId, Integer profileId) {
        return profileRepository.findByUserIdAndProfileId(userId, profileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Profile not found for user %d and profile %d", userId, profileId)));
    }


    public Profile create(Integer userId, Profile profile) {
        Optional<User> result = userRepository.findById(userId);
        if (result.isPresent()) {
            profile.setUser(result.get());
            return profileRepository.save(profile);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %d not found", userId));
        }
    }

}
