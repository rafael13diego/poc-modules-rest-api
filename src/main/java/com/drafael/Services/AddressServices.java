package com.drafael.Services;

import com.drafael.entities.Address;
import com.drafael.entities.Profile;
import com.drafael.repositories.AddressRepository;
import com.drafael.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServices {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public List<Address> findAddressByProfileIdAndUser(Integer userId, Integer profileId) {
        return addressRepository.findByProfileId(userId, profileId);
    }

    public Address createAddress(Integer userId, Integer profileId, Address address) {
        Optional<Profile> result = profileRepository.findByUserIdAndProfileId(userId, profileId);
        if (result.isPresent()){
            address.setProfile(result.get());
            return addressRepository.save(address);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Profile not found for user %d and profile %d", userId, profileId));
        }
    }
}
