package com.github.serhx4.data;

import com.github.serhx4.domain.ShippingInfo;
import com.github.serhx4.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShippingInfoRepository extends JpaRepository<ShippingInfo, Long> {
    Optional<ShippingInfo> findByUser(User user);
    Optional<ShippingInfo> findByUserUsername(String username);
}
