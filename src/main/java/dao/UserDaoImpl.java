package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import models.User;
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	UserJpaSpring user;
	@Override
	public User retrieveUser(int id) {
		// TODO Auto-generated method stub
		return user.findById(id).orElse(null);
	}

}
