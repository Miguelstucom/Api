package controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.*;

import models.Dishe;
import models.Restaurant;
import service.DishService;
import service.RestaurantService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api")
public class DishController {
	
	
	@Autowired
	DishService dishservice;
	
	@GetMapping(value="dishes",produces=MediaType.APPLICATION_JSON_VALUE)
	public Page<Dishe>retrieveDishes(Pageable pageDish){
		return dishservice.retrieveDishes(pageDish);
	}
	
	@GetMapping(value="dishes/{idRes}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Dishe>retrieveDishesbyId(@PathVariable("idRes") Integer idRes){
		return dishservice.getDishByResId(idRes);
	}

    @GetMapping("/dishes/filter/{allergens}")
    public Page<Dishe> getDishesByAllergens(@PathVariable String allergens, Pageable pageDishFilter) {
    	return dishservice.getDishesByAllergens(allergens, pageDishFilter);
    }

}
