package Actividades_11_08;

import java.net.*;
import java.io.*;
import java.util.*;

public class Cliente {

    private final int PUERTO = 5000;
    private Socket cliente;
    private String mensaje;
    private DataOutputStream salida;

    public static void main(String[] args) {
        Cliente c = new Cliente();
        c.initClient();
    }

    /**
     * Inicializa el cliente
     */
    public void initClient(){
        try {
            cliente = new Socket("192.168.1.230", PUERTO);
            System.out.println("Write message.....");
            Scanner sc = new Scanner(System.in);
            mensaje = sc.nextLine();
            salida = new DataOutputStream(cliente.getOutputStream());
            salida.writeUTF(mensaje);
            System.out.println("Message send");
            salida.close();
            cliente.close();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
