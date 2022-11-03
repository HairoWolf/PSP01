package Actividades_11_03;

import java.net.*;

public class Ej_02Ejemplo1URL {
    public static void main(String[] args) {
        URL url;
        try{
            System.out.println("=====ALARM=====Constructor simple para URL");
            url = new URL("http://www.as.com");
            Visualizar(url);

            System.out.println("Otro constructor simple para una URL");
            url = new URL("http://localhost:80/PFC/gest/ccli_gestion.php?S=3");
            Visualizar(url);

            System.out.println("Constructor para protocolo + URL + Direccion: ");
            url = new URL("http", "docs.oracle.com","/javase/7");
            Visualizar(url);

            System.out.println("Constructor para protocolo + URL + protocolo + directorio: ");
            url = new URL("http", "docs.oracle.com", 80, "/javase/7");
            Visualizar(url);

            System.out.println("Constructor para un objeto URL y un directorio: ");
            URL urlBase = new URL("http://docs.oracle.com/");
            url = new URL(urlBase, "/javase/7/docs/api/java/net/URL.html");
            Visualizar(url);
        }
        catch (MalformedURLException me){
            me.printStackTrace();
        }
    }//fin del main

    //Metodo para visualizar el uso de los metodos de la clase URL
    private static void Visualizar(URL url) {
        System.out.println("\tURL COMPLETA: "+url.toString());
        System.out.println("\tgetProtocol(): "+url.getProtocol());
        System.out.println("\tgetHost(): "+url.getHost());
        System.out.println("\tgetPort(): "+url.getPort());
        System.out.println("\tgetFile(): "+url.getFile());
        System.out.println("\tgetUserInfo(): "+url.getUserInfo());
        System.out.println("\tgetPath(): "+url.getPath());
        System.out.println("\tgetAuthority(): "+url.getAuthority());
        System.out.println("\tgetQuery(): "+url.getQuery());
        System.out.println("===============================================");
    }//fin del metodo visualizar

}
