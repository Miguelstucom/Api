package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import models.Review;
@Repository
public class ReviewsDaoImpl implements ReviewsDao {
	@Autowired
	ReviewsJpaSpring review;

	@Override
	public List<Review> RetriveReviews(int id) {
		return review.findByResId(id);
	}

}
