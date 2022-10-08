package com.globalmart.app;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.glx.app.dao.ProductRepo;
import com.glx.app.dto.Category;
import com.glx.app.dto.Product;
import com.glx.app.exception.ProductException;
import com.glx.app.services.ProductServicesInterface;

@SpringBootTest
@Transactional
class ProductTest {

	@Autowired
	ProductServicesInterface productService = Mockito.mock(ProductServicesInterface.class);
	@Autowired
	private ProductRepo productRepo;

	Category category = new Category(1, "categoryName", "categoryDescription");

	@BeforeEach
	void starterScript() throws ProductException {
		productRepo.save(new Product(6, "MyProduct", "MyDescription", 2500.00, 25, null, category));
	}

	@Test
	void addProductTest() throws ProductException {
		productService.deleteProductById(6);
		Product product = new Product(6, "MyProduct", "MyDescription", 2500.00, 25, null, category);
		assertDoesNotThrow( () -> productService.addProduct(product));
	}
		@Test
	void getProductByIdTest() throws ProductException {
		assertNotNull(productService.getProductById(6));
		assertThrows(ProductException.class, () -> productService.getProductById(999));
	}

	@Test
	void getProductByNameTest() throws ProductException {
		assertNotNull(productService.getProductByName("MyProduct"));
		assertThrows(ProductException.class, () -> productService.getProductByName("NoProduct"));
	}

	@Test
	void getAllProducts() throws ProductException {
		assertDoesNotThrow(() -> productService.getAllProducts());
		productRepo.deleteAll();
		productRepo.flush();
		assertThrows(ProductException.class, ()-> productService.getAllProducts());
	} 


}
