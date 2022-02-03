package musicToMuch;

import org.stringtemplate.v4.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class InicioSesion extends HttpServlet{
    

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {

                    resp.setCharacterEncoding("UTF-8");

                    HttpSession session = req.getSession(true);
                    
                    session.setMaxInactiveInterval(60 * 10);

                    PrintWriter out = resp.getWriter();
                    String usuario = req.getParameter("usuario");
                    String passwordWeb = req.getParameter("password");
                    String hashtextWeb = DB.getHashTextPasswordFromText(passwordWeb);;
                    String hashtextDb = DB.getHashTextPassword(usuario);
                    
                    session.setAttribute("admin",(comprobarPassword(hashtextDb,hashtextWeb))?"true":"false");

                    System.err.println(comprobarPassword(hashtextDb, hashtextWeb));
                    
                    System.err.println( "ADMIN STATUS!!!!!" + (String) session.getAttribute("admin"));


                    pintarPagina(out, req, resp, session);
                    
                  


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {

                    PrintWriter out = resp.getWriter();
                    resp.setCharacterEncoding("UTF-8");

                    pintarPagina(out);
                    
                    
            


    }

    private boolean comprobarPassword(String pass1, String pass2)
    {
      return pass1.equals(pass2);
    }


    private void pintarPagina(PrintWriter out, HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws ServletException, IOException 
    {
      boolean admin = Boolean.parseBoolean((String) session.getAttribute("admin"));
      
      if(admin)
      {
        resp.sendRedirect(req.getContextPath() + "/panelControl?pag=home");
      }else
      {
        pintarPaginaAlert(out);
      }



    }

    private void pintarPagina(PrintWriter out)
    {
      out.println(PlantillasHTML.DOCTYPE);
      out.println(PlantillasHTML.OPEN_HTML);
      out.println(PlantillasHTML.HTML_LOGIN_HEADER);
      
      out.println(PlantillasHTML.OPEN_BODY);
      
      // PlantillasHTML.setCloseButton();
      // out.println(PlantillasHTML.NAVBAR.render());

      out.println(PlantillasHTML.NAVBAR_TEMPLATE);
      out.println(PlantillasHTML.LOGIN_CONTAINER);

      out.println(PlantillasHTML.BUNDLE_POPPER_SCRIPT);

      out.println(PlantillasHTML.CLOSE_BODY);
      
      out.println(PlantillasHTML.HTML_FOOTER);

      out.println(PlantillasHTML.CLOSE_HTML);


    }

    private void pintarPaginaAlert(PrintWriter out)
    {
      out.println(PlantillasHTML.DOCTYPE);
      out.println(PlantillasHTML.OPEN_HTML);
      out.println(PlantillasHTML.HTML_LOGIN_HEADER);
      
      out.println(PlantillasHTML.OPEN_BODY);

      out.println(PlantillasHTML.NAVBAR_TEMPLATE);

      out.println(PlantillasHTML.LOGIN_CONTAINER);

      out.println(PlantillasHTML.BUNDLE_POPPER_SCRIPT);

      out.println(PlantillasHTML.CLOSE_BODY);
      
      out.println(PlantillasHTML.HTML_FOOTER);

      out.println(PlantillasHTML.CLOSE_HTML);


    }

    



}
