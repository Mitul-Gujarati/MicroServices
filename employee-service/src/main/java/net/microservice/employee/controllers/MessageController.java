package net.microservice.employee.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/api/employees")
public class MessageController {
	
	@Value("${message}")
	private String message;
	
	@GetMapping("/user/message")
	public String getMessage() {
		return message;
	}
}
