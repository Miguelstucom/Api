package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.Dishe;
import models.Review;
import service.RestaurantService;
import service.ReviewsService;

@RestController
@RequestMapping("/api")
public class ReviewsController {
	
	@Autowired
	ReviewsService rs;
	
	@GetMapping(value="reviews/{idRes}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Review>retrieveReviewsbyId(@PathVariable("idRes") Integer idRes){
		return rs.getReviewhByResId(idRes);
	}

}
