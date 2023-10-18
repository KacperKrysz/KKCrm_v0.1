<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Wyświetlanie danych Activity</title>
</head>
<body>

<%@include file="forms/header.jsp"%>

<h1>Szczegóły aktywności</h1>

<a href="/activity/edit/${clientId}/${activity.id}">
  <button>Edytuj</button>
</a>

  <p><strong>Temat:</strong> ${activity.subject}</p>
  <p><strong>Status:</strong> ${activity.status}</p>
  <p><strong>Data:</strong> ${activity.date}</p>
  <p><strong>Priorytet:</strong> ${activity.priority}</p>
  <p><strong>Opis:</strong> ${activity.description}</p>
  <p><strong>Osoba kontaktowa:</strong> ${activity.contactPerson}</p>
  <p><strong>Software Patron:</strong> ${activity.softwarePatron}</p>

  <p><strong>Klient:</strong> ${clientName}</p>

<a href="/clients/${clientId}">Powrót do listy aktywności</a>
</body>
</html>