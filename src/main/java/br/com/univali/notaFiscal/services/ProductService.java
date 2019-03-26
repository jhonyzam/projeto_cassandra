package br.com.univali.notaFiscal.services;

import java.util.List;
import java.util.UUID;

import br.com.univali.notaFiscal.commands.ProductForm;
import br.com.univali.notaFiscal.domain.Product;

/**
 * Created by jt on 1/10/17.
 */
public interface ProductService {

    List<Product> listAll();

    Product getById(UUID id);

    Product saveOrUpdate(Product product);

    void delete(UUID id);

    Product saveOrUpdateProductForm(ProductForm productForm);
}
