package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MessagesDao;
import dao.RestaurantDao;
import models.Message;
import models.Restaurant;

@Service
public class MessagesServiceImpl implements MessagesService {
	
	@Autowired
	MessagesDao dao;
	
	
	@Override
	public Message retrieveMessages(String name) {
		// TODO Auto-generated method stub
		return dao.retrieveMessages(name);
	}


	@Override
	public List<Message> getMessages() {
		// TODO Auto-generated method stub
		return dao.getMessages();
	}
}
