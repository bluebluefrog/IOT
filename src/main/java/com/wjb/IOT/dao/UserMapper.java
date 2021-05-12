package com.wjb.IOT.dao;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import com.wjb.IOT.model.pojo.User;
import com.wjb.IOT.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public ArrayList<User> listAllUser() {

        String sql="select * from user";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList list = new ArrayList();
        try {
            connection = DbUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String dusername = resultSet.getString("username");
                String dpassword = resultSet.getString("password");
                String demail = resultSet.getString("email");
                String dcommon = resultSet.getString("common");
                String dimg = resultSet.getString("img");
                Long dbalence = resultSet.getLong("balence");
                int drole = resultSet.getInt("role");
                User user = new User();
                user.setUserId(userId);
                user.setUsername(dusername);
                user.setPassword(dpassword);
                user.setEmail(demail);
                user.setCommon(dcommon);
                user.setImg(dimg);
                user.setBalence(dbalence);
                user.setRole(drole);
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConnection(resultSet, preparedStatement, connection);
        }
        return list;
    }

    public User getSingleUser(String username,int userId) {
        User user = new User();
        String sql;
        if (userId == 0) {
            sql = "select * from user where username=?";
        }else {
            sql="select * from user where user_id=?";
        }
        try {
            Connection connection = DbUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            if (userId == 0) {
                preparedStatement.setString(1, username);
            } else {
                preparedStatement.setInt(1, userId);
            }
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int uId = resultSet.getInt("user_id");
                String dusername = resultSet.getString("username");
                String dpassword = resultSet.getString("password");
                String demail = resultSet.getString("email");
                String dcommon = resultSet.getString("common");
                String dimg = resultSet.getString("img");
                Long dbalence = resultSet.getLong("balence");
                int drole = resultSet.getInt("role");
                user.setUserId(uId);
                user.setUsername(dusername);
                user.setPassword(dpassword);
                user.setEmail(demail);
                user.setCommon(dcommon);
                user.setImg(dimg);
                user.setBalence(dbalence);
                user.setRole(drole);
            }
            DbUtils.closeConnection(resultSet,preparedStatement,connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }
    public void register(User user){
        String email = user.getEmail();
        String username = user.getUsername();
        String password = user.getPassword();
        String common = user.getCommon();
        String img = user.getImg();
        Long balence = user.getBalence();

        String sql="insert into user(email,username,password,role,common,img,balence) values(?,?,?,?,?,?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement =null;
        try {
            connection = DbUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setInt(4,1);
            preparedStatement.setString(5,common);
            preparedStatement.setString(6,img);
            preparedStatement.setLong(7,balence);
            int cnt = preparedStatement.executeUpdate();
            if (cnt < 0) {
                throw new RuntimeException("register fail");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConnection(null,preparedStatement,connection);
        }
    }


    public void update(User user) {
        String email = user.getEmail();
        String username = user.getUsername();
        String password = user.getPassword();
        String common = user.getCommon();
        String img = user.getImg();
        Long balence = user.getBalence();

        String sql="update user set email=?,password=?,common=?,img=?,balence=? where username=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DbUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3,common);
            preparedStatement.setString(4,img);
            preparedStatement.setLong(5,balence);
            preparedStatement.setString(6, username);
            int cnt = preparedStatement.executeUpdate();
            if (cnt < 0) {
                throw new RuntimeException("update fail");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConnection(null,preparedStatement,connection);
        }
    }

    public void delete(int userId) {

        String sql="delete from user where user_id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DbUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            int cnt = preparedStatement.executeUpdate();
            if (cnt < 0) {
                throw new RuntimeException("delete fail");
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
