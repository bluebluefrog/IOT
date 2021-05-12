package com.wjb.IOT.service;

import com.wjb.IOT.dao.CartMapper;
import com.wjb.IOT.dao.OrderMapper;
import com.wjb.IOT.dao.ProductMapper;
import com.wjb.IOT.model.pojo.Cart;
import com.wjb.IOT.model.pojo.Order;
import com.wjb.IOT.model.pojo.OrderItem;
import com.wjb.IOT.model.pojo.Product;
import com.wjb.IOT.utils.OrderNumberUtils;

import java.util.List;

public class OrderService {
    OrderMapper orderMapper = new OrderMapper();

    CartMapper cartMapper = new CartMapper();

    ProductMapper productMapper = new ProductMapper();

    public void addOrder(Order order) {
        if (order == null) {
            throw new RuntimeException("order is null");
        }
        List<Cart> cartList = cartMapper.showUserCart(order.getUserId());
        String orderNumber = OrderNumberUtils.createOrderNumber();
        int totalPrice=0;
        for (Cart cart : cartList) {
            Product cartProduct = productMapper.selectProductById(cart.getProductId());
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(cartProduct.getProductId());
            orderItem.setProductName(cartProduct.getName());
            orderItem.setTotalPrice(cartProduct.getPrice() * cart.getQuantity());
            orderItem.setUnitPrice(cartProduct.getPrice());
            orderItem.setQuantity(cart.getQuantity());
            orderItem.setProductImg(cartProduct.getImage());
            orderItem.setOrderNo(orderNumber);
            totalPrice += cartProduct.getPrice() * cart.getQuantity();
            orderMapper.createOrderItem(orderItem);
            cartProduct.setStock(cartProduct.getStock()-cart.getQuantity());
            productMapper.updateProduct(cartProduct);
            cartMapper.deleteCart(cart.getCartId());
        }
        order.setTotalPrice(totalPrice);
        order.setOrderStatus(1);
        order.setOrderNo(orderNumber);
        orderMapper.createOrder(order);
    }

    public void deleteOrder(String orderNo) {
        List<OrderItem> orderItems = orderMapper.selectOrderItemByOrderNo(orderNo);
        for (OrderItem orderItem:orderItems) {
            Product product = productMapper.selectProductById(orderItem.getProductId());
            product.setStock(product.getStock() + orderItem.getQuantity());
            productMapper.updateProduct(product);
        }
        if (orderNo==null) {
            throw new RuntimeException("no orderNo");
        }
        orderMapper.deleteOrder(orderNo);
    }

    public List<Order> showOrder(int userId) {
        if (userId <= 0) {
            throw new RuntimeException("no userId");
        }
        List<Order> orders = orderMapper.selectOrderByUserId(userId);
        return orders;
    }

    public Order getOrderByOrderId(int orderId) {
        Order order = orderMapper.selectOrderByOrderId(orderId);
        return order;
    }

    public void updateOrderByOrderId(Order order) {
        orderMapper.updateOrder(order);
    }
}
