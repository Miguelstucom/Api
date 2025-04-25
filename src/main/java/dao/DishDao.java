package dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import models.Dishe;
@Service
public interface DishDao {

	Page<Dishe>retrieveDishes(Pageable pageDish);
	List<Dishe> getDishByResId(Integer idRes);
}
