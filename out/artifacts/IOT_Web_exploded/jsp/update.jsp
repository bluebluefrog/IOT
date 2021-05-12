<%@page contentType="text/html;charset=utf-8"%>
<!-- 更新油画页面 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>作品更新</title>
<link rel="stylesheet" type="text/css" href="css\create.css">
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/validation.js"></script>
<script type="text/javascript">
	function checkSubmit(){
		var result = true;
		var r1 = checkEmpty("#name","#errPname");
		var r2 = checkCategory('#categoryId', '#errCategory');
		var r3 = checkPrice('#price', '#errPrice');
		var r5 = checkEmpty('#detail', '#errDescription');
		var r5 = checkEmpty('#stock', '#errStock');
		if (r1 && r2 && r3 && r5){
			return true;
		}else{
			return false;
		}
	}
	
	//检查预览图片 
	function selectPreview(){
		checkFile("#painting","#errPainting");
		$("#preview").hide();
		$("#isPreviewModified").val(1);
	}
	
</script>
</head>
<body>
<div class="container">
	<fieldset>
		<legend>add product</legend>
		<form action="/product?method=update" method="post"
			  autocomplete="off" enctype="multipart/form-data"
			  onsubmit = "return checkSubmit()">
			<ul class="ulform">
				<li>
					<span>productName</span>
					<span id="errPname"></span>
					<input id="name" name="name" onblur="checkEmpty('#name','#errPname')" value="${product.name}"/>
				</li>
				<li>
					<span>categoryId</span>
					<span id="errCategory"></span>
					<input id="categoryId" name="categoryId" onchange="checkCategory('#categoryId','#errCategory')"value="${product.categoryId}"/>
				</li>
				<li>
					<span>price</span>
					<span id="errPrice"></span>
					<input id="price" name="price" onblur="checkPrice('#price','#errPrice')"value="${product.price}"/>
				</li>
				<li>
					<span>preview</span>
					<input type="hidden" id="isPreviewModified" name="isPreviewModified" value="0">
					<span id="errPainting"></span>
					<img id="preview" src="${product.image}" style="width:361px;height:240px"/><br/>
					<input id="painting" name="painting" type="file"
						   style="padding-left: 0px;" accept="image/*" onchange="selectPreview()"/>
				</li>

				<li>
					<span>stock</span>
					<span id="errStock"></span>
					<input id="stock" name="stock" onblur="checkEmpty('#stock','#errStock')"value="${product.stock}"/>
				</li>

				<li>
					<span>detail</span>
					<span id="errDescription"></span>
					<textarea
							id="detail" name="detail"
							onblur="checkEmpty('#detail','#errDescription')"
							>${product.detail}</textarea>
				</li>
				<li style="text-align: center;">
					<input type="hidden" id="id" name="id" value="${product.productId}">
					<button type="submit" class="btn-button">submit</button>
				</li>
			</ul>
		</form>
	</fieldset>
</div>
</body>
</html>
