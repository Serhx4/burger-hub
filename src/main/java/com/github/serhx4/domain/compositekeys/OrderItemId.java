package com.github.serhx4.domain.compositekeys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemId implements Serializable {

    private Long order;
    private Long id;

}
