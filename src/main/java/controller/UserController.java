package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import Exceptions.EmailAlreadyExistsException;
import dao.UserJpaSpring;
import io.jsonwebtoken.Claims;
import models.User;
import models.UserChangePassword;
import models.changePasswordRequest;
import models.tokenRequest;
import service.UserService;

@RestController
@CrossOrigin(origins = {"http://10.30.212.6", "http://192.168.10.20"})
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserService user;
	
    @Autowired
    private UserJpaSpring userRepository;
    
    @Autowired
    private AuthController authController;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

	
//	@GetMapping(value="user/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
//	public User retrieveUser(@PathVariable("id") int id) {
//		return user.retrieveUser(id);
//	}
	
	@PostMapping("/user")
	public ResponseEntity<?> createUser(@RequestBody User newuser) throws IOException{
		User usr = newuser;
		java.util.Map<String, Object> response = new HashMap<>();
		try {
			if(user.findByEmail(usr.getEmail()) != null) {
                throw new EmailAlreadyExistsException("Este correo ya ha sido utilizado");
			}
			
			byte[] defaultImage = loadDefaultImage();
			newuser.setImage(defaultImage);
			
			
			String encodedPassword = passwordEncoder.encode(newuser.getPassword());
	        newuser.setPassword(encodedPassword);
			
			user.addUser(newuser);
			response.put("message", "El usuario ha sido creado con éxito");
//			response.put("Usuario", usr);
			return new ResponseEntity<java.util.Map<String, Object>>(response, HttpStatus.CREATED);
		} catch(DataAccessException e) {
			response.put("message","Error");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<java.util.Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (EmailAlreadyExistsException e) {
            response.put("message", "Error al crear el usuario");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
	}
	
	@PostMapping("/userRetrieve")
	public ResponseEntity<?> retrieveUser(@RequestBody tokenRequest data){
		try {
	        String token = data.getToken();
	        String userIdFromToken = authController.getClaimFromToken(token, Claims::getSubject);

	        User user = userRepository.findById(Integer.parseInt(userIdFromToken)).orElse(null);

	        if (user == null) {
	            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
	        }
	        String imagenBase64 = Base64.getEncoder().encodeToString(user.getImage());

	        Map<String, Object> response = new HashMap<>();
	        response.put("name", user.getName());
	        response.put("surname", user.getSurname());
	        response.put("email", user.getEmail());
	        response.put("image", imagenBase64);

	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>("Token inválido o error al procesar", HttpStatus.BAD_REQUEST);
	    }
	}
	
	@PutMapping(value = "/user", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateUser(@RequestBody changePasswordRequest updatedUser) {
		
		java.util.Map<String, Object> response = new HashMap<>();		
		
		try {
	
			String token = updatedUser.getToken();
			String lastpassword = updatedUser.getLastpassword();
			String newpassword = updatedUser.getNewpassword();
			
			
			System.out.println("last: " + lastpassword + " | New: " + newpassword);
	        String userIdFromToken = authController.getClaimFromToken(token, Claims::getSubject);
	        
	        User userUpdated = userRepository.findById(Integer.parseInt(userIdFromToken)).orElse(null);
	
	        if (user == null) {
	            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
	        }
	        
	        if(!passwordEncoder.matches(lastpassword, userUpdated.getPassword())) {
	        	return new ResponseEntity<>("error, la contraseña no es correcta", HttpStatus.NOT_FOUND);
	    	}
	        
			String encodedPassword = passwordEncoder.encode(newpassword);
			userUpdated.setPassword(encodedPassword);
	
	        user.updateUser(userUpdated);
					
			response.put("message", "El usuario ha sido actualizado con éxito");
			response.put("Usuario", updatedUser);
			return new ResponseEntity<java.util.Map<String, Object>>(response, HttpStatus.CREATED);
			
		} catch(DataAccessException e) {
			response.put("message","Error al realizar al actualizar los datos en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<java.util.Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		
	}
	
	@PutMapping(value = "/user/{userId}/premium")
	public ResponseEntity<?> togglePremiumStatus(@PathVariable("userId") int userId) {
	    Map<String, Object> response = new HashMap<>();

	    try {
	        User userToUpdate = userRepository.findById(userId).orElse(null);

	        if (userToUpdate == null) {
	            response.put("message", "Error: No se encontró el usuario con el ID proporcionado");
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }

	        // Toggle the premium status
	        int currentPremiumStatus = userToUpdate.getPremium();
	        userToUpdate.setPremium(currentPremiumStatus == 0 ? 1 : 0);
	        userRepository.save(userToUpdate);

	        response.put("message", "El estado premium del usuario ha sido cambiado con éxito");
	        response.put("Usuario", userToUpdate);
	        return new ResponseEntity<>(response, HttpStatus.OK);

	    } catch (DataAccessException e) {
	        response.put("message", "Error al actualizar el estado premium en la base de datos");
	        response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") int id){
		java.util.Map<String, Object> response = new HashMap<>();
		User actualUser = user.retrieveUser(id);
		
		if(actualUser==null) {
			response.put("message", "Error al eliminar, el usuario: " + id +" no existe en la base de datos");
			return new ResponseEntity<java.util.Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		try {
			user.deleteUser(id);
	
			response.put("mensaje","El usuario " + actualUser.getName() + " ha sido eliminado con exito");
			return new ResponseEntity<java.util.Map<String, Object>>(response, HttpStatus.CREATED);
		}
		catch(DataAccessException e) {
			response.put("message","Error al eliminar los datos en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<java.util.Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@PutMapping(value = "/user/password", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePassword(@RequestBody UserChangePassword request) {

	    java.util.Map<String, Object> response = new HashMap<>();

	    try {
	        User userToUpdate = userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());

	        if (userToUpdate == null) {
	            response.put("mensaje", "Error: No se pudo autenticar al usuario con el email y contraseña proporcionados");
	            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	        }
	        userToUpdate.setPassword(request.getNewPassword());
	        user.updateUser(userToUpdate);

	        response.put("message", "La contraseña del usuario ha sido actualizada con éxito");
	        response.put("Usuario", userToUpdate);
	        return new ResponseEntity<>(response, HttpStatus.CREATED);

	    } catch (DataAccessException e) {
	        response.put("message", "Error al actualizar la contraseña en la base de datos");
	        response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@PostMapping("/{userId}/uploadImage")
    public ResponseEntity<String> uploadUserImage(@PathVariable Integer userId, @RequestParam("image") MultipartFile file) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            user.setImage(file.getBytes());
            userRepository.save(user);

            return ResponseEntity.status(HttpStatus.OK)
                    .body("Imagen subida con éxito para el usuario: " + user.getName());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al subir la imagen: " + e.getMessage());
        }
    }
	
	private byte[] loadDefaultImage() throws IOException {
	    InputStream is = getClass().getClassLoader().getResourceAsStream("assets/image-user.png");
	    if (is == null) {
	        throw new IOException("Default avatar not found!");
	    }
	    return is.readAllBytes();
	}

}
