package musicToMuch;
import java.time.LocalDate;

public class Entrada {
    private int id;
    private String titulo;
    private String texto;
    private LocalDate fecha; 


    public Entrada()
    {
        this.id = 0;
        this.titulo = "";
        this.texto = "";
        this.fecha = LocalDate.now();

    }

    public Entrada(int id, String titulo, String texto, LocalDate fecha)
    {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.fecha = fecha;
    }

    public Entrada(String titulo, String texto, LocalDate fecha)
    {
        this.id = 0;
        this.titulo = titulo;
        this.texto = texto;
        this.fecha = fecha;
    }

    public int getId()
    {
        return this.id;
    }

    public String getTitulo()
    {
        return this.titulo;
    }

    public String getTexto()
    {
        return this.texto;
    }

    public LocalDate getFecha()
    {
        return this.fecha;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public void setTexto(String texto)
    {
        this.texto = texto;
    }

    public void setFecha(LocalDate fecha)
    {
        this.fecha = fecha;
    }

    public String toString()
    {
        return "[" + "ID" + this.id + "Titulo: " + this.titulo + " Texto: " + this.texto + "Fecha: " + this.fecha +"]";
    }

    public String getHTMLUser()
    {
        String html = "<div class='card' style='width: 18rem;''>";
        html += "<div class='card-body'>";
        html += "<h5 class='card-title'>"+ this.titulo + "</h5>";
        html += "<h6 class='card-subtitle mb-2 text-muted'>"+ this.fecha + "</h6>";
        html += "<p class='card-text'>"+ this.texto +"</p>";
        html += "</div>";
        html += "</div>";            

        return html;
    }

    public String getHTMLAdmin()
    {
        String html = "<div class='card' style='width: 18rem;''>";
        html += "<div class='card-body'>";
        html += "<h5 class='card-title'>"+ this.titulo + "</h5>";
        html += "<h6 class='card-subtitle mb-2 text-muted'>"+ this.fecha + "</h6>";
        html += "<p class='card-text'>"+ this.texto +"</p>";
        html += "<form action='./editar?update=entradaBlog' method='post'>";
        html += "<input type='hidden' name='idEntrada' value='" + this.id + "'>";
        html += "<input type='submit' value='Editar' class='btn btn-primary'>";    
        html += "</form>"; 
        
        html += "</div>";
        html += "</div>";            

        return html;
    }
}
