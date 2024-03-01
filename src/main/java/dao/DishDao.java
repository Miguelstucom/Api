package dao;

import java.util.List;

import org.springframework.stereotype.Service;

import models.Dishe;
@Service
public interface DishDao {

	List<Dishe>retrieveDishes();
	List<Dishe> getDishByResId(Integer idRes);
}
