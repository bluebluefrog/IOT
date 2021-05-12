<%@ page import="com.wjb.IOT.model.pojo.User" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
        content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">

    <title>welocome</title>
 
    <link rel="stylesheet" type="text/css" href="./resources/css/FiraCode.css">
    <link rel="stylesheet" type="text/css" href="./resources/css/nutssss.css">
    <link rel="icon" href="./resources/favicon.ico">
    <script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<%
    User user = (User)request.getSession().getAttribute("user");
    request.setAttribute("user", user);
%>
<body>
<a href="start.jsp">back to iot</a>
    <div id="box">
        <!-- 个人资料卡片 -->
        <div class="meBox">
            <!-- 头像 -->
            <img class="headPhoto" src="<%=user.getImg()%>">
            <br>
            <!-- 介绍 -->
            <div class="meBox-title">
                <p>I'm <%=user.getUsername()%></p>
                <div class="fg"></div>
            </div>
            <div class="meBox-text">
                <p><%=user.getCommon()%><img src="./resources/images/002.png" alt="冰激凌" style="width: 15px; vertical-align: middle;"></p>
                <p>my email:</p>
                <p><%=user.getEmail()%></p>
                <p>my balance:</p>
                <p><%=user.getBalence()%></p>
            </div>
            <!-- 两个按钮 -->
            <div class="meBox-Button">
                <form action="/user?method=logout" method="post">
                    <button type="submit">Logout</button>
                </form>

                <form action="/user?method=showupdate&id=${user.userId}" method="post">
                    <button type="submit">change person info</button>
                </form>
            </div>
        </div>

        <!-- 伪终端介绍 -->
        <div id="cmdBox">
            <!-- 第一个终端 -->
            <div class="cmd">
                <!-- 三个按钮 -->
                <div class="click">
                    <div class="red"></div>
                    <div class="yellow"></div>
                    <div class="green"></div>
                </div>
                <!-- 顶部标题 -->
                <div class="title">
                    <span>love - bash</span>
                </div>
                <!-- 终端内文字 -->
                <div class="cmdText">
                    <span style="color: rgb(0, 190, 0);">root@nutssss</span>
                    <span style="color: blue;">~</span>
                    <span style="color: rgb(39, 39, 39);">./test.sh</span>
                    <br />
                    <span style="color: rgb(0, 190, 0);">root@nutssss</span>
                    <span style="color: blue;">~</span>
                    <span style="color: rgb(39, 39, 39);">cat /me.txt</span>
                    <p>I love computers and I can teach myself everything I am interested in</p>
                    <span style="color: rgb(0, 190, 0);">root@nutssss</span>
                    <span style="color: blue;">~</span>
                    <span style="color: rgb(39, 39, 39);">sudo rm -rf /welocome to IOT</span>
                </div>
            </div>
            <!-- 第二个终端 -->
            <div class="cmd cmd2">
                <!-- 三个按钮 -->
                <div class="click">
                    <div class="red"></div>
                    <div class="yellow"></div>
                    <div class="green"></div>
                </div>
                <!-- 顶部标题 -->
                <div class="title">
                    <span>lovexhj - bash</span>
                </div>
                <!-- 终端内文字 -->
                <div class="cmdText">
                    <span style="color: rgb(0, 190, 0);">root@nutssss</span>
                    <span style="color: blue;">~</span>
                    <span style="color: rgb(39, 39, 39);">./links.sh</span>
                    <ul class="ul">
                        <li><a href="#">Blog</a></li>
                        <li><a href="#">Love</a></li>
                        <li><a href="#">Note</a></li>
                        <li><a href="h#">Mc</a></li>
                        <li><a href="#">McBlog</a></li>
                    </ul>
                    <span style="color: rgb(0, 190, 0);">root@nutssss</span>
                    <span style="color: blue;">~</span>
                </div>
            </div>
        </div>
    </div>

</body>

</html>
