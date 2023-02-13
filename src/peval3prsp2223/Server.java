package peval3prsp2223;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Javier Tienda González
 * @version 1.0
 * @date 13/02/2023
 *
 * Server that creates a pair of asymmetric keys, set a connection and through it receives an encrypted message, decrypts it and prints it on the screen
 */

public class Server {
    final int PORT = 5000;
    ServerSocket serverSocket;
    Socket clientSocket;
    DataInputStream input;
    DataOutputStream output;
    public final static File PUBLICKEYPATH = new File("src/peval3prsp2223/PublicKey.txt");
    private final File PRIVATEKEYPATH = new File("src/peval3prsp2223/PrivateKey.txt");

    public static void main(String[] args) {
        Server server = new Server();
        server.initServer();
    }

    public void initServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            clientSocket = new Socket();

            //Claves asimétricas
            AsymmetricKeyGenerator.generateKeysAndSaveToFile(PUBLICKEYPATH, PRIVATEKEYPATH);
            GMethods.printDiv();
            GMethods.printSuccess("Claves asimétricas creadas");
            GMethods.printDiv();

            //Conexión del cliente
            GMethods.println("Esperando conexión...");
            clientSocket = serverSocket.accept();
            input = new DataInputStream(clientSocket.getInputStream());
            output = new DataOutputStream(clientSocket.getOutputStream());
            GMethods.printSuccess("Cliente conectado");
            GMethods.printDiv();

            //Clave del cliente
            int keySize = input.readInt();
            byte[] encryptedKey = input.readNBytes(keySize);
            GMethods.println("Clave cifrada recibida");

            //Descifrado de la clave del cliente
            byte[] decryptedKey = AsymmetricDecrypter.decryptMessage(PRIVATEKEYPATH, encryptedKey);
            GMethods.printSuccess("Clave descifrada");
            GMethods.printDiv();

            //Mensaje
            int messageSize = input.readInt();
            byte[] encryptedMessage = input.readNBytes(messageSize);
            GMethods.println("Mensaje cifrado recibido");

            //Descifrado del mensaje
            String decryptedMessage = new String(SymmetricDecrypter.decryptMessage(decryptedKey, encryptedMessage));
            GMethods.printSuccess("Mensaje descifrado: " + decryptedMessage);
            GMethods.printDiv();

            //Cerramos todas las conexiones y las comunicaciones
            input.close();
            output.close();
            clientSocket.close();
            serverSocket.close();
            GMethods.println("Servidor cerrado");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}