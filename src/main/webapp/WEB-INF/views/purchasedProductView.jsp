<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Szczegóły zakupionego produktu</title>
</head>
<body>

<%@include file="forms/header.jsp"%>

<h1>Szczegóły zakupionego produktu</h1>

<a href="/pproduct/edit/${clientId}/${purchasedProduct.id}">
  <button>Edytuj</button>
</a>

<p><strong>Typ produktu:</strong> ${purchasedProduct.productType}</p>
<p><strong>Data zakupu:</strong> ${purchasedProduct.purchaseDate}</p>
<p><strong>Data aktualizacji:</strong> ${purchasedProduct.pqfmeaUpdateDate}</p>
<p><strong>Data ważności PQ-MSA:</strong> ${purchasedProduct.pqmsaUpdateDate}</p>
<p><strong>Rodzaj licencji:</strong> ${purchasedProduct.licenseSeatNumber}</p>
<p><strong>Cena:</strong> ${purchasedProduct.price}</p>
<p><strong>Rabat:</strong> ${purchasedProduct.rabat}</p>
<p><strong>Cena po rabacie:</strong> ${purchasedProduct.priceWithRabat}</p>
<p><strong>Opis:</strong> ${purchasedProduct.description}</p>
<p><strong>Przydzielone do:</strong> ${purchasedProduct.softwarePatron}</p>

<p><strong>Klient:</strong> ${clientName}</p>

<a href="/clients/${clientId}">Powrót do klienta</a>
</body>
</html>