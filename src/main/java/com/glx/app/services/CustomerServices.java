package com.glx.app.services;

import java.util.List;
import java.util.Optional;

import com.glx.app.dto.Customer;
import com.glx.app.exception.GlxException;

public interface CustomerServices {

	public Optional<Customer> getCustomerById(Integer id) throws GlxException;

	public Customer addCustomer(Customer customer) throws GlxException;

	public boolean deleteCustomerById(Integer id) throws GlxException;

	public Customer updateCustomer(Customer customer) throws GlxException;

	public List<Customer> getAllCustomers() throws GlxException;

	public List<Customer> getCustomerByUserNameAndPassword(String name, String password) throws GlxException;

}
