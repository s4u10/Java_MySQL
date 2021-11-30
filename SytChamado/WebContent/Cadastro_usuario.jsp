<%@page import="java.sql.SQLException" %>
<%@page import="java.sql.PreparedStatement" %>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.Connection" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de Usuários</title>
</head>
<body>
	<%
		// Scriptlets
		if (request.getParameter("txtLogin") != null){
			try {
				 Class.forName("com.mysql.jdbc.Driver");
			
				 String SQL = "INSERT INTO USUARIOS (login, senha) VALUES (?,?)";
				 
			try {
				 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chamado_rlsys?useSSL=false","root","a1234567");
				// Statement stm = conn.createStatement();
				 PreparedStatement pstm = conn.prepareStatement(SQL);
				 //stm.execute(SQL);
				 //stm.close();
				 pstm.setString(1, request.getParameter("txtLogin"));
				 pstm.setString(2, request.getParameter("txtSenha"));
				 pstm.execute();
				 pstm.close();
				 conn.close();
				 
				 response.sendRedirect("http://localhost:8080/SytChamado/Login");
				 
				 } catch (SQLException e) {
					 out.println("Problema no banco de dados" + e.getMessage());
				 }
			 } catch (ClassNotFoundException ex) {
				 
				 out.println("Problema ao carregar o drives de conexão!");
			 }
		 }
		
	%>
	<form method="POST" action="Cadastro_usuario.jsp">
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