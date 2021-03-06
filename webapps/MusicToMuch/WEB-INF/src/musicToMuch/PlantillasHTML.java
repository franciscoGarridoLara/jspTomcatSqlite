package musicToMuch;
import org.stringtemplate.v4.*;
import java.time.LocalDateTime;

//TODO: CARGAR POOPER JS Y BOOTSTRAP LOCALMENTE.
public class PlantillasHTML {

  public static final String HTML_INSTALLER_HEADER = """
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link href="./recursos/css/installer.css" rel="stylesheet">
        <link rel="icon" type="image/x-icon" href="./recursos/imagenes/icon.png">
        <title>Installer</title>
    </head>
    """;

    public static final String HTML_LOGIN_HEADER = """
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link href="./recursos/css/login.css" rel="stylesheet">
        <link rel="icon" type="image/x-icon" href="./recursos/imagenes/icon.png">
        <title>Login</title>
    </head>
    """;

    public static final String HTML_PANEL_HEADER = """
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link href="./recursos/css/panel.css" rel="stylesheet">
        <link rel="icon" type="image/x-icon" href="./recursos/imagenes/icon.png">
        <title>Panel de Control</title>
    </head>
    """;

    public static final String HTML_BLOG_HEADER = """
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link href="./recursos/css/blog.css" rel="stylesheet">
        <link rel="icon" type="image/x-icon" href="./recursos/imagenes/icon.png">
        <title>Blog</title>
    </head>
    """;


    public static final String HTML_FOOTER = """
    
    <footer >
        <div class="container">
            <div class="row">
            <p class="text-center">Creado por Francisco Garrido Lara &#169</p>
            <hr>
            <p class="text-center">Programaci??n de servicios y procesos</p>
            </div>
        </div>
    </footer>
    
    """;
    
    public static final String BOOTSTRAP_CSS = "<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3' crossorigin='anonymous'>";
    public static final String BUNDLE_POPPER_SCRIPT = "<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js' integrity='sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p' crossorigin='anonymous'></script>";
    public static final String CERRAR_SESSION_BTN = """
        <form class="d-flex">
            <a class="btn btn-danger" href="">Cerrar Sesion</a>
        </form>

    """;

    public static final String SCRIPT_CARGA = """
    <script>
    function cargar()
    {
        window.location.replace("http://localhost:8080/MusicToMuch/inicioSesion");

    }


    document.addEventListener('DOMContentLoaded', function(event){
        console.log('Documento cargado, redireccionando...');
        setTimeout(cargar,4000);
    });
    </script>
    """;

    public static final String NAVBAR_TEMPLATE = """
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container-fluid">
    <a class="navbar-brand" href="./inicio">MusicToMuch</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="./inicioSesion">Login</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="./blog">Entradas</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="./panelControl">Panel de control</a>
        </li>
        </ul>
        <form class="d-flex">
            <a class="btn btn-danger" href="./cerrar">Cerrar Sesion</a>
        </form>
    </div>
    </div>
</nav>
    
    """;
    
    
    public static ST NAVBAR = new ST(NAVBAR_TEMPLATE);

    public static final String LOGIN_CONTAINER = """
    
    <div class="container container-login">
    <div class="row">
        <div class="col-md-4 offset-md-5 col-4 offset-5">
            <img src="./recursos/imagenes/ondas-sonoras.png" class="logo">
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 offset-md-4 col-4 offset-4">
            <form action="inicioSesion" method="POST">
                <div class="mb-3">
                  <label for="usuario" class="form-label fs-3 fw-bold">Usuario</label>
                  <input type="text" class="form-control" id="usuario" name = "usuario" aria-describedby="Introduce tu nombre de usuario" placeholder="Introduce tu nombre de usuario">
                  
                </div>
                <div class="mb-3">
                  <label for="password" class="form-label fs-3 fw-bold">Password</label>
                  <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                </div>
                <div class="mb-3 offset-mb-4"> 
                  <button type="submit" class="btn btn-primary text-center">Iniciar Sesion</button>
                </div>
                
                
              </form>
        </div>

    </div>

  </div>
    
    
    
    """;


    public static final String CONTAINER_PANEL_PASSWD = """ 
    
    <div class="container container-passwd">
            <h1 class="text-center titulo">Cambiar Contrase??a</h1>
            <form class="" action="./cambiarPasswd" method="POST">
                <div class="mb-3 col-4 offset-4">
                    <label for="pass1" class="form-label">Contase??a actual</label>
                    <input type="password" class="form-control" id=""  name="pass1" required>
                  </div>
            
                <div class="mb-3 col-4 offset-4">
                  <label for="pass2" class="form-label">Contrase??a nueva</label>
                  <input type="password" class="form-control" id="" name="pass2" required>
                </div>
                <button type="submit" class="btn btn-primary offset-4 col-4">Submit</button>
              </form>
          </div>

    
    
    
    """;

    public static final String CONTAINER_PANEL_HOME = """ 
    
    <div class="container container-passwd">
            <h1 class="text-center titulo">Usuarios</h1>
            <table class="table text-center">
              <thead>
                <tr>
                  <th scope="col">User</th>
                  <th scope="col">Borrar</th>
                </tr>
              </thead>
              <tbody>
                $usuarios$
              </tbody>
            </table>
            <form action="./insertarUsuario" method="post">
              <input type="submit" name="insertar" value="Insertar usuario" class="btn btn-warning">
            </form>
            
    </div>

    
    
    
    """;


    public static final String CONTAINER_PANEL_ENTRADAS = """ 
    
    <div class="container container-entradas">
            <h1 class="text-center titulo">Entradas</h1>
            <table class="table text-center">
              <thead>
                <tr>
                  <th scope="col">Entrada</th>
                  <th scope="col">Edicion</th>
                </tr>
              </thead>
              <tbody>
                  $entradas_td$
              </tbody>
            </table>
            <form action="./editar?insertar=true" method="post">
              <input type="submit" name="insertar" value="Insertar entrada" class="btn btn-warning">
            </form>
            
    </div>

    
    
    
    """;

    public static final String PANEL_SIDEBAR = """ 
    
    <div class="d-flex flex-column flex-shrink-0 p-3 text-white bg-dark side-bar" style="width: 280px;">
    <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
      <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"></use></svg>
      <span class="fs-4">Sidebar</span>
    </a>
    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
      <li class="nav-item">
        <a href="./panelControl?pag=home" class="nav-link active" aria-current="page">
          <svg class="bi me-2" width="16" height="16"><use xlink:href="#home"></use></svg>
          Usuarios
        </a>
      </li>
      <li>
        <a href="./panelControl?pag=chgPasswd" class="nav-link text-white">
          <svg class="bi me-2" width="16" height="16"><use xlink:href="#speedometer2"></use></svg>
          Cambiar Contrase??a
        </a>
      </li>
      <li>
        <a href="./panelControl?pag=entradas" class="nav-link text-white">
          <svg class="bi me-2" width="16" height="16"><use xlink:href="#grid"></use></svg>
          Entradas
        </a>
      </li>
    </ul>
    <hr>
  </div>
    
    
    
    """;


    public static final String BOOTSTRAP_RED_ALERT = "<div class='alert alert-warning' role='alert'>" + "Login Fallido " + LocalDateTime.now() + " </div>";
   
    
    public static final String CONTAINER_BLOG = """
    
    
    <div class="container">
    <h1 class="text-center text-white">Blog</h1>
      <div class="d-flex flex-wrap">
        $entradas$
      </div>
    </div>

    
    
    """;

    public static final String FORM_EDITAR = 
    """
    <form action="/editar" method="post">
            <input type="hidden" name="idEntrarda" value="$id$">
            <input type="submit" value="Editar" class="btn btn-primary">
        </form>
    """;

    public static final String FORM_INSERTAR_USUARIO = """
    <h2 class="text-center" style="color:white;">Creacion de Usuario</h2>
    <form action="./insertarUsuario?insertar=true" method="POST">
    <div class="mb-3">
      <label class="form-label">Username</label>
      <input type="text" class="form-control" name="username" id="username" placeholder="Nombre de usuario" required>
    </div>
    <div class="mb-3">
      <label class="form-label">Contrase??a</label>
      <input type="text" class="form-control" name="password" id="password" placeholder="Contrase??a" required>
    </div>
    <div class="form-check">
    <input class="form-check-input" type="checkbox" id="isadmin" name="isadmin" value="true" checked>
    <label class="form-check-label">Admin?</label>
    </div>
    
    <button type="submit" class="btn btn-warning">Crear</button>
  </form>


    """;

    public static final String ADMIN_CHECKBOX = """

    <div class="form-check">
    <input class="form-check-input" type="checkbox" id="isadmin" name="isadmin" value="true" checked>
    <label class="form-check-label">Admin?</label>
    </div>
    


    """;
    public static final String TR_ENTRADA = """
    <tr>
    <td>$nombre_entrada$</td>
    <td><form action="./editar" method="post">
          <input type="hidden" name="idEntrada" value="$id$">
          <input type="submit" value="editar" class="btn btn-primary">
          <input type="submit" name="borrar" value="Borrar" class="btn btn-danger">
        </form>
    </td>
    </tr>
    """;


    public static final String TR_USUARIO = """
    <tr>
    <td>$nombre_usuario$</td>
    <td><form action="./borrarUsuario" method="POST">
          <input type="hidden" name="username" id="username" value="$username$">
          <input type="submit" value="Borrar" class="btn btn-danger">
        </form>
    </td>
    </tr>
    """;


    public static final String FORM_ENTRADA = """ 
    
    <form action="./editar?update=true" method="POST">
    <input type="hidden" name="id" id="id" value="$id$">
    <div class="mb-3">
      <label class="form-label">Titulo</label>
      <input type="text" class="form-control" name="titulo" id="titulo" value="$titulo$">
    </div>
    <div class="mb-3">
      <label class="form-label">Contenido</label>
      <textarea type="text" class="form-control" name="contenido" id="contenido" value="">$contenido$</textarea>
    </div>
    <div class="mb-3">
      <input type="date" class="form-control" name="fecha" id="fecha" value="$fecha$">
    </div>
    <button type="submit" class="btn btn-warning">Editar</button>
  </form>
    
    
    
    """;

    public static final String FORM_ENTRADA_INSERTAR = """ 
    <h2 class="text-center" style="color:white;">Insertar Entrada</h2>
    <form action="./editar?insertar=entrada" method="POST">
    
    <div class="mb-3">
      <label class="form-label">Titulo</label>
      <input type="text" class="form-control" name="titulo" id="titulo" value="">
    </div>
    <div class="mb-3">
      <label class="form-label">Contenido</label>
      <textarea type="text" class="form-control" name="contenido" id="contenido" value=""></textarea>
    </div>
    <div class="mb-3">
      <input type="date" class="form-control" name="fecha" id="fecha" value="">
    </div>
    <button type="submit" class="btn btn-warning">Insertar</button>
  </form>
    
    
    
    """;
    public static final String INSTALLER_SPINNER = """ 
    
    <div class="container">
      <div class="text-center">
        <div class="spinner-border" role="status">
        </div>
        <p>Configurando...</p>
      </div>
    </div>
    
    
    """;

    public static final String SUCCESS_ALERT = """

    <div class="alert alert-success" role="alert">
    Contrase??a cambiada correctamente.
    </div>


    """;


    public static final String DANGER_ALERT = """

    <div class="alert alert-danger" role="alert">
    Ha habido un error cambiando la contrase??a.
    </div>


    """;

    public static final String INDEX_PAGE = """

    <!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="./recursos/css/index.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="icon" type="image/x-icon" href="./recursos/imagenes/icon.png">
  <title>MusicToMuch</title>
</head>

<body>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container-fluid">
      <a class="navbar-brand" href="./inicio">MusicToMuch</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="./inicioSesion">Login</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="./blog">Entradas</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="./panelControl">Panel de control</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <div style="text-align:center; position: fixed; top: 10vh; right: 1vh; background-color: white; border-radius: 1em; width: 20vh; height: 5vh; box-shadow: rgba(0, 0, 0, 0.25) 0px 54px 55px, rgba(0, 0, 0, 0.12) 0px -12px 30px, rgba(0, 0, 0, 0.12) 0px 4px 6px, rgba(0, 0, 0, 0.17) 0px 12px 13px, rgba(0, 0, 0, 0.09) 0px -3px 5px;">
    <h2>$visitas$ visitas</h2>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
    crossorigin="anonymous"></script>
</body>

<footer>
  <div class="container">
    <div class="row">
      <p class="text-center">Creado por Francisco Garrido Lara &#169</p>
      <hr>
      <p class="text-center">Programaci??n de servicios y procesos</p>
    </div>
  </div>
</footer>

</html>



    """;
    public static final String OPEN_CONTAINER = "<div class='container'>";
    public static final String CLOSE_DIV = "</div>";
    public static final String OPEN_BODY = "<body>";
    public static final String CLOSE_BODY = "</body>";
    public static final String OPEN_MAIN = "<main>";
    public static final String CLOSE_MAIN = "</main>";
    public static final String OPEN_HTML = "<html lang='es'>";
    public static final String CLOSE_HTML = "</html>";
    public static final String DOCTYPE = "<!doctype html>";



 

}
