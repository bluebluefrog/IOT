package com.wjb.IOT.utils;

import java.util.Date;
import java.util.Timer;
import java.util.UUID;

public class OrderNumberUtils {

    public static String createOrderNumber() {
        StringBuilder sb = new StringBuilder();
        UUID uuid = UUID.randomUUID();
        Date date = new Date();
        long time = date.getTime();
        sb.append(uuid.toString() + time);
        return sb.toString();
    }

    public static void main(String[] args) {
        String orderNumber = createOrderNumber();
        System.out.println(orderNumber);
    }
}
