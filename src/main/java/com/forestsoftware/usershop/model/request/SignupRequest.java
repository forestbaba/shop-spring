package com.forestsoftware.usershop.model.request;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;


@Data //The @Data annotation comes with lombok to define object and
// handle setter and getter methods
public class SignupRequest {
   @NotEmpty(message = "lastname is required")
    @Size(min = 3, max = 30)
    private String lastname;

    @NotEmpty (message = "firstname is required")
    @Size(min = 3, max = 30)
    private String firstname;

    @NotEmpty (message = "mobile number can not be empty")
    @Size(min = 11, max = 30)
    private String mobile;

    private String image;

    @NotEmpty (message = "email can not can not be empty")
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotEmpty (message = "password is required")
    @Size(min = 6, max = 40)
    private String password;


//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Set<String> getRole() {
//        return this.role;
//    }
//
//    public String getFirstName() {
//        return firstname;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstname = firstName;
//    }
//
//    public String getLastName() {
//        return lastname;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastname = lastName;
//    }
//
//    public String getMobile() {
//        return mobile;
//    }
//
//    public void setMobile(String mobile) {
//        this.mobile = mobile;
//    }
//
//    public void setRole(Set<String> role) {
//        this.role = role;
//    }
}
