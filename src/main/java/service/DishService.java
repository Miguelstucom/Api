package service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import models.Dishe;
import models.Restaurant;

public interface DishService {
	List<Dishe>retrieveDishes();
	List<Dishe> getDishByResId(Integer idRes);
    List<Dishe> getDishesByAllergens(String allergens);
	List<Dishe> getDishesByAllergens(List<Boolean> allergens);
}
