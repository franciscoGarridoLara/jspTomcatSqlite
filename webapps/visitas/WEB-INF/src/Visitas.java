package ejemplo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Visitas extends HttpServlet {
    static int contador = 0;


    private void synchronized incrementar()
    {
     this.contador++;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>Hola mundo</h1>");
        out.println("hoy es " + new Date());
        out.println("<br/>Visitas " + incrementar());
        out.println("<br/>Id del hilo " + Thread.currentThread().getId());
        out.println("</body>");
        out.println("</html>");
    }
}
