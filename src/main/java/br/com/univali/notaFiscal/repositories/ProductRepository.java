package br.com.univali.notaFiscal.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.univali.notaFiscal.domain.Product;

import java.util.UUID;

public interface ProductRepository extends CrudRepository<Product, UUID> {
	
}
