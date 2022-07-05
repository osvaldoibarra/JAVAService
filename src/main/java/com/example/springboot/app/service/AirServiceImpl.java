package com.example.springboot.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot.app.exception.ResourceNotFoundException;
import com.example.springboot.app.model.Air;

import com.example.springboot.app.repository.AirRepository;

@Service //Notación para indicar que es un servicio
@Transactional //define que un conjunto de instrucciones que se ejecutan en bloque asegura y valida el metodo termine correctamente antes de ejecutar algun otro metodo
public class AirServiceImpl implements AirService {

	//Inyección de dependecias (crea una instancia cuando lo requiera)
	@Autowired
	private AirRepository productRepository;


	@Override
	public Air updateProduct(Air product) {
		Optional<Air> productDb = this.productRepository.findById(product.getId());

		if (productDb.isPresent()) {
			Air productUpdate = productDb.get();
			productUpdate.setId(product.getId());
			productUpdate.setName(product.getName());
			productRepository.save(productUpdate);
			return productUpdate;
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + product.getId());
		}
	}

	@Override
	public List<Air> getAllProduct() {
		return this.productRepository.findAll();
	}

	@Override
	public Air getProductById(long productId) {

		Optional<Air> productDb = this.productRepository.findById(productId);

		if (productDb.isPresent()) {
			return productDb.get();
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + productId);
		}
	}

	@Override
	public void deleteProduct(long productId) {
		Optional<Air> productDb = this.productRepository.findById(productId);

		if (productDb.isPresent()) {
			this.productRepository.delete(productDb.get());
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + productId);
		}

	}
	@Override
	public Air createProduct(Air product) {
		return productRepository.save(product);
	}
}
