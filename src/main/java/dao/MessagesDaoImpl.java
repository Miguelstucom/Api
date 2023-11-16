package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import models.Message;
@Repository
public class MessagesDaoImpl implements MessagesDao {
	@Autowired
	MessagesJpaSpring message;

	@Override
	public Message retrieveMessages(String name) {
		return message.findById(name).orElse(null);
	}

	@Override
	public List<Message> getMessages() {
		return message.findAll();
	}

}
