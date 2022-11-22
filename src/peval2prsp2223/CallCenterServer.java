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
            while (true) {
                Socket clientSocket;
                clientSocket = servidor.accept();
                conActuales++;
                Thread hilo = new Thread(new CallCenterThread(clientSocket, conActuales , CONMAXIMAS));
                hilo.start();
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
