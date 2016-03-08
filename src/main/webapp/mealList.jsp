<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 07.03.2016
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Meal list</title>
</head>
<body>
<table>
    <tr>
        <th>Time</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach items="${meals}" var="meal">
        <tr>
            <c:if test="${meal.exceed}">
                <td style="color: #FF0000"><c:out value="${meal.stringTime}"/></td>
                <td style="color: #FF0000"><c:out value="${meal.description}"/></td>
                <td style="color: #FF0000"><c:out value="${meal.calories}"/></td>
            </c:if>
            <c:if test="${!meal.exceed}">
                <td style="color: #008000"><c:out value="${meal.stringTime}"/></td>
                <td style="color: #008000"><c:out value="${meal.description}"/></td>
                <td style="color: #008000"><c:out value="${meal.calories}"/></td>
            </c:if>
            <td><c:out value="${meal.id}"/></td>
            <td>
                <form method="post" action="<c:url value="/meals"/>">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="${meal.id}">
                    <input type="submit" value="Delete meal">
                </form>
            </td>
            <td>
                <form method="post" action="<c:url value="/update?id=${meal.id}"/> ">
                    <input type="submit" value="Update meal">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<form method="post" action="<c:url value="/meals"/>">
    <input type="hidden" name="action" value="add"> <br>
    <input type="text" name="description"  placeholder="Description" required/> <br>
    <input type="date" name="date" required> <br>
    <input type="time" name="time" required> <br>
    <input type="number" name="calories" required> <br>
    <input type="submit" value="Add meal">
</form>
</body>
</html>
