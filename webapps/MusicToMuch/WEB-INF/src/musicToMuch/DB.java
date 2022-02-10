package musicToMuch;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Optional;
// Bibliotecas para conectar con SQLite
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DB {
    private static DB db_instance = null;
    private final static String DB_NAME = "musicToMuch.db";
    private final static String URL = "jdbc:sqlite:" + DB_NAME;
    public static Connection conn;


    private DB()
    {
        conn = null;
    }


    public static DB getInstanceDb()
    {   
        if(db_instance == null)
        {
            db_instance = new DB();
        }

        return db_instance;
    }


    private static void conectar()
    {
        try {
            Class.forName("org.sqlite.JDBC")
            .getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(URL);
        } catch (Exception e) {
            
            System.out.println(e.getMessage());
        }
    }

    private static void desconectar()
    {
        try {
            if(conn != null)
            {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void crearTablas()
    {
        conectar();

         if(conn != null)
         {
            try {
                // Se crea la tabla si no existe
               String sql = "CREATE TABLE IF NOT EXISTS Usuarios (usuario TEXT PRIMARY KEY, password TEXT, admin TINYINT);";
               Statement stmt = conn.createStatement();
               stmt.execute(sql);
   
               // Se insertan los datos
               String sqlInsert = "INSERT INTO Usuarios(usuario, password,admin) VALUES(?,?,?)";
               PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
               String usuario = "admin";
               String password = "admin";
               Integer admInteger = 1;
   
               
               pstmt.setString(1, usuario);
               pstmt.setString(2, generateMD5(password));
               pstmt.setInt(3, admInteger);
               pstmt.executeUpdate();
   
               sql = "CREATE TABLE IF NOT EXISTS Entradas (id INTEGER PRIMARY KEY AUTOINCREMENT,titulo TEXT,texto TEXT,fecha INTEGER);";
   
   
               
               stmt = conn.createStatement();
               stmt.execute(sql);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            desconectar();
         }

        

    }


    public static String getHashTextPassword(String usuario)
    {
        String hashText = "NO EXISTE";
        String query= "SELECT password FROM Usuarios WHERE usuario = ?";
        PreparedStatement pst;
        ResultSet rs;
        

        conectar();
        
        try {
            
            pst = conn.prepareStatement(query);
            pst.setString(1, usuario);
            rs = pst.executeQuery();

            if(rs.next())
            {
                hashText = rs.getString(1);
            }


            
        } catch (SQLException e) {
            System.err.println(e);
        }

        

        

        desconectar();

        return hashText;
    }

    public static String getHashTextPasswordFromText(String text)
    {
      String hashtext = null;
        try {
    
          // Static getInstance method is called with hashing MD5
          MessageDigest md = MessageDigest.getInstance("MD5");

          // digest() method is called to calculate message digest
          //  of an input digest() return array of byte
          byte[] messageDigest = md.digest(text.getBytes());

          // Convert byte array into signum representation
          BigInteger no = new BigInteger(1, messageDigest);

          // Convert message digest into hex value
          hashtext = no.toString(16);
          while (hashtext.length() < 32) {
              hashtext = "0" + hashtext;
          }
          
      }
      
      // For specifying wrong message digest algorithms
      catch (NoSuchAlgorithmException e) {
          throw new RuntimeException(e);
      }

      return hashtext;
    }

    public String generateMD5(String input)
    {

        String hashtext = null;


        try {
  
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
  
            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());
  
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
  
            // Convert message digest into hex value
            hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            
        }
        
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }


        return hashtext;

    }

    public static boolean comprobarPassword(String str1, String str2)
    {
        return str1.equals(str2);
    }

    public List<Entrada> getAllEntradas()
    {
        List<Entrada> entradas = new ArrayList<>();
        Entrada entrada;
        String titulo,texto;
        int id;
        LocalDate fecha;
        conectar();

        String sqlInsert = "SELECT * FROM entradas ORDER BY fecha DESC";
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                id = rs.getInt("id");
                titulo = rs.getString("titulo");
                texto = rs.getString("texto");
                fecha = LocalDate.parse(rs.getString("fecha"));
                entrada = new Entrada(id,titulo,texto,fecha);
                entradas.add(entrada);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


        desconectar();
        return entradas;
    }

    public Optional<Entrada> getEntradaById(String id)
    {
        Optional<Entrada> opEntrada = Optional.empty();
        Entrada entrada;
        String titulo,texto;
        int identificador;
        LocalDate fecha;

        conectar();
        String sql = "SELECT * FROM entradas WHERE id = ?";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);

            ResultSet rs = pst.executeQuery();

            if(rs.next())
            {
                
                identificador = rs.getInt("id");
                titulo = rs.getString("titulo");
                texto = rs.getString("texto");
                fecha = LocalDate.parse(rs.getString("fecha"));
                entrada = new Entrada(identificador,titulo,texto,fecha); 
                opEntrada = Optional.of(entrada);
            }


        } catch (SQLException e) {
            System.err.println(e);
            e.printStackTrace();
        }

        desconectar();
        return opEntrada;
    }


    

    public boolean insertarEntrada(Entrada entrada)
    {
        boolean exito = false;

        String sql = "INSERT INTO Entradas(titulo,texto,fecha) VALUES(?,?,?);";

        conectar();

        try {
            
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, entrada.getTitulo());
            pst.setString(2, entrada.getTexto());
            pst.setString(3, (entrada.getFecha() != null)?entrada.getFecha().toString():LocalDate.now().toString());

            if(pst.executeUpdate() > 0)
            {
                exito = true;
            }

            

        } catch (Exception e) {
            System.err.println(e);
            e.getStackTrace();
        }

        desconectar();

        return exito;
    }

    public boolean editarEntrada(Entrada entrada)
    {
        boolean exito = false;

        String sql = "UPDATE Entradas SET titulo = ?, texto = ?, fecha = ? WHERE id = ?";

        conectar();

        try {
            
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, entrada.getTitulo());
            pst.setString(2, entrada.getTexto());
            pst.setString(3, entrada.getFecha().toString());
            pst.setInt(4, entrada.getId());
            
            int res = pst.executeUpdate();

            if(res > 0)
            {
                exito = true;
            }

        } catch (SQLException e) {
            System.err.println(e);
            e.getStackTrace();
        }


        desconectar();
        return exito;
    }

    public boolean borrarEntrada(String idEntrada)
    {
        boolean exito = false;
        String sql = "DELETE FROM Entradas WHERE id = ?1";

        conectar();

        try {
            
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, idEntrada);
            
            
            int res = pst.executeUpdate();

            if(res > 0)
            {
                exito = true;
            }

        } catch (SQLException e) {
            System.err.println(e);
            e.getStackTrace();
        }


        desconectar();

        return exito;
    }


    public boolean insertarUsuario(Usuario usuario)
    {
        boolean exito = false;

        String sql = "INSERT INTO Usuarios(usuario,password,admin) VALUES(?,?,?)";

        conectar();

       if(usuario != null)
       {
        try {
            
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, usuario.getUsername());
            pst.setString(2, getHashTextPasswordFromText(usuario.getPassword()));
            pst.setInt(3, (usuario.getAdmin())?1:0);
            
            
            int res = pst.executeUpdate();

            if(res > 0)
            {
                exito = true;
            }

        } catch (SQLException e) {
            System.err.println(e);
            e.getStackTrace();
        }
       }


        desconectar();



        return exito;
    }


    public List<Usuario> getAllUsers()
    {

        List<Usuario> users = new ArrayList<>();
        Usuario user;
        String username,password;
        boolean admin;
        
        conectar();

        String sqlInsert = "SELECT * FROM Usuarios";
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                username = rs.getString("usuario");
                password = rs.getString("password");
                admin = (rs.getInt("admin") > 0);
                user = new Usuario(username,password,admin);
                users.add(user);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


        desconectar();
        return users;

    }

    public boolean updatePassword(String usuario, String newPasswd)
    {
        boolean exito = false;

        String sql = "UPDATE Usuarios SET password = ?1 WHERE usuario = ?2";
        String hashPasswd = getHashTextPasswordFromText(newPasswd);

        conectar();

        try {
            
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, hashPasswd);
            pst.setString(2, usuario);
            
            
            int res = pst.executeUpdate();

            if(res > 0)
            {
                exito = true;
            }

        } catch (SQLException e) {
            System.err.println(e);
            e.getStackTrace();
        }


        desconectar();

        return exito;

    }


    public static boolean isAdmin(String username)
    {
        boolean admin = false;
        Usuario usuario = getUsuario(username);

        if(usuario != null)
            admin = usuario.getAdmin();

        return admin;
    }

    public static boolean verificarSesion(String username, String password)
    {
        boolean verificado = false;

        String hashPasswd = getHashTextPasswordFromText(password);

        Usuario usuario = getUsuario(username);

        if(usuario != null)
        {
            if(usuario.getPassword().equals(hashPasswd))
            {
                verificado = true;
            }
        }


        return verificado;
    }

    public static Usuario getUsuario(String username)
    {
        Usuario usuario = null;

        String sql = "SELECT * FROM Usuarios WHERE usuario = ?";

        conectar();

        try {
            
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            
            ResultSet rs = pst.executeQuery();

            if(rs.next())
            {
                usuario = new Usuario(rs.getString("usuario"),rs.getString("password"),(rs.getInt("admin") > 0));
            }

        } catch (SQLException e) {
            System.err.println(e);
            e.getStackTrace();
        }


        desconectar();


        return usuario;
    }



}
