<%--
  Created by IntelliJ IDEA.
  User: handsomewarren
  Date: 9/5/21
  Time: 8:29 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function index(){
        $.ajax({
            type: 'GET',
            async: true,
            url: "/product?method=list",
            success:function () {
                window.location.href="/product?method=list"
            }
        })
    }
    onload(index())
</script>
<html>
<head>
</head>
<body>
</body>
</html>
