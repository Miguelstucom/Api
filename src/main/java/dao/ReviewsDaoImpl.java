package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import models.Review;
@Repository
public class ReviewsDaoImpl implements ReviewsDao {
	@Autowired
	ReviewsJpaSpring reviews;

	@Override
	public List<Review> RetriveReviews(int id) {
		return reviews.findByResId(id);
	}

	@Override
	public boolean addReview(Review review) {
		reviews.save(review);
		return true;
	}

}
