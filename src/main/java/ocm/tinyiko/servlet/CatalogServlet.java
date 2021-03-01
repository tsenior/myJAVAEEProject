package ocm.tinyiko.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns= "/CatalogServlet")
public class CatalogServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public CatalogServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append(request.getParameter("name"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String name = request.getParameter("name");
		String manufacture = request.getParameter("manufacture");
		String sku = request.getParameter("sku");
		
		response.setHeader("some Header", "some Header values");
		
		Catalog.addItem(new CatalogItem(name, manufacture, sku));
		
		request.setAttribute("items", Catalog.getItems());
		RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
		dispatcher.forward(request, response);
	}

	
/*private void returnResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {

		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<table>");
		for(CatalogItem item:Catalog.getItems()) {
			out.println("<tr>");
			out.println("<td>");
			out.print(item.getName());
			out.println("</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}*/

}
