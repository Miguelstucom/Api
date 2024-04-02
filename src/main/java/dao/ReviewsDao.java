package dao;

import java.util.List;

import org.springframework.stereotype.Service;
import models.Review;
import models.User;

@Service
public interface ReviewsDao {
	List<Review>RetriveReviews(int id);
	boolean addReview(Review review);
}
