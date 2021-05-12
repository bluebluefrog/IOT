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
	function showUpdateUser(userId){
		$.ajax({
			type: 'POST',
			async: true,
			url: "/user?method=showupdate&id="+userId,
			success:function () {
				window.location.href="/update.jsp"
			}
		})
	}
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
					url: "/user?method=delete&id=" + id,
					type : "POST",
					dataType:"json",
					success : function(json){
							windows.reload()
						//console.log(json);
					}
				})
			}
		})
	}

	function getSingleUser() {
		var name;
		name = $("#searchname").val();

		$.ajax({
			url:"/user?method=getSingleUser&username="+name,
			type:"GET",
			async: true,
			success:function () {
				window.location.href="/user?method=getSingleUser&username="+name;
			}
		})
	}
</script>
</head>
<body>
<a href="start.jsp">back to iot</a>
	<div class="container">
		<fieldset>
			<legend>user list</legend>
			<div style="height: 40px">
				<a href="/register.html" class="btn-button">add</a>
				<input type="search" id="searchname">search username</input><a href="#" onclick="getSingleUser()"><button>Search</button></a>
			</div>
			<!-- 油画列表 -->
			<table cellspacing="0px">
				<tr>
					<td>username</td>
					<td>password</td>
					<td>common</td>
					<td>balence</td>
					<td>email</td>
					<td>management</td>
				</tr>
				<tr>
					<td>${pageData.username}</td>
					<td>${pageData.password}</td>
					<td>${pageData.common}</td>
					<td>${pageData.balence}</td>
					<td>${pageData.email}</td>
					<td>
						<a class="oplink" data-preview="${pageData.img}" data-pname="${pageData.username}" href="javascript:void(0)" onclick="showPreview(this)">preview</a>
						<a class="oplink" href="#" onclick="showUpdateUser(${pageData.userId})">update</a>
						<a class="oplink" href="javascript:void(0)" data-id="${pageData.userId}" data-pname="${pageData.username}" data-preview="${pageData.img}" onclick="del(this)">delete</a>
					</td>
				</tr>

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
