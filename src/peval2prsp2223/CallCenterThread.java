package peval2prsp2223;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Javier Tienda González
 * @version 1.0
 * @date 22/11/2022
 * @info Server that receive a request connection
 */

public class CallCenterThread implements Runnable {

    private Socket clientSocket;
    private String tipoConsulta;
    private String nombre;
    private int conActuales; // num de conexiones actuales activas
    private int CONMAXIMAS; // maximo de conexiones permitidas
    private DataInputStream entrada;
    private DataOutputStream salida;
    private String[] consultas = {"Futurologia", "Meeting", "Compras"};

    /**
     * Constructor de clase del hilo parametrizado
     * @param clientSocket el socket del cliente (Socket)
     * @param conActuales numero de conexiones (int)
     * @param CONMAXIMAS maximo numero de conexiones (int)
     */
    public CallCenterThread(Socket clientSocket, int conActuales, int CONMAXIMAS) {
        this.clientSocket = clientSocket;
        this.conActuales = conActuales;
        this.CONMAXIMAS = CONMAXIMAS;
    }

    /**
     * Metodo que se comunica con el cliente
     */
    @Override
    public void run() {
        if(conActuales > CONMAXIMAS){
            try {
                salida = new DataOutputStream(clientSocket.getOutputStream());
                salida.writeUTF("El servidor esta lleno");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                entrada = new DataInputStream(clientSocket.getInputStream());
                salida = new DataOutputStream(clientSocket.getOutputStream());

                salida.writeUTF("Introduce un nombre: ");
                this.nombre = entrada.readUTF();

                GMethods.println(nombre + " seleccionando tipo de consulta...");
                GMethods.println("IP de " + nombre + " : " + clientSocket.getInetAddress());
                GMethods.println("Puerto de "+ nombre + " : " + clientSocket.getPort());
                String mensajeSelConsulta = "Elija consulta: ";

                for (int i = 0; i < consultas.length; i++) {
                    mensajeSelConsulta += "\n" + i + ". " + consultas[i];
                }
                salida.writeUTF(mensajeSelConsulta);

                int numTipoConsulta = entrada.readInt();
                this.tipoConsulta = consultas[numTipoConsulta];

                GMethods.println("El " + nombre + " ha seleccionado " + consultas[numTipoConsulta]);

            } catch (IOException e) {
                GMethods.printError("Error de entrada y salida");
                e.printStackTrace();
            }
            int tiempoInicio = (int) System.currentTimeMillis();
            switch (tipoConsulta) {
                case "Futurologia":
                    try {
                        salida.writeUTF("Estás en la consulta de Futurología (salir: para finalizar la conexion)");
                        executeQuery();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Meeting":
                    try {
                        salida.writeUTF("Estás en la consulta de Meeting (salir: para finalizar la conexion)");
                        executeQuery();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Compras":
                    try {
                        salida.writeUTF("Estás en la consulta de Compras (salir: para finalizar la conexion)");
                        executeQuery();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            try {
                int tiempoTotal = (int) (System.currentTimeMillis() - tiempoInicio) / 60000;
                float precio = (float) (tiempoTotal * 1.20);
                salida.writeUTF(nombre + " debe pagar " + precio + "€ (Estancia: " + tiempoTotal + " minuto(s))");
                GMethods.println(nombre + " ha desconectado");
                clientSocket.close();
                CallCenterServer.setConActuales(conActuales--);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metodo que realiza el bucle de comunicacio entre el hilo y el cliente
     * @throws IOException
     */
    private void executeQuery() throws IOException {
        boolean loop = true;
        String mensajeRecibido;
        while (loop) {
            mensajeRecibido = entrada.readUTF();
            if (!mensajeRecibido.equalsIgnoreCase("salir")) {
                GMethods.println(nombre + " dice: " + mensajeRecibido);
                GMethods.println("Escribe mensaje para " + this.nombre);
                salida.writeUTF(GMethods.keyBString());
            }
            else {
                loop = false;
            }
        }
    }
}
