package com.wjb.IOT.dao;

import com.wjb.IOT.model.pojo.Order;
import com.wjb.IOT.model.pojo.OrderItem;

import java.util.List;

public class OrderMapperTest {
    public static void main(String[] args) {
        OrderMapper orderMapper = new OrderMapper();
        Order order = new Order();
        OrderItem orderItem = new OrderItem();
        order.setOrderId(1);
        order.setOrderNo("12311");
        order.setUserId(12);
        order.setOrderStatus(1);
        order.setReceiverAddress("testAddress");
        order.setReceiverMobile("test");
        order.setReceiverName("tese");
        order.setTotalPrice(100);
        orderMapper.createOrder(order);
        orderMapper.updateOrder(order);
        orderItem.setOrderNo("test1");
        orderItem.setProductId(1);
        orderItem.setProductName("test");
        orderItem.setProductImg("/upload/");
        orderItem.setUnitPrice(1);
        orderItem.setQuantity(1);
        orderItem.setTotalPrice(1);
        orderMapper.createOrderItem(orderItem);
        List<Order> orders = orderMapper.selectOrderByUserId(12);
        for (Order or :
                orders) {
            System.out.println(or.toString());
        }
//        orderMapper.deleteOrder(1);
    }
}
