package service;

import java.util.List;

import models.Dishe;
import models.Review;

public interface ReviewsService {
	List<Review> getReviewhByResId(Integer idRes);
	boolean addReview(Review review);
}
