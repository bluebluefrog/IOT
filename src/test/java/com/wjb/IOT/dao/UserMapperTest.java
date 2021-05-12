package com.wjb.IOT.dao;

import com.wjb.IOT.model.pojo.User;

public class UserMapperTest {

    public static void main(String[] args) {
        UserMapper userMapper = new UserMapper();
//        userMapper.update("wwwwwwwwww","test","123431212356");
        User singleUser = userMapper.getSingleUser("wjb", 0);
        System.out.println(singleUser.getUsername());
    }

}