package ejemplo;
import java.time.LocalDateTime;


public class Mensaje {
    private String usuario;
    private String mensaje;

    public Mensaje()
    {
        this.usuario = "";
        this.mensaje = "";
    }

    public Mensaje(String usuario, String mensaje)
    {
        this.usuario = usuario;
        this.mensaje = mensaje;
    }

    //get,set

    public String getUsuario()
    {
        return this.usuario;
    }



    public String getMensaje()
    {
        return this.mensaje;
    }


    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }


    public void setMensaje(String msg)
    {
        this.mensaje = msg;
    }

    public String toString()
    {
        return "[ " + LocalDateTime.now() + " ] " +  this.usuario + ": " + this.mensaje;
    }

}
