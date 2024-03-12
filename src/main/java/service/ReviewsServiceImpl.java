package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DishDao;
import dao.ReviewsDao;
import models.Review;
@Service
public class ReviewsServiceImpl implements ReviewsService {
	
	@Autowired
	ReviewsDao dao;

	@Override
	public List<Review> getReviewhByResId(Integer idRes) {
		return dao.RetriveReviews(idRes);
	}

}
