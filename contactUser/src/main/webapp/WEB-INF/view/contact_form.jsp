<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix = "s" uri = "http://www.springframework.org/tags" %>
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
Contact Form
<s:url var="saveContact" value="/user/saveContact" />
<f:form action="${saveContact}" modelAttribute="command">
<table border="1">
<tr>
<td>name</td>
<td><f:input path="name" /></td>
</tr>

<tr>
<td>phone</td>
<td><f:input path="phone" /></td>
</tr>

<tr>
<td>email</td>
<td><f:input path="email" /></td>
</tr>

<tr>
<td>address</td>
<td><f:input path="address" /></td>
</tr>

<tr>
<td>remark</td>
<td><f:input path="remark" /></td>
</tr>

<tr>
<td>
<button>save</button>
</tr>

</table>
</f:form>

</td>
</tr>

</table>
</body>
</html>