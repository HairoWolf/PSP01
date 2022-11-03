package Actividades_11_03;

import peval1psp2223.GMethods;

import java.net.*;

public class Ej_01TestInetAddress {
    public static void main(String[] args) {
        InetAddress dir = null;
        System.out.println("=============================");
        System.out.println("SALIDA PARA EL LOCALHOST: ");
        try{
            //LOCALHOST
            dir = InetAddress.getByName("localhost");
            pruebaMetodos(dir);

            //URL
            System.out.println("=============================");
            System.out.println("SALIDA PARA UNA URL: ");
            dir = InetAddress.getByName("www.youtube.es");
            pruebaMetodos(dir);

            //Mostrar todas las direcciones de una URL mediante un array de tipo InetAddress---
            //--- en este caso vamos a obtener todas las direcciones asociadas a google
            System.out.println("=============================");
            System.out.println("DIRECCIONES IP PARA: " + dir.getHostName());
            InetAddress [] direcciones = InetAddress.getAllByName(dir.getHostName());
            for (int i = 0; i < direcciones.length; i++) {
                System.out.println(direcciones[i].toString());
            }
            System.out.println("=============================");
        }
        catch(UnknownHostException ue){
            ue.printStackTrace();
        }
    }//fin del main

    //Metodo que prueva distintos metodos del paquete java.net
    private static void pruebaMetodos(InetAddress dir){
        System.out.println("Metodo getByName(): " + dir);
        InetAddress dir2;
        try{
            dir2 = InetAddress.getLocalHost();
            System.out.println("Metodo getLocalHost(): " + dir2);
        }
        catch (UnknownHostException ue){
            ue.printStackTrace();
        }
        //Otros metodos de la clase
        System.out.println("Metodo getHostName(): " + dir.getHostName());
        System.out.println("Metodo getHostAddress(): " + dir.getHostAddress());
        System.out.println("Metodo toString(): " + dir.toString());
        System.out.println("Metodo getCanonicalHostName(): " + dir.getCanonicalHostName());
    }//fin del metodo pruebaMetodos
}//Fin programa
