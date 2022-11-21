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

    private Socket cliente;
    private String tipoConsulta;
    private String nombre;
    private DataInputStream entrada;
    private DataOutputStream salida;

    public CallCenterThread(Socket cliente, String tipoConsulta, String nombre) {
        this.cliente = cliente;
        this.tipoConsulta = tipoConsulta;
        this.nombre = nombre;
        try {
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());
        }
        catch (IOException e) {
            GMethods.printError("Error de entrada y salida");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        int tiempoInicio = (int) System.currentTimeMillis();
        switch (tipoConsulta) {
            case "Futurologia":
                try {
                    salida.writeUTF("Estás en la consulta de Futurología (salir: para finalizar la conexion)");
                    realizarConsulta();

                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "Meeting":
                try {
                    salida.writeUTF("Estás en la consulta de Meeting (salir: para finalizar la conexion)");
                    realizarConsulta();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "Compras":
                try {
                    salida.writeUTF("Estás en la consulta de Compras (salir: para finalizar la conexion)");
                    realizarConsulta();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
        try {
            int tiempoTotal = (int) (System.currentTimeMillis() - tiempoInicio) / 60000;
            float precio = (float) (tiempoTotal * 1.20);
            salida.writeUTF(nombre + " debe pagar " + precio + "€ (Estancia: " + tiempoTotal + " minuto(s))");
            GMethods.println(nombre + " ha desconectado");
            cliente.close();
            int conActuales = CallCenterServer.getConActuales() - 1;
            GMethods.println(conActuales + "");
            CallCenterServer.setConActuales(conActuales);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void realizarConsulta() throws IOException {
        boolean sigue = true;
        String mensajeRecibido;
        while (sigue) {
            mensajeRecibido = entrada.readUTF();
            if (!mensajeRecibido.equalsIgnoreCase("salir")) {
                GMethods.println(nombre + " dice: " + mensajeRecibido);
                GMethods.println("¿Qué respondes?");
                salida.writeUTF(GMethods.keyBString());
            }
            else {
                sigue = false;
            }
        }
    }
}
