<%@page import="java.sql.SQLException" %>
<%@page import= "java.sql.Statement" %>
<%@page import= "java.sql.ResultSet" %>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.Connection" %>



<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="erro.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table> 

<tr>
	<td>ID</td>
	<td>Login</td>

</tr>
<%
			try {
				 Class.forName("com.mysql.jdbc.Driver");
			
				 String SQL = "SELECT * FROM usuarios";
				 
			try {
				 Connection conn = DriverManager.getConnection(
						 "jdbc:mysql://localhost:3306/chamado_rlsys?useSSL=false","root","a1234567");
		
				 Statement stm = conn.prepareStatement(SQL);
		
				 
				 ResultSet rs = stm.executeQuery(SQL);
				 
				 while(rs.next()){
%>

	<tr>
	<td><% out.println(rs.getString("id")); %></td>
	<TD><% out.println(rs.getString("login")); %></TD>
	</tr>
<%
		 }
				 } catch (SQLException e) {
					 out.println("Problema no banco de dados" + e.getMessage());
				 }
			 } catch (ClassNotFoundException ex) {
				 out.println("Problema ao carregar o drives de conexão!");
			 }
%>

	</table>
</body>
</html>