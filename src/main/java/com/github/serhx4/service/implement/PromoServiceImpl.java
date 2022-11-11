package com.github.serhx4.service.implement;

import com.github.serhx4.data.PromoCodeRepository;
import com.github.serhx4.domain.PromoCode;
import com.github.serhx4.service.PromoService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PromoServiceImpl implements PromoService {

    private final PromoCodeRepository promoCodeRepository;

    public PromoServiceImpl(PromoCodeRepository promoCodeRepository) {
        this.promoCodeRepository = promoCodeRepository;
    }

    @Override
    public Iterable<PromoCode> findAll() {
        return promoCodeRepository.findAll();
    }

    @Override
    public Optional<PromoCode> findByCode(String code) {
        return promoCodeRepository.findByCode(code);
    }
}
