package dao;

import org.springframework.stereotype.Service;

import models.Message;
import models.Restaurant;
import models.User;
@Service
public interface UserDao {
	boolean addUser(User res);
	void updateUser(User res);
	boolean deleteUser(int idRes);
	User retrieveUser(int id);
	User findByEmail(String mail);
}
