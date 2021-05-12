package com.wjb.IOT.service;

import com.wjb.IOT.dao.ProductMapper;
import com.wjb.IOT.model.pojo.Product;
import com.wjb.IOT.utils.PageModel;

public class ProductService {

    private ProductMapper productMapper = new ProductMapper();

    public PageModel list(int page, int rows , String category) {
        if (rows == 0 || page == 0) {
            throw new RuntimeException("rows wrong");
        }
        return productMapper.list(page, rows, category);
    }

    public void createProduct(Product product) {
        if (product == null) {
            throw new RuntimeException("product is null");
        }
        productMapper.createProduct(product);
    }

    public void updateProduct(Product product) {
        if (product == null) {
            throw new RuntimeException("product is null");
        }
        productMapper.updateProduct(product);
    }

    public void deleteProduct(int productId) {

        if (productId <= 0) {
            throw new RuntimeException("wrong productId");
        }
        productMapper.deleteProduct(productId);
    }

    public Product selectProductById(int productId) {
        Product product = productMapper.selectProductById(productId);
        return product;
    }
}
