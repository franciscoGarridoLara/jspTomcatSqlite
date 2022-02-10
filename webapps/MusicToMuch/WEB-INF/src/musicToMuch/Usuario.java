package musicToMuch;

public class Usuario {

    private String username;
    private String password;
    private boolean admin;

    public Usuario()
    {
        this.username = "";
        this.password = "";
        this.admin = false;
    }

    public Usuario(String user, String passwd, boolean admin)
    {
        this.username = user;
        this.password = passwd;
        this.admin = admin;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setAdmin(boolean admin)
    {
        this.admin = admin;
    }

    public boolean getAdmin()
    {
        return this.admin;
    }

}
