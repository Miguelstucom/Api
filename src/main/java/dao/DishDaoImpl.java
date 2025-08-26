package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import models.Dishe;
@Repository
public class DishDaoImpl implements DishDao{
	@Autowired
	DishJpaSpring dish;
	@Override
	public Page<Dishe> retrieveDishes(Pageable pageDish) {
		return dish.findAll(pageDish);
	}
	@Override
    public List<Dishe> getDishByResId(Integer idRes) {
        return dish.findByResId(idRes);
    }

}
