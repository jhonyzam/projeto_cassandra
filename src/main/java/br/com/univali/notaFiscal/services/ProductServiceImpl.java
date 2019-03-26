package br.com.univali.notaFiscal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.univali.notaFiscal.commands.ProductForm;
import br.com.univali.notaFiscal.converters.ProductFormToProduct;
import br.com.univali.notaFiscal.domain.Product;
import br.com.univali.notaFiscal.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by jt on 1/10/17.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductFormToProduct productFormToProduct;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductFormToProduct productFormToProduct) {
        this.productRepository = productRepository;
        this.productFormToProduct = productFormToProduct;
    }


    @Override
    public List<Product> listAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add); //fun with Java 8
        return products;
    }

    @Override
    public Product getById(UUID id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    public void delete(UUID id) {
        productRepository.deleteById(id);

    }

    @Override
    public Product saveOrUpdateProductForm(ProductForm productForm) {
        Product savedProduct = saveOrUpdate(productFormToProduct.convert(productForm));

        System.out.println("Saved Product Id: " + savedProduct.getId());
        return savedProduct;
    }
}
