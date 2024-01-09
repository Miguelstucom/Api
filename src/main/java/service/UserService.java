package service;

import models.Message;
import models.Restaurant;
import models.User;

public interface UserService {
	boolean addUser(User res);
	void updateUser(User res);
	boolean deleteUser(int idUser);
	User retrieveUser(int id);
}
