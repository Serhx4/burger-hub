package com.github.serhx4;

import com.github.serhx4.data.*;
import com.github.serhx4.domain.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;

import static com.github.serhx4.domain.Ingredient.Type.*;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner preLoader(final IngredientRepository ingredientRepo,
                                       final BurgerRepository burgerRepo,
                                       UserRepository repo, ShippingInfoRepository shippingRepo, PromoCodeRepository promoCodeRepo,
                                       PasswordEncoder encoder) {
        return strings -> {

            User burgerChef =
                    new User(null, "chef", "chef@burgerhub.com",
                            encoder.encode("p"), null, null);
            repo.save(burgerChef);

            ShippingInfo chefShippingInfo = new ShippingInfo();
            chefShippingInfo.setFirstName("Chef");
            chefShippingInfo.setLastName("Burger Hub");
            chefShippingInfo.setPhone("+12334495788");
            chefShippingInfo.setUser(burgerChef);
            chefShippingInfo.setCountry("USA");
            chefShippingInfo.setState("California");
            chefShippingInfo.setCity("San Francisco");
            chefShippingInfo.setStreet("123 Main St");
            chefShippingInfo.setApartment("11 Apt");
            chefShippingInfo.setZip("123");
            shippingRepo.save(chefShippingInfo);

            // Ingredients
            final Ingredient sesameSeedBun = new Ingredient("SBUN", "Sesame seed bun", BigDecimal.valueOf(0.19),
                            "image/ingredients/seasame_bun.png", BREAD, null);
            final Ingredient toastedBread = new Ingredient("BRED", "Toasted bread", BigDecimal.valueOf(0.09),
                    "image/ingredients/bread.png", BREAD, null);
            final Ingredient beefPatty = new Ingredient("BEEF", "Beef patty", BigDecimal.valueOf(2.5),
                            "image/ingredients/beef_patty.png", PROTEIN, null);
            final Ingredient lambPatty = new Ingredient("LAMB", "Lamb patty", BigDecimal.valueOf(2.5),
                            "image/ingredients/lamb_patty.png", PROTEIN, null);
            final Ingredient salmon = new Ingredient("SALM", "Salmon fillet", BigDecimal.valueOf(3.5),
                    "image/ingredients/salmon.png", PROTEIN, null);
            final Ingredient shrimps = new Ingredient("SHRM", "Shrimp patty", BigDecimal.valueOf(3.5),
                    "image/ingredients/shrimps.png", PROTEIN, null);
            final Ingredient crunchyPickles = new Ingredient("PCKL", "Crunchy pickles", BigDecimal.valueOf(0.1),
                    "image/ingredients/pickles.png", VEGGIES, null);
            final Ingredient juicyTomatoes = new Ingredient("TOMT", "Juicy tomatoes", BigDecimal.valueOf(0.2),
                    "image/ingredients/tomatoes.png", VEGGIES, null);
            final Ingredient crispLettuce = new Ingredient("LETC", "Crisp lettuce", BigDecimal.valueOf(0.2),
                    "image/ingredients/lettuce.png", VEGGIES, null);
            final Ingredient redOnions = new Ingredient("ONIO","Sliced red onions", BigDecimal.valueOf(0.1),
                    "image/ingredients/red_onion.png", VEGGIES, null);
            final Ingredient cucumber = new Ingredient("CUCM", "Sliced cucumber", BigDecimal.valueOf(0.1),
                    "image/ingredients/cucumber.png", VEGGIES, null);
            final Ingredient mushrooms = new Ingredient("MUSH", "Grilled mushrooms", BigDecimal.valueOf(0.2),
                    "image/ingredients/mushrooms.png", VEGGIES, null);
            final Ingredient chilliPepper = new Ingredient("CHIL", "Chili pepper", BigDecimal.valueOf(0.1),
                    "image/ingredients/chili_pepper.png", VEGGIES, null);
            final Ingredient sweetPepper = new Ingredient("SPEP", "Sweet pepper", BigDecimal.valueOf(0.1),
                    "image/ingredients/sweet_pepper.png", VEGGIES, null);
            final Ingredient yellowMustard = new Ingredient("MUST", "Yellow mustard", BigDecimal.valueOf(0.2),
                    "image/ingredients/mustard.png", SAUCE, null);
            final Ingredient tomatoKetchup = new Ingredient("KTCH", "Tomato ketchup", BigDecimal.valueOf(0.2),
                    "image/ingredients/ketchup.png", SAUCE, null);
            final Ingredient creammyMayonnaise = new Ingredient("MAYO", "Creammy mayonnaise", BigDecimal.valueOf(0.2),
                    "image/ingredients/mayonnaise.png", SAUCE, null);
            final Ingredient bbqSauce = new Ingredient("BBQS", "BBQ sauce", BigDecimal.valueOf(0.2),
                    "image/ingredients/bbq.png", SAUCE, null);
            final Ingredient cheddarCheese = new Ingredient("CHED", "Cheddar cheese", BigDecimal.valueOf(0.3),
                    "image/ingredients/cheddar.png", CHEESE, null);
            final Ingredient mozarellaCheese = new Ingredient("MOZA", "Mozarella cheese", BigDecimal.valueOf(0.3),
                    "image/ingredients/mozarella.png", CHEESE, null);
            final Ingredient crispyBacon = new Ingredient("BCON", "Crispy bacon", BigDecimal.valueOf(0.3),
                    "image/ingredients/bacon.png", TOPPING, null);
            final Ingredient friedEgg = new Ingredient("FREG", "Fried egg", BigDecimal.valueOf(0.3),
                    "image/ingredients/egg.png", TOPPING, null);

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
            Burger hamburger = new Burger();
            hamburger.setName("Hamburger");
            hamburger.setImageUri("image/burger/hamburger.png");
            hamburger.setUser(burgerChef);
            hamburger.setIngredients(new ArrayList<Ingredient>(){{
                add(sesameSeedBun);
                add(beefPatty);
                add(crunchyPickles);
                add(yellowMustard);
                add(tomatoKetchup);
            }});

            Burger cheeseburger = new Burger();
            cheeseburger.setName("Cheeseburger");
            cheeseburger.setImageUri("image/burger/cheeseburger.png");
            cheeseburger.setUser(burgerChef);
            cheeseburger.setIngredients(new ArrayList<Ingredient>(){{
                add(sesameSeedBun);
                add(beefPatty);
                add(cheddarCheese);
                add(crunchyPickles);
                add(yellowMustard);
                add(tomatoKetchup);
            }});

            Burger baconCheeseburger = new Burger();
            baconCheeseburger.setName("Bacon Cheeseburger");
            baconCheeseburger.setImageUri("image/burger/bacon cheeseburger.png");
            baconCheeseburger.setUser(burgerChef);
            baconCheeseburger.setIngredients(new ArrayList<Ingredient>(){{
                add(sesameSeedBun);
                add(beefPatty);
                add(cheddarCheese);
                add(crunchyPickles);
                add(yellowMustard);
                add(tomatoKetchup);
                add(crispyBacon);
            }});

            Burger whopper = new Burger();
            whopper.setName("Whopper");
            whopper.setImageUri("image/burger/whopper.png");
            whopper.setUser(burgerChef);
            whopper.setIngredients(new ArrayList<Ingredient>() {{
                add(sesameSeedBun);
                add(beefPatty);
                add(juicyTomatoes);
                add(crispLettuce);
                add(creammyMayonnaise);
                add(tomatoKetchup);
                add(crunchyPickles);
                add(redOnions);
            }});

            Burger salmonBurger = new Burger();
            salmonBurger.setName("Wild Salmon");
            salmonBurger.setImageUri("image/burger/salmon.png");
            salmonBurger.setUser(burgerChef);
            salmonBurger.setIngredients(new ArrayList<Ingredient>() {{
                add(sesameSeedBun);
                add(salmon);
                add(friedEgg);
                add(sweetPepper);
                add(cucumber);
                add(redOnions);
                add(creammyMayonnaise);
            }});

            Burger shrimpBurger = new Burger();
            shrimpBurger.setName("Shrimp Burger");
            shrimpBurger.setImageUri("image/burger/shrimp.png");
            shrimpBurger.setUser(burgerChef);
            shrimpBurger.setIngredients(new ArrayList<Ingredient>() {{
                add(sesameSeedBun);
                add(shrimps);
                add(friedEgg);
                add(sweetPepper);
                add(crispLettuce);
                add(bbqSauce);
            }});

            burgerRepo.save(hamburger);
            burgerRepo.save(cheeseburger);
            burgerRepo.save(baconCheeseburger);
            burgerRepo.save(whopper);
            burgerRepo.save(salmonBurger);
            burgerRepo.save(shrimpBurger);

            // Promo Codes

            PromoCode iLoveMac = new PromoCode();
            iLoveMac.setCode("#ILOVEMAC");
            iLoveMac.setDiscount(BigDecimal.valueOf(0.05));

            PromoCode mcDonaldsForever = new PromoCode();
            mcDonaldsForever.setCode("#MCDONALDSFOREVER");
            mcDonaldsForever.setDiscount(BigDecimal.valueOf(0.01));
            PromoCode iLoveBurgerHub = new PromoCode();
            iLoveBurgerHub.setCode("#ILOVEBURGERHUB");
            iLoveBurgerHub.setDiscount(BigDecimal.valueOf(0.2));

            PromoCode iLoveBurgers = new PromoCode();
            iLoveBurgers.setCode("#ILOVEBURGERS");
            iLoveBurgers.setDiscount(BigDecimal.valueOf(0.1));

            PromoCode iWantToEat = new PromoCode();
            iWantToEat.setCode("#IWANTTOEAT");
            iWantToEat.setDiscount(BigDecimal.valueOf(0.3));

            PromoCode giveMeSomeMeal = new PromoCode();
            giveMeSomeMeal.setCode("#GIVEMESOMEMEAL");
            giveMeSomeMeal.setDiscount(BigDecimal.valueOf(0.5));

            promoCodeRepo.save(iLoveMac);
            promoCodeRepo.save(mcDonaldsForever);
            promoCodeRepo.save(iLoveBurgerHub);
            promoCodeRepo.save(iLoveBurgers);
            promoCodeRepo.save(iWantToEat);
            promoCodeRepo.save(giveMeSomeMeal);

        };
    }
}
