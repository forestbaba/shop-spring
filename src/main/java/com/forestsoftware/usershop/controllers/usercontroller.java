package com.forestsoftware.usershop.controllers;

import com.forestsoftware.usershop.model.ERole;
import com.forestsoftware.usershop.model.MessageResponse;
import com.forestsoftware.usershop.model.Role;
import com.forestsoftware.usershop.model.User;
import com.forestsoftware.usershop.model.request.JwtResponse;
import com.forestsoftware.usershop.model.request.SignupRequest;
import com.forestsoftware.usershop.model.request.LoginRequest;
import com.forestsoftware.usershop.repository.RoleRepository;
import com.forestsoftware.usershop.repository.UserRepository;
import com.forestsoftware.usershop.security.jwt.JwtUtils;
import com.forestsoftware.usershop.service.ErrorValidationService;
import com.forestsoftware.usershop.service.UserDetailsImpl;
import com.forestsoftware.usershop.service.UserService;
import com.forestsoftware.usershop.utility.Workerclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
public class usercontroller {

    @Autowired
    UserRepository userRepository;

//    @Autowired
//    PasswordEncoder encoder;
//    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private ErrorValidationService errorValidationService;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    Workerclass workerclass;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserService userService;




    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid  @RequestBody SignupRequest signupRequest, BindingResult bindingResult) {

        System.out.println("======"+signupRequest.getEmail());
        User user = new User();

//        user.setEmail(signupRequest.getEmail());
//        ResponseEntity<?>errorMap = errorValidationService.errorHandler(bindingResult);
//        if(errorMap != null) return errorMap;
//        if (userRepository.existsByEmail(reqBody.getEmail())) {
//            return ResponseEntity.badRequest().body(new MessageResponse("Email already taken"));
//        }
//        if (userRepository.existsByPhone(reqBody.getMobile())) {
//            return ResponseEntity.badRequest().body(new MessageResponse("Mobile no already taken"));
//        }

        User user1 = userService.createUser(user,signupRequest);
        return new ResponseEntity<User>(user1,HttpStatus.CREATED);

    }



    @PostMapping("/signin")
    public ResponseEntity<?> signin (@Valid @RequestBody LoginRequest request ){
        Map<String, Object> response = new HashMap<>();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));

    }
}
