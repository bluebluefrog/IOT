<%@ page import="com.wjb.IOT.model.pojo.User" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>update info</title>
</head>
<link rel="stylesheet" type="text/css" href="resources/css/register.css"/>
<script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>

<%
  User curuser = (User)request.getSession().getAttribute("curuser");
  request.setAttribute("user", curuser);
%>
<body>
	<form action="/user?method=update" method="post" enctype="multipart/form-data" style="border:1px solid #ccc">
    <div class="container">
    <h1>update info</h1>
    <p>Please fill in this form to create an account.</p>
    <hr>
    <label for="email"><b>Email</b></label>
    <input type="text" placeholder="Enter Email" name="email" value=<%=curuser.getEmail()%> required>

    <label for="username"><b>username</b></label>
    <input type="username" placeholder="Enter username" name="username" readonly value=<%=curuser.getUsername()%> required>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="psw" id="psw" value=<%=curuser.getPassword()%> required>

    <label for="psw-repeat"><b>Repeat Password</b></label>
    <input type="password" placeholder="Repeat Password" name="psw-repeat" id="psw-repeat" onkeyup="validate()" value=<%=curuser.getPassword()%>required>

    <label for="common"><b>introduce yourself</b></label>
    <input type="text" placeholder="introduce yourself" name="common" value=<%=curuser.getCommon()%> required>
        <c:if test="${user.role==2}">
        <label for="balence"><b>balence</b></label>
        <input type="text" placeholder="balence" name="balence" value=<%=curuser.getBalence()%> required>
        </c:if>
    <label for="files">Chose your photo Select files:</label>
    <input type="file" id="files" name="files">
    <img width="100" height="100" src=<%=curuser.getImg()%>

    <span id="tishi"></span>

    <div class="clearfix">
      <button type="submit" class="signupbtn" id="submit">update</button>
    </div>
  </div>
</form>

<script>
        function validate() {
              var pwd1 = document.getElementById("psw").value;
              var pwd2 = document.getElementById("psw-repeat").value;

             if(pwd1 == pwd2) {
               document.getElementById("tishi").innerHTML="<font color='green'>same password</font>";
               document.getElementById("submit").disabled = false;
              }
              else {
               document.getElementById("tishi").innerHTML="<font color='red'>password not same</font>";
                document.getElementById("submit").disabled = true;
              }
          }
      </script>
</body>
</html>