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
    private String mensaje;
    private DataOutputStream salida;

    public static void main(String[] args) {
        Servidor s = new Servidor();
        s.initServer();
    }

    public void initServer(){
        try {
            server = new ServerSocket(PUERTO);
            socket = new Socket();
            boolean loop = true;

            System.out.println("Waiting connection....");
            socket = server.accept();
            System.out.println("Cliente connected....");

            while(loop) {
                entrada = new DataInputStream(socket.getInputStream());
                salida = new DataOutputStream(socket.getOutputStream());
                mensajeRecibido = entrada.readUTF();
                if(mensajeRecibido.equals("salir")){
                    loop = false;
                }
                else {
                    System.out.println("El cliente dice.... " + mensajeRecibido);
                    System.out.println("===========================");
                    System.out.println("Write message..... (salir : para terminar la comunicacion)");
                    Scanner sc = new Scanner(System.in);
                    mensaje = sc.nextLine();
                    if (mensaje.equals("salir")) {
                        loop = false;
                        salida.writeUTF(mensaje);
                    } else {
                        salida.writeUTF(mensaje);
                        System.out.println("Message send");
                        System.out.println("===========================");
                    }
                }
            }
            salida.close();
            entrada.close();
            socket.close();
            server.close();
            System.out.println("End connection");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
