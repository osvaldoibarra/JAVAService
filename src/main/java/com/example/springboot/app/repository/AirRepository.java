package com.example.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.app.model.Air;

//Notación para indicar que es un repositorio
@Repository
public interface AirRepository extends JpaRepository<Air, Long> {
	// Con @Repository le indico los metodos principales select, create, update, delete
}