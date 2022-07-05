package com.example.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.app.model.Country;

//Notación para indicar que es un repositorio
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
	// Con @Repository le indico los metodos principales select, create, update, delete
}