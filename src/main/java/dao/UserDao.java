package dao;

import org.springframework.stereotype.Service;

import models.Message;
import models.User;
@Service
public interface UserDao {
	User retrieveUser(int id);
}
