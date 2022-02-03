package ejemplo;

import java.util.Vector;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Chat extends HttpServlet
{

    public static Vector<Mensaje> mensajes = new Vector<Mensaje>();

    public synchronized void addMensaje(Mensaje m)
    {
        Chat.mensajes.add(m);    
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {
        
        String usuario = req.getParameter("usuario");
        String contenido = req.getParameter("contenido");
        
        Mensaje m = new Mensaje(usuario,contenido);
        

        addMensaje(m);

        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");

        String form = """ 
        
        <form action='sqlite' method='post'>
            <h1>Live Chat 1.0</h1>
            Usuario:
            <input type='text' name='usuario'>
            <br/>
            Mensaje:
            <input type='text' name='contenido'>
            <input type='submit' value='enviar'>
        </form>
        """;

        out.println(form);
        
        for(Mensaje msg: Chat.mensajes)
        {   
            out.println(msg.toString() + "<br/>");
        }
        
        out.println("<br/>Id del hilo " + Thread.currentThread().getId());
        out.println("</body>");
        out.println("</html>");
    }

}