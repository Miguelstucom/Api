package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import models.Restaurant;
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
	
	@Override
	public boolean addUser(User res) {
			user.save(res);
			return true;
	}
	
	@Override
	public void updateUser(User res) {
		if(user.findById(res.getId())!=null) {
			user.save(res);
		}
		
	}
	
	@Override
	public boolean deleteUser(int idRes) {
		if(user.findById(idRes) != null) {
			user.deleteById(idRes);
			return true;
		}
		return false;
	}

	@Override
	public User findByEmail(String mail) {
		// TODO Auto-generated method stub
		return user.findByEmail(mail);
	}
}
