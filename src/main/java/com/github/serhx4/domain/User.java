package com.github.serhx4.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @NotBlank(message = "Username is required")
    private String username;

    @Email(message = "Email is not valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "shipping_info_id")
    private ShippingInfo shippingInfo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Burger> burgerList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Order> orders = new ArrayList<>();

}
