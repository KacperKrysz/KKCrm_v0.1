
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
  <select name="type" id="type" required>
    <option value="Klient" ${client.type == 'Klient' ? 'selected' : ''} >Klient</option>
    <option value="Potencjalny klient" ${client.type == 'Potencjalny klient' ? 'selected' : ''} >Potencjalny klient</option>
    <option value="Nieaktywny" ${client.type == 'Nieaktywny' ? 'selected' : ''} >Nieaktywny</option>
    <option value="Uczelnia" ${client.type == 'Uczelnia' ? 'selected' : ''} >Uczelnia</option>
    <option value="Inny" ${client.type == 'Inny' ? 'selected' : ''} >Inny</option>
  </select><br>

  <label for="industry">Branża:</label>
  <input type="text" id="industry" name="industry" maxlength="100" value="${client.industry}"><br>

  <label for="address">Adres:</label>
  <textarea id="address" name="address" rows="3" cols="40" >${client.address}</textarea><br>

  <label for="nip">NIP:</label>
  <input type="text" id="nip" name="nip" maxlength="100" value="${client.nip}"><br>

  <label for="source">Źródło:</label>
  <select name="source" id="source" required>
    <option value="kontakt mailowy" ${client.source == 'kontakt mailowy' ? 'selected' : ''}>Kontakt mailowy</option>
    <option value="konferencja"  ${client.source == 'konferencja' ? 'selected' : ''} >Konferencja</option>
    <option value="ze szkoleń"  ${client.source == 'ze szkoleń' ? 'selected' : ''} >Ze szkoleń</option>
    <option value="lead"  ${client.source == 'lead' ? 'selected' : ''} >lead</option>
    <option value="czat"  ${client.source == 'czat' ? 'selected' : ''} >czat</option>
  </select><br>

  <label for="trainingPatron">Patron szkolenia:</label>
  <select name="trainingPatron" id="trainingPatron" required>
    <option value="Imię nazwisko 1"  ${client.trainingPatron == 'Imię nazwisko 1' ? 'selected' : ''}>Imię nazwisko 1</option>
    <option value="Imię nazwisko 2" ${client.trainingPatron == 'Imię nazwisko 2' ? 'selected' : ''}>Imię nazwisko 2</option>
    <option value="Imię nazwisko 3" ${client.trainingPatron == 'Imię nazwisko 3' ? 'selected' : ''}>Imię nazwisko 3</option>
    <option value="Imię nazwisko 4" ${client.trainingPatron == 'Imię nazwisko 4' ? 'selected' : ''}>Imię nazwisko 4</option>
    <option value="Imię nazwisko 5" ${client.trainingPatron == 'Imię nazwisko 5' ? 'selected' : ''}>Imię nazwisko 5</option>
    <option value="Imię nazwisko 6" ${client.trainingPatron == 'Imię nazwisko 6' ? 'selected' : ''}>Imię nazwisko 6</option>
    <option value="Imię nazwisko 7" ${client.trainingPatron == 'Imię nazwisko 7' ? 'selected' : ''}>Imię nazwisko 7</option>
  </select><br>

  <label for="softwarePatron">Patron oprogramowania:</label>
  <input type="text" id="softwarePatron" name="softwarePatron" maxlength="100" value="${client.softwarePatron}"><br>

  <label for="additionalInfo">Dodatkowe informacje:</label>
  <textarea id="additionalInfo" name="additionalInfo" rows="5" cols="40" >${client.additionalInfo}</textarea><br>

  <label for="needManualUpdate">Czy potrzebuje ręcznej aktualizacji: </label>
  <input type="checkbox" id="needManualUpdate" name="needManualUpdate" value="1" ${client.needManualUpdate == 1 ? 'checked' : ''}><br>
  <input type="hidden" name="needManualUpdate" value="0">


  <input type="submit" value="Zapisz">
</form>

</body>
</html>
