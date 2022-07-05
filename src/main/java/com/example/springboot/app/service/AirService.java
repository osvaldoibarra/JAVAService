package com.example.springboot.app.service;

import java.util.List;

import com.example.springboot.app.model.Air;


/**
 * Interfaz con los metodos que seran utilizados para relizar los metodos de un CRUD
 * @author Usuario
 *
 */
public interface AirService {


	/**
	 * Metodo para actualizar  un producto el cual espera como parametro un objeto de la clase Product
	 * Este Objeto debe contener los nuevos datos del producto. que se va a actualizar 
	 * 
	 * @param product
	 * @return
	 */
	Air updateProduct(Air product);
	
	/**
	 * Obtiene de la Base de datos todos los productos registrados en la tabla products
	 * 
	 * @return
	 */
	List<Air> getAllProduct();

	/**
	 * Obtiene el detale de un producto en especifico a traves de su ID
	 * 
	 * @param productId
	 * @return
	 */
	Air getProductById(long productId);

	/**
	 * Elimina un producto de la tabla products, a traves de su ID
	 * @param id
	 */
	void deleteProduct(long id);
	Air createProduct(Air product);
}
