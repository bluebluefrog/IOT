package com.wjb.IOT.controller;

import com.wjb.IOT.model.pojo.User;
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
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * warren servlet test
 */
@WebServlet("/user")
public class UserController extends HttpServlet{

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if(method.equals("listUser")){
            listUserForAdmin(req,resp);
        }
        if (method.equals("getSingleUser")) {
            getSingleUser(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String method = req.getParameter("method");
        if(method.equals("check_login")){
            doLogin(req,resp);
        } else if (method.equals("register")) {
            doRegister(req,resp);
        }else if(method.equals("showupdate")){
            showUpdate(req, resp);
        }else if(method.equals("update")){
            update(req, resp);
        } else if (method.equals("logout")) {
            logout(req,resp);
        }else if (method.equals("delete")) {
            delete(req,resp);
        }
    }

    private void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("uname");
        String password = req.getParameter("psw");
        User user = userService.checkLogin(username, password);
//        req.setAttribute("user", user);

        HttpSession session = req.getSession();
        session.setAttribute("user", user);

//        ServletContext servletContext = req.getServletContext();
//        servletContext.setAttribute("user", user);
//
        req.getRequestDispatcher("/start.jsp").forward(req, resp);
    }

    private void doRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException{
          User user = new User();
//        String email = req.getParameter("email");
//        String username = req.getParameter("username");
//        String password = req.getParameter("psw");
//        String img=req.getParameter("files");
        //1?????????coomon file upload
        FileItemFactory fileItemFactory = new DiskFileItemFactory();
        //FileItemFactory?????????????????????????????????????????????FileItem??????
        //ServletFileUpload?????????FileUpload????????????Java web???Http????????????
        ServletFileUpload sf = new ServletFileUpload(fileItemFactory);
        //2??????fileItem,????????????????????????????????????????????????fileitem
        List<FileItem> fileItems = null;
        try {
            fileItems = sf.parseRequest(req);
            for (FileItem item:fileItems) {
                //???????????????????????????????????????????????????
                if (item.isFormField()) {
                    switch (item.getFieldName()) {
                        //????????????????????????????????????????????????setCharacterEncoding?????????
                        case "email":
                            user.setEmail(item.getString("UTF-8"));
                            break;
                        case "username":
                            user.setUsername(item.getString("UTF-8"));
                            break;
                        case "psw":
                            user.setPassword(item.getString("UTF-8"));
                            break;
                        case "common":
                            user.setCommon(item.getString("UTF-8"));
                            break;
                        default:
                            break;
                    }
                } else {
                    //3??????????????????????????????
                    //??????????????????????????????????????????
                    String realPath = req.getServletContext().getRealPath("/upload/userphoto");
                    //????????????????????????????????????
                    String fileName = UUID.randomUUID().toString();
                    //???????????????????????????
                    String suffix = item.getName().substring(item.getName().lastIndexOf("."));
                    //???????????????????????????
                    String newName = fileName + suffix;
                    File imgfile = new File(realPath, newName);
                    item.write(imgfile);
                    user.setImg("/upload/userphoto/" +newName);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setCreateTime(new Date());
        user.setBalence(0l);
        user.setRole(1);
        User registerUser = userService.register(user);
//        registerUser.setPassword("this is not showing on webpage");

        resp.sendRedirect("/login.html");
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        resp.sendRedirect("/start.jsp");
    }

    private void showUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        String userId = req.getParameter("id");
        User curUser = userService.showUpdate(user.getUsername(),Integer.parseInt(userId));
        req.getSession().setAttribute("curuser", curUser);
        req.getRequestDispatcher("/update.jsp").forward(req,resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = new User();
//        String email = req.getParameter("email");
//        String username = req.getParameter("username");
//        String password = req.getParameter("psw");
//        String img=req.getParameter("files");
        //1?????????coomon file upload
        FileItemFactory fileItemFactory = new DiskFileItemFactory();
        //FileItemFactory?????????????????????????????????????????????FileItem??????
        //ServletFileUpload?????????FileUpload????????????Java web???Http????????????
        ServletFileUpload sf = new ServletFileUpload(fileItemFactory);
        //2??????fileItem,????????????????????????????????????????????????fileitem
        List<FileItem> fileItems = null;
        try {
            fileItems = sf.parseRequest(req);
            for (FileItem item:fileItems) {
                //???????????????????????????????????????????????????
                if (item.isFormField()) {
                    switch (item.getFieldName()) {
                        //????????????????????????????????????????????????setCharacterEncoding?????????
                        case "email":
                            user.setEmail(item.getString("UTF-8"));
                            break;
                        case "username":
                            user.setUsername(item.getString("UTF-8"));
                            break;
                        case "psw":
                            user.setPassword(item.getString("UTF-8"));
                            break;
                        case "common":
                            user.setCommon(item.getString("UTF-8"));
                            break;
                        case "balence":
                            user.setBalence(Long.parseLong(item.getString("UTF-8")));
                            break;
                        default:
                            break;
                    }
                } else {
                    //3??????????????????????????????
                    //??????????????????????????????????????????
                    String realPath = req.getServletContext().getRealPath("/upload/userphoto");
                    //????????????????????????????????????
                    String fileName = UUID.randomUUID().toString();
                    //???????????????????????????
                    String suffix = item.getName().substring(item.getName().lastIndexOf("."));
                    //???????????????????????????
                    String newName = fileName + suffix;
                    item.write(new File(realPath, newName));
                    user.setImg("/upload/userphoto/" +newName);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user.getBalence() == null) {
            user.setBalence(0l);
        }
        User oldUser = userService.getSingleUser(user.getUsername());
        if(oldUser.getRole()==1) {
            user.setBalence(oldUser.getBalence());
        }
        User afterUpdateUser = userService.update(user);

        String substring = oldUser.getImg().substring(oldUser.getImg().lastIndexOf("/"));
        String realPath = req.getServletContext().getRealPath("/upload/userphoto");
        File file = new File(realPath + substring);
        file.deleteOnExit();

//        registerUser.setPassword("this is not showing on webpage");
        resp.sendRedirect("/start.jsp");
    }

    public void listUserForAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = userService.listUserForAdmin();
        req.setAttribute("pageData", userList);
        req.getRequestDispatcher("/jsp/listUserForAdmin.jsp").forward(req, resp);
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("id");
        userService.deleteUser(Integer.parseInt(userId));
        req.getRequestDispatcher("/jsp/listUserForAdmin.jsp").forward(req, resp);
    }

    public void getSingleUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        User user = userService.getSingleUser(userName);
        req.setAttribute("pageData", user);
        req.getRequestDispatcher("/jsp/listSingleUserForAdmin.jsp").forward(req, resp);
    }

    public static void checkLogin(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User)req.getSession().getAttribute("user");
        if (user == null) {
            throw new RuntimeException("Need Login");
        }
    }

    public static void checkAdmin(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User)req.getSession().getAttribute("user");
        if (user == null) {
            throw new RuntimeException("Need Login");
        }
        if (user.getRole() != 2) {
            throw new RuntimeException("Need Admin");
        }
    }

    public static User getLoginUser(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User)req.getSession().getAttribute("user");
        return user;
    }
}
