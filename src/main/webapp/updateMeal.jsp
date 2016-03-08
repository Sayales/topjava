<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 08.03.2016
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="<c:url value="/meals?id=${id}"/>">
    <input type="hidden" name="action" value="update"> <br>
    <input type="text" name="description"  placeholder="Description" required/> <br>
    <input type="date" name="date" required> <br>
    <input type="time" name="time" required> <br>
    <input type="number" name="calories" required> <br>
    <input type="submit" value="Update meal">
</form>
</body>
</html>
