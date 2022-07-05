package com.example.springboot.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot.app.exception.ResourceNotFoundException;
import com.example.springboot.app.model.Employee;
import com.example.springboot.app.repository.EmployeeRepository;

@Service //Notación para indicar que es un servicio
@Transactional //define que un conjunto de instrucciones que se ejecutan en bloque asegura y valida el metodo termine correctamente antes de ejecutar algun otro metodo
public class EmployeeServiceImpl implements EmployeeService {

	//Inyección de dependecias (crea una instancia cuando lo requiera)
	@Autowired
	private EmployeeRepository productRepository;


	@Override
	public Employee updateProduct(Employee product) {
		Optional<Employee> productDb = this.productRepository.findById(product.getId());

		if (productDb.isPresent()) {
			Employee productUpdate = productDb.get();
			productUpdate.setId(product.getId());
			productUpdate.setSuername(product.getSuername());
			productUpdate.setFirstname(product.getFirstname());
			productRepository.save(productUpdate);
			return productUpdate;
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + product.getId());
		}
	}

	@Override
	public List<Employee> getAllProduct() {
		return this.productRepository.findAll();
	}

	@Override
	public Employee getProductById(long productId) {

		Optional<Employee> productDb = this.productRepository.findById(productId);

		if (productDb.isPresent()) {
			return productDb.get();
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + productId);
		}
	}

	@Override
	public void deleteProduct(long productId) {
		Optional<Employee> productDb = this.productRepository.findById(productId);

		if (productDb.isPresent()) {
			this.productRepository.delete(productDb.get());
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + productId);
		}
	}
	@Override
	public Employee createProduct(Employee product) {
		return productRepository.save(product);
	}
}
