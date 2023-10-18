<%--
  Created by IntelliJ IDEA.
  User: kacpe
  Date: 09.10.2023
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<%@include file="header.jsp"%>

<form method="post">
  <label for="nameSurname">Imię i nazwisko:</label>
  <input type="text" name="nameSurname" id="nameSurname" required><br>

  <label for="mobileNumber">Numer telefonu komórkowego:</label>
  <input type="text" name="mobileNumber" id="mobileNumber"><br>

  <label for="phoneNumber">Numer telefonu:</label>
  <input type="text" name="phoneNumber" id="phoneNumber"><br>

  <label for="position">Stanowisko:</label>
  <input type="text" name="position" id="position"><br>

  <label for="department">Dział:</label>
  <input type="text" name="department" id="department"><br>

  <label for="email">Adres email:</label>
  <input type="email" name="email" id="email"><br>

  <label for="isContactPerson">Osoba kontaktowa?</label>
  <input type="checkbox" id="isContactPerson" name="isContactPerson" value="1"><br>
  <input type="hidden" name="isContactPerson" value="0"><br>

  <label for="marketingConsent">Zgoda na marketing:</label>
  <input type="checkbox" id="marketingConsent" name="marketingConsent" value="1"><br>
  <input type="hidden" name="marketingConsent" value="0"><br>

  <label for="rodoConsent">Zgoda na RODO:</label>
  <input type="checkbox" id="rodoConsent" name="rodoConsent" value="1"><br>
  <input type="hidden" name="rodoConsent" value="0"><br>

  <label for="marketingConsentDate">Data zgody na marketing:</label>
  <input type="date" name="marketingConsentDate" id="marketingConsentDate"><br>

  <label for="rodoConsentDate">Data zgody na RODO:</label>
  <input type="date" name="rodoConsentDate" id="rodoConsentDate"><br>

  <label for="softwarePatron">Przydzielone do:</label>
  <input type="text" name="softwarePatron" id="softwarePatron"><br>

  <input type="submit" value="Dodaj kontakt">
</form>

</body>
</html>
