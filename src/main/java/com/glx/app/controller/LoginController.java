package com.glx.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.glx.app.dto.Customer;
import com.glx.app.exception.GlxException;
import com.glx.app.services.CustomerServices;

@RestController
public class LoginController {

	@Autowired
	private CustomerServices customerServices;

	@GetMapping("login/customer")
	public List<Customer> getCustomerByUserName(@RequestParam(value = "userName") String userName,
			@RequestParam(value = "password") String password) throws GlxException {
				
		return customerServices.getCustomerByUserNameAndPassword(userName, password);

	}
}
