package service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import models.Dishe;
import models.Restaurant;

public interface DishService {
	Page<Dishe>retrieveDishes(Pageable pageDish);
	List<Dishe> getDishByResId(Integer idRes);
    Page<Dishe> getDishesByAllergens(String allergens, Pageable pageDishFilter);
	Page<Dishe> getDishesByAllergens(Page<Boolean> allergens, Pageable pageDishFilter);
}
