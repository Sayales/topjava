<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h3>User list</h3>
<p>Choose user number</p> <br>
    <form method="post" action="users">
        <input type="number" name="userId">
        <button type="submit">Submit</button>
    </form>
</body>
</html>
