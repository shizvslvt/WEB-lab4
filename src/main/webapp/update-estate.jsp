<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="/header.jsp" %>
    <title>Update Estate</title>
</head>
<body>

<div class="main">
    <h1>Update Estate</h1>
    <div class="container">

        <form action="estates" method="POST">
            <input type="hidden" name="a" value="update">
            <input type="hidden" name="id" value="${estate.id}">

            <label for="sellerId">Seller Name:</label>
            <input type="text" id="sellerId" name="sellerId" value="${estate.sellerId}" required><br><br>

            <label for="title">Title:</label>
            <input type="text" id="title" name="title" value="${estate.title}" required><br><br>

            <label for="cost">Cost:</label>
            <input type="number" id="cost" name="cost" value="${estate.cost}" required><br><br>

            <label for="time">Time:</label>
            <input type="text" id="time" name="time" value="${estate.time}" required><br><br>

            <button type="submit">Update Estate</button>
        </form>

    </div>
</div>
</body>
</html>
