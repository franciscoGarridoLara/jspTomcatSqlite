package musicToMuch;

import org.stringtemplate.v4.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Inicio extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {

                    PrintWriter out = resp.getWriter();
                    resp.setCharacterEncoding("UTF-8");

                    sumarContador();

                    pintarPagina(out);
                    
                    

    }

    private void sumarContador()
    {
        DB.getInstanceDb().incrementarContador();
    }

    private int getContador()
    {
        return DB.getInstanceDb().getContador();
    }

    private void pintarPagina(PrintWriter out)
    {
        ST st = new ST(PlantillasHTML.INDEX_PAGE,'$','$');
        st.add("visitas", getContador());
        out.println(st.render());
    }

}
