package musicToMuch;

import org.stringtemplate.v4.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Blog extends HttpServlet{
    
    ST st;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {

                    HttpSession session = req.getSession(true);
                    

                    PrintWriter out = resp.getWriter();

                    if(session != null)
                    {
                      if(session.getAttribute("admin") != null)
                      {
                        if(session.getAttribute("admin").equals("true"))
                        {
                          System.err.println("FELICIDADES ES ADMIN");
                          pintarBlogAdmin(out);
                        }else
                        {
                            System.err.print("NO ES ADMIN");
                            pintarBlogUsuario(out);

                        }
                      }else
                      {
                        pintarBlogUsuario(out);
                      }
                    }
                    
                  


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {

                    doPost(req, resp);
    }


    private void pintarBlogAdmin(PrintWriter out)
    {
        out.println(PlantillasHTML.DOCTYPE);
        out.println(PlantillasHTML.OPEN_HTML);
        out.println(PlantillasHTML.HTML_BLOG_HEADER);
        
        out.println(PlantillasHTML.OPEN_BODY);
        out.println(PlantillasHTML.NAVBAR_TEMPLATE);
        out.println(getEntradasAdmin());

        out.println(PlantillasHTML.BUNDLE_POPPER_SCRIPT);
        out.println(PlantillasHTML.CLOSE_BODY);
        
        out.println(PlantillasHTML.CLOSE_HTML);
    }

    private void pintarBlogUsuario(PrintWriter out)
    {
      out.println(PlantillasHTML.DOCTYPE);
      out.println(PlantillasHTML.OPEN_HTML);
      out.println(PlantillasHTML.HTML_BLOG_HEADER);
      
      out.println(PlantillasHTML.OPEN_BODY);
      out.println(PlantillasHTML.NAVBAR_TEMPLATE);
      out.println(getEntradasUser());
      

      out.println(PlantillasHTML.BUNDLE_POPPER_SCRIPT);
      out.println(PlantillasHTML.CLOSE_BODY);
      
      out.println(PlantillasHTML.CLOSE_HTML);
    }

    private String getEntradasAdmin()
    {
      String html = "";

      for (Entrada entrada : DB.getInstanceDb().getAllEntradas()) {
          html += entrada.getHTMLAdmin();
      }

      st = new ST(PlantillasHTML.CONTAINER_BLOG,'$','$');
      st.add("entradas", html);
      html = st.render();

      return html;
    }

    private String getEntradasUser()
    {
      String html = "";

      for (Entrada entrada : DB.getInstanceDb().getAllEntradas()) {
        html += entrada.getHTMLUser();
    }

      st = new ST(PlantillasHTML.CONTAINER_BLOG,'$','$');
      st.add("entradas", html);
      html = st.render();

      return html;
    }
}
