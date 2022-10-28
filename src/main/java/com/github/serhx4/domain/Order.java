package com.github.serhx4.domain;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Data
@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private ShippingInfo shippingInfo;

    @ManyToOne
    private PromoCode promoCode;

    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CCV")
    private String ccCVV;


    private LocalDate placedAt;
    @PrePersist
    private void prePersist() {
        this.placedAt = LocalDate.now();
    }

    private BigDecimal total;

    @ElementCollection
    @Column(name = "quantity")
    private Map<Burger, Integer> burgers;

}
