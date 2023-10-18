<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Edycja aktywności</title>
</head>
<body>

<%@include file="header.jsp"%>

<h1>Edycja aktywności</h1>

<form method="post">
  <p><strong>Temat:</strong> <input type="text" name="subject" value="${activity.subject}" required></p>
  <p><strong>Status:</strong> <input type="text" name="status" value="${activity.status}" required></p>
  <p><strong>Data: ${activity.date} </strong> <input type="date" name="date" pattern="\d{4}-\d{2}-\d{2}" value="${activity.date}" required></p>
  <p><strong>Priorytet:</strong> <input type="text" name="priority" value="${activity.priority}"></p>
  <p><strong>Opis:</strong> <input type="text" name="description" value="${activity.description}"></p>
  <p><strong>Osoba kontaktowa:</strong> <input type="text" name="contactPerson" value="${activity.contactPerson}"></p>
  <p><strong>Software Patron:</strong> <input type="text" name="softwarePatron" value="${activity.softwarePatron}"></p>

  <p><strong>Klient:</strong> ${clientName}</p>

  <input type="submit" value="Zapisz zmiany">
</form>

<a href="/clients/${clientId}">Powrót do listy aktywności</a>
</body>
</html>
