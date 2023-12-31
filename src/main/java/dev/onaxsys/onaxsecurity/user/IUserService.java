package dev.onaxsys.onaxsecurity.user;

public interface IUserService {
    UserProfile createUser(UserProfile userProfile);

    UserProfile getUserProfile(String username);
}
