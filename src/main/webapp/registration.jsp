<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rejestracja</title>
</head>
<body>
    <form action="registration" method="post">
        <label>Login:</label><input type="text" id="login" name="login" />
        <label>Hasło:</label><input type="text" id="password" name="password" />

        <p>Typ konta:</p>
        <label>Zwykłe</label><input type="radio" name="type" value="normal" />
        <label>Premium</label><input type="radio" name="type" value="premium" />
        <label>Administrator</label><input type="radio" name="type" value="admin" />

        <input type="submit" id="submit" value="Wyślij" />
    </form>
    
    <a href="/">Logowanie</a>
</body>
</html>