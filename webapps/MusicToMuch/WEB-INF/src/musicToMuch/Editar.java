package musicToMuch;


import org.stringtemplate.v4.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Editar extends HttpServlet{
    Optional<Entrada> opEntrada;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {
                    resp.setCharacterEncoding("UTF-8");
                    resp.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
                    resp.setHeader("Pragma", "no-cache");

                    String idEntrada = req.getParameter("idEntrada");
                    String borrar = (String) req.getParameter("borrar");
                    
                    System.err.print(borrar);
                    
                    PrintWriter out = resp.getWriter();

                    resp.setCharacterEncoding("UTF-8");

                    HttpSession session = req.getSession(true);
                    
                    if(session != null)
                    {
                        if(Boolean.parseBoolean((String)session.getAttribute("admin")))
                        {
                            
                            if(req.getParameter("update") != null && req.getParameter("update").equals("true"))
                            {
                                updateEntrada(req,resp);
                            }else if(req.getParameter("insertar") != null && req.getParameter("insertar").equals("true"))
                            {
                                pintarInsertarEntrada(req,resp);
                            
                            }else if(req.getParameter("insertar") != null && req.getParameter("insertar").equals("entrada"))
                            {
                                insertarEntrada(req,resp);
                            }else if(req.getParameter("update") != null && req.getParameter("update").equals("entradaBlog"))
                            {
                                editarEntradaBlog(req,resp);
                            }else if(borrar != null && borrar.equals("Borrar"))
                            {
                                if(idEntrada != null)
                                {
                                    borrarEntrada(req,resp, idEntrada);
                                }
                            }
                            
                            else
                                {
                                    if(idEntrada != null)
                                    {
                                    
                                        this.opEntrada = DB.getInstanceDb().getEntradaById(idEntrada);
                                        if(opEntrada.isPresent())
                                    {
                                        
                                        pintarEntrada(out, opEntrada.get());
                                    }
                            
                                }
                            }
                            
                        }else
                        {
                            resp.sendRedirect(req.getContextPath() + "/inicioSesion");
                        }
                    }else{
                        resp.sendRedirect(req.getContextPath() + "/inicioSesion");
                    }
                    
                    
                  


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                  throws ServletException, IOException {
                    resp.setCharacterEncoding("UTF-8");
                    doPost(req, resp);
    }


    private void pintarEntrada(PrintWriter out, Entrada entrada)
    {
      
      out.println(PlantillasHTML.DOCTYPE);
      out.println(PlantillasHTML.OPEN_HTML);
      out.println(PlantillasHTML.HTML_PANEL_HEADER);
      out.println(PlantillasHTML.OPEN_BODY);

      out.println(PlantillasHTML.NAVBAR_TEMPLATE);
      out.println(PlantillasHTML.OPEN_MAIN);

      out.println(PlantillasHTML.PANEL_SIDEBAR);

      out.println(PlantillasHTML.OPEN_CONTAINER);
      out.println(getFormEntradaFomateado(entrada));
      out.println(PlantillasHTML.CLOSE_DIV);

      out.println(PlantillasHTML.CLOSE_MAIN);
      out.println(PlantillasHTML.CLOSE_BODY);
      out.println(PlantillasHTML.CLOSE_HTML);
      
    }

    private String getFormEntradaFomateado(Entrada entrada)
    {
        ST st = new ST(PlantillasHTML.FORM_ENTRADA,'$','$');
        st.add("id", entrada.getId());
        st.add("titulo", entrada.getTitulo());
        st.add("contenido", entrada.getTexto());
        st.add("fecha", entrada.getFecha());

        return st.render();

    }

    private void updateEntrada(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException 
    {

        int id;
        String titulo, texto;
        LocalDate fecha;

        id = Integer.parseInt(req.getParameter("id"));
        titulo = formatearContenido((String) req.getParameter("titulo"));
        texto = formatearContenido((String) req.getParameter("contenido"));
        fecha = LocalDate.parse(req.getParameter("fecha"));

        

        Entrada entrada = new Entrada(id,titulo,texto,fecha);

        
        if(DB.getInstanceDb().editarEntrada(entrada))
        {
            resp.sendRedirect(req.getContextPath() + "/panelControl?pag=entradas");
        }else
        {
            System.err.println("Error actualizando la entrada.");
        }

    }

    private void editarEntradaBlog(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException 
    {
        Runnable error = () ->  System.err.println("Error editando entrada de blog");
        String id = req.getParameter("idEntrada");
        Optional<Entrada> entrada = Optional.empty();

        if(id != null)
        {
            System.err.println("ID: " + id);
            entrada = DB.getInstanceDb().getEntradaById(id);
        }

        PrintWriter out = resp.getWriter();

        if(entrada.isPresent())
        {
            pintarEntrada(out,entrada.get());
        }





    }

    private void pintarInsertarEntrada(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException 
    {

        PrintWriter out = resp.getWriter();

      out.println(PlantillasHTML.DOCTYPE);
      out.println(PlantillasHTML.OPEN_HTML);
      out.println(PlantillasHTML.HTML_PANEL_HEADER);
      out.println(PlantillasHTML.OPEN_BODY);

      out.println(PlantillasHTML.NAVBAR_TEMPLATE);
      out.println(PlantillasHTML.OPEN_MAIN);

      out.println(PlantillasHTML.PANEL_SIDEBAR);

      out.println(PlantillasHTML.OPEN_CONTAINER);
      out.println(PlantillasHTML.FORM_ENTRADA_INSERTAR);
      out.println(PlantillasHTML.CLOSE_DIV);

      out.println(PlantillasHTML.CLOSE_MAIN);
      out.println(PlantillasHTML.CLOSE_BODY);
      out.println(PlantillasHTML.CLOSE_HTML);

    }

    private void insertarEntrada(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException 
    {
        int id;
        String titulo, texto;
        LocalDate fecha;

        
        titulo = formatearContenido((String) req.getParameter("titulo"));
        texto = formatearContenido((String) req.getParameter("contenido"));
        try {
            fecha = LocalDate.parse(req.getParameter("fecha"));
        } catch (DateTimeParseException e) {
            System.err.println(e);
            e.printStackTrace();
            fecha = LocalDate.now();
        }

        
        Entrada entrada = new Entrada(titulo,texto,fecha);

        
        if(DB.getInstanceDb().insertarEntrada(entrada))
        {
            resp.sendRedirect(req.getContextPath() + "/panelControl?pag=entradas");
        }else
        {
            System.err.println("Error insertando la entrada.");
        }
    }

    private void borrarEntrada(HttpServletRequest req, HttpServletResponse resp, String idEntrada)
    throws ServletException, IOException 
    {

        boolean exito = DB.getInstanceDb().borrarEntrada(idEntrada);
        resp.sendRedirect(req.getContextPath() + "/panelControl?pag=entradas");
        


    }

    private String formatearContenido(String contenido)
    {
        return contenido.replaceAll("\\<.*?\\>", "");
    }
}
