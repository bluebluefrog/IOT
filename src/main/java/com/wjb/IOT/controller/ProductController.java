package com.wjb.IOT.controller;
import com.wjb.IOT.model.pojo.Product;
import com.wjb.IOT.service.ProductService;
import com.wjb.IOT.utils.PageModel;
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
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/product")
public class ProductController extends HttpServlet{

    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        String c = req.getParameter("category");
        if (method.equals("list")) {
            list(req,resp,c);
        } if (method.equals("listForAdmin")) {
            listForAdmin(req,resp,c);
        }if (method.equals("showUpdate")) {
            showUpdate(req,resp,c);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String method = req.getParameter("method");
        if (method.equals("create")) {
            create(req, resp);
        }if (method.equals("update")) {
            update(req, resp);
        }
        if (method.equals("delete")) {
            deleteProduct(req, resp);
        }
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Product product = new Product();
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
                        case "name":
                            product.setName(item.getString("UTF-8"));
                            break;
                        case "detail":
                            product.setDetail(item.getString("UTF-8"));
                            break;
                        case "categoryId":
                            product.setCategoryId(Integer.parseInt(item.getString("UTF-8")));
                            break;
                        case "price":
                            product.setPrice(Integer.parseInt(item.getString("UTF-8")));
                            break;
                        case "stock":
                            product.setStock(Integer.parseInt(item.getString("UTF-8")));
                            break;
                        default:
                            break;
                    }
                } else {
                        //3文件保存到服务器目录
                        //获取服务器在运行时的物理地址
                        String realPath = req.getServletContext().getRealPath("/upload/itemphoto");
                        //处理文件名，名字不能重复
                        String fileName = UUID.randomUUID().toString();
                        //提取原始文件扩展名
                        String suffix = item.getName().substring(item.getName().lastIndexOf("."));
                        //拼接随机名和扩展名
                        String newName = fileName + suffix;
                        File imgfile = new File(realPath, newName);
                        item.write(imgfile);
                        product.setImage("/upload/itemphoto/" + newName);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        product.setStatus(1);
        productService.createProduct(product);
        resp.sendRedirect("/start.jsp");
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int isPreviewModified = 0;
        Product product = new Product();
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
                        case "name":
                            product.setName(item.getString("UTF-8"));
                            break;
                        case "detail":
                            product.setDetail(item.getString("UTF-8"));
                            break;
                        case "categoryId":
                            product.setCategoryId(Integer.parseInt(item.getString("UTF-8")));
                            break;
                        case "price":
                            product.setPrice(Integer.parseInt(item.getString("UTF-8")));
                            break;
                        case "stock":
                            product.setStock(Integer.parseInt(item.getString("UTF-8")));
                            break;
                            case "isPreviewModified":
                            isPreviewModified = Integer.parseInt(item.getString("UTF-8"));
                            break;
                        case "id":
                            product.setProductId(Integer.parseInt(item.getString("UTF-8")));
                            break;
                        default:
                            break;
                    }
                } else {
                    if (isPreviewModified==1) {
                        //3文件保存到服务器目录
                        //获取服务器在运行时的物理地址
                        String realPath = req.getServletContext().getRealPath("/upload/itemphoto");
                        //处理文件名，名字不能重复
                        String fileName = UUID.randomUUID().toString();
                        //提取原始文件扩展名
                        String suffix = item.getName().substring(item.getName().lastIndexOf("."));
                        //拼接随机名和扩展名
                        String newName = fileName + suffix;
                        File imgfile = new File(realPath, newName);
                        item.write(imgfile);
                        product.setImage("/upload/itemphoto/" + newName);
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        product.setStatus(1);
        if (isPreviewModified==1) {
            Product updateProduct = productService.selectProductById(product.getProductId());
            String substring = updateProduct.getImage().substring(updateProduct.getImage().lastIndexOf("/"));
            String realPath = req.getServletContext().getRealPath("/upload/itemphoto");
            File file = new File(realPath + substring);
            file.deleteOnExit();
        }
        productService.updateProduct(product);
        resp.sendRedirect("/start.jsp");
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String productId = req.getParameter("id");

        Product product = productService.selectProductById(Integer.parseInt(productId));
        String substring = product.getImage().substring(product.getImage().lastIndexOf("/"));
        String realPath = req.getServletContext().getRealPath("/upload/itemphoto");
        File file = new File(realPath + substring);
        file.deleteOnExit();
        productService.deleteProduct(Integer.parseInt(productId));
        resp.sendRedirect("/start.jsp");
    }

    private void list(HttpServletRequest req, HttpServletResponse resp,String category) throws ServletException, IOException {
        //2.调用Service方法,得到处理结果
        PageModel pageModel = productService.list(1,30,category);
        req.setAttribute("pageModel", pageModel);
        //3.请求转发至对应JSP(view)进行数据展现
        req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
    }

    private void listForAdmin(HttpServletRequest req, HttpServletResponse resp,String category) throws ServletException, IOException {
        //2.调用Service方法,得到处理结果
        PageModel pageModel = productService.list(1,30,category);
        req.setAttribute("pageModel", pageModel);
        //3.请求转发至对应JSP(view)进行数据展现
        req.getRequestDispatcher("/jsp/list.jsp").forward(req, resp);
    }

    private void showUpdate(HttpServletRequest req, HttpServletResponse resp, String category) throws ServletException, IOException {
        String id = req.getParameter("id");
        Product product = productService.selectProductById(Integer.parseInt(id));
        req.setAttribute("product",product);
        req.getRequestDispatcher("/jsp/update.jsp").forward(req, resp);
    }
}
