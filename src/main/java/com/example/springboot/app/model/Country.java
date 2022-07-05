package com.example.springboot.app.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity//Notación que indica que es una entidad y permite que JPA tome esta clase de Java y pueda interpetara como una tabla en SQL
@Table(name = "Country")//Indica el nombre de la tabla, que JPA creara en la Base de Datos
public class Country {

    @Id //Llave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.AUTO) //Se le indica que el campo ID es Autonumerico
    private long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}