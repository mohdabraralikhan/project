package org.nicecharity.crowdfunding.user;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;


    private final AuthenticationManager authenticationManager;
    public UserController(AuthenticationManager authenticationManager){
    this.authenticationManager = authenticationManager;
}

        @PostMapping("/login")
        public ResponseEntity<User> login(@RequestBody User user) {
        Authentication authenticationRequest = 
        UsernamePasswordAuthenticationToken
        .unauthenticated(user.getUsername(),user.getPassword());

        Authentication authenticationResponse =
			this.authenticationManager.authenticate(authenticationRequest);

            UserDetails userDetails = (UserDetails) authenticationResponse.getPrincipal();
            User existingUser = userService.getByUsername(userDetails.getUsername());
        return ResponseEntity.ok(existingUser);

        }


        @PostMapping("/register")
        public ResponseEntity<String> register(@RequestBody User user) {
            userService.registerUser( user);
            return ResponseEntity.ok().build();
    }

    
}
