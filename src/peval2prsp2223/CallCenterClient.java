package peval2prsp2223;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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
    private static CallCenterClient myClient;

    public static void main(String[] args) {
        myClient = new CallCenterClient();
        GMethods.println("Iniciando el cliente");
        try {
            myClient.initClient();
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
            if(readMessageServerAvailable()){
                writeMessage();
                readMessage();
                int numConsulta = GMethods.keyBInt();
                salida.writeInt(numConsulta);
                myClient.runClient();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Ejecuta el bucle de consulta del cliente
     */
    public void runClient() {
        boolean loop = true;
        while (loop) {
            try {
                readMessage();
                GMethods.println("Escribe mensaje");
                writeMessage();
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
            readMessage();
            cliente.close();// cierre del socket
            GMethods.println("Te has desconectado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lee los mensajes que le envia el servidor
     * @throws IOException
     */
    private void readMessage() throws IOException {
        mensajeARecibir = entrada.readUTF();
        System.out.println(mensajeARecibir);
    }

    /**
     * Lee el primer mensaje y identifica si el servidor esta completo o no
     * @return boolean que confirma si puede crear una comunicacion con el server
     * @throws IOException
     */
    private boolean readMessageServerAvailable() throws IOException {
        boolean serverAvailable;
        mensajeARecibir = entrada.readUTF();
        System.out.println(mensajeARecibir);
        if(mensajeARecibir.equals("El servidor esta lleno")){
            serverAvailable = false;
        }
        else{
            serverAvailable = true;
        }
        return serverAvailable;
    }

    /**
     * Escribe el texto que va a enviar al servidor
     * @throws IOException
     */
    private void writeMessage() throws IOException {
        mensajeAEnviar = GMethods.keyBString();
        salida.writeUTF(mensajeAEnviar);
    }
}
