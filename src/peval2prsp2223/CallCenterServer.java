package peval2prsp2223;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Javier Tienda González
 * @version 1.0
 * @date 22/11/2022
 * @info Server that receive a request connection
 */

public class CallCenterServer {

    private static ServerSocket servidor;
    private static final int PUERTO = 5000; // puerto por el que vamos a abrir la conexion
    private static int conActuales = 0; // num de conexiones actuales activas
    private static int CONMAXIMAS = 3; // maximo de conexiones permitidas
    private DataOutputStream salida;
    private DataInputStream entrada;
    private String[] consultas = {"Futurologia", "Meeting", "Compras"};

    public static void main(String[] args) {
        CallCenterServer myServer = new CallCenterServer();
        myServer.initServer();
    }

    /**
     * Inicializa el servidor
     */
    public void initServer(){
        try {
            servidor = new ServerSocket(PUERTO);
            GMethods.println("Se ha iniciado el servidor");
            while (conActuales < CONMAXIMAS) {
                Socket clientSocket;
                clientSocket = servidor.accept();

                entrada = new DataInputStream(clientSocket.getInputStream());
                salida = new DataOutputStream(clientSocket.getOutputStream());

                salida.writeUTF("Introduce un nombre: ");
                String nombre = entrada.readUTF();

                GMethods.println(nombre + " seleccionando tipo de consulta...");
                String mensajeSelConsulta = "Elija consulta: ";

                for (int i = 0; i < consultas.length; i++) {
                    mensajeSelConsulta += "\n" + i + ". " + consultas[i];
                }
                salida.writeUTF(mensajeSelConsulta);

                int numTipoConsulta = entrada.readInt();

                GMethods.println("El " + nombre + " ha seleccionado " + consultas[numTipoConsulta]);
                conActuales++;
                Thread hilo = new Thread(new CallCenterThread(clientSocket, consultas[numTipoConsulta], nombre));
                hilo.start();
            }
            if (!servidor.isClosed()) {
                try {
                    //TODO
                    GMethods.printError("El servidor se ha llenado y ya no acepta más clientes");
                    servidor.close();

                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Getters y setter de los campos de clase
     */
    public static int getConActuales() {
        return conActuales;
    }

    public static void setConActuales(int conActuales) {
        CallCenterServer.conActuales = conActuales;
    }
}
