package dev.onaxsys.onaxsecurity.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    
    private final IUserRepository _userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this._userRepository = userRepository;
    }

    public UserProfile createUser(UserProfile userProfile) {
        return _userRepository.save(userProfile);
    }

    public UserProfile getUserProfile(String username) {

        //#region Using Optional. This also works by inferencing. This uses JPA Repository 'magic' methods to find by email based on the field name. BUT it is not recommended
        //Optional<UserProfile> objResp =  _userRepository.findByEmail(username);
        //#endregion
        UserProfile objResp =  _userRepository.findBy(Example.of(UserProfile.builder().email(username).build())
                                        ,query -> query.first()).orElse(null);
        return objResp;
    }
}
