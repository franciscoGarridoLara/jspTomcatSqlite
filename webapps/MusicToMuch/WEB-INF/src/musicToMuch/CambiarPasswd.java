package musicToMuch;

import java.util.*;
import org.stringtemplate.v4.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CambiarPasswd extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {

                    HttpSession session = req.getSession(true);
                    
                    

                    PrintWriter out = resp.getWriter();


                    if(PanelControl.esAdministrador(session))
                    {
                      if(cambiarPasswd(req,resp,session))
                         {
                             System.err.println("contraseña cambiada");
                             resp.sendRedirect(req.getContextPath() + "/panelControl?pag=chgPasswd&success=true");
                            
                         }else{
                            System.err.println("contraseña NO cambiada");
                            resp.sendRedirect(req.getContextPath() + "/panelControl?pag=chgPasswd&success=false");
                         }
                    }
                    
                  


    }

    private boolean cambiarPasswd(HttpServletRequest req, HttpServletResponse resp, HttpSession session)
    throws ServletException, IOException {
    
        boolean exito = false;
        String original;
        String usuario;


        String pass1 = req.getParameter("pass1");
        String pass2 = req.getParameter("pass2");


        if(pass1 != null && pass2 != null)
        {
            
            usuario = (String) session.getAttribute("user");

            original = DB.getInstanceDb().getUsuario(usuario).getPassword();
            
            pass1 = DB.getHashTextPasswordFromText(pass1);

            System.err.println(usuario + "--" + pass1 + ":" + pass2 + ":" + original);

            

            if(original.equals(pass1))
            {
                exito = DB.getInstanceDb().updatePassword(usuario, pass2);
            }


        }

        return exito;
    }




}
