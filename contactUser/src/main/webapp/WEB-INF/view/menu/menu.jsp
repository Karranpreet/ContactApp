<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "s" uri = "http://www.springframework.org/tags" %>
<%@ page isELIgnored="false"%>
<c:if test="${sessionScope.userId== null }">

<a href="#">Home</a> | <a href="#">Login</a> | <a href="reg_form">Register</a> | <a href="#">About</a>
</c:if>

<c:if test="${sessionScope.userId != null && sessionScope.role==1 }">


<a href="user/dashboard">Home</a> | <a href="<s:url value="/admin/users"/>">User List</a> | <a href="logout">Logout</a>
</c:if>

<c:if test="${sessionScope.userId != null && sessionScope.role==2 }">
<%-- user --%>
<s:url var="url_cform" value="/user/contact_form" />
<s:url var="logout" value="/logout" />
<s:url var="clist" value="/user/clist" />
<a href="user/dashboard">Home</a> | <a href="${url_cform}">Add Contact</a>| <a href="${clist}">User List</a> | <a href="${logout}">Logout</a>
</c:if>