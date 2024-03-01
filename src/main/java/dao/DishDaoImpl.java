package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import models.Dishe;
@Repository
public class DishDaoImpl implements DishDao{
	@Autowired
	DishJpaSpring dish;
	@Override
	public List<Dishe> retrieveDishes() {
		return dish.findAll();
	}
	@Override
    public List<Dishe> getDishByResId(Integer idRes) {
        return dish.findByResId(idRes);
    }

}
