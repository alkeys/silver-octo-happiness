<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>



ingrse de credenciales correo y contraseña
<form action="hello-servlet" method="get" >
    <input type="text" name="correo" placeholder="correo">
    <input type="password" name="pass" placeholder="contraseña">
    <input type="submit" value="Ingresar">
</form>


</body>
</html>