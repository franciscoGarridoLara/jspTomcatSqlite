
function cargar()
{
    window.location.replace("http://localhost:8080/MusicToMuch/inicioSesion");

}


document.addEventListener('DOMContentLoaded', function(event){
    console.log('DOM fully loaded and parsed');
    setTimeout(cargar,3000);
});