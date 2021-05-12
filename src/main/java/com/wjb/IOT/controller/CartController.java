package com.wjb.IOT.controller;

import com.wjb.IOT.model.pojo.Cart;
import com.wjb.IOT.model.pojo.User;
import com.wjb.IOT.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class CartController extends HttpServlet {
    private CartService cartService = new CartService();

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
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) {
        Cart cart = new Cart();
        String productId = req.getParameter("productId");
        cart.setProductId(Integer.parseInt(productId));
        cart.setUserId(UserController.getLoginUser(req,resp).getUserId());
        cart.setQuantity(1);
        cartService.addCart(cart);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) {
        Cart cart = new Cart();
        String productId = req.getParameter("id");
        String option = req.getParameter("option");
        User loginUser = UserController.getLoginUser(req, resp);
        Cart singleCartProduct = cartService.showUserCartWithSingleProduct(loginUser.getUserId(), Integer.parseInt(productId));
        if (option.equals("plus")) {
            singleCartProduct.setQuantity(singleCartProduct.getQuantity() + 1);
        }if (option.equals("minus")) {
            singleCartProduct.setQuantity(singleCartProduct.getQuantity() - 1);
        }
        cartService.updateCart(singleCartProduct);
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Cart> cartList = cartService.showUserCart(UserController.getLoginUser(req, resp).getUserId());
        req.setAttribute("pageData", cartList);
        req.getRequestDispatcher("/jsp/listCart.jsp").forward(req,resp);
    }
}
