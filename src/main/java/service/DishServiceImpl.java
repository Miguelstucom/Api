package service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DishDao;
import dao.RestaurantDao;
import models.Dishe;
@Service
public class DishServiceImpl implements DishService{
	
	@Autowired
	DishDao dao;

	@Override
	public List<Dishe> retrieveDishes() {
		return dao.retrieveDishes();
	}

	@Override
	public List<Dishe> getDishByResId(Integer idRes) {
		return dao.getDishByResId(idRes);
	}
	
	public List<Dishe> getDishesByAllergens(List<Boolean> allergens) {
        // Obtener todos los platos
        List<Dishe> allDishes = dao.retrieveDishes();

        // Filtrar platos basados en allergens
        return allDishes.stream()
                .filter(dish -> matchesAllergens(dish.getAllergens(), allergens))
                .collect(Collectors.toList());
    }

    private boolean matchesAllergens(String dishAllergens, List<Boolean> allergens) {
        // Convertir la cadena de allergens del plato a una lista de booleanos
        List<Boolean> dishAllergensList = dishAllergens.chars()
                .mapToObj(c -> c == '1')
                .collect(Collectors.toList());

        // Verificar si las listas de allergens coinciden
        return dishAllergensList.equals(allergens);
    }

}
