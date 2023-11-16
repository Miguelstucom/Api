package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import models.Message;
import models.Restaurant;

public interface MessagesJpaSpring extends JpaRepository<Message, String> {

}
