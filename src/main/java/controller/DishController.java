package controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.Dishe;
import models.Restaurant;
import service.DishService;
import service.RestaurantService;

@RestController
@RequestMapping("/api")
public class DishController {
	
	
	@Autowired
	DishService dishservice;
	
	@GetMapping(value="dishes",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Dishe>retrieveDishes(){
		return dishservice.retrieveDishes();
	}
	
	@GetMapping(value="dishes/{idRes}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Dishe>retrieveDishesbyId(@PathVariable("idRes") Integer idRes){
		return dishservice.getDishByResId(idRes);
	}

    @GetMapping("/dishes/filter/{allergens}")
    public List<Dishe> getDishesByAllergens(@PathVariable String allergens) {
        List<Dishe> allDishes = dishservice.retrieveDishes();
        return allDishes.stream()
                .filter(dish -> matchesAllergens(dish.getAllergens(), allergens))
                .collect(Collectors.toList());
    }

    private boolean matchesAllergens(String dishAllergens, String allergens) {
        for (int i = 0; i < allergens.length(); i++) {
            char allergenChar = allergens.charAt(i);
            char dishAllergenChar = dishAllergens.charAt(i);

            if (allergenChar == '1' && dishAllergenChar != '1') {
                return false;
            }
        }
        return true;
    }
}
