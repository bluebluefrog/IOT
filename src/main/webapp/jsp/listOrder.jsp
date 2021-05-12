<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>product list</title>
	<script src="../js/sweetalert2.js" type="text/javascript"></script>
	<script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>
	<link rel="stylesheet" type="text/css" href="../css/list.css">
<script type="text/javascript">
	//对话框显示预览图
	function showPreview(previewObj){
		var preview = $(previewObj).attr("data-preview");
		var pname = $(previewObj).attr("data-pname");
		Swal.fire({
			title: pname,
			html: "<img src='" + preview + "' style='width:361px;height:240px'>",
			showCloseButton: true,
			showConfirmButton: false
		})
	}

	function pay(orderId) {
		$.ajax({
			type: 'POST',
			async: true,
			url: "/order?method=update&id="+orderId+"&option=pay",
			success:function () {
				location.reload()
			}
		})
	}
	function cancel(orderId) {
		$.ajax({
			type: 'POST',
			async: true,
			url: "/order?method=update&id="+orderId+"&option=cancel",
			success:function () {
				location.reload()
			}
		})
	}

</script>
</head>
<body>
<a href="start.jsp">back to iot</a>
	<div class="container">
		<fieldset>
			<legend>order list</legend>
			<!-- 油画列表 -->
			<table cellspacing="0px">
				<td>orderNo</td>
				<td>userId</td>
				<td>totalPrice</td>
				<td>receiverName</td>
				<td>receiverMobile</td>
				<td>receiverAddress</td>
				<td>orderStatus</td>
				<td>management</td>
				<c:forEach items="${pageData}" var="order">
				<tr>
					<td>${order.orderNo}</td>
					<td>${order.userId}</td>
					<td><fmt:formatNumber pattern="$0.00" value="${order.totalPrice}"></fmt:formatNumber> </td>
					<td>${order.receiverName}</td>
					<td>${order.receiverMobile}</td>
					<td>${order.receiverAddress}</td>
					<c:if test="${order.orderStatus==1}">
						<td>ordered</td>
					</c:if>

					<c:if test="${order.orderStatus==2}">
						<td>payed</td>
					</c:if>

					<c:if test="${order.orderStatus==3}">
						<td>canceled</td>
					</c:if>
					<c:if test="${order.orderStatus==1}">
					<td>
						<a class="oplink" href="#" onclick="pay(${order.orderId})">pay</a>
						<a class="oplink" href="#" onclick="cancel(${order.orderId})">cancel</a>
					</td>
					</c:if>
				</tr>
				</c:forEach>
			</table>
			<!-- 分页组件 -->
<%--			<ul class="page">--%>
<%--				<li><a href="/management?method=list&p=1">首页</a></li>--%>
<%--				<li><a href="/management?method=list&p=${pageModel.hasPreviousPage?pageModel.page-1:1}">上页</a></li>--%>
<%--				<c:forEach begin="1" end="${pageModel.totalPages }" step="1" var="pno">--%>
<%--					<li ${pno==pageModel.page?"class='active'":""}>--%>
<%--						<a href="/management?method=list&p=${pno }">${pno }</a>--%>
<%--					</li>--%>
<%--				</c:forEach>--%>
<%--				<li><a href="/management?method=list&p=${pageModel.hasNextPage?pageModel.page+1:pageModel.totalPages}">下页</a></li>--%>
<%--				<li><a href="/management?method=list&p=${pageModel.totalPages}">尾页</a></li>--%>
<%--			</ul>--%>
		</fieldset>
	</div>
</body>
</html>
