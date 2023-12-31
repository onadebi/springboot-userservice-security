package dev.onaxsys.onaxsecurity.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.onaxsys.onaxsecurity.user.dto.AuthenticationResponse;
import dev.onaxsys.onaxsecurity.user.dto.RegisterRequest;
import dev.onaxsys.onaxsecurity.user.dto.AuthenticationRequest;


@RestController
@RequestMapping("/api/v1/auth")
public class UserServiceController { //extends BaseController {
    

    private final IUserService _userService;

    @Autowired
    public UserServiceController(UserService userService) {
        this._userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(_userService.createUser(request));
    }

    // @PostMapping("/authenticate")
    // public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
    //     // return _userService.register(request);
    // }


    @GetMapping(path="user/{username}")
    public String getUserProfile(@PathVariable String username) {
        return String.format("Hello %s",username); //_userService.getUserProfile(username);
    }

}
