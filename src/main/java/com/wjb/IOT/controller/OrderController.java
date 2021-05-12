package com.wjb.IOT.controller;

import com.wjb.IOT.model.pojo.Order;
import com.wjb.IOT.model.pojo.User;
import com.wjb.IOT.service.OrderService;
import com.wjb.IOT.service.UserService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/order")
public class OrderController extends HttpServlet {
    private OrderService orderService = new OrderService();

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        String c = req.getParameter("category");
        if (method.equals("list")) {
            list(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String method = req.getParameter("method");
        if (method.equals("create")) {
            create(req, resp);
        }
        if (method.equals("update")) {
            update(req, resp);
        }
        if (method.equals("delete")) {
            delete(req, resp);
        }
    }
    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orderList = orderService.showOrder(UserController.getLoginUser(req, resp).getUserId());
        req.setAttribute("pageData",orderList);
        req.getRequestDispatcher("/jsp/listOrder.jsp").forward(req, resp);
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = new Order();
        FileItemFactory fileItemFactory = new DiskFileItemFactory();
        ServletFileUpload sf = new ServletFileUpload(fileItemFactory);
        List<FileItem> fileItems = null;
        try {
            fileItems = sf.parseRequest(req);
            for (FileItem item:fileItems) {
                //判断是否是不是普通输入项，还是文件
                if (item.isFormField()) {
                    switch (item.getFieldName()) {
                        //需要解决中文乱码，因为上面设置的setCharacterEncoding会失效
                        case "receiverName":
                            order.setReceiverName(item.getString("UTF-8"));
                            break;
                        case "receiverMobile":
                            order.setReceiverMobile(item.getString("UTF-8"));
                            break;
                        case "receiverAddress":
                            order.setReceiverAddress(item.getString("UTF-8"));
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        order.setUserId(UserController.getLoginUser(req,resp).getUserId());
        orderService.addOrder(order);
        req.getRequestDispatcher("/start.jsp").forward(req, resp);
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("id");
        String option = req.getParameter("option");
        Order orderByOrderId = orderService.getOrderByOrderId(Integer.parseInt(orderId));

        if (option.equals("pay")) {
            User loginUser = UserController.getLoginUser(req, resp);
            long totalPrice=loginUser.getBalence() - (long)orderByOrderId.getTotalPrice();
            if (totalPrice < 0) {
                throw new RuntimeException("Balence not enough");
            }
            orderByOrderId.setOrderStatus(2);
            orderService.updateOrderByOrderId(orderByOrderId);
            loginUser.setBalence(totalPrice);
            userService.update(loginUser);
        }
        if (option.equals("cancel")) {
            orderByOrderId.setOrderStatus(3);
            orderService.updateOrderByOrderId(orderByOrderId);
        }
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) {
        String orderNo = req.getParameter("orderNo");
        orderService.deleteOrder(orderNo);
    }

}
