package controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.User;
import service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserService user;
	
	@GetMapping(value="user/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public User retrieveUser(@PathVariable("id") int id) {
		return user.retrieveUser(id);
	}
	
	@PostMapping("/user")
	public ResponseEntity<?> createUser(@RequestBody User newuser){
		User usr = newuser;
		java.util.Map<String, Object> response = new HashMap<>();
		try {
			user.addUser(newuser);
			response.put("message", "El usuario ha sido creado con éxito");
			response.put("Usuario", usr);
			return new ResponseEntity<java.util.Map<String, Object>>(response, HttpStatus.CREATED);
		}
		catch(DataAccessException e) {
			response.put("message","Error");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<java.util.Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping(value = "/user", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateUser(@RequestBody User updatedUser) {

		User newUser = new User();
		java.util.Map<String, Object> response = new HashMap<>();		
		
		if(user.retrieveUser(updatedUser.getId())==null) {
			response.put("mensaje", "Error: no se pudo editar, el User ID: " + updatedUser.getId() + "no existe en la base de datos");
			return new ResponseEntity<java.util.Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		
		user.updateUser(updatedUser);
		
		try {
			newUser.setName(updatedUser.getName());
			newUser.setEmail(updatedUser.getEmail());
			newUser.setPassword(updatedUser.getPassword());
			newUser.setEmail(updatedUser.getEmail());
			
			user.updateUser(updatedUser);
			response.put("message", "El usuario ha sido actualizado con éxito");
			response.put("Usuario", updatedUser);
			return new ResponseEntity<java.util.Map<String, Object>>(response, HttpStatus.CREATED);
			
		} catch(DataAccessException e) {
			response.put("message","Error al realizar al actualizar los datos en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<java.util.Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
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

}