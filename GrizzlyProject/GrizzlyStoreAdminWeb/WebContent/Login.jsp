<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"
     
      %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" media="screen" href="styles\main.css">
    <script src="scripts\main.js"></script>
<title>Insert title here</title>
</head>
<body>
<form action="grizzlystore" method="post" >
 <div class="loginwindow">
        <img src="images/logingrizzly.jpg" height=280px width=550px><br>
        <input class="logininput" type="text"  name="username" placeholder="Username"><br>
           <input class="logininput" type="text"  name="password" placeholder="Password"><br>
        <button class="loginbtn">Login</button>
    </div></form>
   <%String username=request.getParameter("username");
   session.setAttribute("user", username);
   %>
${status} 
</body>
</html>