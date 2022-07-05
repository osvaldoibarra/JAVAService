package com.example.springboot.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.app.exception.Mensaje;
import com.example.springboot.app.model.Country;
import com.example.springboot.app.service.CountryService;

@RestController //Notación para indicar que es un controlador de tipo Rest y que puede interceptar peticiones al servidor.
@RequestMapping("/apiv1")//Notación para mapear los endpoint (las urls de nueestra API) es decir /products/nombredelServicio
public class CountryController {

	//Inyección de dependencias
	@Autowired
	private CountryService productService; //Contiene los metodos del CRUD que va a poder utilizar nuestra apliacion

	//Definimos que a nuestro metodo solo se podra aceder a traves de una peticion de tipo GET y el nombre de la ruta URL
	@GetMapping("/listacountry")
	public ResponseEntity<?> getAllProduct() {
		List<Country> lista = productService.getAllProduct();
		if(lista.isEmpty()){
			return new ResponseEntity<>(new Mensaje("Sin Ciudades en la BD"), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(productService.getAllProduct());
	}

	/* Definimos que a nuestro metodo solo se podra aceder a traves de una peticion de tipo GET y el nombre de la ruta URL
	 * ademas dentro dentro de las laves {} espera un parametro, el cual debe ser el numero de ID del producto
	 * del cual queremos ver el detalle de sus campos en la BD
	 */
	@GetMapping("/detallecountry/{id}")
	public ResponseEntity<Country> getProductById(@PathVariable long id) {
		return ResponseEntity.ok().body(productService.getProductById(id));
	}
		
	/**
	 * Para actuliazar los datos de un producto es a traves de una peticion de tipo PUT
	 * Este metodo realizar una actualizacion, por lo que al nombre de la ruta, espera entre llaves
	 * El Id del producto a actualizar
	 * 
	 * @param id
	 * @param product
	 * @return
	 */
	@PutMapping("/actualizacountry/{id}")
	public ResponseEntity<Country> updateProduct(@PathVariable long id, @RequestBody Country product) {
		product.setId(id);
		return ResponseEntity.ok().body(this.productService.updateProduct(product));
	}

	/**
	 * Para eliminar un producto de la Base de Datos se deb hacer mediante una peticion de tipo DELETE
	 * Este metodo realizar una eliminiacion del producto, por lo que al nombre de la ruta, espera entre llaves
	 * El Id del producto a eliminar
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/eliminacountry/{id}")
	public HttpStatus deleteProduct(@PathVariable long id) {
		this.productService.deleteProduct(id);
		return HttpStatus.OK;
	}
	@PostMapping("/country/add")
	public ResponseEntity<Country> createProduct(@RequestBody Country product) {
		return ResponseEntity.ok().body(this.productService.createProduct(product));
	}
}