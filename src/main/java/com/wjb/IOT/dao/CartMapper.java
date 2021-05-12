package com.wjb.IOT.dao;

import com.wjb.IOT.model.pojo.Cart;
import com.wjb.IOT.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartMapper {

    public List<Cart> showUserCart(int userId) {
        String sql="select * from cart where user_id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Cart> list = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            connection = DbUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int cartId = resultSet.getInt("cart_id");
                int productId = resultSet.getInt("product_id");
                int uId = resultSet.getInt("user_id");
                int quantity = resultSet.getInt("quantity");
                Cart cart = new Cart();
                cart.setCartId(cartId);
                cart.setProductId(productId);
                cart.setUserId(userId);
                cart.setQuantity(quantity);
                list.add(cart);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConnection(resultSet,preparedStatement,connection);
        }
        return list;
    }

    public Cart showSingleUserCart(int userId,int productId) {
        String sql="select * from cart where user_id=? and product_id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Cart cart = new Cart();
        try {
            connection = DbUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,userId);
            preparedStatement.setInt(2,productId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int cartId = resultSet.getInt("cart_id");
                int proId = resultSet.getInt("product_id");
                int uId = resultSet.getInt("user_id");
                int quantity = resultSet.getInt("quantity");
                cart.setCartId(cartId);
                cart.setProductId(productId);
                cart.setUserId(userId);
                cart.setQuantity(quantity);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConnection(resultSet,preparedStatement,connection);
        }
        return cart;
    }

    public void addCart(Cart cart) {
        String sql="insert into cart(product_id,user_id,quantity) values(?,?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DbUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,cart.getProductId());
            preparedStatement.setInt(2,cart.getUserId());
            preparedStatement.setInt(3, cart.getQuantity());
            int count = preparedStatement.executeUpdate();
            if (count < 1) {
                throw new RuntimeException("cart create fail");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConnection(null,preparedStatement,connection);
        }
    }

    public void updateCart(Cart cart) {
        String sql="update cart set product_id=?,user_id=?,quantity=? where cart_id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DbUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,cart.getProductId());
            preparedStatement.setInt(2,cart.getUserId());
            preparedStatement.setInt(3, cart.getQuantity());
            preparedStatement.setInt(4, cart.getCartId());
            int count = preparedStatement.executeUpdate();
            if (count < 1) {
                throw new RuntimeException("update create fail");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConnection(null,preparedStatement,connection);
        }
    }

    public void deleteCart(int cartId) {
        String sql="delete from cart where cart_id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DbUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,cartId);
            int count = preparedStatement.executeUpdate();
            if (count < 1) {
                throw new RuntimeException("delete create fail");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConnection(null,preparedStatement,connection);
        }
    }
}
