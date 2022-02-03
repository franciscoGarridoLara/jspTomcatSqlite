package musicToMuch;

import org.stringtemplate.v4.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Editar extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {

                    resp.setCharacterEncoding("UTF-8");

                    HttpSession session = req.getSession(true);
                    
                    if(session != null)
                    {
                        if(Boolean.parseBoolean((String)session.getAttribute("admin")))
                        {
                            
                        }else
                        {
                            resp.sendRedirect(req.getContextPath() + "/inicioSesion");
                        }
                    }else{
                        resp.sendRedirect(req.getContextPath() + "/inicioSesion");
                    }
                    
                    
                  


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {

                    doPost(req, resp);
    }


}
