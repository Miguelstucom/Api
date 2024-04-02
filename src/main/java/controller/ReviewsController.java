package controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.Dishe;
import models.Review;
import models.User;
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
	
	@PostMapping("/review")
	public ResponseEntity<?> createUser(@RequestBody Review newreview){
		Review review = newreview;
		java.util.Map<String, Object> response = new HashMap<>();
		try {
			rs.addReview(newreview);
			response.put("message", "La Review ha sido publicada");
			response.put("Review", review);
			return new ResponseEntity<java.util.Map<String, Object>>(response, HttpStatus.CREATED);
		}
		catch(DataAccessException e) {
			response.put("message","Error");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<java.util.Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
