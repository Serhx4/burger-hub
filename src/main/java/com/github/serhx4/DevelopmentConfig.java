package com.github.serhx4;

import com.github.serhx4.data.BurgerRepository;
import com.github.serhx4.data.IngredientRepository;
import com.github.serhx4.data.PromoCodeRepository;
import com.github.serhx4.data.UserRepository;
import com.github.serhx4.domain.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import static com.github.serhx4.domain.Ingredient.Type.*;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner preLoader(final IngredientRepository ingredientRepo,
                                       final BurgerRepository burgerRepo,
                                       UserRepository repo, PromoCodeRepository promoCodeRepo,
                                       PasswordEncoder encoder) {
        return strings -> {

            ShippingInfo chefShippingInfo = ShippingInfo.builder()
                    .firstName("Chef")
                    .lastName("Burger Hub")
                    .phone("+12334495788")
                    .country("USA")
                    .state("California")
                    .city("San Francisco")
                    .street("123 Main St")
                    .apartment("11 Apt")
                    .zip("123")
                    .build();

            User burgerChef = User.builder()
                    .username("chef")
                    .email("chef@burgerhub.com")
                    .password(encoder.encode("p"))
                    .shippingInfo(chefShippingInfo)
                    .build();
            repo.save(burgerChef);

            // Ingredients
            final Ingredient sesameSeedBun = new Ingredient("SBUN", "Sesame seed bun", BigDecimal.valueOf(0.19),
                    "image/ingredients/seasame_bun.png", BREAD);
            final Ingredient toastedBread = new Ingredient("BRED", "Toasted bread", BigDecimal.valueOf(0.09),
                    "image/ingredients/bread.png", BREAD);
            final Ingredient beefPatty = new Ingredient("BEEF", "Beef patty", BigDecimal.valueOf(2.5),
                    "image/ingredients/beef_patty.png", PROTEIN);
            final Ingredient lambPatty = new Ingredient("LAMB", "Lamb patty", BigDecimal.valueOf(2.5),
                    "image/ingredients/lamb_patty.png", PROTEIN);
            final Ingredient salmon = new Ingredient("SALM", "Salmon fillet", BigDecimal.valueOf(3.5),
                    "image/ingredients/salmon.png", PROTEIN);
            final Ingredient shrimps = new Ingredient("SHRM", "Shrimp patty", BigDecimal.valueOf(3.5),
                    "image/ingredients/shrimps.png", PROTEIN);
            final Ingredient crunchyPickles = new Ingredient("PCKL", "Crunchy pickles", BigDecimal.valueOf(0.1),
                    "image/ingredients/pickles.png", VEGGIES);
            final Ingredient juicyTomatoes = new Ingredient("TOMT", "Juicy tomatoes", BigDecimal.valueOf(0.2),
                    "image/ingredients/tomatoes.png", VEGGIES);
            final Ingredient crispLettuce = new Ingredient("LETC", "Crisp lettuce", BigDecimal.valueOf(0.2),
                    "image/ingredients/lettuce.png", VEGGIES);
            final Ingredient redOnions = new Ingredient("ONIO", "Sliced red onions", BigDecimal.valueOf(0.1),
                    "image/ingredients/red_onion.png", VEGGIES);
            final Ingredient cucumber = new Ingredient("CUCM", "Sliced cucumber", BigDecimal.valueOf(0.1),
                    "image/ingredients/cucumber.png", VEGGIES);
            final Ingredient mushrooms = new Ingredient("MUSH", "Grilled mushrooms", BigDecimal.valueOf(0.2),
                    "image/ingredients/mushrooms.png", VEGGIES);
            final Ingredient chilliPepper = new Ingredient("CHIL", "Chili pepper", BigDecimal.valueOf(0.1),
                    "image/ingredients/chili_pepper.png", VEGGIES);
            final Ingredient sweetPepper = new Ingredient("SPEP", "Sweet pepper", BigDecimal.valueOf(0.1),
                    "image/ingredients/sweet_pepper.png", VEGGIES);
            final Ingredient yellowMustard = new Ingredient("MUST", "Yellow mustard", BigDecimal.valueOf(0.2),
                    "image/ingredients/mustard.png", SAUCE);
            final Ingredient tomatoKetchup = new Ingredient("KTCH", "Tomato ketchup", BigDecimal.valueOf(0.2),
                    "image/ingredients/ketchup.png", SAUCE);
            final Ingredient creammyMayonnaise = new Ingredient("MAYO", "Creammy mayonnaise", BigDecimal.valueOf(0.2),
                    "image/ingredients/mayonnaise.png", SAUCE);
            final Ingredient bbqSauce = new Ingredient("BBQS", "BBQ sauce", BigDecimal.valueOf(0.2),
                    "image/ingredients/bbq.png", SAUCE);
            final Ingredient cheddarCheese = new Ingredient("CHED", "Cheddar cheese", BigDecimal.valueOf(0.3),
                    "image/ingredients/cheddar.png", CHEESE);
            final Ingredient mozarellaCheese = new Ingredient("MOZA", "Mozarella cheese", BigDecimal.valueOf(0.3),
                    "image/ingredients/mozarella.png", CHEESE);
            final Ingredient crispyBacon = new Ingredient("BCON", "Crispy bacon", BigDecimal.valueOf(0.3),
                    "image/ingredients/bacon.png", TOPPING);
            final Ingredient friedEgg = new Ingredient("FREG", "Fried egg", BigDecimal.valueOf(0.3),
                    "image/ingredients/egg.png", TOPPING);

            ingredientRepo.save(sesameSeedBun);
            ingredientRepo.save(toastedBread);
            ingredientRepo.save(beefPatty);
            ingredientRepo.save(lambPatty);
            ingredientRepo.save(shrimps);
            ingredientRepo.save(salmon);
            ingredientRepo.save(crunchyPickles);
            ingredientRepo.save(cucumber);
            ingredientRepo.save(redOnions);
            ingredientRepo.save(juicyTomatoes);
            ingredientRepo.save(crispLettuce);
            ingredientRepo.save(mushrooms);
            ingredientRepo.save(chilliPepper);
            ingredientRepo.save(sweetPepper);
            ingredientRepo.save(yellowMustard);
            ingredientRepo.save(tomatoKetchup);
            ingredientRepo.save(bbqSauce);
            ingredientRepo.save(creammyMayonnaise);
            ingredientRepo.save(cheddarCheese);
            ingredientRepo.save(mozarellaCheese);
            ingredientRepo.save(crispyBacon);
            ingredientRepo.save(friedEgg);

            // Burgers

            Burger hamburger = Burger.builder()
                    .name("Hamburger")
                    .imageUri("image/burger/hamburger.png")
                    .user(burgerChef)
                    .ingredients(new ArrayList<>(Arrays.asList(
                            sesameSeedBun,
                            beefPatty,
                            crunchyPickles,
                            yellowMustard,
                            tomatoKetchup
                    )))
                    .build();
            burgerRepo.save(hamburger);

            Burger cheeseburger = Burger.builder()
                    .name("Cheeseburger")
                    .imageUri("image/burger/cheeseburger.png")
                    .user(burgerChef)
                    .ingredients(new ArrayList<>(Arrays.asList(
                            sesameSeedBun,
                            beefPatty,
                            cheddarCheese,
                            crunchyPickles,
                            yellowMustard,
                            tomatoKetchup
                    )))
                    .build();
            burgerRepo.save(cheeseburger);

            Burger baconCheeseburger = Burger.builder()
                    .name("Bacon Cheeseburger")
                    .imageUri("image/burger/bacon cheeseburger.png")
                    .user(burgerChef)
                    .ingredients(new ArrayList<>(Arrays.asList(
                            sesameSeedBun,
                            beefPatty,
                            cheddarCheese,
                            crunchyPickles,
                            yellowMustard,
                            tomatoKetchup,
                            crispyBacon
                    )))
                    .build();
            burgerRepo.save(baconCheeseburger);

            Burger whopper = Burger.builder()
                    .name("Whopper")
                    .imageUri("image/burger/whopper.png")
                    .user(burgerChef)
                    .ingredients(new ArrayList<>(Arrays.asList(
                            sesameSeedBun,
                            beefPatty,
                            juicyTomatoes,
                            crispLettuce,
                            creammyMayonnaise,
                            tomatoKetchup,
                            crunchyPickles,
                            redOnions
                    )))
                    .build();
            burgerRepo.save(whopper);

            Burger salmonBurger = Burger.builder()
                    .name("Wild Salmon")
                    .imageUri("image/burger/salmon.png")
                    .user(burgerChef)
                    .ingredients(new ArrayList<>(Arrays.asList(
                            sesameSeedBun,
                            salmon,
                            friedEgg,
                            sweetPepper,
                            cucumber,
                            redOnions,
                            creammyMayonnaise
                    )))
                    .build();
            burgerRepo.save(salmonBurger);

            Burger shrimpBurger = Burger.builder()
                    .name("Shrimp Burger")
                    .imageUri("image/burger/shrimp.png")
                    .user(burgerChef)
                    .ingredients(new ArrayList<>(Arrays.asList(
                            sesameSeedBun,
                            shrimps,
                            friedEgg,
                            sweetPepper,
                            crispLettuce,
                            bbqSauce
                    )))
                    .build();
            burgerRepo.save(shrimpBurger);

            // Promo Codes

            PromoCode iLoveMac = PromoCode.builder()
                    .code("#ILOVEMAC")
                    .discount(BigDecimal.valueOf(0.05))
                    .build();
            promoCodeRepo.save(iLoveMac);

            PromoCode mcDonaldsForever = PromoCode.builder()
                    .code("#MCDONALDSFOREVER")
                    .discount(BigDecimal.valueOf(0.01))
                    .build();
            promoCodeRepo.save(mcDonaldsForever);

            PromoCode iLoveBurgerHub = PromoCode.builder()
                    .code("#ILOVEBURGERHUB")
                    .discount(BigDecimal.valueOf(0.2))
                    .build();
            promoCodeRepo.save(iLoveBurgerHub);

            PromoCode iLoveBurgers = PromoCode.builder()
                    .code("#ILOVEBURGERS")
                    .discount(BigDecimal.valueOf(0.1))
                    .build();
            promoCodeRepo.save(iLoveBurgers);

            PromoCode iWantToEat = PromoCode.builder()
                    .code("#IWANTTOEAT")
                    .discount(BigDecimal.valueOf(0.3))
                    .build();
            promoCodeRepo.save(iWantToEat);

            PromoCode giveMeSomeMeal = PromoCode.builder()
                    .code("#GIVEMESOMEMEAL")
                    .discount(BigDecimal.valueOf(0.5))
                    .build();
            promoCodeRepo.save(giveMeSomeMeal);
        };
    }

}
