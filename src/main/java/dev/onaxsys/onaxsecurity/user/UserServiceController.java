package dev.onaxsys.onaxsecurity.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class UserServiceController { //extends BaseController {
    

    private final IUserService _userService;

    @Autowired
    public UserServiceController(IUserService userService) {
        this._userService = userService;
    }


    @GetMapping(path="user/{username}")
    public String getUserProfile(@PathVariable String username) {
        return String.format("Hello %s",username); //_userService.getUserProfile(username);
    }

}
