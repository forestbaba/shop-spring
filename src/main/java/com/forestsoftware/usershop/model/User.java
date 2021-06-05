package com.forestsoftware.usershop.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table (name = "users",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "phone"),
        @UniqueConstraint(columnNames = "email")
}
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotEmpty(message = "first name is required")
    @Size(max = 50)
    public String firstname;

    @NotEmpty(message = "last name can not be empty")
    @Size(max = 50)
    public String lastname;

//    @NotEmpty (message = "email is required")
    @Size(max = 50)
    public String email;
    @NotEmpty
    public String password;

   // @NotEmpty
    @Size(max = 50)
    public String apiKey;
    public String phone;

//    @ColumnDefault("")
    public String imageUrl;


//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "store_id", referencedColumnName = "id")
//    private Store store;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Store store;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Long balance;

    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @CreationTimestamp
    private Date dateCreated;

    @UpdateTimestamp
    private Date dateUpdated;
}
