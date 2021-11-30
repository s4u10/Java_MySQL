<%@page import="java.sql.SQLException" %>
<%@page import="java.sql.PreparedStatement" %>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.Connection" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="erro.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de Usuários</title>
</head>
<body>
<%
	if (request.getParameter("msg") !=null){
		out.println("Caadastrado com sucesso!");
	}
%>

	<form method="POST" action="Usuario">
	Login:<br>
	<input type="text" name="txtLogin">
	<br>
	Senha:<br>
	<input type="password" name="TxtSenha">
	<br>
	<input type="submit" value="Cadastrar">
	</form>
</body>
</html>