package dao;

import java.util.List;

import org.springframework.stereotype.Service;

import models.Message;
import models.Restaurant;

@Service
public interface MessagesDao {
	Message retrieveMessages(String name);
	List<Message>getMessages();
}
