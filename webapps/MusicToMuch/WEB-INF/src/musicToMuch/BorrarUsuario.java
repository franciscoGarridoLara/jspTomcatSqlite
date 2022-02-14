package musicToMuch;

import javax.servlet.http.HttpServlet;
import java.util.*;
import org.stringtemplate.v4.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BorrarUsuario extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {

                    HttpSession session = req.getSession(true);
                    
                    

                    PrintWriter out = resp.getWriter();


                    if(PanelControl.esAdministrador(session))
                    {
                
                        borrarUsuario(req,resp);
                        resp.sendRedirect(req.getContextPath() + "/panelControl");
                    }else
                    {
                        resp.sendRedirect(req.getContextPath() + "/inicioSesion");
                    }
                    
                  


    }
    
    private boolean borrarUsuario(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        
        boolean exito = false;
        String usuario = (String) req.getParameter("username");

        
        if(usuario != null)
        {
            exito = DB.getInstanceDb().borrarUsuario(usuario);
        }


        return exito;    
    }


    

}
