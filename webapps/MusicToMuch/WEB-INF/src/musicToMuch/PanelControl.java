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


public class PanelControl extends HttpServlet{
    private String opcion = null;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {

                    HttpSession session = req.getSession(true);
                    
                    if(this.opcion == null)
                    {
                      this.opcion = "home";
                    }

                    PrintWriter out = resp.getWriter();

                    if(session != null)
                    {
                      if(session.getAttribute("admin") != null)
                      {
                        if(session.getAttribute("admin").equals("true"))
                        {
                          System.err.println("FELICIDADES ES ADMIN");
                          pintarPanel(out);
                        }else
                        {
                          System.err.println("NO ES ADMIN");
                        }
                      }else
                      {
                        pintarPaginaSinAutorizacion(out);
                      }
                    }
                    
                  


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {
                    this.opcion = req.getParameter("pag");

                    resp.setCharacterEncoding("UTF-8");
                    doPost(req, resp);
                    
    }

    private void pintarPanel(PrintWriter out)
    {
                    switch(opcion)  
                    {
                      case "home":
                        pintarHome(out);
                      break;
                      
                      case "chgPasswd":
                        pintarPasswd(out);
                      break;
                      
                      case "entradas":
                        pintarEntradas(out);
                      break;
                    }
    }

    private void pintarPaginaSinAutorizacion(PrintWriter out)
    {
                    out.println("<html>");
                    out.println("<h1>No estas autorizado crack.</h1>");
                    out.println("</html>");
    }

    private void pintarHome(PrintWriter out)
    {
      out.println(PlantillasHTML.DOCTYPE);
      out.println(PlantillasHTML.OPEN_HTML);
      out.println(PlantillasHTML.HTML_PANEL_HEADER);
      out.println(PlantillasHTML.OPEN_BODY);

      out.println(PlantillasHTML.NAVBAR_TEMPLATE);
      out.println(PlantillasHTML.OPEN_MAIN);

      out.println(PlantillasHTML.PANEL_SIDEBAR);
      out.println(PlantillasHTML.CONTAINER_PANEL_HOME);

      out.println(PlantillasHTML.CLOSE_MAIN);
      out.println(PlantillasHTML.CLOSE_BODY);
      out.println(PlantillasHTML.CLOSE_HTML);
    }

    private void pintarPasswd(PrintWriter out)
    {
      out.println(PlantillasHTML.DOCTYPE);
      out.println(PlantillasHTML.OPEN_HTML);
      out.println(PlantillasHTML.HTML_PANEL_HEADER);
      out.println(PlantillasHTML.OPEN_BODY);

      out.println(PlantillasHTML.NAVBAR_TEMPLATE);
      out.println(PlantillasHTML.OPEN_MAIN);

      out.println(PlantillasHTML.PANEL_SIDEBAR);
      out.println(PlantillasHTML.CONTAINER_PANEL_PASSWD);

      out.println(PlantillasHTML.CLOSE_MAIN);
      out.println(PlantillasHTML.CLOSE_BODY);
      out.println(PlantillasHTML.CLOSE_HTML);
    }

    private void pintarEntradas(PrintWriter out)
    {
      
      out.println(PlantillasHTML.DOCTYPE);
      out.println(PlantillasHTML.OPEN_HTML);
      out.println(PlantillasHTML.HTML_PANEL_HEADER);
      out.println(PlantillasHTML.OPEN_BODY);

      out.println(PlantillasHTML.NAVBAR_TEMPLATE);
      out.println(PlantillasHTML.OPEN_MAIN);

      out.println(PlantillasHTML.PANEL_SIDEBAR);
      out.println(getEntradasFormateadas());

      out.println(PlantillasHTML.CLOSE_MAIN);
      out.println(PlantillasHTML.CLOSE_BODY);
      out.println(PlantillasHTML.CLOSE_HTML);
      
    }


    private String getEntradasFormateadas()
    {
      ST st;
      String result = "";
      String htmlEntradas = "";
      List<Entrada> entradas = DB.getInstanceDb().getAllEntradas();

      for (Entrada entrada : entradas) {
        st = new ST(PlantillasHTML.TR_ENTRADA,'$','$');
        st.add("nombre_entrada", entrada.getTitulo());
        st.add("id", entrada.getId());
        htmlEntradas+= st.render();
      }

      st = new ST(PlantillasHTML.CONTAINER_PANEL_ENTRADAS, '$','$');
      st.add("entradas_td", htmlEntradas);
      result = st.render();

      return result;
    }





}
