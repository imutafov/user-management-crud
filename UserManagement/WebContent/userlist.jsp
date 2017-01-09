<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
    <form method="POST" action='SearchServlet' name="frmSearch">
        <input type="text" name="firstname"/>
        <input type="submit" value="Search by first name" />
    </form>
    <table border=1>
        <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th><a href="SortServlet?criteria=lastname&order=asc">/\</a></th>
                <th><a href="SortServlet?criteria=lastname&order=desc">\/</a></th>
                <th>Birth Date</th>
                <th><a href="SortServlet?criteria=birthdate&order=asc">/\</a></th>
                <th><a href="SortServlet?criteria=birthdate&order=desc">\/</a></th>
				<th>Phone Number</th>
                <th>Email</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.firstName}" /></td>
                    <td colspan="3"><c:out value="${user.lastName}" /></td>
                    <td colspan="3"><fmt:formatDate pattern="dd.MM.yyyy" value="${user.birthDate}" /></td>
                    <td><c:out value="${user.phoneNumber}"/></td>
                    <td><c:out value="${user.email}" /></td>
                    <td><a href="UserServlet?action=edit&userId=<c:out value="${user.userId}"/>">Update</a></td>
                    <td><a href="UserServlet?action=delete&userId=<c:out value="${user.userId}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
     <p><a href="UserServlet?action=listUser">Reset to default</a></p>
    <p><a href="UserServlet?action=insert">Add User</a></p>
</body>
</html>