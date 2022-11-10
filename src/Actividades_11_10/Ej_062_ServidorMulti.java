package Actividades_11_10;

import java.io.IOException;
import java.net.ServerSocket;

public class Ej_062_ServidorMulti {

    public static void main(String[] args) {
        try{
            ServerSocket servidor;
            servidor = new ServerSocket(6000);
            System.out.println("Servidor iniciado....");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
