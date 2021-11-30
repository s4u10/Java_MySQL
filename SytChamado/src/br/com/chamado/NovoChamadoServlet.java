package br.com.chamado;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/NovoChamado")
public class NovoChamadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Novo Chamado</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1>Preencha as informações do chamado</h1>");
				out.println("<hr/>");
				out.println("<form method='POST'>");
				out.println("Título:<br> <input Type='text' name='txtTitulo'>");
				out.println("<br>");
				out.println("Conteúdo:<br> <textarea name='txtConteudo' rows'10' cols='40'></textarea>");
				out.println("<br>");
				out.println("<input type='submit' value='Abrir Chamado'>");
				out.println("</form>");
				out.println("<a href='http://localhost:8080/SytChamado/ListarChamados'>Listar Chamados </a>");
				out.println("<br>");
				out.println("<a href='http://localhost:8080/SytChamado/logoff'>Sair</a>");
				out.println("</body>");
				out.println("</html>");
			 } catch (IOException e) {
				 
			 }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			 
			 PrintWriter out = response.getWriter();
			 
			 String sTitulo = request.getParameter("txtTitulo");
			 String sConteudo = request.getParameter("txtConteudo");
						 
			 if (sTitulo.trim().length() < 4) {
				 out.println("Preencha o campo Titulo");
			 } else if (sConteudo.trim().length() < 4) {
				 out.println("Preencha o campo conteudo");
			 } else {
				 try {
					 Class.forName("com.mysql.jdbc.Driver");
					// String SQL = "INSERT INTO chamados (titulo, conteudo) VALUES (";
					// SQL += " '" + sTitulo + "','"+ sConteudo +"')";
					 String SQL = "INSERT INTO chamados (titulo, conteudo, data) VALUES (?,?, ?)";
					 
				try {
					 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chamado_rlsys?useSSL=false","root","a1234567");
					// Statement stm = conn.createStatement();
					 PreparedStatement pstm = conn.prepareStatement(SQL);
					 //stm.execute(SQL);
					 //stm.close();
					 
					 //Date data = new Date();
					 
					// java.sql.Date dataSQL = new java.sql.Date(data.getTime());
					 
					 //SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
					 // dt.format(data);
					 
					 
					 Date dataSQL = new Date(new java.util.Date().getTime());
					 
					 pstm.setString(1, sTitulo);
					 pstm.setString(2, sConteudo);
					// pstm.setDate(3, dt.format(data));
					 pstm.setDate(3,  dataSQL);
					 
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

