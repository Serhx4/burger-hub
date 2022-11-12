package com.github.serhx4.service;

import com.github.serhx4.domain.ShippingInfo;
import com.github.serhx4.domain.User;

import java.util.Optional;

public interface ShippingInfoService {

    Optional<ShippingInfo> findById(Long id);

    Optional<ShippingInfo> findByUsername(String username);

    ShippingInfo saveAndFlush(ShippingInfo shippingInfo);

    void deleteById(Long id);
}
