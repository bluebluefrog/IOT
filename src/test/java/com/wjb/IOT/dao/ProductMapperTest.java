package com.wjb.IOT.dao;

import com.wjb.IOT.model.pojo.Product;

public class ProductMapperTest {

    public static void main(String[] args) {
        ProductMapper productMapper = new ProductMapper();
        Product product = new Product();

        System.out.println(product.getProductId());
        System.out.println(product);
        product.setName("laptop");
        product.setImage("/upload/");
        product.setDetail("1");
        product.setCategoryId(1);
        product.setPrice(100);
        product.setStock(100);
        product.setStatus(1);
        productMapper.createProduct(product);
        product.setName("laptop2");
        product.setProductId(6);
        productMapper.updateProduct(product);
        productMapper.deleteProduct(6);
        product = productMapper.selectProductById(1);
        product = productMapper.selectProductById(16);
        System.out.println(product);
    }
}