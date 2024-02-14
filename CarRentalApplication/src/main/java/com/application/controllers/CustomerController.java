package com.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.entities.Customers;
import com.application.sevices.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Customers customer) {
		String message = customerService.save(customer);
		return ResponseEntity.ok(message);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Customers customer) {
		String message = customerService.login(customer);
		return ResponseEntity.ok(message);
	}

}