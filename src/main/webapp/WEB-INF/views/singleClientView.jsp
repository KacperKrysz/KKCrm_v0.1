<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Lista Klientów</title>
  <script type="text/javascript">
    function confirmAndRedirect() {
      var answer = confirm("Czy na pewno chcesz usunąć klienta?");
      if (answer) {
        window.location.href = "/clients/delete/${client.id}"; // Przekierowanie do innej strony
      }
    }

  </script>
</head>
<body>

<%@include file="forms/header.jsp"%>

<h2>Informacje o kliencie</h2>
<a href="/clients/edit/${client.id}">
  <button>Edytuj</button>
</a>
<a href="javascript:void(0);" onclick="confirmAndRedirect();">
  <button>Usuń</button>
</a>

<p>ID: ${client.id}</p>
<p>Nazwa: ${client.fullName}</p>
<p>Nazwa skrócona: ${client.shortName}</p>
<p>Typ: ${client.type}</p>
<p>Branża: ${client.industry}</p>
<p>Adres: ${client.address}</p>
<p>NIP: ${client.nip}</p>
<p>Źródło pozyskania: ${client.source}</p>
<p>Osoba kontaktowa: ${client.contactPerson}</p>
<p>Opiekun: ${client.trainingPatron}</p>
<p>Przydzielone do: ${client.softwarePatron}</p>
<p>Zakupione produkty:</p>
<p>PQ-FMEA+
  <input type="checkbox" name="checkboxName" id="checkboxId" disabled
  <c:if test="${client.haspqfmeaPlus == 1}">
         checked
  </c:if>
  > Aktualizacja do: ${client.pqfmeaPlusUpdateDate}
</p>
<p>PQ-FMEA
  <input type="checkbox" name="checkboxName" id="checkboxId" disabled
  <c:if test="${client.haspqfmea == 1}">
         checked
  </c:if>
  > Aktualizacja do: ${client.pqfmeaUpdateDate}
</p>
<p>PQ-MSA
  <input type="checkbox" name="checkboxName" id="checkboxId" disabled
  <c:if test="${client.haspqmsa == 1}">
         checked
  </c:if>
  > Subskrypcja do: ${client.pqmsaUpdateDate}
</p>
<p>Wymagana przesłania update:
  <input type="checkbox" name="checkboxName" id="checkboxId" disabled
  <c:if test="${client.needManualUpdate == 1}">
         checked
  </c:if>
</p>

<P>Dodatkowe informacje:</P>
<p>${client.additionalInfo}</p>
<p>Data utworzenia: ${client.creationDate}</p>
<p>Data modyfikacji: ${client.modificationDate}</p>


  <h2>Aktywności</h2>
  <a href="/activity/addActivity/${clientId}">
    <button>Dodaj</button>
  </a>
  <table border="1">
    <tr>
      <th>Temat</th>
      <th>Status</th>
      <th>Osoba kontaktowa</th>
      <th>Data kontaktu</th>
      <th>Przydzielone do</th>
      <th>Działanie</th>
    </tr>
    <c:forEach var="activity" items="${activities}">
      <tr>
        <td><a href="/activity/${client.id}/${activity.id}">${activity.subject}</a></td>
        <td>${activity.status}</td>
        <td>${activity.contactPerson}</td>
        <td>${activity.date}</td>
        <td>${activity.softwarePatron}</td>
        <td>
          <a href="/activity/delete/${activity.id}" onclick="return confirm('Czy na pewno chcesz usunąć akcję?');">
          <button>Usuń</button>
        </a></td>
      </tr>
    </c:forEach>

  </table>



<h2>Kontakty</h2>
<a href="/contact/add/${clientId}">
  <button>Dodaj</button>
</a>
<table border="1">
  <tr>
    <th>Imię i nazwisko</th>
    <th>E-mail</th>
    <th>Telefon komórkowy</th>
    <th>Telefon stacjonarny</th>
    <th>Stanowisko</th>
    <th>Dział</th>
    <th>Działanie</th>
  </tr>
  <c:forEach var="contact" items="${contacts}">
    <tr>
      <td><a href="/contact/${client.id}/${contact.id}">${contact.nameSurname}</a></td>
      <td>${contact.email}</td>
      <td>${contact.mobileNumber}</td>
      <td>${contact.phoneNumber}</td>
      <td>${contact.position}</td>
      <td>${contact.department}</td>
      <td>
        <a href="/contact/delete/${contact.id}" onclick="return confirm('Czy na pewno chcesz usunąć kontakt?');">
          <button>Usuń</button>
        </a></td>
    </tr>
  </c:forEach>

</table>

</a>
</body>
</html>
