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
  <title>Dodaj zakupiony produkt</title>
</head>
<body>
<%@include file="header.jsp"%>

<h1>Dodaj Produkt Zakupiony</h1>
<form method="post">

  <label for="productType">Typ produktu:</label>
  <select name="productType" id="productType" required>
    <option value="PQ-FMEA+">PQ-FMEA+</option>
    <option value="PQ-FMEA">PQ-FMEA</option>
    <option value="PQ-MSA+">PQ-MSA+</option>
  </select><br>

  <script>
    let currentDate = new Date();

    let year = currentDate.getFullYear();
    let month = (currentDate.getMonth() + 1).toString().padStart(2, '0');
    let day = currentDate.getDate().toString().padStart(2, '0');
    let formattedDate = year + "-" + month + "-" + day;

    document.getElementById("purchaseDate").value = formattedDate;
  </script>

  <label for="purchaseDate">Data zakupu:</label>
  <input type="date" name="purchaseDate" id="purchaseDate" required><br>

  <label for="pqfmeaUpdateDate">Data aktualizacji:</label>
  <input type="date" name="pqfmeaUpdateDate" id="pqfmeaUpdateDate"><br>

  <label for="pqmsaUpdateDate">Data ważności PQ-MSA:</label>
  <input type="date" name="pqmsaUpdateDate" id="pqmsaUpdateDate"><br>

  <label for="licenseSeatNumber">Rodzaj licencji:</label>
  <select name="licenseSeatNumber" id="licenseSeatNumber" required>
    <option value="unlimited">nieograniczona</option>
    <option value="subscription">subskrybcja</option>
    <option value="1-seat">1 stanowisko</option>
    <option value="2-seat">2 stanowiska</option>
    <option value="3-seat">3 stanowiska</option>
    <option value="4-seat">4 stanowiska</option>
    <option value="5-seat">5 stanowisk</option>
    <option value="6-seat">6 stanowisk</option>
  </select><br>

  <label for="price">Cena:</label>
  <input type="number" name="price" id="price" step="0.01" required><br>

  <label for="rabat">Rabat:</label>
  <input type="number" name="rabat" id="rabat"><br>

  <label for="priceWithRabat">Cena po rabacie:</label>
  <input type="number" name="priceWithRabat" id="priceWithRabat" step="0.01"><br>

  <label for="description">Opis:</label>
  <textarea name="description" id="description"></textarea><br>

  <label for="softwarePatron">Przydzielone do:</label>
  <input type="text" name="softwarePatron" id="softwarePatron"><br>

  <input type="submit" value="Dodaj Produkt">
</form>

</body>
</html>
