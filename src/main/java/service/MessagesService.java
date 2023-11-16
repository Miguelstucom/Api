package service;

import java.util.List;

import models.Message;
import models.Restaurant;

public interface MessagesService {
	Message retrieveMessages(String name);
	List <Message> getMessages();
}
