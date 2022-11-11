package com.github.serhx4.service.implement;

import com.github.serhx4.data.ShippingInfoRepository;
import com.github.serhx4.domain.ShippingInfo;
import com.github.serhx4.domain.User;
import com.github.serhx4.service.ShippingInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ShippingInfoServiceImpl implements ShippingInfoService {

    private final ShippingInfoRepository shippingInfoRepository;

    @Override
    public Optional<ShippingInfo> findById(Long id) {
        return shippingInfoRepository.findById(id);
    }

    @Override
    public Optional<ShippingInfo> findByUser(User user) {
        return shippingInfoRepository.findByUser(user);
    }

    @Override
    public Optional<ShippingInfo> findByUsername(String username) {
        return shippingInfoRepository.findByUserUsername(username);
    }

    @Override
    public ShippingInfo saveAndFlush(ShippingInfo shippingInfo) {
       return shippingInfoRepository.save(shippingInfo);
    }

    @Override
    public void deleteById(Long id) {
        shippingInfoRepository.deleteById(id);
    }
}
