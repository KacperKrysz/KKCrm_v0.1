<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista Klientów</title>
</head>
<body>
<a href="/home">
    <button>Home</button>
</a>
<a href="/clients/list">
    <button>Klienci</button>
</a>
<a href="x">
    <button>Kontakty</button>
</a>
<a href="x">
    <button>Statystyki</button>
</a>

<h2>Lista Klientów</h2>
<table border="1">
    <tr>
        <th>Nazwa</th>
        <th>Typ</th>
        <th>Adres</th>
        <th>Ważność aktualizacji</th>
        <th>Ważność PQ-MSA</th>
        <th>Suma ilości zakupów</th>
        <th>Suma wartości zakupów</th>
        <th>Licencja</th>
        <th>Przydzielone do</th>
        <!-- Dodaj więcej kolumn, jeśli jest taka potrzeba -->
    </tr>
    <c:forEach var="client" items="${clients}">
        <tr>
            <td><a href="/clients/${client.fullName}">${client.fullName}</a></td>
            <td>${client.type}</td>
            <td>${client.address}</td>
            <td>${client.pqfmeaPlusUpdateDate}</td>
            <td>${client.pqmsaUpdateDate}</td>
            <td>ilość zakupów</td>
            <td>wartość zakupów</td>
            <td>nieograniczona</td>
            <td>${client.softwarePatron}</td>
            <!-- Dodaj więcej kolumn, jeśli jest taka potrzeba -->
        </tr>
    </c:forEach>
</table>
</body>
</html>
