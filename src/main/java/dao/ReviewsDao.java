package dao;

import java.util.List;

import org.springframework.stereotype.Service;
import models.Review;

@Service
public interface ReviewsDao {
	List<Review>RetriveReviews(int id);
}
