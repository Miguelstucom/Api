package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.RestaurantDao;
import dao.UserDao;
import models.User;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao dao;
	@Override
	public User retrieveUser(int id) {
		// TODO Auto-generated method stub
		return dao.retrieveUser(id);
	}

}
