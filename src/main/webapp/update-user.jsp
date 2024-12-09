<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="/header.jsp" %>
</head>
<body>

<div class="main">
    <h1>Update User</h1>
    <div class="container">

        <form action="users" method="POST">
            <input type="hidden" name="a" value="update">
            <input type="hidden" name="id" value="${user.id}">

            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${user.name}" required><br><br>

            <label for="mail">Email:</label>
            <input type="email" id="mail" name="mail" value="${user.mail}" required><br><br>

            <button type="submit">Update User</button>
        </form>

    </div>
</div>
</body>
</html>
