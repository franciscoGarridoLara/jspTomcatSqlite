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

public class InsertarUsuario extends HttpServlet{
    
    private boolean insertar = false;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {
                    resp.setCharacterEncoding("UTF-8");

                    HttpSession session = req.getSession(true);

                    PrintWriter out = resp.getWriter();

                    this.insertar = Boolean.parseBoolean((String) req.getParameter("insertar"));

                    if(PanelControl.esAdministrador(session))
                    {
                        if(this.insertar)
                        {
                             insertarUsuario(req,resp);
                             this.insertar = false;
                        }else
                        {
                            pintarFormulario(out);
                        }
                        
                    }else{
                        resp.sendRedirect(req.getContextPath() + "/inicioSesion");
                    }
                    


    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {
                    resp.setCharacterEncoding("UTF-8");
                    doPost(req,resp);

    }



    private void pintarFormulario(PrintWriter out)
    {
      
      out.println(PlantillasHTML.DOCTYPE);
      out.println(PlantillasHTML.OPEN_HTML);
      out.println(PlantillasHTML.HTML_PANEL_HEADER);
      out.println(PlantillasHTML.OPEN_BODY);

      out.println(PlantillasHTML.NAVBAR_TEMPLATE);
      out.println(PlantillasHTML.OPEN_MAIN);

      out.println(PlantillasHTML.PANEL_SIDEBAR);

      out.println(PlantillasHTML.OPEN_CONTAINER);
      out.println(PlantillasHTML.FORM_INSERTAR_USUARIO);
      out.println(PlantillasHTML.CLOSE_DIV);

      out.println(PlantillasHTML.CLOSE_MAIN);
      out.println(PlantillasHTML.CLOSE_BODY);
      out.println(PlantillasHTML.CLOSE_HTML);
      
    }

    private void insertarUsuario(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean admin = Boolean.parseBoolean((String)req.getParameter("isadmin"));
        System.err.println(username + "-" + password + "-" + admin);

        Usuario usuario = new Usuario(username, password, admin);



        if(DB.getInstanceDb().insertarUsuario(usuario))
        {
            System.err.println("Insertado correctamente");
            resp.sendRedirect(req.getContextPath() + "/panelControl?pag=home");
        }else{
            System.err.println("Problema insertando usuario");
            resp.sendRedirect(req.getContextPath() + "/panelControl?pag=home");
        }

    }


}
