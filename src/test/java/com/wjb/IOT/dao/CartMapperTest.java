package com.wjb.IOT.dao;

import com.wjb.IOT.model.pojo.Cart;

import java.util.List;

public class CartMapperTest {
    public static void main(String[] args) {
        CartMapper cartMapper = new CartMapper();
        Cart cart = new Cart();
        cart.setProductId(1);
        cart.setUserId(1);
        cart.setQuantity(1);
        cartMapper.addCart(cart);
        cart.setCartId(1);
        cart.setQuantity(2);
        cartMapper.updateCart(cart);
        List<Cart> carts = cartMapper.showUserCart(1);
        System.out.println(carts);
    }
}
