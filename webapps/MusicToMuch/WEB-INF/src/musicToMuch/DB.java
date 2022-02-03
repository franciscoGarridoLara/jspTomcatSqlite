package musicToMuch;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
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
               String sql = "CREATE TABLE IF NOT EXISTS usuarios (usuario TEXT PRIMARY KEY, password TEXT);";
               Statement stmt = conn.createStatement();
               stmt.execute(sql);
   
               // Se insertan los datos
               String sqlInsert = "INSERT INTO usuarios(usuario, password) VALUES(?,?)";
               PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
               String usuario = "admin";
               String password = "admin";
   
               
               pstmt.setString(1, usuario);
               pstmt.setString(2, generateMD5(password));
               pstmt.executeUpdate();
   
               sql = "CREATE TABLE IF NOT EXISTS entradas (id INTEGER PRIMARY KEY AUTOINCREMENT,titulo TEXT,texto TEXT,fecha INTEGER);";
   
   
               
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

        String sqlInsert = "SELECT * FROM entradas";
        
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


    

    public boolean insertarEntrada(Entrada entrada)
    {
 
        return false;
    }

    public boolean editarEntrada(Entrada entrada)
    {
 
        return false;
    }

    public boolean borrarEntrada(Entrada entrada)
    {
 
        return false;
    }


    public boolean insertarUsuario(Usuario usuario)
    {

        return false;
    }



}
