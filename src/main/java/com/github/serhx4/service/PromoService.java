package com.github.serhx4.service;

import com.github.serhx4.domain.PromoCode;

import java.util.Optional;

public interface PromoService {

    Iterable<PromoCode> findAll();

    Optional<PromoCode> findByCode(String code);
}
