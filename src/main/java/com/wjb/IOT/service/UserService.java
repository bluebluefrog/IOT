package com.wjb.IOT.service;

import com.wjb.IOT.dao.UserMapper;
import com.wjb.IOT.exception.BussinessException;
import com.wjb.IOT.model.pojo.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private UserMapper userMapper = new UserMapper();

    public User checkLogin(String username, String password) {
        User duser = userMapper.getSingleUser(username,0);
        if (duser == null) {
            throw new BussinessException("n001", "user not exist");
        }
        if(!password.equals(duser.getPassword())){
            throw new BussinessException("n002", "password wrong");
        }
        return duser;
    }

    public User register(User user) {
        User userByUserName = userMapper.getSingleUser(user.getUsername(),0);
        if (userByUserName.getUsername() != null) {

            throw new RuntimeException("User exists！");
        }
        userMapper.register(user);
        User curuser = checkLogin(user.getUsername(),user.getPassword());
        return user;
    }

    public User showUpdate(String username,int userId) {
        User showupdate = userMapper.getSingleUser(username,userId);
        return showupdate;
    }

    public User update(User user) {
        User userByUserName = userMapper.getSingleUser(user.getUsername(),0);
        userMapper.update(user);
        return userByUserName;
    }

    public List<User> listUserForAdmin() {
        ArrayList<User> userList =userMapper.listAllUser();
        return userList;
    }

    public void deleteUser(int userId) {
        userMapper.delete(userId);
    }

    public User getSingleUser(String userName) {
        User singleUser = userMapper.getSingleUser(userName, 0);
        return singleUser;
    }
}
