<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formularz logowania</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }
        .login-container {
            max-width: 300px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            text-align: center;
        }
        .login-container h2 {
            margin-bottom: 20px;
        }
        .login-container input[type="text"],
        .login-container input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        .login-container input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            padding: 10px 20px;
            cursor: pointer;
        }
        .login-container a {
            text-decoration: none;
            color: #007bff;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2>Logowanie</h2>
    <form action="/home" method="POST">
        <label for="login">Login:</label>
        <input type="text" id="login" name="login" required><br>

        <label for="password">Hasło:</label>
        <input type="password" id="password" name="password" required><br>

        <input type="submit" value="Zaloguj się">
    </form>
    <p><a href="zapomnialem_hasla.html">Zapomniałem hasła</a></p>
</div>
</body>
</html>