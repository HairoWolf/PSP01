package peval2prsp2223;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author Javier Tienda González
 * @version 1.0
 * @date 22/11/2022
 * @info Client that establishes a connection with the server and allows communication
 */

public class CallCenterClient {

    private final int PUERTO = 5000;
    private Socket cliente;
    private String mensajeAEnviar;
    private String mensajeARecibir;
    private DataOutputStream salida;
    private DataInputStream entrada;

    public static void main(String[] args) {
        CallCenterClient myClient = new CallCenterClient();
        GMethods.println("Iniciando el cliente");
        try {
            myClient.initClient();
            myClient.ejecutar();
        }
        catch (RuntimeException e) {
            GMethods.printError("El servidor esta cerrado");
        }
    }

    /**
     * Inicializa el cliente
     */
    public void initClient() {
        try {
            cliente = new Socket("localhost", PUERTO);
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());
            leerTexto();
            escribirTexto();
            leerTexto();
            int numConsulta = GMethods.keyBInt();
            salida.writeInt(numConsulta);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ejecutar() {
        boolean loop = true;
        while (loop) {
            try {
                leerTexto();
                GMethods.println("Que respondes?");
                escribirTexto();
                if (mensajeAEnviar.equalsIgnoreCase("salir")) {
                    loop = false;
                }

            }
            catch (IOException e) {
                // error provocado por el cierre del servidor
                GMethods.printError("Se ha cerrado el servidor");
                loop = false; // sale del bucle
            }
        }
        try {
            leerTexto();
            cliente.close();// cierre del socket
            GMethods.println("Te has desconectado");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }// fin de ejecutar

    private void leerTexto() throws IOException {
        mensajeARecibir = entrada.readUTF();
        System.out.println( mensajeARecibir);
    }

    private void escribirTexto() throws IOException {
        mensajeAEnviar = GMethods.keyBString();
        salida.writeUTF(mensajeAEnviar);
    }
}
