package service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dao.DishDao;
import dao.RestaurantDao;
import models.Dishe;
@Service
public class DishServiceImpl implements DishService{
	
	@Autowired
	DishDao dao;

	@Override
	public Page<Dishe> retrieveDishes(Pageable pageDish) {
		return dao.retrieveDishes(pageDish);
	}

	@Override
	public List<Dishe> getDishByResId(Integer idRes) {
		return dao.getDishByResId(idRes);
	}
	
	public Page<Dishe> getDishesByAllergens(List<Boolean> allergens, Pageable pageDishFilter) {
        // Obtener todos los platos
        Page<Dishe> allDishes = dao.retrieveDishes(pageDishFilter);

        // Filtrar platos basados en allergens
        List<Dishe> ListDish= allDishes.stream()
                .filter(dish -> matchesAllergens(dish.getAllergens(), allergens))
                .collect(Collectors.toList());
        return convertListToPage(ListDish, pageDishFilter);
    }

    private boolean matchesAllergens(String dishAllergens, List<Boolean> allergens) {
        List<Boolean> dishAllergensList = dishAllergens.chars()
                .mapToObj(c -> c == '1')
                .collect(Collectors.toList());

        return dishAllergensList.equals(allergens);
    }
    
    @Override
    public Page<Dishe> getDishesByAllergens(String allergens, Pageable pageDishFilter) {
        if (allergens.chars().allMatch(ch -> ch == '0')) {
            return retrieveDishes(pageDishFilter);
        } else {
            List<Dishe> listDish= retrieveDishes(pageDishFilter).stream()
                    .filter(dish -> !hasMatchingAllergens(dish.getAllergens(), allergens))
                    .collect(Collectors.toList());
            return convertListToPage(listDish, pageDishFilter);
        }
    }

    private boolean hasMatchingAllergens(String dishAllergens, String inputAllergens) {
        for (int i = 0; i < dishAllergens.length(); i++) {
            if (inputAllergens.charAt(i) == '1' && dishAllergens.charAt(i) == '1') {
                return true;
            }
        }
        return false;
    }
    
    public Page<Dishe> convertListToPage(List<Dishe> list, Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Dishe> subList;

        if (list.size() < startItem) {
            subList = List.of(); // Página vacía
        } else {
            int toIndex = Math.min(startItem + pageSize, list.size());
            subList = list.subList(startItem, toIndex);
        }

        return new PageImpl<>(subList, pageable, list.size());
    }

}
