package Actividades_11_08;

import java.net.*;
import java.io.*;
import java.util.*;

public class Cliente {

    private final int PUERTO = 5000;
    private Socket cliente;
    private String mensaje;
    private DataOutputStream salida;
    private DataInputStream entrada;
    private String mensajeRecibido;

    public static void main(String[] args) {
        Cliente c = new Cliente();
        c.initClient();
    }

    /**
     * Inicializa el cliente
     */
    public void initClient(){
        try {
            boolean loop = true;
            cliente = new Socket("localhost", PUERTO);
            while(loop) {
                System.out.println("Write message..... (salir : para terminar la comunicacion)");
                Scanner sc = new Scanner(System.in);
                mensaje = sc.nextLine();
                salida = new DataOutputStream(cliente.getOutputStream());
                entrada = new DataInputStream(cliente.getInputStream());
                if(mensaje.equals("salir")){
                    loop = false;
                    salida.writeUTF(mensaje);
                }
                else {
                    salida.writeUTF(mensaje);
                    System.out.println("Message send");
                    System.out.println("===========================");
                    mensajeRecibido = entrada.readUTF();
                    if(mensajeRecibido.equals("salir")){
                        loop = false;
                    }
                    else {
                        System.out.println("El servidor dice.... " + mensajeRecibido);
                        System.out.println("===========================");
                    }
                }
            }
            entrada.close();
            salida.close();
            cliente.close();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
