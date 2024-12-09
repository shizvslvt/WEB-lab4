<%@ page import="org.example.weblab4v2.classes.Query" %>
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
    <h1>SQL Query</h1>
    <form action="query" method="post">
        <label for="query"></label>
        <textarea id="query" name="query" rows="4" cols="50" required></textarea>
        <button type="submit">Execute</button>
    </form>

    <%
        Query query = (Query) request.getAttribute("query");
        if (query != null && query.getResult() != null && !query.getResult().isEmpty()) {
            List<String[]> results = query.getResult();
            String[] headers = results.get(0);
    %>
    <table>
        <thead>
        <tr>
            <% for (String header : headers) { %>
            <th><%= header %></th>
            <% } %>
        </tr>
        </thead>
        <tbody>
        <% for (int i = 1; i < results.size(); i++) { %>
        <tr>
            <% for (String cell : results.get(i)) { %>
            <td><%= cell %></td>
            <% } %>
        </tr>
        <% } %>
        </tbody>
    </table>
    <%
        }
    %>
</div>
</body>
</html>
