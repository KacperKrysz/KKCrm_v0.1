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
  <input type="text" id="fullName" name="fullName" maxlength="100" required><br>

  <label for="shortName">Skrócona nazwa:</label>
  <input type="text" id="shortName" name="shortName" maxlength="100" required><br>

  <label for="type">Typ:</label>
  <select name="type" id="type" required>
    <option value="Klient">Klient</option>
    <option value="Potencjalny klient">Potencjalny klient</option>
    <option value="Nieaktywny">Nieaktywny</option>
    <option value="Uczelnia">Uczelnia</option>
    <option value="Inny">Inny</option>
  </select><br>

  <label for="industry">Branża:</label>
  <input type="text" id="industry" name="industry"><br>

  <label for="address">Adres:</label>
  <textarea id="address" name="address" rows="3" cols="40" ></textarea><br>

  <label for="nip">NIP:</label>
  <input type="text" id="nip" name="nip" maxlength="100"><br>

  <label for="source">Źródło pozyskania:</label>
  <select name="source" id="source" required>
    <option value="kontakt mailowy">Kontakt mailowy</option>
    <option value="konferencja">Konferencja</option>
    <option value="ze szkoleń">Ze szkoleń</option>
    <option value="lead">lead</option>
    <option value="czat">czat</option>
  </select><br>

  <label for="trainingPatron">Patron szkolenia:</label>
  <select name="trainingPatron" id="trainingPatron" required>
    <option value="Imię nazwisko 1">Imię nazwisko 1</option>
    <option value="Imię nazwisko 2">Imię nazwisko 2</option>
    <option value="Imię nazwisko 3">Imię nazwisko 3</option>
    <option value="Imię nazwisko 4">Imię nazwisko 4</option>
    <option value="Imię nazwisko 5">Imię nazwisko 5</option>
    <option value="Imię nazwisko 6">Imię nazwisko 6</option>
    <option value="Imię nazwisko 7">Imię nazwisko 7</option>
  </select><br>

  <label for="softwarePatron">Patron oprogramowania:</label>
  <input type="text" id="softwarePatron" name="softwarePatron" maxlength="100"><br>

  <label for="additionalInfo">Dodatkowe informacje:</label>
  <textarea id="additionalInfo" name="additionalInfo" rows="5" cols="40" ></textarea><br>

  <label for="needManualUpdate">Czy potrzebuje ręcznej aktualizacji:</label>
  <input type="checkbox" id="needManualUpdate" name="needManualUpdate" value="1"><br>
  <input type="hidden" name="needManualUpdate" value="0"><br>


  <input type="submit" value="Dodaj klienta">
</form>
<a href="/clients/list">
  <button>Anuluj</button>
</a>
</body>
</html>
