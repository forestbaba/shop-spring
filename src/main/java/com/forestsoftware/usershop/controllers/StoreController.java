package com.forestsoftware.usershop.controllers;

import com.forestsoftware.usershop.exceptions.ResourceNotFoundException;
import com.forestsoftware.usershop.model.MessageResponse;
import com.forestsoftware.usershop.model.Store;
import com.forestsoftware.usershop.model.User;
import com.forestsoftware.usershop.model.request.StoreObjectRequest;
import com.forestsoftware.usershop.repository.StoreRepository;
import com.forestsoftware.usershop.repository.UserRepository;
import com.forestsoftware.usershop.service.UserDetailsImpl;
import com.forestsoftware.usershop.utility.Workerclass;
import jdk.management.resource.internal.ResourceNatives;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/store")
public class StoreController {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    Workerclass workerclass;

    @PostMapping("/create")
    public ResponseEntity<?> createStore(@Valid @RequestBody StoreObjectRequest storeRequest, Authentication authentication){

        UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
        System.out.println("+++++++++++++"+userDetails);
        Map<Object, Object> storeResponse = new HashMap<>();

        Store store = new Store();
        store.setName(storeRequest.getName());
        store.setAddress(storeRequest.getAddress());
        store.setLongitude(storeRequest.getLongitude());
        store.setLatitude(storeRequest.getLatitude());
        store.setBalance(0.0);
        store.setImage(workerclass.getGravatar80pxUrl(userDetails.getEmail()));

        return userRepository.findById(userDetails.getId()).map(user ->{
            System.out.println("++++++44+++++++"+user);

            store.setUser(user);
            storeRepository.save(store);
            storeResponse.put("error", false);
            storeResponse.put("message","store created successfully");
            storeResponse.put("store", store);
           return ResponseEntity.ok(storeResponse);
        }).orElseThrow(()->new ResourceNotFoundException("User does not found"));

    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('USER')")

    public ResponseEntity<?> updateStore(@Valid @RequestBody StoreObjectRequest storeObjectRequest,
                                         @PathVariable Long id, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Map<Object, Object> updateResponse = new HashMap<>();

        Optional<Store> store = storeRepository.findById(id);
        Optional<User> storeUser = userRepository.findById(userDetails.getId());

        System.out.println("store user: ===>" + store.get().getUser().getId());
        System.out.println("Main user user: ===>" + storeUser.get().getId());


        if (!store.get().getUser().getId().equals(storeUser.get().getId())) {

            updateResponse.put("error", true);
            updateResponse.put("message", "You are not unthorized to update this store");
            return new ResponseEntity<>(new MessageResponse(updateResponse.toString()), HttpStatus.UNAUTHORIZED);
        }

        Store store1 = new Store();
        store1.setAddress(storeObjectRequest.getAddress());
        store1.setName(storeObjectRequest.getName());
        store1.setAvailable(store1.getAvailable() == null ? true : store1.getAvailable());
        store1.setLongitude(storeObjectRequest.getLongitude() == null ? BigDecimal.valueOf(0.01d) : storeObjectRequest.getLongitude());
        store1.setLatitude(storeObjectRequest.getLatitude() == null ? BigDecimal.valueOf(0) : storeObjectRequest.getLatitude());
        return new ResponseEntity(storeRepository.save(store1), HttpStatus.OK);

    }
}
