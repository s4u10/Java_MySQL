package br.com.chamado;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/EditarChamado")
public class EditarChamadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter(); 
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			 	Connection conn = DriverManager.getConnection(
			 			"jdbc:mysql://localhost:3306/chamado_rlsys?useSSL=false","root","a1234567");
				 
			
				String SQL = "SELECT * FROM chamados WHERE id = ?";
			 
				PreparedStatement pstm = conn.prepareStatement(SQL);
				pstm.setInt(1, Integer.parseInt(request.getParameter("id")));
				
				
				ResultSet rs = pstm.executeQuery();
				
				if(rs.next()) {
				
					out.println("<html>");
					out.println("<head>");
					out.println("<title>Editar Chamado</title>");
					out.println("</head>");
					out.println("<body>");
					out.println("<h1>Editar as informações do chamado</h1>");
					out.println("<hr/>");
					out.println("<form method='POST'>");
					out.println("Título:<br> <input Type='text' name='txtID' readonly='readonly' value='"+ rs.getInt("id")+"'>");
					out.println("<br>");
					out.println("Título:<br> <input Type='text' name='txtTitulo'value='"+ rs.getString("titulo")+"'>");
					out.println("<br>");
					out.println("Conteúdo:<br> <textarea name='txtConteudo' rows'10' cols='40'>"+ rs.getString("conteudo")+"'</textarea>");
					out.println("<br>");
					out.println("<input type='submit' value='Atualizar Chamado'>");
					out.println("</form>");
					out.println("<a href='http://localhost:8080/SytChamado/ListarChamados'>Listar Chamados </a>");
					out.println("<br>");
					out.println("<a href='http://localhost:8080/SytChamado/Logoff'>Sair</a>");
					out.println("</body>");
					out.println("</html>");
				} else {
					out.println("Este cchamado não existe");
				}
				pstm.close();
				conn.close();
				
				} catch (SQLException e) {
			 out.println("Problema no banco de dados" + e.getMessage()); 
		 } catch (ClassNotFoundException ex) {
		 out.println("Problema ao carregar o drives de conexão!");
	 }
}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			 
			 PrintWriter out = response.getWriter();
			 
			 int id = Integer.parseInt(request.getParameter("id"));
			 String sTitulo = request.getParameter("txtTitulo");
			 String sConteudo = request.getParameter("txtConteudo");
						 
			 if (sTitulo.trim().length() < 4) {
				 out.println("Preencha o campo Titulo");
			 } else if (sConteudo.trim().length() < 4) {
				 out.println("Preencha o campo conteudo");
			 } else {
				 try {
					 Class.forName("com.mysql.jdbc.Driver");

					 String SQL = "UPDATE chamados SET titulo = ?, conteudo = ? WHERE ID = ?";
					 
				try {
					 Connection conn = DriverManager.getConnection(
							 "jdbc:mysql://localhost:3306/chamado_rlsys?useSSL=false","root","a1234567");

					 PreparedStatement pstm = conn.prepareStatement(SQL);
				
					 pstm.setString(1, sTitulo);
					 pstm.setString(2, sConteudo);
					 pstm.setInt(3, id);
					 
					 pstm.execute();
					 pstm.close();
					 conn.close();
					 response.sendRedirect("http://localhost:8080/SytChamado/ListarChamados");
					 
					 } catch (SQLException e) {
						 out.println("Problema no banco de dados" + e.getMessage());
					 }
				 } catch (ClassNotFoundException ex) {
					 
					 out.println("Problema ao carregar o drives de conexão!");
				 }
			 }
		}
}
