<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Dane kontaktu</title>
</head>
<body>

<%@include file="forms/header.jsp"%>

<h1>Szczegóły kontaktu</h1>

<a href="/contact/edit/${clientId}/${contact.id}">
  <button>Edytuj</button>
</a>

<p><strong>Imię i nazwisko:</strong> ${contact.nameSurname}</p>
<p><strong>Numer komórkowy:</strong> ${contact.mobileNumber}</p>
<p><strong>Numer stacjonarny:</strong> ${contact.phoneNumber}</p>
<p><strong>Stanowisko:</strong> ${contact.position}</p>
<p><strong>Dział:</strong> ${contact.department}</p>
<p><strong>Adres e-mail:</strong> ${contact.email}</p>
<p>Osoba kontaktowa?
  <input type="checkbox" name="checkboxName" id="checkboxId" disabled
  <c:if test="${contact.isContactPerson == 1}">
         checked
  </c:if>
</p>
<p>Zgoda na marketing:
  <input type="checkbox" name="checkboxName" id="checkboxId" disabled
  <c:if test="${contact.marketingConsent == 1}">
         checked
  </c:if>
  > Data zgody: ${contact.marketingConsentDate}
</p>
<p>Zgoda na RODO:
  <input type="checkbox" name="checkboxName" id="checkboxId" disabled
  <c:if test="${contact.rodoConsent == 1}">
         checked
  </c:if>
  > Data zgody: ${contact.rodoConsentDate}
</p>
<p><strong>Przydzielone do:</strong> ${contact.softwarePatron}</p>

<p><strong>Klient:</strong> ${clientName}</p>

<a href="/clients/${clientId}">Powrót do widoku klienta</a>
</body>
</html>