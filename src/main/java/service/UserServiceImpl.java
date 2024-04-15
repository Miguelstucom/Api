package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.RestaurantDao;
import dao.UserDao;

import models.Restaurant;
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
	
	@Override
	public boolean addUser(User res) {
		return dao.addUser(res);
	}
	
	@Override
	public void updateUser(User res) {
		dao.updateUser(res);
		
	}
	
	@Override
	public boolean deleteUser(int idRes) {
		return dao.deleteUser(idRes);
	}
	
	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return dao.findByEmail(email);
	}
	

}
