package com.wjb.IOT.dao;

import com.sun.tools.corba.se.idl.constExpr.Or;
import com.wjb.IOT.model.pojo.Order;
import com.wjb.IOT.model.pojo.OrderItem;
import com.wjb.IOT.model.pojo.Product;
import com.wjb.IOT.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public void createOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO order_item(order_no, product_id, product_name, product_img, unit_price, quantity, total_price) VALUES (?,?,?,?,?,?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement =null;
        try{
            connection = DbUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,orderItem.getOrderNo());
            preparedStatement.setInt(2, orderItem.getProductId());
            preparedStatement.setString(3, orderItem.getProductName());
            preparedStatement.setString(4, orderItem.getProductImg());
            preparedStatement.setInt(5, orderItem.getUnitPrice());
            preparedStatement.setInt(6, orderItem.getQuantity());
            preparedStatement.setInt(7, orderItem.getTotalPrice());
            int count = preparedStatement.executeUpdate();
            if (count < 1) {
                throw new RuntimeException("create order item fail");
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConnection(null,preparedStatement,connection);
        }
    }

    public void createOrder(Order order) {
        String sql = "INSERT INTO `order`(`order_no`, `user_id`, `total_price`, `receiver_name`, `receiver_mobile`, `receiver_address`, `order_status`) VALUES (?,?,?,?,?,?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement =null;
        try{
            connection = DbUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,order.getOrderNo());
            preparedStatement.setInt(2, order.getUserId());
            preparedStatement.setInt(3,order.getTotalPrice());
            preparedStatement.setString(4, order.getReceiverName());
            preparedStatement.setString(5, order.getReceiverMobile());
            preparedStatement.setString(6, order.getReceiverAddress());
            preparedStatement.setInt(7, order.getOrderStatus());
//            preparedStatement.setTimestamp(8,null);
//            preparedStatement.setTimestamp(9,null);
//            preparedStatement.setTimestamp(10,null);
            int count = preparedStatement.executeUpdate();
            if (count < 1) {
                throw new RuntimeException("create order fail");
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConnection(null,preparedStatement,connection);
        }
    }

    public void updateOrder(Order order) {
        String sql = "update `order` set `order_no`=?, `user_id`=?, `total_price`=?, `receiver_name`=?, `receiver_mobile`=?, `receiver_address`=?, `order_status`=? where order_id=?";
        Connection connection = null;
        PreparedStatement preparedStatement =null;
        try{
            connection = DbUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,order.getOrderNo());
            preparedStatement.setInt(2, order.getUserId());
            preparedStatement.setInt(3,order.getTotalPrice());
            preparedStatement.setString(4, order.getReceiverName());
            preparedStatement.setString(5, order.getReceiverMobile());
            preparedStatement.setString(6, order.getReceiverAddress());
            preparedStatement.setInt(7, order.getOrderStatus());
            preparedStatement.setInt(8, order.getOrderId());
            int count = preparedStatement.executeUpdate();
            if (count < 1) {
                throw new RuntimeException("update order fail");
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConnection(null,preparedStatement,connection);
        }
    }

    public void deleteOrder(String orderNo) {
        String sql = "delete from `order` where order_no=?";
        Connection connection = null;
        PreparedStatement preparedStatement =null;
        try{
            connection = DbUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,orderNo);
            int count = preparedStatement.executeUpdate();
            if (count < 1) {
                throw new RuntimeException("delete order fail");
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConnection(null,preparedStatement,connection);
        }
    }

    public List<Order> selectOrderByUserId(int userId) {
        String sql = "select * from `order` where `user_id`=?";
        Connection connection = null;
        PreparedStatement preparedStatement =null;
        ResultSet resultSet = null;
        List<Order> list = new ArrayList();
        try{
            connection = DbUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                String orderNo = resultSet.getString("order_no");
                int cuId = resultSet.getInt("user_id");
                int totalPrice = resultSet.getInt("total_price");
                String receiverName = resultSet.getString("receiver_name");
                String receiverMobile = resultSet.getString("receiver_mobile");
                String receiverAddress = resultSet.getString("receiver_address");
                int orderStatus = resultSet.getInt("order_status");

                Order order = new Order();
                order.setOrderId(orderId);
                order.setOrderNo(orderNo);
                order.setUserId(cuId);
                order.setTotalPrice(totalPrice);
                order.setReceiverName(receiverName);
                order.setReceiverMobile(receiverMobile);
                order.setReceiverAddress(receiverAddress);
                order.setOrderStatus(orderStatus);
                list.add(order);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConnection(null,preparedStatement,connection);
        }
        return list;
    }

    public Order selectOrderByOrderId(int orderId) {
        String sql = "select * from `order` where `order_id`=?";
        Connection connection = null;
        PreparedStatement preparedStatement =null;
        ResultSet resultSet = null;
        Order order = new Order();
        try{
            connection = DbUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,orderId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int orId = resultSet.getInt("order_id");
                String orderNo = resultSet.getString("order_no");
                int cuId = resultSet.getInt("user_id");
                int totalPrice = resultSet.getInt("total_price");
                String receiverName = resultSet.getString("receiver_name");
                String receiverMobile = resultSet.getString("receiver_mobile");
                String receiverAddress = resultSet.getString("receiver_address");
                int orderStatus = resultSet.getInt("order_status");

                order.setOrderId(orderId);
                order.setOrderNo(orderNo);
                order.setUserId(cuId);
                order.setTotalPrice(totalPrice);
                order.setReceiverName(receiverName);
                order.setReceiverMobile(receiverMobile);
                order.setReceiverAddress(receiverAddress);
                order.setOrderStatus(orderStatus);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConnection(null,preparedStatement,connection);
        }
        return order;
    }

    public List<OrderItem> selectOrderItemByOrderNo(String orderNo) {
        String sql = "select * from `order_item` where `order_no`=?";
        Connection connection = null;
        PreparedStatement preparedStatement =null;
        ResultSet resultSet = null;
        List list = new ArrayList();
        try{
            connection = DbUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,orderNo);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int orItemId = resultSet.getInt("order_item_id");
                String orNo = resultSet.getString("order_no");
                int proId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                String productImg = resultSet.getString("product_img");
                int unitPrice = resultSet.getInt("unit_price");
                int quantity = resultSet.getInt("quantity");
                int totalPrice = resultSet.getInt("total_price");

                OrderItem orderItem = new OrderItem();
                orderItem.setOrderItemId(orItemId);
                orderItem.setOrderNo(orNo);
                orderItem.setProductId(proId);
                orderItem.setProductName(productName);
                orderItem.setProductImg(productImg);
                orderItem.setUnitPrice(unitPrice);
                orderItem.setQuantity(quantity);
                orderItem.setTotalPrice(totalPrice);
                list.add(orderItem);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConnection(resultSet,preparedStatement,connection);
        }
        return list;
    }
}
