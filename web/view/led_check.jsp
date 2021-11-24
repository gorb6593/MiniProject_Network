<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style >
.box{
	text-align: center;
}
.container{
	display: inline-block;
	
}
.btn btn-danger{
	width: 300px;
}
a {
font-size: 80px;
}
.row{
	margin: 80px;
}

</style>
</head>
<body>
   <!-- <form name="category" action='/http_test/led' method='GET'> -->
   <div class="box">
   <div class="container">
   <a >LED status : ${status }</a>
   <c:choose>
   <c:when test="${status eq 'on' }">
   		<li><img src="/hp/img/led_on.png"></li>
   		</c:when>
   	<c:otherwise>
   		<li><img src="/hp/img/led_off.png"></li>
   	</c:otherwise>
   	</c:choose>
   	<div class="row">
   	<div class="col-sm-6" style="text-align: right;" >
	   <!-- <form  name="btn_on" onclick="click()" method='GET'> -->
	     <form action="ledonadd.mc"  name="btn_on" onclick="click()" method='GET'> 
	      <input type = 'hidden' name='LED' value='on'>
	      <input class='btn btn-danger' type='submit' value='on' style="height: 50px; width: 120px;"/>
	   </form>
	  </div>
	   <!-- <form action='/http_test/led' method='GET'> -->
	   <div class="col-sm-6" style="text-align: left;">
	   <!-- <form  name="btn_off" onclick="click()" method='GET'> -->
	     <form action="ledoffadd.mc" name="btn_off" onclick="click()" method='GET'> 
	      <input type = 'hidden' name='LED' value='off'>
	      <input class='btn btn-info' type='submit' value='off' style="height: 50px; width: 120px;"/>
	   </form>
	   <form action="ledonadd.mc" method="post">
	   <input type="submit" name="led" value="on"><br>
	   </form>
	   </div>
   </div>
   	</div>
   	</div>
</body>
</html>