package com.github.serhx4.data;

import com.github.serhx4.domain.PromoCode;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PromoCodeRepository extends CrudRepository<PromoCode, Long> {
    Optional<PromoCode> findByCode(String code);
}
