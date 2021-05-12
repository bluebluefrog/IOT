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
	//删除数据
	function del(delObj){
		var id = $(delObj).attr("data-id");
		var pname = $(delObj).attr("data-pname");
		var preview = $(delObj).attr("data-preview");
		Swal.fire({
			title : "delete[" + pname + "]?",
			html : "<img src='" + preview + "' style='width:361px;height:240px'>",
			showCancelButton: true,
			confirmButtonText: "yes",
			cancelButtonText: "no"
		}).then(function(result){
			if(result.value==true){
				//alert("你点了'是'按钮")
				$.ajax({
					url: "/product?method=delete&id=" + id,
					type : "POST",
					dataType:"json",
					success : function(json){
						if(json.result=="ok"){
							window.location.reload();
						}else{
							Swal.fire({
								title : json.result
							})
						}
						//console.log(json);
					}
				})
			}
		})
	}
</script>
</head>
<body>
<a href="start.jsp">back to iot</a>
	<div class="container">
		<fieldset>
			<legend>product list</legend>
			<div style="height: 40px">
				<a href="/jsp/create.jsp" class="btn-button">add</a>
			</div>
			<!-- 油画列表 -->
			<table cellspacing="0px">
				<c:forEach items="${pageModel.pageData}" var="product">
				<tr>
					<td>${product.name}</td>
					<td><fmt:formatNumber pattern="$0.00" value="${product.price}"></fmt:formatNumber> </td>
					<td>${painting.description }</td>
					<td>
						<a class="oplink" data-preview="${product.image}" data-pname="${product.name}" href="javascript:void(0)" onclick="showPreview(this)">preview</a>
						<a class="oplink" href="/product?method=showUpdate&id=${product.productId}">update</a>
						<a class="oplink" href="javascript:void(0)" data-id="${product.productId}" data-pname="${product.name}" data-preview="${product.image}" onclick="del(this)">delete</a>
					</td>
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
