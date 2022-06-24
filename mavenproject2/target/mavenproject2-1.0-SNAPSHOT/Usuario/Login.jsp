<%-- 
    Document   : login
    Created on : 7 de jun. de 2022, 21:10:12
    Author     : sankhya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema - Login</title>
    </head>
    <body>
        <h1>Autentique-se</h1>
        <br>
        <form action="./Login" method="POST">
            <label>Usuario</label><br>
            <input type="text" name="usuario" id="usuario">
            <br><br>
            <label>Senha</label><br>
            <input type="password" name="senha" id="senha">
            <br><br>
            <input type="submit">
        </form>
    </body>
</html>
