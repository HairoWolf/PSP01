package peval3prsp2223;

import javax.crypto.*;
import java.io.File;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Javier Tienda González
 * @version 1.0
 * @date 13/02/2023
 *
 * @info Class that is able to decrypt messages using an asymmetric key.
 */

public class AsymmetricDecrypter {

    public AsymmetricDecrypter() {
    }

    public static byte[] decryptMessage(File privateKeyPath, byte[] message) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, AsymmetricKeyGenerator.getPrivateKey(privateKeyPath));
            return cipher.doFinal(message);
        }
        catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException |
                 IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }
}