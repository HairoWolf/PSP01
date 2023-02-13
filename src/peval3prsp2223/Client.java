package peval3prsp2223;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Javier Tienda González
 * @version 1.0
 * @date 13/02/2023
 *
 * Client that creates a symmetric key, set a connection and through it sends an encrypted message by its symmetric key encrypted by the public key created by the server
 */

public class Client {
    final int PORT = 5000;
    Socket clientSocket;
    DataOutputStream output;

    public static void main(String[] args) {
        Client client = new Client();
        client.initClient();
    }

    public void initClient() {
        try {
            clientSocket = new Socket("localhost", PORT);
            output = new DataOutputStream(clientSocket.getOutputStream());

            //Clave simétrica del cliente
            byte[] symmetricKey = SymmetricKeyGenerator.generateKey();
            GMethods.printDiv();
            GMethods.println("Clave simétrica creada");

            //Cifrado de la clave simétrica con la clave pública del servidor
            byte[] encryptedSymmetricKey = AsymmetricEncryptor.encryptMessage(Server.PUBLICKEYPATH, symmetricKey);
            int encryptedSymmetricKeySize = encryptedSymmetricKey.length;
            GMethods.printSuccess("Clave simétrica cifrada");

            //Envió de la clave del cliente cifrada
            output.writeInt(encryptedSymmetricKeySize); //Envió tamaño del mensaje
            output.write(encryptedSymmetricKey);
            GMethods.println("Clave simétrica enviada");
            GMethods.printDiv();

            //Mensaje a cifrar
            GMethods.println("Escribe el mensaje a enviar: ");
            String message = GMethods.keyBString();

            //Cifrado del mensaje
            byte[] encryptedMessage = SymmetricEncryptor.encryptMessage(symmetricKey, message);
            int encryptedMessageSize = encryptedMessage.length;
            GMethods.printSuccess("Mensaje cifrado");

            //Envió del mensaje cifrado
            output.writeInt(encryptedMessageSize); //Envió tamaño del mensaje
            output.write(encryptedMessage);
            GMethods.println("Mensaje enviado");
            GMethods.printDiv();

            //Cerramos todas las conexiones y las comunicaciones
            output.close();
            clientSocket.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}