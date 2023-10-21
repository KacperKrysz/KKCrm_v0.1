<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp"%>

<form method="post">
  <label for="subject">Temat:</label>
  <input type="text" id="subject" name="subject" required><br>

  <label for="status">Status:</label>
  <input type="text" id="status" name="status" required><br>

  <label for="date">Data:</label>
  <input type="date" id="date" name="date" pattern="\d{4}-\d{2}-\d{2}" value="" required>
  <script>
    let currentDate = new Date();

    let year = currentDate.getFullYear();
    let month = (currentDate.getMonth() + 1).toString().padStart(2, '0');
    let day = currentDate.getDate().toString().padStart(2, '0');
    let formattedDate = year + "-" + month + "-" + day;

    document.getElementById("date").value = formattedDate;
  </script>

  <label for="priority">Priorytet:</label>
  <input type="text" id="priority" name="priority"><br>

  <label for="description">Opis:</label>
  <input type="text" id="description" name="description"><br>

  <label for="contactPerson">Osoba kontaktowa:</label>
  <input type="text" id="contactPerson" name="contactPerson" ><br>

  <label for="softwarePatron">Przydzielone do:</label>
  <input type="text" id="softwarePatron" name="softwarePatron" ><br>

  <input type="submit" value="Dodaj aktywność">
</form>

<a href="/clients/${clientId}">
  <button>Anuluj</button>
</a>

</body>
</html>
