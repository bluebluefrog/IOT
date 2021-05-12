package com.wjb.IOT.service;

import com.wjb.IOT.dao.CartMapper;
import com.wjb.IOT.dao.ProductMapper;
import com.wjb.IOT.model.pojo.Cart;
import com.wjb.IOT.model.pojo.Product;

import java.util.ArrayList;
import java.util.List;

public class CartService {
    CartMapper cartMapper = new CartMapper();

    ProductMapper productMapper = new ProductMapper();

    public void addCart(Cart cart) {
        Product product = productMapper.selectProductById(cart.getProductId());
        Cart productInCart= cartMapper.showSingleUserCart(cart.getUserId(), cart.getProductId());
        if (productInCart.getQuantity() == null) {
            productInCart.setQuantity(0);
        }
        int amount = product.getStock() - productInCart.getQuantity();
        if (product.getStock() < cart.getQuantity() || amount <= 0) {
            throw new RuntimeException("product stock not enough");
        }
        if (cart == null) {
            throw new RuntimeException("cart is null");
        }
        if (productInCart.getCartId() != null) {
            productInCart.setQuantity(productInCart.getQuantity()+1);
            cartMapper.updateCart(productInCart);
        }
        if(productInCart.getCartId()==null) {
            cartMapper.addCart(cart);
        }
    }

    public void updateCart(Cart cart) {
        Product product = productMapper.selectProductById(cart.getProductId());
        Cart productInCart= cartMapper.showSingleUserCart(cart.getUserId(), cart.getProductId());
        if (productInCart.getQuantity() == null) {
            productInCart.setQuantity(0);
        }
        int amount = product.getStock() - productInCart.getQuantity();
        if (product.getStock() < cart.getQuantity() || amount <= 0) {
            throw new RuntimeException("product stock not enough");
        }
        if (cart == null) {
            throw new RuntimeException("cart is null");
        }
        cartMapper.updateCart(cart);
    }

    public List<Cart> showUserCart(int userId) {
        List<Cart> enableCartList = new ArrayList<>();
        if (userId <= 0) {
            throw new RuntimeException("wrong userId");
        }
        List<Cart> cartList = cartMapper.showUserCart(userId);
        for (Cart cart:cartList) {
            if (cart.getQuantity() >= 1) {
                enableCartList.add(cart);
                Product product = productMapper.selectProductById(cart.getProductId());
                cart.setProduct(product);
            }else {
                cartMapper.deleteCart(cart.getCartId());
            }
        }
        return enableCartList;
    }

    public Cart showUserCartWithSingleProduct(int userId,int productId) {
        if (userId <= 0) {
            throw new RuntimeException("wrong userId");
        }
        return cartMapper.showSingleUserCart(userId, productId);
    }
}
