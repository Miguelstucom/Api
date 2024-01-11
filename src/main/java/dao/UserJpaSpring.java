package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import models.Restaurant;
import models.User;

public interface UserJpaSpring extends JpaRepository<User, Integer> {
	User findByEmailAndPassword(String email, String password);
}
