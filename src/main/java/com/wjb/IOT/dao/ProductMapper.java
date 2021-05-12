package com.wjb.IOT.dao;
import com.wjb.IOT.model.pojo.Product;
import com.wjb.IOT.utils.DbUtils;
import com.wjb.IOT.utils.PageModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductMapper {

    public PageModel list(int page, int rows, String category) {
        String sql = null;
        if (category == null) {
            sql = "select * from product limit ?,10 ";
        } else {
            sql = "select * from product where product_id= ? limit ?,10 ";
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Product> list = new ArrayList();
        try {
             connection = DbUtils.getConnection();
             preparedStatement = connection.prepareStatement(sql);
            if (category != null) {
                preparedStatement.setInt(1,Integer.parseInt(category));
                preparedStatement.setInt(2,(page-1)*10);
            }
            else {
                preparedStatement.setInt(1,(page-1)*10);
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int pid = resultSet.getInt("product_id");
                String pname = resultSet.getString("name");
                String image = resultSet.getString("image");
                String detail = resultSet.getString("detail");
                int cid = resultSet.getInt("category_id");
                int price = resultSet.getInt("price");
                int stock = resultSet.getInt("stock");
                int status = resultSet.getInt("status");
                Product product = new Product();
                product.setProductId(pid);
                product.setName(pname);
                product.setImage(image);
                product.setDetail(detail);
                product.setCategoryId(cid);
                product.setPrice(price);
                product.setStock(stock);
                product.setStatus(status);
                list.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConnection(resultSet,preparedStatement,connection);
        }
        PageModel pageModel = new PageModel(list, page, rows);
        return pageModel;
    }

    public void createProduct(Product product) {
        String sql="INSERT INTO product(name, image, detail, category_id, price, stock, status) VALUES (?,?,?,?,?,?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement =null;
        try {
            connection = DbUtils.getConnection();
            preparedStatement  = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getImage());
            preparedStatement.setString(3, product.getDetail());
            preparedStatement.setInt(4, product.getCategoryId());
            preparedStatement.setInt(5,product.getPrice() );
            preparedStatement.setInt(6, product.getStock());
            preparedStatement.setInt(7,product.getStatus());
            int count = preparedStatement.executeUpdate();
            if (count < 1) {
                throw new RuntimeException("Product create fail");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConnection(null,preparedStatement,connection);
        }
    }

    public void updateProduct(Product product) {

        String sql="update product set name=?, image=?, detail=?, category_id=?, price=?, stock=?, status=? where product_id=?";
        Connection connection = null;
        PreparedStatement preparedStatement =null;
        try {
            connection = DbUtils.getConnection();
            preparedStatement  = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getImage());
            preparedStatement.setString(3, product.getDetail());
            preparedStatement.setInt(4, product.getCategoryId());
            preparedStatement.setInt(5,product.getPrice() );
            preparedStatement.setInt(6, product.getStock());
            preparedStatement.setInt(7,product.getStatus());
            preparedStatement.setInt(8,product.getProductId());
            int count = preparedStatement.executeUpdate();
            if (count < 1) {
                throw new RuntimeException("Product update fail");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConnection(null,preparedStatement,connection);
        }
    }

    public void deleteProduct(int productId) {
        String sql="delete from product where product_id=?";
        Connection connection = null;
        PreparedStatement preparedStatement =null;
        try {
            connection = DbUtils.getConnection();
            preparedStatement  = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);
            int count = preparedStatement.executeUpdate();
            if (count < 1) {
                throw new RuntimeException("Product delete fail");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConnection(null,preparedStatement,connection);
        }
    }

    public Product selectProductById(int productId) {
        String sql="select * from product where product_id=?";
        Connection connection = null;
        PreparedStatement preparedStatement =null;
        Product product = new Product();
        ResultSet resultSet = null;
        try {
            connection = DbUtils.getConnection();
            preparedStatement  = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int pid = resultSet.getInt("product_id");
                String pname = resultSet.getString("name");
                String image = resultSet.getString("image");
                String detail = resultSet.getString("detail");
                int cid = resultSet.getInt("category_id");
                int price = resultSet.getInt("price");
                int stock = resultSet.getInt("stock");
                int status = resultSet.getInt("status");
                product.setProductId(pid);
                product.setName(pname);
                product.setImage(image);
                product.setDetail(detail);
                product.setCategoryId(cid);
                product.setPrice(price);
                product.setStock(stock);
                product.setStatus(status);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConnection(resultSet,preparedStatement,connection);
        }
        return product;
    }
}
