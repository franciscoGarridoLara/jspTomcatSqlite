package musicToMuch;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Cerrar extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {

                    HttpSession session = req.getSession(true);
                    
                    if(session != null)
                    {
                        session.invalidate();
                        resp.sendRedirect(req.getContextPath() + "/inicioSesion");
                    }
                    
                  


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {

                    doPost(req, resp);
    }
}
