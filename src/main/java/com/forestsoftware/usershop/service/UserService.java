package com.forestsoftware.usershop.service;


import com.forestsoftware.usershop.exceptions.EmailAlreadyExistException;
import com.forestsoftware.usershop.exceptions.PhoneAlreadyExistException;
import com.forestsoftware.usershop.model.ERole;
import com.forestsoftware.usershop.model.Role;
import com.forestsoftware.usershop.model.User;
import com.forestsoftware.usershop.model.request.SignupRequest;
import com.forestsoftware.usershop.repository.RoleRepository;
import com.forestsoftware.usershop.repository.UserRepository;
import com.forestsoftware.usershop.utility.Workerclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private Workerclass workerclass;

    @Autowired
    private RoleRepository roleRepository;

    public User createUser(User user, SignupRequest signupRequest){


        userRepository.findByEmail(signupRequest.getEmail());

        try {
            user.setEmail(signupRequest.getEmail());
            user.setFirstname(signupRequest.getFirstname());
            user.setLastname(signupRequest.getLastname());
            user.setPassword(bCryptPasswordEncoder.encode(signupRequest.getPassword()));
            user.setImageUrl(workerclass.getGravatar80pxUrl(signupRequest.getEmail()));
            user.setApiKey(workerclass.randomString(50));

           // user.setRoles(getUserRole(user, signupRequest));
           return userRepository.save(user);

        }catch (EmailAlreadyExistException e){

            throw new EmailAlreadyExistException("Email '"+user.getEmail()+" ' already exist");
        }catch(NullPointerException ex){
            System.out.println("ERR: ====="+ex.getCause());
            throw new EmailAlreadyExistException("Email '"+user.getEmail()+" ' already exist");

        }
    }

    public Set<Role>getUserRole(User user, SignupRequest signupRequest){

        Set<String> stRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if(user.getRoles() == null){

            Role role = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new NullPointerException("Error: Role is not found"));
            System.out.println("MMM;          "+role);
            roles.add(role);

        }else {
            stRoles.forEach(role -> {

               switch (role) {

                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role not found"));
                        roles.add(adminRole);

                        break;

                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR).orElseThrow(() -> new RuntimeException("Error: Role not found"));
                        roles.add(modRole);
                        break;
                    default:
                        Role roleanon = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role not found"));
                        roles.add(roleanon);
                }

            });
        }
//        user.setRoles(roles);
        return roles;
    }
}
