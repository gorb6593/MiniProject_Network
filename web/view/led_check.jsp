<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function click()
{
if(document.btn_on.public.clicked)
   {
    document.btn_on.public.value='on';
    document.btn_on.submit();
   }
else if(document.btn_off.public.clicked)
   {
   document.btn_off.public.value='off';
    document.btn_off.submit();   
   }
  
} 
<link href="common.css" rel="stylesheet" type="text/css">
<style>
body{background-color: #F7FFF5;}
img.absolute{
	postion: absolute;
	left:700;
	top: 50px;
}
</style>
</script>
</head>
<body>
   <!-- <form name="category" action='/http_test/led' method='GET'> -->
   <form name="btn_on" onclick="click()" method='GET'>
      <input type = 'hidden' name='LED' value='on'>
      <input class='onBtn' type='submit' value='on'/>
   </form>
   <!-- <form action='/http_test/led' method='GET'> -->
   <form name="btn_off" onclick="click()" method='GET'>
      <input type = 'hidden' name='LED' value='off'>
      <input class='onBtn' type='submit' value='off'/>
   </form>
   <div class="container">
   <div class=" col-sm-5">
   <h1>led_status : ${status}</h1>
		<c:choose>
		<c:when test= "${status eq 'on'}">
			<li><img src="/hp/web/img/led_on.png"></li>
			</c:when>
		<c:otherwise>
			<li><img src="/hp/web/img/led_off.png"></li>
		</c:otherwise>
		</c:choose> 
		</div>
		<div>	
		<img src="/img/led_on" class="absolute">
		</div>
</body>
</html>