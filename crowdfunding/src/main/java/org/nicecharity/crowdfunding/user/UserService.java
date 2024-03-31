package org.nicecharity.crowdfunding.user;

import org.nicecharity.crowdfunding.user.role.Role;
import org.nicecharity.crowdfunding.user.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;


    public User registerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName("USER");
        
        if(userRole ==null){
            userRole = new Role("USER");
            roleRepository.save(userRole);
        }
        user.getRoles().add(userRole);
        return userRepository.save(user);
    }


    public User getByUsername(String username) {
       return userRepository.findByUsername(username);
    }

    
}
