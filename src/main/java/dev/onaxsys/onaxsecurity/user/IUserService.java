package dev.onaxsys.onaxsecurity.user;


import dev.onaxsys.onaxsecurity.user.dto.AuthenticationResponse;
import dev.onaxsys.onaxsecurity.user.dto.RegisterRequest;

public interface IUserService {
    AuthenticationResponse createUser(RegisterRequest userProfile);

    UserProfile getUserProfile(String username);
}
