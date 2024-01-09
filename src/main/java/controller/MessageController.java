package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.Message;
import service.MessagesService;

@RestController
@RequestMapping("/api")
public class MessageController {

	@Autowired
	MessagesService message;
	
	@GetMapping(value="message/{name}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Message retrieveMessages(@PathVariable("name") String name) {
		return message.retrieveMessages(name);
	}
	
	@GetMapping(value="message",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Message>getMessages(){
		return message.getMessages();
	}
}
