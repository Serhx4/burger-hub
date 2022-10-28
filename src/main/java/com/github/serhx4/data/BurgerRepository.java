package com.github.serhx4.data;

import com.github.serhx4.domain.Burger;
import com.github.serhx4.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface BurgerRepository extends CrudRepository<Burger, Long> {

    Iterable<Burger> findAllByUser(User user);

    Iterable<Burger> findAllByUserUsername(String username);

}
