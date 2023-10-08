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

<h2>Informacje o kliencie</h2>
<!-- link do formularza edycji klienta -->
<a href="x">
  <button>Edytuj</button>
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
<a href="x">
  <button>Usuń</button>
</a>
</body>
</html>
