package com.forestsoftware.usershop.model.request;

import com.forestsoftware.usershop.model.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;


@Data
public class StoreObjectRequest {


    @NotEmpty(message = "name is required")
    private String name;
    @NotEmpty(message = "address is required")
//    @Size(min = 11, max = 30)
    private String address;

//    @DecimalMin(value = "0.1", inclusive = true)
//    @DecimalMax(value = "9.9", inclusive = true)
    private BigDecimal longitude;

//    @DecimalMin(value = "0.1", inclusive = true)
//    @DecimalMax(value = "9.9", inclusive = true)
    private BigDecimal latitude;

    private String image;
    private Double balance;

}
