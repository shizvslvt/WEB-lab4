<%@ page import="org.example.weblab4v2.classes.User" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="/header.jsp" %>
</head>
<body>

<div class="main">
    <h1>Users</h1>
    <div class="container">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<User> users = (List<User>) request.getAttribute("users");
                if (users != null) {
                    for (User user : users) {
            %>
            <tr>
                <td><%= user.getId() %></td>
                <td><%= user.getName() %></td>
                <td><%= user.getMail() %></td>
                <td>
                    <a href="users?a=delete&id=<%= user.getId() %>">Delete</a>
                    <a href="users?a=update&id=<%= user.getId() %>">Edit</a>
                </td>
            </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>
    </div>

    <form action="users?a=add" method="post">
        <label for="name">Name:</label>
        <input id="name" type="text" name="name" required>
        <label for="email">Email:</label>
        <input id="email" type="email" name="email" required>
        <button type="submit">Add User</button>
    </form>
</div>
</body>
</html>
