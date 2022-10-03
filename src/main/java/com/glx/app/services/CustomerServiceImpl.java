package com.glx.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glx.app.dao.CustomerRepo;
import com.glx.app.dto.Customer;
import com.glx.app.exception.GlxException;
@Service
public class CustomerServiceImpl implements CustomerServices {

	@Autowired
	private CustomerRepo customerRepo;
	@Override
	public Optional<Customer> getCustomerById(Integer id) throws GlxException {
		Optional<Customer> customer = customerRepo.findById(id);
		if (customer.isEmpty()) {
			throw new GlxException("User with user id " + id + " does not exist");
		}
		return customer;
	}

	@Override
	public Customer addCustomer(Customer customer) throws GlxException {
		if (customer == null)
			throw new GlxException("Feilds cannot be left empty. Please fill in the necesaary details.");
		String userPhoneNumber = customer.getPhoneNumber();
		if(customerRepo.findByPhoneNumber(userPhoneNumber) != null)
			throw new GlxException("Customer details already exist. Log into your account!");
		customer = customerRepo.save(customer);
		if (customer == null) {
			throw new GlxException("Customer could not be added!! Try again.");
		}
		return customer;
	}
	@Override
	public Customer updateCustomer(Customer customer) throws GlxException {
		if (customer == null) {
			throw new GlxException("Provide details to update");
		}
		int x = customer.getId();
		if (customerRepo.existsById(x)) {
			customer = customerRepo.save(customer);
			if (customer == null) {
				throw new GlxException("Updation Failed!! Try again");
			}
		} else {
			throw new GlxException("No customer with the provided data exists to be updated!! ");
		}
		return customer;
	}

	@Override
	public boolean deleteCustomerById(Integer id) throws GlxException {
		boolean flag = true;
		if (customerRepo.existsById(id)) {
			customerRepo.deleteById(id);
			if (customerRepo.existsById(id)) {
				flag = false;
				throw new GlxException("User not deleted");
			}
		} else {
			flag = false;
			throw new GlxException("No customer with id " + id + " exists to be deleted ");
		}
		return flag;
	}

	@Override
	public List<Customer> getAllCustomers() throws GlxException {
		List<Customer> allCustomers = customerRepo.findAll();
		if (allCustomers.isEmpty()) {
			throw new GlxException("No customers yet!!");
		}
		return allCustomers;
	}

	@Override
	public List<Customer> getCustomerByUserNameAndPassword(String name, String password) throws GlxException {
		List<Customer> customer = new ArrayList<>();
		if (name == null || password == null) {
			throw new GlxException("Username and password are required!! ");
		}
		customer = customerRepo.findByUserNameAndPassword(name, password);
		if (customer.isEmpty())
			throw new GlxException(
					"No customer with the provided username and password. Try again with correct details.");
		return customer;
	}

}
