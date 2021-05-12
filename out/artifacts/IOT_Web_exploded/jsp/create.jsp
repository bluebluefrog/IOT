<%@page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>add product</title>
<link rel="stylesheet" type="text/css" href="../css/create.css">
	<script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/validation.js"></script>
<script type="text/javascript">
	function checkSubmit(){
		var result = true;
		var r1 = checkEmpty("#name","#errPname");
		var r2 = checkCategory('#categoryId', '#errCategory');
		var r3 = checkPrice('#price', '#errPrice');
		var r4 = checkFile('#painting', '#errPainting');
		var r5 = checkEmpty('#detail', '#errDescription');
		var r5 = checkEmpty('#stock', '#errStock');
		if (r1 && r2 && r3 && r4 && r5){
			return true;
		}else{
			return false;
		}
	}
</script>
</head>
<body>
	<div class="container">
		<fieldset>
			<legend>add product</legend>
			<form action="/product?method=create" method="post"
				autocomplete="off" enctype="multipart/form-data"
				onsubmit = "return checkSubmit()">
				<ul class="ulform">
					<li>
						<span>productName</span>
						<span id="errPname"></span>
						<input id="name" name="name" onblur="checkEmpty('#name','#errPname')"/>
					</li>
					<li>
						<span>categoryId</span>
						<span id="errCategory"></span>
						<input id="categoryId" name="categoryId" onchange="checkCategory('#categoryId','#errCategory')"/>
					</li>
					<li>
						<span>price</span>
						<span id="errPrice"></span>
						<input id="price" name="price" onblur="checkPrice('#price','#errPrice')"/>
					</li>
					<li>
						<span>preview</span>
						<span id="errPainting"></span>
						<input id="painting" name="painting" type="file" 
							style="padding-left: 0px;" accept="image/*" onchange="checkFile('#painting','#errPainting')"/>
					</li>

					<li>
						<span>stock</span>
						<span id="errStock"></span>
						<input id="stock" name="stock" onblur="checkEmpty('#stock','#errStock')"/>
					</li>

					<li>
						<span>detail</span>
						<span id="errDescription"></span>
						<textarea
							id="detail" name="detail"
							onblur="checkEmpty('#detail','#errDescription')"
							></textarea>
					</li>
					<li style="text-align: center;">
						<button type="submit" class="btn-button">submit</button>
					</li>
				</ul>
			</form>
		</fieldset>
	</div>

</body>
</html>
