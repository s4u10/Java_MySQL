package br.com.chamado;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Index")
public class IndexServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 try {	
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Sistema de Chamados</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Sistema de Chamados</h1>");
		out.println("<hr/>");
		out.println("<a href='http://localhost:8080/SytChamado/NovoChamado'>Novo Chamado</a>");
		out.println("<br>");
		out.println("<a href='http://localhost:8080/SytChamado/ListarChamados'>Listar Chamados</a>");
		out.println("<br>");
		out.println("<a href='/Logoff'>Sair</a>");
		out.println("</body>");
		out.println("</html>");
	 } catch (IOException e) {
		 //
	 }
	
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
