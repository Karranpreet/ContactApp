<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page isELIgnored="false"%>
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
Contact List

<s:url var="searchContact" value="/user/contactSearch" />
<f:form action="${searchContact}">
<input type="text" name="freeText" placeholder="Enter the search text">
<button>Find</button>
</f:form>
<br/>

<s:url var="deletebulk" value="/user/bulk_delete" />

<f:form action="${deletebulk}">

<button>Delete selected Records</button>
<br/>


<table border="1">
<tr>
<td>Select</td>
<td>SR</td>
<td>CID</td>
<td>Name</td>
<td>Phone</td>
<td>Email</td>
<td>Address</td>
<td>Remark</td>
<td>Action</td>
</tr>
<c:forEach var="c" items="${contactList}" varStatus="st">
<tr>
<td align="center"><input type="checkbox" name="cid" value="${c.contactId}" ></td>
<td>${st.count}</td>
<td>${c.contactId}</td>
<td>${c.name }</td>
<td>${c.phone }</td>
<td>${c.email }</td>
<td>${c.address }</td>
<td>${c.remark}</td>

<s:url var="url_del" value="/user/del_contact">
<s:param name="cid" value="${c.contactId}" />
</s:url>

<s:url var="url_edit" value="/user/edit_contact">
<s:param name="cid" value="${c.contactId}" />
</s:url>

<td><a href="${url_edit}">Edit</a> |<a href="${url_del}">Delete</a> </td>
</tr>
</c:forEach>
</table>
</f:form>

</td>
</tr>

</table>
</body>
</html>