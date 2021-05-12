<%@ page import="com.wjb.IOT.model.pojo.User" %>
<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>IOT</title>
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript" src="js/js1.js"></script>
	<script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
<%--<c:if test="${param.c != null }">--%>
<%--	<c:set var="categoryParam" value="&c=${param.c}"></c:set>--%>
<%--</c:if>--%>
<%--<c:if test="${param.c == null }">--%>
<%--	<c:set var="categoryParam" value=""></c:set>--%>
<%--</c:if>--%>
	<div class="header">
<%--		<div class="logo">--%>
<%--			<img src="image/logo.png">--%>
<%--		</div>--%>
		<div class="auth">
			<ul>
				<c:if test="${user==null}">
				<li><a href="/login.html">login</a></li>
				</c:if>
				<c:if test="${user!=null}">
					<li><a href="#" onclick="logout()">logout</a></li>
				</c:if>
				<li><a href="/register.html">register</a></li>
				<c:if test="${user!=null}">
				<li><a href="/welcome.jsp">personal info</a></li>
				</c:if>
				<c:if test="${user!=null}">
					<li> <a href="/cart?method=list">cart</a></li>
				</c:if>
				<c:if test="${user!=null}">
					<li> <a href="#" onclick="showUserOrder()">order</a></li>
				</c:if>
				<c:if test="${user.role==2}">
					<div class="menu"   onclick="show_menu()" onmouseleave="show_menu1()" style="margin-top: 0px;">
						<div class="menu_title"><a href="#">admin</a></div>
						<ul id="title">
							<li><a href="/product?method=listForAdmin">productManagement</a></li>
							<li><a href="/user?method=listUser" >userManagement</a></li>
						</ul>
					</div>
				</c:if>
			</ul>
		</div>
	</div>
<div class="content">
	<div class="banner">
		<img src="image/welcome.png" class="banner-img">
	</div>
	<div class="img-content">
		<ul>
			<c:forEach items="${pageModel.pageData}" var="product">
				<li>
					<img src="${product.image}" class="img-li">
					<div class="info">
						<h3>${product.name}</h3>
						<p>
								${product.detail}
						</p>
						<div class="img-btn">
							<div class="price">
								<fmt:formatNumber pattern="$0.00" value="${product.price}"></fmt:formatNumber>
							</div>
							<a href="#" onclick="addCart(${product.productId})" class="cart">
								<div class="btn">
									<img src="image/cart.svg">
								</div>
							</a>
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>

<%--<div class="page-nav">--%>
<%--	<ul>--%>
<%--		<li><a href="/page?p=1${categoryParam}">首页</a></li>--%>
<%--		<li><a href="/page?p=${pageModel.hasPreviousPage?pageModel.page-1:1 }${categoryParam}">上一页</a></li>--%>
<%--		<c:forEach begin="1" end="${pageModel.totalPages }" var="pno" step="1">--%>
<%--			<li><span ${pno==pageModel.page?"class='first-page'":""}>--%>
<%--					<!-- c不存在,则href="/page?p=1" -->--%>
<%--				<!-- c存在,则href="/page?p=1&c=1" -->--%>
<%--					<a href="/page?p=${pno}${categoryParam}">--%>
<%--							${pno}--%>
<%--					</a>--%>
<%--				</span></li>--%>
<%--		</c:forEach>--%>
<%--		<li><a href="/page?p=${pageModel.hasNextPage?pageModel.page+1:pageModel.totalPages }${categoryParam}">下一页</a></li>--%>
<%--		<li><a href="/page?p=${pageModel.totalPages}${categoryParam}">尾页</a></li>--%>
<%--	</ul>--%>
<%--</div>--%>
	<div class="footer">
		<p><span>IOT</span>©2021 POWERED BY warren</p>
	</div>
</body>

<script type="text/javascript">

	function logout(){
		$.ajax({
			type: 'POST',
			async: true,
			url: "/user?method=logout",
			success:function () {
					window.location.href="/product?method=list";
			}
		})
	}

	function addCart(productId) {
		$.ajax({
			type: 'POST',
			async: true,
			url: "/cart?method=create&productId="+productId,
			success:function () {
				window.alert("product added to cart")
				window.location.href="/product?method=list";
			}
		})
	}

	function listCart() {
		$.ajax({
			type: 'GET',
			async: true,
			url: "/cart?method=list",
			success:function () {
				// window.location.href="/cart?method=list";
			}
		})
	}

	function listProductForAdmin(){
		$.ajax({
			type: 'GET',
			async: true,
			url: "/product?method=listForAdmin",
			success:function () {
				// window.location.href="/product?method=listForAdmin"
			}
		})
	}

	function showUserOrder(userId) {
		$.ajax({
			type: 'GET',
			async: true,
			url: "/order?method=list",
			success:function () {
				window.location.href="/order?method=list";
			}
		})
	}
</script>
</html>