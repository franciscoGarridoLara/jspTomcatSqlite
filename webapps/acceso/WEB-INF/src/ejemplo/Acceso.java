package ejemplo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Acceso extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");
        String user = req.getParameter("user");
        String passwd = req.getParameter("passwd");


	if(user.equalsIgnoreCase("admin") && passwd.equalsIgnoreCase("1234"))
	{
		out.println("Acceso concedido");
	}else
	{
		out.println("Acceso denegado");
	}

        out.println("</body>");
        out.println("</html>");
    }
}
