package ejemplo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

// Bibliotecas para conectar con SQLite
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class SQLite extends HttpServlet {


    public static Vector<Mensaje> mensajes = new Vector<Mensaje>();

    public synchronized void addMensaje(Mensaje m)
    {
        SQLite.mensajes.add(m);    
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {

        Cookie cookies[] = req.getCookies();
        String username = null;
        String usuario = "";
        if(cookies != null)
        {
            for(Cookie c: cookies)
            {
                if(c.getName().equals("username"))
                {
                    username = c.getValue();
                    break;
                }
            }    

        }

        
        Connection conn = null;
        StringBuffer respuesta = new StringBuffer();
        try {
            // Ruta a la base de datos. El archivo "base_datos.db".
            // Se puede indicar una ruta completa del tipo /home/usuario/... 
            String url = "jdbc:sqlite:base_datos.db";
            // Se crea la conexión a la base de datos:
		Class.forName("org.sqlite.JDBC")
			.getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    // Se crea la tabla si no existe
                    String sql = "CREATE TABLE IF NOT EXISTS mensajes (\n"
                        + "usuario TEXT ,\n"
                        + "contenido TEXT\n"
                        + ");";
                    Statement stmt = conn.createStatement();
                    stmt.execute(sql);

                    // Se insertan los datos
                    String sqlInsert = 
				"INSERT INTO mensajes(usuario, contenido) VALUES(?,?)";
                    PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
                    usuario = req.getParameter("usuario");
                    String contenido = req.getParameter("contenido");
                    pstmt.setString(1, usuario);
                    pstmt.setString(2, contenido);
                    pstmt.executeUpdate();

                    

                    // Se hace una consulta
                    String sqlSelect = "SELECT usuario, contenido FROM mensajes";
                    ResultSet cursor = stmt.executeQuery(sqlSelect);
                    while(cursor.next()) {
                        // Se construye la respuesta que se insertará en el HTML
                        respuesta.append("Usuario: " + cursor.getString("usuario"));
                        respuesta.append(" Mensaje: ");
                        respuesta.append(cursor.getString("contenido"));
                        respuesta.append("<br/>");
                        //addMensaje(new Mensaje(cursor.getString("usuario"), cursor.getString("contenido")));
                    }

                    // Se cierra la conexión con la base de datos
                    conn.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }

        //Creamos la cookie del usuario:

        Cookie cookie = new Cookie("username",usuario);
        cookie.setMaxAge(60);
        cookie.setPath("/chat/sqlite");
        resp.addCookie(cookie);

        // Se escribe la página con la respuesta al usuario
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");

        String form = """ 
        
        <form action='sqlite' method='post'>
            <h1>Live Chat 1.0</h1>
            Usuario:

        """;
        
        form += 
            "<input type='text' name='usuario' value='" + cookie.getValue() +"'>";


        form +=     
        """
            <br/>
            Mensaje:
            <input type='text' name='contenido'>
            <input type='submit' value='enviar'>
        </form>
        """;

        out.println(form);
       
        out.println("<h1>Mensajes:</h1>");
        out.println(respuesta.toString());
        //for (Mensaje mensaje : mensajes) {
        //    out.println("<p>" +  mensaje.toString()+ "</p> </br>");
        //}
        

        out.println("</body>");
        out.println("</html>");
    }
}
