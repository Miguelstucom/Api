package controller;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dao.UserJpaSpring;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import models.User;
import models.UserLoginRequest;
import models.tokenRequest;


@RestController
@CrossOrigin("http://localhost:3000")
public class AuthController {

    @Autowired
    private UserJpaSpring userRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
        
    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest userLoginRequest ) {
        String email = userLoginRequest.getEmail();
        String password = userLoginRequest.getPassword();
		Map<String, Object> response = new HashMap<>();

        User user = userRepository.findByEmail(email);
        
        if (user == null) {
        	return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }else 
        
        if(!passwordEncoder.matches(password, user.getPassword())) {
        	return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);
        }
        
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

	
	@PostMapping("/api/checkUser")
	public ResponseEntity<?> getUserFromToken(@RequestBody tokenRequest data) {
	    try {
	        String token = data.getToken();
	        String userIdFromToken = getClaimFromToken(token, Claims::getSubject);

	        User user = userRepository.findById(Integer.parseInt(userIdFromToken)).orElse(null);

	        if (user == null) {
	            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
	        }

	        // Puedes devolver solo los datos necesarios
	        Map<String, Object> response = new HashMap<>();
	        response.put("nombre", user.getName());
	        response.put("email", user.getEmail());

	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>("Token inválido o error al procesar", HttpStatus.BAD_REQUEST);
	    }
	}


	@PostMapping("/api/viewToken")
	public Boolean validateInfoUsu(@RequestBody tokenRequest data) {
		String token = data.getToken();
		String infoUsu = data.getUser();
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
