package Actividades_11_08;

import java.net.*;
import java.io.*;
import java.util.*;

public class Servidor {

    private final int PUERTO = 5000;
    private ServerSocket server;
    private Socket socket;
    private DataInputStream entrada;
    private String mensajeRecibido;

    public static void main(String[] args) {
        Servidor s = new Servidor();
        s.initServer();
    }

    public void initServer(){
        try {
            server = new ServerSocket(PUERTO);
            socket = new Socket();

            System.out.println("Waiting connection....");
            socket = server.accept();
            entrada = new DataInputStream(socket.getInputStream());
            System.out.println("Cliente connected....");
            mensajeRecibido = entrada.readUTF();
            System.out.println("El cliente dice.... " + mensajeRecibido);

            entrada.close();
            socket.close();
            server.close();
            System.out.println("End connection");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
