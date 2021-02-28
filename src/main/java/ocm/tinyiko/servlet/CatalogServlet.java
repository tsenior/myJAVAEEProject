package ocm.tinyiko.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = "/CatalogServlet", asyncSupported = true)
public class CatalogServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public CatalogServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append(request.getParameter("name"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AsyncContext asyncContext = request.startAsync();
		
		//starting a new thread
		asyncContext.start(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					System.out.println("Print the response");
					System.out.println("Response return by  thread: " + Thread.currentThread().getName());
					returnResponse(request, response);
					asyncContext.complete();
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		System.out.println("initial thread, Response return by thread: " + Thread.currentThread().getName());
	}

	private void returnResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String name = request.getParameter("name");
		String manufacture = request.getParameter("manufacture");
		String sku = request.getParameter("sku");
		
		Catalog.addItem(new CatalogItem(name, manufacture, sku));
		
		response.setHeader("some Header", "some Header values");
		
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
	}

}
