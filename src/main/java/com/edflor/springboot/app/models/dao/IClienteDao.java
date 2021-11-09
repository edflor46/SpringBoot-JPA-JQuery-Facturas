package com.edflor.springboot.app.models.dao;

import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

//import java.util.List;

import com.edflor.springboot.app.models.entity.Cliente;

public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long>{

	/*
	 * public List<Cliente> findAll();
	 * 
	 * public void save(Cliente cliente);
	 * 
	 * public Cliente findOne(Long id);
	 * 
	 * public void delete(Long id);
	 */
	
	@Query("select c from Cliente c left join fetch c.facturas f where c.id=?1")
	public Cliente fetchByIdWhithFacturas(Long id);
}
