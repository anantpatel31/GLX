package com.glx.app.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.glx.app.dto.Product;
import com.glx.app.exception.ProductException;
import com.glx.app.services.ProductServicesInterface;

@RestController
public class ProductController {

	@Autowired
	private ProductServicesInterface productService;

	@PostMapping("product")
	public String addProduct(@Valid @RequestBody Product product) throws ProductException {
		return "Product Added to Database Successfully.";
	}

	@GetMapping("product/{id}")
	public Product getProduct(@PathVariable("id") Integer id) throws ProductException {
		return productService.getProductById(id).get();
	}

	@GetMapping("product")
	public List<Product> getByName(@Valid @RequestParam(value = "name") String name) throws ProductException {
		return productService.getProductByName(name);
	}

	@GetMapping("products")
	public List<Product> getAllProducts() throws ProductException {
		return productService.getAllProducts();
	}

	@PutMapping("product")
	public String updateProduct(@Valid @RequestBody Product product) throws ProductException {
		return productService.updateProduct(product);
	}

	@DeleteMapping("product/{productId}")
	public String deleteProductById(@PathVariable(value = "productId") Integer productId) throws ProductException {
		return (productService.deleteProductById(productId));
	}

	@DeleteMapping("products")
	public String deleteByNames(@RequestParam(value = "product_name") String name) throws ProductException {
		return productService.deleteByName(name);
	}
}
