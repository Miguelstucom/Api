package controller;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dao.UserJpaSpring;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import models.User;
import models.UserLoginRequest;

@RestController
public class AuthController {

    @Autowired
    private UserJpaSpring userRepository;

        
    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest userLoginRequest ) {
        String email = userLoginRequest.getEmail();
        String password = userLoginRequest.getPassword();
		Map<String, Object> response = new HashMap<>();


        User user = userRepository.findByEmailAndPassword(email, password);
        
        String token = doGenerateToken(user.getId() + "");
        
        response.put("User", user);
        response.put("Token", token);

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    private String key = "foodie_guard";
	private String doGenerateToken(String infoUsu) {
		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder().setClaims(claims)
				.setSubject(infoUsu)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 100 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, key).compact();
	}


	public Boolean validateInfoUsu(String token, String infoUsu) {
		final String infoUsuToken = getClaimFromToken(token, Claims::getSubject);
		return (infoUsu.equals(infoUsuToken));
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
	}
	
}
