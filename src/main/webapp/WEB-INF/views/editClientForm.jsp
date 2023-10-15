<%--
  Created by IntelliJ IDEA.
  User: kacpe
  Date: 09.10.2023
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
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
  <label for="fullName">Pełna nazwa:</label>
  <input type="text" id="fullName" name="fullName" maxlength="100" value="${client.fullName}" required><br>

  <label for="shortName">Skrócona nazwa:</label>
  <input type="text" id="shortName" name="shortName" maxlength="100" value="${client.shortName}" required><br>

  <label for="type">Typ:</label>
  <input type="text" id="type" name="type" maxlength="100" value="${client.type}"><br>

  <label for="industry">Branża:</label>
  <input type="text" id="industry" name="industry" maxlength="100" value="${client.industry}"><br>

  <label for="address">Adres:</label>
  <input type="text" id="address" name="address" maxlength="255" value="${client.address}"><br>

  <label for="nip">NIP:</label>
  <input type="text" id="nip" name="nip" maxlength="100" value="${client.nip}"><br>

  <label for="source">Źródło:</label>
  <input type="text" id="source" name="source" maxlength="100" value="${client.source}"><br>

  <label for="contactPerson">Osoba kontaktowa:</label>
  <input type="text" id="contactPerson" name="contactPerson" maxlength="100" value="${client.contactPerson}"><br>

  <label for="trainingPatron">Patron szkolenia:</label>
  <input type="text" id="trainingPatron" name="trainingPatron" maxlength="100" value="${client.trainingPatron}"><br>

  <label for="softwarePatron">Patron oprogramowania:</label>
  <input type="text" id="softwarePatron" name="softwarePatron" maxlength="100" value="${client.softwarePatron}"><br>

  <label for="additionalInfo">Dodatkowe informacje:</label>
  <input type="text" id="additionalInfo" name="additionalInfo" maxlength="100" value="${client.additionalInfo}"><br>

  <label for="needManualUpdate">Czy potrzebuje ręcznej aktualizacji: </label>
  <input type="hidden" name="needManualUpdate" value="0">
  <input type="checkbox" id="needManualUpdate" name="needManualUpdate" value="1" ${client.needManualUpdate == 1 ? 'checked' : ''}><br>



  <input type="submit" value="Zapisz">
</form>

</body>
</html>
