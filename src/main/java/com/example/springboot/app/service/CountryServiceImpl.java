package com.example.springboot.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot.app.exception.ResourceNotFoundException;
import com.example.springboot.app.model.Country;

import com.example.springboot.app.repository.CountryRepository;

@Service //Notación para indicar que es un servicio
@Transactional //define que un conjunto de instrucciones que se ejecutan en bloque asegura y valida el metodo termine correctamente antes de ejecutar algun otro metodo
public class CountryServiceImpl implements CountryService {

	//Inyección de dependecias (crea una instancia cuando lo requiera)
	@Autowired
	private CountryRepository productRepository;


	@Override
	public Country updateProduct(Country product) {
		Optional<Country> productDb = this.productRepository.findById(product.getId());

		if (productDb.isPresent()) {
			Country productUpdate = productDb.get();
			productUpdate.setId(product.getId());
			productUpdate.setCode(product.getCode());
			productUpdate.setName(product.getName());
			productRepository.save(productUpdate);
			return productUpdate;
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + product.getId());
		}
	}

	@Override
	public List<Country> getAllProduct() {
		return this.productRepository.findAll();
	}

	@Override
	public Country getProductById(long productId) {

		Optional<Country> productDb = this.productRepository.findById(productId);

		if (productDb.isPresent()) {
			return productDb.get();
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + productId);
		}
	}

	@Override
	public void deleteProduct(long productId) {
		Optional<Country> productDb = this.productRepository.findById(productId);

		if (productDb.isPresent()) {
			this.productRepository.delete(productDb.get());
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + productId);
		}

	}
	@Override
	public Country createProduct(Country product) {
		return productRepository.save(product);
	}
}
