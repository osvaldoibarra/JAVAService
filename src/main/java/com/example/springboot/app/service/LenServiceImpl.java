package com.example.springboot.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot.app.exception.ResourceNotFoundException;
import com.example.springboot.app.model.Len;
import com.example.springboot.app.repository.LenRepository;

@Service //Notación para indicar que es un servicio
@Transactional //define que un conjunto de instrucciones que se ejecutan en bloque asegura y valida el metodo termine correctamente antes de ejecutar algun otro metodo
public class LenServiceImpl implements LenService {

	//Inyección de dependecias (crea una instancia cuando lo requiera)
	@Autowired
	private LenRepository productRepository;


	@Override
	public Len updateProduct(Len product) {
		Optional<Len> productDb = this.productRepository.findById(product.getId());

		if (productDb.isPresent()) {
			Len productUpdate = productDb.get();
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
	public List<Len> getAllProduct() {
		return this.productRepository.findAll();
	}

	@Override
	public Len getProductById(long productId) {

		Optional<Len> productDb = this.productRepository.findById(productId);

		if (productDb.isPresent()) {
			return productDb.get();
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + productId);
		}
	}

	@Override
	public void deleteProduct(long productId) {
		Optional<Len> productDb = this.productRepository.findById(productId);

		if (productDb.isPresent()) {
			this.productRepository.delete(productDb.get());
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + productId);
		}
	}
	@Override
	public Len createProduct(Len product) {
		return productRepository.save(product);
	}
}
