package com.github.serhx4.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "promocodes")
public class PromoCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private BigDecimal discount;
}
