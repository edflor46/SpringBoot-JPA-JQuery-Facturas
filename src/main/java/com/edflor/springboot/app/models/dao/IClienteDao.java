package com.edflor.springboot.app.models.dao;

import java.util.List;

import com.edflor.springboot.app.models.entity.Cliente;

public interface IClienteDao {

	public List<Cliente> findAll();
	
}