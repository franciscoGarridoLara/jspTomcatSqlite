package musicToMuch;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class Instalador extends HttpServlet {
    boolean instalado = false;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        DB db = DB.getInstanceDb();
        animar(out);
        db.crearTablas();
        //sleep(2500);
        //resp.sendRedirect(req.getContextPath() + "/inicioSesion");


    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {

                    doPost(req, resp);
                    


    }

    private void animar(PrintWriter out)
    {
      out.println(PlantillasHTML.DOCTYPE);
      out.println(PlantillasHTML.OPEN_HTML);
      out.println(PlantillasHTML.HTML_INSTALLER_HEADER);
      out.println(PlantillasHTML.OPEN_BODY);

      
      out.println(PlantillasHTML.OPEN_MAIN);

      out.println(PlantillasHTML.INSTALLER_SPINNER);

      out.println(PlantillasHTML.CLOSE_MAIN);
      
      out.println(PlantillasHTML.SCRIPT_CARGA);
      out.println(PlantillasHTML.CLOSE_BODY);
      out.println(PlantillasHTML.CLOSE_HTML);
    }

    private void sleep(int milis)
    {
        try {
            Thread.sleep(milis);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
