<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="static/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<table border="1" width="80%" align="center">
<tr>
<td height="100px">
<jsp:include page="header/header.jsp" />
</td>
</tr>
<tr>
<td height="25px">
<jsp:include page="menu/menu.jsp" />
</td>
</tr>
<tr>
<td height="350px">
USER Registeration
<f:form action="register" modelAttribute="command" method="POST">
<table border="1">
<tr>
<td>UserName</td>
<td><f:input path="user.name" /></td>
</tr>

<tr>
<td>Password</td>
<td><f:input path="user.password" /></td>
</tr>

<tr>
<td>name</td>
<td><f:input path="user.loginName" /></td>
</tr>

<tr>
<td>Phone</td>
<td><f:input path="user.phone" /></td>
</tr>

<tr>
<td>Address</td>
<td><f:input path="user.address" /></td>
</tr>

<tr>
<td>
<button>Submit</button><br>
<%-- <a href="#">New user Registration</a> --%>
</td>
</tr>

</table>
</f:form>

</td>
</tr>

</table>
</body>
</html>