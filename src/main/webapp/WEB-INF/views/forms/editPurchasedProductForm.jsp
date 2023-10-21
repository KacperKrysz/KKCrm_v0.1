<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Edycja aktywności</title>
</head>
<body>

<%@include file="header.jsp"%>

<h1>Dodaj Produkt Zakupiony</h1>
<form method="post">

  <label for="productType">Typ produktu:</label>
  <select name="productType" id="productType" required>
    <option value="PQ-FMEA+" ${purchasedProduct.productType == 'PQ-FMEA+' ? 'selected' : ''}>PQ-FMEA+</option>
    <option value="PQ-FMEA" ${purchasedProduct.productType == 'PQ-FMEA' ? 'selected' : ''}>PQ-FMEA</option>
    <option value="PQ-MSA+" ${purchasedProduct.productType == 'PQ-MSA+' ? 'selected' : ''}>PQ-MSA+</option>
  </select><br>

  <label for="purchaseDate">Data zakupu:</label>
  <input type="date" name="purchaseDate" id="purchaseDate" value="${purchasedProduct.purchaseDate}" required><br>

  <label for="pqfmeaUpdateDate">Data aktualizacji:</label>
  <input type="date" name="pqfmeaUpdateDate" id="pqfmeaUpdateDate" value="${purchasedProduct.pqfmeaUpdateDate}"><br>

  <label for="pqmsaUpdateDate">Data ważności PQ-MSA:</label>
  <input type="date" name="pqmsaUpdateDate" id="pqmsaUpdateDate" value="${purchasedProduct.pqmsaUpdateDate}"><br>

  <label for="licenseSeatNumber">Rodzaj licencji:</label>
  <select name="licenseSeatNumber" id="licenseSeatNumber" required>
    <option value="unlimited" ${purchasedProduct.licenseSeatNumber == 'unlimited' ? 'selected' : ''}>nieograniczona</option>
    <option value="subscription" ${purchasedProduct.licenseSeatNumber == 'subscription' ? 'selected' : ''}>subskrybcja</option>
    <option value="1-seat" ${purchasedProduct.licenseSeatNumber == '1-seat' ? 'selected' : ''}>1 stanowisko</option>
    <option value="2-seat" ${purchasedProduct.licenseSeatNumber == '2-seat' ? 'selected' : ''}>2 stanowiska</option>
    <option value="3-seat" ${purchasedProduct.licenseSeatNumber == '3-seat' ? 'selected' : ''}>3 stanowiska</option>
    <option value="4-seat" ${purchasedProduct.licenseSeatNumber == '4-seat' ? 'selected' : ''}>4 stanowiska</option>
    <option value="5-seat ${purchasedProduct.licenseSeatNumber == '5-seat' ? 'selected' : ''}">5 stanowisk</option>
    <option value="6-seat" ${purchasedProduct.licenseSeatNumber == '6-seat' ? 'selected' : ''}>6 stanowisk</option>
  </select><br>

  <label for="price">Cena:</label>
  <input type="number" name="price" id="price" step="0.01" value="${purchasedProduct.price}" required><br>

  <label for="rabat">Rabat:</label>
  <input type="number" name="rabat" id="rabat" value="${purchasedProduct.rabat}"><br>

  <label for="priceWithRabat">Cena po rabacie:</label>
  <input type="number" name="priceWithRabat" id="priceWithRabat" step="0.01" value="${purchasedProduct.priceWithRabat}"><br>

  <label for="description">Opis:</label>
  <textarea name="description" id="description" value="${purchasedProduct.description}"></textarea><br>

  <label for="softwarePatron">Przydzielone do:</label>
  <input type="text" name="softwarePatron" id="softwarePatron" value="${purchasedProduct.softwarePatron}"><br>

  <input type="submit" value="Zapisz zmiany">
</form>

<a href="/pproduct/${clientId}/${purchasedProductId}">
  <button>Anuluj</button>
</a>
</body>
</html>
