<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.weblab4v2.classes.Estate" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%@ include file="/header.jsp" %>
    <title>Estates</title>
</head>
<body>
<div class="main">
    <h1>Estates</h1>
    <div class="container">

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Seller ID</th>
        <th>Title</th>
        <th>Cost</th>
        <th>Time</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Estate> estates = (List<Estate>) request.getAttribute("estates");
        if (estates != null && !estates.isEmpty()) {
            for (Estate estate : estates) {
    %>
    <tr>
        <td><%= estate.getId() %></td>
        <td><%= estate.getSellerId() %></td>
        <td><%= estate.getTitle() %></td>
        <td><%= estate.getCost() %></td>
        <td><%= estate.getTime() %></td>
        <td>
            <a class="action-btn" href="estates?a=delete&id=<%= estate.getId() %>">Delete</a>
            <a class="action-btn" href="estates?a=update&id=<%= estate.getId() %>">Edit</a>
        </td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>

    </div>
<form action="estates" method="POST">
    <input type="hidden" name="a" value="add">
    <label for="sellerId">Seller ID:</label>
    <input type="number" id="sellerId" name="sellerId" required>

    <label for="title">Title:</label>
    <input type="text" id="title" name="title" required>

    <label for="cost">Cost:</label>
    <input type="number" id="cost" name="cost" step="0.01" required>

    <button type="submit">Add Estate</button>
</form>

</div>
</body>
</html>
