package dev.onaxsys.onaxsecurity.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import dev.onaxsys.onaxsecurity.user.dto.AuthenticationResponse;
import dev.onaxsys.onaxsecurity.user.dto.RegisterRequest;

@Service
public class UserService implements IUserService {
    
    private final IUserRepository _userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this._userRepository = userRepository;
    }

    public AuthenticationResponse createUser(RegisterRequest userProfile) {
        if(userProfile == null)
            return null;
        
        UserProfile obj = _userRepository.save(new UserProfile(){{
            setEmail(userProfile.getEmail());
            setPassword(userProfile.getPassword());
            setFirstName(userProfile.getFirstName());
            setLastName(userProfile.getLastName());
        }});
        if(obj == null)
            return null;
            AuthenticationResponse resp = new AuthenticationResponse();
            resp.setToken(obj.getEmail());
            return resp;
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
